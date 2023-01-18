import styled from 'styled-components';

export const UserReservationItemArea = styled.div`
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
    font-size: 30px;
    color: rgba(255, 255, 255, 0.5);
    img {
      width: 100%;
      height: 100%;
      object-fit: fill;
      border-radius: 5px;
    }
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
      font-size: 17px;
      font-weight: 700;
      margin-bottom: 8px;
    }
    .reservationPeriod {
      font-weight: 700;
      font-size: 13px;
      color: ${(props) => props.theme.gray};
      margin-bottom: 20px;
    }
    button {
      padding: 10px 20px;
      font-size: 16px;
      font-weight: 700;
      outline: none;
      border-radius: 5px;
      transition: all 0.2s;
      font-family: sans-serif;
      cursor: pointer;
    }
    .buttonYellow {
      background-color: ${(props) => props.theme.yellow};
      border: 1px solid ${(props) => props.theme.yellow};
      color: white;
    }
    .buttonYellow:hover {
      border: 1px solid ${(props) => props.theme.yellow};
      color: ${(props) => props.theme.yellow};
      background-color: white;
    }
    .buttonRed {
      background-color: ${(props) => props.theme.red};
      border: 1px solid ${(props) => props.theme.red};
      color: white;
      margin-bottom: 5px;
    }
    .buttonRed:hover {
      border: 1px solid ${(props) => props.theme.red};
      color: ${(props) => props.theme.red};
      background-color: white;
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
