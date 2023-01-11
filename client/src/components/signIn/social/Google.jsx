import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';

const GoogleButton = () => {
  return (
    <GoogleOAuthProvider clientId="22249000667-f7k5t25nm5qobstfhbfc533v27gd5lpf.apps.googleusercontent.com">
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
