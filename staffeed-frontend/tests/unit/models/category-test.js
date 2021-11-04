import { module, skip } from 'qunit';
import { setupTest } from 'ember-qunit';

module('Unit | Model | category', function (hooks) {
  setupTest(hooks);

  // TODO: own test for category model.
  skip('it exists', function (assert) {
    let store = this.owner.lookup('service:store');
    let model = store.createRecord('category', {});
    assert.ok(model);
  });
});
