import { ReservationInfoModalArea } from './ReservationInfoModal.style';
import { useQuery } from 'react-query';
import { getUserReservationDetail } from '../../modules/userReservation/userReservation';

const ReservationInfoModal = () => {
  const params = new URLSearchParams(location.search);
  let reservationId = params.get('reservation_id');

  const { data } = useQuery(['reservationInfo', reservationId], async () => {
    const data = await getUserReservationDetail(reservationId);
    return data;
  });
  console.log(data);
  return (
    <>
      <ReservationInfoModalArea>
        <h2>예약정보</h2>
        <div className="reservationInfo">
          <div className="reservationNumberArea">
            <span className="label">예약 번호</span>
            <span className="reservationNumber">{data && data.data.data.reservationCode}</span>
          </div>
          <div className="reservationCheckInArea">
            <span className="label">체크인</span>
            <span className="reservationCheckIn">{data && data.data.data.checkIn.split(' ')[0]}</span>
            <span className="reservationCheckIn">{data && data.data.data.checkIn.split(' ')[1]}</span>
          </div>
          <div className="reservationCheckOutArea">
            <span className="label">체크아웃</span>
            <span className="reservationCheckOut">{data && data.data.data.checkOut.split(' ')[0]}</span>
            <span className="reservationCheckOut">{data && data.data.data.checkOut.split(' ')[1]}</span>
          </div>
          <div className="reservationUserNameArea">
            <span className="label">예약자명</span>
            <span className="reservationName">{data && data.data.data.name}</span>
          </div>
          <div className="reservationUserEmailArea">
            <span className="label">이메일</span>
            <span className="reservationEmail">{data && data.data.data.email}</span>
          </div>
          <button className="more">상품 페이지 이동</button>
        </div>
      </ReservationInfoModalArea>
    </>
  );
};

export default ReservationInfoModal;
