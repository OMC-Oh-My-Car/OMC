// import { useState } from 'react';
// import RouterComponent from "./Router";
import GlobalStyles from './style/GlobalStyles';
import styled, { ThemeProvider } from 'styled-components';
import Router from './router/Router';
import theme from './style/Theme';
import { useSelector } from 'react-redux';

const App = () => {
  const isOpenModal = useSelector((state) => state.modal.onModal);
  return (
    <>
      <Container isOpenModal={isOpenModal}>
        <GlobalStyles />
        <ThemeProvider theme={theme}>
          <Router />
        </ThemeProvider>
      </Container>
    </>
  );
};

export default App;

const Container = styled.div`
  display: flex;
  justify-content: center;
  width: 100%;
  height: ${(props) => (props.isOpenModal ? '100vh' : 'fit-content')};
  overflow: ${(props) => (props.isOpenModal ? 'hidden' : '')};
`;
