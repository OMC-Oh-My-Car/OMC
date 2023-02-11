import { useState } from 'react';
import { useQuery } from 'react-query';
import { Container, MainContainer } from './SellerProductListPage.style';
import Header from '../components/header/Header';
import SellerProductList from '../components/sellerProductList/SellerProductList';
import { getSellerProductList } from '../modules/sellerProduct/sellerProductList';
import Pagination from '../components/pagination/Pagination';

const SellerProductListPage = () => {
  const [page, setpage] = useState(1);

  const itemChange = (page) => {
    setpage(page.selected + 1);
  };
  const { isLoading, data, isError } = useQuery('sellerProductList', async () => {
    const data = await getSellerProductList(page);
    return data;
  });
  return (
    <>
      <Container>
        <Header type="short" />
        <MainContainer>
          <SellerProductList data={data} isLoading={isLoading} isError={isError} />
          <Pagination itemChange={itemChange} />
        </MainContainer>
      </Container>
    </>
  );
};
export default SellerProductListPage;
