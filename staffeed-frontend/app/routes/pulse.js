import Route from '@ember/routing/route';
import axios from 'axios';

export default class PulseRoute extends Route {
  async model() {
    const client = axios.create({
      baseURL: 'http://localhost:8080',
      headers: {
        Authorization:
          'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYzNTY2MTc2NiwiZXhwIjoxNjM1NzQ4MTY2fQ.UxqtUBxOGDF79oa_m7hlP0G4fveRVOKpG7P0y1EJ5VKF03nobhl78z5R2-E827ekkd2XIsHpUGLPtBBpWyTYUQ',
      },
    });

    let { data: responseRate } = await client.get('/analytics/response-rate');
    let { data: responses } = await client.get('/analytics/responses');
    let { data: responsesByCategory } = await client.get(
      '/analytics/responses/categories'
    );
    return { responseRate, responses, responsesByCategory };
  }
}
