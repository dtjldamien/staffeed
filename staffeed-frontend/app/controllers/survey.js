import Controller from '@ember/controller';
import { inject as service } from '@ember/service';
import { tracked } from '@glimmer/tracking';
import { action } from '@ember/object';
import axios from 'axios';

export default class SurveyController extends Controller {
  // @service('auth-manager') authManager;

  // @tracked questions = [];

  // @action
  // getQuestions() {
  //   axios.get('/api/questions')
  //     .then(response => {
  //       this.set('questions', response.data);
  //     })
  //     .catch(error => {
  //       console.log(error);
  //     });
  //   }
}
