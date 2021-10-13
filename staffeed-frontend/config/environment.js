'use strict';

module.exports = function (environment) {
  let ENV = {
    modulePrefix: 'staffeed-frontend',
    environment,
    rootURL: '/',
    locationType: 'auto',
    EmberENV: {
      FEATURES: {
        // Here you can enable experimental features on an ember canary build
        // e.g. EMBER_NATIVE_DECORATOR_SUPPORT: true
      },
      EXTEND_PROTOTYPES: {
        // Prevent Ember Data from overriding Date.parse.
        Date: false,
      },
    },

    APP: {
      // Here you can pass flags/options to your application instance
      // when it is created
    },
  };

  // JSON Web Token Authenticator
  ENV['ember-simple-auth-token'] = {
    tokenDataPropertyName: 'tokenData', // Key in session to store token data
    refreshAccessTokens: true, // Enables access token refreshing
    tokenExpirationInvalidateSession: true, // Enables session invalidation on token expiration
    serverTokenRefreshEndpoint: 'http://localhost:8080/auth/login', // Server endpoint to send refresh request
    refreshTokenPropertyName: 'refresh_token', // Key in server response that contains the refresh token
    tokenExpireName: 'exp', // Field containing token expiration
    refreshLeeway: 300, // Amount of time to send refresh request before token expiration
    authorizationHeaderName: 'Authorization', // Header name added to each API request
    authorizationPrefix: 'Bearer ', // Prefix added to each API request  
    };
  
  if (environment === 'development') {
    // ENV.APP.LOG_RESOLVER = true;
    // ENV.APP.LOG_ACTIVE_GENERATION = true;
    // ENV.APP.LOG_TRANSITIONS = true;
    // ENV.APP.LOG_TRANSITIONS_INTERNAL = true;
    // ENV.APP.LOG_VIEW_LOOKUPS = true;
  }

  if (environment === 'test') {
    // Testem prefers this...
    ENV.locationType = 'none';

    // keep test console output quieter
    ENV.APP.LOG_ACTIVE_GENERATION = false;
    ENV.APP.LOG_VIEW_LOOKUPS = false;

    ENV.APP.rootElement = '#ember-testing';
    ENV.APP.autoboot = false;
  }

  if (environment === 'production') {
    // here you can enable a production-specific feature
  }

  return ENV;
};
