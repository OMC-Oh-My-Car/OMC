/* dummy */
import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  onModal: false,
  type: '',
};

export const modalSlice = createSlice({
  name: 'modal',
  initialState,
  reducers: {
    openModal: (state, payload) => {
      return {
        ...state,
        onModal: true,
        type: payload.type,
      };
    },
    closeModal: (state) => {
      return {
        ...state,
        onModal: false,
        type: '',
      };
    },
  },
});

// Action creators are generated for each case reducer function
export const { openModal, closeModal } = modalSlice.actions;

export default modalSlice.reducer;
