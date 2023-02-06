// import React from 'react';
// import BlackButton from './userInfoForm/BlackButton';
import UserInfoInputBox from './userInfoForm/UserInfoInputBox';
import { Template, UserInfoForm, ChangeButton } from './ UserInfo.style';
import { faInfoCircle } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
// import ChangeButton from './userInfoForm/ChangeButton';
import { useEffect, useState } from 'react';
// import NameModal from './userInfoForm/NameModal';
// import EmailModal from './userInfoForm/EmailModal';
// import PhoneModal from './userInfoForm/PhoneModal';
// import HomeModal from './userInfoForm/HomeModal';
import ProfileImg from './userInfoForm/ProfileImg';
import { Link } from 'react-router-dom';
import axios from 'axios';
const UserInfo = () => {
  // const [name, setName] = useState(false);
  // const [mail, setMail] = useState(false);
  // const [phone, setPhone] = useState(false);
  const [user, setUser] = useState({
    email: '',
    username: '',
    profileImg: '',
    phone: '',
  });
  // const [home, setHome] = useState(false);
  // const nameHandler = () => {
  //   setName(true);
  // };
  // const mailHandler = () => {
  //   setMail(true);
  // };
  // const phoneHandler = () => {
  //   setPhone(true);
  // };
  // const homeHandler = () => {
  //   setHome(true);
  // };

  const getUserData = async () => {
    const res = await axios.get('https://5a26-49-142-61-236.jp.ngrok.io/member/detail', {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
      },
    });
    setUser({
      email: res.email,
      username: res.username,
      profileImg: res.profileImg,
      phone: res.phone,
    });
    return res.data;
  };
  useEffect(() => {
    getUserData();
  }, []);
  return (
    <>
      <Template key={user.id}>
        <div className="userInfoHead">
          <FontAwesomeIcon className="userInfoIcon" icon={faInfoCircle} size="2x" />
          <h1>개인정보</h1>
        </div>
        <ProfileImg />
        <UserInfoForm>
          <div className="inputHead">
            <UserInfoInputBox
              labelName="실명"
              inputId="id"
              inputType="text"
              name="id"
              // onChangeInput={onChangeInput}
              text={user.username}
              value={user.username}
            />
            {/* <div className="changeButtonStyle">
              <ChangeButton onClick={nameHandler}>수정</ChangeButton>
              {name && <NameModal setName={setName} />}
            </div> */}
          </div>
        </UserInfoForm>
        <UserInfoForm>
          <div className="inputHead">
            <UserInfoInputBox
              labelName="이메일 주소"
              inputId="이메일"
              inputType="email"
              name="email"
              // onChangeInput={onChangeInput}
              text={user.email}
              value={user.email}
            />
            {/* <div className="changeButtonStyle">
              <ChangeButton onClick={mailHandler}>수정</ChangeButton>
              {mail && <EmailModal setMail={setMail} />}
            </div> */}
          </div>
        </UserInfoForm>
        <UserInfoForm>
          <div className="inputHead">
            <UserInfoInputBox
              labelName="전화번호"
              inputId="전화번호"
              inputType="tel"
              name="tel"
              // onChangeInput={onChangeInput}
              text={user.phone}
              value={user.phone}
            />
            {/* <div className="changeButtonStyle">
              <ChangeButton onClick={phoneHandler}>수정</ChangeButton>
              {phone && <PhoneModal setPhone={setPhone} />}
            </div> */}
          </div>
        </UserInfoForm>
        {/* <UserInfoForm>
          <div className="inputHead">
            <UserInfoInputBox
              labelName="주소"
              inputId="주소"
              inputType="url"
              name="url"
              // onChangeInput={onChangeInput}
              text="경상북도 구미시 상사서로 17 우방아파트 상모동 202동 1205호"
            />
            <div className="changeButtonStyle">
              <ChangeButton onClick={homeHandler}>수정</ChangeButton>
              {home && <HomeModal setHome={setHome} />}
            </div>
          </div>
        </UserInfoForm> */}
        <Link to={'/user/edit'}>
          <ChangeButton>수정하기</ChangeButton>
        </Link>
      </Template>
    </>
  );
};
export default UserInfo;
