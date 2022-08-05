CREATE DATABASE IF NOT EXISTS booking;

/* Crate tables */

CREATE TABLE booking.categorias (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo varchar(30) NOT NULL,
    descripcion varchar(100) NOT NULL,
    url_imagen varchar(255) NOT NULL
);

CREATE TABLE booking.ciudades (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre varchar(100) NOT NULL,
    pais varchar(30) NOT NULL
);

CREATE TABLE booking.productos (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo varchar(100) NOT NULL,
    nombre varchar(70) NOT NULL,
    descripcion varchar(255) NOT NULL,
    direccion varchar(255),
    latitud float,
    longitud float,
    categoria_id int NOT NULL,
	ciudad_id int NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES booking.categorias (id),
    FOREIGN KEY (ciudad_id) REFERENCES booking.ciudades (id)
);

CREATE TABLE booking.imagenes (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo varchar(150) NOT NULL,
    url_imagen varchar(255) NOT NULL
);

CREATE TABLE booking.politicas (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    descripcion varchar(255) NOT NULL
);

CREATE TABLE booking.caracteristicas (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    descripcion varchar(150) NOT NULL,
    icono varchar(50) NOT NULL
);

CREATE TABLE booking.usuarios (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    apellido varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    email varchar(150) NOT NULL,
    nombre varchar(100) NOT NULL,
    ciudad_id int,
    FOREIGN KEY (ciudad_id) REFERENCES booking.ciudades (id)
);

CREATE TABLE booking.roles (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre varchar(40) NOT NULL
);

CREATE TABLE booking.usuario_roles (
    id_usuario int NOT NULL,
    id_rol int NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES booking.usuarios (id),
    FOREIGN KEY (id_rol) REFERENCES booking.roles (id)
);

CREATE TABLE booking.reservas (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    hora_llegada varchar(40) NOT NULL,
    fecha_inicial date NOT NULL,
    fecha_final date NOT NULL,
    producto_id int NOT NULL,
    usuario_id int NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES booking.productos (id),
    FOREIGN KEY (usuario_id) REFERENCES booking.usuarios (id)
);

ALTER TABLE booking.imagenes
ADD producto_id int NOT NULL,
ADD FOREIGN KEY (producto_id) REFERENCES booking.productos(id);

ALTER TABLE booking.politicas
ADD producto_id int NOT NULL,
ADD FOREIGN KEY (producto_id) REFERENCES booking.productos(id);

ALTER TABLE booking.caracteristicas
ADD producto_id int NOT NULL,
ADD FOREIGN KEY (producto_id) REFERENCES booking.productos(id);

/* Create tables end */
/*------------------------------------------------------*/

CREATE UNIQUE INDEX idx_cat_titulo ON booking.categorias(titulo);
CREATE UNIQUE INDEX idx_us_email ON booking.usuarios(email);

/*------------------------------------------------------*/
        /*-- CATEGORIAS*/
INSERT INTO booking.categorias (titulo, descripcion, url_imagen) 
    VALUES 
        ('Hoteles', 'Hospedaje en los mejores hoteles con una increible comodidad para usted', '/Categorias/hoteles.jpg'),
        ('Hostels', 'Hospedajes economicos para el viajero', '/Categorias/hostels.jpg'),
        ('Departamentos', 'Alquile los mejores departamentos al menor precio', '/Categorias/departamentos.jpg'),
        ('Bed and breakfast', 'Una gran comodidad, con un excelente desayuno', '/Categorias/bed_breakfast.jpg');
        
