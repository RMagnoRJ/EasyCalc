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
        double multiplicador;
        int quantidadeDeItens=0;
        while (shop == true){

            System.out.print(  "                                        $  ");
            compra = inn.nextDouble(); inn.nextLine();
            
            System.out.print("\n[ + ]     [ - ]     [ * ]     [ = ]   >>>  ");
            operador = inn.nextLine().charAt(0);
            if (operador == '+'){
                quantidadeDeItens = quantidadeDeItens + 1;
                listaDeCompras.addProduto(compra);
                System.out.println("\n\t  $ " + String.format("%.2f", listaDeCompras.geratotal()));
            } else if (operador == '-'){
                listaDeCompras.removeProduto();
                quantidadeDeItens = quantidadeDeItens - 1;
            } else if (operador == '*'){
                System.out.print(  "\n                              [ * ]   >    ");
                multiplicador = inn.nextDouble(); inn.nextLine();
                compra = compra * multiplicador;
                int resultado = (int) ( 0 + multiplicador);
                quantidadeDeItens = quantidadeDeItens + resultado;
                System.out.printf("\n                              [ = ]     $  %.2f %n", compra);
                listaDeCompras.addProduto(compra);
                System.out.println("\n\t  $ " + String.format("%.2f", listaDeCompras.geratotal()));
                System.out.print("\n[ + ]                         [ = ]   >>>  ");
                operador = inn.nextLine().charAt(0);
                if (operador == '='){
                    shop = false;
                } else {
                    shop = true;
                }
            } else if (operador == '='){
                quantidadeDeItens = quantidadeDeItens + 1;
                listaDeCompras.addProduto(compra);
                System.out.println("\n\t  $ " + String.format("%.2f", listaDeCompras.geratotal()) + "\n");
                shop = false;
            }

        }
        System.out.println("\n<<< TOTAL >>> R$ " + String.format("%.2f", listaDeCompras.geratotal()));
        System.out.println("\nForma de pagamento: \n");
        int i = 1;
        for (PaymentMode mode : PaymentMode.values()){
            System.out.println("["+i+"] " + mode);
            i++;
        }
        System.out.print("\n> ");
        int op = inn.nextInt(); inn.nextLine();
        String aviso = "Desconto de 5%";
        double descontoTaxa = 0.05;
        PayMethod tax = new Pix();
        PaymentMode pay = PaymentMode.PIX;
        if (op == 2){
            pay = PaymentMode.DINHEIRO;
            tax = new Cash();
            aviso = "Desconto de 2%";
            descontoTaxa = 0.02;
        } else if (op == 3){
            pay = PaymentMode.DEBITO;
            tax = new Debito();
            if (listaDeCompras.geratotal() < 5.0){
                aviso = "Taxa de 3% cobrada";
                descontoTaxa = 0.03;
            } else {
                aviso = "Taxa de 1% cobrada";
                descontoTaxa = 0.01;
            }
        } else if (op == 4){
            pay = PaymentMode.CREDITO;
            tax = new Credito();
            if (listaDeCompras.geratotal() < 5.0){
                aviso = "Taxa de 8% cobrada";
                descontoTaxa = 0.08;
            } else {
                aviso = "Taxa de 5% cobrada";
                descontoTaxa = 0.05;
            }
        }
        numeroDaNota = new IdNota(7);
        LocalDateTime agora = LocalDateTime.now();
        agora = LocalDateTime.now();
        Invoice notaFiscalDaCompra = new Invoice(numeroDaNota, 
        cadastraCpf, agora, listaDeCompras, pay, tax, quantidadeDeItens);
        System.out.println("\n");
        arquivoGeral.addNota(notaFiscalDaCompra);
        notaFiscalDaCompra.geraNota();
        System.out.printf("%s : R$ %.2f%n", aviso, (listaDeCompras.geratotal() * descontoTaxa));
        notaFiscalDaCompra.geraTotal();

        inn.close();
    }
}
