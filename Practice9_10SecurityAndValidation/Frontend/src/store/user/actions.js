import axios from 'axios';

function makeRequest(
  axiosFunction,
  { requestId, successStatus, successMessage, errorMessage, storeRelatedAction }
) {
  return new Promise((resolve, reject) => {
    axiosFunction
      .then(response => {
        if (response.status === successStatus) {
          if (storeRelatedAction) {
            storeRelatedAction(response);
          }

          resolve({
            requestId,
            status: response.status,
            message: successMessage,
            customStatus: 'RF-G',
            data: response.data
          });
        } else {
          reject({
            requestId,
            status: response.status,
            message: errorMessage,
            customStatus: 'RF-B'
          });
        }
      })
      .catch(error => {
        reject({
          requestId,
          status: error.response.status,
          message: error.response.data.message,
          customStatus: 'RNF-E',
          error
        });
      });
  });
}

export function signIn({ commit }, userData) {
  return makeRequest(
    axios.post('/api/auth/sign-in', {
      login: userData.login,
      password: userData.password,
      activeRole: userData.activeRole
    }),
    {
      requestId: 'sign-in',
      successStatus: 200,
      successMessage: 'Successfully signed in',
      errorMessage: 'Failed to sign in',
      storeRelatedAction: function storeAction(response) {
        commit('SET_USER', userData);
        commit('SET_FAVOURITE_BOOKS', response.data.favouriteBooks);
        commit('AUTHENTICATE_USER');

        axios.defaults.headers.common.Authorization = response.headers.authorization;
      }
    }
  );
}

export function signUp({ commit }, userData) {
  return makeRequest(
    axios.post('/api/auth/sign-up', {
      login: userData.login,
      password: userData.password,
      activeRole: userData.activeRole
    }),
    {
      requestId: 'sign-up',
      successStatus: 201,
      successMessage: 'Successfully signed up',
      errorMessage: 'Failed to sign up',
      storeRelatedAction: function storeAction(response) {
        commit('SET_USER', userData);
        commit('AUTHENTICATE_USER');

        axios.defaults.headers.common.Authorization = response.headers.authorization;
      }
    }
  );
}

export function logOut({ commit }) {
  return makeRequest(axios.post('/api/auth/log-out'), {
    requestId: 'log-out',
    successStatus: 200,
    successMessage: 'Successfully logged out',
    errorMessage: 'Failed to log out',
    storeRelatedAction: function storeAction() {
      commit('LOG_USER_OUT');

      axios.defaults.headers.common.Authorization = '';
    }
  });
}

export function addBookToFavourites({ commit }, book) {
  return makeRequest(axios.post('/api/user/fav-books', book), {
    requestId: 'add-to-fav',
    successStatus: 200,
    successMessage: 'Successfully added to favourites',
    errorMessage: 'Failed to add to favourites',
    storeRelatedAction: function storeAction() {
      commit('ADD_BOOK_TO_FAVOURITES', book);
    }
  });
}

export function removeBookFromFavourites({ commit }, book) {
  return makeRequest(axios.delete(`/api/user/fav-books/${book.bookNumber}`), {
    requestId: 'remove-from-fav',
    successStatus: 200,
    successMessage: 'Successfully removed from favourites',
    errorMessage: 'Failed to remove from favourites',
    storeRelatedAction: function storeAction() {
      commit('REMOVE_BOOK_FROM_FAVOURITES', book.isbn);
    }
  });
}

export function getFavouriteBooks({ commit }) {
  return makeRequest(axios.get('/api/user/fav-books'), {
    requestId: 'get-fav',
    successStatus: 200,
    successMessage: 'Successfully fetched favourite books',
    errorMessage: 'Failed to fetch favourite books',
    storeRelatedAction: function storeAction(response) {
      commit('SET_FAVOURITE_BOOKS', response.data.books);
    }
  });
}
