import Controller from '@ember/controller';
import { inject as service } from '@ember/service';

export default class SurveyController extends Controller {
  @service('auth-manager') authManager;
}
