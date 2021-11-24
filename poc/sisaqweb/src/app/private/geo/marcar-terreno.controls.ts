import { Control } from "ol/control";

export class MarcaTerreno extends Control {

    private clickStatus: boolean;
    private button: HTMLButtonElement;

    constructor() {
        super({})
        this.clickStatus = false;

        let div = document.getElementById('customControls');

        if (!div) {
            div = document.createElement('div');
            div.id = 'customControls';
            div.className = 'fixed-bottom m-2 d-table  ol-unselectable ol-control';
        }

        this.button = document.createElement('button');
        this.button.id = 'btnMarcaTerreno';
        div.appendChild(this.button);

        let tagI = document.createElement('i');
        tagI.id = 'iconMarcaTerreno';
        tagI.className = 'bi bi-heptagon';
        this.button.appendChild(tagI);

        Control.call(this, {
            element: div,
        });
    
        this.disabled(true);
    }

    private toggle() :void {
        this.clickStatus = !this.clickStatus;

        /* efeito troca do icone */
        let tagI = document.getElementById('iconMarcaTerreno');
        tagI.className = this.clicked ? 'bi bi-heptagon-fill' : 'bi bi-heptagon';
    }

    public disabled(bol : boolean): void {
        
        if(this.clickStatus){
            this.toggle();
        }

        this.button.disabled = bol;
        this.button.className = bol ? 'disabled-button' : '';
    }

    public get clicked() : boolean {
        return this.clickStatus;
    }

    public addClickListener(fn: (clicked: boolean ) => void): void{
        this.button.addEventListener('click', () => {
            this.toggle();
            fn(this.clickStatus);
        }, false);
    }

    public disabledMark() : void {
        this.clickStatus = false;
        let tagI = document.getElementById('iconMarcaTerreno');
        tagI.className = 'bi bi-heptagon';
    }
}