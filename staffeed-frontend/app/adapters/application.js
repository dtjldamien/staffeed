// app/adapters/application.js
import JSONAPIAdapter from '@ember-data/adapter/json-api';
import { inject } from '@ember/service';
import { computed } from '@ember/object';

export default JSONAPIAdapter.extend({
  session: inject('session'),

  namespace: 'api',
  host: 'http://localhost:8080',
  authManager: Ember.inject.service(),

  headers: computed(
    'session.isAuthenticated',
    'session.data.authenticated.token',
    function () {
      if (this.session.isAuthenticated) {
        return {
          Authorization: `Bearer ${this.session.data.authenticated.token}`,
        };
      } else {
        return {};
      }
    }
  ),

  handleResponse(status) {
    if (status === 401 && this.session.isAuthenticated) {
      this.session.invalidate();
    }
    return this._super(...arguments);
  },
});
