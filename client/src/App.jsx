import { useState, useEffect } from 'react';
// import RouterComponent from "./Router";
import GlobalStyles from './style/GlobalStyles';
import styled, { ThemeProvider } from 'styled-components';
import Router from './router/Router';
import theme from './style/Theme';
import { useSelector, useDispatch } from 'react-redux';
import { setUserInfo } from './redux/slice/UserInfo';
import { reissue } from './modules/member/reissue';
import { clearSearch } from './redux/slice/ProductSearch';

const App = () => {
  const dispatch = useDispatch();
  const isOpenModal = useSelector((state) => state.modal.onModal);

  useEffect(() => {
    dispatch(clearSearch());
    const userData = JSON.parse(window.sessionStorage.getItem('userData'));
    const accesstoken = window.sessionStorage.getItem('Authorization');
    if (userData && accesstoken) {
      dispatch(setUserInfo(userData));
    } else {
      reissue()
        .then((res) => {
          console.log(res);
          let accessToken = res.headers.get('Authorization');
          sessionStorage.setItem('Authorization', accessToken);
          sessionStorage.setItem('userData', JSON.stringify(res.data));
          dispatch(setUserInfo(res.data));
        })
        .catch((err) => {
          console.log(`${err.response.status} 에러`);
          console.log(err);
        });
    }
  }, []);
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
