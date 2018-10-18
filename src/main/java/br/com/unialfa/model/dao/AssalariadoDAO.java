package br.com.unialfa.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.unialfa.configuration.ConnectionFactory;
import br.com.unialfa.model.entity.Assalariado;
import br.com.unialfa.model.entity.Endereco;

public class AssalariadoDAO implements DAOInterface<Assalariado> {

	public void salvar(Assalariado obj) {
		PreparedStatement stmt = null;
		Connection conn = ConnectionFactory.getConnection();

		try {
			conn.setAutoCommit(false);
			String sql1 = "insert into assalariado(nome, salario_mensal) values (?, ?)";
			String sql2 = "insert into endereco(rua, numero, bairro, cidade, assalariado_codigo) values(?,?,?,?,?)";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, obj.getNome());
			stmt.setDouble(2, obj.getSalarioMensal());

			int rows = stmt.executeUpdate();

			Long assalariado_codigo = null;

			if (obj.getEndereco() != null) {
				Endereco endereco = obj.getEndereco();

				ResultSet ids = stmt.getGeneratedKeys();
				if (ids.next()) {
					assalariado_codigo = ids.getLong(1);
				}
				stmt = conn.prepareStatement(sql2);
				stmt.setString(1, endereco.getRua());
				stmt.setInt(2, endereco.getNumero());
				stmt.setString(3, endereco.getBairro());
				stmt.setString(4, endereco.getCidade());
				stmt.setLong(5, assalariado_codigo);
				System.out.println(endereco.getBairro());
				stmt.executeUpdate();
				conn.commit();
			}
			
			System.out.println("Salvo com Sucesso");
			conn.commit();
			
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	public void atualizar(Assalariado obj) {
		// TODO Auto-generated method stub

	}

	public void deletarById(Integer id) {
		// TODO Auto-generated method stub

	}

	public Assalariado consultaById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Assalariado> consultarAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
