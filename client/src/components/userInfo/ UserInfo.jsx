// import React from 'react';
// import BlackButton from './userInfoForm/BlackButton';
import UserInfoInputBox from './userInfoForm/UserInfoInputBox';
import { Template, UserInfoForm, ChangeButton } from './ UserInfo.style';
import { faInfoCircle } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useEffect, useState } from 'react';
import ProfileImg from './userInfoForm/ProfileImg';
import { Link } from 'react-router-dom';
// import axios from 'axios';
import axiosInstance from '../../modules';

const UserInfo = () => {
  const [user, setUser] = useState({
    email: '',
    username: '',
    profileImg: '',
    phone: '',
  });
  // const [user, setUser] = useState([]);

  const getUserData = async () => {
    try {
      const res = await axiosInstance.get('/member/detail');
      return res.data;
    } catch (error) {
      console.log(localStorage.getItem('Authorization'));
      return error.response.data;
    }
  };

  useEffect(() => {
    // 상세 정보 받아와 data에 저장
    let userData = getUserData();
    userData.then((res) => {
      // null 값 처리 나중에 서버에서 빈문자열로 변경
      setUser({
        email: res.email,
        username: res.username,
        profileImg: res.profileImg,
        phone: res.phone,
      });
    });
  }, []);
  // const getUserData = async () => {
  //   const res = await axiosInstance.get('https://f5d1-49-142-61-236.jp.ngrok.io/member/detail');
  //   setUser(res.data);
  //   return res.data;
  // };
  // useEffect(() => {
  //   getUserData();
  // }, []);
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
          </div>
        </UserInfoForm>
        <Link to={'/member/modify'}>
          <ChangeButton>수정하기</ChangeButton>
        </Link>
      </Template>
    </>
  );
};
export default UserInfo;
