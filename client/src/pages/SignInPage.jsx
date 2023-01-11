// import React from 'react';
import SignIn from '../components/signIn/SignIn';
import { Container, Main } from './SignInPage.style';
import Header from '../components/header/Header';
const SignInPage = () => {
  return (
    <>
      <Container>
        <Header />
        <Main>
          <SignIn />
        </Main>
      </Container>
    </>
  );
};
export default SignInPage;
