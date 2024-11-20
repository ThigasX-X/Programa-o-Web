package model;

public class Filme {
    private int id;
    private String nome;
    private String descricao;
    private String data_inclusao;

    public Filme() {}

    public Filme(String nome, String descricao, String data_inclusao) {
        this.nome = nome;
        this.descricao = descricao;
        this.data_inclusao = data_inclusao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData_inclusao() {
        return data_inclusao;
    }

    public void setData_inclusao(String data_inclusao) {
        this.data_inclusao = data_inclusao;
    }
}