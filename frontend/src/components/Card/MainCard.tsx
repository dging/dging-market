import React from 'react';
import { useNavigate, Link } from 'react-router-dom';
import styled from 'styled-components';
import { Arrange } from '../../components';
import { addComma } from '../../utils/addComma';
import { calcTime } from '../../utils/calcTime';
import { Test } from '../../assets/images';

const WrapCard = styled(Arrange)`
  cursor: pointer;
`;

const CardImg = styled.div<{ $bg?: string }>`
  box-sizing: border-box;
  width: 250px;
  height: 250px;
  margin-bottom: ${({ theme }) => theme.size.l};
  background-image: url(${(props) => props.$bg});
  background-size: cover;
  background-position: 'center';
  border-radius: ${({ theme }) => theme.size.m};
  border: 1px solid ${({ theme }) => theme.color.black4};
`;

const Title = styled.div`
  width: 250px;
  margin-bottom: ${({ theme }) => theme.size.xxxs};
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  ${({ theme }) => theme.font.r18}
`;

const Price = styled.div`
  display: flex;
  align-items: center;
  ${({ theme }) => theme.font.price18}
`;

const Won = styled.span`
  ${({ theme }) => theme.font.body_12_bold}
`;

const Date = styled.div`
  display: flex;
  align-items: center;
  ${({ theme }) => theme.font.date14};
  color: ${({ theme }) => theme.color.black2};
`;

export default function MainCard(props: {
  goodsId: string;
  storeId: string;
  bg?: string;
  title: string;
  price: number;
  date: string;
}) {
  const navigate = useNavigate();

  return (
    <WrapCard
      display='flex'
      flexdirection='column'
      onClick={() => navigate(`/detail/${props.storeId}/${props.goodsId}`)}
    >
      <CardImg $bg={Test} />
      <Title>{props.title}</Title>
      <Arrange width='100%' display='flex' justifycontent='space-between'>
        <Price>
          {addComma(props.price)}&nbsp;<Won>원</Won>
        </Price>
        <Date>{calcTime(props.date)}</Date>
      </Arrange>
    </WrapCard>
  );
}
