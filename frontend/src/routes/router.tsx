import { createBrowserRouter } from 'react-router-dom';
import { Layout } from '../layout/Layout';
import MainPage from '../pages/MainPage';
import MystorePage from '../pages/MystorePage';
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
    element: <Layout />,
    children: [
      {
        path: '/',
        element: <MainPage />,
      },
      {
        path: '/mystore',
        element: <MystorePage />,
      },
      {
        path: '/sell',
        element: <SellPage />,
      },
      {
        path: '/talk',
        element: <TalkPage />,
      },
      {
        path: '/mygoods',
        element: <MygoodsPage />,
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
        path: '/goodsmanage',
        element: <GoodsManagePage />,
      },
      {
        path: '/history',
        element: <HistoryPage />,
      },
    ],
  },
]);
