import styled from 'styled-components';

export const ReviewModalArea = styled.div`
  width: 100%;
  height: 90%;
  overflow-y: scroll;
  flex-direction: column;
  padding-right: 25px;
  h2 {
    font-size: 27px;
    font-weight: 700;
    margin-bottom: 30px;
    .starIcon {
      margin-right: 10px;
    }
  }
  .reviewScoreList {
    display: flex;
    flex-wrap: wrap;
    width: 100%;
    margin-bottom: 20px;
  }
  @media (max-width: 744px) {
    .reviewScoreList {
      display: flex;
      flex-direction: column;
    }
  }
`;

export const ProductReviewScoreArea = styled.div`
  width: 50%;
  height: 36px;
  .margin {
    margin-right: 20px;
  }
  .reviewFlexBox {
    display: flex;
    align-items: center;
    justify-content: space-between;
    .reviewText {
      font-size: 16px;
      font-weight: 300;
    }
    .reviewScore {
      display: flex;
      align-items: center;
      width: 170px;
      height: 100%;
      .scoreBarBackground {
        flex-grow: 1;
        height: 4px;
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
  @media (max-width: 744px) {
    width: 100%;
    .margin {
      margin-right: 0px;
    }
    .reviewFlexBox {
      padding-right: 20px;
      .reviewText {
        font-size: 15px;
      }
      .reviewScore {
        width: 40vw;
        max-width: 300px;
        .scoreBarBackground {
          margin-right: 15px;
        }
      }
    }
  }
`;

export const ProductReviewListArea = styled.div`
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  .reviewListItem {
    display: flex;
    flex-direction: column;
    width: 100%;
    margin-bottom: 30px;
    /* border: 1px solid black; */
    .user {
      width: 100%;
      display: flex;
      margin-bottom: 16px;
      .userIconBackground {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 40px;
        height: 40px;
        border-radius: 50%;
        background-color: #dcdcdc;
        overflow: hidden;
        margin-right: 10px;
        .userIcon {
          margin-top: 5;
          font-size: 27px;
          color: white;
        }
      }
      .userInfo {
        flex-grow: 1;
        height: 100%;
        .username {
          font-size: 17px;
          margin-bottom: 7px;
        }
        .createAt {
          font-size: 13px;
          color: ${(props) => props.theme.gray};
        }
      }
    }
    .review {
      font-weight: 300;
      line-height: 24px;
    }
  }
  .more {
    margin-bottom: 40px;
    padding: 10px 20px;
    font-size: 18px;
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
  .more:hover {
    border: 1px solid ${(props) => props.theme.yellow};
    color: ${(props) => props.theme.yellow};
    background-color: white;
  }
  @media (max-width: 1128px) {
    .reviewListItem {
      padding-right: 40px;
    }
  }
  @media (max-width: 744px) {
    .reviewListItem {
      padding-right: 20px;
    }
  }
`;
