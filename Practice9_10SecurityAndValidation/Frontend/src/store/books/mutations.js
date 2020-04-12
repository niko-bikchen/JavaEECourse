export function SET_BOOKS(state, books) {
  state.books = [...books];
}

export function CLEAR_BOOKS(state) {
  state.books = [];
}
