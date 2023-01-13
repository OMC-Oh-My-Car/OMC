// import React from "react";
import { StyledInput, StyledLabel, StyledP } from './CheckBox.style';
const CheckBox = ({ text }) => {
  return (
    <>
      <StyledLabel htmlFor={text}>
        <StyledInput type="checkbox" id={text} name={text} />
        <StyledP>{text}</StyledP>
      </StyledLabel>
    </>
  );
};
export default CheckBox;
