CREATE TABLE Prodotto(
    id integer auto_increment primary key,
    nome char(50) not null,
    marca varchar(50) not null,
    colore varchar(50) not null,
    prezzoListino float not null,
    descrizione text
);

CREATE TABLE Foto (
    numero int not null,
    id_prodotto int not null references  Prodotto(id),
    estensione  varchar(4) not null,

    primary key(numero, id_prodotto),
);

CREATE TABLE Telefonia (
    id_prodotto int not null references  Prodotto(id) primary key ,
    batteria_tipo varchar(20) not null,
    batteria_capacita float not null,
    ram_tipo varchar(20) not null,
    ram_quantita float(20) not null,
    so varchar(20) not null,
    fotocamera_posteriore float,
    fotocamera_anteriore float,
    cpu_nome varchar(20),
    cpu_hertz float not null,





)




