import { useState } from 'react';
import { useQuery } from 'react-query';
import { Container, MainContainer } from './ProductListPage.style';
import Header from '../components/header/Header';
import ProductList from '../components/productList/ProductList';
import { getProductList } from '../modules/product/productList';
import Pagination from '../components/pagination/Pagination';

const ProductListPage = () => {
  const [filter, setFilter] = useState(0);
  const [page, setpage] = useState(1);
  const itemChange = (page) => {
    setpage(page.selected + 1);
  };
  const { isLoading, data, isError } = useQuery('productList', async () => {
    const data = await getProductList(filter, page, 'search');
    return data;
  });
  return (
    <>
      <Container>
        <Header type="long" />
        <MainContainer>
          <div className="productFilter">
            <button className={`productFilterItem ${filter === 0 ? ' active' : ''}`} onClick={() => setFilter(0)}>
              최신순
            </button>
            <button className={`productFilterItem ${filter === 1 ? ' active' : ''}`} onClick={() => setFilter(1)}>
              인기순
            </button>
            <button className={`productFilterItem ${filter === 2 ? ' active' : ''}`} onClick={() => setFilter(2)}>
              조회순
            </button>
            <button className={`productFilterItem ${filter === 3 ? ' active' : ''}`} onClick={() => setFilter(3)}>
              추천순
            </button>
          </div>
          <ProductList data={data} isLoading={isLoading} isError={isError} />
          <Pagination itemChange={itemChange} />
        </MainContainer>
      </Container>
    </>
  );
};

export default ProductListPage;
