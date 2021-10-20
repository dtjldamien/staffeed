import Service from '@ember/service';

export default class AuthManagerService extends Service {
  accessToken = null;

  authenticate(username, password) {
    let userData = {
      username: username,
      password: password,
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
  }

  invalidate() {
    this.set('accessToken', null);
  }

  isAuthenticated = Ember.computed.bool('accessToken');
}
