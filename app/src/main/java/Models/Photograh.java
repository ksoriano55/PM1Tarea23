package Models;
import java.sql.Blob;

public class Photograh {
    private int id;
    private String Descripcion;
    private byte[] Imagen;

    public Photograh(int Id, String descripcion, byte[] imagen) {
        id = Id;
        Descripcion = descripcion;
        Imagen = imagen;
    }
    public Photograh() {
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public byte[] getImagen() {
        return Imagen;
    }

    public void setImagen(byte[] imagen) {
        Imagen = imagen;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
