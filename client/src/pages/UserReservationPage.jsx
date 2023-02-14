import { useState } from 'react';
import { useQuery } from 'react-query';
import { Container, MainContainer } from './UserReservationPage.style';
import Header from '../components/header/Header';
import UserReservationEmpty from '../components/userReservation/UserReservationEmpty';
import UserReservationList from '../components/userReservation/UserReservationList';
import { getUserReservationList } from '../modules/userReservation/userReservation';
import Pagination from '../components/pagination/Pagination';
const ReservationUserPage = ({ openModalController }) => {
  const [page, setpage] = useState(1);

  const itemChange = (page) => {
    setpage(page.selected + 1);
  };
  const { isLoading, data, isError } = useQuery(['userReservation', page], async () => {
    const data = await getUserReservationList(page);
    return data;
  });

  return (
    <>
      <Container>
        <Header type="short" />
        <MainContainer>
          {data && data.data.data.length === 0 ? (
            <>
              <UserReservationEmpty />
            </>
          ) : (
            <>
              <UserReservationList
                data={data}
                isLoading={isLoading}
                isError={isError}
                openModalController={openModalController}
              />
            </>
          )}
          <Pagination itemChange={itemChange} />
        </MainContainer>
      </Container>
    </>
  );
};
export default ReservationUserPage;
