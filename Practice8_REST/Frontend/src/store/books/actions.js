import axios from 'axios';

export function getAllBooks({ commit }) {
  return new Promise((resolve, reject) => {
    axios
      .get('/books')
      .then(response => {
        if (response.status === 200) {
          commit('SET_BOOKS', response.data.books);
          resolve({
            action: 'getAllBooks',
            status: response.status,
            message: 'Books successfully fetched from the server ✌'
          });
        } else {
          reject({
            action: 'getAllBooks',
            status: response.status,
            message: 'Something went wrong while fetching books from the server 💀'
          });
        }
      })
      .catch(error => {
        reject({
          action: 'getAllBooks',
          status: error.response.status,
          message: 'Something went wrong while fetching books from the server 💀',
          error
        });
      });
  });
}

export function addBook({ commit }, book) {
  return new Promise((resolve, reject) => {
    axios
      .post('/add-book', book)
      .then(response => {
        if (response.status === 200) {
          commit('SET_BOOKS', response.data.books);
          resolve({
            action: 'addBook',
            status: response.status,
            message: 'Book successfully added ✌'
          });
        } else {
          reject({
            action: 'addBook',
            status: response.status,
            message: 'Something went wrong while adding the book 💀'
          });
        }
      })
      .catch(error => {
        reject({
          action: 'addBook',
          status: error.response.status,
          message: 'Something went wrong while adding the book 💀',
          error
        });
      });
  });
}

export function filterBooks(_, filter) {
  return new Promise((resolve, reject) => {
    axios
      .post('/filter-books', filter)
      .then(response => {
        if (response.status === 200) {
          resolve({
            action: 'filterBooks',
            status: response.status,
            message: 'Search successfully completed ✌',
            books: response.data.books
          });
        } else {
          reject({
            action: 'filterBooks',
            status: response.status,
            message: 'Something went wrong while searching books 💀'
          });
        }
      })
      .catch(error => {
        reject({
          action: 'filterBooks',
          status: error.response.status,
          message: 'Something went wrong while searching books 💀',
          error
        });
      });
  });
}
