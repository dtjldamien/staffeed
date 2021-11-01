import Controller from '@ember/controller';
import { inject as service } from '@ember/service';
import axios from 'axios';

export default class SurveyController extends Controller {
  @service('auth-manager') authManager;

  @tracked questions = [];

  @action
  getQuestions() {
    this.questions = axios
      .get('http://localhost:8080/questions')
      .then((response) => {
        return response.data;
      })
      .catch((err) => {
        console.log(err.response.status);
        throw err;
      });
  }
}
