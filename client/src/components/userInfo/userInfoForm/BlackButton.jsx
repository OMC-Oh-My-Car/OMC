// import React from "react";
import { BlackButtonStyle } from './BlackButton.style';

const BlackButton = ({ width, height, text }) => {
  return (
    <>
      <BlackButtonStyle width={width} height={height}>
        {text}
      </BlackButtonStyle>
    </>
  );
};
export default BlackButton;
