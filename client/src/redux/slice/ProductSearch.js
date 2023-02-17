/* dummy */
import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  q: '',
};

export const searchSlice = createSlice({
  name: 'search',
  initialState,
  reducers: {
    setSearch: (state, payload) => {
      return {
        ...state,
        q: payload.payload,
      };
    },
    clearSearch: (state) => {
      return {
        ...state,
        q: '',
      };
    },
  },
});

// Action creators are generated for each case reducer function
export const { setSearch, clearSearch } = searchSlice.actions;

export default searchSlice.reducer;
