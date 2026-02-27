/* ============================================================
   SIGEGAN – Sistema Integral de Gestión Ganadera
   script.js – Lógica principal compartida del sistema
   ============================================================ */

/* ──────────────────────────────────────────────
   1. BASE DE DATOS SIMULADA (localStorage)
   Simula los usuarios y datos del sistema.
   En producción esto vendría de un servidor real.
────────────────────────────────────────────── */
const DB = {

  /* Usuarios del sistema con sus roles y credenciales */
  usuarios: [
    { id: 1, nombre: "Carlos Mendoza",   usuario: "admin",     clave: "admin123",   rol: "administrador" },
    { id: 2, nombre: "Luis Herrera",     usuario: "propietario1", clave: "prop123", rol: "propietario",   propietarioId: 1 },
    { id: 3, nombre: "Rosa Gutiérrez",   usuario: "propietario2", clave: "prop456", rol: "propietario",   propietarioId: 2 },
    { id: 4, nombre: "Dr. Andrés Ríos",  usuario: "veterinario",  clave: "vet123",  rol: "veterinario" },
  ],

  /* Propietarios de ganado */
  propietarios: [
    { id: 1, nombre: "Luis Herrera",     doc: "12345678", tel: "310-000-0001", email: "luis@correo.com" },
    { id: 2, nombre: "Rosa Gutiérrez",   doc: "87654321", tel: "320-000-0002", email: "rosa@correo.com" },
    { id: 3, nombre: "Marcos Castillo",  doc: "11223344", tel: "300-000-0003", email: "marcos@correo.com" },
  ],

  /* Lotes de ganado activos */
  lotes: [
    { id: 1, propietarioId: 1, propietarioNombre: "Luis Herrera",   cantidad: 25, fechaIngreso: "2026-03-01", estado: "activo" },
    { id: 2, propietarioId: 2, propietarioNombre: "Rosa Gutiérrez", cantidad: 40, fechaIngreso: "2026-03-10", estado: "activo" },
    { id: 3, propietarioId: 3, propietarioNombre: "Marcos Castillo",cantidad: 15, fechaIngreso: "2026-03-15", estado: "activo" },
  ],

  /* Historial de salidas (cobros generados) */
  salidas: [
    { id: 1, loteId: 99, propietarioId: 1, propietarioNombre: "Luis Herrera", cantidad: 10, dias: 20, preciodia: 30, total: 6000, fechaSalida: "2026-02-20" },
  ],

  /* Registros veterinarios */
  veterinario: [
    { id: 1, loteId: 1, propietarioId: 1, propietarioNombre: "Luis Herrera",   fecha: "2026-03-05", diagnostico: "Buen estado general", tratamiento: "Vitaminas A y B12", veterinario: "Dr. Andrés Ríos", costo: 150000 },
    { id: 2, loteId: 2, propietarioId: 2, propietarioNombre: "Rosa Gutiérrez", fecha: "2026-03-12", diagnostico: "Revisión preventiva",  tratamiento: "Desparasitante oral",   veterinario: "Dr. Andrés Ríos", costo: 200000 },
  ],

  /* Registros de alimentación */
  alimentacion: [
    { id: 1, loteId: 1, propietarioId: 1, propietarioNombre: "Luis Herrera",   fecha: "2026-03-20", tipo: "Pasto Natural",  cantidad: 200, unidad: "kg" },
    { id: 2, loteId: 2, propietarioId: 2, propietarioNombre: "Rosa Gutiérrez", fecha: "2026-03-21", tipo: "Concentrado",    cantidad: 150, unidad: "kg" },
    { id: 3, loteId: 3, propietarioId: 3, propietarioNombre: "Marcos Castillo",fecha: "2026-03-22", tipo: "Forraje",        cantidad: 100, unidad: "kg" },
  ],

  /* Historial de cambios del sistema */
  historial: [
    { id: 1, fecha: "2026-03-20 08:10", accion: "Ingreso de ganado",     usuario: "admin",        detalle: "Lote #3: 15 animales de Marcos Castillo" },
    { id: 2, fecha: "2026-03-21 10:25", accion: "Control veterinario",   usuario: "veterinario",  detalle: "Revisión preventiva en lote de Rosa Gutiérrez" },
    { id: 3, fecha: "2026-03-22 09:00", accion: "Registro alimentación", usuario: "veterinario",  detalle: "Forraje 100kg para lote de Marcos Castillo" },
    { id: 4, fecha: "2026-03-22 14:30", accion: "Inicio de sesión",      usuario: "propietario1", detalle: "Luis Herrera consultó su información" },
  ],

  /* Precio por animal por día (configurable) */
  precioAnimalDia: 30,

  /* Capacidad máxima de la hacienda */
  capacidadMax: 150,
};

