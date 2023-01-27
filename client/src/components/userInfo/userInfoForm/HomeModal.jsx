// import React from "react";
import { ModalBackground, ContainerDiv, HeaderDiv } from './HomeModal.style';
import InfoChange from './InfoChange';
import BlackButton from './BlackButton';
import { useEffect, useRef } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faClose } from '@fortawesome/free-solid-svg-icons';
const HomeModal = ({ setHome }) => {
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
          if (e.target === outside.current) setHome(false);
        }}
      >
        <ContainerDiv>
          <HeaderDiv>
            <span className="titleSpan">주소</span>
            <span className="closeIconTemplate">
              <FontAwesomeIcon className="closeIcon" icon={faClose} onClick={() => setHome(false)} />
            </span>
          </HeaderDiv>
          <div className="modalInputBox">
            <InfoChange
              labelName="주/도"
              inputId="text"
              inputType="text"
              name="text"
              // onChangeInput={onChangeInput}
              placeholder="예) 서울특별시"
            />
            <InfoChange
              labelName="시/군/구"
              inputId="text"
              inputType="text"
              name="text"
              // onChangeInput={onChangeInput}
              placeholder="예) 강남구"
            />
            <InfoChange
              labelName="도로명 주소"
              inputId="text"
              inputType="text"
              name="text"
              // onChangeInput={onChangeInput}
              placeholder="예) 상사서로 17"
            />
            <InfoChange
              labelName="동호수"
              inputId="text"
              inputType="text"
              name="text"
              // onChangeInput={onChangeInput}
              placeholder="예) 202동 1205호"
            />
            <BlackButton width="480px" height="45px" text="저장" />
          </div>
        </ContainerDiv>
      </ModalBackground>
    </>
  );
};
export default HomeModal;
