// import React from 'react';
// import BlackButton from './userInfoForm/BlackButton';
import UserInfoInputBox from './UserInfoInputBox';
import { Template, UserInfoForm, ChangeButton } from './UserInfoEdit.style';
import { faInfoCircle } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useEffect, useState } from 'react';
import ProfileImg from './ProfileImg';
import axios from 'axios';
const UserInfoEdit = () => {
  let reg_name1 = /^[가-힣]+$/;
  const [user, setUser] = useState({
    email: '',
    username: '',
    profileImg: '',
    phone: '',
  });
  const getUserData = async () => {
    const res = await axios.get('/member/detail');
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
  const editUserInfo = async (data) => {
    let check = window.confirm('회원 정보를 수정하시겠습니까?');
    if (check) {
      if (data.username.length < 2 || data.username.length > 16) {
        window.alert('2자~16자 사이의 닉네임을 입력해주세요');
        return;
      }
      if (!reg_name1.test(data.username)) {
        window.alert('올바른 이름을 입력해주세요');
        return;
      }
      try {
        let temp = {
          email: data.email,
          username: data.username,
          profileImg: data.profileImg,
          phone: data.phone,
        };
        const res = await axios.patch('/member/modify', temp);
        if (res.status === 200) {
          window.location.replace('/userinfo');
        }
        window.alert('회원 정보 수정 완료.');
      } catch (error) {
        return error.response.data;
      }
    }
  };
  const onChangeInput = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };
  return (
    <>
      <Template key={user.id}>
        <div className="userInfoHead">
          <FontAwesomeIcon className="userInfoIcon" icon={faInfoCircle} size="2x" />
          <h1>개인정보 수정</h1>
        </div>
        <ProfileImg />
        <UserInfoForm>
          <div className="inputHead">
            <UserInfoInputBox
              labelName="실명"
              inputId="id"
              inputType="text"
              name="id"
              text={user.username}
              value={user.username}
              onChange={onChangeInput}
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
              text={user.email}
              value={user.email}
              onChange={onChangeInput}
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
              onChange={onChangeInput}
            />
          </div>
        </UserInfoForm>
        <ChangeButton formSubmit={() => editUserInfo({ ...user })}>수정완료</ChangeButton>
      </Template>
    </>
  );
};
export default UserInfoEdit;
