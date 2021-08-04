import { createSlice } from "@reduxjs/toolkit";

export const toolbarSlice = createSlice({
   name: "toolbar",
   initialState: {
       accountType: "",
       sessionLength: sessionStorage.length,
   } ,
   reducers: {
       updateAccountType: (state) => {
           state.accountType = sessionStorage.accountType;
       },
       updateLength: (state) => {
           state.sessionLength = sessionStorage.length;
       }
   }
});

export const { updateAccountType, updateLength } = toolbarSlice.actions;
export default toolbarSlice.reducer;