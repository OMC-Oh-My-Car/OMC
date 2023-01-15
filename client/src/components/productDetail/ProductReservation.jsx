// import React from 'react';
import { ProductReservationArea } from './ProductReservation.style';
import { faStar } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const ProductReservation = () => {
  return (
    <>
      <ProductReservationArea>
        <div className="flexBox">
          <div>
            <span className="price">₩ 77,500 /박</span>
          </div>
          <div className="prductDescription">
            <FontAwesomeIcon className="starIcon" icon={faStar} />
            <span>4.69 · </span>
            <span className="productInfoReviewCount">후기 32개</span>
          </div>
        </div>
        <div className="reservationDate">
          <div className="startDate">
            <span>체크인</span>
            <span className="checkDate">2023.02.09</span>
          </div>
          <div className="endDate">
            <span>체크아웃</span>
            <span className="checkDate">2023.02.10</span>
          </div>
        </div>
        <button className="reserveButton">예약하기</button>
        <div className="priceFlex">
          <span>₩77,500 x 2박</span>
          <span>₩155,000</span>
        </div>
        <div className="totalPriceFlex">
          <span>총 합계</span>
          <span>₩155,000</span>
        </div>
      </ProductReservationArea>
    </>
  );
};

export default ProductReservation;
