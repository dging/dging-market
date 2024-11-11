import { createBrowserRouter } from 'react-router-dom';
import {
  DefaultLayout,
  MystoreManageLayout,
  HistoryLayout,
  MystoreLayout,
} from '../layout/Layout';

import {
  MystoreGoods,
  MystoreReview,
  MystoreKeep,
  MystoreFollowing,
  MystoreFollower,
} from '../templates';

import { MainSearchMenu } from '../components/Menu';

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
} from '../pages';

export const router = createBrowserRouter([
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
            element: <MystoreGoods />,
          },
          {
            path: '/mystore/review',
            element: <MystoreReview />,
          },
          {
            path: '/mystore/keep',
            element: <MystoreKeep />,
          },
          {
            path: '/mystore/following',
            element: <MystoreFollowing />,
          },
          {
            path: '/mystore/follower',
            element: <MystoreFollower />,
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
                path: '/history',
                element: <HistoryPage />,
              },
            ],
          },
        ],
      },
    ],
  },
]);
