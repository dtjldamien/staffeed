import Controller from '@ember/controller';
import { action } from '@ember/object';
import { tracked } from '@glimmer/tracking';
import { inject as service } from '@ember/service';

export default class LoginController extends Controller {
  @service('auth-manager') authManager;

  @tracked username = '';
  @tracked password = '';

  @action
  authenticate() {
    this.authManager
      .authenticate(this.username, this.password)
      .then((res) => {
        this.transitionToRoute('index');
      })
      .catch((err) => {
        console.log('Error obtaining token: ' + err.response.status);
      });
  }
}
