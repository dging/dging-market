package com.dging.dgingmarket.domain.product;

import com.dging.dgingmarket.domain.common.Image;
import com.dging.dgingmarket.domain.common.Tag;
import com.dging.dgingmarket.enums.MainCategory;
import com.dging.dgingmarket.enums.ProductQuality;
import com.dging.dgingmarket.enums.RunningStatus;
import com.dging.dgingmarket.domain.store.Store;
import com.dging.dgingmarket.util.converter.MainCategoryAttributeConverter;
import com.dging.dgingmarket.util.converter.ProductQualityAttributeConverter;
import com.dging.dgingmarket.util.converter.RunningStatusAttributeConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter(value = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TBL_PRODUCT")
@EntityListeners({AuditingEntityListener.class})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private int views;

    @Convert(converter = MainCategoryAttributeConverter.class)
    @Column(length = 3, nullable = false, columnDefinition = "char(3)")
    private MainCategory mainCategory;

    @Column(length = 100, nullable = false)
    private String middleCategory;

    @Column(length = 100)
    private String subCategory;

    @Column(nullable = false)
    private int price;

    @Convert(converter = ProductQualityAttributeConverter.class)
    @Column(length = 2, nullable = false, columnDefinition = "char(2)")
    private ProductQuality quality;

    @Column(nullable = false)
    private int quantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images = new ArrayList<>();

    @Column(name = "store_id", insertable = false, updatable = false, nullable = false)
    private Long storeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductTag> productTags = new ArrayList<>();

    @Convert(converter = RunningStatusAttributeConverter.class)
    @Column(length = 20, nullable = false, columnDefinition = "varchar(20)")
    private RunningStatus runningStatus;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean allowsOffers;

    @Column(length = 100)
    private String region;

    @Column(length = 100)
    private String location;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean isDirectTradeAvailable;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean isShippingFreeIncluded;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean uploaded;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean deleted;

    @CreatedDate
    @Column(length = 6, nullable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(length = 6, nullable = false)
    private Date updatedAt;

    private void setRequiredProductImagesOnly(List<Image> images) {

        int size = images.size();
        List<ProductImage> tempProductImages = new ArrayList<>();

        for (int i = 0; i < size; ++i) {
            Image image = images.get(i);
            tempProductImages.add(ProductImage.create(this, image, i + 1));
        }

        for (int i = 0; i < size; ++i) {
            ProductImage tempProductImage = tempProductImages.get(i);
            for (ProductImage thisProductImage : this.images) {
                if(Objects.equals(thisProductImage, tempProductImage)) {
                    thisProductImage.setPriority(tempProductImage.getPriority());
                    tempProductImages.set(i, thisProductImage);
                    break;
                }
            }
        }

        this.images.clear();
        this.images.addAll(tempProductImages);
    }

    private void setRequiredProductTagsOnly(List<Tag> tags) {

        int size = tags.size();
        ArrayList<ProductTag> tempProductTags = new ArrayList<>();

        for(int i = 0; i < size; ++i) {
            Tag tag = tags.get(i);
            tempProductTags.add(ProductTag.create(this, tag, i + 1));
        }

        for (int i = 0; i < size; ++i) {
            ProductTag tempProductTag = tempProductTags.get(i);
            for (ProductTag thisProductTag : this.productTags) {
                if(Objects.equals(thisProductTag, tempProductTag)) {
                    thisProductTag.setPriority(tempProductTag.getPriority());
                    tempProductTags.set(i, thisProductTag);
                    break;
                }
            }
        }

        this.productTags.clear();
        this.productTags.addAll(tempProductTags);
    }

    public static Product create(Store store, String title, String content, MainCategory mainCategory, String middleCategory, String subCategory, List<Image> images, List<Tag> tags, int price, ProductQuality quality, int quantity, boolean allowsOffers, String region, String location, boolean isDirectTradeAvailable, boolean isShippingFreeIncluded) {
        Product product = new Product();
        product.setStore(store);
        product.setTitle(title);
        product.setContent(content);
        product.setMainCategory(mainCategory);
        product.setMiddleCategory(middleCategory);
        product.setSubCategory(subCategory);
        if(images != null && !images.isEmpty()) product.setRequiredProductImagesOnly(images);
        if(tags != null && !tags.isEmpty()) product.setRequiredProductTagsOnly(tags);
        product.setPrice(price);
        product.setQuality(quality);
        product.setQuantity(quantity);
        product.setRunningStatus(RunningStatus.AVAILABLE);
        product.setAllowsOffers(allowsOffers);
        product.setRegion(region);
        product.setLocation(location);
        product.setDirectTradeAvailable(isDirectTradeAvailable);
        product.setShippingFreeIncluded(isShippingFreeIncluded);
        product.setUploaded(true);
        return product;
    }

    public static Product createTemporally(Store store, String title, String content, MainCategory mainCategory, String middleCategory, String subCategory, List<Image> images, List<Tag> tags, int price, ProductQuality quality, int quantity, boolean allowsOffers, String region, String location, boolean isDirectTradeAvailable, boolean isShippingFreeIncluded) {
        Product product = create(store, title, content, mainCategory, middleCategory, subCategory, images, tags, price, quality, quantity, allowsOffers, region, location, isDirectTradeAvailable, isShippingFreeIncluded);
        product.setUploaded(false);
        return product;
    }

    public void update(String title, String content, MainCategory mainCategory, String middleCategory, String subCategory, List<Image> images, List<Tag> tags, int price, ProductQuality quality, int quantity, boolean allowsOffers, String region, String location, boolean isDirectTradeAvailable, boolean isShippingFreeIncluded) {
        this.title = title;
        this.content = content;
        this.mainCategory = mainCategory;
        this.middleCategory = middleCategory;
        this.subCategory = subCategory;
        if(images != null && !images.isEmpty()) setRequiredProductImagesOnly(images);
        if(tags != null && !tags.isEmpty()) setRequiredProductTagsOnly(tags);
        this.price = price;
        this.quality = quality;
        this.quantity = quantity;
        this.allowsOffers = allowsOffers;
        this.region = region;
        this.location = location;
        this.isDirectTradeAvailable = isDirectTradeAvailable;
        this.isShippingFreeIncluded = isShippingFreeIncluded;
        this.uploaded = true;
    }

    public void changeRunningStatus(RunningStatus runningStatus) {
        this.runningStatus = runningStatus;
    }

//    private void updateProductTags(List<String> tags) {
//
//        List<ProductTag> productTags = tags.stream().filter(tag -> !tag.isEmpty()).map(Tag::create).map(tag -> ProductTag.create(this, tag)).collect(Collectors.toList());
//
//        List<ProductTag> tempProductTags = new ArrayList<>();
//
//        for (ProductTag thisProductTag : this.productTags) {
//            for (ProductTag productTag : productTags) {
//                if (!thisProductTag.equals(productTag)) {
//                    tempProductTags.add(productTag);
//                } else {
//                    tempProductTags.add(thisProductTag);
//                }
//            }
//        }
//
//        this.productTags.clear();
//        this.productTags.addAll(tempProductTags);
//
//    }

    public void countView() {
        this.views += 1;
    }

    public void delete() {
        this.deleted = true;
    }

}
