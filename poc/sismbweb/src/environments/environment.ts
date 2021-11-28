// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  clientId: 'LwK5L5VzYIYff_a4qUfcKwyu4yIa',
  redirectUri: window.location.origin + '/sismbweb',
  scope: 'openid',
  responseType: 'code',
  showDebugInformation: true,
  issuer: 'sgm',
  revocationEndpoint: 'https://allandomingos.ddns.net/oauth2/revoke',
  userinfoEndpoint: 'https://allandomingos.ddns.net/oauth2/userinfo',
  loginUrl: 'https://allandomingos.ddns.net/oauth2/authorize',
  logoutUrl: 'https://allandomingos.ddns.net/oidc/logout',
  requireHttps: false,
  oidc: true,
  skipIssuerCheck: true,

  wso2_sisca_api: 'https://allandomingos.ddns.net/rest/sisca/v1',
  wso2_sisaq_api: 'https://allandomingos.ddns.net/rest/sisaq/v1',
  wso2_sismb_api: 'https://allandomingos.ddns.net/rest/sismb/v1',
  
  loader_time_show: 500,
  loader_time_hiden: 0
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
