package com.dging.dgingmarket.web.api.config;

import com.dging.dgingmarket.domain.common.Image;
import com.dging.dgingmarket.domain.common.Tag;
import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.product.ProductRepository;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.domain.user.exception.UserNotFoundException;
import com.dging.dgingmarket.util.constant.BasePaths;
import com.dging.dgingmarket.util.constant.DocumentDescriptions;
import com.dging.dgingmarket.util.enums.ImageType;
import com.dging.dgingmarket.util.enums.ProductQuality;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
//@LocalDummyDataInitialization
//@Order(2)
public class ProductInitializer implements ApplicationRunner {

    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(productRepository.count() > 0) {

        } else {

            User user = userRepository.findByUserId("userId").orElseThrow(UserNotFoundException::new);

            List<Product> products = dummy(user);

            productRepository.saveAll(products);
        }
    }

    private static List<Product> dummy(User user) {

        return IntStream.rangeClosed(1, 100).mapToObj(i -> {

            String fileName1 = "fileName" + (3 * i - 2);
            String filePath1 = user.getUserId() + "/" + BasePaths.BASE_PATH_PRODUCT + "/" + fileName1;
            String fileName2 = "fileName" + (3 * i - 1);
            String filePath2 = user.getUserId() + "/" + BasePaths.BASE_PATH_PRODUCT + "/" + fileName2;
            String fileName3 = "fileName" + 3 * i;
            String filePath3 = user.getUserId() + "/" + BasePaths.BASE_PATH_PRODUCT + "/" + fileName3;
            String tagName1 = "tag" + (3 * i - 2);
            String tagName2 = "tag" + (3 * i - 1);
            String tagName3 = "tag" + 3 * i;

            return Product.create(
                    user.getStore(),
                    DocumentDescriptions.EXAMPLE_PRODUCT_TITLE,
                    DocumentDescriptions.EXAMPLE_PRODUCT_CONTENT,
                    DocumentDescriptions.EXAMPLE_MAIN_CATEGORY,
                    DocumentDescriptions.EXAMPLE_MIDDLE_CATEGORY,
                    DocumentDescriptions.EXAMPLE_SUB_CATEGORY,
                    List.of(
                            Image.create(
                                    user,
                                    ImageType.PRODUCT,
                                    fileName1,
                                    filePath1,
                                    "http://www.example.com/" + filePath1,
                                    1024
                            ),
                            Image.create(
                                    user,
                                    ImageType.PRODUCT,
                                    fileName2,
                                    filePath2,
                                    "http://www.example.com/" + filePath2,
                                    1024
                            ),
                            Image.create(
                                    user,
                                    ImageType.PRODUCT,
                                    fileName3,
                                    filePath3,
                                    "http://www.example.com/" + filePath3,
                                    1024
                            )
                    ),
                    List.of(Tag.create(tagName1), Tag.create(tagName2), Tag.create(tagName3)),
                    Integer.parseInt(DocumentDescriptions.EXAMPLE_PRICE),
                    ProductQuality.find(DocumentDescriptions.EXAMPLE_QUALITY),
                    Integer.parseInt(DocumentDescriptions.EXAMPLE_QUANTITY),
                    Boolean.parseBoolean(DocumentDescriptions.EXAMPLE_ALLOWS_OFFERS),
                    DocumentDescriptions.EXAMPLE_REGION,
                    DocumentDescriptions.EXAMPLE_LOCATION,
                    Boolean.parseBoolean(DocumentDescriptions.EXAMPLE_IS_DIRECT_TRADE_AVAILABLE),
                    Boolean.parseBoolean(DocumentDescriptions.EXAMPLE_IS_SHIPPING_FEE_INCLUDED)
            );
        }).toList();
    }
}
