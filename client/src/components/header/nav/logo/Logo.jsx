// import React from 'react';
import { LogoArea } from './Logo.style';
import logo from '../../../../assets/images/logo.png';

const Logo = () => {
  return (
    <>
      <LogoArea>
        <img className="logoIcon" src={logo} alt="omcLogo" />
        <div className="logo">OMC</div>
      </LogoArea>
    </>
  );
};

export default Logo;
