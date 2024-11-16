import React from 'react';
import styled from 'styled-components';
import { Arrange } from '../Base';
import { ImgBtn } from '../Button';
import Test from '../../assets/images/Test.png';
import Default from '../../assets/images/Default.png';

const TopInfo = styled.div`
  margin-bottom: 10px;
  padding: 10px 20px;
  border-bottom: 1px solid ${({ theme }) => theme.color.black5};
`;

const Title = styled.div`
  height: 20px;
  margin-bottom: 5px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.r18};
`;

const ConnectDate = styled.div`
  height: 21px;
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.font.body14}
`;

const WrapGoodsInfo = styled.div`
  display: flex;
  gap: 10px;
`;

const GoodsImage = styled.img`
  width: 64px;
  height: 64px;
  border: 1px solid ${({ theme }) => theme.color.black4};
  border-radius: 4px;
`;

const GoodsName = styled.div`
  height: 18px;
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.font.r16};
`;

const GoodsPrice = styled.div`
  display: flex;
  height: 13px;
  align-items: center;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.price18};
`;

const GoodsDelivery = styled.div`
  width: fit-content;
  height: fit-content;
  padding: 5px 8px;
  color: ${({ theme }) => theme.color.black2};
  background-color: ${({ theme }) => theme.color.black3};
  border-radius: 4px;
  ${({ theme }) => theme.font.info12};
`;

export default function TalkDetailCard() {
  return (
    <TopInfo>
      <Arrange padding='10px 0' margin='0 0 10px 0'>
        <Title>FDOPE</Title>
        <ConnectDate>13일전 접속</ConnectDate>
      </Arrange>
      <WrapGoodsInfo>
        <GoodsImage src={Test} alt='Product Thumbnail' />
        <Arrange display='flex' flexdirection='column' gap='10px'>
          <GoodsName>Frank Ocean - Blond</GoodsName>
          <GoodsPrice>200,000원</GoodsPrice>
          <GoodsDelivery>무료배송</GoodsDelivery>
        </Arrange>
      </WrapGoodsInfo>
    </TopInfo>
  );
}
