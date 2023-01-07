// import React from 'react';
import { ProductListArea } from './ProductList.style';
import ProductItem from './productItem/ProductItem';

const ProductList = () => {
  return (
    <>
      <ProductListArea>
        <ul className="productFilter">
          <li className="productFilterItem">최신순</li>
          <li className="productFilterItem">인기순</li>
          <li className="productFilterItem">조회순</li>
          <li className="productFilterItem">추천순</li>
        </ul>
        <div className="productList">
          <ProductItem />
          <ProductItem />
          <ProductItem />
          <ProductItem />
          <ProductItem />
          <ProductItem />
          <ProductItem />
          <ProductItem />
          <ProductItem />
          <ProductItem />
        </div>
      </ProductListArea>
    </>
  );
};

export default ProductList;
