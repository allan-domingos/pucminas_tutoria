import { OAuthModuleConfig } from "angular-oauth2-oidc";

export const OAUTH: OAuthModuleConfig = {
    resourceServer: {
        sendAccessToken: true,
        allowedUrls: [
            'https://bomdestino.gov.br/'
        ],
    }
}