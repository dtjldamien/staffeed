import Controller from '@ember/controller';
import { action } from '@ember/object';
import { inject as service } from '@ember/service';
import { tracked } from '@glimmer/tracking';
import axios from 'axios';

export default class SurveyController extends Controller {
  @service('router') router;
  @service('auth-manager') authManager;

  @tracked isSubmitted = false;
  @tracked responses = {};

  get questions() {
    return this.model.questions;
  }

  @action
  submit() {
    this.isSubmitted = true;
    console.log(this.responses);

    const client = axios.create({
      baseURL: `http://localhost:8080/user/${this.authManager.userId}/response`,
      headers: {
        Authorization: `Bearer ${this.authManager.accessToken}`,
      },
    });

    // iterate key value responses and create survey responses
    Object.keys(this.responses).forEach((key, value) => {
      client
        .post('/', {
          questionId: key,
          response: value,
        })
        .then((res) => {
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err.response.status);
          throw err;
        });
    });
  }

  @action
  optionToggled(questionId, answer) {
    this.responses[questionId] = answer;
    console.log(this.responses);
  }
}
