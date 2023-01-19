import styled from 'styled-components';

export const SellerReservationItemArea = styled.div`
  display: flex;
  width: 100%;
  margin-bottom: 20px;
  .image {
    width: 100%;
    max-width: 220px;
    height: 170px;
    background-color: #eee;
    margin-right: 20px;
    img {
      width: 100%;
      height: 100%;
      object-fit: fill;
      border-radius: 5px;
      cursor: pointer;
    }
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
      font-size: 17px;
      margin-top: 5px;
      .reservationNumber {
        width: 100%;
        margin-bottom: 30px;
      }
      .reservationDate {
        width: 100%;
        margin-bottom: 10px;
      }
      .userEmail {
        width: 100%;
      }
    }
    .button {
      display: flex;
      justify-content: flex-end;
      width: 100%;
      .buttonLeft {
        padding: 10px 20px;
        font-size: 16px;
        font-weight: 700;
        outline: none;
        border-radius: 5px;
        transition: all 0.2s;
        font-family: sans-serif;
        background-color: ${(props) => props.theme.yellow};
        border: 1px solid ${(props) => props.theme.yellow};
        color: white;
        margin-right: 10px;
        cursor: pointer;
      }
      .buttonLeft:hover {
        border: 1px solid ${(props) => props.theme.yellow};
        color: ${(props) => props.theme.yellow};
        background-color: white;
      }
      .buttonRight {
        padding: 10px 20px;
        font-size: 16px;
        font-weight: 700;
        outline: none;
        border-radius: 5px;
        transition: all 0.2s;
        font-family: sans-serif;
        background-color: ${(props) => props.theme.red};
        border: 1px solid ${(props) => props.theme.red};
        color: white;
        cursor: pointer;
      }
      .buttonRight:hover {
        border: 1px solid ${(props) => props.theme.red};
        color: ${(props) => props.theme.red};
        background-color: white;
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
    .flexRight {
      .reservationInfo {
        margin-bottom: 20px;
        font-size: 16px;
        .reservationNumber {
          margin-bottom: 25px;
        }
      }
    }
  }
`;
