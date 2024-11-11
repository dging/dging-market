import React from 'react';
import styled from 'styled-components';

const WrapBarTitle = styled.div`
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.r32};
`;

export default function BarTitle(props: {
  title?: string;
  style?: React.CSSProperties;
}) {
  return <WrapBarTitle style={props.style}>| {props.title}</WrapBarTitle>;
}
