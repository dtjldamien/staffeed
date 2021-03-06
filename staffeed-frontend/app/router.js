import EmberRouter from '@ember/routing/router';
import config from 'staffeed-frontend/config/environment';

export default class Router extends EmberRouter {
  location = config.locationType;
  rootURL = config.rootURL;
}

Router.map(function () {
  this.route('index', { path: '/' });
  this.route('login');
  this.route('survey');
  this.route('pulse');
  this.route('meetings');
  this.route('productivity');
  this.route('privacy');
});
