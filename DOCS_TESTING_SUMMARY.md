Título: Resumen de pruebas y conclusiones

Objetivo
-------
Documento que resume las pruebas añadidas al proyecto, qué métodos y endpoints fueron evaluados, qué comportamientos se validaron, problemas detectados y las acciones tomadas para que las pruebas de integración funcionen en H2.

Checklist (contenido del documento)
- Lista de tests creados (tipo y ubicación)
- Métodos/endpoint evaluados por cada test
- Comportamientos y aserciones principales
- Casos límite y escenarios cubiertos
- Problemas encontrados durante la integración y solución aplicada
- Archivos modificados y nuevos añadidos
- Instrucciones para ejecutar pruebas localmente
- Recomendaciones y siguientes pasos

1) Tests añadidos y su clasificación
---------------------------------
Unitarios (Mockito / pruebas aisladas):
- `src/test/java/com/social/backend/service/UsuarioServiceTest.java`
  - Clase bajo prueba: `UsuarioService`
  - Métodos evaluados: `guardar(Usuario)`, `generarNuevoId()` (privado, indirecto a través de `guardar`), `loginPorEmailYCelular(String,String)`, `obtenerPorCelular(String)`, `obtenerTodos()`, `obtenerAmigos(String)` (donde aplica).
  - Estrategia: mocks de `UsuarioRepository`; se validaron incrementos de ID, normalización de email (lowercase), y manejo de conflictos (email/celular existentes) mediante excepciones.

- `src/test/java/com/social/backend/service/MensajeServiceTest.java`
  - Clase bajo prueba: `MensajeService`
  - Métodos evaluados: `crearMensajeConContenido(MensajeCrearDTO)`, `obtenerMensajesEntre(String,String)`, `guardarMensaje(Mensaje)` (si existe prueba directa).
  - Estrategia: mocks de `MensajeRepository` y `ContenidoRepository`; uso de ArgumentCaptor para inspeccionar entidades guardadas (IDs, relaciones, campos como `idTipoContenido`, `idTipoArchivo`, `localizaContenido` y `contenidoImag`). Se comprobó que:
    - se calcula `consMesaje` como `max + 1` usando `findMaxConsMensaje()`;
    - se calcula `conseContenido` como `max + 1` usando `findMaxConseContenido()`;
    - se guardan tanto `Mensaje` como `Contenido` con las relaciones de claves compuestas correctas.

Controller (pruebas unitarias con MockMvc standalone / sin contexto completo):
- `src/test/java/com/social/backend/controller/UsuarioControllerTest.java`
  - Controller bajo prueba: `UsuarioController`
  - Endpoints evaluados: `POST /api/usuarios` (crearUsuario), `GET /api/usuarios` (obtenerTodos), `POST /api/usuarios/login` (login), `GET /api/usuarios/por-celular/{celular}`, `GET /api/usuarios/amigos/{id}`.
  - Estrategia: MockMvc en modo standalone con un mock manual de `UsuarioService`; se verificaron códigos HTTP y cuerpos esperados (creación, conflicto, autorización, lista de usuarios, etc.).

- `src/test/java/com/social/backend/controller/MensajeControllerTest.java`
  - Controller bajo prueba: `MensajeController`
  - Endpoints evaluados: `GET /api/mensajes/{user1}/{user2}` (obtenerMensajesEntreUsuarios), `POST /api/mensajes` (crearMensaje).
  - Estrategia: MockMvc standalone; mock de `MensajeService`; comprobación de status (200 OK para GET, 201 CREATED para POST) y que el servicio es llamado con DTOs correctos.

Integración (Spring context + base de datos en memoria H2):
- `src/test/java/com/social/backend/integration/MensajeIntegrationTest.java`
  - Objetivo: verificar la integración entre `MensajeService`, `MensajeRepository` y `ContenidoRepository` y la persistencia real de `Mensaje` y `Contenido` usando una base de datos en memoria.
  - Perfil de pruebas: `@ActiveProfiles("test")` para usar `src/test/resources/application-test.properties`.
  - Comportamientos evaluados: que `crearMensajeConContenido(dto)` inserta filas en las tablas correspondientes y mantiene consistencia de IDs y relaciones.

2) Métodos y endpoints cubiertos (resumen por clase)
---------------------------------------------------
- UsuarioService
  - `guardar(Usuario)` — validación de unicidad (email, celular), normalización de email y asignación de `consecUser` nuevo; guarda en repositorio.
  - `generarNuevoId()` — (probado indirectamente) incremento correcto a partir del último `consecUser` existente y manejo de valores no numéricos.
  - `loginPorEmailYCelular(String email, String celular)` — búsqueda por email y celular; lanza excepción en credenciales inválidas.
  - `obtenerPorCelular(String celular)` — devuelve usuario por celular o lanza `NoSuchElementException`.
  - `obtenerTodos()` y `obtenerAmigos(String)` — delegación a repositorio.

