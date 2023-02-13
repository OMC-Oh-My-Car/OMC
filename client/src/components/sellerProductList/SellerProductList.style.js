import styled from 'styled-components';

export const SellerProductListArea = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 10px 0px 30px;
  .flexBox {
    width: 100%;
    display: flex;
    justify-content: space-between;
    border-bottom: 1px solid ${(props1) => props1.theme.gray};
    margin-bottom: 30px;
    padding-bottom: 5px;
    h1 {
      font-size: 27px;
      font-weight: 700;
    }
    button {
      padding: 10px 20px;
      font-size: 16px;
      font-weight: 700;
      outline: none;
      border-radius: 5px;
      transition: all 0.2s;
      font-family: sans-serif;
      margin-bottom: 30px;
      cursor: pointer;
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
  .productList {
    width: 100%;
    display: grid;
    gap: 25px;
    grid-template-columns: repeat(4, 1fr);
    /* vw 길이 - padding 길이 - gap 길이* + 텍스트 길이 */
    /* grid-auto-rows: minmax(auto, 400px); */
  }
  @media (max-width: 1180px) {
    .productList {
      grid-template-columns: repeat(3, 1fr);
      /* vw 길이 - padding 길이 - gap 길이) / 컬럼 갯수 + 텍스트 길이 */
    }
  }
  @media (max-width: 950px) {
    .productList {
      grid-template-columns: repeat(2, 1fr);
      /* vw 길이 - padding 길이 - gap 길이) / 컬럼 갯수 + 텍스트 길이 */
    }
  }
  @media (max-width: 550px) {
    .productList {
      grid-template-columns: repeat(1, 1fr);
      /* vw 길이 - padding 길이 - gap 길이) / 컬럼 갯수 + 텍스트 길이 */
    }
  }
`;
