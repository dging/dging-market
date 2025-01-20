import React, { useState, useEffect, Suspense } from 'react';
import styled, { useTheme } from 'styled-components';
import {
  Arrange,
  StoreReviewScoreCard,
  StoreReviewCard,
} from '../../components';
import { getStoresProductsReviews } from '../../api/store/storeApi';
import { useMyStore } from '../../recoil/myStoreRecoil/useMyStore';
import { MyStoreReviewsType } from '../../types/storeType';

export default function MystoreReviewTemplate() {
  const [productsReviews, setProductsReviews] = useState<MyStoreReviewsType[]>(
    []
  );
  const theme = useTheme();
  const { getStoresMe } = useMyStore();

  const getProductsReviews = async () => {
    await getStoresProductsReviews(getStoresMe.id)
      .then((res) => {
        console.log(res);
        setProductsReviews(res);
      })
      .catch((err) => console.error(err));
  };

  useEffect(() => {
    getProductsReviews();
  }, []);

  return (
    <Arrange
      width={theme.page_size.width}
      margin='0 auto'
      padding='0 0 100px 0'
    >
      <StoreReviewScoreCard
        rating={getStoresMe.rating === null ? 0 : getStoresMe.rating}
      />
      <Suspense fallback={<></>}>
        <Arrange
          display='flex'
          flexdirection='column'
          width='100%'
          padding={`${theme.size.xxxxl} 0 ${theme.size.xl} 0`}
          gap='40px'
        >
          {productsReviews.length === 0 ? (
            <>상점후기가 없습니다.</>
          ) : (
            productsReviews.map((val) => (
              <StoreReviewCard
                key={val.productId}
                id={val.id}
                storeId={val.storeId}
                storeName={val.storeName}
                profileImageUrl={val.profileImageUrl}
                rating={val.rating}
                productId={val.productId}
                productName={val.productName}
                content={val.content}
                createdAt={val.createdAt}
              />
            ))
          )}
        </Arrange>
      </Suspense>
    </Arrange>
  );
}
