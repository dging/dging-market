import React from 'react';
import styled from 'styled-components';
import { Arrange, ImgBtn } from '../../components';
import { Test } from '../../assets/images';

const WrapTalkCard = styled.div<{ $status?: boolean }>`
  box-sizing: border-box;
  display: flex;
  width: 100%;
  padding: 20px;
  border: 0;
  background-color: ${(props) =>
    props.$status ? props.theme.color.black3 : 'white'};
  align-items: center;
  gap: 20px;
  &:hover {
    cursor: pointer;
  }
`;

const Name = styled.div`
  width: 100%;
  height: 20px;
  margin-bottom: 10px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.r18};
`;

const WrapInfo = styled.div`
  display: flex;
  width: 100%;
  height: 21px;
  justify-content: space-between;
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.font.body14};
`;

const Info = styled.div`
  width: 280px;
  height: 21px;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
`;

const Date = styled.div`
  width: fit-content;
`;

interface ContentType {
  id: number;
  name: string;
  date: string;
  content: string;
}

export default function TalkCard(props: {
  content: ContentType;
  $status?: boolean;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}) {
  return (
    <WrapTalkCard as='button' $status={props.$status} onClick={props.onClick}>
      <ImgBtn
        as='div'
        width='64px'
        height='64px'
        $backgroundimage={Test}
        borderradius='64px'
      />
      <Arrange width='344px'>
        <Name>{props.content.name}</Name>
        <WrapInfo>
          <Info>{props.content.content}</Info>
          <Arrange>&middot;</Arrange>
          <Date>{props.content.date}</Date>
        </WrapInfo>
      </Arrange>
      <ImgBtn
        as='div'
        width='64px'
        height='64px'
        $backgroundimage={Test}
        borderradius='4px'
      />
    </WrapTalkCard>
  );
}
