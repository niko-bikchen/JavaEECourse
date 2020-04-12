export function getBookValidators(state) {
  return { ...state.book };
}

export function getUserValidators(state) {
  return { ...state.user };
}

export function getGeneralValidators(state) {
  return { ...state.general };
}
