import React, { useState } from 'react';
import styled from 'styled-components';
import { Arrange, ImgBtn } from '../../components';
import { cheat } from '../../utils/_data';
import { AddPicture, Default, Test } from '../../assets/images';

const TopInfo = styled.div`
  padding: 8px 20px;
  border-bottom: 1px solid ${({ theme }) => theme.color.black5};
`;

const Title = styled.div`
  height: 20px;
  margin-bottom: 5px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.r18};
`;

const ConnectDate = styled.div`
  height: 21px;
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.font.body14}
`;

const WrapGoodsInfo = styled.div`
  display: flex;
  gap: 10px;
`;

const GoodsImage = styled.img`
  width: 64px;
  height: 64px;
  border: 1px solid ${({ theme }) => theme.color.black4};
  border-radius: 4px;
`;

const GoodsName = styled.div`
  height: 18px;
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.font.r16};
`;

const GoodsPrice = styled.div`
  display: flex;
  height: 13px;
  align-items: center;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.price18};
`;

const GoodsDelivery = styled.div`
  width: fit-content;
  height: fit-content;
  padding: 5px 8px;
  color: ${({ theme }) => theme.color.black2};
  background-color: ${({ theme }) => theme.color.black3};
  border-radius: 4px;
  ${({ theme }) => theme.font.info12};
`;

const WrapTalk = styled.div`
  box-sizing: border-box;
  display: flex;
  width: 570px;
  height: 650px;
  padding: 20px;
  border-bottom: 1px solid ${({ theme }) => theme.color.black5};
  flex-direction: column;
  gap: 20px;
  overflow-y: scroll;
`;

const SpeechBubble = styled.div`
  box-sizing: border-box;
  width: 375px;
  height: fit-content;
  margin-top: 20px;
  padding: 16px;
  color: ${({ theme }) => theme.color.black0};
  background-color: ${({ theme }) => theme.color.black3};
  border-radius: 0px 16px 16px 16px;
  ${({ theme }) => theme.font.body14};
`;

const RightSpeechBubble = styled(SpeechBubble)`
  border-radius: 16px 0px 16px 16px;
`;

const WrapCheatingBar = styled.div`
  box-sizing: border-box;
  display: flex;
  width: 530px;
  height: 56px;
  margin: auto;
  padding: 16px 20px;
  border-radius: 58px;
  background-color: ${({ theme }) => theme.color.black4};
  justify-content: space-between;
`;

const CheatingInput = styled.input`
  width: 445px;
  height: 21px;
  color: ${({ theme }) => theme.color.black2};
  border: none;
  background-color: transparent;
  ${({ theme }) => theme.font.body14};
  &:focus {
    outline: none;
  }
`;

interface TalkType {
  name: string;
  content: string;
}

interface ContentType {
  id: number;
  name: string;
  price: number;
  date: string;
  state: string;
  last_connect: string;
  talk: TalkType[];
}

interface PropsType {
  content: ContentType;
}

export default function TalkDetailCard(num: { props: number }) {
  console.log(num.props, cheat);
  const [content, useContent] = useState(cheat[num.props]);
  console.log(content);

  const LeftTalk = ({ data }: any) => {
    return (
      <Arrange display='flex' gap='10px'>
        <ImgBtn
          as='div'
          width='40px'
          height='40px'
          borderradius='40px'
          $backgroundimage={Test}
        />
        <SpeechBubble>{data}</SpeechBubble>
      </Arrange>
    );
  };

  const RightTalk = ({ data }: any) => {
    return (
      <Arrange display='flex' gap='10px' margin='0 0 0 auto'>
        <RightSpeechBubble>{data}</RightSpeechBubble>

        <ImgBtn
          as='div'
          width='40px'
          height='40px'
          borderradius='40px'
          $backgroundimage={Default}
        />
      </Arrange>
    );
  };

  return (
    <>
      {/* 윗부분 */}
      <TopInfo>
        <Arrange padding='10px 0' margin='0 0 10px 0'>
          <Title>{content.name}</Title>
          <ConnectDate>{content.last_connect}</ConnectDate>
        </Arrange>
        <WrapGoodsInfo>
          <GoodsImage src={Test} alt='Product Thumbnail' />
          <Arrange display='flex' flexdirection='column' gap='10px'>
            <GoodsName>{content.title}</GoodsName>
            <GoodsPrice>{content.price}원</GoodsPrice>
            <GoodsDelivery>{content.state}</GoodsDelivery>
          </Arrange>
        </WrapGoodsInfo>
      </TopInfo>
      {/* 채팅창 */}
      <WrapTalk>
        {content === undefined ? (
          <></>
        ) : (
          content.talk.map((val: TalkType, idx: number) =>
            val.name === 'you' ? (
              <RightTalk key={idx} data={val.content} />
            ) : (
              <LeftTalk key={idx} data={val.content} />
            )
          )
        )}
      </WrapTalk>
      {/* 입력란 */}
      <WrapCheatingBar>
        <CheatingInput placeholder='메시지를 입력하세요.' maxLength={100} />
        <ImgBtn width='24px' height='24px' $backgroundimage={AddPicture} />
      </WrapCheatingBar>
    </>
  );
}
