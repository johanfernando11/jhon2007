import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Paquete {
    private final String codigo;
    private final String destinatario;
    private final Tamano tamano;
    private final LocalDateTime fechaIngreso;

    public Paquete(String codigo, String destinatario, Tamano tamano) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.tamano = tamano;
        this.fechaIngreso = LocalDateTime.now();
    }

    public String getCodigo() { return codigo; }
    public String getDestinatario() { return destinatario; }
    public Tamano getTamano() { return tamano; }
    public LocalDateTime getFechaIngreso() { return fechaIngreso; }

    public String fechaIngresoFmt() {
        return fechaIngreso.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "Paquete{" +
                "codigo='" + codigo + '\'' +
                ", destinatario='" + destinatario + '\'' +
                ", tamano=" + tamano +
                ", fechaIngreso=" + fechaIngresoFmt() +
                '}';
    }
}
