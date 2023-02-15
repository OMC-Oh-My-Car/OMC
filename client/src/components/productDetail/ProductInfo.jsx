// import React from 'react';
import { ProductInfoArea } from './ProductInfo.style';
import { faStar } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import ProductReservation from './ProductReservation';

const ProductInfo = ({ data, reviewData, modalController }) => {
  return (
    <>
      {data && reviewData ? (
        <>
          <ProductInfoArea>
            <div className="productInfo">
              <h1>{data.data.data.subject}</h1>
              <div className="prductDescription">
                <FontAwesomeIcon className="starIcon" icon={faStar} />
                <span>{data.data.data.star} · </span>
                <span className="productInfoReviewCount">후기 {reviewData.data.pageInfo.totalElements}개</span>
                <span> · </span>
                <span className="productInfoPlace">{data.data.data.address}</span>
              </div>
              <div className="content">
                <span>{data.data.data.description}</span>
                <button
                  className="more"
                  onClick={() => modalController('content', '700px', '800px', 'product_content')}
                >
                  내용 더 보기
                </button>
              </div>
              <div className="facilities">
                <h2>숙소 편의시설</h2>
                <div className="facilityList">
                  <div className="facilityLeft">
                    {data.data.data.facilities.map((el, index) => {
                      return (
                        <div key={index} className="facilityItem">
                          <div>{el}</div>
                        </div>
                      );
                    })}
                  </div>
                </div>
                <button
                  className="more"
                  onClick={() => modalController('facility', '700px', '800px', 'product_facility')}
                >
                  편의시설 모두 보기
                </button>
              </div>
            </div>
            <div className="reservation">
              <ProductReservation data={data} reviewData={reviewData} />
              <span
                role="presentation"
                onClick={() => modalController('addReport', '600px', '590px', 'product_add_report')}
                className="report"
              >
                숙소 신고하기
              </span>
            </div>
          </ProductInfoArea>
        </>
      ) : (
        <></>
      )}
    </>
  );
};

export default ProductInfo;
