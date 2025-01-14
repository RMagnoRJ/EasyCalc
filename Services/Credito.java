package Classes.EasyCalc.Services;

public class Credito implements PayMethod {
    
    public Double payMethod(Double valor){

        if (valor < 5.0){
            double tax = valor * 0.08;
            valor = valor + tax;
        } else {
            double tax = valor * 0.05;
            valor = valor + tax;
        }
        return valor;

    }

}
