// import React from 'react';
// import RouterComponent from "./Router";
import GlobalStyles from './GlobalStyles';
import { ThemeProvider } from 'styled-components';
import theme from './style/Theme';

function App() {
  return (
    <>
      <GlobalStyles />
      <ThemeProvider theme={theme}>
        <div>dummy</div>
      </ThemeProvider>
    </>
  );
}

export default App;
