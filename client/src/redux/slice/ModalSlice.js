/* dummy */
import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  onModal: true,
  type: 'content',
  width: '700px',
  height: '800px',
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
        width: payload.width,
        height: payload.height,
      };
    },
    closeModal: (state) => {
      return {
        ...state,
        onModal: false,
        type: '',
        width: '0px',
        height: '0px',
      };
    },
  },
});

// Action creators are generated for each case reducer function
export const { openModal, closeModal } = modalSlice.actions;

export default modalSlice.reducer;
