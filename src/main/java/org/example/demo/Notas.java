package org.example.demo;

public class Notas {

    private int codigo;
    private String modulo;
    private String curso;
    private String cicloFormativo;
    private int nota;


    public Notas(int codigo, String modulo, String curso, int nota, String cicloFormativo) {
        this.codigo = codigo;
        this.modulo = modulo;
        this.curso = curso;
        this.nota = nota;
        this.cicloFormativo = cicloFormativo;
    }

    public Notas() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCicloFormativo() {
        return cicloFormativo;
    }

    public void setCicloFormativo(String cicloFormativo) {
        this.cicloFormativo = cicloFormativo;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Notas{");
        sb.append("codigo=").append(codigo);
        sb.append(", modulo='").append(modulo).append('\'');
        sb.append(", curso='").append(curso).append('\'');
        sb.append(", cicloFormativo='").append(cicloFormativo).append('\'');
        sb.append(", nota=").append(nota);
        sb.append('}');
        return sb.toString();
    }
}
