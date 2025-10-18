import java.util.*;

// ==================== CLASE LIBRO ====================
class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private String categoria;
    private boolean disponible;

    public Libro(String isbn, String titulo, String autor, String categoria) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.disponible = true;
    }

    // Getters y Setters
    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCategoria() { return categoria; }
    public boolean isDisponible() { return disponible; }
    
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return String.format("ISBN: %s | Título: %s | Autor: %s | Categoría: %s | Disponible: %s",
                isbn, titulo, autor, categoria, disponible ? "Sí" : "No");
    }
}

// ==================== CLASE USUARIO ====================
class Usuario {
    private String id;
    private String nombre;
    private String email;
    private LinkedList<String> historialPrestamos; // LinkedList para historial

    public Usuario(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.historialPrestamos = new LinkedList<>();
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public LinkedList<String> getHistorialPrestamos() { return historialPrestamos; }

    public void agregarPrestamo(String prestamo) {
        historialPrestamos.add(prestamo);
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Nombre: %s | Email: %s | Préstamos realizados: %d",
                id, nombre, email, historialPrestamos.size());
    }
}

// ==================== CLASE PRÉSTAMO ====================
class Prestamo {
    private String idPrestamo;
    private String isbnLibro;
    private String idUsuario;
    private Date fechaPrestamo;

    public Prestamo(String idPrestamo, String isbnLibro, String idUsuario) {
        this.idPrestamo = idPrestamo;
        this.isbnLibro = isbnLibro;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = new Date();
    }

    public String getIdPrestamo() { return idPrestamo; }
    public String getIsbnLibro() { return isbnLibro; }
    public String getIdUsuario() { return idUsuario; }

    @Override
    public String toString() {
        return String.format("Préstamo #%s | Libro ISBN: %s | Usuario: %s | Fecha: %s",
                idPrestamo, isbnLibro, idUsuario, fechaPrestamo);
    }
}

// ==================== CLASE RESERVA ====================
class Reserva {
    private String isbnLibro;
    private String idUsuario;
    private Date fechaReserva;

    public Reserva(String isbnLibro, String idUsuario) {
        this.isbnLibro = isbnLibro;
        this.idUsuario = idUsuario;
        this.fechaReserva = new Date();
    }

    public String getIsbnLibro() { return isbnLibro; }
    public String getIdUsuario() { return idUsuario; }

    @Override
    public String toString() {
        return String.format("Reserva | Libro ISBN: %s | Usuario: %s | Fecha: %s",
                isbnLibro, idUsuario, fechaReserva);
    }
}

// ==================== MÓDULO 1: GESTIÓN DE LIBROS ====================
class GestionLibros {
    private ArrayList<Libro> libros;

    public GestionLibros() {
        this.libros = new ArrayList<>();
    }

    // CRUD - Create
    public void agregarLibro(Libro libro) {
        if (buscarPorISBN(libro.getIsbn()) != null) {
            System.out.println("❌ Error: Ya existe un libro con ese ISBN");
            return;
        }
        libros.add(libro);
        System.out.println("✅ Libro agregado exitosamente");
    }

    // CRUD - Read (Listar todos)
    public void listarLibros() {
        if (libros.isEmpty()) {
            System.out.println("📚 No hay libros registrados");
            return;
        }
        System.out.println("\n📚 LISTA DE LIBROS:");
        System.out.println("═══════════════════════════════════════════════════════════════");
        for (Libro libro : libros) {
            System.out.println(libro);
        }
        System.out.println("═══════════════════════════════════════════════════════════════");
    }

    // CRUD - Update
    public void actualizarLibro(String isbn, String nuevoTitulo, String nuevoAutor, String nuevaCategoria) {
        Libro libro = buscarPorISBN(isbn);
        if (libro == null) {
            System.out.println("❌ Libro no encontrado");
            return;
        }
        if (nuevoTitulo != null && !nuevoTitulo.isEmpty()) libro.setTitulo(nuevoTitulo);
        if (nuevoAutor != null && !nuevoAutor.isEmpty()) libro.setAutor(nuevoAutor);
        if (nuevaCategoria != null && !nuevaCategoria.isEmpty()) libro.setCategoria(nuevaCategoria);
        System.out.println("✅ Libro actualizado exitosamente");
    }

