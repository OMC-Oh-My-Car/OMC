// import React from 'react';
import { Container } from './ProductListPage.style';
import Header from '../components/header/Header';
import ProductList from '../components/productList/ProductList';

const ProductListPage = () => {
  return (
    <>
      <Container>
        <Header type="long" />
        <ProductList />
      </Container>
    </>
  );
};

export default ProductListPage;
