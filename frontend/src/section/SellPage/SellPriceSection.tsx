import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { useSell } from '../../recoil/sell/useSell';
import { SellInput } from '../../components';
import { PinkRoundCheck } from '../../assets/images';

const WrapContent = styled.div`
  display: flex;
  flex-direction: column;
  gap: 20px;
`;

const WrapCheckBox = styled.div`
  display: flex;
  align-items: center;

  /* input[type='checkbox'] {
    width: 20px;
    height: 20px;
    margin: 0 4px 0 0;
    border: 2px solid ${({ theme }) => theme.color.black1};
    border-radius: 50%;
    cursor: pointer;

    appearance: none;

    &:checked::before {
      content: '';
      position: absolute;
      top: 50%;
      left: 50%;
      width: 20px;
      height: 20px;
      border: 10px solid white;
      border-radius: 50%;
      background-color: transparent;
      transform: translate(-50%, -50%);
    }
  } */
  label {
    height: 16px;
    color: ${({ theme }) => theme.color.black0};
    ${({ theme }) => theme.font.r14};
  }
`;

const RoundCheckBox = styled.input<{
  checked: boolean;
}>`
  width: 20px;
  height: 20px;
  margin: 0 4px 0 0;
  border: 2px solid ${({ theme }) => theme.color.black1};

  ${({ checked, theme }) =>
    checked &&
    `
    width: 20px;
    height: 20px;
    background: white url(${PinkRoundCheck}) no-repeat center / 100%;
    border: none;
  `}
  border-radius: 50%;
  cursor: pointer;

  appearance: none;

  &:checked::before {
    content: '';
    position: absolute;
    top: 50%;
    left: 50%;
    width: 20px;
    height: 20px;
    border: 10px solid white;
    border-radius: 50%;
    background-color: transparent;
    transform: translate(-50%, -50%);
  }
`;

export default function SellPriceSection() {
  const theme = useTheme();
  const { sellPrice, setSellPrice, sellProposal, setSellProposal } = useSell();

  return (
    <WrapContent>
      <SellInput
        value={sellPrice}
        setValue={setSellPrice}
        unit='원'
        maxLength={7}
      />
      <WrapCheckBox>
        <RoundCheckBox
          type='checkbox'
          checked={sellProposal}
          onChange={() => {
            setSellProposal(!sellProposal);
          }}
        />
        <label>가격제안 받기</label>
      </WrapCheckBox>
    </WrapContent>
  );
}
