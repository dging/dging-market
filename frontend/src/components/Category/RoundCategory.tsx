import React, { useState } from 'react';
import styled from 'styled-components';
import { RoundBtn } from '../Button';
import { Arrange } from '../Base/';

const WrapRoundCategory = styled.div`
  display: flex;
  width: 100%;
  height: fit-content;
  gap: 10px;
  flex-wrap: wrap;
`;

export default function RoundCategory(props: {
  words: Array<string>;
  action?: any;
}) {
  const [status, setStatus] = useState(0);

  return (
    <WrapRoundCategory>
      {props.words.map((value: string, index: number) => (
        <RoundBtn
          key={index}
          $status={status === index}
          onClick={() => {
            setStatus(index);
            props.action?.();
          }}
        >
          {value}
        </RoundBtn>
      ))}
    </WrapRoundCategory>
  );
}
