// import React from "react";
import OrangeButton from '../signUp/signUpForm/OrangeButton';
import SignUpInputBox from '../signUp/signUpForm/SignUpInputBox';
import { Template, SignInButton } from './SignIn.style';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faRightToBracket } from '@fortawesome/free-solid-svg-icons';
import kakaoIcon from '../../assets/images/kakaoIcon.png';
import googleIcon from '../../assets/images/googleIcon.png';
import CheckBox from './checkBox/CheckBox';
import GoogleButton from './social/Google';
import KakaoButton from './social/Kakao';
import { useState, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { login } from '../../modules/member/login';
import { logout } from '../../modules/member/logout';
import { useDispatch } from 'react-redux';
import { setUserInfo, clearUserInfo } from '../../redux/slice/UserInfo';

const SignIn = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const kakaoLogin = '';
  const googleLogin = '';
  const [idEmail, setIdEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleIdEmail = (e) => {
    setIdEmail(e.target.value);
  };

  const handlePassword = (e) => {
    setPassword(e.target.value);
  };

  const submitHandler = (data) => {
    console.log(`${idEmail}, ${password}`);
    login(data)
      .then((res) => {
        let accessToken = res.headers.get('Authorization');
        sessionStorage.setItem('Authorization', accessToken);
        sessionStorage.setItem('userData', JSON.stringify(res.data));
        dispatch(setUserInfo(res.data));
        navigate('/');
      })
      .catch((err) => {
        console.log(`${err.response.status} 에러`);
        console.log(err);
      });
  };

  const handleLogout = async () => {
    logout()
      .then((res) => {
        console.log('로그아웃되었습니다.');
        window.sessionStorage.removeItem('Authorization');
        dispatch(clearUserInfo());
        navigate('/');
      })
      .catch((err) => {
        console.log(`${err.response.status} 에러`);
        console.log('로그아웃 실패!');
      });
  };

  return (
    <>
      <Template>
        <h1>로그인</h1>
        <SignUpInputBox
          labelName="아이디"
          inputId="email"
          inputType="email"
          name="email"
          onChangeInput={handleIdEmail}
          placeholder="이메일을 입력해주세요!"
        />
        <SignUpInputBox
          labelName="비밀번호"
          inputId="password"
          inputType="password"
          name="password"
          onChangeInput={handlePassword}
          placeholder="비밀번호를 입력해주세요!"
        />
        <button
          className="btn"
          onClick={() =>
            submitHandler({
              email: idEmail,
              password: password,
            })
          }
        >
          로그인
        </button>
        <div className="signupLink">
          Don’t have an account?
          <Link to={'/signup'}>Sign up</Link>
        </div>
        <div className="flexBox">
          <a href={kakaoLogin}>
            <img src={kakaoIcon} alt="kakaoAuth" />
          </a>
          <a href={googleLogin}>
            <img src={googleIcon} alt="googleAuth" />
          </a>
        </div>
        {/* <GoogleButton />
        <KakaoButton /> */}
      </Template>
    </>
  );
};
export default SignIn;
