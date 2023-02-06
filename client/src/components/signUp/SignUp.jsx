// import React from 'react';
import OrangeButton from './signUpForm/OrangeButton';
import SignUpInputBox from './signUpForm/SignUpInputBox';
import SignUpInputLongBox from './signUpForm/SignUpInputLongBox';
import { Template, SignUpForm, SignUpButton, SignInComment, EmailButton, GoLogin } from './SignUp.style';
import { faFile } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useCallback, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import SignUpSuccessModal from '../alert/SignUpSuccessModal';
import ConfirmNumberModal from './signUpForm/ConfirmNumberModal';
const SignUp = () => {
  const [idEmail, setIdEmail] = useState('');
  const [password, setPassword] = useState('');
  const [passwordConfirm, setPasswordConfirm] = useState('');
  const [username, setUsername] = useState('');
  const [nickname, setNickname] = useState('');
  const [phone, setPhone] = useState('');
  const [confirmNumber, setConfirmNumber] = useState('');

  const [idEmailError, setIdEmailError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [passwordConfirmError, setPasswordConfirmError] = useState('');
  const [usernameError, setUsernameError] = useState('');
  const [nicknameError, setNicknameError] = useState('');
  const [phoneError, setPhoneError] = useState('');

  //유효성 검사
  const [isIdEmail, setIsIdEmail] = useState(false);
  const [isPassword, setIsPassword] = useState(false);
  const [isPasswordConfirm, setIsPasswordConfirm] = useState(false);
  const [isUsername, setIsUsername] = useState(false);
  const [isNickname, setIsNickname] = useState(false);
  const [isPhone, setIsPhone] = useState(false);
  const navigate = useNavigate();

  const [alert, setAlert] = useState({
    open: false,
    title: '',
    message: '',
    body: '',
    callback: false,
  });
  const confirmNumberHandler = () => {
    setConfirmNumber(true);
  };
  const onChangeId = useCallback((e) => {
    setIdEmail(e.target.value);
    const emailRegex =
      /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    const emailCurrent = e.target.value;
    setIdEmail(emailCurrent);
    if (!emailRegex.test(emailCurrent)) {
      setIdEmailError('이메일 형식이 틀렸어요! 다시 확인해주세요 ㅜ ㅜ');
      setIsIdEmail(false);
    } else {
      setIdEmailError('올바른 이메일 형식이에요 : )');
      setIsIdEmail(true);
    }
  }, []);

  const onChangePassword = useCallback((e) => {
    setPassword(e.target.value);
    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
    const passwordCurrent = e.target.value;
    setPassword(passwordCurrent);

    if (!passwordRegex.test(passwordCurrent)) {
      setPasswordError('숫자+영문자+특수문자 조합으로 8자리 이상 입력해주세요!');
      setIsPassword(false);
    } else {
      setPasswordError('안전한 비밀번호에요 : )');
      setIsPassword(true);
    }
  }, []);

  const onChangePasswordConfirm = useCallback(
    (e) => {
      setPasswordConfirm(e.target.value);
      const passwordConfirmCurrent = e.target.value;
      setPasswordConfirm(passwordConfirmCurrent);
      if (password === passwordConfirmCurrent) {
        setPasswordConfirmError('비밀번호를 똑같이 입력했어요 : )');
        setIsPasswordConfirm(true);
      } else {
        setPasswordConfirmError('비밀번호가 틀려요. 다시 확인해주세요 ㅜ ㅜ');
        setIsPasswordConfirm(false);
      }
    },
    [password],
  );
  const onChangeUserName = useCallback((e) => {
    setUsername(e.target.value);
    if (e.target.value.length < 2 || e.target.value.length > 5) {
      setUsernameError('2글자 이상 5글자 미만으로 입력해주세요.');
      setIsUsername(false);
    } else {
      setUsernameError('올바른 이름 형식입니다 :)');
      setIsUsername(true);
    }
  }, []);
  const onChangeNickName = useCallback((e) => {
    setNickname(e.target.value);
    if (e.target.value.length < 2 || e.target.value.length > 10) {
      setNicknameError('2글자 이상 10글자 미만으로 입력해주세요.');
      setIsNickname(false);
    } else {
      setNicknameError('올바른 닉네임 형식입니다 :)');
      setIsNickname(true);
    }
  }, []);
  const onChangePhone = useCallback((e) => {
    setPhone(e.target.value);
    if (e.target.value.length < 11) {
      setPhoneError('11개의 숫자를 입력해주세요.');
      setIsPhone(false);
    } else {
      setPhoneError('올바른 전화번호 형식입니다 :)');
      setIsPhone(true);
    }
  }, []);
  const submitHandle = async () => {
    await axios
      .post('/member', {
        email: idEmail,
        password: password,
        passwordConfirm: passwordConfirm,
        username: username,
        nickname: nickname,
        phone: phone,
      })
      .then((res) => {
        if (res.status === 201) {
          console.log('회원가입 성공');
          setAlert({
            open: true,
            title: '회원가입 완료',
            message: '회원가입이 완료되었습니다. 로그인 페이지로 이동합니다.',
            body: '로그인 후 서비스를 모두 이용하실 수 있습니다.',
            callback: function () {
              navigate('/member/login');
            },
          });
        }
      })
      .catch((err) => {
        if (err.response.status === 401) {
          console.log('401 에러');
          console.log(err);
        } else {
          console.log(`${err.response.status} 에러`);
          console.log(err);
        }
        setAlert({
          open: true,
          title: '회원가입 실패!!!',
          message: '회원가입에 실패했습니다. 관리자에게 문의해주세요.',
          body: '로그인 후 서비스를 모두 이용하실 수 있습니다.',
          callback: function () {
            navigate('/member/login');
          },
        });
      });
  };
  const sendNumberHandler = async () => {
    await axios
      .post('/member/confirm/mail', {
        email: idEmail,
      })
      .then((res) => {
        if (res.status === 201) {
          console.log('메일함에서 인증번호를 확인해주세요!');
        }
      })
      .catch((err) => {
        if (err.response.status === 401) {
          console.log('401에러');
          console.log(err);
        } else {
          console.log(`${err.response.status} 에러`);
          console.log(err);
        }
      });
  };
  useEffect(() => {
    setAlert({
      title: '!회원가입!',
      message: 'OMC에 오신것을 환영합니다!',
      body: '회원가입 후 서비스를 모두 이용하실 수 있습니다 !!!!!!!!!!!!!!!!!!',
    });
  }, []);
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
            labelName="아이디"
            inputId="email"
            inputType="email"
            name="email"
            onChangeInput={onChangeId}
            placeholder="이메일을 입력해주세요!"
          />
          <EmailButton>
            <OrangeButton
              text="인증하기"
              width="120px"
              height="40px"
              onClick={() => {
                confirmNumberHandler();
                sendNumberHandler();
              }}
            />
            {confirmNumber && <ConfirmNumberModal setConfirmNumber={setConfirmNumber} />}
          </EmailButton>
        </SignUpForm>
        {idEmail.length > 0 && <span className={`message ${isIdEmail ? 'success' : 'error'}`}>{idEmailError}</span>}
        <SignUpForm>
          <SignUpInputLongBox
            labelName="비밀번호"
            inputId="password"
            inputType="password"
            name="password"
            onChangeInput={onChangePassword}
            placeholder="비밀번호를 입력해주세요!"
          />
        </SignUpForm>
        {password.length > 0 && <span className={`message ${isPassword ? 'success' : 'error'}`}>{passwordError}</span>}
        <SignUpForm>
          <SignUpInputLongBox
            labelName="비밀번호 확인"
            inputId="passwordConfirm"
            inputType="password"
            name="passwordConfirm"
            onChangeInput={onChangePasswordConfirm}
            placeholder="비밀번호를 다시 입력해주세요!"
          />
        </SignUpForm>
        {passwordConfirm.length > 0 && (
          <span className={`message ${isPasswordConfirm ? 'success' : 'error'}`}>{passwordConfirmError}</span>
        )}
        <SignUpForm>
          <SignUpInputLongBox
            labelName="본명"
            inputId="name"
            inputType="name"
            name="name"
            onChangeInput={onChangeUserName}
            placeholder="본명을 입력해주세요!"
          />
        </SignUpForm>
        {username.length > 0 && <span className={`message ${isUsername ? 'success' : 'error'}`}>{usernameError}</span>}
        <SignUpForm>
          <SignUpInputLongBox
            labelName="닉네임"
            inputId="nickname"
            inputType="nickname"
            name="nickname"
            onChangeInput={onChangeNickName}
            placeholder="닉네임을 입력해주세요!"
          />
        </SignUpForm>
        {nickname.length > 0 && <span className={`message ${isNickname ? 'success' : 'error'}`}>{nicknameError}</span>}
        <SignUpForm>
          <SignUpInputLongBox
            labelName="전화번호"
            inputId="phone"
            inputType="phone"
            name="phone"
            onChangeInput={onChangePhone}
            placeholder="전화번호를 입력해주세요!"
          />
        </SignUpForm>
        {phone.length > 0 && <span className={`message ${isPhone ? 'success' : 'error'}`}>{phoneError}</span>}
        <SignUpButton>
          <OrangeButton text="회원가입" width="180px" height="40px" onClick={submitHandle} />
          {alert && (
            <SignUpSuccessModal
              setAlert={setAlert}
              message={alert.message}
              title={alert.title}
              callback={alert.callback}
              body={alert.body}
            />
          )}
        </SignUpButton>
        <SignInComment>
          이미 가입하셨나요?
          <GoLogin href={'/member/login'}>로그인 하러가기</GoLogin>
        </SignInComment>
      </Template>
    </>
  );
};
export default SignUp;
