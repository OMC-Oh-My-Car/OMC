import { useState } from 'react';
import { Container } from './ProductListPage.style';
import Header from '../components/header/Header';
import ProductList from '../components/productList/ProductList';

const ProductListPage = () => {
  const [filter, setFilter] = useState(0);
  return (
    <>
      <Container>
        <Header type="long" />
        <ProductList filter={filter} setFilter={setFilter} />
      </Container>
    </>
  );
};

export default ProductListPage;
