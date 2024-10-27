import { useState } from 'react';
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
// import './App.css'
import Btn from './components/Button/Btn';

function App() {
  // const [count, setCount] = useState(0)

  return (
    <>
      <Btn status={true} width='200px'>
        후기 남기기
      </Btn>
    </>
  );
}

export default App;
