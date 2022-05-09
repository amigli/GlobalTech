drop database if exists progettoTSW;

CREATE DATABASE progettoTSW;

use progettoTSW;

CREATE TABLE Prodotto(
     id integer auto_increment primary key,
     nome char(50) not null,
     marca varchar(50) not null,
     colore varchar(50) not null,
     prezzoListino float not null,
     descrizione char(50),
     batteriaTipo varchar(20),
     batteriaCapacita float,
     ramTipo varchar(20),
     ramQuantita float(20),
     so varchar(20),
     fotocameraPosteriore float,
     fotocameraAnteriore float,
     cpuNome varchar(20),
     cpuHertz float,
     gpu varchar(20),
     schermo varchar(30),
     autonomia int
);

CREATE TABLE Foto (
  numero int not null,
  id_prodotto int not null references  Prodotto(id),
  estensione  varchar(4) not null,

  primary key(numero, id_prodotto)
);

CREATE TABLE Categoria(
  id integer auto_increment primary key,
  nome char(50) not null,
  descrizione char(50)
);

CREATE TABLE Appartenere(
    id_categoria int references Categoria(id),
    id_prodotto int references Prodotto(id),

    primary key (id_prodotto, id_categoria)
);


CREATE TABLE Utente (
    id int auto_increment primary key,
    nomeUtente char (30) not null,
    password varchar (20) not null,
    dataNascita date not null,
    nome char (30) not null,
    cognome char (30) not null,
    admin boolean not null
);

CREATE TABLE Carrello (
    idUtente int primary key references Utente (id) ,
    totale double not null
);

CREATE TABLE Ordine (
    id int auto_increment primary key,
    prezzoTotale float not null,
    tracking char (20),
    dataO date not null,
    speseSpedizione float not null,
    modalitaPagamento char (30) not null,
    stato char (30) not null,
    idUtente int references Utente (id)
);

CREATE TABLE Contenere (
    idProdotto int references Prodotto(id),
    idOrdine int references Ordine(ID),
    quantita int not null,
    prezzoAcquisto double not null,
    primary key (idProdotto, idOrdine)
);

CREATE TABLE Offerta (
    id int auto_increment primary key,
    percentuale double not null,
    dataInizio date not null,
    dataFine date not null
);

CREATE TABLE Applicare(
    idProdotto int references Prodotto(id),
    idOfferta int references Offerta(id),
    primary key(idProdotto, idOfferta)
);


CREATE TABLE Comporre(
    idUtente int not null references  Utente(id) ,
    idProdotto int not null references prodotto(id),
    quantita int not null,
    prezzo double not null,

    primary key(idUtente, idProdotto)
);

CREATE TABLE Magazzino (
    id int not null primary key,
    citta char (30)
);

CREATE TABLE Riserva(
    idProdotto int not null references Prodotto(id),
    idMagazzino int not null references Magazzino(id),
    quantita int not null,

    primary key (idProdotto, idMagazzino)
);

CREATE TABLE Cliente(
    idUtente int not null primary key references Utente(id),
    viaC char (30) not null,
    civico int not null,
    citta char (30) not null,
    cap int not null,
    numAcquisti int not null,
    numeroTelefono char(10) not null
);
