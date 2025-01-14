package Classes.EasyCalc.Entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Classes.EasyCalc.Enum.PaymentMode;
import Classes.EasyCalc.Services.PayMethod;

public class Invoice {
    
    // Id da nota(ok), CPF(ok), data da compra(ok), lista de produtos (ok), metodo de pagamento(ok)

    private IdNota notaFiscal;
    private Cpf cpf;
    private LocalDateTime dataDaCompra;
    private DateTimeFormatter showDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private ListaDeProdutos compras;
    private PaymentMode pagamento;
    private PayMethod mode;
    private Double totalDaCompra;
    private Double valorDaNota;
    
    public Invoice() {
    }

    public Invoice( IdNota notaFiscal, 
                    Cpf cpf, 
                    LocalDateTime dataDaCompra, 
                    ListaDeProdutos compras, 
                    PaymentMode pagamento,
                    PayMethod mode) {
        this.notaFiscal = notaFiscal;
        this.cpf = cpf;
        this.dataDaCompra = dataDaCompra;
        this.compras = compras;
        this.pagamento = pagamento;
        this.mode = mode;

    }

    public IdNota getNotaFiscal() {
        return notaFiscal;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public LocalDateTime getDataDaCompra() {
        return dataDaCompra;
    }

    public ListaDeProdutos getCompras() {
        return compras;
    }

    public PaymentMode getPagamento() {
        return pagamento;
    }

    public Double getTotalDaCompra() {
        return totalDaCompra;
    }

    public Double getValorDaNota(){
        return valorDaNota;
    }

    public void geraNota(){

        System.out.println("---------------------------------");
        System.out.println("Nota Fiscal nº " + notaFiscal.getNumeroDaNota());
        System.out.println("---------------------------------");
        System.out.println("Data da compra: " + getDataDaCompra().format(showDataHora));
        System.out.println("CPF: " + cpf.getCpf());
        System.out.println("---------------------------------");
        for (int i = 0; i < compras.getProdutos().size(); i++){
            System.out.println("        " + compras.getProdutos().get(i));
        }
        System.out.println("---------------------------------");
        this.totalDaCompra = compras.geratotal();
        System.out.println("Total R$ " + String.format("%.2f", getTotalDaCompra()));
        System.out.println("---------------------------------");
        System.out.println("Forma de pagamento: " + getPagamento());
        
    }

    public void geraTotal(){
        
        double valorFinal = compras.geratotal();
        valorFinal = mode.payMethod(valorFinal);
        this.valorDaNota = valorFinal;
        System.out.println("Total a pagar: R$ " + String.format("%.2f", getValorDaNota()));
        System.out.println("---------------------------------");

    }

  

    public PayMethod getMode() {
        return mode;
    }


}
