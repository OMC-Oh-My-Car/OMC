/* dummy */
import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  productId: '',
  phone: '',
  startDate: '',
  endDate: '',
};

export const reservationSlice = createSlice({
  name: 'reservation',
  initialState,
  reducers: {
    setReservation: (state, payload) => {
      console.log(payload.payload);
      return {
        ...state,
        productId: payload.payload.productId,
        phone: payload.payload.phone,
        startDate: payload.payload.startDate,
        endDate: payload.payload.endDate,
      };
    },
    clearReservation: (state) => {
      return {
        ...state,
        productId: '',
        phone: '',
        startDate: '',
        endDate: '',
      };
    },
  },
});

// Action creators are generated for each case reducer function
export const { setReservation, clearReservation } = reservationSlice.actions;

export default reservationSlice.reducer;
