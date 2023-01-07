import { useState } from 'react';
import { UserInfoArea } from './UserInfo.style';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars, faCircleUser } from '@fortawesome/free-solid-svg-icons';
import UserInfoDetail from './UserInfoDetail';

const UserInfo = () => {
  // 나중에 리덕스로 이동
  const [isOpen, setIsOpen] = useState(false);
  const navController = () => {
    setIsOpen(true);
  };

  const toggleSide = () => {
    setIsOpen(false);
  };

  return (
    <>
      <UserInfoArea onClick={navController} isOpen={isOpen}>
        <FontAwesomeIcon className="hambergerIcon" icon={faBars} />
        <FontAwesomeIcon className="userIcon" icon={faCircleUser} />
      </UserInfoArea>
      {isOpen && (
        <>
          <UserInfoDetail toggleSide={toggleSide} />
        </>
      )}
    </>
  );
};

export default UserInfo;
