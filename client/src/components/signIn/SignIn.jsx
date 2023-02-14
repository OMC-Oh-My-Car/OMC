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
import { useNavigate, Link } from 'react-router-dom';
import SignUpSuccessModal from '../alert/SignUpSuccessModal';
import { login } from '../../modules/member/login';
import { logout } from '../../modules/member/logout';
import { useDispatch } from 'react-redux';
import { setUserInfo, clearUserInfo } from '../../redux/slice/UserInfo';

const SignIn = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [idEmail, setIdEmail] = useState('');
  const [password, setPassword] = useState('');
  const [alert, setAlert] = useState({
    open: false,
    title: '',
    message: '',
    callback: false,
  });

  useEffect(() => {
    setAlert({
      title: '!로그인!',
      message: 'OMC에 오신것을 환영합니다!',
      body: '로그인 후 서비스를 모두 이용하실 수 있습니다.',
    });
  }, []);
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
        navigate('/signin');
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
          <Link to={'/signin'}>
            <OrangeButton
              text="로그인"
              width="197px"
              height="40px"
              onClick={() =>
                submitHandler({
                  email: idEmail,
                  password: password,
                })
              }
            />
          </Link>
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
