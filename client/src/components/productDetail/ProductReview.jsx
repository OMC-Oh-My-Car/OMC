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
        reviewData.data.data ? (
          <>
            <ProductReviewArea>
              <h2>
                <span>
                  <FontAwesomeIcon className="starIcon" icon={faStar} />
                  {reviewData.data.productTotalStar.totalStarAvg} · 후기 {reviewData.data.pageInfo.totalElements}개
                </span>
              </h2>
              <div className="reviewScoreList">
                <ProductReviewScoreArea reviewScore={reviewData.data.productTotalStar.starAccuracyAvg}>
                  <div className="reviewFlexBox">
                    <div className="reviewText">정확도</div>
                    <div className="reviewScore">
                      <div className="scoreBarBackground">
                        <div className="scoreBar" />
                      </div>
                      <span>{reviewData.data.productTotalStar.starAccuracyAvg}</span>
                    </div>
                  </div>
                </ProductReviewScoreArea>
                <ProductReviewScoreArea reviewScore={reviewData.data.productTotalStar.starCleanlinessAvg}>
                  <div className="reviewFlexBox">
                    <div className="reviewText">청결도</div>
                    <div className="reviewScore">
                      <div className="scoreBarBackground">
                        <div className="scoreBar" />
                      </div>
                      <span>{reviewData.data.productTotalStar.starCleanlinessAvg}</span>
                    </div>
                  </div>
                </ProductReviewScoreArea>
                <ProductReviewScoreArea reviewScore={reviewData.data.productTotalStar.starCostEffectiveAvg}>
                  <div className="reviewFlexBox">
                    <div className="reviewText">가격 대비 만족도</div>
                    <div className="reviewScore">
                      <div className="scoreBarBackground">
                        <div className="scoreBar" />
                      </div>
                      <span>{reviewData.data.productTotalStar.starCostEffectiveAvg}</span>
                    </div>
                  </div>
                </ProductReviewScoreArea>
                <ProductReviewScoreArea reviewScore={reviewData.data.productTotalStar.starLocationAvg}>
                  <div className="reviewFlexBox">
                    <div className="reviewText">위치</div>
                    <div className="reviewScore">
                      <div className="scoreBarBackground">
                        <div className="scoreBar" />
                      </div>
                      <span>{reviewData.data.productTotalStar.starLocationAvg}</span>
                    </div>
                  </div>
                </ProductReviewScoreArea>
              </div>
              <ProductReviewListArea>
                {reviewData.data.data.map((el, index) => {
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
                })}
                <div className="btnArea">
                  <button
                    className="more"
                    onClick={() => modalController('review', '900px', '800px', 'product_review')}
                  >
                    후기 {reviewData.data.pageInfo.totalElements}개 모두 보기
                  </button>
                </div>
              </ProductReviewListArea>
            </ProductReviewArea>
          </>
        ) : (
          <>
            <ProductReviewArea>
              <h2>
                <span>
                  <FontAwesomeIcon className="starIcon" icon={faStar} />0 · 후기 0개
                </span>
              </h2>
              <div className="reviewScoreList">
                <ProductReviewScoreArea reviewScore="0">
                  <div className="reviewFlexBox">
                    <div className="reviewText">정확도</div>
                    <div className="reviewScore">
                      <div className="scoreBarBackground">
                        <div className="scoreBar" />
                      </div>
                      <span>0</span>
                    </div>
                  </div>
                </ProductReviewScoreArea>
                <ProductReviewScoreArea reviewScore="0">
                  <div className="reviewFlexBox">
                    <div className="reviewText">청결도</div>
                    <div className="reviewScore">
                      <div className="scoreBarBackground">
                        <div className="scoreBar" />
                      </div>
                      <span>0</span>
                    </div>
                  </div>
                </ProductReviewScoreArea>
                <ProductReviewScoreArea reviewScore="0">
                  <div className="reviewFlexBox">
                    <div className="reviewText">가격 대비 만족도</div>
                    <div className="reviewScore">
                      <div className="scoreBarBackground">
                        <div className="scoreBar" />
                      </div>
                      <span>0</span>
                    </div>
                  </div>
                </ProductReviewScoreArea>
                <ProductReviewScoreArea reviewScore="0">
                  <div className="reviewFlexBox">
                    <div className="reviewText">위치</div>
                    <div className="reviewScore">
                      <div className="scoreBarBackground">
                        <div className="scoreBar" />
                      </div>
                      <span>0</span>
                    </div>
                  </div>
                </ProductReviewScoreArea>
              </div>
              <ProductReviewListArea></ProductReviewListArea>
            </ProductReviewArea>
          </>
        )
      ) : (
        <></>
      )}
    </>
  );
};

export default ProductReview;
