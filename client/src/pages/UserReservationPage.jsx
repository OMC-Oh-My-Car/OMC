import { useState } from 'react';
import { useQuery } from 'react-query';
import { Container, MainContainer } from './UserReservationPage.style';
import Header from '../components/header/Header';
// import UserReservationEmpty from '../components/userReservation/UserReservationEmpty';
import UserReservationList from '../components/userReservation/UserReservationList';
import { getUserReservationList } from '../modules/userReservation/userReservation';
import Pagination from '../components/pagination/Pagination';
const ReservationUserPage = ({ openModalController }) => {
  const [page, setpage] = useState(1);

  const itemChange = (page) => {
    setpage(page.selected + 1);
  };
  const { isLoading, data, isError } = useQuery(['productList', page], async () => {
    const data = await getUserReservationList(page);
    return data;
  });
  // 예약 정보 상세 조회(모달 창에서)

  // 리뷰 작성(모달 창에서)

  // 리뷰 조회(모달 창에서)

  // 예약 취소(모달 창에서)

  // 취소 사유 조회(모달 창에서)
  return (
    <>
      <Container>
        <Header type="short" />
        <MainContainer>
          {/* <UserReservationEmpty /> */}
          <UserReservationList
            data={data}
            isLoading={isLoading}
            isError={isError}
            openModalController={openModalController}
          />
          <Pagination itemChange={itemChange} />
        </MainContainer>
      </Container>
    </>
  );
};
export default ReservationUserPage;
