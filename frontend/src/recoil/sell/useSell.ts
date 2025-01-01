import { useState } from 'react';
import { useRecoilState, useSetRecoilState, useRecoilValue } from 'recoil';
import {
  SellAddress,
  SellImage,
  SellName,
  SellCategory,
  SellDescription,
  SellTag,
  SellPrice,
  SellProposal,
  SellDeliveryFee,
  SellDirect,
  SellCount,
  SellState,
} from './atom';
import { SellTagCountState } from './selector';

export const useSell = () => {
  const [sellAddress, setSellAddress] = useRecoilState(SellAddress);
  const [sellCategory, setSellCategory] = useRecoilState(SellCategory);
  const [sellCount, setSellCount] = useRecoilState(SellCount);
  const [sellDeliveryFee, setSellDeliveryFee] = useRecoilState(SellDeliveryFee);
  const [sellDescription, setSellDescription] = useRecoilState(SellDescription);
  const [sellDirect, setSellDirect] = useRecoilState(SellDirect);
  const [sellImage, setSellImage] = useRecoilState(SellImage);
  const [sellName, setSellName] = useRecoilState(SellName);
  const [sellPrice, setSellPrice] = useRecoilState(SellPrice);
  const [sellProposal, setSellProposal] = useRecoilState(SellProposal);
  const [sellState, setSellState] = useState(SellState);
  const [sellTag, setSellTag] = useRecoilState(SellTag);

  const invalidField: string[] = [];

  const addTag = (tagData: string) => {
    console.log('sellTag: ', sellTag, 'tagData', tagData);
    if (sellTag.length >= 5) {
      alert('태그는 최대 5개까지 입니다.');
    } else if (sellTag.length >= 0 && sellTag.length < 5) {
      setSellTag((prev) => [...prev, tagData]);
    } else {
      console.log('error');
    }
  };

  const addImage = (imageData: any) => {
    // console.log('sellImage: ', sellImage, 'imageData', imageData);

    if (sellTag.length >= 5) {
      alert('사진은 최대 5장까지 입니다.');
    } else if (sellImage.length >= 0 && sellImage.length < 5) {
      const files = Array.from(imageData);
      files.forEach((file: any) => {
        const reader = new FileReader();
        reader.onload = () => {
          setSellTag((prev) => [...prev, reader.result as string]);
          console.log('sellImage: ', sellImage);
        };
        reader.readAsDataURL(file);
      });
    } else {
      console.log('error');
    }
  };

  const checkSell = () => {
    if (sellCategory.length === 0) invalidField.push('sellCategory');
    if (!sellCount.trim()) invalidField.push('sellCount');
    if (!sellDeliveryFee.trim()) invalidField.push('sellDeliveryFee');
    if (sellDescription.length === 0) invalidField.push('sellDescription');
    if (!sellDirect.trim()) invalidField.push('sellDirect');
    // if (sellImage.length === 0) invalidField.push('sellImage');
    if (!sellName.trim()) invalidField.push('sellName');
    if (!sellPrice.trim()) invalidField.push('sellPrice');
    if (!sellState) invalidField.push('sellState');
  };

  const onClickSellRegister = () => {
    console.log(
      'sellAddress : ',
      sellAddress,
      'sellCategory : ',
      sellCategory,
      ' sellCount : ',
      sellCount,
      ' sellDeliveryFee : ',
      sellDeliveryFee,
      ' sellDescription : ',
      sellDescription,
      ' sellDirect : ',
      sellDirect,
      ' sellImage : ',
      sellImage,
      ' sellName : ',
      sellName,
      ' sellPrice : ',
      sellPrice,
      ' sellProposal : ',
      sellProposal,
      ' sellState : ',
      sellState,
      ' sellTag : ',
      sellTag
    );
    checkSell();

    if (invalidField.length === 0) {
      console.log('pass');
    } else {
      alert('모두 작성해주세요.');
      console.log(invalidField);
    }
  };

  return {
    sellAddress,
    setSellAddress,
    sellCategory,
    setSellCategory,
    sellCount,
    setSellCount,
    sellDeliveryFee,
    setSellDeliveryFee,
    sellDescription,
    setSellDescription,
    sellDirect,
    setSellDirect,
    sellImage,
    setSellImage,
    sellName,
    setSellName,
    sellPrice,
    setSellPrice,
    sellProposal,
    setSellProposal,
    sellState,
    setSellState,
    sellTag,
    setSellTag,
    addTag,
    addImage,
    onClickSellRegister,
  };
};