/* Guarda la DB en localStorage (para persistencia durante la sesión) */
function guardarDB() {
  localStorage.setItem("sigegan_db", JSON.stringify(DB));
}

/* Carga la DB desde localStorage si existe */
function cargarDB() {
  const guardada = localStorage.getItem("sigegan_db");
  if (guardada) {
    const data = JSON.parse(guardada);
    Object.assign(DB, data);
  }
}

/* ──────────────────────────────────────────────
   2. AUTENTICACIÓN Y SESIÓN
   Manejo del usuario activo en localStorage.
────────────────────────────────────────────── */

/* Inicia sesión: valida credenciales y guarda sesión */
function iniciarSesion(usuario, clave) {
  const user = DB.usuarios.find(u => u.usuario === usuario && u.clave === clave);
  if (!user) return null;
  const sesion = { id: user.id, nombre: user.nombre, usuario: user.usuario, rol: user.rol, propietarioId: user.propietarioId || null };
  localStorage.setItem("sigegan_sesion", JSON.stringify(sesion));
  return sesion;
}

/* Obtiene la sesión activa */
function getSesion() {
  const s = localStorage.getItem("sigegan_sesion");
  return s ? JSON.parse(s) : null;
}

/* Cierra la sesión y redirige al login */
function cerrarSesion() {
  localStorage.removeItem("sigegan_sesion");
  window.location.href = "index.html";
}

/* Protege páginas: si no hay sesión o rol incorrecto, redirige */
function protegerPagina(rolRequerido) {
  const sesion = getSesion();
  if (!sesion) { window.location.href = "index.html"; return null; }
  if (sesion.rol !== rolRequerido) {
    // Redirige al dashboard correcto según el rol
    const rutas = { administrador: "admin.html", propietario: "propietario.html", veterinario: "veterinario.html" };
    window.location.href = rutas[sesion.rol] || "index.html";
    return null;
  }
  return sesion;
}

/* ──────────────────────────────────────────────
   3. UTILIDADES GENERALES
────────────────────────────────────────────── */

/* Formatea fecha de YYYY-MM-DD a DD/MM/YYYY */
function formatFecha(f) {
  if (!f) return "—";
  const p = f.split("-");
  return `${p[2]}/${p[1]}/${p[0]}`;
}

/* Fecha de hoy en formato YYYY-MM-DD */
function hoy() {
  return new Date().toISOString().split("T")[0];
}

/* Calcula días entre dos fechas */
function calcDias(ingreso, salida) {
  const a = new Date(ingreso);
  const b = new Date(salida || hoy());
  return Math.max(0, Math.round((b - a) / 86400000));
}

/* Formatea número como moneda */
function formatMoney(n) {
  return "$" + Number(n).toLocaleString("es-CO");
}

/* Calcula total de animales activos */
function totalAnimalesActivos() {
  return DB.lotes.filter(l => l.estado === "activo").reduce((s, l) => s + l.cantidad, 0);
}

/* ──────────────────────────────────────────────
   4. SISTEMA DE NAVEGACIÓN (SPA simple)
   Cambia entre secciones dentro de la misma página.
────────────────────────────────────────────── */
function mostrarSeccion(id) {
  document.querySelectorAll(".page-section").forEach(s => s.classList.remove("active"));
  document.querySelectorAll(".nav-item").forEach(n => n.classList.remove("active"));
  const seccion = document.getElementById("sec-" + id);
  if (seccion) seccion.classList.add("active");
  const navItem = document.querySelector(`[data-nav="${id}"]`);
  if (navItem) navItem.classList.add("active");
  // Actualiza el título del top-bar
  if (navItem) {
    const titulo = navItem.querySelector("span:last-child")?.textContent;
    const topTitle = document.getElementById("top-title");
    if (topTitle && titulo) topTitle.textContent = titulo;
  }
}

