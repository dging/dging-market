import React, { useState } from 'react';
import { RoundBtn } from '../Button';
import { Arrange } from '../Base/';

export default function RoundCategory(props: { words: Array<string> }) {
  const [status, setStatus] = useState(0);

  return (
    <Arrange display='flex' gap='10px'>
      {props.words.map((value: string, index: number) => (
        <RoundBtn
          key={index}
          $status={status === index}
          onClick={() => setStatus(index)}
        >
          {value}
        </RoundBtn>
      ))}
    </Arrange>
  );
}
