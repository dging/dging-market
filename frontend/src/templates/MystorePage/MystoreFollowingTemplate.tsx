import React, { useState, useEffect, Suspense } from 'react';
import { useTheme } from 'styled-components';
import { Arrange, FollowingCard } from '../../components';
import { getStoresFollowings } from '../../api/store/storeApi';
import { useMyStore } from '../../recoil/myStoreRecoil/useMyStore';
import { MyStoreFollowingsType } from '../../types/storeType';

export default function MystoreFollowingTemplate() {
  const [followings, setFollowings] = useState<MyStoreFollowingsType[]>([]);
  const { getStoresMe } = useMyStore();
  const theme = useTheme();

  const getFollowings = async () => {
    await getStoresFollowings(getStoresMe)
      .then((res) => {
        console.log(res);
        setFollowings(res);
      })
      .catch((err) => {
        console.error('getFollowings : ', err);
        setFollowings([]);
      });
  };

  useEffect(() => {
    getFollowings();
  }, []);

  return (
    <Arrange
      width={theme.page_size.width}
      margin='0 auto'
      padding='20px 0 100px 0'
    >
      {getFollowings.length === 0 ? (
        <>팔로잉이 없습니다.</>
      ) : (
        <Suspense fallback={<></>}>
          <Arrange
            display='flex'
            width='100%'
            padding='20px 0 0 0'
            flexdirection='column'
            gap='50px'
          >
            {followings.map((val, idx) => (
              <FollowingCard key={idx} />
            ))}
          </Arrange>
        </Suspense>
      )}
    </Arrange>
  );
}
