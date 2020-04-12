<template>
  <q-layout view="hHh LpR fff">
    <q-header>
      <q-toolbar>
        <q-toolbar-title>Simple Books List</q-toolbar-title>
        <template v-if="!userAuthenticated">
          <q-btn class="bg-white text-primary" @click="showSignInDialog = true">Sign In</q-btn>
          <q-btn outline class="bg-transparent text-white q-ml-sm" @click="showSignUpDialog = true"
            >Sign Up
          </q-btn>
        </template>
        <template v-else>
          <div class="q-mr-md text-h6">{{ userData.login }}</div>
          <q-btn push class="bg-white text-primary" @click="showFavourites = true">
            Favourite Books
          </q-btn>
          <q-btn push class="bg-red-5 text-white q-ml-md" @click="logOut">Log Out</q-btn>
        </template>
      </q-toolbar>
      <q-dialog v-model="showSignInDialog">
        <q-card style="width: 50%">
          <q-card-section class="row items-center q-pb-none">
            <div class="col-12 text-right">
              <q-btn class="q-mr-lg" icon="close" flat round dense v-close-popup></q-btn>
            </div>
            <div class="col-12 text-center">
              <h5>Sign In</h5>
            </div>
          </q-card-section>

          <q-card-section>
            <q-form class="q-gutter-lg">
              <q-input
                v-model="userSignInData.login"
                type="text"
                label="Login"
                maxlength="20"
                :rules="validation.login"
                :readonly="ajaxActive"
                lazy-rules
                outlined
                counter
              ></q-input>
              <q-input
                v-model="userSignInData.password"
                type="password"
                label="Password"
                maxlength="100"
                :rules="validation.password"
                :readonly="ajaxActive"
                lazy-rules
                outlined
                counter
              ></q-input>
              <div class="text-center">
                <q-btn
                  type="submit"
                  color="primary"
                  label="Submit"
                  :loading="ajaxActive"
                  @click="signIn"
                  push
                ></q-btn>
              </div>
            </q-form>
          </q-card-section>
        </q-card>
      </q-dialog>
      <q-dialog v-model="showSignUpDialog">
        <q-card style="width: 50%">
          <q-card-section class="row items-center q-pb-none">
            <div class="col-12 text-right">
              <q-btn class="q-mr-lg" icon="close" flat round dense v-close-popup></q-btn>
            </div>
            <div class="col-12 text-center">
              <h5>Sign Up</h5>
            </div>
          </q-card-section>

          <q-card-section>
            <q-form class="q-gutter-lg">
              <q-input
                v-model="userSignUpData.login"
                type="text"
                label="Login"
                maxlength="20"
                :rules="validation.login"
                :readonly="ajaxActive"
                lazy-rules
                outlined
                counter
              ></q-input>
              <q-input
                v-model="userSignUpData.password"
                type="password"
                label="Password"
                maxlength="100"
                :rules="validation.password"
                :readonly="ajaxActive"
                lazy-rules
                outlined
                counter
              ></q-input>
              <div class="text-center">
                <q-btn
                  type="submit"
                  color="primary"
                  label="Submit"
                  :loading="ajaxActive"
                  @click="signUp"
                  push
                ></q-btn>
              </div>
            </q-form>
          </q-card-section>
        </q-card>
      </q-dialog>
      <q-dialog v-model="showFavourites">
        <q-card>
          <q-card-section class="row items-center q-pb-none">
            <q-btn icon="close" flat round dense v-close-popup />
          </q-card-section>

          <q-card-section>
            <q-table
              :columns="columns"
              :data="favouriteBooks"
              title="⭐ Favourite books ⭐"
              row-key="isbn"
            >
            </q-table>
          </q-card-section>
        </q-card>
      </q-dialog>
    </q-header>
    <q-page-container>
      <router-view></router-view>
    </q-page-container>
    <q-footer>
      <q-toolbar>
        <q-toolbar-title>BooksList & Co.</q-toolbar-title>
      </q-toolbar>
    </q-footer>
  </q-layout>
</template>

<script>
import notifyUtils from '../mixins/notify';

export default {
  name: 'MainLayout',
  mixins: [notifyUtils],
  data() {
    return {
      showFavourites: false,
      showSignInDialog: false,
      showSignUpDialog: false,
      validation: {},
      ajaxActive: false,
      userSignInData: {
        login: '',
        password: '',
        activeRole: 'ROLE_USER'
      },
      userSignUpData: {
        login: '',
        password: '',
        activeRole: 'ROLE_USER'
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
      ]
    };
  },
  computed: {
    userAuthenticated() {
      return this.$store.getters['userStore/userAuthenticated'];
    },
    userData() {
      return this.$store.getters['userStore/getUserData'];
    },
    favouriteBooks() {
      return this.$store.getters['userStore/getFavouriteBooks'];
    }
  },
  methods: {
    signIn() {
      this.ajaxActive = true;
      this.$store
        .dispatch('userStore/signIn', this.userSignInData)
        .then(response => {
          this.ajaxActive = false;

          if (response.status === 200) {
            this.showSignInDialog = false;
            this.userSignInData.login = '';
            this.userSignInData.password = '';

            this.notifySuccess(response.message);
          } else {
            this.notifyError(response.message);
          }
        })
        .catch(error => {
          this.ajaxActive = false;
          this.notifyError(error.message);
        });
    },
    signUp() {
      this.ajaxActive = true;
      this.$store
        .dispatch('userStore/signUp', this.userSignUpData)
        .then(response => {
          this.ajaxActive = false;

          if (response.status === 201) {
            this.showSignUpDialog = false;
            this.userSignUpData.password = '';
            this.userSignUpData.login = '';

            this.notifySuccess(response.message);
          } else {
            this.notifyError(response.message);
          }
        })
        .catch(error => {
          this.ajaxActive = false;
          this.notifyError(error.message);
        });
    },
    logOut() {
      this.$store
        .dispatch('userStore/logOut')
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
  },
  created() {
    const userValidators = this.$store.getters['validationStore/getUserValidators'];
    this.validation.login = userValidators.login;
    this.validation.password = userValidators.password;
  }
};
</script>
