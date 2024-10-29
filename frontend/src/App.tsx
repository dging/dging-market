import { useState } from 'react';
import Btn from './components/Button/Btn';
import Footer from './layout/Footer/Footer';
import LoginHeader from './layout/Header/LoginHeader';
import MainHeader from './layout/Header/MainHeader';

function App() {
  const [active, setActive] = useState<number>(0);
  const buttons = ['상품등록', '상품관리', '구매 / 판매 내역'];

  return (
    <>
      <LoginHeader />
      <MainHeader />

      {buttons.map((value, index) => (
        <Btn
          key={index}
          $status={index === active}
          width='200px'
          onClick={() => setActive(index)}
        >
          {value}
        </Btn>
      ))}
      <Footer />
    </>
  );
}

export default App;
