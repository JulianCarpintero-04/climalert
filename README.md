# Climalert
Tomé como base una plantilla dispuesta por la cátedra, la cual primero la leí por completo, verificando que la implementación sea la correcta.
Dentro de la implementación me fijé primeramente en la estructura del archivo, dividido en las respectivas capas.
Controller para manejar los endpoints, service para darle la lógica al consumo del clima y la generación de alertas y el repositorio para almacenar de forma local los datos obtenidos.

Luego verifiqué la forma de consumir la api, por lo que leí parte de la documentación de Wheather API para asegurarme que se esté consumiendo de forma correcta. Además de leer la documentación, me registré, generé la API KEY y verifiqué mediante el Swagger propuesto. Allí consulté el clima de "Buenos Aires" aclarando el idioma español con "es" allí verifiqué que el mensaje que me devolvía era correcto, por lo que la api estaba funcionando correctamente.

Cuando ya supe que la api estaba en funcionamiento, comencé a ejecutar mi código y observé los logs de la consola.
Como la temperatura actualmente en CABA es baja, no cumplía la condición para dar la alerta, sin embargo podía ver los datos del clima en los logs de mi consola.
Ante la situación de no cumplir las condiciones climaticas para que me tire una alerta, decidí bajar los valores de temperatura y humedad para verificar que la implementación de las alertas era la correcta. (Las alertas se loggearon correctamente)

Por último, me pareció interesante implementar realmente el envío al mail de la alerta climática, por lo que hice algunos cambios en la implementación del código (creo que el foco del ejercicio no es el envío por mail). Allí agregué una dependencia que provee spring boot para enviar mails (spring-boot-starter-mail) que me dá la herramienta JavaMailSender y utilicé SMTP para hacer efectivo el envío. SMTP puede pedir credenciales de autenticación y activa un protocolo de seguridad TLS para garantizar que el contenido del correo y las contraseñas viajen encriptadas.

## *Agrego capturas de pantalla (Archivos en .png) que van siguiendo todo el proceso
