package modelos;

public class ContaEspecial extends Conta {
    double limite;

    public double getLimite() {
        return limite;
    }

    public ContaEspecial(int id,double limite){
        super(id);
        this.limite = limite;
    }

    public ContaEspecial(int id, String data, double saldo, double limite) {
        super(id, data, saldo);
        this.limite = limite;
    }
    
    void debitar(double valor) {
        saldo -= valor;
    }
}
