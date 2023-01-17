import styled from 'styled-components';

export const ProductCalendarModalArea = styled.div`
  position: absolute;
  background-color: white;
  top: 55px;
  right: -1px;
  width: 308px;
  box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.1), 0 1px 4px hsla(0, 0%, 0%, 0.1), 0 2px 8px hsla(0, 0%, 0%, 0.1);
  border-radius: 10px;
  padding: 30px 30px;
  h2 {
    font-size: 23px;
    font-weight: 700;
    margin-bottom: 20px;
  }
  span {
    font-size: 13px;
  }
  .calendar {
    display: flex;
    justify-content: center;
    margin-top: 30px;
    width: 100%;
    .react-datepicker__day--in-range {
      background-color: ${(props) => props.theme.yellow};
    }
    .react-datepicker__day--in-selecting-range {
      background-color: #ffa260;
    }
    .react-datepicker__day--selected {
      background-color: ${(props) => props.theme.yellow};
    }
    .react-datepicker__day--selecting-range-start {
      background-color: #ffa260;
    }
  }
  .close {
    position: absolute;
    right: 15px;
    top: 15px;
    padding: 5px 10px;
    font-size: 13px;
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
  .close:hover {
    border: 1px solid ${(props) => props.theme.yellow};
    color: ${(props) => props.theme.yellow};
    background-color: white;
  }
`;
