import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';

import { Arrange } from '../Base';
import { Btn, ImgBtn, IncludeImgBtn } from '../Button';
import Test from '../../assets/images/Test.png';
import RightArrowGray from '../../assets/images/RightArrowGray.png';

const WrapCard = styled(Arrange)`
  border: 1px solid ${({ theme }) => theme.color.black5};
  border-radius: 8px;
`;

const Bar = styled.div`
  width: 100%;
  height: 1px;
  background-color: ${({ theme }) => theme.color.black5};
`;

const TitleDate = styled(Arrange)`
  height: 16px;
  display: flex;
  align-items: center;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.b10_ls8};
`;

const Status = styled(Arrange)`
  height: 11px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.eb10};
`;

const Price = styled(Arrange)`
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.eb12_ls2};
`;

const Unit = styled.span`
  height: 14px;
  margin-left: 2px;
  ${({ theme }) => theme.font.b10_lh150};
`;

const Title = styled.div`
  width: fit-content;
  max-width: 260px;
  height: 12px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.r12};
`;

const Date = styled(Arrange)`
  ${({ theme }) => theme.font.r10_ls8};
  color: ${({ theme }) => theme.color.black2};
`;

export default function BuyHistoryCard(props: {
  $rightbgimg?: string;
  value?: boolean;
  onChange?: React.ChangeEventHandler<HTMLInputElement>;
}) {
  const theme = useTheme();

  const [status, setStatus] = useState(false);
  const [text, setText] = useState('거래완료');

  return (
    <WrapCard
      display='flex'
      width='570px'
      padding='10px 16px 12px 16px'
      flexdirection='column'
      gap='10px'
    >
      <Arrange width='100%' display='flex' justifycontent='space-between'>
        <TitleDate>24년 02월 17일</TitleDate>
        <ImgBtn width='16px' height='16px' $backgroundimage={RightArrowGray} />
      </Arrange>
      <Bar />
      <Status>거래완료</Status>
      <Arrange display='flex'>
        <ImgBtn
          as='div'
          width='62px'
          height='62px'
          $backgroundimage={Test}
          style={{ borderRadius: '4px' }}
        />
        <Arrange
          display='flex'
          height='62px'
          padding='8px 10px'
          flexdirection='column'
          justifycontent='space-between'
        >
          <Price width='100%' height='14px' display='flex' alignitems='center'>
            <Arrange height='12px'>200,000</Arrange>
            <Unit>원</Unit>
          </Price>

          <Title>Test - Test</Title>

          <Date width='100%' height='14px'>
            달려라씨날도 / 택배거래
          </Date>
        </Arrange>
      </Arrange>

      <Btn
        width='100%'
        height='30px'
        padding='0px'
        $status={status}
        onClick={() => setStatus(!status)}
      >
        {status ? '후기 남기기' : '후기 수정하기'}
      </Btn>
    </WrapCard>
  );
}
