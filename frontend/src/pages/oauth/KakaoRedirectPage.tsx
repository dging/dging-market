import React, { useEffect, useState } from 'react';

export default function KakaoRedirectPage() {
  const [kakaoCode, setKakaoCode] = useState('');

  useEffect(() => {
    const getCode = async () => {
      const code = new URLSearchParams(window.location.search).get('code');

      if (code) {
        setKakaoCode(code);
      } else {
        console.log('no code');
      }
    };

    getCode();
  });

  return <div>kakao code : {kakaoCode}</div>;
}
