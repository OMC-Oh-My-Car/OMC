import styled from 'styled-components';

export const SellerReservationListArea = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 10px 0px 30px;
  h1 {
    font-size: 27px;
    font-weight: 700;
    border-bottom: 1px solid ${(props1) => props1.theme.gray};
    padding-bottom: 30px;
    margin-bottom: 20px;
  }
  .filter {
    display: flex;
    width: 280px;
    justify-content: space-between;
    font-size: 19px;
    color: ${(props1) => props1.theme.gray};
    font-weight: 700;
    margin-bottom: 30px;
    li {
      cursor: pointer;
    }
    .active {
      color: ${(props1) => props1.theme.yellow};
    }
  }
  .reservationList {
    display: flex;
    flex-direction: column;
    width: 100%;
  }
  @media (max-width: 550px) {
    .filter {
      width: 230px;
      font-size: 15px;
    }
  }
`;
