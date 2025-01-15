import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {
  useRecoilState,
  useSetRecoilState,
  useRecoilValue,
  useResetRecoilState,
} from 'recoil';
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
import { postProductRegister } from '../../api/product/productApi';

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
  const [sellState, setSellState] = useRecoilState(SellState);
  const [sellTag, setSellTag] = useRecoilState(SellTag);

  const resets = [
    useResetRecoilState(SellAddress),
    useResetRecoilState(SellCategory),
    useResetRecoilState(SellCount),
    useResetRecoilState(SellDeliveryFee),
    useResetRecoilState(SellDescription),
    useResetRecoilState(SellDirect),
    useResetRecoilState(SellImage),
    useResetRecoilState(SellName),
    useResetRecoilState(SellPrice),
    useResetRecoilState(SellProposal),
    useResetRecoilState(SellState),
    useResetRecoilState(SellTag),
  ];

  const navigate = useNavigate();

  const invalidField: string[] = [];

  const resetState = () => {
    resets.forEach((reset) => reset());
  };

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

  const onClickSellRegister = async () => {
    // console.log(
    //   'sellAddress : ',
    //   sellAddress,
    //   typeof sellAddress,
    //   'sellCategory : ',
    //   sellCategory,
    //   typeof sellCategory,
    //   ' sellCount : ',
    //   sellCount,
    //   typeof sellCount,
    //   ' sellDeliveryFee : ',
    //   sellDeliveryFee,
    //   typeof sellDeliveryFee,
    //   ' sellDescription : ',
    //   sellDescription,
    //   typeof sellDescription,
    //   ' sellDirect : ',
    //   sellDirect,
    //   typeof sellDirect,
    //   ' sellImage : ',
    //   sellImage,
    //   typeof sellImage,
    //   ' sellName : ',
    //   sellName,
    //   typeof sellName,
    //   ' sellPrice : ',
    //   sellPrice,
    //   typeof sellPrice,
    //   ' sellProposal : ',
    //   sellProposal,
    //   typeof sellProposal,
    //   ' sellState : ',
    //   sellState,
    //   typeof sellState,
    //   ' sellTag : ',
    //   sellTag,
    //   typeof sellTag
    // );
    const data = {
      imageIds: sellImage,
      title: sellName,
      mainCategory: sellCategory[1],
      middleCategory: sellCategory[2],
      subCategory: sellCategory[3],
      quality: sellState,
      content: sellDescription,
      tags: sellTag,
      price: Number(sellPrice),
      allowsOffers: sellProposal,
      isShippingFeeIncluded: sellDeliveryFee === '배송비포함',
      isDirectTradeAvailable: sellDirect === '가능',
      region: sellAddress.address,
      location: sellAddress.detailAddress,
      quantity: Number(sellCount),
      isTemporarySave: false,
    };

    console.log(data);

    checkSell();

    if (invalidField.length === 0) {
      console.log('pass');

      const result = await postProductRegister(data);
      switch (result) {
        case 201:
          alert('상품이 성공적으로 등록되었습니다.');
          navigate('/');
          break;
        case 400:
          alert('업로드가 되지 않은 파일이 존재합니다.');
          break;
        case 404:
          alert('사용자가 존재하지 않습니다.');
          navigate('/');
          break;
      }
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
    resetState,
    addTag,
    addImage,
    onClickSellRegister,
  };
};
