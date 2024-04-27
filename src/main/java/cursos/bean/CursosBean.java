package cursos.bean;

import java.io.InputStream;

public class CursosBean {
    private int id;
    private String titulo;
    private String descricao;
    private int duracao;
    private double preco;
    private InputStream bannerInputStream;
    private String bannerBase64;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public InputStream getBannerInputStream() {
        return bannerInputStream;
    }

    public void setBannerInputStream(InputStream bannerInputStream) {
        this.bannerInputStream = bannerInputStream;
    }
    
    public String getBannerBase64() {
        return bannerBase64;
    }

    public void setBannerBase64(String bannerBase64) {
        this.bannerBase64 = bannerBase64;
    }
    
}