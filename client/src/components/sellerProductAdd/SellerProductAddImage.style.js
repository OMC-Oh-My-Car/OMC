import styled from 'styled-components';

export const SellerProductAddImageArea = styled.div`
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
    img {
      width: 100px;
      height: 100px;
      border: 1px solid ${(props) => props.theme.gray};
    }
  }
  .imageUpload {
    display: none;
  }
`;
