import React, { useEffect } from 'react';
import { styled, useTheme } from 'styled-components';
import { Arrange, ImgBtn } from '../../../components';
import { instance } from '../../../api/axios/instance';
import axios from 'axios';
import {
  LoginFacebook,
  LoginGoogle,
  LoginKakao,
  LoginNaver,
} from '../../../assets/images';

const Title = styled.div`
  margin: 100px 0 100px 35px;
  line-height: 32px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.eb24};
`;

const WrapSocialLoginBtn = styled.div<{ $social?: string }>`
  display: flex;
  box-sizing: border-box;
  width: 480px;
  height: 60px;
  align-items: center;
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-radius: 60px;
  padding: 0 20px;
  gap: 20px;
  color: ${({ theme, $social }) =>
    $social === 'kakao' || $social === 'google' ? theme.color.black0 : 'white'};
  background-color: ${({ theme, $social }) =>
    $social ? theme.color[$social] : 'white'};
  ${({ theme }) => theme.font.b18};
  cursor: pointer;
`;

export default function LoginModal() {
  const social = [
    { kor: '카카오톡으로 로그인', eng: 'kakao', img: LoginKakao },
    { kor: '구글로 로그인', eng: 'google', img: LoginGoogle },
    { kor: '페이스북으로 로그인', eng: 'facebook', img: LoginFacebook },
    { kor: '네이버로 로그인', eng: 'naver', img: LoginNaver },
  ];

  const kakaoSocial = () => {
    console.log(import.meta.env.VITE_KAKAO_OAUTH_URL);
    window.location.href = import.meta.env.VITE_KAKAO_OAUTH_URL;
  };

  const SocialLoginList = () => {
    return (
      <Arrange display='flex' flexdirection='column' gap='20px' margin='0 auto'>
        {social.map((val, idx) => (
          <WrapSocialLoginBtn
            $social={val.eng}
            key={idx}
            onClick={() => {
              console.log(val.eng.toUpperCase(), val.eng);
              kakaoSocial();
            }}
          >
            <ImgBtn $backgroundimage={val.img} />
            <Arrange height='20px'>{val.kor}</Arrange>
          </WrapSocialLoginBtn>
        ))}
      </Arrange>
    );
  };

  return (
    <>
      <Title>
        SNS 계정 연동하고
        <br />
        3초안에 로그인하세요!
      </Title>

      <SocialLoginList />
    </>
  );
}
