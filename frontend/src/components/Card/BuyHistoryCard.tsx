import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { useReviewModal } from '../../recoil/reviewModal/useReviewModal';
import { Arrange, Btn, ImgBtn } from '../../components';
import { Test, RightArrowGray } from '../../assets/images';
import { addComma } from '../../utils/addComma';

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

interface ContentType {
  id: number;
  title: string;
  price: string;
  buydate: string;
  trade: string;
  seller: string;
  state: string;
  tradeway: string;
  review: boolean;
}

export default function BuyHistoryCard(props: {
  $rightbgimg?: string;
  value?: boolean;
  content: ContentType;
  onChange?: React.ChangeEventHandler<HTMLInputElement>;
}) {
  const navigate = useNavigate();
  const { setShowModal, setModalName } = useReviewModal();

  return (
    <WrapCard
      display='flex'
      width='570px'
      padding='10px 16px 12px 16px'
      flexdirection='column'
      gap='10px'
    >
      <Arrange width='100%' display='flex' justifycontent='space-between'>
        <TitleDate>{props.content.buydate}</TitleDate>
        <ImgBtn
          width='16px'
          height='16px'
          $backgroundimage={RightArrowGray}
          onClick={() => {
            navigate(`/history/buy/${props.content.id}`);
          }}
        />
      </Arrange>
      <Bar />
      <Status>{props.content.state}</Status>
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
            <Arrange height='12px'>
              {addComma(props.content.price) || '0'}
            </Arrange>
            <Unit>원</Unit>
          </Price>

          <Title>{props.content.title}</Title>

          <Date width='100%' height='14px'>
            {props.content.seller} / {props.content.trade}
          </Date>
        </Arrange>
      </Arrange>

      <Btn
        width='100%'
        height='30px'
        padding='0px'
        $status={!props.content.review}
        onClick={() => {
          if (!props.content.review) {
            setShowModal(true);
            setModalName(props.content.seller);
          }
        }}
      >
        {props.content.review ? '후기 수정하기' : '후기 남기기'}
      </Btn>
    </WrapCard>
  );
}
