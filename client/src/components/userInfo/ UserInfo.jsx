// import React from 'react';
// import BlackButton from './userInfoForm/BlackButton';
import UserInfoInputBox from './userInfoForm/UserInfoInputBox';
import { Template, UserInfoForm } from './ UserInfo.style';
import { faInfoCircle } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const UserInfo = () => {
  return (
    <>
      <Template>
        <div className="userInfoHead">
          <FontAwesomeIcon className="userInfoIcon" icon={faInfoCircle} size="2x" />
          <h1>개인정보</h1>
        </div>
        <UserInfoForm>
          <UserInfoInputBox
            width="100px"
            labelName="실명"
            inputId="id"
            inputType="text"
            name="id"
            // onChangeInput={onChangeInput}
            text="나호연"
          />
          <UserInfoInputBox
            width="100px"
            labelName="이메일 주소"
            inputId="이메일"
            inputType="email"
            name="email"
            // onChangeInput={onChangeInput}
            text="nahy0107@naver.com"
          />
          <UserInfoInputBox
            width="100px"
            labelName="전화번호"
            inputId="전화번호"
            inputType="tel"
            name="tel"
            // onChangeInput={onChangeInput}
            text="010-9775-7034"
          />
          <UserInfoInputBox
            width="100px"
            labelName="주소"
            inputId="주소"
            inputType="url"
            name="url"
            // onChangeInput={onChangeInput}
            text="경상북도 구미시 상사서로 17 상모동 우방아파트 202동 1205호"
          />
        </UserInfoForm>
      </Template>
    </>
  );
};
export default UserInfo;
