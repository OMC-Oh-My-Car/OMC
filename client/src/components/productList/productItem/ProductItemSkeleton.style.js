import styled from 'styled-components';

export const ProductItemSkeletonArea = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  /* align-items: center; */
  width: 100%;
  .imageArea {
    position: relative;
    width: 100%;
    height: calc(100% - 88px);
    font-size: 30px;
    color: rgba(255, 255, 255, 0.5);
    border-radius: 10px;
    background-color: #eee;
  }
  .productInfo {
    position: relative;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    font-size: 15px;
    width: 100%;
    height: 88px;
    padding-top: 10px;
    /* border: 1px solid black; */
    font-family: sans-serif;
    .productTitle {
      width: 100%;
      height: 20px;
      background-color: #eee;
      border-radius: 10px;
      margin-bottom: 8px;
    }
    .productLocation {
      width: 60%;
      height: 20px;
      background-color: #eee;
      border-radius: 10px;
      margin-bottom: 8px;
    }
    .productPrice {
      width: 30%;
      height: 20px;
      background-color: #eee;
      border-radius: 10px;
      margin-bottom: 8px;
    }
  }
`;
