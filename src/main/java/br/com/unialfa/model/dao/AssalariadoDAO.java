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
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		String sql = "UPDATE assalariado SET nome = ?, "
				+ "salario_mensal = ? WHERE codigo = ?";
		String sql2 = "UPDATE endereco SET rua = ?, numero = ?, "
				+ "bairro = ?, cidade = ? WHERE assalariado_id = ?";
		try {
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void deletarById(Integer id) throws SQLException {	
		PreparedStatement stmt = null;
		Connection conn = ConnectionFactory.getConnection();
		Assalariado assalariado = new Assalariado();
		Endereco endereco = new Endereco();
		
		try {
		String sql = "Select assalariado.codigo, assalariado.nome, assalariado.salario_mensal, "
				+ "endereco.id, endereco.rua, endereco.numero, endereco.bairro, endereco.cidade "
				+ "FROM assalariado join endereco on assalariado.codigo = endereco.assalariado_codigo "
				+ "WHERE assalariado.codigo = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			assalariado.setCodigo(rs.getInt("codigo"));
			assalariado.setNome(rs.getString("nome"));
			assalariado.setSalarioMensal(rs.getDouble("salario_mensal"));
			endereco.setBairro(rs.getString("bairro"));
			endereco.setCidade(rs.getString("rua"));
			endereco.setRua(rs.getString("numero"));
			assalariado.setEndereco(endereco);
		} 
		stmt.close();
		conn.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public List<Assalariado> consultarAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
