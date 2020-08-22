package leonardoLanjoni.aula21082020;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PessoaRepository {
	
	private ConnectionManager connectionManager;
	
	public PessoaRepository(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
		this.createTable();		
	}

	private void createTable() {
		PreparedStatement createTable = null;
		try {
			connectionManager.prepareStatement("create table if not exists pessoa (" + 
				"id long not null primary key," + 
				"nome varchar(255) not null," + 
				"altura number(9,2) not null" + 
				")");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (createTable != null) {
				try {
					createTable.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	void save(Pessoa pessoa) {
		boolean existe = !Objects.isNull(pessoa.getId());
		PreparedStatement save = null;
		
		if(existe) {
			try {
				save = connectionManager.prepareStatement("update pessoa set nome= ?, altura= ? where id = ?");
				setCamposObrigatorios(pessoa, save);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					save.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else {
			try {
				save = connectionManager.prepareStatement("insert into pessoa (nome, altura, id) values(?,?,?");
				setCamposObrigatorios(pessoa, save);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					save.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public List<Pessoa> findAll() throws SQLException {
		List<Pessoa> all = new ArrayList<>();
		PreparedStatement psSelect = connectionManager.prepareStatement("select id, nome, altura from pessoa");
		ResultSet rsSelect = psSelect.executeQuery();
		try {
			while (rsSelect.next()) {
				Pessoa pessoa= new Pessoa(
						rsSelect.getLong("id"), 
						rsSelect.getString("nome"), 
						rsSelect.getDouble("altura"));
				all.add(pessoa);
			}
		} finally {
			rsSelect.close();
			psSelect.close();
		}
		return all;
	}
	
	public Pessoa findById(Long id) throws SQLException {
		Pessoa found = null;
		PreparedStatement selectId = connectionManager.prepareStatement("select id, nome, altura from pessoa where id = ?");
		selectId.setLong(1, id);
		ResultSet rsSelect = selectId.executeQuery();
		try {
			if (rsSelect.next()) {
				found = new Pessoa(
						rsSelect.getLong("id"), 
						rsSelect.getString("nome"), 
						rsSelect.getDouble("metragem")); 
			}
		} finally {
			rsSelect.close();
			selectId.close();
		}
		return found;
	}

	private PreparedStatement setCamposObrigatorios(Pessoa pessoa, PreparedStatement save){
		try {
			save.setString(1, pessoa.getNome());
			save.setDouble(2, pessoa.getAltura());
			save.setLong(3, pessoa.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return save;
	}
}