package Classes.EasyCalc.Services;

public class Debito implements PayMethod {
    
    public Double payMethod(Double valor){
        
        if (valor < 5.0){
            double tax = valor * 0.03;
            valor = valor + tax;
        } else {
            double tax = valor * 0.01;
            valor = valor + tax;
        }
        return valor;
    }

}
