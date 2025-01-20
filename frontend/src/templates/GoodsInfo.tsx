import React from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange, BarTitle, IncludeImgBtn } from '../components';
import { Locate, Notebook, Tag } from '../assets/images';
import { GoodsInfoType } from '../types/productType';

const Bar = styled.div`
  width: 1px;
  height: 74px;
  margin: 8px 0 0 0;
  background-color: ${({ theme }) => theme.color.black5};
`;

function GoodsInfo(props: GoodsInfoType) {
  const theme = useTheme();
  console.log(props);
  return (
    <Arrange
      display='flex'
      flexdirection='column'
      gap='50px'
      padding='0 20px 0 0'
    >
      <BarTitle style={{ ...theme.font.r18 }}>상품정보</BarTitle>
      <Arrange width='747px' style={{ ...theme.font.body14 }}>
        {props.content}
        {/* TEST - TEST */}
      </Arrange>

      <Arrange
        display='flex'
        width='100%'
        padding='20px 10px 30px 10px'
        gap='10px'
        style={{
          borderTop: `1px solid ${theme.color.black5}`,
          borderBottom: `1px solid ${theme.color.black5}`,
        }}
      >
        <Arrange
          width='229px'
          display='flex'
          padding='10px 0'
          flexdirection='column'
          alignitems='center'
          gap='20px'
        >
          <IncludeImgBtn
            as='div'
            $leftbgimg={Locate}
            text='직거래지역'
            textstyle={{ color: theme.color.black1, ...theme.font.info14 }}
          />
          <Arrange
            textalign='center'
            style={{
              color: theme.color.black2,
              ...theme.font.font14_bold,
            }}
          >
            {props.region || props.location ? (
              <>
                {props.region || '없음'}
                <br />({props.location || '없음'})
              </>
            ) : (
              <>없음</>
            )}
          </Arrange>
        </Arrange>

        <Bar />

        <Arrange
          width='229px'
          display='flex'
          padding='10px 0'
          flexdirection='column'
          alignitems='center'
          gap='20px'
        >
          <IncludeImgBtn
            as='div'
            $leftbgimg={Notebook}
            text='카테고리'
            textstyle={{ color: theme.color.black1, ...theme.font.info14 }}
          />
          <Arrange
            textalign='center'
            style={{
              color: theme.color.black2,
              ...theme.font.font14_bold,
            }}
          >
            {props.mainCategory} &gt; {props.middleCategory} &gt;{' '}
            {props.subCategory}
            {/* test &gt; test &gt; test */}
          </Arrange>
        </Arrange>

        <Bar />

        <Arrange
          width='229px'
          display='flex'
          padding='10px 0'
          flexdirection='column'
          alignitems='center'
          gap='20px'
        >
          <IncludeImgBtn
            as='div'
            $leftbgimg={Tag}
            text='상품태그'
            textstyle={{ color: theme.color.black1, ...theme.font.info14 }}
          />
          <Arrange
            textalign='center'
            style={{
              color: theme.color.black2,
              ...theme.font.font14_bold,
            }}
          >
            {props.tags.map(
              (val: { id: number; name: string }, idx: number) => (
                <span key={val.id}>#{val.name} </span>
              )
            )}
            {/* {['싸다', '편하다', '가깝다'].map((val: string, idx: number) => (
              <span key={idx}>#{val} </span>
            ))} */}
          </Arrange>
        </Arrange>
      </Arrange>
    </Arrange>
  );
}

export default GoodsInfo;
