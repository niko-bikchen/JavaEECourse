const notifyUtils = {
  data() {
    return {
      icons: {}
    };
  },
  methods: {
    notifySuccess(message, timeout = 2500) {
      this.$q.notify({
        color: 'green-5',
        textColor: 'white',
        icon: this.icons.checkSolid,
        message,
        position: 'top',
        progress: true,
        timeout
      });
    },
    notifyError(message, timeout = 2500) {
      this.$q.notify({
        color: 'red-5',
        textColor: 'white',
        icon: this.icons.exclamationTriangleSolid,
        message,
        position: 'top',
        progress: true,
        timeout
      });
    }
  },
  created() {
    const icons = this.$store.getters['iconsStore/getIcons'];
    this.icons.checkSolid = icons.checkSolid;
    this.icons.exclamationTriangleSolid = icons.exclamationTriangleSolid;
  }
};

export default notifyUtils;
