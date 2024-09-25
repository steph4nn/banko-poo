package modelos;

public class ContaEspecial extends Conta {
    private double limite;
    
    public ContaEspecial(int id, String data, double saldo, double limite) throws Exception {
        super(id, data, saldo);
        if (limite < 50){
            throw new Exception("O limite minimo de uma conta especial é R$50");
        }
        this.limite = limite;
    }
    

    public double getLimite() {
        return limite;
    }
    
    public void setLimite(double limite) {
    	this.limite = limite;
    }

    
    @Override
    public void debitar(double valor) throws Exception {
    	if (getSaldo() - valor >= -limite) {
    		saldo -= valor;
    	} else {
    		throw new Exception("Não é possível debitar valor que exceda o limite.");
    	}
    	
    }
    
}
