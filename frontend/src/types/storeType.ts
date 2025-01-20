export interface MyStoreType {
  createdAt: string;
  favoriteProductsTotalCount: number;
  followersTotalCount: number;
  followingsTotalCount: number;
  id: number;
  introduction: string | null;
  isAuthenticated: boolean;
  name: string;
  productsTotalCount: number;
  profileImageUrl: string;
  rating: number | null;
  reviewsTotalCount: number;
  salesCount: number;
  userId: number;
}

export interface MyStoreProductsType {
  id: number;
  storeId: number;
  storeName: string;
  title: string;
  favoriteCount: number;
  runningStatus: string;
  images: { id: number; url: string }[];
  price: number;
  tags: { id: number; name: string }[];
  isTemporarySave: false;
  createdAt: string;
  updatedAt: string;
}

export interface MyStoreReviewsType {
  id: number;
  storeId: number;
  storeName: string;
  profileImageUrl: string;
  rating: number;
  productId: number;
  productName: string;
  content: string;
  createdAt: string;
}

export interface MyStoreFollowingsType {
  storeId: number;
  storeName: string;
  salesCount: number;
  followersCount: number;
  //   수정해야됨
  recentProducts: string[];
  createdAt: string;
}
