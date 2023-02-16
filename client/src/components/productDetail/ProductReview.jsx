// import React from 'react';
import { ProductReviewArea, ProductReviewScoreArea, ProductReviewListArea } from './ProductReview.style';
import { faStar, faUser } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const ProductReview = ({ data, reviewData, modalController }) => {
  if (reviewData) {
    console.log(reviewData.data.data);
  }
  return (
    <>
      {data && reviewData ? (
        <>
          <ProductReviewArea>
            <h2>
              <span>
                <FontAwesomeIcon className="starIcon" icon={faStar} />
                {!reviewData ? reviewData.data.productTotalStar.totalStarAvg : 0} · 후기{' '}
                {!reviewData ? reviewData.data.pageInfo.totalElements : 0}개
              </span>
            </h2>
            <div className="reviewScoreList">
              <ProductReviewScoreArea reviewScore={!reviewData ? reviewData.data.productTotalStar.starAccuracyAvg : 0}>
                <div className="reviewFlexBox">
                  <div className="reviewText">정확도</div>
                  <div className="reviewScore">
                    <div className="scoreBarBackground">
                      <div className="scoreBar" />
                    </div>
                    <span>{!reviewData ? reviewData.data.productTotalStar.starAccuracyAvg : 0}</span>
                  </div>
                </div>
              </ProductReviewScoreArea>
              <ProductReviewScoreArea
                reviewScore={!reviewData ? reviewData.data.productTotalStar.starCleanlinessAvg : 0}
              >
                <div className="reviewFlexBox">
                  <div className="reviewText">청결도</div>
                  <div className="reviewScore">
                    <div className="scoreBarBackground">
                      <div className="scoreBar" />
                    </div>
                    <span>{!reviewData ? reviewData.data.productTotalStar.starCleanlinessAvg : 0}</span>
                  </div>
                </div>
              </ProductReviewScoreArea>
              <ProductReviewScoreArea
                reviewScore={!reviewData ? reviewData.data.productTotalStar.starCostEffectiveAvg : 0}
              >
                <div className="reviewFlexBox">
                  <div className="reviewText">가격 대비 만족도</div>
                  <div className="reviewScore">
                    <div className="scoreBarBackground">
                      <div className="scoreBar" />
                    </div>
                    <span>{!reviewData ? reviewData.data.productTotalStar.starCostEffectiveAvg : 0}</span>
                  </div>
                </div>
              </ProductReviewScoreArea>
              <ProductReviewScoreArea reviewScore={!reviewData ? reviewData.data.productTotalStar.starLocationAvg : 0}>
                <div className="reviewFlexBox">
                  <div className="reviewText">위치</div>
                  <div className="reviewScore">
                    <div className="scoreBarBackground">
                      <div className="scoreBar" />
                    </div>
                    <span>{!reviewData ? reviewData.data.productTotalStar.starLocationAvg : 0}</span>
                  </div>
                </div>
              </ProductReviewScoreArea>
            </div>
            <ProductReviewListArea>
              {!reviewData ? (
                reviewData.data.data.map((el, index) => {
                  if (index < 5) {
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
                  }
                })
              ) : (
                <></>
              )}
              {!reviewData ? (
                <>
                  <div className="btnArea">
                    <button
                      className="more"
                      onClick={() => modalController('review', '900px', '800px', 'product_review')}
                    >
                      후기 {!reviewData.data.pageInfo.totalElements}개 모두 보기
                    </button>
                  </div>
                </>
              ) : (
                <></>
              )}
            </ProductReviewListArea>
          </ProductReviewArea>
        </>
      ) : (
        <></>
      )}
    </>
  );
};

export default ProductReview;
