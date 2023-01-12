import styled from 'styled-components';

export const ProductReviewArea = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  h2 {
    margin-bottom: 20px;
    span {
      width: 100%;
      font-size: 20px;
      .starIcon {
        margin-right: 5px;
      }
    }
  }
  .reviewScoreList {
    display: flex;
    flex-wrap: wrap;
    width: 100%;
  }
`;
