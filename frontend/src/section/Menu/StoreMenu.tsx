import React from 'react';
import { useLocation, Link } from 'react-router-dom';
import { styled } from 'styled-components';
import Arrange from '../../components/Base/Arrange';

const WrapStoreBoard = styled(Arrange)`
  position: absolute;
  padding: ${({ theme }) => theme.size.xl};
  border: 1px solid ${({ theme }) => theme.color.black0};
  border-radius: ${({ theme }) => theme.size.xxxxxs};
  background-color: white;

  font-size: ${({ theme }) => theme.size.m};
  top: 36px;
  right: -2px;
  z-index: 99;
`;

const StyledLink = styled(Link)<{ $status?: boolean }>`
  text-align: center;
  text-decoration: none;
  color: ${(props) =>
    props.$status ? props.theme.color.pink100 : props.theme.color.black0};
  ${({ theme }) => theme.font.r16};
`;

export default function StoreMenu() {
  const path = useLocation().pathname;

  return (
    <WrapStoreBoard
      display='flex'
      width='112px'
      flexdirection='column'
      gap='16px'
    >
      <StyledLink $status={path === '/goodsmanage'} to='/goodsmanage'>
        내 상품
      </StyledLink>
      <StyledLink $status={path === '/keepgoods'} to='/keepgoods'>
        찜한 상품
      </StyledLink>
      <StyledLink $status={path === '/setting'} to='/setting'>
        계정 설정
      </StyledLink>
      <StyledLink $status={path === '/service'} to='/service'>
        고객센터
      </StyledLink>
    </WrapStoreBoard>
  );
}
