import styled from 'styled-components';

export const ProductInfoArea = styled.div`
  position: relative;
  display: flex;
  justify-content: space-between;
  width: 100%;
  margin: 30px 0px;
  padding-bottom: 30px;
  margin-bottom: 30px;
  border-bottom: 1px solid black;
  .productInfo {
    display: flex;
    flex-direction: column;
    width: 65%;
    h1 {
      font-size: 28px;
      font-weight: 700;
      margin-bottom: 10px;
    }
    .prductDescription {
      width: 100%;
      font-size: 15px;
      padding-bottom: 30px;
      margin-bottom: 30px;
      border-bottom: 1px solid ${(props) => props.theme.gray};
    }
    .starIcon {
      margin-right: 5px;
    }
    .productInfoReviewCount {
      text-decoration: underline;
      cursor: pointer;
    }
    .productInfoPlace {
      text-decoration: underline;
      cursor: pointer;
    }
    .content {
      /* display: flex; */
      /* flex-wrap: wrap; */
      width: 100%;
      padding-bottom: 30px;
      margin-bottom: 30px;
      border-bottom: 1px solid ${(props) => props.theme.gray};
      span {
        font-weight: 300;
        text-overflow: ellipsis;
        line-height: 24px;
        overflow: hidden;
      }
    }
    .facilities {
      width: 100%;
      h2 {
        font-size: 24px;
        font-weight: 700;
        margin-bottom: 10px;
      }
      .facilityList {
        display: flex;
        flex-wrap: wrap;
        width: 100%;
        margin-bottom: 20px;
        .facilityLeft {
          width: 50%;
        }
        .facilityRight {
          width: 50%;
        }
        .facilityItem {
          display: flex;
          align-items: center;
          width: 50%;
          font-size: 18px;
          font-weight: 300;
          min-height: 40px;
        }
      }
      .more {
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
    }
  }
  .reservation {
    /* margin-left: 70px; */
    width: 30%;
    height: 400px;
    border: 1px solid black;
    position: sticky !important;
    top: var(--navigation-bar-offset, 200px) !important;
  }
  @media (max-width: 1128px) {
    .facilityList {
      flex-direction: column;
    }
    .facilityLeft {
      width: 100%;
    }
    .facilityRight {
      display: none;
    }
  }
`;
