// import React from "react";
import Header from '../components/header/Header';
import { Container } from './UserInfoPage.style';
import UserInfo from '../components/userInfo/ UserInfo';
const UserInfoPage = () => {
  return (
    <>
      <Container>
        <Header type="long" />
        <UserInfo />
      </Container>
    </>
  );
};
export default UserInfoPage;
