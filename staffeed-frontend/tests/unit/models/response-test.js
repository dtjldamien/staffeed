import { module, skip } from 'qunit';
import { setupTest } from 'ember-qunit';

module('Unit | Model | response', function (hooks) {
  setupTest(hooks);

  // TODO: own test for question model.
  skip('it exists', function (assert) {
    let store = this.owner.lookup('service:store');
    let model = store.createRecord('response', {});
    assert.ok(model);
  });
});
