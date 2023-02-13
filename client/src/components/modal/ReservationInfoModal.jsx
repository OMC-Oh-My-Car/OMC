import { ReservationInfoModalArea } from './ReservationInfoModal.style';
import { useQuery } from 'react-query';
import { getUserReservationDetail } from '../../modules/userReservation/userReservation';

const ReservationInfoModal = () => {
  const params = new URLSearchParams(location.search);
  let reservationId = params.get('reservation_id');

  if (reservationId) {
    const { data } = useQuery(['reservationInfo', reservationId], async () => {
      const data = await getUserReservationDetail(reservationId);
      return data;
    });
  }
  return (
    <>
      <ReservationInfoModalArea>
        <h2>예약정보</h2>
        <div className="reservationInfo">
          <div className="reservationNumberArea">
            <span className="label">예약 번호</span>
            <span className="reservationNumber">2023-0201-0001</span>
          </div>
          <div className="reservationCheckInArea">
            <span className="label">체크인</span>
            <span className="reservationCheckIn">2023-02-01</span>
          </div>
          <div className="reservationCheckOutArea">
            <span className="label">체크아웃</span>
            <span className="reservationCheckOut">2023-02-02</span>
          </div>
          <div className="reservationUserNameArea">
            <span className="label">예약자명</span>
            <span className="reservationName">최민수</span>
          </div>
          <div className="reservationUserEmailArea">
            <span className="label">이메일</span>
            <span className="reservationEmail">shinker1002@naver.com</span>
          </div>
          <button className="more">상품 페이지 이동</button>
        </div>
      </ReservationInfoModalArea>
    </>
  );
};

export default ReservationInfoModal;
