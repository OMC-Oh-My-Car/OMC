// import React from "react";
import { InfoChangeContainer } from './InfoChange.style';

const InfoChange = ({ labelName, inputId, inputType, name, onChangeInput, value, placeholder }) => {
  return (
    <>
      <InfoChangeContainer>
        <div className="inputChangeLabelContainer">
          <label className="inputChangeLabel" htmlFor={inputId}>
            {labelName}
          </label>
        </div>
        <input
          className="inputChangeInput"
          name={name}
          id={inputId}
          type={inputType}
          value={value}
          onChange={onChangeInput}
          placeholder={placeholder}
        ></input>
      </InfoChangeContainer>
    </>
  );
};
export default InfoChange;
