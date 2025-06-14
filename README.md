# ProyectoOliverProgra3
Proyecto del Curso de Programación III de la UMG 5to semestre

# Manual de Usuario del Sistema de Registro Vehicular

## Introducción
El Sistema de Registro Vehicular es una aplicación de escritorio diseñada para gestionar los registros de vehículos en Guatemala, incluyendo multas y traspasos de propiedad, y para proporcionar análisis estadísticos. Este manual guía a los usuarios en el acceso y uso de las funcionalidades de la aplicación.

## Requisitos del Sistema
- **Sistema Operativo**: Windows (probado en Windows 10/11).
- **Entorno de Ejecución Java**: JRE 8 o superior.
- **Espacio en Disco**: Al menos 100 MB para archivos de datos.
- **Carpeta de Datos**: Asegúrese de que exista la carpeta `C:\Users\Mayby\Desktop\SIRVE_Datos_Vehiculos DataSet - copia` con subcarpetas para cada departamento (por ejemplo, `Guatemala`, `Quetzaltenango`) que contengan archivos como `<departamento>_multas.txt`, `<departamento>_traspasos.txt` y `<departamento>_vehiculos.txt`.

## Primeros Pasos
1. **Iniciar la Aplicación**:
   - Haga doble clic en el archivo JAR ejecutable o ejecute `java -jar VehicleRegistry.jar` desde la línea de comandos.
   - Se abrirá la ventana principal (`RegistroVehiculosGT`).

2. **Descripción de la Ventana Principal**:
   - La ventana principal brinda acceso a las funciones de gestión de vehículos y a un botón para ver estadísticas.
   - Función clave: botón **"Mirar Estadística"** para abrir el panel de estadísticas.

## Visualización de Estadísticas
1. **Abrir el Panel de Estadísticas**:
   - En la ventana principal, haga clic en el botón **"Mirar Estadística"**.
   - Se abrirá una nueva ventana (`Estadistica`) con informes estadísticos.

2. **Características del Panel de Estadísticas**:
   - **Top 10 Vehículos con Más Multas**:
     - Tabla con los 10 vehículos con más multas, mostrando:
       - Posición (1–10)
       - Placa
       - Número de Multas
       - Monto Total de Multas
       - Departamento

   - **Top 10 Vehículos con Más Traspasos**:
     - Tabla con los 10 vehículos con más traspasos de propiedad, mostrando:
       - Posición (1–10)
       - Placa
       - Número de Traspasos
       - Departamento

   - **Mes con Más Movimientos**:
     - Muestra el mes (ej. `2023-05`) con el mayor número combinado de multas y traspasos.
     - Muestra el total de movimientos de ese mes.

   - **Rendimiento del Algoritmo**:
     - Muestra métricas de rendimiento de búsqueda para dos estructuras de datos (ABB y árboles AVL):
       - Tiempo máximo de búsqueda ABB (µs)
       - Tiempo mínimo de búsqueda ABB (µs)
       - Tiempo promedio de búsqueda ABB (µs)
       - Tiempo promedio de búsqueda AVL (µs)

3. **Cerrar el Panel**:
   - Haga clic en el botón cerrar (X) de la ventana `Estadistica` para regresar a la ventana principal.

## Gestión de Datos de Vehículos
**Nota**: Este manual asume que la gestión de datos (por ejemplo, agregar multas/traspasos) se realiza a través de `RegistroVehiculosGT`. Como no se proporcionaron detalles específicos de la interfaz, se da una guía general.

1. **Agregar Multas**:
   - Navegue a la sección de multas en la ventana principal (si está disponible).
   - Ingrese los datos: placa, fecha (AAAA-MM-DD), descripción, monto y departamento.
   - Guarde la multa; se registrará en `<departamento>_multas.txt`.

2. **Agregar Traspasos**:
   - Navegue a la sección de traspasos.
   - Ingrese los datos: placa, DPI/nombre del dueño anterior, fecha, DPI/nombre del nuevo dueño y departamento.
   - Guarde el traspaso; se registrará en `<departamento>_traspasos.txt`.

3. **Actualizar/Eliminar Datos**:
   - Use los controles de la interfaz para localizar y modificar multas o traspasos existentes.
   - Confirme los cambios para actualizar los archivos de texto correspondientes.

