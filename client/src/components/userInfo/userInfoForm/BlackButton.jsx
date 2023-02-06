// import React from "react";
import { BlackButtonStyle } from './BlackButton.style';

const BlackButton = ({ width, height, text, onClick }) => {
  return (
    <>
      <BlackButtonStyle width={width} height={height} onClick={onClick}>
        {text}
      </BlackButtonStyle>
    </>
  );
};
export default BlackButton;
