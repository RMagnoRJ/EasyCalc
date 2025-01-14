package Classes.EasyCalc.Services;

public class Pix implements PayMethod {
    
    public Double payMethod(Double valor){

        double tax = valor * 0.05;
        return valor - tax;

    }

}
