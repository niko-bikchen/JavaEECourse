export function getBooks(state) {
  return [...state.books];
}

export function getIcons(state) {
  return { ...state.icons };
}
