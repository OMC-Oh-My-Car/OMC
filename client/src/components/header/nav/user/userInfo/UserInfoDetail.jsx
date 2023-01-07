import { useEffect, useRef } from 'react';
import { UserInfoDetailArea } from './UserInfoDetail.style';

const UserInfoDetail = ({ toggleSide }) => {
  const outSide = useRef();

  useEffect(() => {
    document.addEventListener('mousedown', handlerOutside);
    return () => {
      document.removeEventListener('mousedown', handlerOutside);
    };
  });
  const handlerOutside = (e) => {
    if (!outSide.current.contains(e.target)) {
      toggleSide();
    }
  };

  return (
    <>
      <UserInfoDetailArea ref={outSide}>
        <ul className="menu">
          <li className="menuItem">회원 정보</li>
          <li className="menuItem">회원 정보 수정</li>
          <li className="menuItem">찜 목록</li>
        </ul>
        <ul className="menu">
          <li className="menuItem">로그아웃</li>
        </ul>
      </UserInfoDetailArea>
    </>
  );
};

export default UserInfoDetail;
