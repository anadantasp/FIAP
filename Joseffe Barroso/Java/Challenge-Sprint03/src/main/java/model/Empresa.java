package model;

import java.util.ArrayList;


public class Empresa {
	private int id;
	private String nome;
	private boolean ativoIpo;
	private String setor;
	private Ipo ipo;
	
	private ArrayList<Governanca> governancas = new ArrayList<>();
	private ArrayList<Valores> valores = new ArrayList<>();
	private ArrayList<Balanco> balancos = new ArrayList<>();
	
	
	public Empresa() {
		
	}

	public Empresa(int id, String nome, boolean ativoIpo, String setor) {
		this.id = id;
		this.nome = nome;
		this.ativoIpo = ativoIpo;
		this.setor = setor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean getAtivoIpo() {
		return ativoIpo;
	}

	public void setAtivoIpo(boolean ativoIpo) {
		this.ativoIpo = ativoIpo;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}
	
	public Ipo getIpo() {
		return ipo;
	}

	public void setIpo(Ipo ipo) {
		this.ipo = ipo;
	}

	public ArrayList<Governanca> getGovernancas() {
		return governancas;
	}
	
	public void setGovernancas(ArrayList<Governanca> governancas) {
		this.governancas = governancas;
	}

	public ArrayList<Valores> getValores() {
		return valores;
	}
	
	public void setValores(ArrayList<Valores> valores) {
		this.valores = valores;
	}

	public ArrayList<Balanco> getBalancos() {
		return balancos;
	}
	
	public void setBalancos(ArrayList<Balanco> balancos) {
		this.balancos = balancos;
	}
	
	public void addGovernanca(Governanca governanca) {
		governancas.add(governanca);
	}
	
	public void addValores(Valores valor) {
		valores.add(valor);
	}
	
	public void addBalanco(Balanco balanco) {
		balancos.add(balanco);
	}	
}
