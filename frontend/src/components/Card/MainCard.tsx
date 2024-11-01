import React from 'react';
import styled from 'styled-components';
import { Arrange, SpanBold } from '../Base';
import { ImgBtn } from '../Button';
import Test from '../../assets/images/Test.png';

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
  ${({ theme }) => theme.font.date14}
  color: ${({ theme }) => theme.color.black2}
`;

export default function MainCard(props: { bg?: string }) {
  return (
    <Arrange display='flex' flexdirection='column'>
      <CardImg $bg={Test} />
      <Title>Test - Test</Title>
      <Arrange width='100%' display='flex' justifycontent='space-between'>
        <Price>
          200,000&nbsp;<Won>원</Won>
        </Price>
        <Date>12시간 전</Date>
      </Arrange>
    </Arrange>
  );
}
