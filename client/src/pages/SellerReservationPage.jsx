import { useState } from 'react';
import { useQuery } from 'react-query';
import { Container, MainContainer } from './SellerReservationPage.style';
import Header from '../components/header/Header';
import Pagination from '../components/pagination/Pagination';
import SellerReservationList from '../components/sellerReservation/SellerReservationList';
import { getSellerReservationList } from '../modules/sellerReservation/sellerReservation';
// import SellerReservationListEmpty from '../components/sellerReservation/SellerReservationListEmpty';
const SellerReservationPage = () => {
  const [page, setpage] = useState(1);
  const [filter, setFilter] = useState(0);

  const itemChange = (page) => {
    setpage(page.selected + 1);
  };

  const { isLoading, data, isError } = useQuery('sellerProductList', async () => {
    const data = await getSellerReservationList(filter, page);
    return data;
  });

  return (
    <>
      <Container>
        <Header type="short" />
        <MainContainer>
          <SellerReservationList
            data={data}
            isLoading={isLoading}
            isError={isError}
            filter={filter}
            setFilter={setFilter}
          />
          {/* <SellerReservationListEmpty /> */}
          <Pagination itemChange={itemChange} />
        </MainContainer>
      </Container>
    </>
  );
};
export default SellerReservationPage;
