// import { useState } from 'react';
import { UserReservationItemArea } from './UserReservationItem.style';
// import { faStar, faCircleRight, faCircleLeft } from '@fortawesome/free-solid-svg-icons';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
// import { ReactComponent as HeartRegular } from '../../../assets/images/heart-regular.svg';
// import { ReactComponent as HeartSolid } from '../../../assets/images/heart-solid.svg';

const UserReservationItem = () => {
  return (
    <>
      <UserReservationItemArea>
        <div className="imageArea">
          <img src="https://cdn.thescoop.co.kr/news/photo/202107/51410_73168_3149.jpg" alt="상품사진" />
        </div>
        <div className="productInfo">
          <span className="productTitle">전통한옥 단독주택</span>
          <span className="reservationPeriod">2023년 1월 13일 - 2023년 1월 14일</span>
          <button className="button buttonRed">예약 정보</button>
          <button className="button buttonYellow">예약 취소</button>
        </div>
      </UserReservationItemArea>
    </>
  );
};

export default UserReservationItem;
