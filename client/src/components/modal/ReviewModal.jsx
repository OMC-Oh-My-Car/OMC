// import React from 'react';
import { ReviewModalArea, ProductReviewScoreArea, ProductReviewListArea } from './ReviewModal.style';
import { faStar, faUser } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { getProductReview } from '../../modules/userProduct/userProductReview';
import { useQuery } from 'react-query';
import { useLocation } from 'react-router-dom';

const ReviewModal = () => {
  const location = useLocation();
  const productId = location.pathname.split('/')[2];

  const { data } = useQuery(['productReview', productId], async () => {
    const data = await getProductReview(productId);
    return data;
  });

  return (
    <>
      <ReviewModalArea>
        <h2>
          <span>
            <FontAwesomeIcon className="starIcon" icon={faStar} />
            {data.data.productTotalStar.totalStarAvg} · 후기 {data.data.pageInfo.totalElements}개
          </span>
        </h2>
        <div className="reviewScoreList">
          <ProductReviewScoreArea reviewScore={data.data.productTotalStar.starCleanlinessAvg}>
            <div className="reviewFlexBox margin">
              <div className="reviewText">청결도</div>
              <div className="reviewScore">
                <div className="scoreBarBackground">
                  <div className="scoreBar" />
                </div>
                <span>{data.data.productTotalStar.starCleanlinessAvg}</span>
              </div>
            </div>
          </ProductReviewScoreArea>
          <ProductReviewScoreArea reviewScore={data.data.productTotalStar.starAccuracyAvg}>
            <div className="reviewFlexBox">
              <div className="reviewText">정확성</div>
              <div className="reviewScore">
                <div className="scoreBarBackground">
                  <div className="scoreBar" />
                </div>
                <span>{data.data.productTotalStar.starAccuracyAvg}</span>
              </div>
            </div>
          </ProductReviewScoreArea>
          <ProductReviewScoreArea reviewScore={data.data.productTotalStar.starLocationAvg}>
            <div className="reviewFlexBox margin">
              <div className="reviewText">위치</div>
              <div className="reviewScore">
                <div className="scoreBarBackground">
                  <div className="scoreBar" />
                </div>
                <span>{data.data.productTotalStar.starLocationAvg}</span>
              </div>
            </div>
          </ProductReviewScoreArea>
          <ProductReviewScoreArea reviewScore={data.data.productTotalStar.starCostEffectiveAvg}>
            <div className="reviewFlexBox">
              <div className="reviewText">가격 대비 만족도</div>
              <div className="reviewScore">
                <div className="scoreBarBackground">
                  <div className="scoreBar" />
                </div>
                <span>{data.data.productTotalStar.starCostEffectiveAvg}</span>
              </div>
            </div>
          </ProductReviewScoreArea>
        </div>
        <ProductReviewListArea>
          {data &&
            data.data.data.map((el, index) => {
              return (
                <>
                  <div key={index} className="reviewListItem">
                    <div className="user">
                      <div className="userIconBackground">
                        <FontAwesomeIcon className="userIcon" icon={faUser} />
                      </div>
                      <div className="userInfo">
                        <div className="username">{el.nickname}</div>
                        <div className="createAt">{el.createTime}</div>
                      </div>
                    </div>
                    <span className="review">{el.content}</span>
                  </div>
                </>
              );
            })}
        </ProductReviewListArea>
      </ReviewModalArea>
    </>
  );
};

export default ReviewModal;
