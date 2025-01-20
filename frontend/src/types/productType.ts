export interface ProductType {
  createdAt: string;
  id: number;
  images: { id: number; url: string }[];
  price: number;
  runningStatus: string;
  storeId: number;
  storeName: string;
  tags: { id: number; name: string }[];
  title: string;
}

export interface DetailProductType {
  id: number;
  storeId: number;
  storeName: string;
  title: string;
  content: string;
  favoriteCount: number;
  viewCount: number;
  quality: string;
  quantity: number;
  region: string;
  location: string;
  mainCategory: string;
  middleCategory: string;
  subCategory: string;
  runningStatus: string;
  images: { id: number; url: string }[];
  price: number;
  tags: { id: number; name: string }[];
  isTemporarySave: boolean;
  createdAt: string;
}

export interface GoodsProfileType {
  createdAt: string;
  favoriteCount: number;
  images: { id: number; url: string }[];
  price: number;
  quality: string;
  region: string;
  location: string;
  title: string;
  viewCount: number;
}

export interface GoodsInfoType {
  content: string;
  region: string;
  location: string;
  mainCategory: string;
  middleCategory: string;
  subCategory: string;
  tags: { id: number; name: string }[];
}

export interface ProductSellType {
  imageIds?: { id: number; url: string }[];
  title: string;
  mainCategory: string;
  middleCategory: string;
  subCategory: string;
  quality: string;
  content: string;
  tags: string[];
  price: number;
  allowsOffers: boolean;
  isShippingFeeIncluded: boolean;
  isDirectTradeAvailable: boolean;
  region: string;
  location: string;
  quantity: number;
  isTemporarySave: boolean;
}

export interface StoreInfoType {
  followersCount: number;
  name: string;
  salesCount: number;
}

export interface MainCardType {
  goodsId: string;
  storeId: string;
  bg?: string;
  title: string;
  price: number;
  date: string;
}

export interface ProductsFavoriteType {
  id: number;
  storeId: number;
  storeName: string;
  title: string;
  runningStatus: string;
  images: { id: number; url: string }[];
  price: number;
  tags: { id: number; name: string }[];
  createdAt: string;
}
