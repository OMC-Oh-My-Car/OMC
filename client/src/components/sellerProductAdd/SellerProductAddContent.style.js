import styled from 'styled-components';

export const SellerProductAddContentArea = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 30px;
  h2 {
    width: 100%;
    max-width: 520px;
    font-size: 20px;
    font-weight: 700;
    margin-bottom: 20px;
  }
  textarea {
    width: 100%;
    max-width: 520px;
    padding: 5px;
    font-size: 17px;
    border: 1px solid ${(props) => props.theme.gray};
    outline: none;
    margin-bottom: 10px;
  }
  .count {
    width: 100%;
    max-width: 520px;
    display: flex;
    justify-content: flex-end;
    span {
      color: ${(props) => props.theme.gray};
    }
  }
`;
