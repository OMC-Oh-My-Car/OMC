// import React from "react";
import { InputContainer } from './SignUpInputLongBox.style';

const SignUpInputLongBox = ({ labelName, inputId, inputType, name, onChangeInput, value, placeholder }) => {
  return (
    <>
      <InputContainer>
        <label className="inputLabel" htmlFor={inputId}>
          {labelName}
        </label>
        <input
          name={name}
          className="inputBox"
          id={inputId}
          type={inputType}
          value={value}
          onChange={onChangeInput}
          placeholder={placeholder}
        ></input>
      </InputContainer>
    </>
  );
};

export default SignUpInputLongBox;
