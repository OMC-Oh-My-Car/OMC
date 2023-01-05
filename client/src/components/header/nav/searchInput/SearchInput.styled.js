import styled from 'styled-components';

export const SearchInputArea = styled.div`
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-grow: 1;
  max-width: ${(props) => (props.type === 'short' ? '520px' : '670px')};
  height: 100%;
  /* border: 1px solid black; */
  input {
    width: 100%;
    height: 100%;
    font-size: 17px;
    font-weight: 500;
    padding: 0.6em 1.2em;
    border-radius: 25px;
    outline: none;
    border: 2px solid #94989c;
    /* box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.1), 0 1px 4px hsla(0, 0%, 0%, 0.1), 0 2px 8px hsla(0, 0%, 0%, 0.1); */
  }
  Input:focus {
    border: 1px solid #53baff;
    outline: 3px solid #b5deff;
  }
  .searchIconArea {
    position: absolute;
    right: 15px;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 30px;
    height: 30px;
    border-radius: 50%;
    background-color: ${(props) => props.theme.red};
    .searchIcon {
      position: absolute;
      color: white;
      cursor: pointer;
    }
  }
`;
