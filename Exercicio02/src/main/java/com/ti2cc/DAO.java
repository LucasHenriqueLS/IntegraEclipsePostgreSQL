package com.ti2cc;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão Não efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão Não efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirFruta(Fruta fruta) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO fruta (codigo, nome, quantidade) "
					       + "VALUES ("+fruta.getCodigo()+ ", '" + fruta.getNome() + "', '"  
					       + fruta.getQuantidade() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarFruta(Fruta fruta) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE fruta SET nome = '" + fruta.getNome() + "', quantidade = '"  
				       + fruta.getQuantidade() + "'"
					   + " WHERE codigo = " + fruta.getCodigo();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirFruta(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM fruta WHERE codigo = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Fruta[] getFrutas() {
		Fruta[] frutas = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM fruta");		
	         if(rs.next()){
	             rs.last();
	             frutas = new Fruta[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                frutas[i] = new Fruta(rs.getInt("codigo"), rs.getString("nome"), 
	                		                  rs.getInt("quantidade"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return frutas;
	}

	
	public Fruta[] getFrutasMasculinos() {
		Fruta[] frutas = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM fruta WHERE fruta.sexo LIKE 'M'");		
	         if(rs.next()){
	             rs.last();
	             frutas = new Fruta[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                frutas[i] = new Fruta(rs.getInt("codigo"), rs.getString("nome"), 
                         		                  rs.getInt("quantidade"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return frutas;
	}
}