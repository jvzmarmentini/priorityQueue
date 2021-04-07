/**
 * Tarefa
 */
public class Tarefa implements Comparable<Tarefa> {
    private String nome;
    private Integer prioridade;

    public Tarefa(String nome, int prioridade) {
        this.nome = nome;
        this.prioridade = prioridade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public int compareTo(Tarefa t) {
        return this.getPrioridade().compareTo(t.getPrioridade());
    }

    @Override
    public String toString() {
        return "nome= " + nome + ", prioridade= " + prioridade;
    }

}