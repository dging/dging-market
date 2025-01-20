import React, { useState, useEffect, Suspense } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange, RoundBtn, MainCard } from '../../components';
import { useMyStore } from '../../recoil/myStoreRecoil/useMyStore';
import { MyStoreProductsType } from '../../types/storeType';
import { getStoresProducts } from '../../api/store/storeApi';
import { useLocation } from 'react-router-dom';

const WrapBlack = styled(Arrange)`
  ${({ theme }) => theme.font.r18}
`;

const WrapGrey = styled(Arrange)`
  color: ${({ theme }) => theme.color.black2};
`;

const WrapCard = styled(Arrange)`
  width: 100%;
  grid-template-columns: repeat(4, 1fr);
`;

export default function MystoreGoodsTemplate() {
  const type = ['최신순', '인기순', '저가순', '고가순'];
  const [status, setStatus] = useState(type[0]);
  const [storeProductsData, setStoreProductsData] = useState<
    MyStoreProductsType[]
  >([]);
  const theme = useTheme();
  const { getStoresMe } = useMyStore();

  const getProductInfo = async () => {
    await getStoresProducts(getStoresMe.id)
      .then((res) => {
        console.log(res);
        setStoreProductsData(res);
      })
      .catch((err) => console.error(err));
  };

  useEffect(() => {
    getProductInfo();
  }, []);

  return (
    <>
      <Arrange
        width={theme.page_size.width}
        margin='0 auto'
        padding='0 0 100px 0'
      >
        <Arrange
          width='100%'
          display='flex'
          justifycontent='space-between'
          alignitems='center'
          padding='20px 0 50px 0'
        >
          <WrapBlack display='flex' gap='10px'>
            <Arrange>|</Arrange>
            <Arrange>전체</Arrange>
            <WrapGrey>{getStoresMe.salesCount}</WrapGrey>
          </WrapBlack>
          <Arrange display='flex' gap='10px'>
            {type.map((val, idx) => (
              <RoundBtn
                key={idx}
                $status={status === type[idx]}
                onClick={() => setStatus(type[idx])}
              >
                {val}
              </RoundBtn>
            ))}
          </Arrange>
        </Arrange>

        <WrapCard
          width='100%'
          display='grid'
          gap='50px'
          justifycontent='space-between'
        >
          <Suspense fallback={<></>}>
            {storeProductsData.map((val: MyStoreProductsType, idx: number) => (
              <MainCard
                key={idx}
                goodsId={val.id.toString()}
                storeId={val.storeId.toString()}
                title={val.title}
                price={val.price}
                date={val.createdAt}
              />
            ))}
          </Suspense>
        </WrapCard>
      </Arrange>
    </>
  );
}
