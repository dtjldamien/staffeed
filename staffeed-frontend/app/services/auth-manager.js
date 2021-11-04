import Service from '@ember/service';
import { bool } from '@ember/object/computed';
import classic from 'ember-classic-decorator';
import axios from 'axios';
@classic
export default class AuthManagerService extends Service {
  accessToken = null;

  async authenticate(username, password) {
    let userData = {
      username: username,
      password: password,
    };

    await axios
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

  isAuthenticated = bool('accessToken');
}
