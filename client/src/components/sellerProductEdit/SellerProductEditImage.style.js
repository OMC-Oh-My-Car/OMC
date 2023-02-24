import styled from 'styled-components';

export const SellerProductEditImageArea = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 40px;
  h2 {
    width: 100%;
    max-width: 500px;
    font-size: 20px;
    font-weight: 700;
    margin-bottom: 20px;
  }
  .imageViewer {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    width: 100%;
    max-width: 520px;
    height: 400px;
    border: 1px solid ${(props) => props.theme.gray};
    margin-bottom: 10px;
  }
  .uploadLabel {
    img {
      width: 200px;
      cursor: pointer;
    }
  }
  .imageSlice {
    display: flex;
    gap: 5px;
    width: 100%;
    max-width: 520px;
    overflow-x: scroll;
    overflow-y: hidden;
    /* width */
    ::-webkit-scrollbar {
      height: 10px;
    }

    /* Track */
    ::-webkit-scrollbar-track {
      background: #efdcdc;
    }

    /* Handle */
    ::-webkit-scrollbar-thumb {
      background: ${(props) => props.theme.red};
    }

    /* Handle on hover */
    ::-webkit-scrollbar-thumb:hover {
      background: #555;
    }
    .imageArea {
      position: relative;
      .closeIcon {
        position: absolute;
        top: 7px;
        left: 7px;
        font-size: 20px;
        color: white;
      }
      img {
        width: 170px;
        height: 170px;
        border: 1px solid ${(props) => props.theme.gray};
      }
    }
  }
  .imageUpload {
    display: none;
  }
`;
