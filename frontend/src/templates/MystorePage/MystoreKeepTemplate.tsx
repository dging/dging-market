import React, { useState, useEffect, Suspense } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange, CheckBox, KeepCard, RoundBtn } from '../../components';
import { CheckBorderGray, CheckBorderWhite } from '../../assets/images';
import { useMyStore } from '../../recoil/myStoreRecoil/useMyStore';
import { getProductsFavorite } from '../../api/product/productApi';
import { ProductsFavoriteType } from '../../types/productType';

const SelectDeleteBtn = styled.button`
  height: 32px;
  padding: 0px ${({ theme }) => theme.size.xxxs};
  border-radius: ${({ theme }) => theme.size.xxxxxs};
  background-color: white;
  border: 1px solid ${({ theme }) => theme.color.black5};
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.font.b14};
  cursor: pointer;
`;

const WrapCard = styled(Arrange)`
  grid-template-columns: repeat(2, 1fr);
`;

export default function MystoreKeepTemplate() {
  const type = ['최신순', '인기순', '저가순', '고가순'];
  const [status, setStatus] = useState(type[0]);
  const [checkboxValue, setCheckBoxValue] = useState(false);
  const [keepCardInfo, setKeepCardInfo] = useState<ProductsFavoriteType[]>([]);
  const theme = useTheme();

  const getKeepCardInfo = async () => {
    return await getProductsFavorite()
      .then((res) => {
        setKeepCardInfo(res);
      })
      .catch((err) => {
        console.error('getKeepCardInfo : ', err);
        setKeepCardInfo([]);
      });
  };

  useEffect(() => {
    getKeepCardInfo();
  }, []);

  return (
    <>
      <Arrange
        width={theme.page_size.width}
        margin='0 auto'
        padding='20px 0 100px 0'
      >
        {keepCardInfo.length === 0 ? (
          <>찜한 상품이 없습니다.</>
        ) : (
          <Suspense fallback={<></>}>
            <Arrange
              width='100%'
              display='flex'
              justifycontent='space-between'
              alignitems='center'
            >
              <Arrange display='flex' gap='10px'>
                <CheckBox
                  $bgimg={checkboxValue ? CheckBorderGray : CheckBorderWhite}
                  value={checkboxValue}
                  onChange={() => {
                    setCheckBoxValue(!checkboxValue);
                    console.log(checkboxValue);
                  }}
                />
                <SelectDeleteBtn>선택삭제</SelectDeleteBtn>
              </Arrange>
              <Arrange display='flex' gap='10px'>
                {type.map((val, idx) => (
                  <RoundBtn
                    key={idx}
                    $status={status === val}
                    onClick={() => setStatus(val)}
                  >
                    {val}
                  </RoundBtn>
                ))}
              </Arrange>
            </Arrange>

            <WrapCard
              padding={`${theme.size.xxxxl} 0`}
              display='grid'
              gap='20px'
            >
              {keepCardInfo.map((val) => (
                <KeepCard />
              ))}
            </WrapCard>
          </Suspense>
        )}
      </Arrange>
    </>
  );
}
