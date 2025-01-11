import React from 'react';
import { useLocation, Link } from 'react-router-dom';
import { styled } from 'styled-components';
import Arrange from '../../components/Base/Arrange';
import { useMainModal } from '../../recoil/ModalRecoil/useMainModal';
import { AccountSettingModal } from '../../layout/Modal';

const WrapStoreBoard = styled(Arrange)`
  position: absolute;
  padding: ${({ theme }) => theme.size.xl};
  border: 1px solid ${({ theme }) => theme.color.black0};
  border-radius: ${({ theme }) => theme.size.xxxxxs};
  background-color: white;
  font-size: ${({ theme }) => theme.size.m};
  top: 36px;
  right: -2px;
  z-index: 9;
`;

const StyledLink = styled.div<{ $status?: boolean }>`
  text-align: center;
  text-decoration: none;
  color: ${(props) =>
    props.$status ? props.theme.color.pink100 : props.theme.color.black0};
  ${({ theme }) => theme.font.r16};
  cursor: pointer;
`;

export default function StoreMenu() {
  const path = useLocation().pathname;
  const { showMystoreModal, handleMystoreModal } = useMainModal();

  return (
    <>
      {showMystoreModal && <AccountSettingModal />}
      <WrapStoreBoard
        display='flex'
        width='112px'
        flexdirection='column'
        gap='16px'
      >
        <Link to='/goodsmanage' style={{ textDecoration: 'none' }}>
          <StyledLink $status={path === '/goodsmanage'}>내 상품</StyledLink>
        </Link>
        <Link to='/keepgoods' style={{ textDecoration: 'none' }}>
          <StyledLink $status={path === '/keepgoods'}>찜한 상품</StyledLink>
        </Link>
        <StyledLink
          onClick={() => {
            handleMystoreModal(true);
          }}
        >
          계정 설정
        </StyledLink>
        <StyledLink>고객센터</StyledLink>
      </WrapStoreBoard>
    </>
  );
}
