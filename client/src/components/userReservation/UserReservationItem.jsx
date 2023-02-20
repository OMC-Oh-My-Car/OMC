// import { useState } from 'react';
import { UserReservationItemArea } from './UserReservationItem.style';
// import { faStar, faCircleRight, faCircleLeft } from '@fortawesome/free-solid-svg-icons';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
// import { ReactComponent as HeartRegular } from '../../../assets/images/heart-regular.svg';
// import { ReactComponent as HeartSolid } from '../../../assets/images/heart-solid.svg';
import { useNavigate } from 'react-router-dom';

const UserReservationItem = ({ item, openModalController }) => {
  console.log(item);
  console.log(item.status);
  const navigate = useNavigate();

  const modalController = (type, width, height, modal, id) => {
    navigate(`/user/123/reservation?reservation_id=${id}&modal=${modal}`);
    openModalController({ type, width, height });
  };
  const SetContent = () => {
    if (item.status === 'RESERVATION_COMPLETE') {
      return (
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
      );
    } else if (item.status === 'RESERVATION_CANCEL') {
      return (
        <button
          className="button buttonYellow"
          onClick={() =>
            modalController('reservationCancel', '600px', '800px', 'reservation_cancel_reason', item.reservationId)
          }
        >
          취소된 예약
        </button>
      );
    } else {
      if (item.hasReview) {
        return (
          <button
            className="button buttonYellow"
            onClick={() =>
              modalController('reservationReview', '600px', '800px', 'reservation_review', item.reservationId)
            }
          >
            리뷰 확인
          </button>
        );
      } else {
        return (
          <button
            className="button buttonYellow"
            onClick={() =>
              modalController('reservationReviewAdd', '600px', '800px', 'reservation_add_review', item.reservationId)
            }
          >
            리뷰 작성
          </button>
        );
      }
    }
  };
  return (
    <>
      <UserReservationItemArea>
        <div className="imageArea">
          <img src={item.thumbNail} alt="상품사진" />
        </div>
        <div className="productInfo">
          <span className="productTitle">{item.title}</span>
          <span className="reservationPeriod">
            {item.checkIn} - {item.checkOut}
          </span>
          <button
            className="button buttonRed"
            onClick={() => modalController('reservationInfo', '500px', '550px', 'reservation_info', item.reservationId)}
          >
            예약 정보
          </button>
          <SetContent />
        </div>
      </UserReservationItemArea>
    </>
  );
};

export default UserReservationItem;
