// import React from 'react';
import { NavArea } from './Nav.styled';
import Logo from './logo/Logo';
import SearchInput from './searchInput/SearchInput';
import HeaderUser from './user/HeaderUser';

const Nav = ({ type }) => {
  return (
    <>
      <NavArea type={type}>
        <Logo />
        <SearchInput type={type} />
        <HeaderUser />
      </NavArea>
    </>
  );
};

export default Nav;
