// import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
// import LandingPage from '../pages/LandingPage';
import SignIn from '../components/signIn/SignIn';
export default function Router() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route index element={<SignIn />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}
