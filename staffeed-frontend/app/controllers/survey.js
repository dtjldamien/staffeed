import Controller from '@ember/controller';
import { action } from '@ember/object';
import { inject as service } from '@ember/service';
import { tracked } from '@glimmer/tracking';

export default class SurveyController extends Controller {
  @service('router') router;
  @service('auth-manager') authManager;

  @tracked isSubmitted = false;

  get questions() {
    return this.model.questions;
  }

  @action
  submit() {
    this.isSubmitted = true;
  }
}
