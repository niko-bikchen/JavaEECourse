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
                mask="###-#-##-######-#"
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
              🔍 Search books 🔍
            </span>
          </q-card-section>
          <q-card-section>
            <q-form class="q-gutter-md" @submit="searchBooks">
              <q-select
                v-model="criteria.searchCriteria"
                :options="filterOptions"
                :rules="validation.notEmpty"
                label="Search criteria"
                outlined
                :readonly="ajaxActive"
              ></q-select>
              <q-input
                v-model="criteria.searchInput"
                :rules="validation.notEmpty"
                label="Search input"
                outlined
                :readonly="ajaxActive"
              ></q-input>
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
            <template v-slot:body-cell-actions="props">
              <q-td :props="props">
                <template v-if="userAuthenticated">
                  <template v-if="!isInFavourites(props) || favouriteBooks.length === 0">
                    <q-btn
                      dense
                      push
                      rounded
                      color="green-5"
                      @click="addToFavourites(props)"
                      :icon="icons.plusSolid"
                    >
                      <q-tooltip
                        anchor="center left"
                        self="center right"
                        :offset="[10, 10]"
                        content-class="bg-accent"
                        >Add to Favourites</q-tooltip
                      >
                    </q-btn>
                  </template>
                  <template v-if="isInFavourites(props)">
                    <q-btn
                      dense
                      push
                      rounded
                      color="red-5"
                      @click="removeFromFavourites(props)"
                      :icon="icons.minusSolid"
                    >
                      <q-tooltip
                        anchor="center left"
                        self="center right"
                        :offset="[10, 10]"
                        content-class="bg-accent"
                        >Remove from Favourites</q-tooltip
                      >
                    </q-btn>
                  </template>
                </template>
                <template v-else>
                  <div>
                    Sign In to be able to add books to favourites
                  </div>
                </template>
              </q-td>
            </template>
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
      validation: {},
      icons: {},
      book: {
        title: '',
        author: '',
        isbn: ''
      },
      search: [],
      criteria: {
        searchInput: '',
        searchCriteria: ''
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
        },
        {
          name: 'actions',
          required: true,
          label: 'Actions',
          field: '',
          align: 'center',
          sortable: false
        }
      ],
      filterOptions: ['title', 'isbn']
    };
  },
  computed: {
    books() {
      return this.$store.getters['booksStore/getBooks'];
    },
    favouriteBooks() {
      return this.$store.getters['userStore/getFavouriteBooks'];
    },
    userAuthenticated() {
      return this.$store.getters['userStore/userAuthenticated'];
    }
  },
  methods: {
    addBook() {
      this.ajaxActive1 = true;
      this.$store
        .dispatch('booksStore/addBook', this.book)
        .then(response => {
          this.ajaxActive1 = false;

          if (response.status === 201) {
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
        .dispatch('booksStore/searchBooks', this.criteria)
        .then(response => {
          this.ajaxActive = false;

          if (response.status === 200) {
            this.notifySuccess(response.message);
            this.search = [...response.data.books];
            this.showResult = true;
          } else {
            this.notifyError(response.message);
          }
        })
        .catch(error => {
          this.ajaxActive = false;
          this.notifyError(error.message);
        });
    },
    addToFavourites(tableData) {
      this.$store
        .dispatch('userStore/addBookToFavourites', tableData.row)
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
    },
    removeFromFavourites(tableData) {
      this.$store
        .dispatch('userStore/removeBookFromFavourites', tableData.row)
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
    },
    isInFavourites(tableData) {
      return this.favouriteBooks.filter(book => book.isbn === tableData.row.isbn).length;
    }
  },
  created() {
    this.$store
      .dispatch('booksStore/getAllBooks')
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

    const bookValidators = this.$store.getters['validationStore/getBookValidators'];
    const generalValidators = this.$store.getters['validationStore/getGeneralValidators'];

    this.validation.title = bookValidators.title;
    this.validation.author = bookValidators.author;
    this.validation.isbn = bookValidators.isbn;
    this.validation.notEmpty = generalValidators.notEmpty;

    const icons = this.$store.getters['iconsStore/getIcons'];

    this.icons.plusSolid = icons.plusSolid;
    this.icons.minusSolid = icons.minusSolid;
  }
};
</script>
