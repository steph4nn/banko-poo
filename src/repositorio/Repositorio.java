package repositorio;

import modelos.Conta;
import modelos.Correntista;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Repositorio {
    private ArrayList<Conta> contas = new ArrayList<>();
    private ArrayList<Correntista> correntistas = new ArrayList<>();

    public void adicionarConta(Conta c)	{
        contas.add(c);
    }
    public void adicionarCorrentista(Correntista c)	{
        correntistas.add(c);
    }
    public void removerConta(Conta c)	{
        contas.remove(c);
    }
    public void removerCorrentista(Correntista c)	{
        correntistas.remove(c);
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public ArrayList<Correntista> getCorrentistas() {
        return correntistas;
    }

    public void carregarObjetos(){
        // carregar para o repositorio os objetos dos arquivos csv
        try {
            //caso os arquivos nao existam, serao criados vazios
            File f1 = new File( new File(".\\contas.csv").getCanonicalPath() ) ;
            File f2 = new File( new File(".\\correntistas.csv").getCanonicalPath() ) ;
            if (!f1.exists() || !f2.exists() ) {
                //System.out.println("criando arquivo .csv vazio");
                FileWriter arquivo1 = new FileWriter(f1); arquivo1.close();
                FileWriter arquivo2 = new FileWriter(f2); arquivo2.close();
                return;
            }
        } catch(Exception ex){
            throw new RuntimeException("criacao dos arquivos vazios:"+ex.getMessage());
        }
    }
}
