import { TileWMS } from "ol/source";
import TileState from "ol/TileState";
import { AuthService } from "src/app/common/auth.service";
import { environment } from "src/environments/environment";

export class IBGEWMS extends TileWMS {

    constructor(private authService: AuthService, params?: any) {
        super({
            url: environment.wso2_sgm_ibge_geo,
            params: params,
            serverType: 'geoserver',
            transition: 0,
            tileLoadFunction: (tile: any, src: string) =>  {
   
                var xhr = new XMLHttpRequest();
                xhr.open('GET', src);
                xhr.setRequestHeader("Authorization", "Bearer " + authService.token);
                xhr.responseType = 'blob';
                xhr.addEventListener('loadend', function (evt) {
                    var data = this.response;
                    if (data !== undefined) {
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