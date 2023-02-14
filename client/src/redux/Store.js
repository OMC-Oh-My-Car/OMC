import { configureStore } from '@reduxjs/toolkit';
import counter from './slice/CounterSlice';
import modal from './slice/ModalSlice';
import user from './slice/UserInfo';

export const store = configureStore({
  reducer: {
    counter: counter,
    modal: modal,
    user: user,
  },
});
