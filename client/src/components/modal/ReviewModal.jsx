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
  console.log(data.data.data);

  return (
    <>
      <ReviewModalArea>
        <h2>
          <span>
            <FontAwesomeIcon className="starIcon" icon={faStar} />
            4.91 · 후기 11개
          </span>
        </h2>
        <div className="reviewScoreList">
          <ProductReviewScoreArea reviewScore="4.2">
            <div className="reviewFlexBox margin">
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
              <div className="reviewText">정확성</div>
              <div className="reviewScore">
                <div className="scoreBarBackground">
                  <div className="scoreBar" />
                </div>
                <span>4.2</span>
              </div>
            </div>
          </ProductReviewScoreArea>
          <ProductReviewScoreArea reviewScore="4.2">
            <div className="reviewFlexBox margin">
              <div className="reviewText">의사소통</div>
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
              <div className="reviewText">위치</div>
              <div className="reviewScore">
                <div className="scoreBarBackground">
                  <div className="scoreBar" />
                </div>
                <span>4.2</span>
              </div>
            </div>
          </ProductReviewScoreArea>
          <ProductReviewScoreArea reviewScore="4.2">
            <div className="reviewFlexBox margin">
              <div className="reviewText">체크인</div>
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
              <div className="reviewText">가격 대비 만족도</div>
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
                        <div className="username">혜지</div>
                        <div className="createAt">2021년 12월</div>
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
