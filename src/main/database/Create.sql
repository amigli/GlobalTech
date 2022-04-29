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



