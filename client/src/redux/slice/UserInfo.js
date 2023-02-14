/* dummy */
import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  email: '',
  nickname: '',
  username: '',
  type: '',
  imageUrl: '',
};

export const userInfoSlice = createSlice({
  name: 'userInfo',
  initialState,
  reducers: {
    setUserInfo: (state, payload) => {
      return {
        ...state,
        email: payload.payload.email,
        nickname: payload.payload.nickname,
        username: payload.payload.username,
        type: payload.payload.type,
        imageUrl: payload.payload.imageUrl,
      };
    },
    clearUserInfo: (state) => {
      return {
        ...state,
        email: '',
        nickname: '',
        username: '',
        type: '',
        imageUrl: '',
      };
    },
  },
});

// Action creators are generated for each case reducer function
export const { setUserInfo, clearUserInfo } = userInfoSlice.actions;

export default userInfoSlice.reducer;
