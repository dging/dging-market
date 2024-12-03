import React, { useState } from 'react';
import styled from 'styled-components';
import { Arrange } from '../Base';

export const RadioWrapper = styled.label`
  position: relative;
  display: inline-block;
  cursor: pointer;
`;

export const RadioInput = styled.input`
  position: absolute;
  opacity: 0;
  cursor: pointer;

  &:checked + span:after {
    display: block;
  }
`;

export const RadioIcon = styled.span`
  position: absolute;
  top: 0;
  left: 0;
  height: 16px;
  width: 16px;
  color: ${({ theme }) => theme.color.black0};
  border-radius: 50%;
  border: 2px solid
    ${(props) =>
      props.defaultChecked
        ? props.theme.color.pink100
        : props.theme.color.black1};
  background-color: white;
  ${({ theme }) => theme.font.r18};

  &:after {
    content: '';
    position: absolute;
    display: none;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 10px;
    height: 10px;
    border-radius: 50%;
    background-color: ${({ theme }) => theme.color.pink100};
  }
`;

interface RadioBtnType {
  inputValue: Array<string>;
  name: string;
  disabled?: boolean;
  defaultChecked?: boolean;
  style?: React.CSSProperties;
  onChange?: any;
}

export default function RadioBtn(props: RadioBtnType) {
  const [currentStatus, setCurrentStatus] = useState(0);

  return (
    <Arrange display='flex' flexdirection='column' gap='40px'>
      {props.inputValue.map((val, idx) => (
        <RadioWrapper defaultChecked={idx === currentStatus} key={idx}>
          <RadioInput
            type='radio'
            name={props.name}
            value={val}
            disabled={false}
            defaultChecked={idx === currentStatus}
            onChange={() => setCurrentStatus(idx)}
          />
          <RadioIcon defaultChecked={idx === currentStatus}>
            <Arrange width='100px' margin='-2px 0 0 24px'>
              {val}
            </Arrange>
          </RadioIcon>
        </RadioWrapper>
      ))}
    </Arrange>
  );
}
