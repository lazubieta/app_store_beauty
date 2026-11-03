# 🌸 Aura Beauty — Aplicación Móvil Nativa Android

![Android Native](https://img.shields.io/badge/Platform-Android%20Native-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Language-Kotlin%201.9-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Target SDK](https://img.shields.io/badge/Target%20SDK-34%20(Android%2014)-blue?style=for-the-badge)
![Architecture](https://img.shields.io/badge/Architecture-MVVM%20Clean-FF4081?style=for-the-badge)
![Compensar](https://img.shields.io/badge/Institución-UCompensar-F47196?style=for-the-badge)

> **"Belleza que te acompaña"**  
> Proyecto de desarrollo de frontend nativo para la tienda virtual de cosmética y cuidado dermatológico **Aura Beauty**, diseñado y modelado bajo la especificación formal de la Actividad 2 e implementado en Android Studio con Kotlin y XML.

---

## 📋 Información Institucional
- **Estudiante / Desarrollador:** Lisandro Andrés Zubieta Calderón
- **Programa Académico:** Desarrollo de Aplicaciones Móviles Nativas
- **Institución:** Fundación Universitaria Compensar
- **Docente:** Flavio Alexander Navarro Carmona
- **Lugar y Fecha:** Bogotá D.C., Colombia — Noviembre de 2026

---

## 🌟 Características Principales por Rol

### 👤 1. Flujo del Comprador
- **Pantalla de Inicio / Catálogo (`HomeCatalogFragment`):** Saludo personalizado, buscador en tiempo real, chips de filtrado por categoría (👄 Labios, 👁️ Ojos, ✨ Piel) y banner promocional.
- **Detalle de Producto (`ProductDetailActivity`):** Galería con paginación, selector dinámico circular de tonos, calificación con estrellas, precio en COP y badge de stock.
- **Carrito de Compras (`CartFragment`):** Ajuste reactivo de cantidades `[-]` `[+]`, eliminación de artículos y desglose financiero transparente (Subtotal, Envío, Total).
- **Pago Seguro (`SecurePaymentActivity`):** Validación de dirección de envío, método de pago enmascarado y cierre transaccional con candado de seguridad (Cifrado TLS 1.3).
- **Seguimiento del Pedido (`OrderTrackingActivity`):** Línea de tiempo vertical en tiempo real (Confirmado, En preparación, En camino, Entregado), ruta simulada en mapa y garantía legal del derecho de retracto (Ley 1480 de 2011).
- **Perfil de Usuario (`BuyerProfileFragment`):** Gestión de cuenta, direcciones y radicación formal de retracto.

### 🛍️ 2. Flujo del Vendedor
- **Panel del Vendedor (`SellerDashboardFragment`):** Tarjetas KPI (Ventas de hoy, Pedidos, Stock bajo) y botones de acción rápida ("Nuevo producto", "Ver pedidos").
- **Catálogo de Mis Productos (`SellerProductsFragment`):** CRUD completo, buscador, filtros por estado (Activos / Agotados) y menú de activación/desactivación.
- **Nuevo Producto (`AddProductActivity`):** Formulario de alta con zona de fotografía mediante cámara (`CameraX`), marca, categoría, tono, precio y stock.
- **Editar Producto (`EditProductActivity`):** Actualización de datos, cambio de foto y opción para desactivar cosméticos.
- **Pedidos Recibidos (`SellerOrdersFragment`):** Listado cronológico de órdenes con badge de estado clasificado por color (Pendiente, En preparación, Enviado).
- **Perfil de Tienda (`SellerProfileFragment`):** Métricas de cumplimiento, calificación de vendedor y gestión de sesión.

### 👑 3. Flujo del Administrador
- **Panel de Administración (`AdminDashboardFragment`):** Cuadrícula 2x2 de métricas consolidadas (Usuarios 1,284, Productos 356, Ventas $12,450, Alertas 7) y gráfica de barras semanal de ventas de los últimos 7 días.
- **Gestión de Usuarios (`AdminUsersFragment`):** Búsqueda de cuentas, filtros por rol (Compradores / Vendedores) y badges de estado (Activo / Bloqueado).
- **Detalle y Edición de Usuario (`UserDetailActivity`):** Formulario para actualizar datos, rol en plataforma y botón para **bloqueo preventivo de cuentas** sospechosas.
- **Gestión de Productos (`AdminProductsFragment`):** Supervisión y auditoría del catálogo general.
- **Pagos y Auditoría (`AdminReportsAuditFragment`):** Monitoreo de pasarela activa (Stripe Operativa), conteo de transacciones y registro inmutable de logs del sistema conforme a la Ley 527 de 1999.

### 🔐 4. Capa Transversal (Todos los Roles)
- **Splash Screen (`SplashActivity`):** Pantalla de bienvenida con desvanecimiento animado, logotipo oficial vectorial (`ic_giraffe_mascot.xml`) y slogan.
- **Autenticación Multirrol (`LoginActivity`):** Selector de roles instantáneo y credenciales demostrativas precargadas.
- **Biometría Nativa (`BiometricAuthHelper`):** Integración con `androidx.biometric.BiometricPrompt` respaldada en el Hardware Keystore local del teléfono.
- **Menú de Navegación (`BottomNavigationView`):** Menús personalizados diferenciados por rol con iconos vectoriales y selección activa tinted en color primario.
- **Recuperación de Contraseña (`RecoverPasswordActivity`):** Flujo seguro de restablecimiento de contraseña.

---

## 🎨 Sistema de Diseño y Calidad Técnica XML

El proyecto cumple al 100% con los criterios de evaluación de **cero hardcoding** y uso de colores no predeterminados:

| Recurso | Archivo | Descripción |
|---|---|---|
| **Colores Personalizados** | `res/values/colors.xml` | Paleta cosmética Aura Beauty: `primary` (`#F47196`), `primary_dark` (`#D84C74`), `secondary_mint` (`#4DB6AC`), `app_background` (`#FFF8F9`), tonos de maquillaje y estados. |
| **Cadenas Centralizadas** | `res/values/strings.xml` | Más de 100 cadenas semánticas categorizadas por rol, sin textos literales en layouts. |
| **Dimensiones Estandarizadas** | `res/values/dimens.xml` | Grilla de espaciado Material, áreas táctiles ergonómicas (mínimo 52dp) y escala tipográfica. |
| **Estilos y Temas** | `res/values/themes.xml` | Estilos para botones pill (`Widget.AuraBeauty.Button.Primary`), tarjetas con borde tenaz y campos de texto con outline rosado. |
| **Drawables Vectoriales** | `res/drawable/` | Logotipo oficial (`ic_giraffe_mascot.xml`), badges (`bg_badge_active.xml`), gráfica de barras, gradientes y selectores interactivos. |

---

## 🏛️ Marco Legal Colombiano en el Código

- **Ley 1480 de 2011 (Estatuto del Consumidor):** Precios claros, desglose de envío y botón de **Derecho de Retracto** visible en perfil e historial con plazo legal de 5 días hábiles.
- **Ley 1581 de 2012 (Protección de Datos Personales / Habeas Data):** Checkbox obligatorio en registro para consentimiento informado y biometría protegida en el chip local (TEE).
- **Ley 527 de 1999 (Comercio Electrónico):** Generación de identificador único por orden y registro inmutable de auditoría transaccional.
- **Ley 1273 de 2009 (Delitos Informáticos):** Cero tarjetas o contraseñas en texto plano, tráfico HTTPS / TLS 1.3 forzado.

---

## 🚀 Instrucciones de Instalación y Ejecución

### Requisitos Previos
- **Java Development Kit (JDK):** Versión 17 o superior.
- **Android Studio:** Versión Iguana, Jellyfish o superior.
- **Android SDK:** API Level 34 (Android 14) instalado.

### Clonar y Abrir el Proyecto
```bash
git clone https://github.com/lazubieta/app_store_beauty.git
# o mediante SSH:
# git clone git@github.com:lazubieta/app_store_beauty.git
cd app_store_beauty
```
1. Abrir Android Studio y seleccionar **File -> Open**.
2. Seleccionar el directorio del proyecto y aguardar la sincronización de Gradle.

### Compilar y Generar el APK
Para generar el archivo ejecutable `.apk` para validación:

En Windows (PowerShell / CMD):
```cmd
.\gradlew.bat assembleDebug
```

En Linux / macOS:
```bash
chmod +x gradlew
./gradlew assembleDebug
```

El archivo APK se generará en:
```
app/build/outputs/apk/debug/app-debug.apk
```

---

## 📂 Estructura del Proyecto

```
AuraBeautyApp/
├── app/
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/com/aurabeauty/app/
│           │   ├── AuraBeautyApplication.kt
│           │   ├── data/
│           │   │   ├── model/ (User, Product, Order, CartItem, AuditLog, ToneOption)
│           │   │   └── repository/ (MockDataRepository)
│           │   ├── ui/
│           │   │   ├── auth/ (Splash, Login, Register, Recover, BiometricAuthHelper)
│           │   │   ├── buyer/ (Home, Detail, Cart, Payment, Tracking, Profile, Adapters)
│           │   │   ├── seller/ (Dashboard, Products, Add, Edit, Orders, Profile, Adapters)
│           │   │   └── admin/ (Dashboard, Users, UserDetail, Products, Audit, Adapters)
│           │   └── utils/ (Extensions)
│           └── res/
│               ├── drawable/ (Vectores, badges, gradientes y mascota oficial)
│               ├── layout/ (Layouts completos de los 3 roles y transversal)
│               ├── menu/ (Bottom navigation para Comprador, Vendedor y Admin)
│               └── values/ (colors.xml, strings.xml, dimens.xml, themes.xml)
├── build.gradle.kts
├── settings.gradle.kts
├── gradlew
├── gradlew.bat
├── README.md
├── DOCUMENTACION_TECNICA_ACTIVIDAD_1_ACTUALIZADA.md
└── GUION_VIDEO_EXPLICATIVO.md
```

---

## 📹 Video Explicativo Técnico
El repositorio incluye el documento [`GUION_VIDEO_EXPLICATIVO.md`](./GUION_VIDEO_EXPLICATIVO.md) con el minutero cronometrado y las indicaciones paso a paso para la grabación del video de sustentación de máximo 5 minutos.
