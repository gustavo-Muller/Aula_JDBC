package br.com.unialfa.model.entity;

public abstract class Funcionario {

	private Integer codigo;
	private String nome;
	private double salarioMensal;
	private Endereco endereco;

	public Funcionario() {
	}

	public Funcionario(String nome, double salarioMensal) {
		this.nome = nome;
		this.salarioMensal = salarioMensal;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSalarioMensal() {
		return salarioMensal;
	}

	public void setSalarioMensal(double salarioMensal) {
		this.salarioMensal = salarioMensal;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
