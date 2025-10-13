package proyecto;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class GestorTareas {
    private Stack<Tarea> pilaPrioritaria;
    private Queue<Tarea> colaEspera;
    private Map<String, String> registroTrazabilidad;
  
    public GestorTareas() {
        this.pilaPrioritaria = new Stack<>();
        this.colaEspera = new LinkedList<>();
        this.registroTrazabilidad = new HashMap<>();
    }
    
    /**
     * Agrega una tarea al sistema según su nivel de prioridad.
     * Las tareas con prioridad alta (3) van a la pila prioritaria.
     * Las tareas con prioridad media (2) o baja (1) van a la cola de espera.
     * 
     * @param tarea La tarea a agregar al sistema
     */
    public void agregarTarea(Tarea tarea) {
        if (tarea == null) {
            throw new IllegalArgumentException("No se puede agregar una tarea nula");
        } 

        if (tarea.getPrioridad() == 3) {
            pilaPrioritaria.push(tarea);
            System.out.println("Tarea agregada a PILA PRIORITARIA: " + tarea.getId());
        } else {
            colaEspera.offer(tarea);
            System.out.println("Tarea agregada a COLA DE ESPERA: " + tarea.getId());
        }
    }
    
    /**
     * Procesa la siguiente tarea disponible siguiendo el orden de prioridad.
     * Primero revisa la pila prioritaria, luego la cola de espera.
     * La tarea procesada se registra en el mapa de trazabilidad.
     * 
     * @return La tarea que fue procesada, o null si no hay tareas pendientes
     */
    public Tarea procesarSiguienteTarea() {
        Tarea tareaAProcesar = null;
        String origen = "";
        if (!pilaPrioritaria.isEmpty()) {
            tareaAProcesar = pilaPrioritaria.pop();
            origen = "Pila Prioritaria";
        } 

        else if (!colaEspera.isEmpty()) {
            tareaAProcesar = colaEspera.poll();
            origen = "Cola de Espera";
        }
        
        if (tareaAProcesar != null) {
            long tiempoActual = System.currentTimeMillis();
            long tiempoEnSistema = tiempoActual - tareaAProcesar.getTiempoLlegada();
            String infoCompletada = "Completada - Origen: " + origen + 
                                   ", Tiempo en sistema: " + tiempoEnSistema + "ms, " +
                                   "Prioridad: " + tareaAProcesar.getNombrePrioridad();
            
            registroTrazabilidad.put(tareaAProcesar.getId(), infoCompletada);
            
            System.out.println("\n=== TAREA PROCESADA ===");
            System.out.println(tareaAProcesar);
            System.out.println("Extraída de: " + origen);
            System.out.println("Tiempo en sistema: " + tiempoEnSistema + "ms");
            System.out.println("=======================\n");
            
            return tareaAProcesar;
        }
        
        System.out.println("No hay tareas pendientes para procesar.");
        return null;
    }
    
    /**
     * Consulta el estado actual de una tarea según su ID.
     * Busca primero en el registro de completadas, luego en las pendientes.
     * 
     * @param idTarea El identificador de la tarea a consultar
     * @return String con el estado de la tarea
     */
    public String consultarEstadoTarea(String idTarea) {
        if (idTarea == null || idTarea.trim().isEmpty()) {
            return "ID inválido";
        }
        
        if (registroTrazabilidad.containsKey(idTarea)) {
            return registroTrazabilidad.get(idTarea);
        }
        
        for (Tarea t : pilaPrioritaria) {
            if (t.getId().equals(idTarea)) {
                return "Pendiente en Pila Prioritaria - Prioridad: " + t.getNombrePrioridad();
            }
        }

        for (Tarea t : colaEspera) {
            if (t.getId().equals(idTarea)) {
                return "Pendiente en Cola de Espera - Prioridad: " + t.getNombrePrioridad();
            }
        }
        
        return "ID no encontrado";
    }
    
    public void mostrarEstadoSistema() {
        System.out.println("\n========== ESTADO DEL SISTEMA ==========");
        System.out.println("Tareas en Pila Prioritaria: " + pilaPrioritaria.size());
        System.out.println("Tareas en Cola de Espera: " + colaEspera.size());
        System.out.println("Tareas Completadas: " + registroTrazabilidad.size());
        System.out.println("========================================\n");
    }
    
    /**
     * Retorna el número total de tareas pendientes en el sistema
     * @return Cantidad de tareas sin procesar
     */
    public int getTareasPendientes() {
        return pilaPrioritaria.size() + colaEspera.size();
    }
    
    /**
     * Retorna el número de tareas completadas
     * @return Cantidad de tareas procesadas
     */
    public int getTareasCompletadas() {
        return registroTrazabilidad.size();
    }
}