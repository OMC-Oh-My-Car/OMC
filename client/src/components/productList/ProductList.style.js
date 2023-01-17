import styled from 'styled-components';

export const ProductListArea = styled.main`
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  padding: 20px 100px;
  /* border: 1px solid black; */
  .productFilter {
    display: flex;
    justify-content: end;
    align-items: center;
    gap: 10px;
    width: 100%;
    height: 40px;
    margin-bottom: 10px;
    /* border: 1px solid black; */
    .productFilterItem {
      cursor: pointer;
      font-size: 15px;
      padding: 7px 10px;
      outline: none;
      border: none;
      background-color: white;
      margin: 0;
    }
    .productFilterItem.active {
      color: ${(props) => props.theme.yellow};
      font-weight: 700;
      border: 2px solid ${(props) => props.theme.yellow};
      border-radius: 20px;
    }
    .productFilterItem:hover {
      color: ${(props) => props.theme.red};
    }
  }
  .productList {
    width: 100%;
    display: grid;
    gap: 25px;
    grid-template-columns: repeat(6, 1fr);
    /* vw 길이 - padding 길이 - gap 길이* + 텍스트 길이 */
    grid-auto-rows: minmax(auto, 354px);
  }
  @media (max-width: 1880px) {
    .productList {
      grid-template-columns: repeat(5, 1fr);
      /* vw 길이 - padding 길이 - gap 길이) / 컬럼 갯수 + 텍스트 길이 */
      grid-auto-rows: calc((100vw - 200px - 100px) / 5 + 88px);
    }
  }
  @media (max-width: 1640px) {
    .productList {
      grid-template-columns: repeat(4, 1fr);
      /* vw 길이 - padding 길이 - gap 길이) / 컬럼 갯수 + 텍스트 길이 */
      grid-auto-rows: calc((100vw - 200px - 75px) / 4 + 88px);
    }
  }
  @media (max-width: 1425px) {
    padding: 20px 50px;
    /* vw 길이 - padding 길이 - gap 길이) / 컬럼 갯수 + 텍스트 길이 */
    grid-auto-rows: calc((100vw - 100px - 100px) / 4 + 88px);
  }
  @media (max-width: 1128px) {
    .productList {
      grid-template-columns: repeat(3, 1fr);
      /* vw 길이 - padding 길이 - gap 길이) / 컬럼 갯수 + 텍스트 길이 */
      grid-auto-rows: calc((100vw - 100px - 50px) / 3 + 88px);
    }
  }
  @media (max-width: 950px) {
    .productList {
      grid-template-columns: repeat(2, 1fr);
      /* vw 길이 - padding 길이 - gap 길이) / 컬럼 갯수 + 텍스트 길이 */
      grid-auto-rows: calc((100vw - 100px - 25px) / 2 + 88px);
    }
  }
  @media (max-width: 744px) {
    padding: 20px 25px;
    /* vw 길이 - padding 길이 - gap 길이 - 가로 세로 비율 유지 + 텍스트 길이 */
    grid-auto-rows: calc(100vw - 50px - 25px + 88px);
  }
  @media (max-width: 550px) {
    .productList {
      grid-template-columns: repeat(1, 1fr);
      /* vw 길이 - padding 길이 - gap 길이 - 가로 세로 비율 유지 + 텍스트 길이 */
      grid-auto-rows: calc(100vw - 50px - 25px + 88px);
    }
  }
`;
