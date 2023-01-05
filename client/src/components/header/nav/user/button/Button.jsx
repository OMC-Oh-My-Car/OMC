// import React from 'react';
import { ButtonArea } from './Button.styled';

const Button = () => {
  return (
    <>
      <ButtonArea>
        <button className="buttonRed">로그인</button>
        <button className="buttonYellow">회원가입</button>
      </ButtonArea>
    </>
  );
};

export default Button;
