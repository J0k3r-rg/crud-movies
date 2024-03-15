# CRUD MOVIE

## Ramas del proyecto

   - curd-initial:
        - En esta rama esta el crud inicial de la app el cual tiene para agragar, eliminar, guardar y modificar: 
            - Generos de peliculas
            - Autores
            - Peliculas
   - spring security:
     - Se implementa la seguridad en la aplicacion mediante usuario y contrasena en cada solicitud de la api
   - jwt-implementacion:
     - Se le agrega la autorizacion y autenticacion por token, haciendo una solicitud de tipo post al endpoint /login y en el body se envian las credenciales {"username" : "user","password": "pass"}


Esta Aplicacion esta realizada con las siguientes tecnologias:
```
    - Java 17
    - Spring
    - Spring web
    - Spring Data JPA
    - BD H2
    - Lombok
    - Spring Security
    - JWT
```