import Service from '@ember/service';
import { bool } from '@ember/object/computed';
import classic from 'ember-classic-decorator';
import axios from 'axios';
@classic
export default class AuthManagerService extends Service {
  accessToken = null;
  userId = null;

  async authenticate(username, password) {
    let userData = {
      username: username,
      password: password,
    };

    await axios
      .post('http://localhost:8080/auth/login', userData)
      .then((res) => {
        this.set('userId', res.data.id);
        console.log(this.userId);
        this.set('accessToken', res.data.token);
      })
      .catch((err) => {
        console.log(err.response.status);
        throw err;
      });
  }

  invalidate() {
    this.set('accessToken', null);
    this.set('id', null);
  }

  isAuthenticated = bool('accessToken');
}
