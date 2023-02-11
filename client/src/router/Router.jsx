// import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import ProductListPage from '../pages/ProductListPage';
import ProductDetailPage from '../pages/ProductDetailPage';
import SignIn from '../components/signIn/SignIn';
import UserReservationPage from '../pages/UserReservationPage';
import SellerProductListPage from '../pages/SellerProductListPage';
import SellerReservationPage from '../pages/SellerReservationPage';
import SellerProductAddPage from '../pages/SellerProductAddPage';
import SellerProductEditPage from '../pages/SellerProductEditPage';
import Modal from '../components/modal/Modal';
import { useSelector, useDispatch } from 'react-redux';
import { openModal } from '../redux/slice/ModalSlice';

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
          <Route path="/signin" element={<SignIn />} />
          <Route
            path="/user/:id/reservation"
            element={<UserReservationPage openModalController={openModalController} />}
          />
          <Route path="/seller/:id/product" element={<SellerProductListPage />} />
          <Route path="/seller/:id/product/add" element={<SellerProductAddPage />} />
          <Route path="/seller/:id/product/:id/edit" element={<SellerProductEditPage />} />
          <Route path="/seller/:id/product/:id/reservation" element={<SellerReservationPage />} />
        </Routes>
        {isOpenModal && <Modal />}
      </BrowserRouter>
    </>
  );
};

export default Router;
