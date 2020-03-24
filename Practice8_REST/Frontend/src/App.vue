<template>
  <div id="q-app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
  created() {
    this.$q.loadingBar.setDefaults({
      color: 'green-13',
      size: '5px',
      position: 'top'
    });
  }
};
</script>
