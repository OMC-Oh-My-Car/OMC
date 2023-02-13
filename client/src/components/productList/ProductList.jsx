// import React from 'react';
import { ProductListArea } from './ProductList.style';
import ProductItem from './productItem/ProductItem';
import ProductItemSkeleton from './productItem/ProductItemSkeleton';
import { useNavigate } from 'react-router-dom';

const ProductList = ({ data, isLoading, isError }) => {
  const navigate = useNavigate();
  const skeletonMapArr = Array.from({ length: 12 }, (v, i) => i);

  const handleClick = (productId) => {
    navigate(`/product/${productId}`);
  };

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
              return <ProductItem handleClick={handleClick} key={item.productId} item={item} />;
            })}
        </div>
      </ProductListArea>
    </>
  );
};

export default ProductList;
