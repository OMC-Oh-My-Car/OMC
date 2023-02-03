import { configureStore } from '@reduxjs/toolkit';
import counter from './slice/CounterSlice';
import modal from './slice/ModalSlice';

export const store = configureStore({
  reducer: {
    counter: counter,
    modal: modal,
  },
});
