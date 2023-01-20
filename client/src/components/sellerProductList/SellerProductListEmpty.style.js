import styled from 'styled-components';

export const SellerProductListEmptyArea = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 10px 0px 30px;
  h1 {
    font-size: 27px;
    font-weight: 700;
    border-bottom: 1px solid ${(props1) => props1.theme.gray};
    padding-bottom: 30px;
    margin-bottom: 30px;
  }
  .boldSpan {
    font-size: 20px;
    font-weight: 700;
    margin-bottom: 10px;
  }
  .text {
    font-weight: 300;
    margin-bottom: 25px;
  }
  .addProduct {
    padding: 10px 20px;
    width: 130px;
    font-size: 16px;
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
  .addProduct:hover {
    border: 1px solid ${(props) => props.theme.yellow};
    color: ${(props) => props.theme.yellow};
    background-color: white;
  }
`;
