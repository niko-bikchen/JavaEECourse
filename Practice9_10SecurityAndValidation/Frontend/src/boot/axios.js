import Vue from 'vue';
import axios from 'axios';

axios.defaults.baseURL = window.location.origin;

Vue.prototype.$axios = axios;
