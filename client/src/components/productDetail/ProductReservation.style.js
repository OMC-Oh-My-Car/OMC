import styled from 'styled-components';

export const ProductReservationArea = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  .flexBox {
    width: 100%;
    flex-wrap: wrap;
    gap: 15px;
    display: flex;
    justify-content: space-between;
    align-items: flex-end;
    margin-bottom: 20px;
    .price {
      font-size: 18px;
      font-weight: 700;
    }
    .review {
      font-size: 14px;
    }
    .prductDescription {
      font-size: 13px;
    }
    .starIcon {
      margin-right: 4px;
      font-size: 12px;
    }
    .productInfoReviewCount {
      text-decoration: underline;
      cursor: pointer;
    }
  }
  .reservationDate {
    position: relative;
    display: flex;
    width: 100%;
    height: 50px;
    border: 1px solid ${(props) => props.theme.gray};
    border-radius: 5px;
    font-size: 10px;
    margin-bottom: 20px;
    cursor: pointer;
    .startDate {
      width: 50%;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      padding: 9px 10px;
      border-right: 1px solid ${(props) => props.theme.gray};
      cursor: pointer;
    }
    .endDate {
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      padding: 9px 10px;
      width: 50%;
      cursor: pointer;
    }
    .checkDate {
      font-size: 13px;
      margin-top: 5px;
      font-weight: 700;
    }
  }
  .reserveButton {
    width: 100%;
    height: 50px;
    border-radius: 5px;
    outline: none;
    border: none;
    background-color: ${(props) => props.theme.yellow};
    color: white;
    font-size: 17px;
    font-weight: 700;
    transition: all 0.2s;
    cursor: pointer;
    margin-bottom: 20px;
  }
  .reserveButton:hover {
    border: 1px solid ${(props) => props.theme.yellow};
    color: ${(props) => props.theme.yellow};
    background-color: white;
  }
  .priceFlex {
    display: flex;
    justify-content: space-between;
    align-items: flex-end;
    width: 100%;
    padding-bottom: 20px;
    margin-bottom: 20px;
    border-bottom: 1px solid ${(props) => props.theme.gray};
    span {
      color: ${(props) => props.theme.gray};
      font-size: 14px;
      font-weight: 700;
    }
  }
  .totalPriceFlex {
    display: flex;
    justify-content: space-between;
    align-items: flex-end;
    width: 100%;
    span {
      color: black;
      font-size: 14px;
      font-weight: 700;
    }
  }
`;
