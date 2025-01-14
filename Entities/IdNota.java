package Classes.EasyCalc.Entities;

import Classes.EasyCalc.Services.GeneralFunctions;

public class IdNota {
    
    private Integer numeroDaNota;

    public IdNota() {
    }

    public IdNota(Integer numeroDeRegistros) {
        GeneralFunctions function = new GeneralFunctions();
        this.numeroDaNota = function.addRegistro(numeroDeRegistros);
    }

    public Integer getNumeroDaNota() {
        return numeroDaNota;
    }
    
    public void showNumero(){
        System.out.println(numeroDaNota);
    }
}
