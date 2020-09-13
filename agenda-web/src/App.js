import React from 'react';
import { ToastContainer } from 'react-toastify';
import 'bootstrap/dist/css/bootstrap.min.css';

import GlobalStyle from './styles/global';

import Home from './pages/Home';

function App() {
  return (
    <>
      <GlobalStyle />
      <ToastContainer />
      <Home />
    </>
  );
}

export default App;
