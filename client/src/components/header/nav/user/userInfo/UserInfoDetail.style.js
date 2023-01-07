import styled from 'styled-components';

export const UserInfoDetailArea = styled.nav`
  z-index: 999;
  position: absolute;
  background-color: white;
  top: 62px;
  right: 0px;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 250px;
  border-radius: 8px;
  box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.1), 0 1px 4px hsla(0, 0%, 0%, 0.1), 0 2px 8px hsla(0, 0%, 0%, 0.1);
  .menu {
    width: 100%;
    height: 100%;
    padding: 5px 0px;
    border-bottom: 1px solid ${(props) => props.theme.gray};
    .menuItem {
      display: flex;
      align-items: center;
      padding: 0px 15px;
      width: 100%;
      height: 45px;
      font-size: 15px;
      cursor: pointer;
    }
    .menuItem:hover {
      background-color: #eee;
    }
  }
  .menu:last-child {
    border: none;
  }
`;
