// import React from 'react';
import { ProductReviewScoreArea } from './ProductReviewScore.style';
export default function ProductReviewScore() {
  return (
    <>
      <ProductReviewScoreArea reviewScore="4.2">
        <div className="reviewFlexBox">
          <div className="reviewText">청결도</div>
          <div className="reviewScore">
            <div className="scoreBarBackground">
              <div className="scoreBar" />
            </div>
            <span>4.9</span>
          </div>
        </div>
      </ProductReviewScoreArea>
    </>
  );
}
