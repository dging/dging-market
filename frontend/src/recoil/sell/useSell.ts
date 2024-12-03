import { useState } from 'react';
import { useRecoilState, useSetRecoilState, useRecoilValue } from 'recoil';
import {
  SellImage,
  SellName,
  SellCategory,
  SellStates,
  SellDescription,
  SellTag,
  SellDeliveryFee,
  SellDirect,
  SellCount,
  SellState,
} from './atom';
import { SellTagCountState } from './selector';

export const useSell = () => {
  const [sellState, setSellState] = useState(SellState);

  const [sellImage, setSellImage] = useRecoilState(SellImage);
  const [sellName, setSellName] = useRecoilState(SellName);
  const [sellCategory, setSellCategory] = useRecoilState(SellCategory);
  const [sellStates, setSellStates] = useRecoilState(SellStates);
  const [sellDescription, setSellDescription] = useRecoilState(SellDescription);
  const [sellTag, setSellTag] = useRecoilState(SellTag);
  const [sellPrice, setSellPrice] = useRecoilState(SellTag);
  const [sellDeliveryFee, setSellDeliveryFee] = useRecoilState(SellDeliveryFee);
  const [sellDirect, setSellDirect] = useRecoilState(SellDirect);
  const [sellCount, setSellCount] = useRecoilState(SellCount);

  const sellTagCount = useRecoilValue(SellTagCountState);

  const addTag = (tag: string) => {};

  return addTag;
};
