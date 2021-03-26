package com.ifmg.agendanaee;

public class Aluno {
    private  long id;
    private String nome;
    private double ra;
    private String email;
    private String curso;
    private String periodo_ano;

    public Aluno(long id, String nome, Double ra, String email, String curso, String periodo_ano) {
        this.id = id;
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.curso = curso;
        this.periodo_ano = periodo_ano;
    }

    public Aluno( String nome, Double ra, String email, String curso, String periodo_ano) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.curso = curso;
        this.periodo_ano = periodo_ano;
    }


    public Aluno(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getRa() {
        return ra;
    }

    public void setRa(double ra) {
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPeriodo_ano() {
        return periodo_ano;
    }

    public void setPeriodo_ano(String periodo_ano) {
        this.periodo_ano = periodo_ano;
    }
}
