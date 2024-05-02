package cursos.bean;

import java.sql.Blob;

public class VideoBean {
    private int id;
    private String titulo;
    private Blob video; // Blob representa o vídeo como um objeto binário

    // Getters e Setters omitidos para brevidade

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

    public Blob getVideo() {
        return video;
    }

    public void setVideo(Blob video) {
        this.video = video;
    }
}
