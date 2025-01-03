// 쿠키 설정 함수
export const setCookie = (name: string, value: string, expires?: number) => {
  let cookieValue = `${name}=${encodeURIComponent(value)}; path=/;`;
  if (expires) {
    cookieValue += ` expries=${expires};`;
  }

  document.cookie = cookieValue;
};

// 쿠키 가져오기 함수
export const getCookie = (name: string) => {
  const cookies = document.cookie.split('; ');
  for (const cookie of cookies) {
    const [key, value] = cookie.split('=');
    if (key === name) {
      return decodeURIComponent(value);
    }
  }
  return null;
};

// 쿠키 삭제 함수
export const deleteCookie = (name: string) => {
  setCookie(name, '', -2);
};
