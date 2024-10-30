import React, { useState } from 'react';
import { styled, useTheme } from 'styled-components';
import ImgBtn from '../Button/ImgBtn';
import Arrange from '../Base/Arrange';
import DiskGray from '../../assets/images/DiskGray.png';

const WrapSearchbar = styled(Arrange)`
  border-radius: ${({ theme }) => theme.size.xxxxxs};
  background-color: ${({ theme }) => theme.color.black3};
`;

const SearchInput = styled.input`
  width: 100%;
  height: 24px;
  padding: 2px 0 0 10px;
  background-color: transparent;
  border: none;
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.font.body16};

  &:focus {
    outline: none;
  }

  ::placeholder,
  ::-webkit-input-placeholder {
    color: ${({ theme }) => theme.color.black2};
  }
  ::-ms-input-placeholder {
    color: ${({ theme }) => theme.color.black2};
  }
`;

export default function Searchbar({ props }: any) {
  const theme = useTheme();

  return (
    <WrapSearchbar
      width='637px'
      height='46px'
      padding={theme.size.xxs}
      display='flex'
      alignitems='center'
    >
      <ImgBtn $backgroundimage={DiskGray} margin='0 10px 0 0' />
      <SearchInput
        placeholder='상품명 / 상점명 검색'
        // onChange={props.onChange}
      />
    </WrapSearchbar>
  );
}
