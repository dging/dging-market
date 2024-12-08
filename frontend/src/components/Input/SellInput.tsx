import React from 'react';
import { styled, useTheme } from 'styled-components';
import { SpanGray } from '../Base';

const WrapShortInput = styled.div`
  display: flex;
  box-sizing: border-box;
  width: 300px;
  height: 67px;
  padding: 20px;
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-radius: 4px;
  justify-content: space-between;
  ${({ theme }) => theme.font.body18};
`;

const ShortInput = styled.input`
  width: 230px;
  height: 27px;
  border: none;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.body18};

  &:focus {
    outline: none;
  }

  &::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
  }

  &::-webkit-outer-spin-button {
    -webkit-appearance: none;
    margin: 0;
  }
`;

function SellInput(props: {
  value: any;
  setValue: any;
  unit: string;
  maxLength: number;
}) {
  return (
    <WrapShortInput>
      <ShortInput
        placeholder='수량을 입력해주세요.'
        type='number'
        name='count'
        value={props.value}
        maxLength={props.maxLength}
        onChange={(e) => props.setValue(e.target.value)}
      />
      <SpanGray>{props.unit}</SpanGray>
    </WrapShortInput>
  );
}

export default SellInput;
