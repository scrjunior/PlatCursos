package cursos.bean;

import java.sql.Blob;

public class VideoBean {
    private int id;
    private String titulo;
    private Blob video; 

    

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
