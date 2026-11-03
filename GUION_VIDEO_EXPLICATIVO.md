# Guion Técnico para la Grabación del Video Explicativo (Máximo 5 Minutos)
## Proyecto: Aura Beauty — Aplicación Móvil Nativa Android Studio
### Estudiante: Lisandro Andrés Zubieta Calderón | Fundación Universitaria Compensar

---

## 🎯 Instrucciones Previas para la Grabación
- **Tiempo límite:** 5 minutos (300 segundos). El siguiente minutero está cronometrado para durar exactamente entre 4:30 y 4:50 minutos a ritmo sereno y profesional.
- **Herramienta recomendada:** OBS Studio, Camtasia, Loom o Xbox Game Bar (`Windows + G`).
- **Resolución:** 1080p (Full HD), audio nítido sin ruido de fondo.
- **Disposición de pantalla:** Android Studio abierto con el emulador en un lateral y las pestañas de XML y Kotlin preparadas en el editor.

---

## ⏱️ Minutero Detallado y Guion de Locución Técnica

### 🕒 [0:00 - 0:45] Bloque 1: Introducción y Arquitectura del Proyecto Android Studio
- **Qué mostrar en pantalla:** Ventana principal de Android Studio con el árbol de proyecto en modo "Android", mostrando la estructura limpia de paquetes (`ui`, `data`, `utils`, `res`).
- **Voz del expositor:**
  > *"Cordial saludo profesor Flavio y compañeros. Mi nombre es Lisandro Andrés Zubieta Calderón, estudiante de la Fundación Universitaria Compensar. A continuación presento la sustentación técnica del frontend nativo para la tienda virtual **Aura Beauty**, especializada en cosmética femenina.  
  > La aplicación fue construida de forma nativa en **Android Studio** utilizando **Kotlin** y **XML**. Hemos implementado una arquitectura modular desacoplada inspirada en MVVM, separando la capa de datos en el paquete `data` con repositorios reactivos y modelos inmutables, y la capa de presentación en el paquete `ui`, organizada de forma rigurosa en módulos: `auth` para componentes transversales, `buyer` para el comprador, `seller` para el vendedor y `admin` para el administrador."*

---

### 🕒 [0:45 - 2:00] Bloque 2: Calidad Técnica del Código XML, Drawables, Estilos y Values
- **Qué mostrar en pantalla:** Abrir sucesivamente en el editor de código:
  1. `res/values/colors.xml`
  2. `res/values/strings.xml`
  3. `res/values/dimens.xml`
  4. `res/values/themes.xml`
  5. Carpeta `res/drawable/` mostrando `ic_giraffe_mascot.xml` y los shapes.
- **Voz del expositor:**
  > *"Uno de los pilares de este desarrollo es el cumplimiento estricto de las directrices de calidad técnica XML, logrando **cero hardcoding** en los layouts.  
  > En `colors.xml` diseñamos una paleta cromática exclusiva para la marca Aura Beauty, alejándonos por completo de los colores por defecto de Android Studio. Empleamos tonos primarios rosados suaves `#F47196` y frambuesa `#D84C74`, combinados con verde menta `#4DB6AC` para indicar estados seguros y confirmaciones, asegurando contrastes superiores al estándar WCAG AA.  
  > En `strings.xml` centralizamos más de 100 recursos de texto organizados semánticamente, incluyendo las advertencias legales de la Ley 1480 y Ley 1581.  
  > En `dimens.xml` estandarizamos la cuadrícula de espaciado, alturas táctiles mínimas de 52dp para ergonomía con una sola mano y radios de curvatura.  
  > En `themes.xml` definimos componentes Material personalizados para botones primarios con esquinas redondeadas tipo pill, campos de texto con outline rosado y tarjetas con bordes tenues.  
  > Adicionalmente, en `res/drawable/` construimos todos los elementos en formato vectorial XML: desde la mascota corporativa `ic_giraffe_mascot.xml` hasta badges de estado dinámicos como `bg_badge_active.xml` y `bg_badge_blocked.xml`."*

---

### 🕒 [2:00 - 3:30] Bloque 3: Demostración Técnica de Pantallas por Rol en el Emulador
- **Qué mostrar en pantalla:** Emulador de Android ejecutando la aplicación e interactuando en vivo:

