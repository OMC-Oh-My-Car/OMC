// import React from "react";
import OrangeButton from '../signUp/signUpForm/OrangeButton';
import SignUpInputBox from '../signUp/signUpForm/SignUpInputBox';
import { Template, SignInButton, GoogleButtonTemplate, KakaoButtonTemplate } from './SignIn.style';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faRightToBracket } from '@fortawesome/free-solid-svg-icons';
import CheckBox from './checkBox/CheckBox';
import GoogleButton from './social/Google';
import KakaoButton from './social/Kakao';
import { useState, useEffect } from 'react';
// import { LOGIN_URL } from 'api';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
// import AlertAlarm from '../alert/AlertAlarm';
import SignUpSuccessModal from '../alert/SignUpSuccessModal';
const SignIn = () => {
  const [idEmail, setIdEmail] = useState('');
  const [password, setPassword] = useState('');

  const [alert, setAlert] = useState({
    open: false,
    title: '',
    message: '',
    callback: false,
  });

  const navigate = useNavigate();

  const handleIdEmail = (e) => {
    setIdEmail(e.target.value);
  };
  const handlePassword = (e) => {
    setPassword(e.target.value);
  };
  const handleLogout = () => {
    axios
      .post('https://ffa0-49-142-61-236.jp.ngrok.io/member/logout', {
        headers: {
          authorization: localStorage.getItem('Authorization'),
        },
      })
      .then(() => {
        console.log('로그아웃되었습니다.');
        localStorage.clear();
        navigate('/');
      })
      .catch((err) => {
        console.log(localStorage.getItem('Authorization'));
        console.log(`${err.response.status} 에러`);
        console.log('로그아웃 실패!');
      });
  };
  const submitHandler = () => {
    console.log(`${idEmail}, ${password}`);
    axios
      .post('https://ffa0-49-142-61-236.jp.ngrok.io/member/login', {
        email: idEmail,
        password: password,
      })
      .then((res) => {
        let accessToken = res.headers.get('Authorization');
        let refreshToken = res.headers.get('Set-Cookie');

        localStorage.setItem('Authorization', accessToken);
        localStorage.setItem('Set-Cookie', refreshToken);
        console.log(res);
        navigate('/login');
      })
      .catch((err) => {
        console.log(`${err.response.status} 에러`);
        console.log(err);
        setAlert({
          open: true,
          title: '로그인 실패',
          message: '로그인에 실패했습니다. 관리자에게 문의해주세요.',
        });
      });
  };
  useEffect(() => {
    setAlert({
      title: '!로그인!',
      message: 'OMC에 오신것을 환영합니다!',
      body: '로그인 후 서비스를 모두 이용하실 수 있습니다.',
    });
  }, []);
  return (
    <>
      <Template>
        <div className="signInHead">
          <FontAwesomeIcon className="signInIcon" icon={faRightToBracket} size="2x" />
          <h1>로그인</h1>
        </div>
        <SignUpInputBox
          labelName="아이디"
          inputId="email"
          inputType="email"
          name="email"
          onChangeInput={handleIdEmail}
          placeholder="이메일을 입력해주세요!"
        />
        <div className="idCheckBox">
          <CheckBox text="아이디 기억하기" />
        </div>
        <SignUpInputBox
          labelName="비밀번호"
          inputId="password"
          inputType="password"
          name="password"
          onChangeInput={handlePassword}
          placeholder="비밀번호를 입력해주세요!"
        />
        <SignInButton>
          <OrangeButton text="로그인" width="197px" height="40px" onClick={submitHandler} />
          <OrangeButton text="로그아웃" width="197px" height="40px" onClick={handleLogout} />
          {alert && (
            <SignUpSuccessModal
              setAlert={setAlert}
              message={alert.message}
              title={alert.title}
              callback={alert.callback}
              body={alert.body}
            />
          )}
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
