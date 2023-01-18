// import React from 'react';
import { Container, MainContainer } from './UserReservationPage.style';
import Header from '../components/header/Header';
import ReservationUserEmpty from '../components/userReservation/UserReservationEmpty';

const ReservationUserPage = () => {
  return (
    <>
      <Container>
        <Header />
        <MainContainer>
          <ReservationUserEmpty />
        </MainContainer>
      </Container>
    </>
  );
};
export default ReservationUserPage;
