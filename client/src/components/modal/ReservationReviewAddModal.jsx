import { useState } from 'react';
import { ReservationReviewAddModalArea } from './ReservationReviewAddModal.style';
import { faStar } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useMutation } from 'react-query';
import { addUserReview } from '../../modules/userReview/userReview';

const ReservationReviewAddModal = () => {
  const params = new URLSearchParams(location.search);
  let reservationId = params.get('reservation_id');
  console.log(reservationId);

  const array = [1, 2, 3, 4, 5];

  const [content, setContent] = useState('');
  const [totalStar, setTotalStar] = useState(5);
  const [starCleanliness, setStarCleanliness] = useState(5);
  const [starAccuracy, setStarAccuracy] = useState(5);
  const [starLocation, setStarLocation] = useState(5);
  const [starCostEffective, setStarCostEffective] = useState(5);

  const mutation = useMutation(
    () =>
      addUserReview(
        { content, totalStar, starCleanliness, starAccuracy, starLocation, starCostEffective },
        reservationId,
      ),
    {
      onMutate() {},
      onSuccess(data) {
        console.log(data);
      },
      onError(err) {
        console.log(err);
      },
    },
  );

  return (
    <>
      <ReservationReviewAddModalArea>
        <h2>리뷰 작성</h2>
        <span className="reviewText">이번 여행에 대한 후기를 작성하세요</span>
        <textarea value={content} onChange={(e) => setContent(e.target.value)} type="text" rows="10" cols="15" />
        <div className="count">
          <span>남은 글자 수 : 300글자</span>
        </div>
        <span className="reviewText">여행이 어땠나요?</span>
        <span className="gradeText">전체적 만족도</span>
        <span className="explain">이번 여행은 얼마나 만족스러우셨나요?</span>
        {array.map((el, index) => {
          return (
            <FontAwesomeIcon
              onClick={() => setTotalStar(el)}
              key={index}
              className={`starIcon ${totalStar >= el && 'active'}`}
              icon={faStar}
            />
          );
        })}
        <span className="gradeText">청결도</span>
        <span className="explain">장소가 회원님이 예상한 것만큼 깨끗했나요?</span>
        {array.map((el, index) => {
          return (
            <FontAwesomeIcon
              onClick={() => setStarCleanliness(el)}
              key={index}
              className={`starIcon ${starCleanliness >= el && 'active'}`}
              icon={faStar}
            />
          );
        })}
        <span className="gradeText">정확성</span>
        <span className="explain">상품 사진과 설명이 실제와 얼마나 일치하나요?</span>
        {array.map((el, index) => {
          return (
            <FontAwesomeIcon
              onClick={() => setStarAccuracy(el)}
              key={index}
              className={`starIcon ${starAccuracy >= el && 'active'}`}
              icon={faStar}
            />
          );
        })}
        <span className="gradeText">위치</span>
        <span className="explain">장소가 접근하기 쉬운 위치에 있었나요?</span>
        {array.map((el, index) => {
          return (
            <FontAwesomeIcon
              onClick={() => setStarLocation(el)}
              key={index}
              className={`starIcon ${starLocation >= el && 'active'}`}
              icon={faStar}
            />
          );
        })}
        <span className="gradeText">가격 대비 만족도</span>
        <span className="explain">가격 대비 상품이 만족스러우셨나요?</span>
        {array.map((el, index) => {
          return (
            <FontAwesomeIcon
              onClick={() => setStarCostEffective(el)}
              key={index}
              className={`starIcon ${starCostEffective >= el && 'active'}`}
              icon={faStar}
            />
          );
        })}
        <div className="btnArea">
          <button onClick={() => mutation.mutate()} className="more">
            제출하기
          </button>
        </div>
      </ReservationReviewAddModalArea>
    </>
  );
};

export default ReservationReviewAddModal;
