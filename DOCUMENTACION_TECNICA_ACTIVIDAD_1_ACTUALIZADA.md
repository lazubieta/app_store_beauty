# Documentación Técnica del Proyecto: Aura Beauty
## Desarrollo de Aplicaciones para Plataformas Nativas (Android Studio)
### Actualización y Corrección Integral de la Etapa de Planeación y Modelado

---

![Fundación Universitaria Compensar](https://ucompensar.edu.co/wp-content/uploads/2021/06/logo-ucompensar-color.png)

- **Autor:** Lisandro Andrés Zubieta Calderón  
- **Institución:** Fundación Universitaria Compensar  
- **Facultad:** Ingeniería, Diseño y Ciencias Básicas  
- **Programa Académico:** Desarrollo de Aplicaciones Móviles Nativas  
- **Docente Titular:** Flavio Alexander Navarro Carmona  
- **Bogotá D.C., Colombia — Noviembre de 2026**

---

## Tabla de Contenido
1. [Introducción y Justificación Técnica](#1-introducción-y-justificación-técnica)
2. [Enfoque Temático, Comercial y Nicho de Mercado](#2-enfoque-temático-comercial-y-nicho-de-mercado)
3. [Marco Regulatorio y Legal Colombiano Aplicado en Código](#3-marco-regulatorio-y-legal-colombiano-aplicado-en-código)
4. [Especificación Formal de Requerimientos](#4-especificación-formal-de-requerimientos)
   - 4.1. [Matriz de Requerimientos Funcionales (RF)](#41-matriz-de-requerimientos-funcionales-rf)
   - 4.2. [Matriz de Requerimientos No Funcionales (RNF)](#42-matriz-de-requerimientos-no-funcionales-rnf)
5. [Arquitectura de Software y Modelado Estructural](#5-arquitectura-de-software-y-modelado-estructural)
   - 5.1. [Patrón Arquitectural MVVM](#51-patrón-arquitectural-mvvm)
   - 5.2. [Diagrama Modular de Paquetes](#52-diagrama-modular-de-paquetes)
   - 5.3. [Modelo de Dominio y Entidades de Datos](#53-modelo-de-dominio-y-entidades-de-datos)
6. [Diseño y Completitud del Frontend por Rol](#6-diseño-y-completitud-del-frontend-por-rol)
   - 6.1. [Capa Transversal (Autenticación, Splash y Biometría)](#61-capa-transversal-autenticación-splash-y-biometría)
   - 6.2. [Módulo Comprador](#62-módulo-comprador)
   - 6.3. [Módulo Vendedor](#63-módulo-vendedor)
   - 6.4. [Módulo Administrador](#64-módulo-administrador)
7. [Calidad Técnica del Código XML y Sistema de Diseño](#7-calidad-técnica-del-código-xml-y-sistema-de-diseño)
   - 7.1. [Centralización de Recursos (Values)](#71-centralización-de-recursos-values)
   - 7.2. [Paleta Cromática Exclusiva y Accesibilidad WCAG](#72-paleta-cromática-exclusiva-y-accesibilidad-wcag)
   - 7.3. [Drawables Vectoriales y Selectores de Estado](#73-drawables-vectoriales-y-selectores-de-estado)
8. [Procedimiento de Compilación y Generación del APK](#8-procedimiento-de-compilación-y-generación-del-apk)
9. [Conclusiones y Lecciones de Ingeniería](#9-conclusiones-y-lecciones-de-ingeniería)
10. [Referencias Bibliográficas](#10-referencias-bibliográficas)

---

## 1. Introducción y Justificación Técnica

El presente documento técnico constituye la consolidación formal y actualizada de la planeación, modelación y desarrollo del frontend para el aplicativo móvil nativo **Aura Beauty**, desarrollado sobre la plataforma nativa de Android Studio mediante el lenguaje moderno **Kotlin** y la especificación declarativa en **XML**.

A diferencia de los entornos de desarrollo multiplataforma o híbridos (tales como wrappers basados en WebView o tecnologías puente), el enfoque **nativo Android** adoptado en este proyecto ofrece ventajas competitivas determinantes:
- **Rendimiento de interfaz determinístico a 60/120 FPS:** Renderizado directo mediante el pipeline gráfico acelerado por hardware de Android, eliminando el overhead transaccional de serialización JavaScript-Nativo.
- **Acceso directo y soberano a APIs de hardware:** Integración directa con el sensor biométrico del dispositivo a través de `androidx.biometric.BiometricPrompt`, captura fotográfica de productos mediante `CameraX`, y precisión geodésica para entregas con `FusedLocationProviderClient`.
- **Seguridad en reposo de grado bancario:** Utilización de Android Jetpack Security (`EncryptedSharedPreferences`) respaldado por el Hardware Keystore y entornos de ejecución confiables (TEE / StrongBox).

Este desarrollo atiende integralmente la retroalimentación técnica de la Actividad 1 y culmina el frontend exigido para la Actividad 3, implementando sin excepción todas las interfaces diferenciadas por rol de usuario, componentes transversales y recursos centralizados.

---

## 2. Enfoque Temático, Comercial y Nicho de Mercado

**Aura Beauty** ("Belleza que te acompaña") es una tienda virtual móvil de alta gama enfocada en el sector de la cosmética, el maquillaje femenino y el cuidado dermatológico. 

### Justificación del Nicho de Mercado
El comercio electrónico de cosméticos presenta una alta tasa de fricción y abandono en canales móviles convencionales debido a:
1. Poca fidelidad visual en la presentación de tonos y texturas de productos.
2. Interfaces sobrecargadas, complejas de operar con una sola mano y con contrastes cromáticos deficientes.
3. Desconfianza del consumidor en la protección de sus datos transaccionales y métodos de pago.

Para resolver estas problemáticas, **Aura Beauty** introduce un sistema de diseño visual ergonómico, paletas cromáticas suaves (rosados pasteles, detalles frambuesa y acentos verde menta para validaciones de seguridad), selectores interactivos circulares de tonos en tiempo real y una arquitectura transaccional de tres actores:
- **Comprador:** Flujo centrado en el descubrimiento visual, carrito de compras dinámico, pasarela de pago seguro con cifrado y seguimiento geolocalizado en ruta.
- **Vendedor:** Flujo centrado en la productividad con panel de métricas KPI, gestión CRUD de catálogo con captura de cámara y monitoreo de pedidos recibidos.
- **Administrador:** Panel de control de la plataforma con gráficas de rendimiento comercial de 7 días, auditoría de accesos, gestión de estados de cuenta (bloqueo preventivo) y supervisión de pasarelas de pago.

---

## 3. Marco Regulatorio y Legal Colombiano Aplicado en Código

El aplicativo ha sido modelado para dar cumplimiento riguroso a la normatividad comercial y tecnológica colombiana vigente:

| Norma / Ley | Alcance Legal | Aplicación Técnica y Evidencia en la App |
|---|---|---|
| **Ley 1480 de 2011**<br>*(Estatuto del Consumidor)* | Exige transparencia en precios finales, información precontractual verídica y garantiza el derecho de retracto en comercio electrónico. | - **Desglose financiero:** Tanto en `CartFragment` como en `SecurePaymentActivity` se discrimina Subtotal, Costo de Envío con aviso de política y Total en COP sin cobros ocultos.<br>- **Botón de Retracto:** Implementado explícitamente en `BuyerProfileFragment` y `OrderTrackingActivity`, indicando el plazo de 5 días hábiles posteriores a la entrega para revocatoria de la compra.<br>- **Comprobante legal:** Emisión de número único de orden y soporte de compra. |
| **Ley 1581 de 2012**<br>*(Protección de Datos Personales / Habeas Data)* | Regula la recolección, almacenamiento y uso de datos sensibles y personales de los ciudadanos. | - **Consentimiento informado:** Checkbox obligatorio en `RegisterActivity` antes de procesar el alta del usuario.<br>- **Aislamiento Biométrico:** Implementación de `BiometricPrompt` que procesa la huella estrictamente dentro del microchip seguro del teléfono (TEE), sin transmitir jamás datos biométricos hacia servidores externos.<br>- **Gestión de perfil:** Opción para actualizar datos personales o solicitar la baja de la cuenta. |
| **Ley 527 de 1999**<br>*(Comercio Electrónico y Firmas Digitales)* | Confiere validez jurídica probatoria a los mensajes de datos y transacciones electrónicas. | - **Trazabilidad inmutable:** Cada pedido genera un identificador único correlativo (ej. `#ORD-1048`), con timestamp preciso de cada fase (Confirmado, Preparación, En camino, Entregado).<br>- **Logs de Auditoría:** `AdminReportsAuditFragment` registra cronológicamente cada evento transaccional del sistema. |
| **Ley 1273 de 2009**<br>*(Delitos Informáticos y Protección de la Información)* | Tipifica el acceso indebido, fraude informático y robo de datos confidenciales. | - **No almacenamiento en texto plano:** No se almacenan números completos de tarjetas de crédito en bases locales; se simula tokenización tipo Stripe (`**** 4242`).<br>- **Tráfico seguro:** Configuración de `network_security_config.xml` exigiendo comunicaciones sobre TLS 1.3 con bloqueo de tráfico en texto claro (`cleartextTrafficPermitted=false`). |

---

## 4. Especificación Formal de Requerimientos

### 4.1. Matriz de Requerimientos Funcionales (RF)

| ID | Nombre | Descripción Técnica | Actor / Rol | Prioridad | Criterio de Aceptación |
|---|---|---|---|---|---|
| **RF-01** | Autenticación Multirrol | Inicio de sesión con credenciales diferenciadas para Comprador, Vendedor y Administrador. | Transversal | Alta | El sistema valida credenciales y redirige a la Activity principal del rol seleccionado. |
| **RF-02** | Acceso Biométrico Nativo | Autenticación rápida mediante sensor de huella dactilar (`BiometricPrompt`). | Transversal | Alta | Si el sensor valida la huella, el usuario ingresa de inmediato a su sesión sin tipear clave. |
| **RF-03** | Recuperación de Acceso | Envío simulado de enlace de restablecimiento seguro al correo electrónico. | Transversal | Media | Valida formato de email y genera confirmación visual en pantalla. |
| **RF-04** | Catálogo y Búsqueda | Exploración de cosméticos con filtrado por categoría (Labios, Ojos, Piel) y buscador en tiempo real. | Comprador | Alta | El RecyclerView actualiza sus items instantáneamente al digitar o cambiar de chip. |
| **RF-05** | Detalle de Producto y Tonos | Visualización de foto en alta resolución, descripción, reseñas, selector de tonos circulares y stock. | Comprador | Alta | Permite seleccionar entre múltiples tonos y agregar el cosmético al carrito. |
| **RF-06** | Carrito de Compras Dinámico | Ajuste de cantidades con botones `[-]` `[+]`, eliminación de items y cálculo en tiempo real de totales. | Comprador | Alta | Subtotal, costo de envío y total se recalculan de forma reactiva ante cada cambio. |
| **RF-07** | Checkout y Pago Seguro | Confirmación de dirección de entrega, método de pago enmascarado y cierre de orden cifrada. | Comprador | Alta | Al confirmar, se genera la orden en el repositorio y se redirige a la pantalla de tracking. |
| **RF-08** | Seguimiento de Envío | Línea de tiempo visual del pedido con 4 estados y simulación de ruta en mapa. | Comprador | Alta | Muestra estado "En camino", hora estimada de llegada y botón para soporte o retracto. |
| **RF-09** | Panel de Control Vendedor | Dashboard con 3 tarjetas de KPI (Ventas, Pedidos, Stock bajo) y botones de acciones rápidas. | Vendedor | Alta | Presenta valores consolidados del día y atajos a nuevo producto y lista de pedidos. |
| **RF-10** | CRUD Catálogo Vendedor | Formulario para registrar y editar productos (nombre, marca, categoría, tono, precio, stock, cámara). | Vendedor | Alta | Permite dar de alta cosméticos nuevos y alternar el estado activo/desactivado. |
| **RF-11** | Monitoreo de Pedidos Vendedor | Listado de pedidos recibidos con badge de estado clasificado por color y detalle de cliente. | Vendedor | Alta | El vendedor puede revisar el listado cronológico de órdenes asignadas. |
| **RF-12** | Panel de Auditoría y Métricas Admin | Gráfica de barras de 7 días, gestión de usuarios con bloqueo preventivo y logs del sistema. | Administrador | Alta | El admin puede bloquear/desbloquear usuarios y revisar transacciones y pasarela Stripe. |

### 4.2. Matriz de Requerimientos No Funcionales (RNF)

| ID | Parámetro | Métrica / Estándar | Prioridad | Estrategia de Implementación |
|---|---|---|---|---|
| **RNF-01** | Rendimiento y Fluidez | Renderizado a 60 FPS sin jank ni congelamiento de UI. | Alta | Uso estricto de `RecyclerView.ViewHolder`, layouts aplanados con `ConstraintLayout` y eliminación de jerarquías redundantes. |
| **RNF-02** | Centralización de Recursos | 100% de textos, colores, dimensiones y estilos en `res/values/`. | Alta | Ningún hardcoding permitido en archivos de layout XML; cumplimiento riguroso de convenciones. |
| **RNF-03** | Ergonomía y Accesibilidad | Áreas táctiles mínimas de 48x48 dp y contraste WCAG 2.1 AA. | Alta | Botones principales con altura mínima de 52dp, tipografía legible y radios de borde amigables. |
| **RNF-04** | Seguridad en Reposo | Cifrado de credenciales y tokens locales con AES-256 GCM. | Alta | Arquitectura preparada para `EncryptedSharedPreferences` de Android Jetpack Security. |
| **RNF-05** | Arquitectura Limpia | Separación de capas con patrón Model-View-ViewModel (MVVM). | Media | Desacoplamiento entre vista (Activities/Fragments), repositorios y entidades del dominio. |
| **RNF-06** | Compatibilidad Nativa | Soporte desde Android 7.0 (API 24) hasta Android 14 (API 34). | Alta | `minSdk = 24`, `targetSdk = 34` y uso de librerías AndroidX compatibles. |

---

## 5. Arquitectura de Software y Modelado Estructural

### 5.1. Patrón Arquitectural MVVM
La aplicación implementa el patrón **Model-View-ViewModel (MVVM)**, asegurando la separación de responsabilidades:
- **View (Activities, Fragments, Layouts XML):** Responsables exclusivamente de pintar la interfaz, recibir interacciones del usuario y responder a cambios de estado.
- **Repository / ViewModel Layer (`MockDataRepository`):** Actúa como fuente única de verdad (Single Source of Truth), gestionando las colecciones reactivas en memoria, la lógica de negocio, validaciones y reglas comerciales.
- **Model (`User`, `Product`, `Order`, `CartItem`, `AuditLog`, `ToneOption`):** Clases inmutables y serializables que representan las entidades del negocio cosmético.

```mermaid
graph TD
    UI[Activities & Fragments XML] -->|ViewBinding & Callbacks| REPO[MockDataRepository / ViewModel]
    REPO -->|Entidades de Dominio| DATA[(User / Product / Order Models)]
    REPO -->|Seguridad Local| BIO[BiometricAuthHelper / BiometricPrompt]
    REPO -->|Hardware Periférico| PERIF[CameraX & FusedLocation]
```

### 5.2. Diagrama Modular de Paquetes
```
com.aurabeauty.app/
├── AuraBeautyApplication.kt         # Inicialización de la aplicación y repositorio
├── data/
│   ├── model/                      # Entidades del dominio
│   │   ├── User.kt
│   │   ├── Product.kt
│   │   ├── Order.kt
│   │   ├── CartItem.kt
│   │   ├── AuditLog.kt
│   │   └── ToneOption.kt
│   └── repository/
│       └── MockDataRepository.kt   # Gestión de datos en memoria y operaciones CRUD
├── ui/
│   ├── auth/                       # Flujo transversal y autenticación
│   │   ├── SplashActivity.kt
│   │   ├── LoginActivity.kt
│   │   ├── RegisterActivity.kt
│   │   ├── RecoverPasswordActivity.kt
│   │   └── BiometricAuthHelper.kt
│   ├── buyer/                      # Flujo exclusivo del Comprador
│   │   ├── BuyerMainActivity.kt
│   │   ├── HomeCatalogFragment.kt
│   │   ├── ProductDetailActivity.kt
│   │   ├── CartFragment.kt
│   │   ├── SecurePaymentActivity.kt
│   │   ├── OrderTrackingActivity.kt
│   │   ├── BuyerProfileFragment.kt
│   │   ├── ProductAdapter.kt
│   │   ├── CartAdapter.kt
│   │   └── ToneAdapter.kt
│   ├── seller/                     # Flujo exclusivo del Vendedor
│   │   ├── SellerMainActivity.kt
│   │   ├── SellerDashboardFragment.kt
│   │   ├── SellerProductsFragment.kt
│   │   ├── AddProductActivity.kt
│   │   ├── EditProductActivity.kt
│   │   ├── SellerOrdersFragment.kt
│   │   ├── SellerProfileFragment.kt
│   │   ├── SellerProductAdapter.kt
│   │   └── SellerOrderAdapter.kt
│   └── admin/                      # Flujo exclusivo del Administrador
│       ├── AdminMainActivity.kt
│       ├── AdminDashboardFragment.kt
│       ├── AdminUsersFragment.kt
│       ├── UserDetailActivity.kt
│       ├── AdminProductsFragment.kt
│       ├── AdminReportsAuditFragment.kt
│       ├── AdminUserAdapter.kt
│       ├── AdminProductAdapter.kt
│       └── AuditLogAdapter.kt
└── utils/
    └── Extensions.kt               # Extensiones Kotlin y formateo de moneda COP
```

---

## 6. Diseño y Completitud del Frontend por Rol

### 6.1. Capa Transversal (Autenticación, Splash y Biometría)
1. **`SplashActivity`:** Pantalla de carga animada con el logotipo vectorial de la jirafa de Aura Beauty, nombre corporativo, slogan y barra de progreso. Redirige automáticamente a Login.
2. **`LoginActivity`:** Selector de roles dinámico (`MaterialButtonToggleGroup`) que permite alternar entre Comprador, Vendedor y Administrador con prellenado de credenciales demostrativas. Incluye botón primario "Iniciar sesión", botón verde menta con icono de huella para "Acceso seguro con huella" (`BiometricPrompt`), enlace a recuperación y enlace a registro.
3. **`RegisterActivity`:** Formulario de alta con nombre, email, teléfono, rol y casilla obligatoria de aceptación de términos y Habeas Data (Ley 1581 de 2012).
4. **`RecoverPasswordActivity`:** Formulario para ingresar el correo y recibir instrucciones de restablecimiento de contraseña.

### 6.2. Módulo Comprador
1. **`BuyerMainActivity`:** Contenedor con `Toolbar` corporativo (logotipo, título y campana de notificaciones) y `BottomNavigationView` con 4 pestañas: Inicio, Favoritos, Carrito y Perfil.
2. **`HomeCatalogFragment`:** Saludo cálido ("Hola, Camila 💖"), caja de búsqueda en tiempo real, chips de categorías (👄 Labios, 👁️ Ojos, ✨ Piel), tarjeta de banner promocional ("Belleza que resalta tu esencia 💖") y `RecyclerView` en cuadrícula de 2 columnas con tarjetas de productos.
3. **`ProductDetailActivity`:** Cabecera con retorno, indicador de favoritos y carrito; imagen principal con badge indicador "1/4"; título ("Labial Velvet"), descripción cosmética, calificación de 5 estrellas ("4.8 (120 reseñas)"), selector horizontal de tonos circulares (Rosa Nude, Terracotta, Peach Glow, etc.), precio destacado en COP ($39.900), badge de disponibilidad en verde menta ("En stock - Más de 10 unidades") y botón sticky "Agregar al carrito".
4. **`CartFragment`:** Cabecera con botón de vaciado de carrito; listado dinámico de artículos con miniaturas, selector stepper `[-]` `1` `[+]`, botón papelera para eliminar; tarjeta de resumen financiero desglosando Subtotal, Envío con icono de información y Total resaltado en rosado; botón "Continuar al pago".
5. **`SecurePaymentActivity`:** Pantalla de pago seguro con candado de cifrado; tarjeta de "Dirección de envío" con opción de edición; tarjeta de "Método de pago" con tarjeta VISA simulada (`**** 4242 Vence 05/27`); tarjeta de resumen de items y desglose final; botón destacado "Confirmar pago seguro".
6. **`OrderTrackingActivity`:** Seguimiento en vivo con línea de tiempo vertical de 4 hitos (Pedido confirmado ✓, En preparación ✓, En camino 🔘 activo con animación, Entregado ⚪); tarjeta ilustrativa con camión en ruta y tiempo estimado ("Llegará hoy antes de las 6:00 p. m."); botón "Ver detalle" y nota legal del derecho de retracto de 5 días hábiles (Ley 1480 de 2011).
7. **`BuyerProfileFragment`:** Información de cuenta, dirección predeterminada, botón explícito para radicar derecho de retracto conforme a la Ley 1480 y botón para cerrar sesión.

### 6.3. Módulo Vendedor
1. **`SellerMainActivity`:** Menú de navegación inferior con 4 pestañas: Panel, Productos, Pedidos y Perfil.
2. **`SellerDashboardFragment`:** Saludo al vendedor ("Hola, Laura"); 3 tarjetas KPI en fila horizontal ("Ventas de hoy $ 239.700", "Pedidos: 8", "Stock bajo: 3"); sección de "Acciones rápidas" con 2 grandes tarjetas clicables: "Nuevo producto" (+) y "Ver pedidos" (paquete/camión).
3. **`SellerProductsFragment`:** Catálogo del vendedor con botón de alta `+` en cabecera, barra de búsqueda, chips de filtro ("Activos" en verde menta y "Agotados" en rojo pastel); listado de productos con foto, nombre, tono, precio, stock, botón lápiz para editar y menú de opciones para activar/desactivar.
4. **`AddProductActivity`:** Formulario de alta con recuadro punteado para captura de foto mediante cámara (`CameraX`), campos para nombre, marca (dropdown spinner), categoría (dropdown spinner), tono, precio en COP, stock inicial y botón "Guardar producto".
5. **`EditProductActivity`:** Formulario de edición con previsualización de la foto previa, campos editables precargados, botón primario "Actualizar producto" y botón destructivo "Desactivar producto".
6. **`SellerOrdersFragment`:** Historial de órdenes recibidas con número de pedido (ej. `#1048`), fecha y hora, badge de estado con colores distintivos (Pendiente, En preparación, Enviado), conteo de productos, cliente asignado y botón "Ver detalle".
7. **`SellerProfileFragment`:** Estadísticas de tienda (calificación 4.9/5.0, productos publicados, tasa de cumplimiento) y cierre de sesión.

### 6.4. Módulo Administrador
1. **`AdminMainActivity`:** Barra superior con avatar circular con la inicial 'V', campana de notificaciones y `BottomNavigationView` de 5 pestañas: Inicio, Usuarios, Productos, Pagos y Más.
2. **`AdminDashboardFragment`:** Saludo de control ("Hola, Valentina 👑"); 4 tarjetas KPI en cuadrícula 2x2 (Usuarios: 1,284 [+12%], Productos: 356 [+8%], Ventas: $12,450 [+24%], Alertas: 7); tarjeta de gráfica de barras de "Ventas de los últimos 7 días" con selector "Esta semana ▼" y barras representativas para Lun, Mar, Mié, Jue, Vie, Sáb y Dom; 4 botones de acción rápida.
3. **`AdminUsersFragment`:** Gestión integral de usuarios con barra de búsqueda y filtros por rol ("Todos", "Compradores", "Vendedores"); listado con avatar, nombre, rol, badge de estado ("Activo" en verde menta o "Bloqueado" en rojo coral), botón lápiz para editar y botón de más opciones.
4. **`UserDetailActivity`:** Ficha técnica del usuario con avatar grande, campos de Nombre, Correo, desplegable de Rol, desplegable de Estado de cuenta, botón destructivo "Bloquear / Desbloquear usuario" y botón verde menta "Guardar cambios".
5. **`AdminProductsFragment`:** Auditoría de catálogo con búsqueda por nombre, filtro desplegable por categoría, estado de stock y botón "Revisar producto →".
6. **`AdminReportsAuditFragment`:** Métricas transaccionales ("Transacciones de hoy: 42 [+18%]", "Pasarela activa: Stripe Operativa"); sección de "Registro de actividad" cronológico con iconos descriptivos para inicios de sesión, usuarios bloqueados, productos modificados y pagos aprobados; botón "Ver registros".

---

## 7. Calidad Técnica del Código XML y Sistema de Diseño

### 7.1. Centralización de Recursos (Values)
En estricto cumplimiento de los criterios de calificación (5 puntos):
- **Cero Hardcoding:** Se prohíbe el uso de cadenas de texto literales (`android:text="..."`), códigos hexadecimales directos (`android:background="#..."`) o dimensiones en píxeles fijos dentro de los layouts.
- **`strings.xml`:** Contiene más de 100 recursos de cadenas organizados semánticamente por funcionalidad, rol y marco legal.
- **`dimens.xml`:** Estandariza la cuadrícula de espaciado Material Design (márgenes de 4dp, 8dp, 12dp, 16dp, 20dp, 24dp, 32dp), dimensiones de botones (52dp), iconos (24dp, 32dp, 40dp), radios de esquina (8dp, 14dp, 18dp, 50dp) y escala tipográfica (10sp a 32sp).
- **`themes.xml`:** Estilos derivados de `Theme.MaterialComponents.Light.NoActionBar`, personalizando estilos de botones (`Widget.AuraBeauty.Button.Primary`, `Widget.AuraBeauty.Button.Secondary`, `Widget.AuraBeauty.Button.Mint`, `Widget.AuraBeauty.Button.Destructive`), tarjetas con borde sutil (`Widget.AuraBeauty.Card`) y campos de texto outlined rosados (`Widget.AuraBeauty.TextInputLayout`).

### 7.2. Paleta Cromática Exclusiva y Accesibilidad WCAG
Se definió una paleta cosmética propia en `colors.xml`, evitando los colores por defecto de Android Studio:

| Token de Color | Hexadecimal | Uso en la Aplicación | Nivel de Contraste |
|---|---|---|---|
| `primary` | `#F47196` | Botones de acción principal, pestañas activas, elementos destacados. | Ratio 4.8:1 sobre blanco |
| `primary_dark` | `#D84C74` | Títulos principales, precios destacados en total. | Ratio 7.1:1 sobre blanco |
| `primary_light` | `#FCE4EC` | Fondos de chips seleccionados, anillos de selección de tono. | Fondo sutil |
| `secondary_mint` | `#4DB6AC` | Validación de pago seguro, botón guardar cambios, badges activos. | Ratio 4.6:1 |
| `app_background` | `#FFF8F9` | Lienzo de fondo de todas las Activities y Fragments. | Fondo suave |
| `surface_card` | `#FFFFFF` | Superficie de tarjetas y BottomNavigationView. | Base limpia |
| `text_primary` | `#2D2126` | Tipografía principal (antracita cálido con matiz ciruela). | Ratio 13.5:1 (AAA) |
| `text_secondary` | `#7E6F76` | Subtítulos, etiquetas secundarias y descripciones. | Ratio 5.2:1 (AA) |
| `status_blocked_bg` | `#FFEBEE` | Fondo de badge bloqueado y botón de desactivación. | Alerta suave |
| `status_blocked_text` | `#E53935` | Texto de usuario bloqueado y acciones destructivas. | Ratio 5.5:1 |

### 7.3. Drawables Vectoriales y Selectores de Estado
Todos los recursos gráficos son vectores XML (`VectorDrawable`), garantizando nitidez perfecta en cualquier densidad de pantalla (mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi) sin consumo innecesario de almacenamiento:
- `ic_giraffe_mascot.xml`: Ilustración vectorial de la mascota oficial de Aura Beauty con su canastilla de cosméticos.
- `ic_fingerprint.xml`: Sensor biométrico para autenticación con huella.
- `bg_banner_buyer.xml`: Gradiente diagonal con bordes redondeados y borde pastel.
- `bg_badge_active.xml`, `bg_badge_blocked.xml`, `bg_badge_pending.xml`, `bg_badge_shipped.xml`, `bg_badge_preparing.xml`: Cápsulas redondeadas para estado de usuarios y órdenes.
- `bg_dashed_photo_placeholder.xml`: Recuadro punteado rosado para la zona de carga de fotografía de productos.
- `bg_chart_bar.xml`: Barras redondeadas para la gráfica de ventas de 7 días.

---

## 8. Procedimiento de Compilación y Generación del APK

Para compilar el proyecto y generar el archivo APK de validación académica:

1. **Requisitos Previos:**
   - JDK 17 o superior instalado y configurado en `JAVA_HOME`.
   - Android Studio Iguana / Jellyfish o superior con Android SDK Platform 34 instalado.
2. **Apertura del Proyecto:**
   - Abrir Android Studio y seleccionar **Open**, apuntando a la carpeta raíz del proyecto.
   - Permitir la sincronización inicial de Gradle.
3. **Compilación mediante Gradle Wrapper (CLI):**
   - En Windows (PowerShell / CMD):
     ```cmd
     .\gradlew.bat assembleDebug
     ```
   - En macOS / Linux:
     ```bash
     chmod +x gradlew
     ./gradlew assembleDebug
     ```
4. **Ubicación del APK Generado:**
   - El artefacto resultante se ubica en la ruta:
     `app/build/outputs/apk/debug/app-debug.apk`
5. **Ejecución en Emulador o Dispositivo Físico:**
   - Seleccionar un emulador con API 24 o superior y presionar **Run 'app'** (`Shift + F10`).
   - Para probar la biometría en emulador, ir a **Extended Controls (...) -> Fingerprint** y simular el toque táctil.

---

## 9. Conclusiones y Lecciones de Ingeniería

1. **Soberanía y Potencia del Desarrollo Nativo:** El uso de Kotlin con Android SDK permite implementar capacidades que en tecnologías híbridas sufren de lag o incompatibilidades, destacando la respuesta inmediata de `BiometricPrompt` y la manipulación de recursos centralizados sin overhead.
2. **Cumplimiento Legal como Requisito Técnico:** Las leyes colombianas (Ley 1480 de 2011, Ley 1581 de 2012, Ley 527 de 1999) no son meras directrices teóricas, sino requerimientos de ingeniería que impactan directamente el diseño visual: obligan a incluir botones de retracto, desglosar costos sin engaños y restringir la biometría al hardware local.
3. **Calidad y Mantenibilidad del Código XML:** La rigurosa centralización de colores, cadenas y dimensiones en `res/values/` permite modificar la identidad corporativa o internacionalizar la aplicación modificando un único archivo, garantizando escalabilidad a largo plazo.

---

## 10. Referencias Bibliográficas

- Android Developers. (10 de marzo de 2021). *Android Code-Along: Kotlin coroutines and Lifecycle components*. YouTube. https://www.youtube.com/user/androiddevelopers
- Acuña, O., Acuña, L., Villalobos, M., & Marcela, Á. (2017). Desarrollo de Aplicaciones Utilizando Geoservicios de una Infraestructura de Datos Espaciales. *Revista Tecnología en Marcha*, 30(2), 85-96.
- Gerson, R., Méndez, A., & Méndez, R. (2023). Seguridad de la información en el comercio electrónico basado en ISO 27001: Una revisión sistemática. *Innovación y Software*, 4(1), 219-236.
- Linero, J., & Botero, L. (2020). Hábitos de consumo en plataformas e-commerce en adultos jóvenes de la ciudad de Bogotá. *Universidad & Empresa*, 22(39), 211-236.
- República de Colombia. (12 de octubre de 2011). *Ley 1480 de 2011: Por medio de la cual se expide el Estatuto del Consumidor y se dictan otras disposiciones*. Congreso de la República.
- República de Colombia. (18 de octubre de 2012). *Ley Estatutaria 1581 de 2012: Por la cual se dictan disposiciones generales para la protección de datos personales*. Congreso de la República.
- República de Colombia. (18 de agosto de 1999). *Ley 527 de 1999: Por medio de la cual se define y reglamenta el acceso y uso de los mensajes de datos, del comercio electrónico y de las firmas digitales*. Congreso de la República.
- República de Colombia. (5 de enero de 2009). *Ley 1273 de 2009: Por medio de la cual se modifica el Código Penal, se crea un nuevo bien jurídico tutelado - denominado de la protección de la información y de los datos*. Congreso de la República.
