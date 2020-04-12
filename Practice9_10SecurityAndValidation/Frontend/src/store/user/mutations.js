export function SET_FAVOURITE_BOOKS(state, books) {
  state.favouriteBooks = [...books];
}

export function ADD_BOOK_TO_FAVOURITES(state, book) {
  state.favouriteBooks.push(book);
}

export function REMOVE_BOOK_FROM_FAVOURITES(state, bookIsbn) {
  state.favouriteBooks = state.favouriteBooks.filter(book => book.isbn !== bookIsbn);
}

export function SET_USER(state, userData) {
  state.userData = { ...userData };
}

export function AUTHENTICATE_USER(state) {
  state.isAuthenticated = true;
}

export function LOG_USER_OUT(state) {
  state.isAuthenticated = false;
  state.userData = {};
  state.favouriteBooks = [];
}
