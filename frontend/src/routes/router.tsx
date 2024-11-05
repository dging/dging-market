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
import MainPage from '../pages/MainPage';
import MainSearchPage from '../pages/MainSearchPage';
import SellPage from '../pages/SellPage';
import TalkPage from '../pages/TalkPage';
import MygoodsPage from '../pages/MygoodsPage';
import KeepgoodsPage from '../pages/KeepgoodsPage';
import SettingPage from '../pages/SettingPage';
import ServicePage from '../pages/ServicePage';
import GoodsManagePage from '../pages/GoodsManagePage';
import HistoryPage from '../pages/HistoryPage';

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
        path: '/category',
        element: <MainSearchPage />,
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
