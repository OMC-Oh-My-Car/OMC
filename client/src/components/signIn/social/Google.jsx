import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';

const GoogleButton = () => {
  return (
    <GoogleOAuthProvider clientId={process.env.REACT_APP_API_URL_GOOGLE_CLIENTID}>
      <GoogleLogin
        onSuccess={(response) => {
          console.log(response);
        }}
        onError={() => {
          console.log('Login Failed');
        }}
      />
    </GoogleOAuthProvider>
  );
};

export default GoogleButton;
