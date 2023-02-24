import styled from 'styled-components';

export const ProductItemArea = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  /* align-items: center; */
  width: 100%;
  cursor: pointer;
  .imageArea {
    position: relative;
    width: 100%;
    height: calc(100% - 88px);
    font-size: 30px;
    color: rgba(255, 255, 255, 0.5);
    img {
      width: 100%;
      height: 100%;
      object-fit: fill;
      border-radius: 10px;
    }
    .circleLeftIcon {
      position: absolute;
      top: calc(50% - 15px);
      left: 20px;
      :hover {
        color: rgba(255, 255, 255, 1);
        transform: scale(1.15);
      }
    }
    .circleLeftIcon.disabled {
      display: none;
    }
    .circleRightIcon {
      position: absolute;
      top: calc(50% - 15px);
      right: 20px;
      :hover {
        color: rgba(255, 255, 255, 1);
        transform: scale(1.15);
      }
    }
    .circleRightIcon.disabled {
      display: none;
    }
    .heartIcon {
      position: absolute;
      right: 15px;
      top: 15px;
      width: 25px;
      height: 25px;
    }
  }
  .productInfo {
    position: relative;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    font-size: 15px;
    width: 100%;
    height: 73px;
    padding-top: 10px;
    /* border: 1px solid black; */
    font-family: sans-serif;
    .productTitle {
      font-weight: 700;
      margin-bottom: 8px;
    }
    .productLocation {
      color: ${(props) => props.theme.gray};
    }
    .productPrice {
      margin-top: 15px;
      .colorGray {
        font-weight: 700;
        color: ${(props) => props.theme.gray};
      }
    }
    .productGrade {
      position: absolute;
      top: 10px;
      right: 0px;
      .starIcon {
        font-size: 13px;
        margin-right: 5px;
      }
    }
  }
`;
