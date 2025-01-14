package Classes.EasyCalc.Application;

import java.time.LocalDateTime;
import java.util.Scanner;

import Classes.EasyCalc.Entities.Cpf;
import Classes.EasyCalc.Entities.IdNota;
import Classes.EasyCalc.Entities.Invoice;
import Classes.EasyCalc.Entities.ListaDeProdutos;
import Classes.EasyCalc.Entities.MasterAdm;
import Classes.EasyCalc.Enum.PaymentMode;
import Classes.EasyCalc.Services.Cash;
import Classes.EasyCalc.Services.Credito;
import Classes.EasyCalc.Services.Debito;
import Classes.EasyCalc.Services.PayMethod;
import Classes.EasyCalc.Services.Pix;

public class Program {
    
    public static void main(String[] args) {
        
        // Recapitulação seção 16 - Interfaces

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println(  "                    EASY CALC v 1.0                  ");
        System.out.println(  "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n\n");
        Scanner inn = new Scanner (System.in);
        ListaDeProdutos listaDeCompras = new ListaDeProdutos();
        MasterAdm arquivoGeral = new MasterAdm();
        IdNota numeroDaNota;
        Cpf cadastraCpf = new Cpf("não especificado");
        char operador;
        Double compra;
        boolean shop = true;
        int mostraCpf;
        @SuppressWarnings("unused")
        String recebeCpf;
        System.out.print("Cadastra CPF na nota:\n" +
                           "[1] SIM\n"+
                           "[2] NÃO\n"+
                           "> ");
        mostraCpf = inn.nextInt(); inn.nextLine();
        
        if (mostraCpf == 1){
            System.out.print("\nDigite o CPF: ");
            recebeCpf = inn.nextLine();
            cadastraCpf = new Cpf(recebeCpf);
        } 
        System.out.println("\n\t*-*-*-*-*");
        System.out.println(  "\t COMPRAS ");
        System.out.println(  "\t*-*-*-*-*\n");
        while (shop == true){

            System.out.print("  > ");
            compra = inn.nextDouble(); inn.nextLine();
            
            System.out.print("\n    [ + ]       [ - ]       [ = ]        ");
            operador = inn.nextLine().charAt(0);
            if (operador == '+'){
                listaDeCompras.addProduto(compra);
                System.out.println("\n\t " + String.format("%.2f", listaDeCompras.geratotal()) + "\n");
            } else if (operador == '-'){
                listaDeCompras.removeProduto();
            } else if (operador == '='){
                listaDeCompras.addProduto(compra);
                System.out.println("\n\t " + String.format("%.2f", listaDeCompras.geratotal()) + "\n");
                shop = false;
            }

        }
        System.out.println("\n<<< TOTAL >>> R$ " + String.format("%.2f", listaDeCompras.geratotal()));
        System.out.println("\nForma de pagamento: ");
        int i = 1;
        for (PaymentMode mode : PaymentMode.values()){
            System.out.println("["+i+"] " + mode);
            i++;
        }
        System.out.print("> ");
        int op = inn.nextInt(); inn.nextLine();
        String aviso = "Desconto de 5%";
        PayMethod tax = new Pix();
        PaymentMode pay = PaymentMode.PIX;
        if (op == 2){
            pay = PaymentMode.DINHEIRO;
            tax = new Cash();
            aviso = "Desconto de 2%";
        } else if (op == 3){
            pay = PaymentMode.DEBITO;
            tax = new Debito();
            if (listaDeCompras.geratotal() < 5.0){
                aviso = "Taxa de 3% cobrada";
            } else {
                aviso = "Taxa de 1% cobrada";
            }
        } else if (op == 4){
            pay = PaymentMode.CREDITO;
            tax = new Credito();
            if (listaDeCompras.geratotal() < 5.0){
                aviso = "Taxa de 8% cobrada";
            } else {
                aviso = "Taxa de 5% cobrada";
            }
        }
        numeroDaNota = new IdNota(7);
        LocalDateTime agora = LocalDateTime.now();
        agora = LocalDateTime.now();
        Invoice notaFiscalDaCompra = new Invoice(numeroDaNota, 
        cadastraCpf, agora, listaDeCompras, pay, tax);
        arquivoGeral.addNota(notaFiscalDaCompra);
        notaFiscalDaCompra.geraNota();
        System.out.println(aviso);
        notaFiscalDaCompra.geraTotal();

        inn.close();
    }
}
