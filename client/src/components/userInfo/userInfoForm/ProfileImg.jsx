// import React from "react";
import { Form } from '../userInfoForm/ProfileImg.style';

const ProfileImg = ({ data }) => {
  return (
    <>
      <Form>
        {data ? (
          <img className="imgtemplate" src={data.data.profileImg} alt="profileImage" />
        ) : (
          <>
            <div className="imgtemplate" />
          </>
        )}
      </Form>
    </>
  );
};
export default ProfileImg;