/*       -- ____________________________________________________ */
-- CIUDADES */
	INSERT INTO booking.ciudades (nombre, pais) 
    VALUES
    ('Buenos Aires', 'Argentina'),
    ('Bariloche', 'Argentina'),
	('Carlos Paz', 'Argentina'),
	('Bogota', 'Colombia'),
	('Medellin', 'Colombia'),
	('Cartagena', 'Colombia');
    
	/*-- ____________________________________________________ */
    /*-- HOTELES (productos del 1 al 6)*/
    INSERT INTO booking.productos (nombre, descripcion, titulo,direccion,latitud,longitud, categoria_id, ciudad_id)
    VALUES
    ('Petit hotel','Disfrute de este maravilloso hotel, con las mejores comodidades disponibles en el mercado. Habitaciones sofisticadas y diseño exclusivo para usted.
    Nos encontramos cerca de los principales puntos de atraccion de la ciudad',
    'Alojate en el mejor hotel de Buenos Aires','Cnel. Ramón L. Falcón 2799, C1406 GOC, Buenos Aires, Argentina','-34.63187270152828', '-58.467670187931176', '1', '1'),
    ('Bello hotel','Maravillese con el encanto hecho diseño. Amplios espacios unicamente creados para que disfrute de su estadia. Todo el personal a su servicio.',
    'Estadias que marcan la diferencia','Av González Valencia 52 - 69','-34.61699198116621', '-58.38021562917836', '1', '1'),
    ('La estacion hotel','Si es amante de las aventuras, su estadia en nuestro hotel sera un gran recuerdo que disfrutara rememorar. Contamos con amplios jardines para su disfrute. 
    Habitaciones amplias y con vista al lago. Nos encontramos a 15 minutos del centro de la ciudad.',
    'Disfruta sin limite en este maravilloso hotel.','Av Illia 713, Villa Dominguez, X5152 Villa Carlos Paz, Córdoba, Argentina','-31.412701497206047','-64.49550223658858', '1', '3'),
    ('La flor hotel','Inigualables comodidades para que su estadia sea maravillosa. Disfrute de nuestras increibles instalaciones, con diseño de vanguardia.',
    'Alojate en el corazon de esta maravillosa ciudad','Ac. 26 #69D-91, Bogotá, Cundinamarca','4.661880227497501', '-74.11005980834086', '1', '4'),
    ('Encanto hotel','Los mejores servicios para nuestros huespedes. Cercano a las principales atracciones de la ciudad',
    'Alojarse en un hotel de ensueño nunca fue tan facil','Cra. 19a #102 09, Bogotá','4.6879793633543905', '-74.05275412442384', '1', '4'),
	('Paraiso hotel','Nos especializamos en el servicio al cliente, por eso todo nuestro personal estara a su disposicion durante su estadia, para ayudarlo en lo que precise. 
    Solo tendra que ocuparse de disfrutar al maximo',
    'Especializados en el servicio al cliente','Calle del Arsenal 24, Cra. 8b #58, Cartagena de Indias, Bolívar','10.420196673992068', '-75.54841437042441', '1', '6');
	
    INSERT INTO booking.politicas (descripcion, producto_id) 
    VALUES
    ('Normas de la casa: No se permiten fiestas. No fumar.', '1'),
    ('Seguridad e higiene: Detector de humo.', '1'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 72hs previas al inicio de su estadia.', '1'),
    ('Normas de la casa: No se permiten fiestas. No fumar.', '2'),
    ('Seguridad e higiene: Detector de humo.', '2'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 72hs previas al inicio de su estadia.', '2'),
	('Normas de la casa: No se permiten fiestas. No fumar.', '3'),
    ('Seguridad e higiene: Detector de humo.', '3'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 72hs previas al inicio de su estadia.', '3'),
    ('Normas de la casa: No se permiten fiestas. No fumar.', '4'),
    ('Seguridad e higiene: Detector de humo.', '4'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 72hs previas al inicio de su estadia.', '4'),
    ('Normas de la casa: No se permiten fiestas. No fumar.', '5'),
    ('Seguridad e higiene: Detector de humo.', '5'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 72hs previas al inicio de su estadia.', '5'),
    ('Normas de la casa: No se permiten fiestas. No fumar.', '6'),
    ('Seguridad e higiene: Detector de humo.', '6'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 72hs previas al inicio de su estadia.', '6');
    
	INSERT INTO booking.imagenes (titulo, url_imagen, producto_id) 
    VALUES
	('foto', '/imagenes/hoteles/1/1_1.jpg', '1'),
    ('foto', '/imagenes/hoteles/1/1_2.jpg', '1'),
    ('foto', '/imagenes/hoteles/1/1_3.jpg', '1'),
    ('foto', '/imagenes/hoteles/1/1_4.jpg', '1'),
    ('foto', '/imagenes/hoteles/1/1_5.jpg', '1'),
	('foto', '/imagenes/hoteles/2/2_1.jpg', '2'),
    ('foto', '/imagenes/hoteles/2/2_2.jpg', '2'),
    ('foto', '/imagenes/hoteles/2/2_3.jpg', '2'),
    ('foto', '/imagenes/hoteles/2/2_4.jpg', '2'),
    ('foto', '/imagenes/hoteles/2/2_5.jpg', '2'),
	('foto', '/imagenes/hoteles/3/3_1.jpg', '3'),
    ('foto', '/imagenes/hoteles/3/3_2.jpg', '3'),
    ('foto', '/imagenes/hoteles/3/3_3.jpg', '3'),
    ('foto', '/imagenes/hoteles/3/3_4.jpg', '3'),
    ('foto', '/imagenes/hoteles/3/3_5.jpg', '3'),
	('foto', '/imagenes/hoteles/4/4_1.jpg', '4'),
    ('foto', '/imagenes/hoteles/4/4_2.jpg', '4'),
    ('foto', '/imagenes/hoteles/4/4_3.jpg', '4'),
    ('foto', '/imagenes/hoteles/4/4_4.jpg', '4'),
    ('foto', '/imagenes/hoteles/4/4_5.jpg', '4'),    
	('foto', '/imagenes/hoteles/5/5_1.jpg', '5'),
    ('foto', '/imagenes/hoteles/5/5_2.jpg', '5'),
    ('foto', '/imagenes/hoteles/5/5_3.jpg', '5'),
    ('foto', '/imagenes/hoteles/5/5_4.jpg', '5'),
    ('foto', '/imagenes/hoteles/5/5_5.jpg', '5'),
	('foto', '/imagenes/hoteles/6/6_1.jpg', '6'),
    ('foto', '/imagenes/hoteles/6/6_2.jpg', '6'),
    ('foto', '/imagenes/hoteles/6/6_3.jpg', '6'),
    ('foto', '/imagenes/hoteles/6/6_4.jpg', '6'),
    ('foto', '/imagenes/hoteles/6/6_5.jpg', '6');
    
    
  /*  -- opciones de iconos.
    
	-- ('Wifi', 'faWifi', '1'),
--     ('TV', 'faTv', '1'),
--     ('Estacionamiento gratuito', 'faSquareParking', '1'),
--     ('Pileta', 'faPersonSwimming', '1'),
--     ('Aire acondicionado', 'faSnowflake', '1'),
-- 		('Admite mascotas', 'faPaw', '1'),
--     ('Productos de aseo', 'faBath', '1'),
--     ('Bar', 'faMartiniGlass', '1'),
--     ('Desayuno', 'faMugSaucer', '1'),
--     ('Adaptado movilidad reducida', 'faWheelchair', '1'),
--     ('Traslado aeropuerto', 'faVanShuttle', '1');  */
    
    
	INSERT INTO booking.caracteristicas (descripcion, icono, producto_id) 
    VALUES
    ('Wifi', 'faWifi', '1'),
    ('TV', 'faTv', '1'),
    ('Pileta', 'faPersonSwimming', '1'),
    ('Aire acondicionado', 'faSnowflake', '1'),
    ('Productos de aseo', 'faBath', '1'),
    ('Bar', 'faMartiniGlass', '1'),
    ('Desayuno', 'faMugSaucer', '1'),
    ('Adaptado movilidad reducida', 'faWheelchair', '1'),
	('Wifi', 'faWifi', '2'),
    ('TV', 'faTv', '2'),
    ('Pileta', 'faPersonSwimming', '2'),
    ('Aire acondicionado', 'faSnowflake', '2'),
    ('Productos de aseo', 'faBath', '2'),
    ('Bar', 'faMartiniGlass', '2'),
    ('Desayuno', 'faMugSaucer', '2'),
    ('Adaptado movilidad reducida', 'faWheelchair', '2'),
	('Wifi', 'faWifi', '3'),
    ('TV', 'faTv', '3'),
    ('Aire acondicionado', 'faSnowflake', '3'),
    ('Bar', 'faMartiniGlass', '3'),
    ('Desayuno', 'faMugSaucer', '3'),
    ('Adaptado movilidad reducida', 'faWheelchair', '3'),
	('Wifi', 'faWifi', '4'),
    ('TV', 'faTv', '4'),
    ('Aire acondicionado', 'faSnowflake', '4'),
    ('Productos de aseo', 'faBath', '4'),
    ('Bar', 'faMartiniGlass', '4'),
    ('Desayuno', 'faMugSaucer', '4'),
	('Wifi', 'faWifi', '5'),
    ('TV', 'faTv', '5'),
    ('Pileta', 'faPersonSwimming', '5'),
    ('Aire acondicionado', 'faSnowflake', '5'),
    ('Bar', 'faMartiniGlass', '5'),
    ('Desayuno', 'faMugSaucer', '5'),
	('Wifi', 'faWifi', '6'),
    ('TV', 'faTv', '6'),
    ('Pileta', 'faPersonSwimming', '6'),
    ('Aire acondicionado', 'faSnowflake', '6'),
    ('Productos de aseo', 'faBath', '6'),
    ('Bar', 'faMartiniGlass', '6'),
    ('Desayuno', 'faMugSaucer', '6'),
    ('Adaptado movilidad reducida', 'faWheelchair', '6'),
    ('Traslado aeropuerto', 'faVanShuttle', '6');
    
  /* --  ________________________________________________________________________ */
   
/*-- HOSTELS (productos 7 al 10) */
   
	INSERT INTO booking.productos (nombre, descripcion, titulo,direccion,latitud,longitud, categoria_id, ciudad_id)
    VALUES
    ('Hostel Arcoiris','Cada dia es una aventura, pero tambien es importante tener un lugar calido al cual regresar. En nuestro hostel encontraras lo necesario para tus necesidades.',
    'Alojate en un hostel sin igual.','Av. Exequiel Bustillo 4201, R8400 San Carlos de Bariloche, Río Negro, Argentina','-41.12555663284974','-71.35685136180092', '2', '2'),
    ('Hostel Aventura','Todo lo necesario para que al terminar cada dia tengas a donde descansar comodamente.',
    'Estadias al alcance de todos','Cl. 14 #1#60, Cartagena de Indias, Provincia de Cartagena, Bolívar','10.412926886366527', '-75.55092372760313', '2', '3'),
    ('En familia hostel','Disfruta del dia, y de noche despreocupate por donde ir a descansar. En nuestras instalaciones tendras todo lo que precisas.',
    'Estadias para aventureros','Carrera 48 numero 11 b sur 24 Carrera 48 #11 bsur 24, Medellín, Antioquia','6.195175438979446','-75.57886047080645', '2', '5'),
    ('Hostel Calidez','Amplias instalaciones y espacios seguros. Disfruta de los paisajes y descansa sin preocupaciones en nuestro precioso hostel.',
    'Hostel con todo lo que precisas','Cra. 35 #2Sur-120, Medellín, El Poblado, Medellín, Antioquia','6.198398424271781','-75.56760141750698', '2', '5');

    INSERT INTO booking.politicas (descripcion, producto_id) 
    VALUES
    ('Normas del hostel: No fumar. Check in: 16:00hs - Check out: 10:00hs', '7'),
    ('Seguridad e higiene: Detector de humo.', '7'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 48hs previas al inicio de su estadia.', '7'),
    ('Normas del hostel: No fumar. Check in: 16:00hs - Check out: 10:00hs', '8'),
    ('Seguridad e higiene: Detector de humo.', '8'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 48hs previas al inicio de su estadia.', '8'),
	('Normas del hostel: No fumar. Check in: 16:00hs - Check out: 10:00hs', '9'),
    ('Seguridad e higiene: Detector de humo.', '9'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 48hs previas al inicio de su estadia.', '9'),
    ('Normas del hostel: No fumar. Check in: 16:00hs - Check out: 10:00hs', '10'),
    ('Seguridad e higiene: Detector de humo.', '10'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 48hs previas al inicio de su estadia.', '10');
    
   	INSERT INTO booking.imagenes (titulo, url_imagen, producto_id) 
    VALUES
	('foto', '/imagenes/hostels/1/1_1.jpg', '7'),
	('foto', '/imagenes/hostels/1/1_2.jpg', '7'),
	('foto', '/imagenes/hostels/1/1_3.jpg', '7'),
	('foto', '/imagenes/hostels/1/1_4.jpg', '7'),
	('foto', '/imagenes/hostels/1/1_5.jpg', '7'),
	('foto', '/imagenes/hostels/2/2_1.jpg', '8'),
	('foto', '/imagenes/hostels/2/2_2.jpg', '8'),
	('foto', '/imagenes/hostels/2/2_3.jpg', '8'),
	('foto', '/imagenes/hostels/2/2_4.jpg', '8'),
	('foto', '/imagenes/hostels/2/2_5.jpg', '8'),
	('foto', '/imagenes/hostels/3/3_1.jpg', '9'),
	('foto', '/imagenes/hostels/3/3_2.jpg', '9'),
	('foto', '/imagenes/hostels/3/3_3.jpg', '9'),
	('foto', '/imagenes/hostels/3/3_4.jpg', '9'),
	('foto', '/imagenes/hostels/3/3_5.jpg', '9'),
	('foto', '/imagenes/hostels/4/4_1.jpg', '10'),
	('foto', '/imagenes/hostels/4/4_2.jpg', '10'),
	('foto', '/imagenes/hostels/4/4_3.jpg', '10'),
	('foto', '/imagenes/hostels/4/4_4.jpg', '10'),
	('foto', '/imagenes/hostels/4/4_5.jpg', '10');
    
    INSERT INTO booking.caracteristicas (descripcion, icono, producto_id) 
    VALUES
    ('Wifi', 'faWifi', '7'),
    ('Desayuno', 'faMugSaucer', '7'),
	('Wifi', 'faWifi', '8'),
    ('TV', 'faTv', '8'),
    ('Bar', 'faMartiniGlass', '8'),
	('Wifi', 'faWifi', '9'),
    ('TV', 'faTv', '9'),
    ('Aire acondicionado', 'faSnowflake', '9'),
    ('Desayuno', 'faMugSaucer', '9'),
	('Wifi', 'faWifi', '10'),
	('Estacionamiento gratuito', 'faSquareParking', '10'),
    ('Desayuno', 'faMugSaucer', '10');

  /* --  ________________________________________________________________________ */
   
/*-- DEPARTAMENTOS (productos 11 al 16)*/

    INSERT INTO booking.productos (nombre, descripcion, titulo,direccion,latitud,longitud, categoria_id, ciudad_id)
    VALUES
    ('Solar Apartments','Departamento completamente equipado. Cocina con elementos basicos. Sabanas y toallones a disposicion.',
    'Un departamento con todo lo que precisas','Av. Francisco Beiró 4591, C1419 CABA, Argentina','-34.60841677903311','-58.51479460774368', '3', '1'),
    ('La abadia departamento','Disfruta de una increible estadia en un departamento con todas las comodidades necesarias para hacer de tu viaje, uno maravilloso.',
    'Estadias que marcan la diferencia','Los Pintores 10, San Carlos de Bariloche, Río Negro, Argentina','-41.12308373087906', '-71.36609453621075', '3', '2'),
    ('Hogar dulce hogar','Un departamento con todo lo necesario para que no tengas que preocuparte de nada que no sea mas que disfrutar.',
    'Disfruta sin limite','Exequiel Bustillo 5AV. 6356, San Carlos de Bariloche, Río Negro, Argentina','-41.12030667680886', '-71.38020457683079', '3', '2'),
    ('Aroma a hogar apartments','Comodidad, elementos de hiegene y de cocina; todo lo necesario para que no te preocupes de nada.',
    'Alojate sin preocuparte por nada','Cra. 45a #129-30, Suba, Bogotá','4.715921022697878', '-74.05339883773179', '3', '4'),
    ('New sun apartments','Cocina completa, camas comodas, espacios amplios. Sentite como un tu casa y no te preocupes de nada. ',
    'Alojarse con comodidad nunca fue tan facil','Carrera 25 #10-51, Transversal Superior, Medellín, Antioquia','6.208662043606', '-75.55509221569947', '3', '5'),
	('Azul, departamento de estilo','Ubicacion estrategica y todo lo que precisas para no preocuparte de nada en tu estadia. Elementos de cocina e higiene, ademas de amplios espacios.',
    'Sentite como en tu hogar','Cl. 10 #161, Cartagena de Indias, Provincia de Cartagena, Bolívar','10.407171825895803', '-75.55229376768405', '3', '6');

    INSERT INTO booking.politicas (descripcion, producto_id) 
    VALUES
    ('Normas de la casa: No se permiten fiestas. No se pueden hacer ruidos molestos luego de las 21hs.', '11'),
    ('Seguridad e higiene: Detector de humo. Deposito de seguridad.', '11'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 72hs previas al inicio de su estadia.', '11'),
    ('Normas de la casa: No se permiten fiestas. No se pueden hacer ruidos molestos luego de las 21hs', '12'),
    ('Seguridad e higiene: Detector de humo. Deposito de seguridad.', '12'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 72hs previas al inicio de su estadia.', '12'),
	('Normas de la casa: No se permiten fiestas. No se pueden hacer ruidos molestos luego de las 21hs', '13'),
    ('Seguridad e higiene: Detector de humo. Deposito de seguridad.', '13'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 72hs previas al inicio de su estadia.', '13'),
    ('Normas de la casa: No se permiten fiestas. No se pueden hacer ruidos molestos luego de las 21hs', '14'),
    ('Seguridad e higiene: Detector de humo. Deposito de seguridad.', '14'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 72hs previas al inicio de su estadia.', '14'),
    ('Normas de la casa: No se permiten fiestas. No se pueden hacer ruidos molestos luego de las 21hs', '15'),
    ('Seguridad e higiene: Detector de humo. Deposito de seguridad.', '15'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 72hs previas al inicio de su estadia.', '15'),
    ('Normas de la casa: No se permiten fiestas. No se pueden hacer ruidos molestos luego de las 21hs', '16'),
    ('Seguridad e higiene: Detector de humo. Deposito de seguridad.', '16'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 72hs previas al inicio de su estadia.', '16');

   	INSERT INTO booking.imagenes (titulo, url_imagen, producto_id) 
    VALUES
	('foto', '/imagenes/departamentos/1/1_1.jpg', '11'),
    ('foto', '/imagenes/departamentos/1/1_2.jpg', '11'),
    ('foto', '/imagenes/departamentos/1/1_3.jpg', '11'),
    ('foto', '/imagenes/departamentos/1/1_4.jpg', '11'),
    ('foto', '/imagenes/departamentos/1/1_5.jpg', '11'),
	('foto', '/imagenes/departamentos/2/2_1.jpg', '12'),
    ('foto', '/imagenes/departamentos/2/2_2.jpg', '12'),
    ('foto', '/imagenes/departamentos/2/2_3.jpg', '12'),
    ('foto', '/imagenes/departamentos/2/2_4.jpg', '12'),
    ('foto', '/imagenes/departamentos/2/2_5.jpg', '12'),
	('foto', '/imagenes/departamentos/3/3_1.jpg', '13'),
    ('foto', '/imagenes/departamentos/3/3_2.jpg', '13'),
    ('foto', '/imagenes/departamentos/3/3_3.jpg', '13'),
    ('foto', '/imagenes/departamentos/3/3_4.jpg', '13'),
    ('foto', '/imagenes/departamentos/3/3_5.jpg', '13'),
	('foto', '/imagenes/departamentos/4/4_1.jpg', '14'),
    ('foto', '/imagenes/departamentos/4/4_2.jpg', '14'),
    ('foto', '/imagenes/departamentos/4/4_3.jpg', '14'),
    ('foto', '/imagenes/departamentos/4/4_4.jpg', '14'),
    ('foto', '/imagenes/departamentos/4/4_5.jpg', '14'),
	('foto', '/imagenes/departamentos/5/5_1.jpg', '15'),
    ('foto', '/imagenes/departamentos/5/5_2.jpg', '15'),
    ('foto', '/imagenes/departamentos/5/5_3.jpg', '15'),
    ('foto', '/imagenes/departamentos/5/5_4.jpg', '15'),
    ('foto', '/imagenes/departamentos/5/5_5.jpg', '15'),
	('foto', '/imagenes/departamentos/6/6_1.jpg', '16'),
    ('foto', '/imagenes/departamentos/6/6_2.jpg', '16'),
    ('foto', '/imagenes/departamentos/6/6_3.jpg', '16'),
    ('foto', '/imagenes/departamentos/6/6_4.jpg', '16'),
    ('foto', '/imagenes/departamentos/6/6_5.jpg', '16');
    
	INSERT INTO booking.caracteristicas (descripcion, icono, producto_id) 
    VALUES
    ('Wifi', 'faWifi', '11'),
    ('TV', 'faTv', '11'),
    ('Pileta', 'faPersonSwimming', '11'),
    ('Aire acondicionado', 'faSnowflake', '11'),
    ('Productos de aseo', 'faBath', '11'),
    ('Adaptado movilidad reducida', 'faWheelchair', '11'),
    ('Admite mascotas', 'faPaw', '11'),
	('Wifi', 'faWifi', '12'),
    ('TV', 'faTv', '12'),
    ('Aire acondicionado', 'faSnowflake', '12'),
    ('Productos de aseo', 'faBath', '12'),
    ('Admite mascotas', 'faPaw', '12'),
	('Wifi', 'faWifi', '13'),
    ('TV', 'faTv', '13'),
    ('Aire acondicionado', 'faSnowflake', '13'),
    ('Adaptado movilidad reducida', 'faWheelchair', '13'),
    ('Admite mascotas', 'faPaw', '13'),
	('Wifi', 'faWifi', '14'),
    ('TV', 'faTv', '14'),
    ('Aire acondicionado', 'faSnowflake', '14'),
    ('Productos de aseo', 'faBath', '14'),
    ('Admite mascotas', 'faPaw', '14'),
	('Wifi', 'faWifi', '15'),
    ('TV', 'faTv', '15'),
    ('Aire acondicionado', 'faSnowflake', '15'),
    ('Admite mascotas', 'faPaw', '15'),
	('Wifi', 'faWifi', '16'),
    ('TV', 'faTv', '16'),
    ('Aire acondicionado', 'faSnowflake', '16'),
    ('Productos de aseo', 'faBath', '16'),
    ('Admite mascotas', 'faPaw', '16'),
    ('Adaptado movilidad reducida', 'faWheelchair', '16');
    
/*	--  ________________________________________________________________________ */
   
/*-- BED AND BREAKFAST (productos 17 al 22)*/

    INSERT INTO booking.productos (nombre, descripcion, titulo,direccion,latitud,longitud, categoria_id, ciudad_id)
    VALUES
    ('BB, bed-breakfast','Comodidad y deliciosos desayunos. Nada como relajarse y disfrutar',
    'Alojamiento y disfrute','BOO, Gral. José de San Martín 450, B1638 Vicente López, Provincia de Buenos Aires, Argentina','-34.519124803125834', '-58.473098914305574', '4', '1'),
    ('Campos de olivos bedBreakfast','Desayunos abundantes y variados. Comodidad como en ningun otro lugar. Jardines amplios para relajarse.',
    'Estadias que marcan la diferencia','Av. Exequiel Bustillo 6000, San Carlos de Bariloche, Río Negro, Argentina','-41.12016212682168', '-71.37592831853486', '4', '2'),
    ('bed-breakfast Maravilla','Alojamiento de estilo, con todas las comodidades y desayunos deliciosos para arrancar su dia.',
    'Disfruta del momento en nuestro hospedaje','Azopardo, J. G. Artigas Esquina, X5152 Villa Carlos Paz, Córdoba, Argentina','-31.408453193381103', '-64.48576841455939', '4', '3'),
    ('Sol y luna bed-breakfast','Nada tan rico como un buen desayuno y con la posibilidad de poder pedirlo a la habitacion. Vistas al jardin desde las habitaciones.',
    'Alojate sin preocupaciones','Cra. 21a #159a-28, Bogotá','4.740431488287416', '-74.04638632074798', '4', '4'),
    ('Aromas del bosque, bed-breakfast','Amplias comodidades. Comer variado y rico en el desayuno nos ayuda a arrancar el dia, por lo que nuestro desayuno te va a encantar.',
    'Alojarse con comodidad y disfrutar','Calle 16, Variante #28-51, Las Palmas, Medellín, Antioquia','6.213628512845832', '-75.55822266795376', '4', '5'),
	('Duendes, bed-breakfast','Descansa como nunca en nuestro hospedaje y disfruta de unos deliciosos desayunos gourmet.',
    'Alojamiento de ensueño','Cra. 18 ##24-125, Cartagena de Indias, Provincia de Cartagena, Bolívar','10.41476253921539', '-75.54151583855649', '4', '6');

    INSERT INTO booking.politicas (descripcion, producto_id) 
    VALUES
    ('Normas del lugar: Check in: 16:00hs - Check out: 10:00hs', '17'),
    ('Seguridad e higiene: Detector de humo. Deposito de seguridad.', '17'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 1 semana previa a la fecha de estadia', '17'),
    ('Normas ddel lugar: Check in: 16:00hs - Check out: 10:00hs', '18'),
    ('Seguridad e higiene: Detector de humo. Deposito de seguridad.', '18'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 1 semana previa a la fecha de estadia', '18'),
	('Normas del lugar: Check in: 16:00hs - Check out: 10:00hs', '19'),
    ('Seguridad e higiene: Detector de humo. Deposito de seguridad.', '19'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 1 semana previa a la fecha de estadia', '19'),
    ('Normas del lugar: Check in: 16:00hs - Check out: 10:00hs', '20'),
    ('Seguridad e higiene: Detector de humo. Deposito de seguridad.', '20'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 1 semana previa a la fecha de estadia', '20'),
    ('Normas del lugar: Check in: 16:00hs - Check out: 10:00hs', '21'),
    ('Seguridad e higiene: Detector de humo. Deposito de seguridad.', '21'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 1 semana previa a la fecha de estadia', '21'),
    ('Normas del lugar: Check in: 16:00hs - Check out: 10:00hs', '22'),
    ('Seguridad e higiene: Detector de humo. Deposito de seguridad.', '22'),
    ('Politicas de cancelacion: se puede cancelar con un aviso minimo de 1 semana previa a la fecha de estadia', '22');

	INSERT INTO booking.imagenes (titulo, url_imagen, producto_id) 
    VALUES
	('foto', '/imagenes/bed_breakfast/1/1_1.jpg', '17'),
    ('foto', '/imagenes/bed_breakfast/1/1_2.jpg', '17'),
    ('foto', '/imagenes/bed_breakfast/1/1_3.jpg', '17'),
    ('foto', '/imagenes/bed_breakfast/1/1_4.jpg', '17'),
    ('foto', '/imagenes/bed_breakfast/1/1_5.jpg', '17'),
	('foto', '/imagenes/bed_breakfast/2/2_1.jpg', '18'),
    ('foto', '/imagenes/bed_breakfast/2/2_2.jpg', '18'),
    ('foto', '/imagenes/bed_breakfast/2/2_3.jpg', '18'),
    ('foto', '/imagenes/bed_breakfast/2/2_4.jpg', '18'),
    ('foto', '/imagenes/bed_breakfast/2/2_5.jpg', '18'),
	('foto', '/imagenes/bed_breakfast/3/3_1.jpg', '19'),
    ('foto', '/imagenes/bed_breakfast/3/3_2.jpg', '19'),
    ('foto', '/imagenes/bed_breakfast/3/3_3.jpg', '19'),
    ('foto', '/imagenes/bed_breakfast/3/3_4.jpg', '19'),
    ('foto', '/imagenes/bed_breakfast/3/3_5.jpg', '19'),
	('foto', '/imagenes/bed_breakfast/4/4_1.jpg', '20'),
    ('foto', '/imagenes/bed_breakfast/4/4_2.jpg', '20'),
    ('foto', '/imagenes/bed_breakfast/4/4_3.jpg', '20'),
    ('foto', '/imagenes/bed_breakfast/4/4_4.jpg', '20'),
    ('foto', '/imagenes/bed_breakfast/4/4_5.jpg', '20'),
	('foto', '/imagenes/bed_breakfast/5/5_1.jpg', '21'),
    ('foto', '/imagenes/bed_breakfast/5/5_2.jpg', '21'),
    ('foto', '/imagenes/bed_breakfast/5/5_3.jpg', '21'),
    ('foto', '/imagenes/bed_breakfast/5/5_4.jpg', '21'),
    ('foto', '/imagenes/bed_breakfast/5/5_5.jpg', '21'),
	('foto', '/imagenes/bed_breakfast/6/6_1.jpg', '22'),
    ('foto', '/imagenes/bed_breakfast/6/6_2.jpg', '22'),
    ('foto', '/imagenes/bed_breakfast/6/6_3.jpg', '22'),
    ('foto', '/imagenes/bed_breakfast/6/6_4.jpg', '22'),
    ('foto', '/imagenes/bed_breakfast/6/6_5.jpg', '22');
    
    INSERT INTO booking.caracteristicas (descripcion, icono, producto_id) 
    VALUES
    ('Wifi', 'faWifi', '17'),
    ('TV', 'faTv', '17'),
    ('Pileta', 'faPersonSwimming', '17'),
    ('Aire acondicionado', 'faSnowflake', '17'),
    ('Admite mascotas', 'fa-solid fa-paw', '17'),
	('Wifi', 'faWifi', '18'),
    ('TV', 'faTv', '18'),
    ('Aire acondicionado', 'faSnowflake', '18'),
    ('Productos de aseo', 'faBath', '18'),
	('Wifi', 'faWifi', '19'),
    ('TV', 'faTv', '19'),
    ('Aire acondicionado', 'faSnowflake', '19'),
    ('Adaptado movilidad reducida', 'faWheelchair', '19'),
	('Wifi', 'faWifi', '20'),
    ('TV', 'faTv', '20'),
    ('Aire acondicionado', 'faSnowflake', '20'),
    ('Productos de aseo', 'faBath', '20'),
    ('Admite mascotas', 'fa-solid fa-paw', '20'),
	('Wifi', 'faWifi', '21'),
    ('TV', 'faTv', '21'),
    ('Aire acondicionado', 'faSnowflake', '21'),
	('Wifi', 'faWifi', '22'),
    ('TV', 'faTv', '22'),
    ('Aire acondicionado', 'faSnowflake', '22'),
    ('Productos de aseo', 'faBath', '22'),
    ('Adaptado movilidad reducida', 'faWheelchair', '22');
    
    INSERT INTO booking.roles (nombre) 
    VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN');
    
    INSERT INTO booking.usuarios (nombre, apellido, password, email, ciudad_id) 
    VALUES
    ('Leslie', 'Lowen', '$2a$10$IGZIRQxUMREScmK0XRDQh.APlyd1fU3PGWVobt.Z/hvA3JskMIxsa', 'LeslieAdmin@gmail.com', 1),
    ('Andres', 'G', '$2a$10$2gBVaxJQIVSZ9Bp6qQ69/.8vmYqJHqKmsAya8qY6tk5pcb0GG8Mzu', 'AndresUser@gmail.com', 4);

    INSERT INTO booking.usuario_roles (id_usuario, id_rol) 
    VALUES
    ('1', '2'),
    ('2', '1');

    /*-- alter table LA_TABLA AUTO_INCREMENT=1; para reiniciar los id despues de borrar los elementos
    -- delete from LA_TABLA para borrar todo el contenido de una tabla */


