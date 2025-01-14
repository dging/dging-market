import React, { useState, useEffect } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../components';
import { GoodsProfile } from '../section';
import { GoodsInfo, StoreInfo } from '../templates';
import { getProductsId } from '../api/product/productApi';
import {
  DetailProductType,
  GoodsProfileType,
  GoodsInfoType,
} from '../types/productType';

const WrapInfo = styled.div`
  display: grid;
  grid-template-columns: 767px 1px 373px;
  gap: 10px;
`;

const Bar = styled.div`
  width: 1px;
  height: 100%;
  background-color: ${({ theme }) => theme.color.black5};
`;

export default function GoodsDetailPage() {
  const { goodsId } = useParams();
  const theme = useTheme();
  const [data, setData] = useState<DetailProductType | null>(null);
  const [goodsProfile, setGoodsProfile] = useState<GoodsProfileType>(
    {} as GoodsProfileType
  );
  const [goodsInfo, setGoodsInfo] = useState<GoodsInfoType>(
    {} as GoodsInfoType
  );

  const productId = async () => {
    try {
      if (goodsId) {
        await getProductsId(goodsId).then((res) => {
          console.log(res);
          setData(res);
          console.log(data);
          setGoodsProfile(
            (({
              createdAt,
              favoriteCount,
              images,
              price,
              quality,
              region,
              location,
              title,
              viewCount,
            }) => ({
              createdAt,
              favoriteCount,
              images,
              price,
              quality,
              region,
              location,
              title,
              viewCount,
            }))(res)
          );

          setGoodsInfo(
            (({
              content,
              region,
              location,
              mainCategory,
              middleCategory,
              subCategory,
              tags,
            }) => ({
              content,
              region,
              location,
              mainCategory,
              middleCategory,
              subCategory,
              tags,
            }))(res)
          );
        });
      }
    } catch {
      return {};
    }
  };

  useEffect(() => {
    productId();
  }, []);

  console.log(goodsProfile, goodsInfo);

  return (
    <>
      {data && (
        <Arrange
          width={theme.page_size.width}
          margin='0 auto'
          padding='20px 0 100px 0'
        >
          <GoodsProfile
            createdAt={data.createdAt}
            favoriteCount={data.favoriteCount}
            images={data.images}
            price={data.price}
            quality={data.quality}
            region={data.region}
            location={data.location}
            title={data.title}
            viewCount={data.viewCount}
          />

          <WrapInfo>
            <GoodsInfo
              content={data.content}
              region={data.region}
              location={data.location}
              mainCategory={data.mainCategory}
              middleCategory={data.middleCategory}
              subCategory={data.subCategory}
              tags={data.tags}
            />
            <Bar />
            <StoreInfo />
          </WrapInfo>
        </Arrange>
      )}
    </>
  );
}
