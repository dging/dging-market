import React, { useState, useMemo, useRef, useEffect } from 'react';
import { useLocation, useSearchParams } from 'react-router-dom';
import { useRecoilState, useResetRecoilState } from 'recoil';
import styled, { useTheme } from 'styled-components';
import { Arrange, SpanGray } from '../components/Base';
import { RadioBtn, RoundBtn } from '../components/Button';
import { RoundCategory } from '../components/Category';
import { UnderlineTitle, BarTitle } from '../components/Title';
import { AddImage } from '../components/Input';
import { category1, category2, category3 } from '../utils/_data';
import { onPressEnter } from '../utils/onPressEnter';
import {
  SellState,
  SellImage,
  SellName,
  SellCategory,
  SellStates,
  SellDescription,
  SellTag,
  SellPrice,
  SellDeliveryFee,
  SellDirect,
  SellCount,
} from '../recoil/sell/atom';

const Wrap = styled.div<{ gap?: number }>`
  display: flex;
  flex-direction: column;
  gap: ${(props) => props.gap + 'px'};
`;

const WrapContent = styled.div`
  display: flex;
  flex-direction: column;
  gap: 20px;
`;

const TitleInput = styled.input<{ width?: string }>`
  box-sizing: border-box;
  width: ${(props) =>
    props.width ? props.width : props.theme.page_size.width};
  height: 67px;
  padding: 20px;
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-radius: 4px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.body18};

  &:focus {
    outline: none;
  }
`;

const DescriptionTextarea = styled.textarea`
  box-sizing: border-box;
  width: 100%;
  height: 200px;
  padding: 20px;
  color: ${({ theme }) => theme.color.black0};
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-radius: 4px;
  resize: none;
  ${({ theme }) => theme.font.body18};

  &:focus {
    outline: none;
  }
`;

const WrapShortInput = styled.div`
  display: flex;
  box-sizing: border-box;
  width: 300px;
  height: 67px;
  padding: 20px;
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-radius: 4px;
  justify-content: space-between;
  ${({ theme }) => theme.font.body18};
`;

const ShortInput = styled.input`
  width: 230px;
  height: 27px;
  border: none;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.body18};

  &:focus {
    outline: none;
  }
`;

const Ring = styled.div`
  box-sizing: border-box;
  width: 10px;
  height: 10px;
  border: 3px solid ${({ theme }) => theme.color.pink100};
  border-radius: 50%;
`;

