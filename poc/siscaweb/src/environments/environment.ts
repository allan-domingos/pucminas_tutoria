// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  clientId: 'rL5Cmoca6c2u_cf6l67qIOkrUrsa',
  redirectUri: window.location.origin + '/siscaweb',
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

  wso2_sgm_safim: 'https://allandomingos.ddns.net/rest/safim/v1',
  wso2_sgm_ibge_dados: 'https://allandomingos.ddns.net/rest/ibge/dados/v1',
  wso2_sgm_ibge_geo: 'https://allandomingos.ddns.net/rest/ibge/geo/v1/wms',
  wso2_sgm_inpe_geo: 'https://allandomingos.ddns.net/rest/inpe/geo/v1/ows',
  wso2_sgm_stur: 'https://allandomingos.ddns.net/rest/stur/v1',
  wso2_sgm_mimg: 'https://allandomingos.ddns.net/rest/mimg/v1',

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
