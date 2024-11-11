import React from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../components/Base';
import { IncludeImgBtn } from '../components/Button';
import { StoreReviewCard } from '../components/Card';
import { BarTitle } from '../components/Title';
import Locate from '../assets/images/Locate.png';
import NoteBook from '../assets/images/NoteBook.png';
import Tag from '../assets/images/Tag.png';

export default function StoreInfo() {
  const theme = useTheme();
  return (
    <Arrange
      padding='0 0 0 30px'
      display='flex'
      flexdirection='column'
      gap='50px'
      style={{ borderLeft: `1px solid ${theme.color.black5}` }}
    >
      <BarTitle title='상점정보' style={{ ...theme.font.r18 }} />
      <StoreReviewCard />
    </Arrange>
  );
}
