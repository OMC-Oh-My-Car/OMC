import styled from 'styled-components';

export const SellerProductAddPriceArea = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 40px;
  h2 {
    width: 100%;
    max-width: 520px;
    font-size: 20px;
    font-weight: 700;
    margin-bottom: 20px;
  }
  .addPrice {
    position: relative;
    display: flex;
    width: 100%;
    max-width: 520px;
    span {
      position: absolute;
      top: 10px;
      left: 15px;
      font-size: 25px;
    }
    input {
      width: 100%;
      padding: 5px 40px;
      height: 50px;
      font-size: 17px;
      border: 1px solid ${(props) => props.theme.gray};
      outline: none;
      margin-bottom: 10px;
    }
  }
  .count {
    width: 100%;
    max-width: 520px;
    display: flex;
    justify-content: flex-end;
    span {
      color: ${(props) => props.theme.gray};
    }
  }
`;
