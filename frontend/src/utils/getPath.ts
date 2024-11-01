import { useLocation } from 'react-router-dom';

export const getPath = () => {
  const location = useLocation();
  return location.pathname;
};
