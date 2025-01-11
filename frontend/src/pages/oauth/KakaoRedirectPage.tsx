import React, { useEffect, useState } from 'react';
import { useCookies } from 'react-cookie';
import axios from 'axios';
import { getCookie } from '../../api/auth/cookie';

export default function KakaoRedirectPage() {
  const [kakaoCode, setKakaoCode] = useState('');
  const [kakaoAccessToken, setKakaoAccessToken] = useState('');
  const [accessToken, setAccessToken] = useState('');
  const [refreshToken, setRefreshToken] = useState('');

  const [cookies, setCookie] = useCookies([
    'kakao_access_token',
    'access_token',
    'refresh_token',
  ]);

  useEffect(() => {
    const code = new URLSearchParams(window.location.search).get('code');

    const getCode = async () => {
      const data = {
        grant_type: 'authorization_code',
        client_id: import.meta.env.VITE_KAKAO_CLIENT_ID,
        code,
        redirect_uri: 'http://localhost:5173/oauth/kakao/redirect',
      };

      if (code) {
        setKakaoCode(code);
        axios
          .post('https://kauth.kakao.com/oauth/token', data, {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
            },
          })
          .then((res) => {
            console.log(res);
            const { access_token } = res.data;
            setKakaoAccessToken(access_token);

            setCookie('kakao_access_token', access_token, {
              path: '/',
              maxAge: 1.5 * 24 * 60 * 60,
            });

            axios
              .post(
                `https://api.dev.dgingmarket.com/users/social/KAKAO/token`,
                {
                  accessToken: access_token,
                },
                {
                  headers: {
                    'Content-Type':
                      'application/x-www-form-urlencoded;charset=utf-8',
                  },
                }
              )
              .then((res) => {
                const { accessToken, refreshToken } = res.data;
                setCookie('access_token', accessToken, {
                  path: '/',
                  maxAge: 1.5 * 24 * 60 * 60,
                });
                setCookie('refresh_token', refreshToken, {
                  path: '/',
                  maxAge: 1.5 * 24 * 60 * 60,
                });

                setAccessToken(accessToken);
                setRefreshToken(refreshToken);
              });

            // if (
            //   !cookies['kakao_access_token'] ||
            //   !cookies['kakao_refresh_token']
            // ) {
            //   setCookie('kakao_access_token', access_token, {
            //     path: '/',
            //     maxAge: 1.5 * 24 * 60 * 60,
            //   });
            //   setCookie('kakao_refresh_token', refresh_token, {
            //     path: '/',
            //     maxAge: 1.5 * 24 * 60 * 60,
            //   });
            // } else {
            //   console.log(getCookie('access_token'));
            //   console.log(getCookie('refresh_token'));
            // }
          });
      } else {
        console.log('no code');
      }
    };

    getCode();
  }, []);

  return (
    <>
      <div>kakao code : {kakaoCode}</div>
      <div>kakao access_token : {kakaoAccessToken}</div>
      <div>access_token : {accessToken}</div>
      <div>refresh_token : {refreshToken}</div>
    </>
  );
}
