// import React from 'react';
import { SellerReservationItemArea } from './SellerReservationItem.style';

const SellerReservationItem = ({ item, modalController }) => {
  return (
    <>
      <SellerReservationItemArea>
        <div className="image">
          <img src="https://cdn.thescoop.co.kr/news/photo/202107/51410_73168_3149.jpg" alt="상품사진" />
        </div>
        <div className="flexRight">
          <div className="reservationInfo">
            <span className="reservationNumber">예약 번호: 2022-1220-0874</span>
            <span className="reservationDate">예약 기간: 2023년 1월 12일 ~ 2023년 1월 13일</span>
            <span className="userEmail">이메일: shinker1002@naver.com</span>
          </div>
          <div className="button">
            <button
              onClick={() => modalController('reservationInfo', '500px', '550px', 'reservation_info', '2022-1222-0001')}
              className="buttonLeft"
            >
              예약 정보
            </button>
            {/* <button
              onClick={() =>
                modalController('reservationCancel', '600px', '800px', 'reservation_cancel_reason', '2022-1222-0001')
              }
              className="buttonRight"
            >
              취소 사유
            </button> */}
            {/* <button
              onClick={() =>
                modalController('reservationReview', '600px', '800px', 'reservation_review', '2022-1222-0001')
              }
              className="buttonRight"
            >
              리뷰 조회
            </button> */}
            <button
              onClick={() =>
                modalController('reservationReviewAdd', '600px', '800px', 'reservation_add_review', '2022-1222-0001')
              }
              className="buttonRight"
            >
              예약 취소
            </button>
          </div>
        </div>
      </SellerReservationItemArea>
    </>
  );
};

export default SellerReservationItem;
