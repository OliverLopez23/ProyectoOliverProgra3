# Technical Manual for Vehicle Registry System

## Introduction
The Vehicle Registry System is a Java-based desktop application for managing vehicle records, fines, and transfers in Guatemala, with statistical analysis capabilities. This manual provides developers with an overview of the system’s architecture, class structure, and instructions for maintenance and extension.

## System Overview
- **Purpose**: Manage vehicle data (fines, transfers, vehicle details) across departments and provide statistics (e.g., top fined vehicles, search performance).
- **Technology Stack**:
  - **Language**: Java (JDK 8+).
  - **GUI Framework**: Swing.
  - **Data Storage**: Text files in `C:\Users\Mayby\Desktop\SIRVE_Datos_Vehiculos DataSet - copia\<department>`.
  - **Data Structures**: Doubly linked lists (`ListaDobleMultas`), circular linked lists (`ListaCircularTraspasos`), binary search trees (`Arboles`), AVL trees (`ArbolesAVL`).
- **Key Features**:
  - CRUD operations for fines and transfers.
  - Statistical reports (top 10 fines/transfers, busiest month, search algorithm performance).
  - File-based persistence per department.

## System Architecture
- **Layered Design**:
  - **Presentation Layer**: Swing JFrames (`RegistroVehiculosGT`, `Estadistica`) for user interaction.
  - **Business Logic Layer**: Classes like `MetEstadistica`, `InformacionMT` handle data processing and statistics.
  - **Data Access Layer**: `ListaDobleMultas`, `ListaCircularTraspasos`, `Arboles`, `ArbolesAVL` manage data storage and retrieval.
- **Data Flow**:
  - User inputs via `RegistroVehiculosGT` trigger operations (e.g., add fine, view stats).
  - `InformacionMT` reads/writes text files and updates in-memory structures.
  - `MetEstadistica` processes data for statistical reports displayed in `Estadistica`.
- **File Structure**:
  - Each department has a subfolder (e.g., `Guatemala`).
  - Files: `<department>_multas.txt` (fines), `<department>_traspasos.txt` (transfers), `<department>_vehiculos.txt` (vehicles).
  - File formats:
    - Fines: `placa,fecha,descripcion,monto` (e.g., `ABC123,2023-05-01,Speeding,500.00`).
    - Transfers: `placa,dpiAnt,nombreAnt,fecha,dpiNew,nombreNew` (e.g., `ABC123,123456789,John Doe,2023-05-01,987654321,Jane Doe`).

## Class Structure
### Core Classes
1. **RegistroVehiculosGT**:
   - **Purpose**: Main JFrame for user interaction.
   - **Key Features**:
     - “Mirar Estadística” button opens `Estadistica`.
     - (Assumed) UI for managing fines/transfers.
   - **Dependencies**: `Estadistica`.
2. **Estadistica**:
   - **Purpose**: JFrame displaying statistical reports.
   - **Components**:
     - Tables for top 10 fines/transfers.
     - Text fields for busiest month and algorithm performance.
   - **Dependencies**: `MetEstadistica`, `InformacionMT`, `Arboles`, `ArbolesAVL`.
3. **MetEstadistica**:
   - **Purpose**: Handles statistical computations.
   - **Methods**:
     - `llenarTablaMultas`: Populates top 10 fines table.
     - `llenarTablaTraspasos`: Populates top 10 transfers table.
     - `encontrarMesConMasMovimientos`: Finds month with most fines/transfers.
     - `calcularTiemposPromedio`: Measures ABB/AVL search times.
   - **Dependencies**: `InformacionMT`, `Arboles`, `ArbolesAVL`, `ListaDobleMultas`, `ListaCircularTraspasos`, `Multa`, `Traspaso`.
4. **ListaDobleMultas**:
   - **Purpose**: Manages fines using a doubly linked list.
   - **Key Methods**:
     - `insertar`: Adds a fine.
     - `guardarMulta`, `actualizarMulta`, `eliminarMulta`: CRUD operations with file persistence.
     - `getAllMultas`: Returns `List<Multa>` for statistics.
   - **Dependencies**: `InformacionMT`, `Multa`.
5. **ListaCircularTraspasos**:
   - **Purpose**: Manages transfers using a circular linked list.
   - **Key Methods**:
     - `insertar`: Adds a transfer.
     - `guardarTraspaso`, `actualizarTraspaso`, `eliminarTraspaso`: CRUD operations.
     - `getAllTraspasos`: Returns `List<Traspaso>`.
   - **Dependencies**: `InformacionMT`, `Traspaso`.
6. **Multa**:
   - **Purpose**: DTO for fine data (placa, fecha, descripcion, monto).
7. **Traspaso**:
   - **Purpose**: DTO for transfer data (placa, dpiAnt, nombreAnt, fecha, dpiNew, nombreNew).
