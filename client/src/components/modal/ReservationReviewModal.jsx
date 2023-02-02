import { ReservationReviewModalArea } from './ReservationReviewModal.style';
import { faStar } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const ReservationReviewModal = () => {
  const array = [1, 2, 3, 4, 5];
  const totalGrade = 1;
  const cleanGrade = 2;
  const accuracyGrade = 3;
  const locationGrade = 4;
  const satisfactionWithPrice = 5;
  return (
    <>
      <ReservationReviewModalArea>
        <h2>작성 리뷰</h2>
        <span className="review">
          크리스마스 기념으로 예약했는데 들어가자마자 너무 행복했습니다🥰 창문으로 보이는 바다도 구름도 너무 예뻤고
          방안의 가구 소품들 모두 너무 완벽했습니다!! 청소도 정말 잘크리스마스 기념으로 예약했는데 들어가자마자 너무
          행복했습니다🥰 창문으로 보이는 바다도 구름도 너무 예뻤고 방안의 가구 소품들 모두 너무 완벽했습니다!!크리스마스
          기념으로 예약했는데 들어가자마자 너무 행복했습니다🥰 창문으로 보이는 바다도 구름도 너무 예뻤고 방안의 가구
          소품들 모두 너무 완벽했습니다!! 청소도 정말 잘 청소도 정말 잘
        </span>
        <span className="gradeText">전체적 만족도</span>
        <span className="explain">이번 여행은 얼마나 만족스러우셨나요?</span>
        {array.map((el, index) => {
          return <FontAwesomeIcon key={index} className={`starIcon ${totalGrade >= el && 'active'}`} icon={faStar} />;
        })}
        <span className="gradeText">청결도</span>
        <span className="explain">장소가 회원님이 예상한 것만큼 깨끗했나요?</span>
        {array.map((el, index) => {
          return <FontAwesomeIcon key={index} className={`starIcon ${cleanGrade >= el && 'active'}`} icon={faStar} />;
        })}
        <span className="gradeText">정확성</span>
        <span className="explain">상품 사진과 설명이 실제와 얼마나 일치하나요?</span>
        {array.map((el, index) => {
          return (
            <FontAwesomeIcon key={index} className={`starIcon ${accuracyGrade >= el && 'active'}`} icon={faStar} />
          );
        })}
        <span className="gradeText">위치</span>
        <span className="explain">장소가 접근하기 쉬운 위치에 있었나요?</span>
        {array.map((el, index) => {
          return (
            <FontAwesomeIcon key={index} className={`starIcon ${locationGrade >= el && 'active'}`} icon={faStar} />
          );
        })}
        <span className="gradeText">가격 대비 만족도</span>
        <span className="explain">가격 대비 상품이 만족스러우셨나요?</span>
        {array.map((el, index) => {
          return (
            <FontAwesomeIcon
              key={index}
              className={`starIcon ${satisfactionWithPrice >= el && 'active'}`}
              icon={faStar}
            />
          );
        })}
      </ReservationReviewModalArea>
    </>
  );
};

export default ReservationReviewModal;
