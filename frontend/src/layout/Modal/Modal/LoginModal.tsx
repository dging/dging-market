import React, { useEffect } from 'react';
import { styled, useTheme } from 'styled-components';
import { Arrange, ImgBtn } from '../../../components';
import { useCookies } from 'react-cookie';
import {
  LoginFacebook,
  LoginGoogle,
  LoginKakao,
  LoginNaver,
} from '../../../assets/images';
import { CloseGray } from '../../../assets/images';
import { useMainModal } from '../../../recoil/ModalRecoil/useMainModal';
import axios from 'axios';

const ModalBackground = styled.div`
  position: absolute;
  width: 100%;
  min-width: ${({ theme }) => theme.page_size.minwidth};
  height: 100vh;
  background-color: #00000080;
  z-index: 10;
`;

const WrapModal = styled.div`
  position: absolute;
  box-sizing: border-box;
  width: 560px;
  height: 760px;
  transform: translate(-50%, -50%);
  top: 50%;
  left: 50%;
  padding: 30px 20px;
  background-color: ${({ theme }) => theme.color.black4};
  border-radius: 8px;
`;

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

  const [cookies] = useCookies(['access_token', 'refresh_token']);

  const { handleLoginModal } = useMainModal();

  const kakaoSocial = async () => {
    console.log(cookies.access_token, cookies.refresh_token);
    if (
      cookies.access_token !== undefined &&
      cookies.refresh_token !== undefined
    ) {
      console.log('Can login');
    } else {
      // window.Kakao.Auth.authorize({
      //   redirectUri: 'http://localhost:5173/oauth/kakao/redirect',
      // });
      window.location.href = import.meta.env.VITE_KAKAO_OAUTH_URL;
    }
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
      <ModalBackground>
        <WrapModal>
          {/* 뒤로가기 버튼, 제목, 닫기 버튼 */}
          <Arrange width='100%' display='flex' justifycontent='space-between'>
            <ImgBtn
              $backgroundimage={CloseGray}
              width='32px'
              height='32px'
              onClick={() => {
                handleLoginModal(false);
              }}
            />
          </Arrange>
          <Title>
            SNS 계정 연동하고
            <br />
            3초안에 로그인하세요!
          </Title>

          <SocialLoginList />
        </WrapModal>
      </ModalBackground>
    </>
  );
}
