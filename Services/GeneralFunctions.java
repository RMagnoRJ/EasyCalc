package Classes.EasyCalc.Services;

import java.util.Random;
import java.util.Scanner;

public class GeneralFunctions {
    
    private Scanner inn = new Scanner (System.in);

   
    public int addRegistro(int reg){

        Integer registro;
        
        int [] numvec = new int[reg];
        String numStr = "";

        for (int i = 0; i < reg; i++){
            Random ram = new Random();
            int numRam = ram.nextInt(1,9);
            numvec[i] = numRam;
        }
        
        for (int i = 0; i < reg; i++){
            numStr = numStr + String.valueOf(numvec[i]);
        }
    
        registro = Integer.valueOf(numStr);
        return registro;
    }

    public String checkStringMin (String variavel, int limiteMin){

        String string = "";
        boolean tryAgain = true;

        while (tryAgain == true){

            System.out.print(variavel + ": " );
            string = inn.nextLine();
            if (string.length() < limiteMin){
                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                "Digite no MÍNIMO " + limiteMin + " \n" + 
                                "caracteres referente ao cadastro!\n");
            } else {
                tryAgain = false;
                break;
            }
        }

        return string;
    }

    public String checkStringMax (String variavel, int limiteMax){

        String string = "";
        boolean tryAgain = true;

        while (tryAgain == true){

            System.out.print(variavel + ": " );
            string = inn.nextLine();
            if (string.length() > limiteMax){
                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                "Digite no MÁXIMO " + limiteMax + " \n" + 
                                "caracteres referente ao cadastro!\n");
            } else {
                tryAgain = false;
                break;
            }
        }

        return string;
    }

    public String checkStringMinMax (String variavel, int limiteMin, int limiteMax){

        String string = "";
        boolean tryAgain = true;

        while (tryAgain == true){

            System.out.print(variavel + ": " );
            string = inn.nextLine();
            if (string.length() < limiteMin || string.length() > limiteMax){
                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                        variavel.toUpperCase() + " deve ser no MÍNIMO " + (limiteMin) + 
                                        "\ne o no MÁXIMO " + (limiteMax) +" !\n");
            } else {
                tryAgain = false;
                break;
            }
        }

        return string;
    }

    public boolean isNumeric(String str){
        if (str != null && str.matches("[0-9.]+")){
            return true;
        } else {
            return false;
        }
    }

    public Integer recebeIntVariavel (String variavel, int limiteMin, int limiteMax){

        Integer numConv = -1;
        boolean testaNum = true;
        while (testaNum == true){
            System.out.print("\n" + variavel);
            String teste = inn.nextLine();

            if (isNumeric(teste) == true){
                
                if (Integer.parseInt(teste) >= limiteMin && Integer.parseInt(teste) <= limiteMax){
                    numConv = Integer.parseInt(teste);
                    testaNum = false;
                } else {
                    System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                        variavel.toUpperCase() + " deve ser no MÍNIMO " + limiteMin + 
                                        "\ne o no MÁXIMO " + limiteMax +" !\n");
                }
                
            }  else if (isNumeric(teste) == false) {
                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                    variavel.toUpperCase() + " deve ser um NÚMERO INTEIRO!");
                teste = "";
            }
        }
        return numConv;

    }

    public Integer recebeInt (int limiteMin, int limiteMax){

        Integer numConv = -1;
        boolean testaNum = true;
        while (testaNum == true){
            System.out.print("\n> ");
            String teste = inn.nextLine();

            if (isNumeric(teste) == true){
                
                if (Integer.parseInt(teste) >= limiteMin && Integer.parseInt(teste) <= limiteMax){
                    numConv = Integer.parseInt(teste);
                    testaNum = false;
                } else {
                    System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                        "Você deve digitar no MÍNIMO " + limiteMin + 
                                        "\ne o no MÁXIMO " + limiteMax +" !\n");
                }
                
            }  else if (isNumeric(teste) == false) {
                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                   "Você deve digitar um NÚMERO INTEIRO!");
                teste = "";
            }
        }
        return numConv;

    }

    public Integer recebeIntIndex (String variavel, int limiteMin, int limiteMax){

        Integer numConv = -1;
        boolean testaNum = true;
        while (testaNum == true){
            System.out.print("\n" + variavel + ": " );
            String teste = inn.nextLine();

            if (isNumeric(teste) == true){
                
                if (Integer.parseInt(teste) >= limiteMin && Integer.parseInt(teste) <= limiteMax){
                    numConv = Integer.parseInt(teste);
                    testaNum = false;
                } else {
                    System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                        variavel.toUpperCase() + " deve ser no MÍNIMO " + limiteMin + 
                                        "\ne o no MÁXIMO " + limiteMax + " !\n");
                }
                
            }  else if (isNumeric(teste) == false) {
                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                    variavel.toUpperCase() + " deve ser um NÚMERO INTEIRO!");
                teste = "";
            }
        }
        return numConv;
    }

    public Double recebeDouble (){

        Double numConv = - 999.9;
        boolean testaNum = true;

        while (testaNum == true){

            System.out.print(" ");
            String teste = inn.nextLine();

            try {
                numConv = Double.parseDouble(teste);
                if (numConv != 999.9){
                    testaNum = false;
                }
                //return numConv;
            } catch (NumberFormatException e) {
                System.out.print("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                   "Você deve digitar apenas NÚMEROS!\n"+
                                   "Ex.: 8.9, 9, 10.742, 0, -9.3\n"+">");
                teste = "";
            }
        }
        System.out.println();
        return numConv;
    }

   

    public void pause(String letreiro, char etc, int repete, int tempo) throws InterruptedException{
        
        System.out.print(letreiro);

        for (int i = 0; i < repete; i++){
            Thread.sleep(tempo);
            System.out.print(etc);
        }

    }

    public void waitLine(){
        System.out.print("Pressione qualquer tecla para continuar... ");
        @SuppressWarnings("unused")
        String wait = inn.nextLine();
        System.out.println();
    }

    
    public Integer checkInt (String variavel){

        Integer numConv = -1;
        boolean testaNum = true;

        System.out.print(" ");
        String teste = inn.nextLine();

        while (testaNum == true){

            if (isNumeric(teste) == true){
                numConv = Integer.parseInt(teste);
                testaNum = false;
                break;
            }  else if (isNumeric(teste) == false) {
                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                   "Você deve digitar apenas NÚMEROS!");
                System.out.print("\n" + variavel + ": ");
                teste = inn.nextLine();
            }
        }
        return numConv;

    }

    public Double checkDouble (String variavel){

        Double numConv = -1.1;
        boolean testaNum = true;

        System.out.print(" ");
        String teste = inn.nextLine();

        while (testaNum == true){

            if (isNumeric(teste) == true){
                
                numConv = Double.parseDouble(teste);
                testaNum = false;
                break;
                
            }  else if (isNumeric(teste) == false) {

                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                   "Você deve digitar apenas NÚMEROS!");
                System.out.print("\n" + variavel + ": ");
                teste = inn.nextLine();

            }
        }
        return numConv;

    }

}
