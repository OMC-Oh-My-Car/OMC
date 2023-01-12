import styled from 'styled-components';

export const ProductReviewScoreArea = styled.div`
  width: 50%;
  height: 36px;
  .reviewFlexBox {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding-right: 80px;
    .reviewText {
      font-size: 18px;
      font-weight: 300;
    }
    .reviewScore {
      display: flex;
      align-items: center;
      width: 150px;
      height: 100%;
      .scoreBarBackground {
        position: relative;
        flex-grow: 1;
        height: 5px;
        background-color: #dddcdc;
        border-radius: 3px;
        margin-right: 10px;
        .scoreBar {
          width: ${(props) => props.reviewScore && `${(props.reviewScore / 5) * 100}%`};
          height: 100%;
          border-radius: 3px;
          background-color: black;
        }
      }
      span {
        font-size: 13px;
      }
    }
  }
`;
