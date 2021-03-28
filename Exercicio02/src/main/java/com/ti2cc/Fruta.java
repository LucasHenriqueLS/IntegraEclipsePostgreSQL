package com.ti2cc;

public class Fruta {
	private int codigo;
	private String nome;
	private int quantidade;
	
	public Fruta() {
		this.codigo = -1;
		this.nome = "";
		this.quantidade = -1;
	}
	
	public Fruta(int codigo, String nome, int quantidade) {
		this.codigo = codigo;
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "Fruta [codigo=" + codigo + ", nome=" + nome + ", quantidade=" + quantidade + "]";
	}
	
}