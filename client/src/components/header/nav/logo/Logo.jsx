// import React from 'react';
import { LogoArea } from './Logo.style';
import logo from '../../../../assets/images/logo.png';
import { useNavigate } from 'react-router-dom';

const Logo = () => {
  const navigate = useNavigate();

  return (
    <>
      <LogoArea onClick={() => navigate('/')}>
        <img className="logoIcon" src={logo} alt="omcLogo" />
        <div className="logo">OMC</div>
      </LogoArea>
    </>
  );
};

export default Logo;
