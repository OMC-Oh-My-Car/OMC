import { useState } from 'react';
import { useQuery } from 'react-query';
import { Container } from './ProductListPage.style';
import Header from '../components/header/Header';
import ProductList from '../components/productList/ProductList';
import { getProductList } from '../modules/product/productList';

const ProductListPage = () => {
  const [filter, setFilter] = useState(0);

  const { isLoading, data, isError } = useQuery('productList', async () => {
    const data = await getProductList(filter, 1, 'search');
    return data;
  });
  return (
    <>
      <Container>
        <Header type="long" />
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
      </Container>
    </>
  );
};

export default ProductListPage;
