// app/controllers/login.js
import Controller from '@ember/controller';
import { inject } from '@ember/service';

export default Controller.extend({
  session: inject('session'),

  actions: {
    authenticate: function() {
      const credentials = this.getProperties('username', 'password');
      const authenticator = 'authenticator:jwt'; // or 'authenticator:jwt'

      this.session.authenticate(authenticator, credentials);
    }
  }
});
