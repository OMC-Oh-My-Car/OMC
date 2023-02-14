import { useState } from 'react';
import { ProductReservationArea } from './ProductReservation.style';
import { faStar } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import ProductCalendarModal from './ProductCalendarModal';

const ProductReservation = ({ data, reviewData }) => {
  const [calendarOpen, setCalendarOpen] = useState(false);
  const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate] = useState(new Date());

  const onChangeDate = (dates) => {
    const [start, end] = dates;
    console.log(new Date());
    console.log(start);

    setStartDate(start);
    setEndDate(end);
  };

  function leftPad(value) {
    if (value >= 10) {
      return value;
    }

    return `0${value}`;
  }
  function toStringByFormatting(source, delimiter = '-', type = 0) {
    if (source === null) return;
    if (type === 0) {
      const year = source.getFullYear();
      const month = leftPad(source.getMonth() + 1);
      const day = leftPad(source.getDate());

      return [year, month, day].join(delimiter);
    } else {
      const year = source.getFullYear() + '년';
      const month = leftPad(source.getMonth() + 1) + '월';
      const day = leftPad(source.getDate()) + '일';

      return [year, month, day].join(' ');
    }
  }

  return (
    <>
      <ProductReservationArea>
        <div className="flexBox">
          <div>
            <span className="price">₩ {data.data.data.price} /박</span>
          </div>
          <div className="prductDescription">
            <FontAwesomeIcon className="starIcon" icon={faStar} />
            <span>{data.data.data.star} · </span>
            <span className="productInfoReviewCount">후기 {reviewData.data.pageInfo.totalElements}개</span>
          </div>
        </div>
        <div className="reservationDate">
          <div className="startDate" role="presentation" onClick={() => setCalendarOpen(true)}>
            <span>체크인</span>
            <span className="checkDate">{toStringByFormatting(startDate)}</span>
          </div>
          <div className="endDate" role="presentation" onClick={() => setCalendarOpen(true)}>
            <span>체크아웃</span>
            <span className="checkDate">{toStringByFormatting(endDate)}</span>
          </div>
          {calendarOpen && (
            <>
              <ProductCalendarModal
                setCalendarOpen={setCalendarOpen}
                startDate={startDate}
                endDate={endDate}
                onChangeDate={onChangeDate}
                toStringByFormatting={toStringByFormatting}
              />
            </>
          )}
        </div>
        <button className="reserveButton">예약하기</button>

        {endDate !== null && !startDate !== null ? (
          <>
            <div className="priceFlex">
              <span>₩ {data.data.data.price + ' x ' + (endDate.getDate() - startDate.getDate()) + '박'}</span>
              <span>₩ {data.data.data.price * (endDate.getDate() - startDate.getDate())}</span>
            </div>
            <div className="totalPriceFlex">
              <span>총 합계</span>
              <span>₩ {data.data.data.price * (endDate.getDate() - startDate.getDate())}</span>
            </div>
          </>
        ) : (
          <></>
        )}
      </ProductReservationArea>
    </>
  );
};

export default ProductReservation;
