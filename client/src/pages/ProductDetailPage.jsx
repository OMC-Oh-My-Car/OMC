// import React from 'react';
import { Container, MainContainer } from './ProductDetailPage.style';
import { useQuery } from 'react-query';
import Header from '../components/header/Header';
import ProductImage from '../components/productDetail/ProductImage';
import ProductInfo from '../components/productDetail/ProductInfo';
import ProductReview from '../components/productDetail/ProductReview';
import ProductMap from '../components/productDetail/ProductMap';
import { getProductDetail } from '../modules/userProduct/userProductDetail';
import { useParams, useNavigate } from 'react-router-dom';

const ProductDetailPage = ({ openModalController }) => {
  const params = useParams();
  const navigate = useNavigate();

  let productId = params.productId;
  const { isLoading, data, isError } = useQuery(['productDetail', productId], async () => {
    const data = await getProductDetail(productId);
    return data;
  });
  const modalController = (type, width, height, modal) => {
    navigate(`/product/${productId}?modal=${modal}`);
    openModalController({ type, width, height });
  };
  return (
    <>
      <Container>
        <Header type="short" />
        <MainContainer>
          <ProductImage data={data} isLoading={isLoading} isError={isError} modalController={modalController} />
          <ProductInfo data={data} isLoading={isLoading} isError={isError} modalController={modalController} />
          <ProductReview data={data} isLoading={isLoading} isError={isError} modalController={modalController} />
          <ProductMap />
        </MainContainer>
      </Container>
    </>
  );
};

export default ProductDetailPage;
