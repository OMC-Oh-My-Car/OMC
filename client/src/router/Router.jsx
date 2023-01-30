// import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
// import LandingPage from '../pages/LandingPage';
import ProductListPage from '../pages/ProductListPage';
import ProductDetailPage from '../pages/ProductDetailPage';
import SignIn from '../components/signIn/SignIn';
import UserReservationPage from '../pages/UserReservationPage';
import SellerProductListPage from '../pages/SellerProductListPage';
import SellerReservationPage from '../pages/SellerReservationPage';
import SellerProductAddPage from '../pages/SellerProductAddPage';
import Modal from '../components/modal/Modal';
import { useSelector, useDispatch } from 'react-redux';
import { openModal, closeModal } from '../redux/slice/ModalSlice';

const Router = () => {
  const onModal = useSelector((state) => state.modal.onModal);
  console.log(onModal);
  const dispatch = useDispatch();

  const closeModalController = () => {
    dispatch(closeModal());
  };
  const openModalController = (type) => {
    dispatch(openModal(type));
  };

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route index element={<ProductListPage />} />
          <Route
            path="/product/:id"
            element={
              <ProductDetailPage
                closeModalController={closeModalController}
                openModalController={openModalController}
              />
            }
          />
          <Route path="/signin" element={<SignIn />} />
          <Route path="/user/:id/reservation" element={<UserReservationPage />} />
          <Route path="/seller/:id/product" element={<SellerProductListPage />} />
          <Route path="/seller/:id/product/add" element={<SellerProductAddPage />} />
          <Route path="/seller/:id/product/:id/edit" element={<SellerProductListPage />} />
          <Route path="/seller/:id/product/:id/reservation" element={<SellerReservationPage />} />
        </Routes>
        {onModal && <Modal closeModalController={closeModalController} />}
      </BrowserRouter>
    </>
  );
};

export default Router;
