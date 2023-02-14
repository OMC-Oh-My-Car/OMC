// import React from 'react';
import SignUp from '../components/signUp/SignUp';
import { Container, Main } from './SignUpPage.style';
import Header from '../components/header/Header';

const SignUpPage = () => {
  return (
    <>
      <Container>
        <Header />
        <Main>
          <SignUp />
        </Main>
      </Container>
    </>
  );
};
export default SignUpPage;
