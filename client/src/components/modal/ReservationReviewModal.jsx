import { ReservationReviewModalArea } from './ReservationReviewModal.style';
import { faStar } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useQuery } from 'react-query';
import { getUserReview } from '../../modules/userReview/userReview';
import { useState } from 'react';

const ReservationReviewModal = () => {
  const params = new URLSearchParams(location.search);
  let reservationId = params.get('reservation_id');
  const array = [1, 2, 3, 4, 5];

  const [totalStar, setTotalStar] = useState(5);
  const [starCleanliness, setStarCleanliness] = useState(5);
  const [starAccuracy, setStarAccuracy] = useState(5);
  const [starLocation, setStarLocation] = useState(5);
  const [starCostEffective, setStarCostEffective] = useState(5);

  const onSuccess = (data) => {
    setTotalStar(data.data.data.totalStar);
    setStarCleanliness(data.data.data.starCleanliness);
    setStarAccuracy(data.data.data.starAccuracy);
    setStarLocation(data.data.data.starLocation);
    setStarCostEffective(data.data.data.starCostEffective);
  };

  const { data } = useQuery(
    ['reservationReview', reservationId],
    async () => {
      const data = await getUserReview(reservationId);
      return data;
    },
    {
      onSuccess,
    },
  );

  return (
    <>
      <ReservationReviewModalArea>
        <h2>작성 리뷰</h2>
        <span className="review">{data && data.data.data.content}</span>
        <span className="gradeText">전체적 만족도</span>
        <span className="explain">이번 여행은 얼마나 만족스러우셨나요?</span>
        {array.map((el, index) => {
          return <FontAwesomeIcon key={index} className={`starIcon ${totalStar >= el && 'active'}`} icon={faStar} />;
        })}
        <span className="gradeText">청결도</span>
        <span className="explain">장소가 회원님이 예상한 것만큼 깨끗했나요?</span>
        {array.map((el, index) => {
          return (
            <FontAwesomeIcon key={index} className={`starIcon ${starCleanliness >= el && 'active'}`} icon={faStar} />
          );
        })}
        <span className="gradeText">정확성</span>
        <span className="explain">상품 사진과 설명이 실제와 얼마나 일치하나요?</span>
        {array.map((el, index) => {
          return <FontAwesomeIcon key={index} className={`starIcon ${starAccuracy >= el && 'active'}`} icon={faStar} />;
        })}
        <span className="gradeText">위치</span>
        <span className="explain">장소가 접근하기 쉬운 위치에 있었나요?</span>
        {array.map((el, index) => {
          return <FontAwesomeIcon key={index} className={`starIcon ${starLocation >= el && 'active'}`} icon={faStar} />;
        })}
        <span className="gradeText">가격 대비 만족도</span>
        <span className="explain">가격 대비 상품이 만족스러우셨나요?</span>
        {array.map((el, index) => {
          return (
            <FontAwesomeIcon key={index} className={`starIcon ${starCostEffective >= el && 'active'}`} icon={faStar} />
          );
        })}
      </ReservationReviewModalArea>
    </>
  );
};

export default ReservationReviewModal;
