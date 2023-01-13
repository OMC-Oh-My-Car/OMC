// import React from "react";
import OrangeButton from '../signUp/signUpForm/OrangeButton';
import SignUpInputBox from '../signUp/signUpForm/SignUpInputBox';
import { Template, SignInButton, GoogleButtonTemplate, KakaoButtonTemplate } from './SignIn.style';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faRightToBracket } from '@fortawesome/free-solid-svg-icons';
import CheckBox from './checkBox/CheckBox';
import GoogleButton from './social/Google';
import KakaoButton from './social/Kakao';
const SignIn = () => {
  return (
    <>
      <Template>
        <div className="signInHead">
          <FontAwesomeIcon className="signInIcon" icon={faRightToBracket} size="2x" />
          <h1>로그인</h1>
        </div>
        <SignUpInputBox
          labelName="아이디"
          inputId="id"
          inputType="text"
          name="id"
          // onChangeInput={onChangeInput}
          placeholder="아이디를 입력해주세요!"
        />
        <div className="idCheckBox">
          <CheckBox text="아이디 기억하기" />
        </div>
        <SignUpInputBox
          labelName="비밀번호"
          inputId="password"
          inputType="password"
          name="password"
          // onChangeInput={onChangeInput}
          placeholder="비밀번호를 입력해주세요!"
        />
        <SignInButton>
          <OrangeButton text="로그인" width="197px" height="40px" />
        </SignInButton>
        <GoogleButtonTemplate>
          <GoogleButton />
        </GoogleButtonTemplate>
        <KakaoButtonTemplate>
          <KakaoButton />
        </KakaoButtonTemplate>
      </Template>
    </>
  );
};
export default SignIn;
