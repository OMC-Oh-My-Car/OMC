// import React from 'react';
import { HeaderUserArea } from './HeaderUser.style';
// import Button from './button/Button';
import UserInfo from './userInfo/UserInfo';

const HeaderUser = () => {
  return (
    <>
      <HeaderUserArea>
        {/* <Button /> */}
        <UserInfo />
      </HeaderUserArea>
    </>
  );
};

export default HeaderUser;
