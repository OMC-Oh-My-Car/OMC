// import React from 'react';
import { ProductReviewArea, ProductReviewScoreArea, ProductReviewListArea } from './ProductReview.style';
import { faStar, faUser } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const ProductReview = ({ modalController }) => {
  return (
    <>
      <ProductReviewArea>
        <h2>
          <span>
            <FontAwesomeIcon className="starIcon" icon={faStar} />
            4.91 · 후기 11개
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
          <div className="reviewListItem">
            <div className="user">
              <div className="userIconBackground">
                <FontAwesomeIcon className="userIcon" icon={faUser} />
              </div>
              <div className="userInfo">
                <div className="username">혜지</div>
                <div className="createAt">2021년 12월</div>
              </div>
            </div>
            <span className="review">크리스마스 기념으로 예약했는데 들어가자마자 너무 행복했습니다🥰</span>
          </div>
          <div className="reviewListItem">
            <div className="user">
              <div className="userIconBackground">
                <FontAwesomeIcon className="userIcon" icon={faUser} />
              </div>
              <div className="userInfo">
                <div className="username">혜지</div>
                <div className="createAt">2021년 12월</div>
              </div>
            </div>
            <span className="review">
              크리스마스 기념으로 예약했는데 들어가자마자 너무 행복했습니다🥰 창문으로 보이는 바다도 구름도 너무 예뻤고
              방안의 가구 소품들 모두 너무 완벽했습니다!! 청소도 정말 잘크리스마
            </span>
          </div>
          <div className="reviewListItem">
            <div className="user">
              <div className="userIconBackground">
                <FontAwesomeIcon className="userIcon" icon={faUser} />
              </div>
              <div className="userInfo">
                <div className="username">혜지</div>
                <div className="createAt">2021년 12월</div>
              </div>
            </div>
            <span className="review">
              크리스마스 기념으로 예약했는데 들어가자마자 너무 행복했습니다🥰 창문으로 보이는 바다도 구름도 너무 예뻤고
              방안의 가구 소품들 모두 너무 완벽했습니다!! 청소도 정말 잘크리스마스 기념으로 예약했는데 들어가자마자 너무
              행복했습니다🥰 창문으로 보이는 바다도 구름도 너무 예뻤고 방안의 가구 소품들 모두 너무
              완벽했습니다!!크리스마스 기념으로 예약했는데 들어가자마자 너무 행복했습니다🥰 창문으로 보이는 바다도
              구름도 너무 예뻤고 방안의 가구 소품들 모두 너무 완벽했습니다!! 청소도 정말 잘 청소도 정말 잘
            </span>
          </div>
          <div className="reviewListItem">
            <div className="user">
              <div className="userIconBackground">
                <FontAwesomeIcon className="userIcon" icon={faUser} />
              </div>
              <div className="userInfo">
                <div className="username">혜지</div>
                <div className="createAt">2021년 12월</div>
              </div>
            </div>
            <span className="review">
              크리스마스 기념으로 예약했는데 들어가자마자 너무 행복했습니다🥰 창문으로 보이는 바다도 구름도 너무 예뻤고
              방안의 가구 소품들 모두 너무 완벽했습니다!! 청소도 정말 잘크리스마스 기념으로 예약했는데 들어가자마자 너무
              행복했습니다🥰 창문으로 보이는 바다도 구름도 너무 예뻤고 방안의 가구 소품들 모두 너무
              완벽했습니다!!크리스마스 기념으로 예약했는데 들어가자마자 너무 행복했습니다🥰 창문으로 보이는 바다도
              구름도 너무 예뻤고 방안의 가구 소품들 모두 너무 완벽했습니다!! 청소도 정말 잘 청소도 정말 잘
            </span>
          </div>
          <div className="reviewListItem">
            <div className="user">
              <div className="userIconBackground">
                <FontAwesomeIcon className="userIcon" icon={faUser} />
              </div>
              <div className="userInfo">
                <div className="username">혜지</div>
                <div className="createAt">2021년 12월</div>
              </div>
            </div>
            <span className="review">
              크리스마스 기념으로 예약했는데 들어가자마자 너무 행복했습니다🥰 창문으로 보이는 바다도 구름도 너무 예뻤고
              방안의 가구 소품들 모두 너무 완벽했습니다!! 청소도 정말 잘크리스마스 기념으로 예약했는데 들어가자마자 너무
              행복했습니다🥰 창문으로 보이는 바다도 구름도 너무 예뻤고 방안의 가구 소품들 모두 너무
              완벽했습니다!!크리스마스 기념으로 예약했는데 들어가자마자 너무 행복했습니다🥰 창문으로 보이는 바다도
              구름도 너무 예뻤고 방안의 가구 소품들 모두 너무 완벽했습니다!! 청소도 정말 잘 청소도 정말 잘
            </span>
          </div>
          <div className="reviewListItem">
            <div className="user">
              <div className="userIconBackground">
                <FontAwesomeIcon className="userIcon" icon={faUser} />
              </div>
              <div className="userInfo">
                <div className="username">혜지</div>
                <div className="createAt">2021년 12월</div>
              </div>
            </div>
            <span className="review">
              크리스마스 기념으로 예약했는데 들어가자마자 너무 행복했습니다🥰 창문으로 보이는 바다도 구름도 너무 예뻤고
              방안의 가구 소품들 모두 너무 완벽했습니다!! 청소도 정말 잘크리스마스 기념으로 예약했는데 들어가자마자 너무
              행복했습니다🥰 창문으로 보이는 바다도 구름도 너무 예뻤고 방안의 가구 소품들 모두 너무
              완벽했습니다!!크리스마스 기념으로 예약했는데 들어가자마자 너무 행복했습니다🥰 창문으로 보이는 바다도
              구름도 너무 예뻤고 방안의 가구 소품들 모두 너무 완벽했습니다!! 청소도 정말 잘 청소도 정말 잘
            </span>
          </div>
          <button className="more" onClick={() => modalController('review', '900px', '800px', 'product_review')}>
            후기 311개 모두 보기
          </button>
        </ProductReviewListArea>
      </ProductReviewArea>
    </>
  );
};

export default ProductReview;
