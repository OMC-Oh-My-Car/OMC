import { ProductCalendarModalArea } from './ProductCalendarModal.style';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { ko } from 'date-fns/esm/locale';

const ProductCalendarModal = ({ setCalendarOpen, startDate, endDate, onChangeDate, toStringByFormatting }) => {
  return (
    <>
      <ProductCalendarModalArea>
        <h2>
          {endDate !== null && !startDate !== null ? endDate.getDate() - startDate.getDate() + '박' : '날짜 선택'}
        </h2>
        <span>
          {toStringByFormatting(startDate, null, 1)} - {toStringByFormatting(endDate, null, 1)}
        </span>
        <div className="calendar">
          <DatePicker
            locale={ko}
            selected={startDate}
            minDate={new Date()}
            onChange={onChangeDate}
            startDate={startDate}
            endDate={endDate}
            selectsRange
            inline
          />
        </div>
        <button className="close" onClick={() => setCalendarOpen(false)}>
          닫기
        </button>
      </ProductCalendarModalArea>
    </>
  );
};

export default ProductCalendarModal;
