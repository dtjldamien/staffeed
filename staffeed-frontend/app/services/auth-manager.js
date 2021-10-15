export default Ember.Service.extend({
  accessToken: null,

  authenticate(login, password) {
    let userData = {
      login: this.login,
      password: this.password,
    };
    let fetchObject = {
      method: 'POST',
      headers: {
        'Content-type': 'application/json',
      },
      body: JSON.stringify(userData),
    };
    fetch('https://gara6.bg/auto-api/users/login', fetchObject).then(
      (result) => {
        this.set('accessToken', result.access_token);
      }
    );
  },

  invalidate() {
    this.set('accessToken', null);
  },

  isAuthenticated: Ember.computed.bool('accessToken'),
});
