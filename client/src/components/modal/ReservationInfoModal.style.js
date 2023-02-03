import styled from 'styled-components';

export const ReservationInfoModalArea = styled.div`
  width: 100%;
  height: 90%;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding-right: 25px;
  h2 {
    font-size: 27px;
    font-weight: 700;
    margin-bottom: 30px;
  }
  .reservationInfo {
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    width: 100%;
    .reservationNumberArea {
      position: relative;
      padding: 40px 0px 0px 20px;
      .label {
        position: absolute;
        top: 13px;
        font-size: 17px;
        color: ${(props) => props.theme.gray};
      }
      .reservationNumber {
        font-size: 22px;
        font-weight: 500;
      }
      width: 100%;
      height: 80px;
      border: 1px solid black;
      border-bottom: none;
    }
    .reservationCheckInArea {
      position: relative;
      padding: 40px 0px 0px 20px;
      .label {
        position: absolute;
        top: 13px;
        font-size: 17px;
        color: ${(props) => props.theme.gray};
      }
      .reservationCheckIn {
        font-size: 22px;
        font-weight: 500;
      }
      width: 50%;
      height: 80px;
      border: 1px solid black;
      border-bottom: none;
      border-right: none;
    }
    .reservationCheckOutArea {
      position: relative;
      padding: 40px 0px 0px 20px;
      .label {
        position: absolute;
        top: 13px;
        font-size: 17px;
        color: ${(props) => props.theme.gray};
      }
      .reservationCheckOut {
        font-size: 22px;
        font-weight: 500;
      }
      width: 50%;
      height: 80px;
      border: 1px solid black;
      border-bottom: none;
    }
    .reservationUserNameArea {
      position: relative;
      padding: 40px 0px 0px 20px;
      .label {
        position: absolute;
        top: 13px;
        font-size: 17px;
        color: ${(props) => props.theme.gray};
      }
      .reservationName {
        font-size: 22px;
        font-weight: 500;
      }
      width: 100%;
      height: 80px;
      border: 1px solid black;
      border-bottom: none;
    }
    .reservationUserEmailArea {
      position: relative;
      padding: 40px 0px 0px 20px;
      .label {
        position: absolute;
        top: 13px;
        font-size: 17px;
        color: ${(props) => props.theme.gray};
      }
      .reservationEmail {
        font-size: 22px;
        font-weight: 500;
      }
      width: 100%;
      height: 80px;
      border: 1px solid black;
    }
    .more {
      padding: 10px 25px;
      margin: 20px 0px;
      font-size: 20px;
      font-weight: 600;
      outline: none;
      border-radius: 5px;
      transition: all 0.2s;
      font-family: sans-serif;
      background-color: ${(props) => props.theme.yellow};
      border: 1px solid ${(props) => props.theme.yellow};
      color: white;
      cursor: pointer;
    }
    .more:hover {
      border: 1px solid ${(props) => props.theme.yellow};
      color: ${(props) => props.theme.yellow};
      background-color: white;
    }
  }
  @media (max-width: 744px) {
    padding: 0px 45px 0px 20px;
  }
  @media (max-width: 500px) {
    padding: 0px 25px 0px 0px;
  }
`;
