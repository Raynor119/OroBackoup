create table Prendas(codigo INT AUTO_INCREMENT,nombre VARCHAR(255) NOT NULL,PRIMARY KEY (codigo));
CREATE TABLE Fundicion (codigo INT AUTO_INCREMENT,codigoprenda INT NOT NULL,pesoinicial FLOAT NOT NULL,pesofinal FLOAT NOT NULL,foto LONGBLOB,PRIMARY KEY (codigo));
