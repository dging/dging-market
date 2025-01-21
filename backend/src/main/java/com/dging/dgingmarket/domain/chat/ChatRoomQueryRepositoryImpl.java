package com.dging.dgingmarket.domain.chat;

import com.dging.dgingmarket.domain.store.Store;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.web.api.dto.ImagesResponse;
import com.dging.dgingmarket.web.api.dto.QImagesResponse;
import com.dging.dgingmarket.web.api.dto.chat.ChatRoomResponse;
import com.dging.dgingmarket.web.api.dto.chat.QChatRoomResponse;
import com.dging.dgingmarket.web.api.dto.product.QProductResponse;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dging.dgingmarket.domain.QImage.image;
import static com.dging.dgingmarket.domain.chat.QChatRoom.chatRoom;
import static com.dging.dgingmarket.domain.product.QProduct.product;
import static com.dging.dgingmarket.domain.product.QProductImage.productImage;

public class ChatRoomQueryRepositoryImpl extends QuerydslRepositorySupport implements ChatRoomQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ChatRoomQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(ChatRoom.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<ChatRoomResponse> room(Long id, Long userId) {

        Optional<ChatRoomResponse> responseOptional = queryFactory.selectFrom(product)
                .join(chatRoom).on(product.eq(chatRoom.product))
                .leftJoin(productImage).on(product.eq(productImage.product))
                .leftJoin(image).on(image.eq(productImage.image))
                .where(
                        chatRoom.id.eq(id),
                        product.deleted.isFalse()
                )
                .groupBy(chatRoom.id)
                .transform(
                        GroupBy.groupBy(chatRoom.id).list(
                                new QChatRoomResponse(
                                        product.id,
                                        GroupBy.list(Projections.constructor(ImagesResponse.class,
                                                image.id,
                                                image.url).skipNulls()),
                                        product.title,
                                        product.price,
                                        product.isShippingFreeIncluded
                                )
                        )
                ).stream().findAny();

        Optional<ChatRoom> chatRoomOptional = queryFactory.selectFrom(chatRoom)
                .where(chatRoom.id.eq(id))
                .stream().findAny();

        if(chatRoomOptional.isPresent() && responseOptional.isPresent()) {
            ChatRoomResponse response = responseOptional.get();
            List<ImagesResponse> distinctImages = response.getProductImages().stream()
                    .distinct()
                    .collect(Collectors.toList());
            response.setProductImages(distinctImages);

            ChatRoom chatRoom = chatRoomOptional.get();
            User recipient = Objects.equals(chatRoom.getFrom().getId(), userId) ? chatRoom.getTo() : chatRoom.getFrom();
            Store recipientStore = recipient.getStore();
            response.setRecipientId(recipientStore.getId());
            response.setRecipientName(recipientStore.getName());
        } else {
            responseOptional = Optional.empty();
        }

        return responseOptional;
    }
}
