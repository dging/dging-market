import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import Btn from '../Button/Btn';
import { ImgBtn } from '../Button';
import { BtnType, ArrangeType } from '../../types/types';
import Arrange from '../Base/Arrange';
import ImageRegistration from '../../assets/images/ImageRegistration.png';

const AddImageLabel = styled.label``;

const AddImageDiv = styled.div`
  width: 150px;
  height: 30px;
  background: #fff;
  border: 1px solid rgb(77, 77, 77);
  border-radius: 10px;
  font-weight: 500;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  &:hover {
    background: rgb(77, 77, 77);
    color: #fff;
  }
`;

const AddImageInput = styled.input`
  /* display: none; */
`;

export default function AddImage() {
  return (
    <>
      <AddImageLabel htmlFor='file'>
        <AddImageDiv>파일 업로드하기</AddImageDiv>
      </AddImageLabel>
      <AddImageInput
        type='file'
        name='file'
        onChange={() => console.log('image')}
      />
    </>
  );
}
