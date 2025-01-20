import React, { useState, useEffect } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange, FollowerCard } from '../../components';
import { getStoresFollowers } from '../../api/store/storeApi';
import { useMyStore } from '../../recoil/myStoreRecoil/useMyStore';

const WrapCard = styled(Arrange)`
  grid-template-columns: repeat(4, 1fr);
`;

export default function MystoreFollowerTemplate() {
  const [followers, setFollowers] = useState([]);
  const { getStoresMe } = useMyStore();
  const theme = useTheme();

  const getFollowers = async () => {
    await getStoresFollowers(getStoresMe.id)
      .then((res) => {
        setFollowers(res);
      })
      .catch((err) => {
        setFollowers([]);
        console.error('getFollowers : ', err);
      });
  };

  useEffect(() => {
    getFollowers();
  }, []);

  return (
    <WrapCard
      display='grid'
      width={theme.page_size.width}
      margin='0 auto'
      padding='20px 0 100px 0'
      gap='20px'
    >
      {followers.length === 0 ? (
        <>팔로워가 없습니다.</>
      ) : (
        <>
          {followers.map((val, idx) => (
            <FollowerCard key={idx} />
          ))}
        </>
      )}
    </WrapCard>
  );
}
