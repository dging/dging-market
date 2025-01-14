import React, { useEffect, useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { MainGoodsMenu } from '../section';
import { Arrange, MainCard, UnderlineTitle } from '../components';
import { getProductsAll } from '../api/product/productApi';
import { ProductType } from '../types/productType';

const WrapCard = styled(Arrange)`
  width: 100%;
  grid-template-columns: repeat(4, 1fr);
`;

export default function MainPage() {
  const theme = useTheme();
  const [products, setProducts] = useState<ProductType[]>();

  useEffect(() => {
    const GetProductsAll = async () => {
      const data = await getProductsAll();
      setProducts(data);
      console.log(data);
    };
    GetProductsAll();
  }, []);

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
            {products &&
              products.map((val, idx) => (
                <MainCard
                  key={idx}
                  goodsId={val.id.toString()}
                  storeId={val.storeId.toString()}
                  title={val.title}
                  price={val.price}
                  date={val.createdAt}
                />
              ))}
          </WrapCard>
        </Arrange>
      </Arrange>
    </>
  );
}
