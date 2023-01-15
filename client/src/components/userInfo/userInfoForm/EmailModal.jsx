// import React from "react";
import { ModalBackground, ContainerDiv, HeaderDiv } from './EmailModal.style';
import InfoChange from './InfoChange';
import BlackButton from './BlackButton';
import { useEffect, useRef } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faClose } from '@fortawesome/free-solid-svg-icons';
const EmailModal = ({ setMail }) => {
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
          if (e.target === outside.current) setMail(false);
        }}
      >
        <ContainerDiv>
          <HeaderDiv>
            <span className="titleSpan">이메일</span>
            <span className="closeIconTemplate">
              <FontAwesomeIcon className="closeIcon" icon={faClose} onClick={() => setMail(false)} />
            </span>
          </HeaderDiv>
          <div className="modalInputBox">
            <InfoChange
              labelName="이메일"
              inputId="email"
              inputType="email"
              name="email"
              // onChangeInput={onChangeInput}
              placeholder="새로운 이메일을 입력해주세요!"
            />
            <BlackButton width="470px" height="45px" text="저장" />
          </div>
        </ContainerDiv>
      </ModalBackground>
    </>
  );
};
export default EmailModal;
