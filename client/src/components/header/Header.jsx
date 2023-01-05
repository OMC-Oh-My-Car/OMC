// import React from 'react';
import { HeaderArea } from './Header.styled';
import Nav from './nav/Nav';

const Header = ({ type }) => {
  return (
    <>
      <HeaderArea>
        <Nav type={type} />
      </HeaderArea>
    </>
  );
};

export default Header;
