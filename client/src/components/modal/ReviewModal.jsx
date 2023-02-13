// import React from 'react';
import { ReviewModalArea, ProductReviewScoreArea, ProductReviewListArea } from './ReviewModal.style';
import { faStar, faUser } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { getProductDetail } from '../../modules/userProduct/userProductDetail';
import { useQuery } from 'react-query';
import { useSelector } from 'react-redux';

const ReviewModal = () => {
  const productId = useSelector((state) => state.modal.lastPath).split('/')[2];
  if (productId) {
    const { data } = useQuery(['productDetail', productId], async () => {
      const data = await getProductDetail(productId);
      return data;
    });
  }

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
              구름도 너무 예뻤고 방안의 가구 소품들 모두 너무 완벽했습니다!! 청소도 정말 잘 청소도 정말 잘들 모두 너무
              완벽했습니다!!크리스마스 기념으로 예약했는데 들어가자마자 너무 행복했습니다🥰 창문으로 보이는 바다도
              구름도 너무 예뻤고 방안의 가구 소품들 모두 너무 완벽했습니다!! 청소도 정말 잘 청소도 정말 잘들 모두 너무
              완벽했습니다!!크리스마스 기념으로 예약했는데 들어가자마자 너무 행복했습니다🥰 창문으로 보이는 바다도
              구름도 너무 예뻤고 방안의 가구 소품들 모두 너무 완벽했습니다!! 청소도 정말 잘 청소도 정말 잘
            </span>
          </div>
        </ProductReviewListArea>
      </ReviewModalArea>
    </>
  );
};

export default ReviewModal;
