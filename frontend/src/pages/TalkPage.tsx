import React, { useState } from 'react';
import styled from 'styled-components';
import {
  Arrange,
  ImgBtn,
  TalkProfileCard,
  TalkDetailCard,
} from '../components';
import { RoundDownArrow } from '../assets/images';
import { profile_cheat } from '../utils/_data';

const BackgroundTalk = styled.div`
  display: flex;
  width: 100%;
  height: fit-content;
  padding: 100px 0;
  background-color: ${({ theme }) => theme.color.black4};
  gap: 20px;
`;

const WrapTitle = styled.div`
  height: 27px;
  color: ${({ theme }) => theme.color.black0};
  margin-right: 10px;
  ${({ theme }) => theme.font.b24};
`;

const WrapTalk1 = styled.div`
  box-sizing: border-box;
  display: flex;
  width: 570px;
  height: 913px;
  padding: 80px 0;
  background-color: white;
  border: 1px solid ${({ theme }) => theme.color.black5};
  border-radius: 16px;
  flex-direction: column;
  gap: 10px;
`;

const WrapTalk2 = styled.div`
  display: flex;
  width: 570px;
  height: 911px;
  /* padding: 80px 0; */
  background-color: white;
  border: 1px solid ${({ theme }) => theme.color.black5};
  border-radius: 16px;
  flex-direction: column;
`;

export default function TalkPage() {
  const [status, setStatus] = useState<number | null>(null);

  return (
    <BackgroundTalk>
      <Arrange display='flex' margin='0 auto' gap='20px'>
        <WrapTalk1>
          <Arrange display='flex' padding='20px' alignitems='center'>
            <WrapTitle>전체 대화</WrapTitle>
            <ImgBtn
              as='div'
              width='24px'
              height='24px'
              $backgroundimage={RoundDownArrow}
            />
          </Arrange>
          {profile_cheat.map((val, idx) => (
            <TalkProfileCard
              content={val}
              key={idx}
              $status={status === val.id}
              onClick={() => setStatus(val.id)}
            />
          ))}
        </WrapTalk1>

        {status !== null && (
          <WrapTalk2>
            <TalkDetailCard props={status} />
          </WrapTalk2>
        )}
      </Arrange>
    </BackgroundTalk>
  );
}
