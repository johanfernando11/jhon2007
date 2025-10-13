package proyecto;

public class Tarea {
    private String id;
    private String descripcion;
    private int prioridad;
    private long tiempoLlegada;
    
    /**
     * Constructor de la clase Tarea
     * @param id Identificador único de la tarea
     * @param descripcion Descripción detallada de la tarea
     * @param prioridad Nivel de prioridad: 1 (Baja), 2 (Media), 3 (Alta)
     */
    public Tarea(String id, String descripcion, int prioridad) {
        this.id = id;
        this.descripcion = descripcion;
        
        if (prioridad < 1 || prioridad > 3) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 3");
        }
        this.prioridad = prioridad;
        
        this.tiempoLlegada = System.currentTimeMillis();
    }
    
    public String getId() {
        return id;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public int getPrioridad() {
        return prioridad;
    }
    
    public long getTiempoLlegada() {
        return tiempoLlegada;
    }
    
    /**
     * Devuelve el nombre descriptivo de la prioridad
     * @return String con el nivel de prioridad
     */
    public String getNombrePrioridad() {
        switch (prioridad) {
            case 1:
                return "Baja";
            case 2:
                return "Media";
            case 3:
                return "Alta";
            default:
                return "Desconocida";
        }
    }
    
    @Override
    public String toString() {
        return "Tarea [ID: " + id + ", Descripción: " + descripcion + 
               ", Prioridad: " + getNombrePrioridad() + " (" + prioridad + ")]";
    }
}