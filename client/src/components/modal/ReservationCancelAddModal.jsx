import { useState } from 'react';
import { ReservationCancelModalAddArea } from './ReservationCancelAddModal.style';
import { useMutation } from 'react-query';
import { cancelUserReservation } from '../../modules/userReservation/userReservation';

const ReservationCancelAddModal = () => {
  const params = new URLSearchParams(location.search);
  let reservationId = params.get('reservation_id');

  const [content, setContent] = useState('');
  // 컨텐츠 변경 함수
  const contentControl = (e) => {
    setContent(e.target.value);
  };
  // 예약 취소 함수
  const mutation = useMutation(() => cancelUserReservation(content, reservationId), {
    onMutate() {},
    onSuccess(data) {
      console.log(data);
    },
    onError(err) {
      console.log(err);
    },
  });
  return (
    <>
      <ReservationCancelModalAddArea>
        <h2>예약 취소하기</h2>
        <span className="explain">이러한 사유로 예약을 취소합니다.</span>
        <textarea value={content} onChange={contentControl} type="text" rows="10" cols="15" />
        <button onClick={() => mutation.mutate(content, reservationId)} className="more">
          제출하기
        </button>
      </ReservationCancelModalAddArea>
    </>
  );
};

export default ReservationCancelAddModal;
