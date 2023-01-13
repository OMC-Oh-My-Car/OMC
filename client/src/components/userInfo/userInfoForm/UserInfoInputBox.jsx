// import React from "react";'
import { InputContainer } from './UserInfoInputBox.style';

const UserInfoInputBox = ({ labelName, inputId, inputType, name, onChangeInput, value, text }) => {
  return (
    <>
      <InputContainer>
        <label className="inputLabel" htmlFor={inputId}>
          {labelName}
        </label>
        <div name={name} className="inputBox" id={inputId} type={inputType} value={value} onChange={onChangeInput}>
          {text}
        </div>
      </InputContainer>
    </>
  );
};
export default UserInfoInputBox;
