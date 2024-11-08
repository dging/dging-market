import React from 'react';
import { styled } from 'styled-components';
import { ArrangeType } from '../../types/types';

const WrapArrange = styled.div<ArrangeType>`
  box-sizing: border-box;
  position: ${(props) => props.position || 'static'};
  display: ${(props) => props.display || 'block'};
  width: ${(props) => props.width || 'fit-content'};
  min-width: ${(props) => props.minwidth};
  height: ${(props) => props.height || 'fit-content'};
  margin: ${(props) => props.margin || '0px'};
  padding: ${(props) => props.padding || '0px'};
  flex-direction: ${(props) => props.flexdirection};
  justify-content: ${(props) => props.justifycontent || 'start'};
  align-items: ${(props) => props.alignitems || 'normal'};
  gap: ${(props) => props.gap};
  text-align: ${(props) => props.textalign || 'left'};
  border-top: ${(props) =>
    props.$top ? `1px solid ${props.theme.color.black5}` : 'none'};
  border-bottom: ${(props) =>
    props.$bottom ? `1px solid ${props.theme.color.black5}` : 'none'};
  background-image: url(${(props) => props.$backgroundimage || null});
  background-position: ${(props) => props.$backgroundposition || 'center'};
  background-size: contain;
  background-repeat: no-repeat;
`;

export default function Arrange(props: ArrangeType) {
  return (
    <WrapArrange {...props} style={props.style}>
      {props.children}
    </WrapArrange>
  );
}
