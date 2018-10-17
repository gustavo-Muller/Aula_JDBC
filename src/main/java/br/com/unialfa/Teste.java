package br.com.unialfa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.unialfa.configuration.ConnectionFactory;
import br.com.unialfa.model.entity.Assalariado;
import br.com.unialfa.model.entity.Endereco;

public class Teste {
	public static void main(String[] args) throws SQLException {
		Assalariado funcionario1 = new Assalariado("Jo�o da Silva", 3000.00);
		Endereco endereco = new Endereco("Rua de S�o Jo�o", 35, "Jd. das Flores", "Goi�nia");
		funcionario1.setEndereco(endereco);
		Connection conn = new ConnectionFactory().getConnection();
		conn.setAutoCommit(false);
		String sql1 = "insert into assalariado(nome, salario_mensal) values (?, ?)";

		PreparedStatement stmt = conn.prepareStatement(sql1);
		stmt.setString(1, funcionario1.getNome());
		stmt.setDouble(2, funcionario1.getSalarioMensal());

		String sql2 = "insert into endereco(rua, numero, bairro, cidade, assalariado_codigo) values(?,?,?,?,?)";

		int rows = stmt.executeUpdate();

		if (rows == 0) {
			System.out.println("Erro!");
		}

		ResultSet ids = stmt.getGeneratedKeys();
		if (ids.next()) {
			funcionario1.setCodigo(ids.getInt(1));
		}

		stmt = conn.prepareStatement(sql2);

		stmt.setString(1, endereco.getRua());
		stmt.setDouble(2, endereco.getNumero());
		stmt.setString(3, endereco.getBairro());
		stmt.setString(4, endereco.getCidade());
		stmt.setInt(5, 1);

		rows = stmt.executeUpdate();
		conn.commit();
		stmt.close();
		System.out.println("Conex�o aberta!");
		conn.close();
	}
}