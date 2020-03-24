<template>
  <q-page padding="">
    <div class="row">
      <div class="col-12 col-md-6">
        <q-card flat="">
          <q-card-section class="text-center">
            <span class="text-h5">📙 Add a New Book 📗</span>
          </q-card-section>
          <q-card-section>
            <q-form class="q-gutter-md" @submit="addBook">
              <q-input
                v-model="book.title"
                :rules="validation.title"
                type="text"
                label="Title"
                outlined
                lazy-rules
                :readonly="ajaxActive1"
              ></q-input>
              <q-input
                v-model="book.author"
                :rules="validation.author"
                type="text"
                label="Author"
                outlined
                lazy-rules
                :readonly="ajaxActive1"
              ></q-input>
              <q-input
                v-model="book.isbn"
                :rules="validation.isbn"
                type="text"
                label="ISBN"
                outlined
                lazy-rules
                :readonly="ajaxActive1"
              ></q-input>
              <div class="text-center">
                <q-btn
                  class="bg-primary text-white"
                  type="submit"
                  label="Submit"
                  push
                  :loading="ajaxActive1"
                ></q-btn>
              </div>
            </q-form>
          </q-card-section>
        </q-card>
      </div>
      <div class="col-12 col-md-6">
        <q-card flat="">
          <q-card-section class="text-center">
            <span class="text-h5">
              Search books
            </span>
          </q-card-section>
          <q-card-section>
            <q-form class="q-gutter-md" @submit="searchBooks">
              <q-input
                v-model="filter.filterInput"
                :rules="validation.notEmpty"
                label="Search input"
                outlined
                :readonly="ajaxActive"
              ></q-input>
              <q-select
                v-model="filter.filterCriteria"
                :options="filterOptions"
                :rules="validation.notEmpty"
                label="Search criteria"
                outlined
                :readonly="ajaxActive"
              ></q-select>
              <div class="text-center">
                <q-btn
                  class="bg-primary text-white"
                  type="submit"
                  label="Submit"
                  push
                  :loading="ajaxActive"
                ></q-btn>
              </div>
            </q-form>
          </q-card-section>
        </q-card>
        <q-dialog v-model="showResult">
          <q-card>
            <q-card-section class="row items-center q-pb-none">
              <q-btn icon="close" flat round dense v-close-popup />
            </q-card-section>

            <q-card-section>
              <q-table
                :columns="columns"
                :data="search"
                title="📚 Search results 📚"
                row-key="isbn"
              >
              </q-table>
            </q-card-section>
          </q-card>
        </q-dialog>
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <div class="q-my-xl">
          <q-table :columns="columns" :data="books" title="📚 Available books 📚" row-key="isbn">
          </q-table>
        </div>
      </div>
    </div>
  </q-page>
</template>

<script>
import notifyUtils from 'src/mixins/notify.js';

export default {
  name: 'Index',
  mixins: [notifyUtils],
  data() {
    return {
      showResult: false,
      ajaxActive: false,
      ajaxActive1: false,
      validation: {
        author: [
          value =>
            /^[a-zA-Z]+ [a-zA-Z]+$/.test(value) ||
            'Author name should consist of two words separated by a whitespace. Author name cannot contain any symbols or numbers'
        ],
        title: [value => value !== '' || 'Book title cannot be empty'],
        isbn: [
          value => /^[0-9]+$/.test(value) || 'ISBN can contain numbers only',
          value => value !== '' || 'ISBN cannot be empty'
        ],
        notEmpty: [value => value !== '' || 'Field cannot be empty']
      },
      book: {
        title: '',
        author: '',
        isbn: ''
      },
      search: [],
      filter: {
        filterInput: '',
        filterCriteria: ''
      },
      columns: [
        {
          name: 'title',
          required: true,
          align: 'left',
          label: 'Book title',
          field: row => row.title,
          sortable: true
        },
        {
          name: 'author',
          required: true,
          align: 'left',
          label: 'Book author',
          field: row => row.author,
          sortable: true
        },
        {
          name: 'isbn',
          required: true,
          align: 'left',
          label: 'Book ISBN',
          field: row => row.isbn,
          sortable: true
        }
      ],
      filterOptions: ['title', 'isbn']
    };
  },
  computed: {
    books() {
      return this.$store.getters['books/getBooks'];
    }
  },
  methods: {
    addBook() {
      this.ajaxActive1 = true;
      this.$store
        .dispatch('books/addBook', this.book)
        .then(response => {
          this.ajaxActive1 = false;

          if (response.status === 200) {
            this.notifySuccess(response.message);
          } else {
            this.notifyError(response.message);
          }
        })
        .catch(error => {
          this.ajaxActive1 = false;
          this.notifyError(error.message);
        });
    },
    searchBooks() {
      this.ajaxActive = true;
      this.$store
        .dispatch('books/filterBooks', this.filter)
        .then(response => {
          this.ajaxActive = false;

          if (response.status === 200) {
            this.notifySuccess(response.message);
            this.search = [...response.books];
            this.showResult = true;
          } else {
            this.notifyError(response.message);
          }
        })
        .catch(error => {
          this.ajaxActive = false;
          this.notifyError(error.message);
        });
    }
  },
  created() {
    this.$store
      .dispatch('books/getAllBooks')
      .then(response => {
        if (response.status === 200) {
          this.notifySuccess(response.message);
        } else {
          this.notifyError(response.message);
        }
      })
      .catch(error => {
        this.notifyError(error.message);
      });
  }
};
</script>
