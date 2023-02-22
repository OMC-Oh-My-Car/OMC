import { Form } from './ProfileImg.style';
import { useState } from 'react';

const ProfileImg = ({ data, handleAddImages, showImages }) => {
  return (
    <>
      <Form>
        {data ? (
          <>
            <img
              className="imgtemplate"
              src={showImages.length === 0 ? data.data.profileImg : showImages[0]}
              alt="profileImage"
            />
            <label className="inputLabel" htmlFor="profileImg">
              프로필 이미지 추가
            </label>
            <input onChange={handleAddImages} id="profileImg" className="imageInput" type="file" accept="image/*" />
          </>
        ) : (
          <>
            <div className="imgtemplate" />
            <label className="inputLabel" htmlFor="profileImg">
              프로필 이미지 추가
            </label>
          </>
        )}
      </Form>
    </>
  );
};
export default ProfileImg;
