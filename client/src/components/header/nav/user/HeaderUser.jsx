// import React from 'react';
import { HeaderUserArea } from './HeaderUser.style';
import Button from './button/Button';
import UserInfo from './userInfo/UserInfo';
import { useSelector, useDispatch } from 'react-redux';

const HeaderUser = () => {
  const userInfo = useSelector((state) => state.user);
  console.log(userInfo);
  return (
    <>
      <HeaderUserArea>
        {userInfo.email ? (
          <>
            <UserInfo />
          </>
        ) : (
          <>
            <Button />
          </>
        )}
      </HeaderUserArea>
    </>
  );
};

export default HeaderUser;
