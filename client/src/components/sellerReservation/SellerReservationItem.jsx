// import React from 'react';
import { SellerReservationItemArea } from './SellerReservationItem.style';

const SellerReservationItem = ({ item, modalController }) => {
  console.log(item);
  const SetContent = () => {
    if (item.status === 'RESERVATION_COMPLETE') {
      return (
        <button
          onClick={() =>
            modalController(
              'reservationCancelAdd',
              '600px',
              '800px',
              'reservation_add_cancel_reason',
              item.reservationId,
            )
          }
          className="buttonRight"
        >
          예약 취소
        </button>
      );
    } else if (item.status === 'RESERVATION_CANCEL') {
      return (
        <button
          onClick={() =>
            modalController('reservationCancel', '600px', '800px', 'reservation_cancel_reason', item.reservationId)
          }
          className="buttonRight"
        >
          취소 사유
        </button>
      );
    } else {
      if (item.hasReview) {
        return (
          <button
            onClick={() =>
              modalController('reservationReview', '600px', '800px', 'reservation_review', item.reservationId)
            }
            className="buttonRight"
          >
            리뷰 조회
          </button>
        );
      } else {
        return (
          <button className="buttonRight" onClick={() => alert('등록된 리뷰가 없습니다')}>
            리뷰 작성
          </button>
        );
      }
    }
  };
  return (
    <>
      <SellerReservationItemArea>
        <div className="image">
          <img src={item.thumbNail} alt="상품사진" />
        </div>
        <div className="flexRight">
          <div className="reservationInfo">
            <span className="reservationNumber">예약 번호: {item.reservationCode}</span>
            <span className="reservationDate">
              예약 기간: {item.checkIn} ~ {item.checkOut}
            </span>
            <span className="userEmail">이메일: {item.email}</span>
          </div>
          <div className="button">
            <button
              onClick={() =>
                modalController('reservationInfo', '500px', '550px', 'reservation_info', item.reservationId)
              }
              className="buttonLeft"
            >
              예약 정보
            </button>
            <SetContent />
          </div>
        </div>
      </SellerReservationItemArea>
    </>
  );
};

export default SellerReservationItem;
