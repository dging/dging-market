import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { RouterProvider } from 'react-router-dom';
import { ThemeProvider } from 'styled-components';
import { theme } from './styles/theme.ts';
import App from './App.jsx';
import GlobalStyle from './styles/global.ts';
import { router } from './routes/router.tsx';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <RouterProvider router={router} />
      <App />
    </ThemeProvider>
  </StrictMode>
);
