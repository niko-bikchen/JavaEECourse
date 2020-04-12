import Vue from 'vue';
import Vuex from 'vuex';

import books from './books';
import user from './user';
import icons from './icons';
import validation from './validation';

Vue.use(Vuex);

/*
 * If not building with SSR mode, you can
 * directly export the Store instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Store instance.
 */

export default new Vuex.Store({
  modules: {
    booksStore: books,
    userStore: user,
    iconsStore: icons,
    validationStore: validation
  },

  // enable strict mode (adds overhead!)
  // for dev mode only
  strict: process.env.DEV
});
