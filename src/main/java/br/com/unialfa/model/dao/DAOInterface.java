package br.com.unialfa.model.dao;

import java.util.List;

public interface DAOInterface<T> {
	public void salvar(T obj);
	public void atualizar(T obj);
	public void deletarById(Integer id);
	public T consultaById(int id);
	public List<T> consultarAll();
}
