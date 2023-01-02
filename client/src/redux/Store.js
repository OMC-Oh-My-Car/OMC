import { configureStore } from '@reduxjs/toolkit';
import counterSlice from './slice/CounterSlice';

export const store = configureStore({
  reducer: {
    counter: counterSlice,
  },
});
