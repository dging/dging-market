import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { useRecoilState } from 'recoil';
import { ShowModal } from '../../recoil/reviewModal/atom';
import { Arrange } from '../Base';
import { Btn, ImgBtn, IncludeImgBtn } from '../Button';
import Test from '../../assets/images/Test.png';
import RightArrowBlack from '../../assets/images/RightArrowBlack.png';

const WrapCard = styled(Arrange)`
  width: 570px;
  border: 1px solid ${({ theme }) => theme.color.black5};
  border-radius: ${({ theme }) => theme.size.m};
  overflow: hidden;
`;

const Title = styled.div`
  width: fit-content;
  max-width: 260px;
  height: 20px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.r18};
`;

const Price = styled(Arrange)`
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.eb18_ls2};
`;

const Unit = styled.span`
  margin-top: 2px;
  margin-left: 2px;
  ${({ theme }) => theme.font.b12_lh150};
`;

const Date = styled(Arrange)`
  ${({ theme }) => theme.font.info12};
  color: ${({ theme }) => theme.color.black2};
`;

export default function SellHistoryCard(props: {
  $rightbgimg?: string;
  value?: boolean;
  onChange?: React.ChangeEventHandler<HTMLInputElement>;
}) {
  const theme = useTheme();
  const [showModal, setShowModal] = useRecoilState(ShowModal);
  const [status, setStatus] = useState(false);
  const [text, setText] = useState('취소 / 환불');

  return (
    <WrapCard display='flex'>
      <ImgBtn as='div' width='180px' height='180px' $backgroundimage={Test} />
      <Arrange
        display='flex'
        height='180px'
        padding={theme.size.xl}
        flexdirection='column'
        justifycontent='space-between'
      >
        <Arrange width='348px' display='flex' alignitems='center' gap='10px'>
          <Arrange width='100%' display='flex' justifycontent='space-between'>
            <Title>Test - Test</Title>
            <IncludeImgBtn
              text={text}
              font={theme.font.b16_ls8}
              textcolor={theme.color.black0}
              $rightimgwidth='20px'
              $rightimgheight='20px'
              $rightbgimg={RightArrowBlack}
              gap='0px'
            />
          </Arrange>
        </Arrange>
        <Price width='100%' height='18px' display='flex' alignitems='center'>
          200,000<Unit>원</Unit>
        </Price>
        <Date width='100%'>
          결제완료일 : 2024-10-14 22:49
          <br />
          구매자 : 닉네임
        </Date>

        <Btn
          width='100%'
          height='30px'
          padding='0px'
          $status={status}
          onClick={() => {
            setStatus(!status);
            setShowModal(true);
          }}
        >
          {status ? '후기 남기기' : '후기 수정하기'}
        </Btn>
      </Arrange>
    </WrapCard>
  );
}
