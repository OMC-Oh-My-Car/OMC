// import React from "react";
import { useState, useRef } from 'react';
import { Form, ImgButton } from '../userInfoForm/ProfileImg.style';

const ProfileImg = () => {
  const [imgFile, setImgFile] = useState('');
  const imgRef = useRef();
  const saveImgFile = () => {
    const file = imgRef.current.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = () => {
      setImgFile(reader.result);
    };
  };
  return (
    <>
      <Form>
        <img
          className="imgtemplate"
          src={imgFile ? imgFile : '../../../assets/images/blank-profile-picture.png'}
          alt=""
        />
        <ImgButton>
          <label className="signup-profileImg-label" htmlFor="profileImg">
            프로필 이미지 추가
          </label>
        </ImgButton>
        <input
          className="signup-profileImg-input"
          type="file"
          accept="image/*"
          id="profileImg"
          onChange={saveImgFile}
          ref={imgRef}
        />
      </Form>
    </>
  );
};
export default ProfileImg;
