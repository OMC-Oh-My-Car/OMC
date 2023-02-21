import { useEffect, useRef } from 'react';
import { UserInfoDetailArea } from './UserInfoDetail.style';
import { useNavigate } from 'react-router-dom';
import { logout } from '../../../../../modules/member/logout';
import { clearUserInfo } from '../../../../../redux/slice/UserInfo';
import { useDispatch } from 'react-redux';

const UserInfoDetail = ({ toggleSide }) => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const outSide = useRef();

  useEffect(() => {
    document.addEventListener('mousedown', handlerOutside);
    return () => {
      document.removeEventListener('mousedown', handlerOutside);
    };
  });

  const handleLogout = async () => {
    logout()
      .then((res) => {
        console.log('로그아웃되었습니다.');
        window.sessionStorage.removeItem('Authorization');
        dispatch(clearUserInfo());
        navigate('/');
      })
      .catch((err) => {
        console.log(`${err.response.status} 에러`);
        console.log('로그아웃 실패!');
      });
  };

  const handlerOutside = (e) => {
    if (!outSide.current.contains(e.target)) {
      toggleSide();
    }
  };

  return (
    <>
      <UserInfoDetailArea ref={outSide}>
        <ul className="menu">
          <li role="presentation" onClick={() => navigate('/user/:id/userInfo')} className="menuItem">
            회원 정보
          </li>
          <li role="presentation" onClick={() => navigate('/user/:id/userInfo/edit')} className="menuItem">
            회원 정보 수정
          </li>
          {/* <li className="menuItem">찜 목록</li> */}
          <li role="presentation" onClick={() => navigate('/user/12/reservation')} className="menuItem">
            예약 목록
          </li>
        </ul>
        <ul className="menu">
          <li role="presentation" onClick={handleLogout} className="menuItem">
            로그아웃
          </li>
        </ul>
      </UserInfoDetailArea>
    </>
  );
};

export default UserInfoDetail;
