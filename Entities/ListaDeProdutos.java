package Classes.EasyCalc.Entities;

import java.util.ArrayList;
import java.util.List;

public class ListaDeProdutos {
    
    private List <Double> produtos = new ArrayList<>();

    public ListaDeProdutos() {
    }

    public ListaDeProdutos(List<Double> produtos) {
        this.produtos = produtos;
    }

    public List<Double> getProdutos() {
        return produtos;
    }

    public void addProduto (Double valor){
        produtos.add(valor);
    }

    public void removeProduto (){
        produtos.removeLast();
    }

    public Double geratotal(){

        double total = 0.0;
        for (int i = 0 ; i < produtos.size(); i++){

            total = total + produtos.get(i);

        }
        return total;
    }
    
}
