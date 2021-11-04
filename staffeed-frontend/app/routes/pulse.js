import Route from '@ember/routing/route';
import axios from 'axios';
import { inject as service } from '@ember/service';

export default class PulseRoute extends Route {
  @service('auth-manager') authManager;

  async model() {
    const client = axios.create({
      baseURL: 'http://localhost:8080',
      headers: {
        Authorization: `Bearer ${this.authManager.accessToken}`,
      },
    });

    let { data: responseRate } = await client.get('/analytics/response-rate');
    let { data: responses } = await client.get('/analytics/responses');
    let { data: responsesByCategory } = await client.get(
      '/analytics/responses/categories'
    );
    let { data: responseByDepartment } = await client.get(
      '/analytics/responses/department'
    );
    return {
      responseRate,
      responses,
      responsesByCategory,
      responseByDepartment,
    };
  }
}
