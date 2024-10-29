import React from 'react';
import { styled } from 'styled-components';

const WrapSpanBold = styled.span`
  font-family: 'NSBold';
`;

export default function SpanBold({ children }: any) {
  return <WrapSpanBold>{children}</WrapSpanBold>;
}
