package exCadClienteContaCCCP;

public class ContaPoupanca extends ContaBancaria{
	private double rentabilidade;

	public ContaPoupanca() {
    	super();
    }
   
    public ContaPoupanca(String agencia, String numero, double salario, double rentabilidade) {
        super(agencia, numero, salario);
       
        this.rentabilidade = rentabilidade;
    }

    public double getRentabilidade() {
        return rentabilidade;
    }


    public void setRentabilidade(double rentabilidade) {
        this.rentabilidade = rentabilidade;
    }
   
    public String exibirDadosConta() {
        return "Tipo de Conta: Conta Poupança\n" + super.exibirDadosConta() + "\nRentabilidade: " + rentabilidade;
    }
    
    public void Depositar(double valor) {
    	valor = valor * (1+rentabilidade);
    	
        super.Depositar(valor);
    }


}
