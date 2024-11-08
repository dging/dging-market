import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../Base';
import { ImgBtn } from '../Button';
import { CheckBox } from '../CheckBox';
import Reservation from '../../assets/images/Reservation.png';
import Soldout from '../../assets/images/Soldout.png';
import CheckBorderWhite from '../../assets/images/CheckBorderWhite.png';
import CheckBorderGray from '../../assets/images/CheckBorderGray.png';
import Test from '../../assets/images/Test.png';

const WrapCard = styled(Arrange)`
  width: 570px;
  border: 1px solid ${({ theme }) => theme.color.black5};
  border-radius: ${({ theme }) => theme.size.m};
  overflow: hidden;
`;

const Title = styled.div<{ $status: string }>`
  width: 100%;
  height: 20px;
  color: ${(props) =>
    props.$status === 'soldout'
      ? props.theme.color.black1
      : props.theme.color.black0};
  ${({ theme }) => theme.font.r18};
`;

const Price = styled(Arrange)<{ $status: string }>`
  color: ${(props) =>
    props.$status === 'soldout'
      ? props.theme.color.black1
      : props.theme.color.black0};
  ${({ theme }) => theme.font.price18};
`;

const Unit = styled.span`
  margin-left: 2px;
  ${({ theme }) => theme.font.body_12_bold};
`;

const Date = styled(Arrange)<{ $status: string }>`
  ${({ theme }) => theme.font.date14};
  color: ${(props) =>
    props.$status === 'soldout'
      ? props.theme.color.black1
      : props.theme.color.black2};
  margin-bottom: 28px;
`;

export default function KeepCard(props: {
  $rightbgimg?: string;
  value?: boolean;
  onChange?: React.ChangeEventHandler<HTMLInputElement>;
}) {
  const theme = useTheme();
  const [status, setStatus] = useState('reserve');

  const returnStatus = (status: string) => {
    if (status === 'reserve') {
      return (
        <ImgBtn
          as='div'
          width='58px'
          height='22px'
          $backgroundimage={Reservation}
        />
      );
    } else if (status === 'soldout') {
      return (
        <ImgBtn
          as='div'
          width='68px'
          height='22px'
          $backgroundimage={Soldout}
        />
      );
    } else {
      return <></>;
    }
  };

  return (
    <WrapCard display='flex'>
      <ImgBtn as='div' width='180px' height='180px' $backgroundimage={Test} />
      <Arrange
        display='flex'
        height='100%'
        padding={theme.size.xl}
        flexdirection='column'
        gap='10px'
      >
        <Arrange width='348px' display='flex' alignitems='center' gap='10px'>
          <Title $status={status}>Test - Test</Title>
          <CheckBox
            $bgimg={props.value ? CheckBorderGray : CheckBorderWhite}
            value={props.value}
            onChange={props.onChange}
          />
        </Arrange>
        <Price
          $status={status}
          height='18px'
          display='flex'
          alignitems='center'
        >
          200,000<Unit>원</Unit>
        </Price>
        <Date $status={status} height='10px'>
          1일전
        </Date>
        {returnStatus(status)}
      </Arrange>
    </WrapCard>
  );
}
