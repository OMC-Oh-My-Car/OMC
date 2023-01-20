// import React from 'react';
import { SellerProductListArea } from './SellerProductList.style';
import SellerProductItem from './SellerProductItem';
import SellerProductItemSkeleton from './SellerProductItemSkeleton';

const SellerProductList = ({ data, isLoading, isError }) => {
  const skeletonMapArr = Array.from({ length: 8 }, (v, i) => i);
  if (isLoading) {
    return (
      <>
        <SellerProductListArea>
          <h1>등록 상품</h1>
          <div className="productList">
            {skeletonMapArr.map((el) => {
              return <SellerProductItemSkeleton key={el} />;
            })}
          </div>
        </SellerProductListArea>
      </>
    );
  }

  if (isError) {
    return (
      <>
        <div>err</div>
      </>
    );
  }

  return (
    <>
      <SellerProductListArea>
        <h1>등록 상품</h1>
        <div className="productList">
          {data &&
            data.data
              .filter((el) => el.productId)
              .map((item) => {
                return <SellerProductItem key={item.productId} />;
              })}
        </div>
      </SellerProductListArea>
    </>
  );
};

export default SellerProductList;
