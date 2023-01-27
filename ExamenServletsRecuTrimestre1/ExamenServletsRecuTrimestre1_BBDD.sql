create database almacenes;

create table IF NOT EXISTS ALMACEN_OLD(
	id bigint NOT NULL AUTO_INCREMENT,
    	nombre varchar(25),
    	PRIMARY KEY ( id )
);

create table IF NOT EXISTS ALMACEN_NEW(
	id bigint NOT NULL AUTO_INCREMENT,
    	nombre varchar(25),
    	PRIMARY KEY ( id )
);

create table IF NOT EXISTS LIBRO(
	id bigint not null auto_increment,
isbn varchar(25),
fechaEdicion date,
almacen_old bigint,
almacen_new bigint,
    	PRIMARY KEY ( id ),
FOREIGN KEY (almacen_old ) REFERENCES `ALMACEN_OLD`(id),
FOREIGN KEY (almacen_new ) REFERENCES `ALMACEN_NEW`(id)
);
