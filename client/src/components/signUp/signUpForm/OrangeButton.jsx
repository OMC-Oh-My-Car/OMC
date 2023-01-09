// import React from "react";
import { OrangeButtonStyle } from './OrangeButton.style';

const OrangeButton = ({ width, height, text }) => {
  return (
    <>
      <OrangeButtonStyle width={width} height={height}>
        {text}
      </OrangeButtonStyle>
    </>
  );
};
export default OrangeButton;
