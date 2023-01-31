import styled from 'styled-components';

export const ProductInfoArea = styled.div`
  position: relative;
  display: flex;
  justify-content: space-between;
  width: 100%;
  margin: 40px 0px;
  padding-bottom: 40px;
  margin-bottom: 40px;
  border-bottom: 1px solid ${(props) => props.theme.gray};
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
      padding-bottom: 40px;
      margin-bottom: 40px;
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
      padding-bottom: 40px;
      margin-bottom: 40px;
      border-bottom: 1px solid ${(props) => props.theme.gray};
      span {
        display: -webkit-box;
        -webkit-line-clamp: 6;
        -webkit-box-orient: vertical;
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
    }
  }
  .reservation {
    padding: 20px;
    width: 30%;
    height: fit-content;
    position: sticky !important;
    top: var(--navigation-bar-offset, 200px) !important;
    border-radius: 10px;
    box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.1), 0 1px 4px hsla(0, 0%, 0%, 0.1), 0 2px 8px hsla(0, 0%, 0%, 0.1);
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
  .more {
    padding: 10px 20px;
    margin-top: 20px;
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
`;