    // CRUD - Delete
    public void eliminarLibro(String isbn) {
        Libro libro = buscarPorISBN(isbn);
        if (libro == null) {
            System.out.println("❌ Libro no encontrado");
            return;
        }
        if (!libro.isDisponible()) {
            System.out.println("❌ No se puede eliminar: el libro está prestado");
            return;
        }
        libros.remove(libro);
        System.out.println("✅ Libro eliminado exitosamente");
    }

    // Búsqueda por ISBN
    public Libro buscarPorISBN(String isbn) {
        for (Libro libro : libros) {
            if (libro.getIsbn().equalsIgnoreCase(isbn)) {
                return libro;
            }
        }
        return null;
    }

    // Búsqueda por Título
    public void buscarPorTitulo(String titulo) {
        boolean encontrado = false;
        System.out.println("\n🔍 Resultados de búsqueda por título:");
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                System.out.println(libro);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("❌ No se encontraron libros con ese título");
        }
    }

    // Búsqueda por Autor
    public void buscarPorAutor(String autor) {
        boolean encontrado = false;
        System.out.println("\n🔍 Resultados de búsqueda por autor:");
        for (Libro libro : libros) {
            if (libro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                System.out.println(libro);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("❌ No se encontraron libros de ese autor");
        }
    }

    // Búsqueda por Categoría
    public void buscarPorCategoria(String categoria) {
        boolean encontrado = false;
        System.out.println("\n🔍 Resultados de búsqueda por categoría:");
        for (Libro libro : libros) {
            if (libro.getCategoria().equalsIgnoreCase(categoria)) {
                System.out.println(libro);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("❌ No se encontraron libros en esa categoría");
        }
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }
}

// ==================== MÓDULO 2: GESTIÓN DE USUARIOS ====================
class GestionUsuarios {
    private ArrayList<Usuario> usuarios; // ArrayList para usuarios

    public GestionUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    // Registrar usuario
    public void registrarUsuario(Usuario usuario) {
        if (buscarUsuarioPorId(usuario.getId()) != null) {
            System.out.println("❌ Error: Ya existe un usuario con ese ID");
            return;
        }
        usuarios.add(usuario);
        System.out.println("✅ Usuario registrado exitosamente");
    }

    // Buscar usuario por ID
    public Usuario buscarUsuarioPorId(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equalsIgnoreCase(id)) {
                return usuario;
            }
        }
        return null;
    }

    // Mostrar usuarios usando Iterator
    public void mostrarUsuariosConIterator() {
        if (usuarios.isEmpty()) {
            System.out.println("👥 No hay usuarios registrados");
            return;
        }
        System.out.println("\n👥 LISTA DE USUARIOS (usando Iterator):");
        System.out.println("═══════════════════════════════════════════════════════════════");
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            System.out.println(usuario);
        }
        System.out.println("═══════════════════════════════════════════════════════════════");
    }

    // Buscar usuario con Iterator
    public void buscarUsuarioConIterator(String busqueda) {
        boolean encontrado = false;
        System.out.println("\n🔍 Resultados de búsqueda:");
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getId().toLowerCase().contains(busqueda.toLowerCase()) ||
                usuario.getNombre().toLowerCase().contains(busqueda.toLowerCase())) {
                System.out.println(usuario);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("❌ No se encontraron usuarios");
        }
    }

    // Mostrar historial de préstamos de un usuario
    public void mostrarHistorialPrestamos(String idUsuario) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            System.out.println("❌ Usuario no encontrado");
            return;
        }
        
        LinkedList<String> historial = usuario.getHistorialPrestamos();
        if (historial.isEmpty()) {
            System.out.println("📋 El usuario no tiene préstamos registrados");
            return;
        }

        System.out.println("\n📋 HISTORIAL DE PRÉSTAMOS de " + usuario.getNombre() + ":");
        System.out.println("═══════════════════════════════════════════════════════════════");
        Iterator<String> iterator = historial.iterator();
        int contador = 1;
        while (iterator.hasNext()) {
            System.out.println(contador + ". " + iterator.next());
            contador++;
        }
        System.out.println("═══════════════════════════════════════════════════════════════");
    }

    // Reporte básico: total de usuarios
    public void reporteTotalUsuarios() {
        System.out.println("\n📊 REPORTE: Total de usuarios registrados: " + usuarios.size());
    }

    // Reporte básico: usuarios con más préstamos
    public void reporteUsuariosActivos() {
        if (usuarios.isEmpty()) {
            System.out.println("📊 No hay usuarios registrados");
            return;
        }

        System.out.println("\n📊 REPORTE: Usuarios más activos:");
        System.out.println("═══════════════════════════════════════════════════════════════");
        
        ArrayList<Usuario> usuariosOrdenados = new ArrayList<>(usuarios);
        usuariosOrdenados.sort((u1, u2) -> 
            Integer.compare(u2.getHistorialPrestamos().size(), u1.getHistorialPrestamos().size())
        );

        for (int i = 0; i < Math.min(5, usuariosOrdenados.size()); i++) {
            Usuario u = usuariosOrdenados.get(i);
            System.out.println((i+1) + ". " + u.getNombre() + " - " + 
                             u.getHistorialPrestamos().size() + " préstamos");
        }
        System.out.println("═══════════════════════════════════════════════════════════════");
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
}

