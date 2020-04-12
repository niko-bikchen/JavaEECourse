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
            storeRelatedAction(response.data);
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

export function getAllBooks({ commit }) {
  return makeRequest(axios.get('/api/books'), {
    requestId: 'get-books',
    successStatus: 200,
    successMessage: 'Successfully fetched books from the server',
    errorMessage: 'Failed to fetch books from the server',
    storeRelatedAction: function storeAction(data) {
      commit('SET_BOOKS', data.books);
    }
  });
}

export function addBook({ commit }, book) {
  return makeRequest(axios.post('/api/books', book), {
    requestId: 'add-book',
    successStatus: 201,
    successMessage: 'Successfully added a book',
    errorMessage: 'Failed to add a book',
    storeRelatedAction: function storeAction(data) {
      commit('SET_BOOKS', data.books);
    }
  });
}

export function searchBooks(_, criteria) {
  return makeRequest(axios.post('/api/books/search', criteria), {
    requestId: 'search-books',
    successStatus: 200,
    successMessage: 'Search successfully completed',
    errorMessage: 'Failed to perform search',
    storeRelatedAction: null
  });
}
