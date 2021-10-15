export default DS.RESTAdapter.extend({
  namespace: 'api',
  host: 'http://localhost:8080',
  authManager: Ember.inject.service(),

  headers: Ember.computed('authManager.accessToken', function () {
    return {
      Authorization: `Bearer ${this.get('authManager.accessToken')}`,
    };
  }),
});
