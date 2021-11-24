import Polygon from 'ol/geom/Polygon';
import { Geo } from './geo';
import { Municipio } from './municipio'



export class Terreno {

    public id: number;
    public matricula: string;
    public dataInclusao: Date;
    public comprimento: number;
    public largura: number;
    public municipio: Municipio;
    public coordenadas?: Array<number>;

    public geo?: Geo;

}