export default function SellPage() {
  const theme = useTheme();
  const [sellState, setSellState] = useRecoilState(SellTag);

  const [sellImage, setSellImage] = useRecoilState(SellImage);
  const [sellName, setSellName] = useRecoilState(SellName);
  const [sellCategory, setSellCategory] = useRecoilState(SellCategory);
  const [sellStates, setSellStates] = useRecoilState(SellStates);
  const [sellDescription, setSellDescription] = useRecoilState(SellDescription);
  const [sellTag, setSellTag] = useRecoilState(SellTag);
  const [sellPrice, setSellPrice] = useRecoilState(SellPrice);
  const [sellDeliveryFee, setSellDeliveryFee] = useRecoilState(SellDeliveryFee);
  const [sellDirect, setSellDirect] = useRecoilState(SellDirect);
  const [sellCount, setSellCount] = useRecoilState(SellCount);

  const [tag, setTag] = useState('');

  const handleChange = (e: any) => {
    const { name, value } = e.target;

    setSellState((preState) => ({
      ...preState,
      [name]: value,
    }));
  };

  return (
    <Arrange
      display='flex'
      width={theme.page_size.width}
      margin='0 auto'
      padding={`${theme.size.xxxxxl} 0 ${theme.size.xxxxxxl} 0`}
      flexdirection='column'
      gap='100px'
    >
      <Wrap gap={50}>
        <UnderlineTitle>상품정보</UnderlineTitle>

        <WrapContent>
          <BarTitle style={{ ...theme.font.r24 }}>상품이미지</BarTitle>
          <AddImage />
        </WrapContent>
      </Wrap>

      <WrapContent>
        <BarTitle style={{ ...theme.font.r24 }}>상품명</BarTitle>

        <TitleInput
          placeholder='상품명을 입력해주세요.'
          maxLength={40}
          name='title'
          // value={sellState.title}
          value={sellName}
          onChange={(e) => setSellName(e.target.value)}
        />

        <Arrange margin='0 0 0 auto'>{sellName.length}/40</Arrange>
      </WrapContent>

      <Wrap gap={50}>
        <WrapContent>
          <BarTitle style={{ ...theme.font.r24 }}>카테고리</BarTitle>

          <Arrange display='flex' alignitems='center' gap='10px'>
            <RoundBtn $status={true} style={{ ...theme.font.category18_bold }}>
              CD
            </RoundBtn>
            <Ring />
            <RoundBtn $status={true} style={{ ...theme.font.category18_bold }}>
              Rock
            </RoundBtn>
            <Ring />
            <RoundBtn $status={true} style={{ ...theme.font.category18_bold }}>
              Punk
            </RoundBtn>
          </Arrange>
        </WrapContent>

        <WrapContent>
          <Arrange style={{ color: theme.color.black2, ...theme.font.r18 }}>
            &middot; Rock 카테고리 선택
          </Arrange>

          <RoundCategory words={category1} />
          <RoundCategory words={category2} />
          <RoundCategory words={category3} />
        </WrapContent>
      </Wrap>

      <WrapContent>
        <BarTitle style={{ ...theme.font.r24 }}>상품상태</BarTitle>

        <RadioBtn name='status' inputValue={['최상', '상', '중']} />
      </WrapContent>

      <WrapContent>
        <BarTitle style={{ ...theme.font.r24 }}>설명</BarTitle>

        <DescriptionTextarea
          placeholder='설명을 입력해주세요.'
          name='description'
          value={sellDescription}
          maxLength={2000}
          onChange={(e) => setSellDescription(e.target.value)}
        />

        <Arrange margin='0 0 0 auto'>{sellDescription.length}/2000</Arrange>
      </WrapContent>

      <WrapContent>
        <BarTitle style={{ ...theme.font.r24 }}>
          태그 <SpanGray>(선택)</SpanGray>
        </BarTitle>

        <TitleInput
          placeholder='태그를 입력해주세요. (최대 5개)'
          maxLength={40}
          value={tag}
          onChange={(e) => setTag(e.target.value)}
          onKeyDown={(e) => onPressEnter(e, { action: setSellTag, value: tag })}
        />
        {sellTag}
      </WrapContent>

      <Wrap gap={50}>
        <UnderlineTitle>가격</UnderlineTitle>
        <WrapShortInput>
          <ShortInput
            placeholder='가격을 입력해주세요.'
            name='price'
            value={sellPrice}
            maxLength={9}
            onChange={() => console.log('price')}
          />
          <SpanGray>원</SpanGray>
        </WrapShortInput>
      </Wrap>

      <Wrap gap={50}>
        <UnderlineTitle>택배거래</UnderlineTitle>

        <WrapContent>
          <BarTitle style={{ ...theme.font.r24 }}>배송비</BarTitle>
          <RadioBtn name='direct' inputValue={['배송비포함', '배송비미포함']} />
        </WrapContent>
      </Wrap>

      <Wrap gap={50}>
        <UnderlineTitle>추가정보</UnderlineTitle>

        <WrapContent>
          <BarTitle style={{ ...theme.font.r24 }}>직거래</BarTitle>
          <RadioBtn name='delivery' inputValue={['가능', '불가능']} />
        </WrapContent>
      </Wrap>

      <WrapContent>
        <BarTitle style={{ ...theme.font.r24 }}>수량</BarTitle>
        <WrapShortInput>
          <ShortInput placeholder='수량을 입력해주세요.' />
          <SpanGray>개</SpanGray>
        </WrapShortInput>
      </WrapContent>

      <button
        onClick={() => {
          console.log(
            sellCategory,
            sellCount,
            sellDeliveryFee,
            sellDescription,
            sellDirect,
            sellImage,
            sellName,
            sellPrice,
            sellStates,
            sellTag
          );
        }}
      >
        test
      </button>
    </Arrange>
  );
}
