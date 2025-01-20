import React, { useEffect, useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { MainGoodsMenu } from '../section';
import { Arrange, MainCard, UnderlineTitle } from '../components';
import { getProductsAll } from '../api/product/productApi';
import { ProductType } from '../types/productType';
import { number } from 'prop-types';

const WrapCard = styled(Arrange)`
  width: 100%;
  grid-template-columns: repeat(4, 1fr);
`;

export default function MainPage() {
  const theme = useTheme();
  const [products, setProducts] = useState<ProductType[]>();
  const [status, setStatus] = useState<number | null>(null);

  useEffect(() => {
    const GetProductsAll = async () => {
      try {
        await getProductsAll()
          .then((res) => {
            console.log(res);

            if (typeof res === 'number') {
              setStatus(res);
            } else {
              setProducts(res);
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } catch {
        console.log('error');
      }
    };
    GetProductsAll();
  }, []);

  const GetGoodsCards = () => {
    if (status === 401) {
      return <div>로그인 후 상품 조회가 가능합니다.</div>;
    } else if (status === null && products?.length === 0) {
      return <div>상품이 없습니다.</div>;
    } else if (status === null && products) {
      return (
        products &&
        products.map((val, idx) => (
          <MainCard
            key={idx}
            goodsId={val.id.toString()}
            storeId={val.storeId.toString()}
            title={val.title}
            price={val.price}
            date={val.createdAt}
          />
        ))
      );
    } else {
      <>불러오기 에러</>;
    }
  };

  return (
    <>
      <MainGoodsMenu />

      <Arrange
        width='100%'
        minwidth={theme.page_size.minwidth}
        display='flex'
        justifycontent='center'
      >
        <Arrange
          width={theme.page_size.width}
          padding={`${theme.size.xxxxxl} 0 ${theme.size.xxxxxxl} 0`}
        >
          <UnderlineTitle font={theme.font.b32}>
            오늘 들어온 상품
          </UnderlineTitle>
          <WrapCard
            width='100%'
            display='grid'
            margin='50px 0 0 0'
            gap='50px'
            justifycontent='space-between'
          >
            <GetGoodsCards />
          </WrapCard>
        </Arrange>
      </Arrange>
    </>
  );
}
