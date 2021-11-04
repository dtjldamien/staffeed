import { module, test } from 'qunit';
import { setupRenderingTest } from 'ember-qunit';
import { render } from '@ember/test-helpers';
import { hbs } from 'ember-cli-htmlbars';

module(
  'Integration | Component | productivity/stacked-bar-chart',
  function (hooks) {
    setupRenderingTest(hooks);

    test('it renders', async function (assert) {
      // Set any properties with this.set('myProperty', 'value');
      // Handle any actions with this.set('myAction', function(val) { ... });

      await render(hbs`<Productivity::StackedBarChart />`);

      // Template block usage:
      await render(hbs`
      <Productivity::StackedBarChart>
        template block text
      </Productivity::StackedBarChart>
    `);

      assert.ok(true);
    });
  }
);
