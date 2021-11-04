import Route from '@ember/routing/route';
import { inject as service } from '@ember/service';
import axios from 'axios';

export default class SurveyRoute extends Route {
  @service('auth-manager') authManager;

  async model() {
    const client = axios.create({
      baseURL: 'http://localhost:8080',
      headers: {
        Authorization: `Bearer ${this.authManager.accessToken}`,
      },
    });

    let { data: questions } = await client.get('/questions');

    return {
      questions,
    };
  }
}
