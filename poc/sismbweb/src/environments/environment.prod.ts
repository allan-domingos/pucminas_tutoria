export const environment = {
  production: true,

  clientId: 'LwK5L5VzYIYff_a4qUfcKwyu4yIa',
  redirectUri: window.location.origin + '/sismbweb',
  scope: 'openid',
  responseType: 'code',
  showDebugInformation: true,
  issuer: 'sismbweb',
  revocationEndpoint: 'https://allandomingos.ddns.net/oauth2/revoke',
  userinfoEndpoint: 'https://allandomingos.ddns.net/oauth2/userinfo',
  loginUrl: 'https://allandomingos.ddns.net/authorize',
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
