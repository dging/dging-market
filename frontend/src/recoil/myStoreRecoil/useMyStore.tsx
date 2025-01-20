import { useState, useEffect } from 'react';
import {
  useRecoilState,
  useResetRecoilState,
  useRecoilValueLoadable,
  useRecoilValue,
} from 'recoil';
import { GetMyStoreId, GetStoresMeSelector, GetStoresMeData } from './atom';
import { MystoreTitle } from '../../components';
import { MystoreMainMenu, StoreProfile } from '../../section';

export const useMyStore = () => {
  const [getStoreMeState, setGetStoreMeState] = useRecoilState(GetStoresMeData);
  const [getStoresMeId, setGetStoresMeId] = useRecoilState(GetMyStoreId);
  const getStoresMe = useRecoilValue(GetStoresMeSelector);

  const StoresMeLoadable = useRecoilValueLoadable(GetStoresMeSelector);

  const StoresMeContents = () => {
    switch (StoresMeLoadable.state) {
      case 'hasValue':
        console.log(StoresMeLoadable.contents);
        return StoresMeLoadable.contents;

      case 'loading':
        return StoresMeLoadable.contents;

      case 'hasError':
        throw StoresMeLoadable.contents;
    }
  };

  return {
    getStoreMeState,
    setGetStoreMeState,
    getStoresMeId,
    setGetStoresMeId,
    getStoresMe,
    StoresMeContents,
  };
};
