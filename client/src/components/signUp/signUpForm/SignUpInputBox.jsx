// import React from "react";
import { InputContainer } from './SignUpInputBox.style';

const SignUpInputBox = ({ labelName, inputId, inputType, name, onChangeInput, value, placeholder }) => {
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

export default SignUpInputBox;