8. **InformacionMT** (Inferred):
   - **Purpose**: Manages file I/O and coordinates data across departments.
   - **Assumed Methods**:
     - `cargarMultas`, `cargarTraspasos`: Load data from files.
     - `getMultasPorDepartamento`, `getTraspasosPorDepartamento`: Access in-memory data.
     - `actualizarVehiculoMulta`, `actualizarVehiculoTraspaso`: Update vehicle records.
9. **Arboles** (Inferred):
   - **Purpose**: Binary search tree for vehicle searches.
   - **Assumed Methods**:
     - `leerArchivos`: Loads vehicle data.
     - `buscarPorPlaca`: Searches by license plate.
     - `inOrden`: Populates table with vehicle data.
10. **ArbolesAVL** (Inferred):
    - **Purpose**: AVL tree for balanced vehicle searches.
    - **Assumed Methods**: Similar to `Arboles`.

### Dependencies
- **External Libraries**: None (standard Java libraries: `javax.swing`, `java.io`, `java.time`, `java.util`).
- **Internal Dependencies**:
  - `Estadistica` → `MetEstadistica` → `InformacionMT`, `ListaDobleMultas`, `ListaCircularTraspasos`, `Arboles`, `ArbolesAVL`.
  - `RegistroVehiculosGT` → `Estadistica`.

## Installation and Setup
1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   ```
2. **Set Up Data Folder**:
   - Create `C:\Users\Mayby\Desktop\SIRVE_Datos_Vehiculos DataSet - copia`.
   - Add subfolders for departments (e.g., `Guatemala`).
   - Populate with text files following the specified formats.
3. **Compile and Run**:
   - Use an IDE (e.g., NetBeans, IntelliJ) or command line:
     ```bash
     javac -d bin src/proyectoprogra3/*.java
     java -cp bin proyectoprogra3.RegistroVehiculosGT
     ```
4. **Package as JAR**:
   ```bash
   jar cfm VehicleRegistry.jar Manifest.txt -C bin .
   ```
   - `Manifest.txt`: `Main-Class: proyectoprogra3.RegistroVehiculosGT`.

## Maintenance Instructions
1. **Adding New Statistics**:
   - Extend `MetEstadistica` with new methods (e.g., `calcularPromedioMultasPorDepartamento`).
   - Update `Estadistica` UI to display new data (add tables/text fields).
2. **Modifying Data Structures**:
   - Replace `Arboles`/`ArbolesAVL` with alternative structures (e.g., hash tables) by updating `MetEstadistica`’s `calcularTiemposPromedio`.
   - Ensure new structures support `buscarPorPlaca` and `inOrden`.
3. **Improving File I/O**:
   - Replace text files with a database (e.g., SQLite) by modifying `InformacionMT`.
   - Update `ListaDobleMultas`/`ListaCircularTraspasos` to use database queries.
4. **Error Handling**:
   - Add try-catch blocks in `MetEstadistica` for file access errors.
   - Enhance `JOptionPane` messages in `ListaDobleMultas`/`ListaCircularTraspasos` for user feedback.
5. **Performance Optimization**:
   - Cache department data in `InformacionMT` to reduce file reads.
   - Use concurrent data structures for large datasets.

## Extending the System
1. **New Features**:
   - **Vehicle Search UI**: Add a search panel in `RegistroVehiculosGT` using `Arboles.buscarPorPlaca`.
   - **Data Export**: Add export functionality in `Estadistica` to save tables as CSV.
2. **New Data Types**:
   - Create new classes (e.g., `ListaAccidentes`) similar to `ListaDobleMultas`.
   - Update `InformacionMT` to handle new file types.
3. **UI Enhancements**:
   - Use JavaFX for a modern UI, replacing Swing.
   - Add charts (e.g., JFreeChart) in `Estadistica` for visual statistics.

## Known Issues
- **Hardcoded Path**: Data folder path is hardcoded; consider a configuration file.
- **No Singleton for Estadistica**: Multiple `Estadistica` windows can open; implement a singleton pattern if needed.
- **File Format Sensitivity**: Invalid file formats (e.g., malformed dates) may cause errors; add robust parsing.

## Testing
- **Unit Tests**:
  - Test `MetEstadistica` methods with mock `InformacionMT` data.
  - Use JUnit for `ListaDobleMultas`/`ListaCircularTraspasos` CRUD operations.
- **Integration Tests**:
  - Verify `Estadistica` tables populate correctly with sample data.
  - Test file I/O with various department configurations.
- **Sample Data**:
  - Create test files in `C:\Users\Mayby\Desktop\SIRVE_Datos_Vehiculos DataSet - copia\Guatemala` with 100+ records.

## Version
- **Version 1.0** (June 2025)
- Developed by [Your Name/Team Name].

## Contact
- For technical support, contact the development team at [your-email@example.com].