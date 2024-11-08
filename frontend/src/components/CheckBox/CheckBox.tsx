import React from 'react';
import { styled } from 'styled-components';
import { Arrange } from '../Base';

type CheckBoxType = {
  width?: string;
  height?: string;
  value?: boolean;
  $bgimg?: any;
  $clickbgimg?: any;
  onChange?: React.ChangeEventHandler<HTMLInputElement>;
};

const CheckBoxInput = styled.input<CheckBoxType>`
  appearance: none;
  display: block;
  width: ${(props) => props.width || '32px'};
  height: ${(props) => props.height || '32px'};
  margin: 0;
  background-image: url(${(props) => props.$bgimg || null});
  background-position: 100%;
  background-color: transparent;
  background-size: contain;
  background-repeat: no-repeat;
  cursor: pointer;
`;

export default function CheckBox(props: CheckBoxType) {
  return (
    <Arrange>
      <CheckBoxInput
        type='checkbox'
        value={props.value}
        onChange={props.onChange}
        {...props}
      />
    </Arrange>
  );
}
