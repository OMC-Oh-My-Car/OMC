import styled from 'styled-components';

export const SellerReservationItemSkeletonArea = styled.div`
  display: flex;
  width: 100%;
  margin-bottom: 20px;
  .image {
    width: 100%;
    max-width: 220px;
    height: 170px;
    background-color: #eee;
    margin-right: 20px;
  }
  .flexRight {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    flex-grow: 1;
    height: 100%;
    .reservationInfo {
      display: flex;
      flex-direction: column;
      .reservationNumber {
        width: 100%;
        max-width: 200px;
        height: 25px;
        background-color: #eee;
        margin-bottom: 15px;
      }
      .reservationDate {
        width: 100%;
        max-width: 300px;
        height: 25px;
        background-color: #eee;
        margin-bottom: 10px;
      }
      .userEmail {
        width: 100%;
        max-width: 300px;
        height: 25px;
        background-color: #eee;
      }
    }
    .button {
      display: flex;
      justify-content: flex-end;
      width: 100%;
      .buttonLeft {
        width: 105px;
        height: 42px;
        border-radius: 5px;
        background-color: #eee;
        margin-right: 10px;
      }
      .buttonRight {
        width: 105px;
        height: 42px;
        border-radius: 5px;
        background-color: #eee;
      }
    }
  }
  @media (max-width: 550px) {
    flex-direction: column;
    .image {
      width: 100%;
      min-height: 50vw;
      max-width: none;
      margin-bottom: 10px;
    }
    .reservationInfo {
      margin-bottom: 20px;
    }
  }
`;
