import { module, test } from 'qunit';
import { setupTest } from 'ember-qunit';

module('Unit | Route | pulse', function (hooks) {
  setupTest(hooks);

  test('it exists', function (assert) {
    let route = this.owner.lookup('route:pulse');
    assert.ok(route);
  });
});
