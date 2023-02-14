// import { useState } from 'react';
import { UserReservationItemArea } from './UserReservationItem.style';
// import { faStar, faCircleRight, faCircleLeft } from '@fortawesome/free-solid-svg-icons';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
// import { ReactComponent as HeartRegular } from '../../../assets/images/heart-regular.svg';
// import { ReactComponent as HeartSolid } from '../../../assets/images/heart-solid.svg';
import { useNavigate } from 'react-router-dom';

const UserReservationItem = ({ item, openModalController }) => {
  console.log(item);
  const navigate = useNavigate();

  const modalController = (type, width, height, modal, id) => {
    navigate(`/user/123/reservation?reservation_id=${id}&modal=${modal}`);
    openModalController({ type, width, height });
  };
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
            onClick={() => modalController('reservationInfo', '500px', '550px', 'reservation_info', item.reservationId)}
          >
            예약 정보
          </button>
          <button
            className="button buttonYellow"
            onClick={() =>
              modalController(
                'reservationCancelAdd',
                '600px',
                '800px',
                'reservation_add_cancel_reason',
                item.reservationId,
              )
            }
          >
            예약 취소
          </button>
          {/* <button
            className="button buttonYellow"
            onClick={() => modalController('reservationCancel', '600px', '800px', 'reservation_cancel_reason', item.reservationId)}
          >
            취소된 예약
          </button>
          <button
            className="button buttonYellow"
            onClick={() => modalController('reservationReviewAdd', '600px', '800px', 'reservation_add_review', item.reservationId)}
          >
            리뷰 작성
          </button>
          <button
            className="button buttonYellow"
            onClick={() => modalController('reservationReview', '600px', '800px', 'reservation_review', item.reservationId)}
          >
            리뷰 확인
          </button> */}
        </div>
      </UserReservationItemArea>
    </>
  );
};

export default UserReservationItem;
