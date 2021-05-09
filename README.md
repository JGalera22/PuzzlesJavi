Proyecto API Puzzles Javi
Esta aplicación simula una tienda de puzzles online.

Ha sido realizada con Spring Boot mediante el lenguaje de Kotlin.

Usuario registrado para acceder a la aplicación sin registrarse  
Username: mcampos  
Password: 12345678  
Se proporciona un archivo JSON con la colección de POSTMAN con todas las rutas de la API REST con datos de ejemplo en el Body.  

La aplicación por defecto se ejecuta en el puerto 9000  

Corregidos:  
La lista de usuario se muestra para los administradores.  
Los usuarios sin permiso no pueden visualizar a los usuarios, pero pueden entrar en la pestaña.  
Los datos se cargan correctamente.  
Filtro de busqueda de zona de administrador funciona correctamente y redirige a la lista de puzzles de administrador.  



Tareas que faltan por realizar:  
Arreglar que a los usuarios no admin les salga el icono de la zona de administración (Android)  
Zona de pedido (API)  
Eliminar un puzzle de la lista de deseados, lo vuelve a añadir en vez de eliminarlo.(Entra en el if correcto pero no ejecuta el DELETE en Android)  
Rutas de Editar o Añadir un puzzle en la zona de admin no funcionan (Android).
