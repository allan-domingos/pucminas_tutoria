import { Injectable } from "@angular/core";
import { OAuthService } from "angular-oauth2-oidc";
import { environment } from "src/environments/environment";

@Injectable()
export class AuthService {

    constructor(private oauthService: OAuthService) {
        this.oauthService.clientId = environment.clientId;
        this.oauthService.issuer = environment.issuer;
        this.oauthService.responseType = environment.responseType;
        this.oauthService.redirectUri = environment.redirectUri;
        this.oauthService.scope = environment.scope;
        this.oauthService.showDebugInformation = environment.showDebugInformation;
        this.oauthService.loginUrl = environment.loginUrl;
        this.oauthService.logoutUrl = environment.logoutUrl;
        this.oauthService.userinfoEndpoint = environment.userinfoEndpoint;
        this.oauthService.requireHttps = environment.requireHttps;
        this.oauthService.oidc = environment.oidc;
        this.oauthService.skipIssuerCheck = environment.skipIssuerCheck;
        this.oauthService.tryLogin({});
    }

    public login(): void {
        this.oauthService.initCodeFlow();
    }

    public logout(): void {
        this.oauthService.logOut();
        this.oauthService.revokeTokenAndLogout();
    }

    get isLoggedIn(): boolean {
        let result = this.oauthService.getAccessToken() != null;
        return result;
    }

    get token(): string {
        return this.oauthService.getAccessToken();
    }

    get name(): string {
        let claims: any = this.oauthService.getIdentityClaims();
        return claims?.given_name;
    }

    get email(): string {
        let claims: any = this.oauthService.getIdentityClaims();
        return claims?.email;
    }

    get username(): string {
        let claims: any = this.oauthService.getIdentityClaims();
        return claims?.sub;
    }

}