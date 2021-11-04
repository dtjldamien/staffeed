import Controller from '@ember/controller';
import { action } from '@ember/object';
import { tracked } from '@glimmer/tracking';
import { inject as service } from '@ember/service';

export default class LoginController extends Controller {
  @service('auth-manager') authManager;
  @service('router') router;

  @tracked username = '';
  @tracked password = '';

  @action
  authenticate() {
    this.authManager
      .authenticate(this.username, this.password)
      .then(() => {
        this.router.transitionTo('index');
      })
      .catch((err) => {
        console.log('Error obtaining token: ' + err);
      });
  }
}
