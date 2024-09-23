package modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Conta {
    protected int id;
    protected String data;
    protected double saldo;
    private ArrayList<Correntista> correntistas = new ArrayList<>();

    public ArrayList<Correntista> getCorrentistas() {
        return correntistas;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public double getSaldo() {
        return saldo;
    }
    

    public Conta(int id, String data, double saldo) {
    	super();
        this.id = id;
        this.saldo = 0;
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = dataAtual.format(formatter);  
    }


    public void debitar(double valor) throws Exception{
    	if (saldo >= valor) {
    		saldo -= valor;    		
    	} else {
    		throw new Exception("Não é possível debitar valor maior que o saldo.");
    	}
    }

    
    public void creditar(double valor) {
        saldo += valor;
    }

    
    public void transferir(double valor, Conta destino) {
        saldo -= valor;
        destino.creditar(valor);
    }

    
    public void adicionarCorrentistaTitular(Correntista co){
        if (correntistas.isEmpty()){
            correntistas.add(co);
        }
    }

    public void adicionarCorrentista(Correntista co){
        correntistas.add(co);
    }

    public void removerCorrentista(Correntista co){
        correntistas.remove(co);
    }

    public Boolean verificarTitular(String cpf){
        if (correntistas.getFirst().getCpf().equals(cpf)){
            return true;
        }
        return false;
    }
    
    public boolean verificarCotitular(String cpf){
        for(Correntista co : correntistas){
            if(co.getCpf().equals(cpf)){
                return true;
            }
        }
        return false;
    }
    @Override
	public String toString() {
		String texto =  "Id=" + id + ", data=" + data  ;
		
		texto += ", correntistas:";
		for(Correntista co : correntistas)
			texto += co.getCpf() + ",";
		return texto;
	}
}
