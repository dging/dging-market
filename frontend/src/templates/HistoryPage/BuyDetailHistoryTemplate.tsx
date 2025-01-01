import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';
import styled, { useTheme } from 'styled-components';
import { Arrange, ImgBtn, IncludeImgBtn, SmallBtn } from '../../components';
import { addComma } from '../../utils/addComma';
import { MessageDot, Test, InfoGray, Explain } from '../../assets/images';

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

const Info12 = styled.div`
  width: fit-content;
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.font.info12};
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

const Line = styled.div`
  position: relative;
  width: 100%;
  height: 1px;
  margin-top: 41px;
  background-color: ${({ theme }) => theme.color.black2};
`;

const Dot = styled.div<{ $status: boolean; left: number }>`
  position: absolute;
  box-sizing: border-box;
  width: 7px;
  height: 7px;
  border: ${(props) =>
    props.$status ? `1px solid ${props.theme.color.pink100}` : ''};
  border-radius: 50%;
  background-color: ${(props) =>
    props.$status ? 'white' : props.theme.color.black2};
  top: -3px;
  left: ${(props) => `calc((99.5% / 3) * ${props.left})`};

  ${Info12} {
    position: absolute;
    width: 40px;

    transform: translate(-50%, 0%);
    top: -18px;
    left: ${(props) =>
      props.left !== 0 && props.left !== 3
        ? '50%'
        : props.left === 3
        ? '-15px'
        : '20px'};

    text-align: center;
    color: ${(props) =>
      props.$status ? props.theme.color.pink100 : props.theme.color.black2};
  }
`;

export default function BuyDetailHistoryTemplate() {
  const theme = useTheme();

  const status = [
    { id: 0, name: '거래시작', order: false },
    { id: 1, name: '배송중', order: false },
    { id: 2, name: '배송완료', order: false },
    { id: 3, name: '거래완료', order: true },
  ];

  return (
    <Arrange
      display='flex'
      flexdirection='column'
      width={theme.page_size.width}
      padding='20px 0 100px 0'
      margin='0 auto'
      gap='20px'
    >
      <Body16Bold>상세 내역</Body16Bold>

      <LineBox>
        <div>
          <Body16Bold margin='0 0 10px 0'>거래가 완료되었습니다.</Body16Bold>
          <div>
            <Line>
              {status.map((val, idx) => (
                <Dot $status={val.order} left={idx} key={idx}>
                  <Info12>{val.name}</Info12>
                </Dot>
              ))}
            </Line>
          </div>
        </div>

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
            <Info12>판매자 : 달려라씨날도</Info12>
          </Arrange>
          <AdjustmentBtn>번개톡</AdjustmentBtn>
        </Arrange>
      </LineBox>

      <Body16Bold>거래 정보</Body16Bold>

      <LineBox>
        <Arrange display='flex' flexdirection='column' gap='10px'>
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
              <span style={{ marginLeft: '10px', textDecoration: 'underline' }}>
                6218001074341
              </span>
            </Body14>
          </Arrange>
        </Arrange>
      </LineBox>

      <Body16Bold>후기</Body16Bold>

      <LineBox>
        <Arrange display='flex' gap='10px'>
          <Body14>이미 후기를 작성하셨습니다.</Body14>
          <IncludeImgBtn
            $leftbgimg={MessageDot}
            $leftimgwidth='17px'
            $leftimgheight='17px'
            text='상점에서 후기 확인'
            textstyle={{ color: theme.color.black2, ...theme.font.body14 }}
          />
        </Arrange>
      </LineBox>
    </Arrange>
  );
}
