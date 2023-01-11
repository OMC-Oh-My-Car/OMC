import { GoogleLogin } from '@react-oauth/google';

const GoogleButton = () => {
  return (
    <GoogleLogin
      onSuccess={(response) => {
        console.log(response);
      }}
      onError={() => {
        console.log('Login Failed');
      }}
    />
  );
};

export default GoogleButton;
