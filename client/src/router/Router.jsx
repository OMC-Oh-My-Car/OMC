// import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import LandingPage from '../pages/LandingPage';

export default function Router() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route index element={<LandingPage />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}
