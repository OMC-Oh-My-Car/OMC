import { useState } from 'react';
import { ReservationReviewAddModalArea } from './ReservationReviewAddModal.style';
import { faStar } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
const ReservationReviewAddModal = () => {
  const array = [1, 2, 3, 4, 5];
  const [totalGrade, setTotalGrade] = useState(5);
  const [cleanGrade, setCleanGrade] = useState(5);
  const [accuracyGrade, setAccuracyGrade] = useState(5);
  const [locationGrade, setLocationGrade] = useState(5);
  const [satisfactionWithPrice, setSatisfactionWithPrice] = useState(5);
  return (
    <>
      <ReservationReviewAddModalArea>
        <h2>리뷰 작성</h2>
        <span className="reviewText">이번 여행에 대한 후기를 작성하세요</span>
        <textarea type="text" rows="10" cols="15" />
        <div className="count">
          <span>남은 글자 수 : 300글자</span>
        </div>
        <span className="reviewText">여행이 어땠나요?</span>

        <span className="gradeText">전체적 만족도</span>
        <span className="explain">이번 여행의 전체적인 평점을 입력해주세요.</span>
        {array.map((el, index) => {
          return (
            <FontAwesomeIcon
              onClick={() => setTotalGrade(el)}
              key={index}
              className={`starIcon ${totalGrade >= el && 'active'}`}
              icon={faStar}
            />
          );
        })}
        <span className="gradeText">청결도</span>
        <span className="explain">장소가 회원님이 예상한 것만큼 깨끗했나요?</span>
        {array.map((el, index) => {
          return (
            <FontAwesomeIcon
              onClick={() => setCleanGrade(el)}
              key={index}
              className={`starIcon ${cleanGrade >= el && 'active'}`}
              icon={faStar}
            />
          );
        })}
        <span className="gradeText">정확성</span>
        <span className="explain">상품 사진과 설명이 실제와 얼마나 일치하나요?</span>
        {array.map((el, index) => {
          return (
            <FontAwesomeIcon
              onClick={() => setAccuracyGrade(el)}
              key={index}
              className={`starIcon ${accuracyGrade >= el && 'active'}`}
              icon={faStar}
            />
          );
        })}
        <span className="gradeText">위치</span>
        <span className="explain">장소가 접근하기 쉬운 위치에 있었나요?</span>
        {array.map((el, index) => {
          return (
            <FontAwesomeIcon
              onClick={() => setLocationGrade(el)}
              key={index}
              className={`starIcon ${locationGrade >= el && 'active'}`}
              icon={faStar}
            />
          );
        })}
        <span className="gradeText">가격 대비 만족도</span>
        <span className="explain">가격 대비 상품이 만족스러우셨나요?</span>
        {array.map((el, index) => {
          return (
            <FontAwesomeIcon
              onClick={() => setSatisfactionWithPrice(el)}
              key={index}
              className={`starIcon ${satisfactionWithPrice >= el && 'active'}`}
              icon={faStar}
            />
          );
        })}
      </ReservationReviewAddModalArea>
    </>
  );
};

export default ReservationReviewAddModal;
