import { TileWMS } from "ol/source";
import TileState from "ol/TileState";
import { AuthService } from "src/app/common/auth.service";
import { environment } from "src/environments/environment";

export class INPEWMS extends TileWMS {

    constructor(private authService: AuthService, params?: any) {
        super({

            url: environment.wso2_sgm_inpe_geo,
            params: params,
            serverType: 'geoserver',
            transition: 0,
            tileLoadFunction: (tile: any, url: string) => {
                var xhr = new XMLHttpRequest();
                xhr.open('GET', url);
                xhr.setRequestHeader("Authorization", "Bearer " + authService.token);
                xhr.responseType = 'blob';
                xhr.addEventListener('loadend', function (evt) {
                    var data = this.response;
                    if (data !== undefined && data !== null) {
                        tile.getImage().src = URL.createObjectURL(data);
                    } else {
                        tile.setState(TileState.ERROR);
                    }
                });
                xhr.addEventListener('error', function () {
                    tile.setState(TileState.ERROR);
                });
                xhr.send();
            }
        });
    }

}