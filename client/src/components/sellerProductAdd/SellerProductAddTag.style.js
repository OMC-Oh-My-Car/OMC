import styled from 'styled-components';

export const SellerProductAddTagArea = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 40px;
  h2 {
    width: 100%;
    max-width: 520px;
    font-size: 20px;
    font-weight: 700;
    margin-bottom: 20px;
  }
`;

export const TagsInput = styled.div`
  display: flex;
  align-items: flex-start;
  flex-wrap: wrap;
  min-height: 48px;
  width: 100%;
  max-width: 520px;
  padding: 0 8px;
  border: 1px solid ${(props) => props.theme.gray};
  > ul {
    display: flex;
    flex-wrap: wrap;
    padding: 0;
    margin: 8px 0 0 0;
    > .tag {
      width: auto;
      height: 32px;
      display: flex;
      align-items: center;
      justify-content: center;
      color: #fff;
      padding: 0px 8px;
      font-size: 14px;
      list-style: none;
      border-radius: 6px;
      margin: 0 8px 8px 0;
      background: ${(props) => props.theme.yellow};
      > .tag-close-icon {
        display: block;
        width: 16px;
        height: 16px;
        line-height: 16px;
        text-align: center;
        font-size: 14px;
        margin-left: 8px;
        color: ${(props) => props.theme.yellow};
        border-radius: 50%;
        background: #fff;
        cursor: pointer;
      }
    }
  }
  > input {
    flex: 1;
    border: none;
    height: 46px;
    font-size: 17px;
    padding: 4px 0 0 0;
    outline: none;
  }
`;
