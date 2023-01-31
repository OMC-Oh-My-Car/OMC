// import React from 'react';
import { Container, MainContainer } from './ProductDetailPage.style';
import Header from '../components/header/Header';
import ProductImage from '../components/productDetail/ProductImage';
import ProductInfo from '../components/productDetail/ProductInfo';
import ProductReview from '../components/productDetail/ProductReview';
import ProductMap from '../components/productDetail/ProductMap';

const ProductDetailPage = ({ openModalController }) => {
  return (
    <>
      <Container>
        <Header type="short" />
        <MainContainer>
          <ProductImage />
          <ProductInfo
            openModalController={() => openModalController({ type: 'content', width: '700px', height: '800px' })}
          />
          <ProductReview />
          <ProductMap />
        </MainContainer>
      </Container>
    </>
  );
};

export default ProductDetailPage;
