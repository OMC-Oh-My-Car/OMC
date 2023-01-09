// import React from 'react';
import OrangeButton from './signUpForm/OrangeButton';
import WhiteButton from './signUpForm/WhiteButton';
import SignUpInputBox from './signUpForm/SignUpInputBox';
import { Template, SignUpForm, SignUpButton, SignInComment } from './SignUp.style';
import { faFile } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
const SignUp = () => {
  return (
    <>
      <Template>
        <div className="signUpHead">
          <FontAwesomeIcon className="signUpIcon" icon={faFile} size="2x" />
          <h1>회원가입</h1>
        </div>
        <h1>회원 정보 입력</h1>
        <SignUpForm>
          <SignUpInputBox
            width="100px"
            labelName="아이디"
            inputId="id"
            inputType="text"
            name="id"
            // onChangeInput={onChangeInput}
            placeholder="아이디를 입력해주세요!"
          ></SignUpInputBox>
          <OrangeButton text="아이디 확인" width="120px" height="40px" />
        </SignUpForm>
        <SignUpInputBox
          labelName="비밀번호"
          inputId="password"
          inputType="password"
          name="password"
          // onChangeInput={onChangeInput}
          placeholder="비밀번호를 입력해주세요!"
        ></SignUpInputBox>
        <SignUpInputBox
          labelName="비밀번호 확인"
          inputId="passwordConfirm"
          inputType="password"
          name="passwordConfirm"
          // onChangeInput={onChangeInput}
          placeholder="비밀번호를 다시 입력해주세요!"
        ></SignUpInputBox>
        <SignUpForm>
          <SignUpInputBox
            labelName="이메일"
            inputId="email"
            inputType="email"
            name="email"
            // onChangeInput={onChangeInput}
            placeholder="이메일을 입력해주세요!"
          ></SignUpInputBox>
          <OrangeButton text="인증하기" width="120px" height="40px" />
        </SignUpForm>
        <SignUpButton>
          <WhiteButton text="회원가입" width="180px" height="40px" />
        </SignUpButton>
        <SignInComment>
          이미 가입하셨나요?
          <div className="goLogin">로그인 하러가기</div>
        </SignInComment>
      </Template>
    </>
  );
};
export default SignUp;
