// import React from 'react';
// import RouterComponent from "./Router";
import GlobalStyles from './style/GlobalStyles';
import styled, { ThemeProvider } from 'styled-components';
import Router from './router/Router';
import theme from './style/Theme';
export default function App() {
  return (
    <>
      <Container>
        <GlobalStyles />
        <ThemeProvider theme={theme}>
          <Router />
        </ThemeProvider>
      </Container>
    </>
  );
}

const Container = styled.div`
  display: flex;
  justify-content: center;
  width: 100%;
`;
