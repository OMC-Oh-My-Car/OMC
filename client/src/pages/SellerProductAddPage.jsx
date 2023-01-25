// import { useState } from 'react';
import { Container, MainContainer } from './SellerProductAddPage.style';
import Header from '../components/header/Header';
import SellerProductAddImage from '../components/sellerProductAdd/SellerProductAddImage';
import SellerProductAddTitle from '../components/sellerProductAdd/SellerProductAddTitle';
import SellerProductAddContent from '../components/sellerProductAdd/SellerProductAddContent';

const SellerProductAddPage = () => {
  return (
    <>
      <Container>
        <Header type="short" />
        <MainContainer>
          <h1>상품 등록</h1>
          <SellerProductAddImage />
          <SellerProductAddTitle />
          <SellerProductAddContent />
        </MainContainer>
      </Container>
    </>
  );
};
export default SellerProductAddPage;
