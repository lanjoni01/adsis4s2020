package leonardoLanjoni.aula21082020;

public class Pessoa {
	private Long id;
	private String nome;
	private Double altura;
	
	public Pessoa(Long id, String nome, Double altura) {
		this.id = id;
		this.nome = nome;
		this.altura = altura;
	}
	
	public Pessoa(String nome, Double altura) {
		this.nome = nome;
		this.altura = altura;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", altura=" + altura + "]";
	}
}