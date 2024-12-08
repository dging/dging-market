import React, { useState, useMemo, useRef, useEffect } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange, SpanGray } from '../../components/Base';
import { BarTitle } from '../../components/Title';
import { onPressEnter } from '../../utils/onPressEnter';
import { useSell } from '../../recoil/sell/useSell';

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

export default function SellTagSection() {
  const theme = useTheme();

  const [tag, setTag] = useState('');
  const { sellTag, addTag } = useSell();
  return (
    <WrapContent>
      <BarTitle style={{ ...theme.font.r24 }}>
        태그 <SpanGray>(선택)</SpanGray>
      </BarTitle>

      <TitleInput
        placeholder='태그를 입력해주세요. (최대 5개)'
        maxLength={40}
        value={tag}
        onChange={(e) => setTag(e.target.value)}
        onKeyDown={(e) => {
          if (e.key === 'Enter') {
            onPressEnter(e, { action: addTag(tag) });
            setTag('');
          }
        }}
      />
      <Arrange
        display='flex'
        flexdirection='column'
        gap='10px'
        style={{ color: theme.color.black2, ...theme.font.info14 }}
      >
        {sellTag.map((val, idx) => (
          <div key={idx}>&middot; {val}</div>
        ))}
      </Arrange>
    </WrapContent>
  );
}
