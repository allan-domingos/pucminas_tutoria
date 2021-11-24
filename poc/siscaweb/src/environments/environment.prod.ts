export const environment = {
  production: true,

  clientId: 'rL5Cmoca6c2u_cf6l67qIOkrUrsa',
  redirectUri: window.location.origin + '/siscaweb',
  scope: 'openid',
  responseType: 'code',
  showDebugInformation: true,
  issuer: 'siscaweb',
  revocationEndpoint: 'https://allandomingos.ddns.net/oauth2/revoke',
  userinfoEndpoint: 'https://allandomingos.ddns.net/oauth2/userinfo',
  loginUrl: 'https://allandomingos.ddns.net/authorize',
  logoutUrl: 'https://allandomingos.ddns.net/oidc/logout',
  requireHttps: false,
  oidc: true,
  skipIssuerCheck: true,

  wso2_sgm_safim: 'https://allandomingos.ddns.net/rest/safim/v1',
  wso2_sgm_ibge_dados: 'https://allandomingos.ddns.net/rest/ibge/dados/v1', //https://servicodados.ibge.gov.br/api/v3/agregados/2296/periodos/-1/variaveis/48?localidades=N3[53]
  wso2_sgm_ibge_geo: 'https://allandomingos.ddns.net/rest/ibge/geo/v1/wms',
  wso2_sgm_inpe_geo: 'https://allandomingos.ddns.net/rest/inpe/geo/v1/ows',
  wso2_sgm_stur: 'https://allandomingos.ddns.net/rest/stur/v1',
  wso2_sgm_mimg: 'https://allandomingos.ddns.net/rest/mimg/v1',

  loader_time_show: 500,
  loader_time_hiden: 0
};
