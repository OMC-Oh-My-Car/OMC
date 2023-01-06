// import React from 'react';
import { UserInfoArea } from './UserInfo.style';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars, faCircleUser } from '@fortawesome/free-solid-svg-icons';
const UserInfo = () => {
  return (
    <>
      <UserInfoArea>
        <FontAwesomeIcon className="hambergerIcon" icon={faBars} />
        <FontAwesomeIcon className="userIcon" icon={faCircleUser} />
      </UserInfoArea>
    </>
  );
};

export default UserInfo;
