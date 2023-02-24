// import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import ProductListPage from '../pages/ProductListPage';
import ProductDetailPage from '../pages/ProductDetailPage';
import SignInPage from '../pages/SignInPage';
import SignUpPage from '../pages/SignUpPage';
import UserReservationPage from '../pages/UserReservationPage';
import SellerProductListPage from '../pages/SellerProductListPage';
import SellerReservationPage from '../pages/SellerReservationPage';
import SellerProductAddPage from '../pages/SellerProductAddPage';
import SellerProductEditPage from '../pages/SellerProductEditPage';
import Modal from '../components/modal/Modal';
import { useSelector, useDispatch } from 'react-redux';
import { openModal } from '../redux/slice/ModalSlice';
import UserInfoPage from '../pages/UserInfoPage';
import PaymentSuccessPage from '../pages/PaymentSuccessPage';
import PaymentFailedPage from '../pages/PaymentFailedPage';
import UserInfoEditPage from '../pages/UserInfoEditPage';

const Router = () => {
  const dispatch = useDispatch();

  const isOpenModal = useSelector((state) => state.modal.onModal);

  const openModalController = (type) => {
    dispatch(openModal(type));
  };

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route index element={<ProductListPage />} />
          <Route path="/product/:productId" element={<ProductDetailPage openModalController={openModalController} />} />
          <Route path="/signin" element={<SignInPage />} />
          <Route path="/signup" element={<SignUpPage />} />
          <Route
            path="/user/:id/reservation"
            element={<UserReservationPage openModalController={openModalController} />}
          />
          <Route path="/seller/:id/product" element={<SellerProductListPage />} />
          <Route path="/seller/:id/product/add" element={<SellerProductAddPage />} />
          <Route path="/seller/:id/product/:productId/edit" element={<SellerProductEditPage />} />
          <Route
            path="/seller/:id/product/:productId/reservation"
            element={<SellerReservationPage openModalController={openModalController} />}
          />
          <Route path="/user/:id/userInfo" element={<UserInfoPage />} />
          <Route path="/user/:id/userInfo/edit" element={<UserInfoEditPage />} />
          <Route path="/payment/success" element={<PaymentSuccessPage />} />
          <Route path="/payment/failed" element={<PaymentFailedPage />} />
        </Routes>
        {isOpenModal && <Modal />}
      </BrowserRouter>
    </>
  );
};

export default Router;
