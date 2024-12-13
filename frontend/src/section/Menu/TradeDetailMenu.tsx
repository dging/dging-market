import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../../components';

const DetailMenuButton = styled.button<{ $change: boolean }>`
  width: fit-content;
  height: fit-content;
  border: 0;
  padding: 0;
  background-color: white;
  color: ${(props) =>
    props.$change ? props.theme.color.pink100 : props.theme.color.black2};
  ${({ theme }) => theme.font.r14}
  cursor: pointer;
`;

const Bar = styled.div`
  width: 1px;
  height: 8px;
  background-color: ${({ theme }) => theme.color.black5};
`;

export default function TradeDetailMenu() {
  const theme = useTheme();
  const navigate = useNavigate();
  const path = useLocation().pathname;
  const [isSelect, setIsSelect] = useState(path);
  return (
    <Arrange
      width='100%'
      // minwidth={theme.page_size.minwidth}
      $bottom={true}
      display='flex'
      justifycontent='center'
    >
      <Arrange
        width={theme.page_size.width}
        display='flex'
        alignitems='center'
        gap='20px'
        padding={`${theme.size.xl} 0`}
      >
        <DetailMenuButton
          $change={path.includes('/history/sell')}
          onClick={() => {
            setIsSelect(path);
            navigate('/history/sell');
          }}
        >
          판매내역
        </DetailMenuButton>
        <Bar />
        <DetailMenuButton
          $change={path.includes('/history/buy')}
          onClick={() => {
            setIsSelect(path);
            navigate('/history/buy');
          }}
        >
          구매내역
        </DetailMenuButton>
        <Bar />
        <DetailMenuButton
          $change={path.includes('/history/adjust')}
          onClick={() => {
            setIsSelect(path);
            navigate('/history/adjust');
          }}
        >
          정산내역
        </DetailMenuButton>
      </Arrange>
    </Arrange>
  );
}
