export function userAuthenticated(state) {
  return state.isAuthenticated;
}

export function getUserData(state) {
  return { ...state.userData };
}

export function getFavouriteBooks(state) {
  return [...state.favouriteBooks];
}
