import React, { useState } from 'react';
import styled from 'styled-components';
import { RoundBtn } from '../Button';
import { Arrange } from '../Base/';
import { useSell } from '../../recoil/sell/useSell';

const WrapRoundCategory = styled.div`
  display: flex;
  width: 100%;
  height: fit-content;
  gap: 10px;
  flex-wrap: wrap;
`;

export default function SellRoundCategory(props: {
  words: Array<string>;
  action?: any;
  order: number;
  value?: { order: number; content: string };
  setValue: React.Dispatch<
    React.SetStateAction<{ order: number; content: string }>
  >;
}) {
  const [status, setStatus] = useState<number | null>(null);
  const { setSellCategory } = useSell();

  const handleSetValue = (val: string) => {
    const newObject = { order: props.order, content: val };
    props.setValue((prev) => ({ ...prev, ...newObject }));
  };

  const newSellCategory = (prevArr: any, val: string) => {
    const newCategory = [...prevArr];
    newCategory[props.order] = val;
    return newCategory;
  };

  return (
    <WrapRoundCategory>
      {props.words.map((val: string, idx: number) => (
        <RoundBtn
          key={idx}
          $status={status === idx}
          onClick={() => {
            setStatus(idx);
            setSellCategory((prev) => newSellCategory(prev, val));
            handleSetValue(val);
          }}
        >
          {val}
        </RoundBtn>
      ))}
    </WrapRoundCategory>
  );
}
