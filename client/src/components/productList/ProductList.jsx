// import React from 'react';
import { ProductListArea } from './ProductList.style';
import ProductItem from './productItem/ProductItem';
import ProductItemSkeleton from './productItem/ProductItemSkeleton';

const ProductList = ({ data, isLoading, isError }) => {
  const skeletonMapArr = Array.from({ length: 12 }, (v, i) => i);

  if (isLoading) {
    return (
      <>
        <ProductListArea>
          <div className="productList">
            {skeletonMapArr.map((el) => {
              return <ProductItemSkeleton key={el} />;
            })}
          </div>
        </ProductListArea>
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
      <ProductListArea>
        <div className="productList">
          {data &&
            data.data.map((item) => {
              return <ProductItem key={item.productId} item={item} />;
            })}
        </div>
      </ProductListArea>
    </>
  );
};

export default ProductList;
