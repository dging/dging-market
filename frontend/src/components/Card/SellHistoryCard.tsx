import React from 'react';
import styled, { useTheme } from 'styled-components';
import { useReviewModal } from '../../recoil/reviewModal/useReviewModal';
import { Arrange, Btn, ImgBtn, IncludeImgBtn } from '../../components';
import { addComma } from '../../utils/addComma';
import { Test, RightArrowBlack } from '../../assets/images';

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

interface ContentType {
  id: number;
  title: string;
  price: string;
  paydate: string;
  buyer: string;
  state: string;
  tradeway: string;
  review: boolean;
}

export default function SellHistoryCard(props: {
  content: ContentType;
  $rightbgimg?: string;
  value?: boolean;
  onChange?: React.ChangeEventHandler<HTMLInputElement>;
}) {
  const theme = useTheme();
  const { setShowModal, modalInfo, setModalInfo } = useReviewModal();

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
            <Title>{props.content.title}</Title>
            <IncludeImgBtn
              text={props.content.state || '오류'}
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
          {addComma(props.content.price) || '0'}
          <Unit>원</Unit>
        </Price>
        <Date width='100%'>
          결제완료일 : {props.content.paydate}
          <br />
          구매자 : {props.content.buyer}
        </Date>

        <Btn
          width='100%'
          height='30px'
          padding='0px'
          $status={!props.content.review}
          onClick={() => {
            if (!props.content.review) {
              setShowModal(true);
              setModalInfo({ ...modalInfo, name: props.content.buyer });
            }
          }}
        >
          {props.content.review ? '후기 수정하기' : '후기 남기기'}
        </Btn>
      </Arrange>
    </WrapCard>
  );
}
