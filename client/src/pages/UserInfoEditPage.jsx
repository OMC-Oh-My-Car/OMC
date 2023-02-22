// import React from "react";
import Header from '../components/header/Header';
import { Container } from './UserInfoPage.style';
import UserInfoEdit from '../components/userInfoEdit/UserInfoEdit';
const UserInfoEditPage = () => {
  return (
    <>
      <Container>
        <Header type="long" />
        <UserInfoEdit />
      </Container>
    </>
  );
};
export default UserInfoEditPage;
