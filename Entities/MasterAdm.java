package Classes.EasyCalc.Entities;

import java.util.ArrayList;
import java.util.List;

public class MasterAdm {
    
    // Lista de notas fiscais para arquivo e consulta

    private List <Invoice> arquivoGeral = new ArrayList<>();

    public MasterAdm() {
    }

    public MasterAdm(List<Invoice> arquivoGeral) {
        this.arquivoGeral = arquivoGeral;
    }

    public List<Invoice> getArquivoGeral() {
        return arquivoGeral;
    }

    public void addNota(Invoice notaFiscal){
        arquivoGeral.add(notaFiscal);
    }
    
}
