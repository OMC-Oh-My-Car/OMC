import styled from 'styled-components';

export const ProductImageArea = styled.div`
  position: relative;
  display: grid;
  width: 100%;
  height: 500px;
  gap: 10px;
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
    border-radius: 5px 0px 0px 5px;
  }
  .image3 {
    border-radius: 0px 5px 0px 0px;
  }
  .image5 {
    border-radius: 0px 0px 5px 0px;
  }
  @media (max-width: 1320px) {
    height: 38vw;
  }
  @media (max-width: 1028px) {
    gap: 8px;
  }
  @media (max-width: 744px) {
    grid-template-columns: repeat(2, 1fr);
    grid-template-areas:
      'a a'
      'a a';
    height: auto;
    .image1 {
      border-radius: 0px;
    }
    .image2 {
      display: none;
    }
    .image3 {
      display: none;
    }
    .image4 {
      display: none;
    }
    .image5 {
      display: none;
    }
  }
`;
