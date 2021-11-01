import Controller from '@ember/controller';

export default class SurveyController extends Controller {
  get questions() {
    return this.model.questions;
  }
}
