import Service from '@ember/service';
import axios from 'axios';
export default class AuthManagerService extends Service {
  accessToken = null;

  async authenticate(username, password) {
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
    // fetch('https://gara6.bg/auto-api/users/login', fetchObject).then(
    //   (result) => {
    //     this.set('accessToken', result.access_token);
    //   }
    // );
    const response = await fetch(
      'http://localhost:8080/auth/login',
      fetchObject
    );
    const data = await response.json();
    console.log(data);
    this.set('accessToken', data.token);

    // axios.post('http://localhost:8080/auth/login', userData).then((res) => {
    //   console.log(res.data);
    //   this.set('accessToken', res.data.token);
    // });
  }

  invalidate() {
    this.set('accessToken', null);
  }

  isAuthenticated = Ember.computed.bool('accessToken');
}
