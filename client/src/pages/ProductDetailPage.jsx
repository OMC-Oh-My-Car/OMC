// import React from 'react';
import { Container, MainContainer } from './ProductDetailPage.style';
import Header from '../components/header/Header';
import ProductImage from '../components/productDetail/ProductImage';
import ProductInfo from '../components/productDetail/ProductInfo';
const ProductDetailPage = () => {
  return (
    <>
      <Container>
        <Header type="short" />
        <MainContainer>
          <ProductImage />
          <ProductInfo />
        </MainContainer>
      </Container>
    </>
  );
};

export default ProductDetailPage;
