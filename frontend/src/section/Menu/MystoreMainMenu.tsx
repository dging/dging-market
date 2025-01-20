import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import styled, { useTheme } from 'styled-components';
import { MyStoreMenuType } from '../../types/storeType';
import { useMyStore } from '../../recoil/myStoreRecoil/useMyStore';

const WrapMenu = styled.div<{ $isOwner?: boolean }>`
  display: grid;
  width: ${({ theme }) => theme.page_size.width};
  margin: 0 auto;
  border: 1px solid ${({ theme }) => theme.color.black2};
  border-bottom: none;
  border-top-left-radius: ${({ theme }) => theme.size.m};
  border-top-right-radius: ${({ theme }) => theme.size.m};
  grid-template-columns: ${(props) =>
    props.$isOwner ? 'repeat(5, 1fr)' : 'repeat(4, 1fr)'};
  overflow: hidden;
`;

const MenuButton = styled.button<{ $status?: boolean }>`
  height: 60px;
  padding: ${({ theme }) => `0 ${theme.size.xxxs}`};
  border: none;
  border-left: none;
  border-right: 1px solid ${({ theme }) => theme.color.black2};
  border-bottom: ${(props) =>
    props.$status
      ? '1px solid white'
      : `1px solid ${props.theme.color.black2}`};
  color: ${(props) =>
    props.$status ? props.theme.color.black0 : props.theme.color.black2};
  background-color: ${(props) =>
    props.$status ? 'white' : props.theme.color.black4};
  ${({ theme }) => theme.font.r18}
  cursor: pointer;
`;

const LastButton = styled(MenuButton)`
  border-right: none;
`;

export default function MystoreMainMenu() {
  const [isOwner, setIsOwner] = useState(true);
  const navigate = useNavigate();
  const location = useLocation();
  const address = location.pathname;
  const { getStoresMe } = useMyStore();

  return (
    <WrapMenu $isOwner={isOwner}>
      <MenuButton
        $status={address === '/mystore/goods'}
        onClick={() => navigate('/mystore/goods')}
      >
        상품 {getStoresMe.salesCount}
      </MenuButton>
      <MenuButton
        $status={address === '/mystore/review'}
        onClick={() => navigate('/mystore/review')}
      >
        상점후기 {getStoresMe.reviewsTotalCount}
      </MenuButton>
      {isOwner && (
        <MenuButton
          $status={address === '/mystore/keep'}
          onClick={() => navigate('/mystore/keep')}
        >
          찜 {getStoresMe.favoriteProductsTotalCount}
        </MenuButton>
      )}
      <MenuButton
        $status={address === '/mystore/following'}
        onClick={() => navigate('/mystore/following')}
      >
        팔로잉 {getStoresMe.followingsTotalCount}
      </MenuButton>
      <LastButton
        $status={address === '/mystore/follower'}
        onClick={() => navigate('/mystore/follower')}
      >
        팔로워 {getStoresMe.followersTotalCount}
      </LastButton>
    </WrapMenu>
  );
}
