import { Link } from 'react-router-dom';
import { styled } from 'styled-components';
import Arrange from '../Base/Arrange';

const WrapStoreBoard = styled(Arrange)`
  position: absolute;
  padding: ${({ theme }) => theme.size.xl};
  border: 1px solid ${({ theme }) => theme.color.black0};
  border-radius: ${({ theme }) => theme.size.xxxxxs};
  background-color: white;
  color: ${({ theme }) => theme.color.black1};
  font-size: ${({ theme }) => theme.size.m};
  top: 36px;
  right: -2px;
`;

const StyledLink = styled(Link)`
  text-align: center;
  text-decoration: none;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.r16};
`;

export default function StoreMenu() {
  return (
    <WrapStoreBoard
      display='flex'
      width='112px'
      flexdirection='column'
      gap='16px'
    >
      <StyledLink to='/mygoods'>내 상품</StyledLink>
      <StyledLink to='/keepgoods'>찜한 상품</StyledLink>
      <StyledLink to='/setting'>계정 설정</StyledLink>
      <StyledLink to='/service'>고객센터</StyledLink>
    </WrapStoreBoard>
  );
}
