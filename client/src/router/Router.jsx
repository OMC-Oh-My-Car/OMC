// import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
// import LandingPage from '../pages/LandingPage';
import ProductListPage from '../pages/ProductListPage';
import ProductDetailPage from '../pages/ProductDetailPage';
import SignIn from '../components/signIn/SignIn';
import UserReservationPage from '../pages/UserReservationPage';

export default function Router() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route index element={<ProductListPage />} />
          <Route path="/product/:id" element={<ProductDetailPage />} />
          <Route path="/signin" element={<SignIn />} />
          <Route path="/user/:id/reservation" element={<UserReservationPage />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}