// ==================== MÓDULO 3: SISTEMA DE PRÉSTAMOS ====================
class SistemaPrestamos {
    private Stack<Prestamo> historialOperaciones; // Stack para deshacer
    private Queue<Reserva> colaEspera; // Queue para reservas FIFO
    private int contadorPrestamos;

    public SistemaPrestamos() {
        this.historialOperaciones = new Stack<>();
        this.colaEspera = new LinkedList<>();
        this.contadorPrestamos = 1;
    }

    // Realizar préstamo
    public void realizarPrestamo(GestionLibros gestionLibros, GestionUsuarios gestionUsuarios, 
                                 String isbn, String idUsuario) {
        Libro libro = gestionLibros.buscarPorISBN(isbn);
        Usuario usuario = gestionUsuarios.buscarUsuarioPorId(idUsuario);

        if (libro == null) {
            System.out.println("❌ Libro no encontrado");
            return;
        }
        if (usuario == null) {
            System.out.println("❌ Usuario no encontrado");
            return;
        }
        if (!libro.isDisponible()) {
            System.out.println("❌ El libro no está disponible");
            return;
        }

        // Realizar el préstamo
        String idPrestamo = "P" + String.format("%03d", contadorPrestamos++);
        Prestamo prestamo = new Prestamo(idPrestamo, isbn, idUsuario);
        
        // Actualizar estado del libro
        libro.setDisponible(false);
        
        // Agregar al historial del usuario (LinkedList)
        usuario.agregarPrestamo("Préstamo " + idPrestamo + ": " + libro.getTitulo() + " - " + new Date());
        
        // Agregar a Stack de operaciones (para deshacer)
        historialOperaciones.push(prestamo);

        System.out.println("✅ Préstamo realizado exitosamente");
        System.out.println("📖 " + prestamo);
    }

    // Deshacer último préstamo (usando Stack - LIFO)
    public void deshacerUltimoPrestamo(GestionLibros gestionLibros) {
        if (historialOperaciones.isEmpty()) {
            System.out.println("❌ No hay operaciones para deshacer");
            return;
        }

        Prestamo ultimoPrestamo = historialOperaciones.pop();
        Libro libro = gestionLibros.buscarPorISBN(ultimoPrestamo.getIsbnLibro());
        
        if (libro != null) {
            libro.setDisponible(true);
            System.out.println("✅ Préstamo deshecho exitosamente");
            System.out.println("📖 Libro devuelto: " + libro.getTitulo());
        }
    }

    // Mostrar historial de operaciones (Stack)
    public void mostrarHistorialOperaciones() {
        if (historialOperaciones.isEmpty()) {
            System.out.println("📚 No hay operaciones registradas");
            return;
        }

        System.out.println("\n📚 HISTORIAL DE OPERACIONES (Stack - LIFO):");
        System.out.println("═══════════════════════════════════════════════════════════════");
        
        Stack<Prestamo> temp = new Stack<>();
        temp.addAll(historialOperaciones);
        
        while (!temp.isEmpty()) {
            System.out.println(temp.pop());
        }
        System.out.println("═══════════════════════════════════════════════════════════════");
    }

