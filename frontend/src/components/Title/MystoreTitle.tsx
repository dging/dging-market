import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import styled, { useTheme } from 'styled-components';
import { Arrange, DropBox } from '../../components';
import { MystoreMainMenu } from '../../section/Menu';
import { MyStoreDetailType } from '../../types/storeType';
import { useMyStore } from '../../recoil/myStoreRecoil/useMyStore';

const WrapTitle = styled(Arrange)<{ $location?: boolean }>`
  ${({ theme }) => theme.font.b24};
  color: ${({ theme }) => theme.color.black0};
  border-bottom: ${(props) =>
    props.$location ? 'none' : `1px solid ${props.theme.color.black5}`};
`;

const WrapPink = styled(Arrange)`
  margin-left: 10px;
  color: ${({ theme }) => theme.color.pink100};
`;

export default function MystoreTitle() {
  const items = ['전체', 'CD', 'Vinyl', 'Cassette', 'DVD'];
  const theme = useTheme();
  const navigate = useNavigate();
  const location = useLocation();
  const address = location.pathname;
  const { getStoresMe } = useMyStore();

  // console.log(props);

  const showTitle = () => {
    if (address === '/mystore/goods')
      return (
        <>
          상품<WrapPink>{getStoresMe.productsTotalCount}</WrapPink>
        </>
      );
    else if (address === '/mystore/review')
      return (
        <>
          상점후기<WrapPink>{getStoresMe.reviewsTotalCount}</WrapPink>
        </>
      );
    else if (address === '/mystore/keep')
      return (
        <>
          찜<WrapPink>{getStoresMe.favoriteProductsTotalCount}</WrapPink>
        </>
      );
    else if (address === '/mystore/following')
      return (
        <>
          팔로잉<WrapPink>{getStoresMe.followingsTotalCount}</WrapPink>
        </>
      );
    else if (address === '/mystore/follower')
      return (
        <>
          팔로워<WrapPink>{getStoresMe.followersTotalCount}</WrapPink>
        </>
      );
    else {
      <>error</>;
    }
  };

  return (
    <>
      <WrapTitle
        display='flex'
        width={theme.page_size.width}
        margin='0 auto'
        padding={`${theme.size.xxxxxl} 0 20px 0`}
        justifycontent='space-between'
        alignitems='center'
        $location={address === '/mystore/review'}
      >
        <Arrange height='36px' display='flex' alignitems='center' gap='10px'>
          {getStoresMe && <Arrange display='flex'>{showTitle()}</Arrange>}
        </Arrange>
        {address === '/mystore/goods' && <DropBox items={items} />}
      </WrapTitle>
    </>
  );
}
