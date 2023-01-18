import styled from 'styled-components';

export const UserReservationItemSkeletonArea = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  /* align-items: center; */
  width: 100%;
  height: 440px;
  /* border: 1px solid black; */
  cursor: pointer;
  .imageArea {
    position: relative;
    width: 100%;
    height: calc(100% - 160px);
    background-color: #eee;
    border-radius: 5px;
  }
  .productInfo {
    position: relative;
    display: flex;
    flex-direction: column;
    font-size: 15px;
    width: 100%;
    flex-grow: 1;
    padding-top: 10px;
    /* border: 1px solid black; */
    font-family: sans-serif;
    .productTitle {
      width: 100%;
      height: 20px;
      background-color: #eee;
      border-radius: 5px;
      margin-bottom: 8px;
    }
    .reservationPeriod {
      width: 60%;
      height: 20px;
      background-color: #eee;
      border-radius: 5px;
      margin-bottom: 20px;
    }
    .button {
      width: 100%;
      height: 35px;
      background-color: #eee;
      font-weight: 700;
      outline: none;
      border-radius: 5px;
      transition: all 0.2s;
      font-family: sans-serif;
      cursor: pointer;
    }
    .buttonRed {
      margin-bottom: 10px;
    }
  }
  @media (max-width: 1320px) {
    height: 34vw;
  }
  @media (max-width: 1180px) {
    height: 40vw;
  }
  @media (max-width: 950px) {
    height: 60vw;
  }
  @media (max-width: 550px) {
    height: 100vw;
  }
`;
