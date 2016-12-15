CREATE TABLE vinusuarios
(
  id_usuario integer NOT NULL DEFAULT nextval('vinusuarios_id_usuario_seq'::regclass),
  nombre text NOT NULL,
  "contraseña" text,
  mail text,
  telefono integer,
  CONSTRAINT vinusuarios_pkey PRIMARY KEY (id_usuario),
  CONSTRAINT vinusuarios_nombre_key UNIQUE (nombre)
);

CREATE TABLE vinpartidas
(
  id_partida integer NOT NULL DEFAULT nextval('vinpartidas_id_partida_seq'::regclass),
  id_usuario integer NOT NULL DEFAULT nextval('vinpartidas_id_usuario_seq'::regclass),
  inicio timestamp without time zone NOT NULL,
  fin timestamp without time zone NOT NULL,
  velocidad real NOT NULL,
  CONSTRAINT pk_idpartida PRIMARY KEY (id_partida),
  CONSTRAINT fk_idusuario FOREIGN KEY (id_usuario)
      REFERENCES public.vinusuarios (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);