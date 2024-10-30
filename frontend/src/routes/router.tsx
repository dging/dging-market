import { createBrowserRouter } from 'react-router-dom';
import { Layout } from '../layout/Layout';
import MainPage from '../pages/MainPage';
import MystorePage from '../pages/MystorePage';
import SellPage from '../pages/SellPage';
import TalkPage from '../pages/TalkPage';

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
    ],
  },
]);
