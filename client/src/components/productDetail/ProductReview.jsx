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
                {data.data.data.star} · 후기 {reviewData.data.pageInfo.totalElements}개
              </span>
            </h2>
            <div className="reviewScoreList">
              <ProductReviewScoreArea reviewScore="4.2">
                <div className="reviewFlexBox">
                  <div className="reviewText">청결도</div>
                  <div className="reviewScore">
                    <div className="scoreBarBackground">
                      <div className="scoreBar" />
                    </div>
                    <span>4.2</span>
                  </div>
                </div>
              </ProductReviewScoreArea>
              <ProductReviewScoreArea reviewScore="4.2">
                <div className="reviewFlexBox">
                  <div className="reviewText">청결도</div>
                  <div className="reviewScore">
                    <div className="scoreBarBackground">
                      <div className="scoreBar" />
                    </div>
                    <span>4.2</span>
                  </div>
                </div>
              </ProductReviewScoreArea>
              <ProductReviewScoreArea reviewScore="4.2">
                <div className="reviewFlexBox">
                  <div className="reviewText">청결도</div>
                  <div className="reviewScore">
                    <div className="scoreBarBackground">
                      <div className="scoreBar" />
                    </div>
                    <span>4.2</span>
                  </div>
                </div>
              </ProductReviewScoreArea>
              <ProductReviewScoreArea reviewScore="4.2">
                <div className="reviewFlexBox">
                  <div className="reviewText">청결도</div>
                  <div className="reviewScore">
                    <div className="scoreBarBackground">
                      <div className="scoreBar" />
                    </div>
                    <span>4.2</span>
                  </div>
                </div>
              </ProductReviewScoreArea>
            </div>
            <ProductReviewListArea>
              {reviewData.data.data.map((el, index) => {
                return (
                  <>
                    <div key={index} className="reviewListItem">
                      <div className="user">
                        <div className="userIconBackground">
                          <FontAwesomeIcon className="userIcon" icon={faUser} />
                        </div>
                        <div className="userInfo">
                          <div className="username">혜지</div>
                          <div className="createAt">2021년 12월</div>
                        </div>
                      </div>
                      <span className="review">{el.content}</span>
                    </div>
                  </>
                );
              })}
              <div className="btnArea">
                <button className="more" onClick={() => modalController('review', '900px', '800px', 'product_review')}>
                  후기 {reviewData.data.pageInfo.totalElements}개 모두 보기
                </button>
              </div>
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
