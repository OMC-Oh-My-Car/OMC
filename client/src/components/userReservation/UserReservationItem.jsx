// import { useState } from 'react';
import { UserReservationItemArea } from './UserReservationItem.style';
// import { faStar, faCircleRight, faCircleLeft } from '@fortawesome/free-solid-svg-icons';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
// import { ReactComponent as HeartRegular } from '../../../assets/images/heart-regular.svg';
// import { ReactComponent as HeartSolid } from '../../../assets/images/heart-solid.svg';

const UserReservationItem = ({ openModalController }) => {
  return (
    <>
      <UserReservationItemArea>
        <div className="imageArea">
          <img src="https://cdn.thescoop.co.kr/news/photo/202107/51410_73168_3149.jpg" alt="상품사진" />
        </div>
        <div className="productInfo">
          <span className="productTitle">전통한옥 단독주택</span>
          <span className="reservationPeriod">2023년 1월 13일 - 2023년 1월 14일</span>
          <button
            className="button buttonRed"
            onClick={() => openModalController({ type: 'reservationInfo', width: '500px', height: '550px' })}
          >
            예약 정보
          </button>
          <button
            className="button buttonYellow"
            onClick={() => openModalController({ type: 'reservationCancel', width: '600px', height: '800px' })}
          >
            취소된 예약
          </button>
          {/* <button
            className="button buttonYellow"
            onClick={() => openModalController({ type: 'reservationReviewAdd', width: '600px', height: '800px' })}
            >
            리뷰 작성
          </button> */}
          {/* <button
            className="button buttonYellow"
            onClick={() => openModalController({ type: 'reservationReview', width: '600px', height: '800px' })}
          >
            리뷰 확인
          </button> */}
        </div>
      </UserReservationItemArea>
    </>
  );
};

export default UserReservationItem;