    // Agregar reserva a la cola (Queue - FIFO)
    public void agregarReserva(GestionLibros gestionLibros, GestionUsuarios gestionUsuarios,
                              String isbn, String idUsuario) {
        Libro libro = gestionLibros.buscarPorISBN(isbn);
        Usuario usuario = gestionUsuarios.buscarUsuarioPorId(idUsuario);

        if (libro == null) {
            System.out.println("❌ Libro no encontrado");
            return;
        }
        if (usuario == null) {
            System.out.println("❌ Usuario no encontrado");
            return;
        }

        Reserva reserva = new Reserva(isbn, idUsuario);
        colaEspera.offer(reserva); // offer() es el método de Queue
        
        System.out.println("✅ Reserva agregada a la cola de espera");
        System.out.println("📋 Posición en la cola: " + colaEspera.size());
    }

    // Procesar siguiente reserva (Queue - FIFO)
    public void procesarSiguienteReserva(GestionLibros gestionLibros, GestionUsuarios gestionUsuarios) {
        if (colaEspera.isEmpty()) {
            System.out.println("❌ No hay reservas en espera");
            return;
        }

        Reserva reserva = colaEspera.poll(); // poll() extrae el primero (FIFO)
        System.out.println("📋 Procesando reserva:");
        System.out.println(reserva);

        // Intentar realizar el préstamo
        realizarPrestamo(gestionLibros, gestionUsuarios, 
                        reserva.getIsbnLibro(), reserva.getIdUsuario());
    }

