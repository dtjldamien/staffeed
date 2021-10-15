export default Ember.Component.extend({
  authManager: Ember.inject.service(),

  actions: {
    authenticate() {
      const { login, password } = this.getProperties('login', 'password');
      this.authManager.authenticate(login, password).then(
        () => {
          alert('Success! Click the top link!');
        },
        (err) => {
          alert('Error obtaining token: ' + err.responseText);
        }
      );
    },
  },
});
