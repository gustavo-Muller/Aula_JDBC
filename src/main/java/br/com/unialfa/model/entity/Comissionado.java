package br.com.unialfa.model.entity;

public class Comissionado extends Funcionario {
	private double porcentagem = 0.20;

	public Comissionado() {
	}

	public Comissionado(String nome, double salarioMensal, double porcentagem) {
		super(nome, salarioMensal);
		this.porcentagem = porcentagem;
	}

	public double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(double porcentagem) {
		this.porcentagem = porcentagem;
	}
}
