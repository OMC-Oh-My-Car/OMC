// import React from 'react';
import { ButtonArea } from './Button.style';
import { useNavigate } from 'react-router-dom';

const Button = () => {
  const navigate = useNavigate();

  return (
    <>
      <ButtonArea>
        <button onClick={() => navigate('/signin')} className="buttonRed">
          로그인
        </button>
        <button onClick={() => navigate('/signup')} className="buttonYellow">
          회원가입
        </button>
      </ButtonArea>
    </>
  );
};

export default Button;
