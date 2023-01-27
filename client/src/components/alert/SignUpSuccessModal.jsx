// import React from "react";
import { ModalBackground, ContainerDiv, HeaderDiv, ButtonTemplate } from './SignUpSuccessModal.style';
import { useEffect, useRef } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPerson } from '@fortawesome/free-solid-svg-icons';
import OrangeButton from '../signUp/signUpForm/OrangeButton';

const SignUpSuccessModal = ({ open, setAlert, message, title, body, callback }) => {
  const outside = useRef();

  useEffect(() => {
    //모달창 화면이 처음에 랜더링이 되었을때
    document.body.style.cssText = `  
      position: fixed;  
      top: -${window.scrollY}px;
      overflow-y: scroll;
      width: 100%;`;
    return () => {
      const scrollY = document.body.style.top;
      document.body.style.cssText = '';
      window.scrollTo(0, parseInt(scrollY || '0', 10) * -1);
    };
  }, []);
  const handleClose = () => {
    setAlert({ open: false });
    if (callback) {
      callback();
    }
  };
  const modalClose = () => {
    setAlert(false);
  };
  return (
    <>
      <ModalBackground
        ref={outside}
        onClick={(e) => {
          if (e.target === outside.current) setAlert(false);
        }}
      >
        <ContainerDiv show={open} hide={handleClose}>
          <HeaderDiv>
            <span className="titleSpan">{title}</span>
          </HeaderDiv>
          <span className="userIconTemplate">
            <FontAwesomeIcon className="handIcon" icon={faPerson} />
          </span>
          <span className="signupTitle">{message}</span>
          <span className="signupBody">{body}</span>
          <ButtonTemplate>
            <OrangeButton text="확인" width="100px" height="50px" onClick={modalClose} />
          </ButtonTemplate>
        </ContainerDiv>
      </ModalBackground>
    </>
  );
};
export default SignUpSuccessModal;
