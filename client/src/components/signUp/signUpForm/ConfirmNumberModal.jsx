// import React from "react";
import { ModalBackground, ContainerDiv, HeaderDiv } from './ConfirmNumberModal.style';
import InfoChange from '../../userInfo/userInfoForm/InfoChange';
import BlackButton from '../../userInfo/userInfoForm/BlackButton';
import { useEffect, useRef } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faClose } from '@fortawesome/free-solid-svg-icons';
const EmailModal = ({ setConfirmNumber }) => {
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
  //body 태그의 css를 position을 fixed로 변경하고,
  //top을 현재 스크롤 위치로 하고 overflow-y: scroll; width: 100%;을 추가
  //스크롤 방지
  //모달이 사라질 때에는 useEffect의 return을 사용해
  //body의 cssText를 리셋시킨 다음 window.scrollTo를 이용해 현재 스크롤 위치로 이동
  return (
    <>
      <ModalBackground
        ref={outside}
        onClick={(e) => {
          if (e.target === outside.current) setConfirmNumber(false);
        }}
      >
        <ContainerDiv>
          <HeaderDiv>
            <span className="titleSpan">인증번호</span>
            <span className="closeIconTemplate">
              <FontAwesomeIcon className="closeIcon" icon={faClose} onClick={() => setConfirmNumber(false)} />
            </span>
          </HeaderDiv>
          <div className="modalInputBox">
            <InfoChange
              labelName="인증번호"
              inputId="text"
              inputType="text"
              name="text"
              // onChangeInput={onChangeInput}
              placeholder="인증번호를 입력해주세요!"
            />
            <BlackButton width="475px" height="45px" text="인증하기" />
          </div>
        </ContainerDiv>
      </ModalBackground>
    </>
  );
};
export default EmailModal;
