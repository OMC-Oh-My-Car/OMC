// import React from "react";
import { OrangeButtonStyle } from './OrangeButton.style';

const OrangeButton = ({ width, height, text, onClick }) => {
  return (
    <>
      <OrangeButtonStyle width={width} height={height} onClick={onClick}>
        {text}
      </OrangeButtonStyle>
    </>
  );
};
export default OrangeButton;
