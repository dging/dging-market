import React from 'react';
import styled from 'styled-components';

const WrapSpan = styled.span`
  color: ${({ theme }) => theme.color.black2};
`;

export default function SpanGray(props: { children?: React.ReactNode }) {
  return <WrapSpan>{props.children}</WrapSpan>;
}
