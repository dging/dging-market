import React from 'react';
import styled from 'styled-components';
import { Arrange, SpanBold, ImgBtn } from '../../components';
import { Github } from '../../assets/images';

const WrapFooter = styled(Arrange)`
  min-width: ${({ theme }) => theme.page_size.width};
  padding: ${({ theme }) => theme.size.xxxxxl};
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.font.body16};
  border-top: 1px solid ${({ theme }) => theme.color.black5};
`;

export default function Footer() {
  return (
    <WrapFooter width='100%' display='flex' justifycontent='center'>
      <Arrange width='766px' display='flex' justifycontent='space-between'>
        <Arrange>
          <SpanBold>디깅마켓 | 개발자</SpanBold> 김민희, 김혜진, 이원석
          <br />
          <SpanBold>호스팅 사업자</SpanBold> Amazon Web Service(AWS)
          <br />
          <SpanBold>문의</SpanBold> tentenacy@gmail.com
          <br />
          <br />
          Copyright © 2024 Dging Inc. All Rights Reserved
        </Arrange>
        <ImgBtn width='32px' height='32px' $backgroundimage={Github}></ImgBtn>
      </Arrange>
    </WrapFooter>
  );
}