/* ──────────────────────────────────────────────
   5. SISTEMA DE NOTIFICACIONES (Toast)
────────────────────────────────────────────── */
function showToast(mensaje, tipo = "ok") {
  const container = document.getElementById("toast-container");
  if (!container) return;
  const icons = { ok: "✅", warn: "⚠️", error: "❌" };
  const toast = document.createElement("div");
  toast.className = `toast ${tipo === "warn" ? "warn" : tipo === "error" ? "error" : ""}`;
  toast.innerHTML = `<span>${icons[tipo]}</span><span>${mensaje}</span>`;
  container.appendChild(toast);
  setTimeout(() => toast.classList.add("show"), 50);
  setTimeout(() => { toast.classList.remove("show"); setTimeout(() => toast.remove(), 400); }, 3500);
}

/* ──────────────────────────────────────────────
   6. REGISTRO EN HISTORIAL
────────────────────────────────────────────── */
function logHistorial(accion, detalle) {
  const sesion = getSesion();
  const now = new Date();
  const fecha = `${now.toLocaleDateString("es-CO")} ${now.toLocaleTimeString("es-CO", { hour: "2-digit", minute: "2-digit" })}`;
  DB.historial.unshift({ id: Date.now(), fecha, accion, usuario: sesion?.usuario || "sistema", detalle });
  guardarDB();
}

/* ──────────────────────────────────────────────
   7. HELPERS DE RENDER COMPARTIDOS
────────────────────────────────────────────── */

/* Renderiza la barra de capacidad en cualquier página */
function renderCapacityBar(containerId) {
  const activos = totalAnimalesActivos();
  const pct = Math.round((activos / DB.capacidadMax) * 100);
  const fillClass = pct >= 90 ? "danger" : pct >= 70 ? "warn" : "";
  const el = document.getElementById(containerId);
  if (!el) return;
  el.innerHTML = `
    <div class="capacity-block">
      <div class="capacity-labels">
        <span>Ocupación de la Hacienda</span>
        <strong>${activos} / ${DB.capacidadMax} animales — Disponibles: ${DB.capacidadMax - activos}</strong>
      </div>
      <div class="bar-track">
        <div class="bar-fill ${fillClass}" style="width:${pct}%"></div>
      </div>
    </div>`;
}

/* Rellena un <select> con los propietarios */
function fillSelectPropietarios(selectId, placeholder = "Seleccionar propietario") {
  const sel = document.getElementById(selectId);
  if (!sel) return;
  sel.innerHTML = `<option value="">— ${placeholder} —</option>` +
    DB.propietarios.map(p => `<option value="${p.id}">${p.nombre}</option>`).join("");
}

/* Rellena un <select> con los lotes activos */
function fillSelectLotes(selectId) {
  const sel = document.getElementById(selectId);
  if (!sel) return;
  const activos = DB.lotes.filter(l => l.estado === "activo");
  sel.innerHTML = activos.length
    ? `<option value="">— Seleccionar lote —</option>` + activos.map(l =>
        `<option value="${l.id}">Lote #${l.id} – ${l.propietarioNombre} (${l.cantidad} animales)</option>`
      ).join("")
    : `<option value="">Sin lotes activos</option>`;
}

/* ──────────────────────────────────────────────
   8. INICIALIZACIÓN AL CARGAR LA PÁGINA
────────────────────────────────────────────── */
document.addEventListener("DOMContentLoaded", () => {
  cargarDB(); // Carga datos persistidos si existen

  // Configura el botón de cerrar sesión
  const btnLogout = document.getElementById("btn-logout");
  if (btnLogout) btnLogout.addEventListener("click", cerrarSesion);

  // Configura navegación del sidebar
  document.querySelectorAll(".nav-item[data-nav]").forEach(item => {
    item.addEventListener("click", () => mostrarSeccion(item.dataset.nav));
  });

  // Menú hamburguesa en móvil
  const menuToggle = document.getElementById("menu-toggle");
  const sidebar = document.querySelector(".sidebar");
  if (menuToggle && sidebar) {
    menuToggle.addEventListener("click", () => sidebar.classList.toggle("open"));
  }
});
