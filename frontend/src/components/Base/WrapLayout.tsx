import React from 'react';
import { styled } from 'styled-components';

const Wrap = styled.div`
  width: 100%;
  min-width: ${({ theme }) => theme.page_size.minwidth};
`;

export default function WrapLayout({ children }: any) {
  console.log(children);
  return <Wrap>{children}</Wrap>;
}
