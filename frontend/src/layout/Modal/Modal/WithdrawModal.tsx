import React from 'react';
import { styled, useTheme } from 'styled-components';
import { Arrange, ImgBtn } from '../../../components';
import { RightArrowLightGray } from '../../../assets/images';

const Title = styled.div`
  margin: 100px 0 0 30px;
  line-height: 32px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.eb24};
`;

const Content = styled.div`
  width: fit-content;
  height: fit-content;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.r18}
`;

export default function WithdrawModal() {
  const WithdrawMenu = () => {
    const menu = [
      '찾는 물품이 없어요',
      '물품이 안 팔려요',
      '비매너 사용자를 만났어요',
      '새 상점을 만들고 싶어요',
      '개인정보를 삭제하고 싶어요',
      '기타',
    ];
    return (
      <Arrange margin='20px auto 0 auto'>
        {menu.map((val, idx) => (
          <Arrange
            key={idx}
            width='460px'
            height='64px'
            display='flex'
            justifycontent='space-between'
            padding='20px 0'
            onClick={() => {
              console.log(val);
            }}
            style={{ cursor: 'pointer' }}
          >
            <Content>{val}</Content>
            <ImgBtn $backgroundimage={RightArrowLightGray} />
          </Arrange>
        ))}
      </Arrange>
    );
  };

  return (
    <>
      <Title>탈퇴 사유가 무엇인가요?</Title>
      <WithdrawMenu />
    </>
  );
}
