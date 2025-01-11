import { createBrowserRouter } from 'react-router-dom';
import {
  DefaultLayout,
  MystoreManageLayout,
  HistoryLayout,
  MystoreLayout,
} from '../layout/Layout';

import {
  MystoreGoodsTemplate,
  MystoreReviewTemplate,
  MystoreKeepTemplate,
  MystoreFollowingTemplate,
  MystoreFollowerTemplate,
  AdjustmentTemplate,
  SellHistoryTemplate,
  BuyHistoryTemplate,
  SellDetailHistoryTemplate,
  BuyDetailHistoryTemplate,
} from '../templates';

import { MainSearchMenu } from '../section/Menu';

import {
  MainPage,
  MainSearchPage,
  SellPage,
  TalkPage,
  MygoodsPage,
  KeepgoodsPage,
  SettingPage,
  ServicePage,
  GoodsManagePage,
  GoodsDetailPage,
  HistoryPage,
  KakaoRedirectPage,
  KakaoTokenPage,
} from '../pages';

export const router = createBrowserRouter([
  { path: '/oauth/kakao/redirect', element: <KakaoRedirectPage /> },
  { path: '/oauth/kakao/token', element: <KakaoTokenPage /> },
  {
    element: <DefaultLayout />,
    errorElement: <div>error</div>,
    children: [
      {
        path: '/',
        element: <MainPage />,
      },
      {
        path: '/talk',
        element: <TalkPage />,
      },
      {
        path: '/keepgoods',
        element: <KeepgoodsPage />,
      },
      {
        path: '/setting',
        element: <SettingPage />,
      },
      {
        path: '/service',
        element: <ServicePage />,
      },
      {
        path: '/detail/:id',
        element: <GoodsDetailPage />,
      },
      {
        element: <MainSearchMenu />,
        children: [
          {
            path: '/category',
            element: <MainSearchPage />,
          },
          // {
          //   path: '/detail/:id',
          //   element: <GoodsDetailPage />,
          // },
        ],
      },
      {
        element: <MystoreLayout />,
        children: [
          {
            path: '/mystore/goods',
            element: <MystoreGoodsTemplate />,
          },
          {
            path: '/mystore/review',
            element: <MystoreReviewTemplate />,
          },
          {
            path: '/mystore/keep',
            element: <MystoreKeepTemplate />,
          },
          {
            path: '/mystore/following',
            element: <MystoreFollowingTemplate />,
          },
          {
            path: '/mystore/follower',
            element: <MystoreFollowerTemplate />,
          },
        ],
      },

      {
        element: <MystoreManageLayout />,
        children: [
          {
            path: '/mygoods',
            element: <MygoodsPage />,
          },
          {
            path: '/sell',
            element: <SellPage />,
          },
          {
            path: '/goodsmanage',
            element: <GoodsManagePage />,
          },
          {
            element: <HistoryLayout />,
            children: [
              {
                path: '/history/sell',
                element: <SellHistoryTemplate />,
              },
              {
                path: '/history/buy',
                element: <BuyHistoryTemplate />,
              },
              {
                path: '/history/adjust',
                element: <AdjustmentTemplate />,
              },
              {
                path: `/history/sell/:id`,
                element: <SellDetailHistoryTemplate />,
              },
              {
                path: `/history/buy/:id`,
                element: <BuyDetailHistoryTemplate />,
              },
            ],
          },
        ],
      },
    ],
  },
]);
