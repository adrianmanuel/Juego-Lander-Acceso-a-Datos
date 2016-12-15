# Juego-Lander-Acceso-a-Datos
Aplicación web creada en Java con JPA, servlets, jsp, html, css, javascript y ajax.
- El diseño de la interfaz de usuario, sigue el mismo diseño del juego que nos proporcionó el profesor.
- He utilizado JPA para hacer un acceso a base de datos persistentes, para poder tratar los registros de
la base de datos como si fueran objetos en Java.

- El proyecto cuenta con los siguientes JSP:

  -index.jsp (es la primera página que se muestra al usuario, la cuál permite iniciar sesión o crear cuenta)
  
  -errorregistro.jsp (en caso de que el usuario que intentamos registrar no pueda hacerlo, se muestra error)
  
  -errorsesion.jsp (en caso de que sea erroneo el inicio de sesión sale su error)
  
  -iniciodesesion.jsp (carga un formulario para hacer el inicio y enviar los datos por post al servlet)
  
  -registro.jsp (carga un formulario para hacer el registro de una nueva cuenta y lo pasa al servlet)
  
  -menu.jsp (será la página que cargará una vez se inicie sesión botones que llevan a acciones)
  
  -juego.jsp (es la página encargada de cargar el juego)
  
  -salir.jsp (cierra la sesión del usuario y envia a la pantalla index.jsp)
  
- Servlets

  -login.java (se encarga de validar a través de la lista de usuarios si el usuario existe y guarda datos en las
  cookies del navegador)
  
  -registrar.java (se encarga de validar el formulario de registro comprobando si no hay otro usuario igual)
  
  -iniciar.java (se encarga de guardar los datos de la partida a través de ajax que lo envia por post)
  
  -mispartidas.java (saca por pantalla las partidas del usuario durante el tiempo)
  
  -top10.java (saca por pantalla las 10 mejores partidas de todos los usuarios registrados)
  
  -enlinea.java (saca los últimos usuarios que han jugado partida)
  
  -salir.java (se encarga de enviar al usuario al index.jsp)
  
- Package.Services
  Son los controladores encargados de manejar la lista de usuarios y partidas.
  
- Package.ModeloBD
  Son las clases creadas con JPA a partir de la base de datos.

- Javascript juego
  Dentro del juego se ha añadido un método ajax que permite recoger los datos que necesitamos para registrar las partidas
  y los envía a través de un post.
  
- Base de datos
  He utilizado dos tablas, vinusuarios y vinpartidas, vinpartidas tiene como FK el id del usuario.
  
- Formulario de registro
  A través de required y de pattern para telefono y email, valido si lo introducido es correcto, así me evito por ejemplo
  que en el campo del teléfono me puedan meter un string, de esta manera la aplicación no da error.
  
**Puntos a Mejorar**
- El diseño podría ser algo más bonito.
- Algunos servlets arrojan directamente una respuesta HTML, se debería realizar un request dispatcher.
- Algunas consultas podrían realizarse de otra manera.
- Encriptación, ya que la contraseña, usuario y cookies van en texto plano.
