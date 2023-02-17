import { useState } from 'react';
import { useQuery } from 'react-query';
import { Container, MainContainer } from './SellerReservationPage.style';
import Header from '../components/header/Header';
import Pagination from '../components/pagination/Pagination';
import SellerReservationList from '../components/sellerReservation/SellerReservationList';
import { getSellerReservationList } from '../modules/sellerReservation/sellerReservation';
import SellerReservationListEmpty from '../components/sellerReservation/SellerReservationListEmpty';
import { useNavigate, useParams } from 'react-router-dom';

const SellerReservationPage = ({ openModalController }) => {
  const navigate = useNavigate();
  const params = useParams();

  let productId = params.productId;
  const [page, setpage] = useState(1);
  const [filter, setFilter] = useState(0);

  const itemChange = (page) => {
    setpage(page.selected + 1);
  };

  const modalController = (type, width, height, modal, reservationId) => {
    let userId = 12;
    navigate(`/seller/${userId}/product/${productId}/reservation?modal=${modal}&reservation_id=${reservationId}`);
    openModalController({ type, width, height });
  };

  const { isLoading, data, isError } = useQuery(['sellerProductList', filter, page, productId], async () => {
    const data = await getSellerReservationList(filter, page, productId);
    return data;
  });

  return (
    <>
      <Container>
        <Header type="short" />
        <MainContainer>
          {data && data.data.data !== [] ? (
            <>
              <SellerReservationList
                data={data}
                isLoading={isLoading}
                isError={isError}
                filter={filter}
                setFilter={setFilter}
                modalController={modalController}
              />
              <Pagination data={data} itemChange={itemChange} />
            </>
          ) : (
            <>
              <SellerReservationListEmpty />
            </>
          )}
        </MainContainer>
      </Container>
    </>
  );
};
export default SellerReservationPage;
