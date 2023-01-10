// import React from 'react';
import { ProductListArea } from './ProductList.style';
import ProductItem from './productItem/ProductItem';

const ProductList = ({ data, isLoading, isError }) => {
  console.log(data);
  console.log(isLoading);
  console.log(isError);
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
