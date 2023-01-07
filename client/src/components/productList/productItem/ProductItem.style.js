import styled from 'styled-components';

export const ProductItemArea = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  img {
    width: 100%;
    height: calc(100% - 88px);
    /* height: calc(100% / 7); */
    object-fit: fill;
    border-radius: 5%;
  }
`;
