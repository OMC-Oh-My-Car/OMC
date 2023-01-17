import styled from 'styled-components';

export const ProductImageArea = styled.div`
  position: relative;
  display: grid;
  width: 100%;
  height: 500px;
  gap: 15px;
  grid-template-columns: repeat(4, 1fr);
  grid-template-areas:
    'a a b c'
    'a a d e';
  img {
    width: 100%;
    height: 100%;
    object-fit: fill;
    cursor: pointer;
  }
  .image1 {
    grid-area: a;
    border-radius: 15px 0px 0px 15px;
  }
  .image3 {
    border-radius: 0px 15px 0px 0px;
  }
  .image5 {
    border-radius: 0px 0px 15px 0px;
  }
  @media (max-width: 1320px) {
    height: 38vw;
  }
  @media (max-width: 1128px) {
    .image1 {
      grid-area: a;
      border-radius: 10px 0px 0px 10px;
    }
    .image3 {
      border-radius: 0px 10px 0px 0px;
    }
    .image5 {
      border-radius: 0px 0px 10px 0px;
    }
  }
`;
