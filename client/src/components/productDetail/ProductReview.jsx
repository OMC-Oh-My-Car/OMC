// import React from 'react';
import { ProductReviewArea } from './ProductReview.style';
import { faStar } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import ProductReviewScore from './ProductReviewScore';
export default function ProductReview() {
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
          <ProductReviewScore />
          <ProductReviewScore />
          <ProductReviewScore />
          <ProductReviewScore />
        </div>
      </ProductReviewArea>
    </>
  );
}
