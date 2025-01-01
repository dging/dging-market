import React from 'react';
import styled from 'styled-components';

const MainTitle = styled.div<{ font?: string }>`
  position: relative;
  width: fit-content;
  color: ${({ theme }) => theme.color.black0};
  ${(props) => props.font || props.theme.font.r32}

  &::after {
    content: '';
    position: absolute;
    width: 100%;
    height: 2px;
    background-color: ${({ theme }) => theme.color.black0};
    bottom: 0;
    left: 0;
  }
`;

export default function UnderlineTitle(props: {
  children?: React.ReactNode;
  font?: string;
}) {
  return <MainTitle font={props.font}>{props.children}</MainTitle>;
}
