package leonardoLanjoni.aula21082020;

	import java.sql.SQLException;
	import java.util.List;
	public class AppRepository {
		
		public static void main(String[] args) throws SQLException {
			ConnectionManager connManager = new ConnectionManager();
			try {
				PessoaRepository repo = new PessoaRepository(connManager);
				
				Pessoa pessoa = new Pessoa("Leonardo Lanjoni", 1.75);
				repo.save(pessoa);
				connManager.commit();
				
				Long id = pessoa.getId();
				System.out.println("Procurando pelo id: " + id);

				Pessoa pessoaBuscada = repo.findById(id);
				System.out.println(pessoaBuscada.toString());

				List<Pessoa> pessoas = repo.findAll();
				
				pessoas.forEach(pessoaAll -> System.out.println("\n "+pessoaAll.toString() +"\n"));
				
			} catch (Exception e) {
				e.printStackTrace();			
			} finally {
				connManager.close();
			}
		}

	}