#### 1. Rol Comprador (0:30 seg):
- **Acción:** En Login, pulsar "Comprador" e ingresar.
- **Voz:**
  > *"Iniciamos con el **Flujo del Comprador**. Aquí observamos la pantalla de inicio con saludo personalizado, buscador en tiempo real y chips de categorías que filtran la cuadrícula de productos en `HomeCatalogFragment`. Al presionar un producto como el Labial Velvet, se abre `ProductDetailActivity` con galería, calificación de 5 estrellas, selector interactivo de tonos circulares y botón para agregar al carrito.  
  > En la pestaña de carrito, las cantidades se modifican dinámicamente con recalculo inmediato de subtotal, envío y total. Al avanzar a `SecurePaymentActivity`, el usuario valida su dirección, tarjeta enmascarada bajo norma de seguridad, y al confirmar, se despliega `OrderTrackingActivity` con la línea de tiempo en vivo y la garantía del derecho de retracto de la Ley 1480 de 2011."*

#### 2. Rol Vendedor (0:30 seg):
- **Acción:** Cerrar sesión y en Login seleccionar "Vendedor".
- **Voz:**
  > *"Cambiamos al **Portal de Vendedor**. En `SellerDashboardFragment` se visualizan 3 métricas KPI clave: ventas del día, pedidos y alertas de stock bajo, junto a accesos rápidos.  
  > En la pestaña 'Mis productos', el vendedor dispone del CRUD completo: puede buscar, filtrar por agotados o activos, y abrir el formulario de edición o de nuevo producto con zona de captura de fotografía mediante `CameraX` para actualizar el catálogo al instante. En 'Pedidos recibidos' se supervisa el estado de entrega por cliente."*

#### 3. Rol Administrador (0:30 seg):
- **Acción:** Cerrar sesión y en Login seleccionar "Administrador".
- **Voz:**
  > *"Finalmente, ingresamos al **Panel de Administración**. El dashboard despliega métricas consolidadas y una gráfica de barras de 7 días para análisis comercial.  
  > En 'Administrar usuarios', el administrador puede auditar cuentas y acceder a `UserDetailActivity` para aplicar bloqueos preventivos por sospecha de fraude, tal como exige la Ley 1273. Y en 'Pagos y auditoría', supervisamos la pasarela Stripe operativa y el registro cronológico inmutable de logs conforme a la Ley 527 de comercio electrónico."*

---

### 🕒 [3:30 - 4:20] Bloque 4: Componentes Transversales y Biometría Nativa
- **Qué mostrar en pantalla:** Regresar a la pantalla de Login y presionar el botón verde "Acceso seguro con huella". En Android Studio, mostrar la clase `BiometricAuthHelper.kt` y el `AndroidManifest.xml`.
- **Voz del expositor:**
  > *"Como requerimiento transversal, la app cuenta con una `SplashActivity` con animación de desvanecimiento, menús de navegación coordinados mediante `BottomNavigationView` y autenticación biométrica nativa mediante `androidx.biometric.BiometricPrompt`.  
  > En `BiometricAuthHelper.kt` consultamos el `BiometricManager` para verificar la disponibilidad del sensor local en el teléfono. Si el usuario confirma su huella, la validación se realiza en el Hardware Keystore aislado del procesador, dando cumplimiento a la Ley Estatutaria 1581 de 2012 de protección de datos personales."*

---

### 🕒 [4:20 - 5:00] Bloque 5: Compilación del APK, Repositorio Git y Cierre
- **Qué mostrar en pantalla:** Abrir la terminal integrada de Android Studio y mostrar el comando `./gradlew assembleDebug` y el log de Git con commits descriptivos.
- **Voz del expositor:**
  > *"Para la validación del aplicativo, el proyecto cuenta con el pipeline de compilación Gradle configurado en `build.gradle.kts` con `compileSdk 34` y `minSdk 24`. Ejecutando el comando `./gradlew assembleDebug` se genera satisfactoriamente el archivo APK en la carpeta `outputs/apk/debug`.  
  > El código fuente se encuentra versionado en GitHub con commits semánticos que reflejan el avance incremental del proyecto, acompañado de su archivo README exhaustivo y la documentación técnica corregida.  
  > Con esto, la tienda virtual Aura Beauty cumple al 100% con los criterios de evaluación exigidos. Muchas gracias por su atención."*

---

## 📌 Checklist de Verificación para el Estudiante antes de Grabar
- [ ] Tiempo total inferior a 5 minutos.
- [ ] Mención explícita de: XML, Layouts, Drawables vectoriales, Estilos, Values centralizados.
- [ ] Demostración de los 3 roles: Comprador, Vendedor y Administrador.
- [ ] Mención de la biometría (`BiometricPrompt`) y del marco legal (Ley 1480, 1581, 527).
- [ ] Demostración del comando para generar el APK (`assembleDebug`).
