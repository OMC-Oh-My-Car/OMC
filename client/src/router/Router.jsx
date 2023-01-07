// import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
// import LandingPage from '../pages/LandingPage';
import ProductListPage from '../pages/ProductListPage';

export default function Router() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route index element={<ProductListPage />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}
