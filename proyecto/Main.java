package proyecto;

public class Main {
    
    public static void main(String[ ] args) {
        System.out.println("==============================================");
        System.out.println("   SISTEMA DE GESTIÓN DE TAREAS ASÍNCRONAS   ");
        System.out.println("==============================================\n");
        
        GestorTareas gestor = new GestorTareas();
      
        System.out.println("--- FASE 1: Agregando tareas al sistema ---\n");
        gestor.agregarTarea(new Tarea("T001", "Actualizar documentación", 1));
        gestor.agregarTarea(new Tarea("T002", "Revisar correos pendientes", 1));
     
        esperarUnMomento();
       
        gestor.agregarTarea(new Tarea("T003", "Generar reporte mensual", 2));
        gestor.agregarTarea(new Tarea("T004", "Actualizar base de datos", 2));
        
        esperarUnMomento();

        gestor.agregarTarea(new Tarea("T005", "Error crítico en producción", 3));
        gestor.agregarTarea(new Tarea("T006", "Fallo de seguridad detectado", 3));
        
        esperarUnMomento();
       
        gestor.agregarTarea(new Tarea("T007", "Organizar reunión semanal", 1));
        gestor.agregarTarea(new Tarea("T008", "Backup de respaldo", 2));

        gestor.mostrarEstadoSistema();
        
        System.out.println("--- FASE 2: Consultando estados ---\n");
        System.out.println("Estado de T001: " + gestor.consultarEstadoTarea("T001"));
        System.out.println("Estado de T005: " + gestor.consultarEstadoTarea("T005"));
        System.out.println("Estado de T999: " + gestor.consultarEstadoTarea("T999"));
        System.out.println();
        
        System.out.println("--- FASE 3: Procesando tareas ---\n");
        
        gestor.procesarSiguienteTarea();
        esperarUnMomento();
        gestor.procesarSiguienteTarea();
        esperarUnMomento();
        gestor.procesarSiguienteTarea();
        esperarUnMomento();
        
        System.out.println("\n--- Nueva tarea urgente agregada durante el procesamiento ---\n");
        gestor.agregarTarea(new Tarea("T009", "Interrupción del servidor", 3));
        esperarUnMomento();
        gestor.procesarSiguienteTarea();
        esperarUnMomento();
        
        System.out.println("--- Procesando tareas restantes ---\n");
        while (gestor.getTareasPendientes() > 0) {
            gestor.procesarSiguienteTarea();
            esperarUnMomento();
        }
   
        System.out.println("\n--- Intentando procesar sin tareas pendientes ---\n");
        gestor.procesarSiguienteTarea();
        gestor.mostrarEstadoSistema();
        
        System.out.println("--- FASE 4: Verificando trazabilidad ---\n");
        System.out.println("Estado de T005: " + gestor.consultarEstadoTarea("T005"));
        System.out.println("Estado de T001: " + gestor.consultarEstadoTarea("T001"));
        System.out.println("Estado de T009: " + gestor.consultarEstadoTarea("T009"));
        
        System.out.println("\n==============================================");
        System.out.println("       DEMOSTRACIÓN FINALIZADA");
        System.out.println("Total tareas procesadas: " + gestor.getTareasCompletadas());
        System.out.println("==============================================");
    }
    
    private static void esperarUnMomento() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // En una aplicación real se manejaría mejor
            Thread.currentThread().interrupt();
        }
    }
}