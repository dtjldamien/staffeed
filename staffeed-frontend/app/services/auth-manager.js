import Service from '@ember/service';
import axios from 'axios';
export default class AuthManagerService extends Service {
  accessToken = null;

  async authenticate(username, password) {
    let userData = {
      username: username,
      password: password,
    };

    axios
      .post('http://localhost:8080/auth/login', userData)
      .then((res) => {
        console.log(res.data);
        this.set('accessToken', res.data.token);
      })
      .catch((err) => {
        console.log(err.response.status);
        throw err;
      });
  }

  invalidate() {
    this.set('accessToken', null);
  }

  isAuthenticated = Ember.computed.bool('accessToken');
}
