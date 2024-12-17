import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';
import styled, { useTheme } from 'styled-components';
import { Arrange, ImgBtn, IncludeImgBtn, SmallBtn } from '../../components';
import { addComma } from '../../utils/addComma';
import { RightArrowBlack, Test, InfoGray, Explain } from '../../assets/images';

const Body16Bold = styled(Arrange)`
  color: ${(props) =>
    props.$status ? props.theme.color.black2 : props.theme.color.black0};
  ${({ theme }) => theme.font.body16_bold};
`;

const Body14 = styled(Arrange)`
  color: ${(props) =>
    props.$status ? props.theme.color.black2 : props.theme.color.black0};
  ${({ theme }) => theme.font.body14};
`;

const Body14Bold = styled(Arrange)`
  color: ${(props) =>
    props.$status ? props.theme.color.black2 : props.theme.color.black0};
  ${({ theme }) => theme.font.body14_bold};
`;

const LineBox = styled.div`
  box-sizing: border-box;
  display: flex;
  width: 100%;
  height: fit-content;
  padding: 20px;
  color: ${({ theme }) => theme.color.black0};
  flex-direction: column;
  border: 1px solid ${({ theme }) => theme.color.black5};
  border-radius: 8px;
  gap: 20px;
`;

const Line = styled.div`
  width: 100%;
  height: 1px;
  background-color: ${({ theme }) => theme.color.black5};
`;

const AdjustmentBtn = styled.button`
  box-sizing: border-box;
  width: 85px;
  height: 38px;
  padding: 10px;
  background-color: white;
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-radius: 4px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.r16};
  cursor: pointer;
`;

const InfoBtn = styled(ImgBtn)`
  background-image: url(${(props) => props.$backgroundimage || null});
`;

const ExplainBtn = styled(ImgBtn)`
  display: none;
  position: absolute;
  top: -7px;
  right: -230px;
  ${InfoBtn}:hover {
    display: block;
  }
`;

const AccountWrap = styled.div`
  display: flex;
  box-sizing: border-box;
  width: 100%;
  height: fit-content;
  padding: 16px 20px;
  flex-direction: column;
  background-color: ${({ theme }) => theme.color.black3};
  border-radius: 4px;
  gap: 10px;
`;

const BankIcon = styled.div`
  width: 16px;
  height: 16px;
  border-radius: 50%;
  background-color: ${({ theme }) => theme.color.pink100};
`;

export default function SellDetailHistoryTemplate() {
  const theme = useTheme();
  const location = useLocation();
  const [showExplain, setShowExplain] = useState(false);
  const { trade } = location.state.trade;

  return (
    <Arrange
      display='flex'
      flexdirection='column'
      width={theme.page_size.width}
      padding='20px 0 100px 0'
      margin='0 auto'
      gap='20px'
    >
      <Body16Bold>주문 상세</Body16Bold>
      <Body16Bold>2024.12.31</Body16Bold>

      <LineBox>
        {trade === 'safe' && (
          <>
            <div>
              <Body16Bold margin='0 0 10px 0'>입금 완료</Body16Bold>
              <Body14>등록한 계좌로 판매 금액이 입금되었어요.</Body14>
            </div>
            <Line />
          </>
        )}

        <Arrange width='100%' display='flex' gap='40px' alignitems='center'>
          <ImgBtn
            as='div'
            width='100px'
            height='100px'
            $backgroundimage={Test}
            borderradius='4px'
          />
          <Arrange
            width='856px'
            display='flex'
            flexdirection='column'
            gap='10px'
          >
            <Arrange style={{ ...theme.font.r18 }}>Frank ocean - Blond</Arrange>
            <Arrange
              display='flex'
              height='18px'
              alignitems='center'
              style={{ ...theme.font.eb18_ls2 }}
            >
              {addComma('200000')}
              <span style={{ height: '20px', ...theme.font.body14_bold }}>
                원
              </span>
            </Arrange>
          </Arrange>
          <AdjustmentBtn>정산 내역</AdjustmentBtn>
        </Arrange>
      </LineBox>

      <Body16Bold>판매 정보</Body16Bold>
      <LineBox>
        <Arrange width='100%' display='flex' justifycontent='space-between'>
          <Body14 $status={true}>상품 금액</Body14>
          <Body14>{addComma('100000')}원</Body14>
        </Arrange>

        {trade === 'safe' && (
          <>
            <Line />

            <Arrange
              width='100%'
              display='flex'
              flexdirection='column'
              gap='10px'
            >
              <Arrange
                width='100%'
                display='flex'
                justifycontent='space-between'
              >
                <Body14Bold display='flex' alignitems='center'>
                  입금예정금액
                  <InfoBtn
                    width='20px'
                    height='20px'
                    margin='-2px 0 0 2px'
                    $backgroundimage={InfoGray}
                  />
                  <Arrange position='relative' width='3px' height='20px'>
                    <ExplainBtn
                      as='div'
                      width='230px'
                      height='30px'
                      $backgroundimage={Explain}
                    />
                  </Arrange>
                </Body14Bold>
                <Body14Bold>{addComma('0')}원</Body14Bold>
              </Arrange>

              <Arrange
                width='100%'
                display='flex'
                justifycontent='space-between'
              >
                <Body14Bold>입금완료금액</Body14Bold>
                <Body14Bold>{addComma('100,000')}원</Body14Bold>
              </Arrange>

              <Body14 $status={true}>내 계좌</Body14>

              <AccountWrap>
                <Arrange display='flex' gap='10px'>
                  <BankIcon />
                  <Arrange style={{ ...theme.font.info16 }}>농협은행</Arrange>
                </Arrange>
                <Arrange
                  style={{ color: theme.color.black2, ...theme.font.info14 }}
                >
                  1234456545845 &middot; 김민희
                </Arrange>
              </AccountWrap>
            </Arrange>
          </>
        )}
      </LineBox>

      <Body16Bold>거래 정보</Body16Bold>

      <LineBox>
        <Arrange width='100%' display='flex' justifycontent='space-between'>
          <Body16Bold display='flex' height='41px' alignitems='center'>
            주문번호 123456789
          </Body16Bold>

          <Body16Bold
            display='flex'
            height='41px'
            alignitems='center'
            $status={true}
          >
            24년 12월 31일 19:18
          </Body16Bold>
        </Arrange>

        <Line />

        <Arrange display='flex' flexdirection='column' gap='10px'>
          <Arrange display='flex'>
            <Body14 $status={true} width='74px'>
              구매자
            </Body14>
            <IncludeImgBtn
              text='로얄현익'
              textstyle={{ ...theme.font.body14 }}
              $rightbgimg={RightArrowBlack}
              $rightimgwidth='19px'
              $rightimgheight='19px'
              gap='0px'
            />
            <SmallBtn style={{ marginLeft: '20px' }}>번개톡</SmallBtn>
          </Arrange>

          {trade === 'safe' && (
            <>
              <Arrange display='flex'>
                <Body14 $status={true} width='74px'>
                  거래방법
                </Body14>
                <Body14>택배거래</Body14>
              </Arrange>

              <Arrange display='flex'>
                <Body14 $status={true} width='74px'>
                  운송장
                </Body14>
                <Body14>
                  우체국택배
                  <span
                    style={{ marginLeft: '10px', textDecoration: 'underline' }}
                  >
                    6218001074341
                  </span>
                </Body14>
              </Arrange>
            </>
          )}
        </Arrange>
      </LineBox>
    </Arrange>
  );
}
