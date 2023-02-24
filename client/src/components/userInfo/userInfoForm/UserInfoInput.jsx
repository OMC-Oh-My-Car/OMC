import { UserInfoInputArea, UserInfoDisableInputArea } from './UserInfoInput.style';

const UserInfoInput = ({ labelName, inputId, inputType, name, value, placeholder, p, disabled, onChange }) => {
  return (
    <>
      {disabled ? (
        <>
          <UserInfoDisableInputArea>
            <label htmlFor={inputId}>{labelName}</label>
            <input name={name} id={inputId} type={inputType} disabled value={value} placeholder={placeholder} />
          </UserInfoDisableInputArea>
        </>
      ) : (
        <>
          <UserInfoInputArea>
            <label htmlFor={inputId}>{labelName}</label>
            <input
              name={name}
              id={inputId}
              type={inputType}
              value={value}
              placeholder={placeholder}
              onChange={onChange}
            />
          </UserInfoInputArea>
        </>
      )}
    </>
  );
};

export default UserInfoInput;
