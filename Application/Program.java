package Classes.EasyCalc.Application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import Classes.EasyCalc.Services.GeneralFunctions;
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
        MasterAdm arquivoGeral = new MasterAdm();
        GeneralFunctions function = new GeneralFunctions();
        String operador;
        Double compra;
        boolean shop;
        int mostraCpf;
        String recebeCpf="";
        boolean purchase;
        boolean program = true;
        int menu;
        while (program == true){
            menu=0;
            System.out.println("\n       **********");
            System.out.println("       |  MENU  |");
            System.out.println("       **********\n");
            System.out.print("\n[1] Registrar compras\n" +
            "[2] Buscar Nota Fiscal\n" +
            "[3] Arquivo Geral de Nota Fiscal\n" +
            "[4] Fechamento\n" +
            "[5] Encerrar Programa");
            menu = function.recebeIntVariavel("\n> ", 1, 5);

            switch (menu) {
                
                case 1:
                // ROTINA DE VENDA
                ListaDeProdutos listaDeCompras = new ListaDeProdutos();
                IdNota numeroDaNota;
                Cpf cadastraCpf = new Cpf("não especificado");
                int ope;
                purchase = true;

                while (purchase == true){
                    System.out.println("\n\t*-*-*");
                    System.out.println(  "\t CPF ");
                    System.out.println(  "\t*-*-*\n");
                    System.out.print("Cadastra CPF na nota:\n" +
                    "[1] SIM\n"+
                    "[2] NÃO\n");
                    mostraCpf = function.recebeIntVariavel("\n> ", 1, 2);

                    if (mostraCpf == 1){
                    System.out.print("\nDigite o CPF (XXXXXXXXXxx):");
                    recebeCpf = function.checkString();
                    cadastraCpf = new Cpf(recebeCpf);
                    }

                    System.out.println("<<< TESTE STR >>> = " + recebeCpf + "\n");

                    System.out.println("\n\t*-*-*-*-*");
                    System.out.println(  "\t COMPRAS ");
                    System.out.println(  "\t*-*-*-*-*\n");
                    double multiplicador;
                    int quantidadeDeItens=0;
                    shop = true;
                    while (shop == true){

                        System.out.print(  "                                        $  ");
                        compra = inn.nextDouble(); inn.nextLine();

                        System.out.print("\n[ + ]     [ - ]     [ * ]     [ = ]   >>>  ");
                        operador = function.checkOperador();

                        if (operador.charAt(0) == '+'){
                            quantidadeDeItens = quantidadeDeItens + 1;
                            listaDeCompras.addProduto(compra);
                            System.out.println("\n\t  $ " + String.format("%.2f", listaDeCompras.geratotal()));

                        } else if (operador.charAt(0) == '-'){
                            listaDeCompras.removeProduto();
                            quantidadeDeItens = quantidadeDeItens - 1;

                        } else if (operador.charAt(0) == '*'){
                            System.out.print(  "\n                              [ * ]   >    ");
                            multiplicador = inn.nextDouble(); inn.nextLine();
                            compra = compra * multiplicador;
                            int resultado = (int) ( 0 + multiplicador);
                            quantidadeDeItens = quantidadeDeItens + resultado;
                            System.out.printf("\n                              [ = ]     $  %.2f %n", compra);
                            listaDeCompras.addProduto(compra);
                            System.out.println("\n\t  $ " + String.format("%.2f", listaDeCompras.geratotal()));
                            System.out.print("\n[ + ]                         [ = ]   >>>  ");
                            operador = inn.nextLine();
                            if (operador.charAt(0) == '='){
                                shop = false;
                            } else {
                                shop = true;
                            }

                        } else if (operador.charAt(0) == '='){
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
                    
                    notaFiscalDaCompra.geraNota();
                    System.out.printf("%s : R$ %.2f%n", aviso, (listaDeCompras.geratotal() * descontoTaxa));
                    notaFiscalDaCompra.geraTotal();
                    arquivoGeral.addNota(notaFiscalDaCompra);
                    ope=0;
                    System.out.println("\n\t  *-*-*-*-*");
                    System.out.println(  "\t    CAIXA  ");
                    System.out.println(  "\t  *-*-*-*-*\n");
                    System.out.println("[1] Abrir nota");
                    System.out.println("[2] Voltar ao MENU");
                    System.out.print("> ");
                    ope = inn.nextInt(); inn.nextLine();
                    if ( ope == 1){
                        purchase = true;
                        compra = 0.0;
                        recebeCpf="";
                    } else {
                        purchase = false;
                        break;
                    }
                }
                break;

                case 2:
                    // BUSCA DE NOTA FISCAL PELO CPF, ou, DATA
                    int busca=0;
                    while (busca < 3){
                        System.out.println("\n\t*-*-*-*");
                        System.out.println(  "\t BUSCA ");
                        System.out.println(  "\t*-*-*-*\n");
                        System.out.print("Realizar busca por:\n" +
                        "[1] CPF\n"+
                        "[2] Data\n"+
                        "[3] Valor da nota\n"+
                        "[4] Voltar ao menu\n"+
                        "> ");
                        busca = inn.nextInt(); inn.nextLine();
                        boolean localizado = false;
                        if (busca == 1){
                            System.out.print("\nDigite o CPF (XXXXXXXXX-xx): ");
                            String buscaCpf = inn.nextLine();
    
                            for (int i = 0; i < arquivoGeral.getArquivoGeral().size(); i++){
    
                                if (arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().equals(buscaCpf)) {
                                    arquivoGeral.getArquivoGeral().get(i).geraNota();
                                    localizado = true;
                                } else if (arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(0) == buscaCpf.charAt(0)){
                                    if(arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(1) == buscaCpf.charAt(1)){
                                        if(arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(2) == buscaCpf.charAt(2)){
                                            if(arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(3) == buscaCpf.charAt(3)){
                                                if(arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(4) == buscaCpf.charAt(4)){
                                                    if(arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(5) == buscaCpf.charAt(5)){
                                                        if(arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(6) == buscaCpf.charAt(6)){
                                                            if(arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(7) == buscaCpf.charAt(7)){
                                                                if(arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(8) == buscaCpf.charAt(8)){
                                                                    arquivoGeral.getArquivoGeral().get(i).geraNota();
                                                                    localizado = true;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } 
                            }
                            if (localizado == false){
                                System.out.println("\n# CPF NÃO LOCALIZADO #\n");
                            }
                        } else if (busca == 2) {
                            System.out.println("\nPreencha os campos abaixo: \n");
                            System.out.print("DIA: ");
                            int dia = inn.nextInt(); inn.nextLine();
                            System.out.print("\nMES: ");
                            int mes = inn.nextInt(); inn.nextLine();
                            System.out.print("\nANO: ");
                            int ano = inn.nextInt(); inn.nextLine();
                            LocalDate buscaData = LocalDate.of(ano, mes, dia);
                            localizado = false;
                            for (int i = 0; i < arquivoGeral.getArquivoGeral().size(); i++){
    
                                if (arquivoGeral.getArquivoGeral().get(i).getDataDaCompra().equals(buscaData)) {
                                    arquivoGeral.getArquivoGeral().get(i).geraNota();
                                    localizado = true;
                                } 
                            }
                            if (localizado == false){
                                System.out.println("\n# REGISTRO NÃO LOCALIZADO #\n");
                            }
                        } else if (busca == 3){
                            System.out.print("\nDigite o valor da nota: R$ ");
                            double valor = inn.nextDouble(); inn.nextLine();
                            localizado = false;
                            for (int i = 0; i < arquivoGeral.getArquivoGeral().size(); i++){
    
                                if (arquivoGeral.getArquivoGeral().get(i).getValorDaNota() == valor) {
                                    arquivoGeral.getArquivoGeral().get(i).geraNota();
                                    System.out.println("\n|  [ 1 ] ENCERRAR  |  PRÓXIMA [ 2 ]  |");
                                    System.out.print("[  ] ");
                                    int proxima = inn.nextInt(); inn.nextLine();
                                    if (proxima == 1) {
                                        i = arquivoGeral.getArquivoGeral().size();
                                        localizado = true;
                                    }
                                    System.out.println("\n"); 
                                } 
                            }
                            if (localizado == false){
                                System.out.println("\n# REGISTRO NÃO LOCALIZADO #\n");
                            }
                        } 
                    }
                    break;

                case 3:
                    // ARQUIVO GERAL DE NOTAS
                    System.out.println("\n\t*-*-*-*-*-*-*-*");
                    System.out.println(  "\t ARQUIVO GERAL ");
                    System.out.println(  "\t*-*-*-*-*-*-*-*\n");
                    int proxima;
                    for (int i = 0; i < arquivoGeral.getArquivoGeral().size(); i++){
    
                        arquivoGeral.getArquivoGeral().get(i).geraNota();

                        System.out.println("\n|  [ 1 ] SAIR  |  PRÓXIMA [ 2 ]  |");
                        System.out.print("[  ] ");
                        proxima = inn.nextInt(); inn.nextLine();
                        if (proxima == 1) {
                            i = arquivoGeral.getArquivoGeral().size();
                        }
                        System.out.println("\n"); 
                    }
                    break;

                case 4:
                    // FECHAMENTO DE VENDAS
                    System.out.println("\n\t*-*-*-*-*-*-*-*");
                    System.out.println(  "\t FECHAMENTO ");
                    System.out.println(  "\t*-*-*-*-*-*-*-*\n");
                    double vendasDoDia=0.0;
                    DateTimeFormatter novaData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    System.out.println("\n---------------------------------");
                    for (int i = 0; i < arquivoGeral.getArquivoGeral().size(); i++){

                        System.out.print("| DATA: ");
                        arquivoGeral.getArquivoGeral().get(i).getDataDaCompra().format(novaData);
                        System.out.print(" | Valor (R$) ");
                        arquivoGeral.getArquivoGeral().get(i).getValorDaNota();
                        System.out.println(" |");
                        vendasDoDia = vendasDoDia + arquivoGeral.getArquivoGeral().get(i).getValorDaNota();
                    }
                    System.out.println("---------------------------------");
                    System.out.printf("Total recebido R$ %.2f", vendasDoDia);
                    System.out.println("---------------------------------\n");
                    function.waitLine();
                    break;

                case 5:
                    program = false;
                    break;

                default:
                    System.out.println("# ERROR #");
                    break;
            }
        }
        System.out.println("\n\t*-*-*-*-*-*-*");
        System.out.println(  "\t  ENCERRADO  ");
        System.out.println(  "\t*-*-*-*-*-*-*\n");
        inn.close();
    }
}
