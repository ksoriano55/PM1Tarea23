package Configuracion;

import java.sql.Blob;

public class Transaccion {
    public static final String DBName = "PM1T23KS";
    public static final String TableImagenes = "photograph";
    public static final String id = "id";
    public static final String descripcion = "descripcion";
    public static final String imagen = "imagen";
    public static final String CreateTablePhotograph = "Create table "+ TableImagenes +" ("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT, imagen BLOB )";

    public static final String DropTablePhotograph = "DROP TABLE IF EXISTS "+ TableImagenes;
    public static final String SelectAllPhotograph = "SELECT * FROM " + TableImagenes;
}