## Resolución de Problemas
- **Panel de Estadísticas Vacío**:
  - Asegúrese de que la carpeta de datos contenga archivos válidos.
  - Verifique que los archivos sigan el formato correcto (ej. `placa,fecha,descripcion,monto` para multas).

- **Errores en la Aplicación**:
  - Verifique que Java esté instalado y actualizado.
  - Revise los permisos de los archivos en la carpeta de datos.

- **Datos Inválidos**:
  - Asegúrese de que las fechas tengan formato `AAAA-MM-DD` y los montos sean numéricos.

- **Soporte Técnico**:
  - Para problemas, contacte al administrador del sistema o al equipo de desarrollo.

## Glosario
- **Multa**: Sanción registrada contra un vehículo.
- **Traspaso**: Cambio de propiedad de un vehículo.
- **ABB/AVL**: Estructuras de datos utilizadas para búsquedas eficientes.
- **Departamento**: Región administrativa en Guatemala (ej. Guatemala, Sacatepéquez).

## Versión
- **Versión 1.0** (Junio 2025)
- Desarrollado por [Tu Nombre o Nombre del Equipo].

# Manual Técnico del Sistema de Registro Vehicular

## Introducción
El Sistema de Registro Vehicular es una aplicación de escritorio basada en Java para gestionar registros de vehículos, multas y traspasos en Guatemala, con capacidades de análisis estadístico. Este manual ofrece a los desarrolladores una visión general de la arquitectura del sistema, estructura de clases e instrucciones de mantenimiento y extensión.

## Visión General del Sistema
- **Propósito**: Gestionar datos vehiculares (multas, traspasos, detalles de vehículos) por departamentos y generar estadísticas.
- **Tecnologías Utilizadas**:
  - **Lenguaje**: Java (JDK 8+).
  - **Interfaz Gráfica**: Swing.
  - **Almacenamiento**: Archivos de texto en `C:\Users\Mayby\Desktop\SIRVE_Datos_Vehiculos DataSet - copia\<departamento>`.
  - **Estructuras de Datos**: Listas dobles (`ListaDobleMultas`), listas circulares (`ListaCircularTraspasos`), árboles binarios de búsqueda (`Arboles`), árboles AVL (`ArbolesAVL`).

- **Características Principales**:
  - Operaciones CRUD para multas y traspasos.
  - Informes estadísticos.
  - Persistencia de datos por archivo por departamento.

## Arquitectura del Sistema
- **Diseño por Capas**:
  - **Capa de Presentación**: JFrames de Swing (`RegistroVehiculosGT`, `Estadistica`).
  - **Lógica de Negocio**: Clases como `MetEstadistica`, `InformacionMT`.
  - **Acceso a Datos**: `ListaDobleMultas`, `ListaCircularTraspasos`, `Arboles`, `ArbolesAVL`.

- **Flujo de Datos**:
  - El usuario interactúa con `RegistroVehiculosGT`.
  - `InformacionMT` lee/escribe archivos de texto.
  - `MetEstadistica` procesa datos para mostrar en `Estadistica`.

- **Estructura de Archivos**:
  - Subcarpetas por departamento (ej. `Guatemala`).
  - Archivos:
    - Multas: `placa,fecha,descripcion,monto`
    - Traspasos: `placa,dpiAnt,nombreAnt,fecha,dpiNew,nombreNew`

## Estructura de Clases
### Clases Principales
1. **RegistroVehiculosGT**: Ventana principal. Acceso a estadísticas y gestión.
2. **Estadistica**: Muestra los informes.
3. **MetEstadistica**: Computa estadísticas.
4. **ListaDobleMultas**: Maneja multas con lista doblemente enlazada.
5. **ListaCircularTraspasos**: Maneja traspasos con lista circular.
6. **Multa** y **Traspaso**: DTOs con datos de multa/traspaso.
7. **InformacionMT**: Carga y gestiona archivos.
8. **Arboles / ArbolesAVL**: Árboles de búsqueda y búsqueda balanceada.

### Dependencias
- Librerías externas: Ninguna (Java estándar).
- Relación entre clases:
  - `RegistroVehiculosGT` → `Estadistica` → `MetEstadistica` → datos

## Instalación y Configuración
1. **Clonar Repositorio**:
   ```bash
   git clone <url-repositorio>
   ```
