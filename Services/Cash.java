package Classes.EasyCalc.Services;

public class Cash implements PayMethod {
    
    public Double payMethod(Double valor){

        double tax = valor * 0.02;
        return valor - tax;

    }
}