    // Mostrar cola de espera
    public void mostrarColaEspera() {
        if (colaEspera.isEmpty()) {
            System.out.println("📋 No hay reservas en espera");
            return;
        }

        System.out.println("\n📋 COLA DE ESPERA (Queue - FIFO):");
        System.out.println("═══════════════════════════════════════════════════════════════");
        
        int posicion = 1;
        for (Reserva reserva : colaEspera) {
            System.out.println("Posición " + posicion + ": " + reserva);
            posicion++;
        }
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
}

// ==================== CLASE PRINCIPAL ====================
public class SistemaBiblioteca {
    private static GestionLibros gestionLibros = new GestionLibros();
    private static GestionUsuarios gestionUsuarios = new GestionUsuarios();
    private static SistemaPrestamos sistemaPrestamos = new SistemaPrestamos();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Cargar datos de ejemplo
        cargarDatosEjemplo();

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1: menuGestionLibros(); break;
                case 2: menuGestionUsuarios(); break;
                case 3: menuSistemaPrestamos(); break;
                case 0: System.out.println("\n👋 ¡Hasta luego!"); break;
                default: System.out.println("❌ Opción inválida");
            }
        } while (opcion != 0);
        
        scanner.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║          📚 SISTEMA DE GESTIÓN DE BIBLIOTECA 📚              ║");
        System.out.println("╠═══════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. 📖 Módulo 1: Gestión de Libros                           ║");
        System.out.println("║  2. 👥 Módulo 2: Gestión de Usuarios                         ║");
        System.out.println("║  3. 🔄 Módulo 3: Sistema de Préstamos                        ║");
        System.out.println("║  0. 🚪 Salir                                                  ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    // ========== MENÚ MÓDULO 1: GESTIÓN DE LIBROS ==========
    private static void menuGestionLibros() {
        int opcion;
        do {
            System.out.println("\n╔═══════════════════════════════════════════════════════════════╗");
            System.out.println("║              📖 MÓDULO 1: GESTIÓN DE LIBROS                   ║");
            System.out.println("╠═══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. ➕ Agregar libro                                          ║");
            System.out.println("║  2. 📋 Listar todos los libros                                ║");
            System.out.println("║  3. ✏  Actualizar libro                                      ║");
            System.out.println("║  4. ❌ Eliminar libro                                         ║");
            System.out.println("║  5. 🔍 Buscar por ISBN                                        ║");
            System.out.println("║  6. 🔍 Buscar por título                                      ║");
            System.out.println("║  7. 🔍 Buscar por autor                                       ║");
            System.out.println("║  8. 🔍 Buscar por categoría                                   ║");
            System.out.println("║  0. ⬅  Volver al menú principal                             ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════╝");
            
            opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1: agregarLibro(); break;
                case 2: gestionLibros.listarLibros(); break;
                case 3: actualizarLibro(); break;
                case 4: eliminarLibro(); break;
                case 5: buscarPorISBN(); break;
                case 6: buscarPorTitulo(); break;
                case 7: buscarPorAutor(); break;
                case 8: buscarPorCategoria(); break;
                case 0: break;
                default: System.out.println("❌ Opción inválida");
            }
        } while (opcion != 0);
    }

    private static void agregarLibro() {
        System.out.println("\n➕ AGREGAR NUEVO LIBRO");
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();
        
        Libro libro = new Libro(isbn, titulo, autor, categoria);
        gestionLibros.agregarLibro(libro);
    }

    private static void actualizarLibro() {
        System.out.println("\n✏ ACTUALIZAR LIBRO");
        System.out.print("ISBN del libro a actualizar: ");
        String isbn = scanner.nextLine();
        System.out.print("Nuevo título (Enter para no cambiar): ");
        String titulo = scanner.nextLine();
        System.out.print("Nuevo autor (Enter para no cambiar): ");
        String autor = scanner.nextLine();
        System.out.print("Nueva categoría (Enter para no cambiar): ");
        String categoria = scanner.nextLine();
        
        gestionLibros.actualizarLibro(isbn, titulo, autor, categoria);
    }

    private static void eliminarLibro() {
        System.out.println("\n❌ ELIMINAR LIBRO");
        System.out.print("ISBN del libro a eliminar: ");
        String isbn = scanner.nextLine();
        gestionLibros.eliminarLibro(isbn);
    }

    private static void buscarPorISBN() {
        System.out.print("\n🔍 ISBN a buscar: ");
        String isbn = scanner.nextLine();
        Libro libro = gestionLibros.buscarPorISBN(isbn);
        if (libro != null) {
            System.out.println("\n✅ Libro encontrado:");
            System.out.println(libro);
        } else {
            System.out.println("❌ Libro no encontrado");
        }
    }

    private static void buscarPorTitulo() {
        System.out.print("\n🔍 Título a buscar: ");
        String titulo = scanner.nextLine();
        gestionLibros.buscarPorTitulo(titulo);
    }

    private static void buscarPorAutor() {
        System.out.print("\n🔍 Autor a buscar: ");
        String autor = scanner.nextLine();
        gestionLibros.buscarPorAutor(autor);
    }

    private static void buscarPorCategoria() {
        System.out.print("\n🔍 Categoría a buscar: ");
        String categoria = scanner.nextLine();
        gestionLibros.buscarPorCategoria(categoria);
    }

    // ========== MENÚ MÓDULO 2: GESTIÓN DE USUARIOS ==========
    private static void menuGestionUsuarios() {
        int opcion;
        do {
            System.out.println("\n╔═══════════════════════════════════════════════════════════════╗");
            System.out.println("║             👥 MÓDULO 2: GESTIÓN DE USUARIOS                  ║");
            System.out.println("╠═══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. ➕ Registrar usuario                                      ║");
            System.out.println("║  2. 📋 Mostrar usuarios (con Iterator)                        ║");
            System.out.println("║  3. 🔍 Buscar usuario (con Iterator)                          ║");
            System.out.println("║  4. 📜 Ver historial de préstamos de usuario                  ║");
            System.out.println("║  5. 📊 Reporte: Total de usuarios                             ║");
            System.out.println("║  6. 📊 Reporte: Usuarios más activos                          ║");
            System.out.println("║  0. ⬅  Volver al menú principal                             ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════╝");
            
            opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1: registrarUsuario(); break;
                case 2: gestionUsuarios.mostrarUsuariosConIterator(); break;
                case 3: buscarUsuario(); break;
                case 4: verHistorialUsuario(); break;
                case 5: gestionUsuarios.reporteTotalUsuarios(); break;
                case 6: gestionUsuarios.reporteUsuariosActivos(); break;
                case 0: break;
                default: System.out.println("❌ Opción inválida");
            }
        } while (opcion != 0);
    }

    private static void registrarUsuario() {
        System.out.println("\n➕ REGISTRAR NUEVO USUARIO");
        System.out.print("ID de usuario: ");
        String id = scanner.nextLine();
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        Usuario usuario = new Usuario(id, nombre, email);
        gestionUsuarios.registrarUsuario(usuario);
    }

    private static void buscarUsuario() {
        System.out.print("\n🔍 Buscar por ID o nombre: ");
        String busqueda = scanner.nextLine();
        gestionUsuarios.buscarUsuarioConIterator(busqueda);
    }

    private static void verHistorialUsuario() {
        System.out.print("\n📜 ID del usuario: ");
        String id = scanner.nextLine();
        gestionUsuarios.mostrarHistorialPrestamos(id);
    }

    // ========== MENÚ MÓDULO 3: SISTEMA DE PRÉSTAMOS ==========
    private static void menuSistemaPrestamos() {
        int opcion;
        do {
            System.out.println("\n╔═══════════════════════════════════════════════════════════════╗");
            System.out.println("║           🔄 MÓDULO 3: SISTEMA DE PRÉSTAMOS                   ║");
            System.out.println("╠═══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. 📤 Realizar préstamo                                      ║");
            System.out.println("║  2. ↩  Deshacer último préstamo (Stack)                      ║");
            System.out.println("║  3. 📚 Ver historial de operaciones (Stack)                   ║");
            System.out.println("║  4. 📋 Agregar reserva a cola (Queue)                         ║");
            System.out.println("║  5. ⏭  Procesar siguiente reserva (Queue)                    ║");
            System.out.println("║  6. 👁  Ver cola de espera (Queue)                            ║");
            System.out.println("║  0. ⬅  Volver al menú principal                             ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════╝");
            
            opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1: realizarPrestamo(); break;
                case 2: sistemaPrestamos.deshacerUltimoPrestamo(gestionLibros); break;
                case 3: sistemaPrestamos.mostrarHistorialOperaciones(); break;
                case 4: agregarReserva(); break;
                case 5: sistemaPrestamos.procesarSiguienteReserva(gestionLibros, gestionUsuarios); break;
                case 6: sistemaPrestamos.mostrarColaEspera(); break;
                case 0: break;
                default: System.out.println("❌ Opción inválida");
            }
        } while (opcion != 0);
    }

    private static void realizarPrestamo() {
        System.out.println("\n📤 REALIZAR PRÉSTAMO");
        System.out.print("ISBN del libro: ");
        String isbn = scanner.nextLine();
        System.out.print("ID del usuario: ");
        String idUsuario = scanner.nextLine();
        
        sistemaPrestamos.realizarPrestamo(gestionLibros, gestionUsuarios, isbn, idUsuario);
    }

    private static void agregarReserva() {
        System.out.println("\n📋 AGREGAR RESERVA");
        System.out.print("ISBN del libro: ");
        String isbn = scanner.nextLine();
        System.out.print("ID del usuario: ");
        String idUsuario = scanner.nextLine();
        
        sistemaPrestamos.agregarReserva(gestionLibros, gestionUsuarios, isbn, idUsuario);
    }

    // ========== MÉTODOS AUXILIARES ==========
    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor ingrese un número válido");
            }
        }
    }

    private static void cargarDatosEjemplo() {
        // Libros de ejemplo
        gestionLibros.agregarLibro(new Libro("978-1", "Cien Años de Soledad", "Gabriel García Márquez", "Ficción"));
        gestionLibros.agregarLibro(new Libro("978-2", "1984", "George Orwell", "Ciencia Ficción"));
        gestionLibros.agregarLibro(new Libro("978-3", "El Principito", "Antoine de Saint-Exupéry", "Infantil"));
        gestionLibros.agregarLibro(new Libro("978-4", "Don Quijote", "Miguel de Cervantes", "Clásicos"));
        gestionLibros.agregarLibro(new Libro("978-5", "Harry Potter", "J.K. Rowling", "Fantasía"));

        // Usuarios de ejemplo
        gestionUsuarios.registrarUsuario(new Usuario("U001", "Juan Pérez", "juan@email.com"));
        gestionUsuarios.registrarUsuario(new Usuario("U002", "María López", "maria@email.com"));
        gestionUsuarios.registrarUsuario(new Usuario("U003", "Carlos Ruiz", "carlos@email.com"));

        System.out.println("✅ Datos de ejemplo cargados exitosamente");
    }
}