- UsuarioController
  - `crearUsuario(Usuario)` (POST `/api/usuarios`) — mapea comportamiento de `guardar`; devuelve 201 o 409 en conflicto.
  - `obtenerTodos()` (GET `/api/usuarios`) — devuelve lista completa.
  - `login(LoginRequest)` (POST `/api/usuarios/login`) — mapea a `loginPorEmailYCelular` y devuelve 200 o 401.
  - `obtenerUsuarioPorCelular(String)` (GET `/api/usuarios/por-celular/{celular}`)
  - `obtenerAmigos(String)` (GET `/api/usuarios/amigos/{id}`)

- MensajeService
  - `crearMensajeConContenido(MensajeCrearDTO)` — lógica compuesta que:
    - calcula IDs (mensaje y contenido) usando `findMax*()` de los repositorios;
    - crea y guarda `Mensaje` con `MensajeId` compuesto;
    - crea y guarda `Contenido` asociado con `ContenidoId` y `contenidoImag` (byte[]);
  - `obtenerMensajesEntre(String, String)` — delega a repositorio con consulta custom.

- MensajeController
  - `obtenerMensajesEntreUsuarios(String user1, String user2)` (GET `/api/mensajes/{user1}/{user2}`)
  - `crearMensaje(MensajeCrearDTO)` (POST `/api/mensajes`)

3) Casos y aserciones principales validadas
------------------------------------------
- Unicidad: `guardar` falla con `IllegalArgumentException` cuando email o celular ya existen.
- Normalización: email siempre guardado en minúsculas.
- Generación de IDs: `consecUser`, `consMesaje`, `conseContenido` incrementan correctamente a partir del máximo actual.
- Persistencia relacional: `Mensaje` y `Contenido` se guardan con claves compuestas coherentes (integridad referencial lógica).
- Endpoints HTTP: códigos de estado correctos para creación (201), éxito (200), conflicto (409) y no autorizado (401).

4) Problemas detectados durante integración y solución aplicada
--------------------------------------------------------------
- Problema: al ejecutar la integración con H2, Hibernate generaba SQL/DDL específico de PostgreSQL (p. ej. `set client_min_messages = WARNING`) y tipos `OID` para columnas `@Lob`, lo que provocaba errores de creación de esquema en H2.
- Acción tomada: en `src/test/resources/application-test.properties` se añadió la propiedad para forzar el dialecto H2:
  - `spring.jpa.database-platform=org.hibernate.dialect.H2Dialect`
- Resultado esperado de la acción: Hibernate genera DDL compatible con H2 (BLOB en lugar de OID) y evita comandos de sesión PostgreSQL; con esto, las pruebas de integración pueden ejecutar la generación de esquema y pasar en H2.

5) Archivos creados/modificados (lista)
--------------------------------------
- Nuevos tests:
  - `src/test/java/com/social/backend/service/UsuarioServiceTest.java`
  - `src/test/java/com/social/backend/controller/UsuarioControllerTest.java`
  - `src/test/java/com/social/backend/service/MensajeServiceTest.java`
  - `src/test/java/com/social/backend/controller/MensajeControllerTest.java`
  - `src/test/java/com/social/backend/integration/MensajeIntegrationTest.java`
- Configuración de pruebas:
  - `src/test/resources/application-test.properties` (actualizado para H2 dialect)
- Cambios en producción para pruebas:
  - `src/main/java/com/social/backend/controller/UsuarioController.java` — añadido constructor para inyección manual en tests MockMvc standalone.
- `pom.xml` — se añadió dependencia de `com.h2database:h2` con scope `test`.

6) Cómo reproducir localmente (comandos)
--------------------------------------
Ejecutar todos los tests (unitarios + integración) desde la raíz del proyecto (PowerShell):

```powershell
cd "C:\Users\ddavi\OneDrive\Documentos\Hanna\Piccolart\Social-backend"
.\mvnw.cmd test
```

7) Recomendaciones y siguientes pasos
-------------------------------------
- Mantener la configuración actual de pruebas si buscas rapidez y simplicidad en CI.
- Si necesitas pruebas contra un entorno idéntico a producción (PostgreSQL): configurar Testcontainers con PostgreSQL y crear un profile de integración que lo use.
- Revisar mapeos LOB (byte[]) si más portabilidad es requerida: se puede añadir `@Column(columnDefinition = "BLOB")` para evitar dependencia en dialectos, pero hay que validar compatibilidad con PostgreSQL.
- Añadir cobertura adicional para casos de error (mensajes nulos, tamaños de payload, comportamiento transaccional en fallos durante guardado de `Contenido`).

8) Registro de cambios (resumen)
--------------------------------
- Se añadieron tests unitarios y de integración, se actualizó `application-test.properties` para H2, se añadió dependencia H2 en `pom.xml`.

Fin del documento.

