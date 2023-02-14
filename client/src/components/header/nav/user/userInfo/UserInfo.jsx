import { useState } from 'react';
import { UserInfoArea } from './UserInfo.style';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars, faCircleUser } from '@fortawesome/free-solid-svg-icons';
import UserInfoDetail from './UserInfoDetail';
import { useSelector } from 'react-redux';

const UserInfo = () => {
  const imageUrl = useSelector((state) => state.user.imageUrl);

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
        {imageUrl ? (
          <>
            <img src={imageUrl} alt="profileImage" />
          </>
        ) : (
          <>
            <FontAwesomeIcon className="userIcon" icon={faCircleUser} />
          </>
        )}
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
