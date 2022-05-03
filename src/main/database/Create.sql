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
    batteria_tipo varchar(20),
    batteria_capacita float,
    ram_tipo varchar(20),
    ram_quantita float(20),
    so varchar(20),
    fotocamera_posteriore float,
    fotocamera_anteriore float,
    cpu_nome varchar(20),
    cpu_hertz float,
    gpu varchar(20),
    schermo varchar(30),
    autonomia int

);

CREATE TABLE Categoria(
  id integer auto_increment primary key,
  nome char(50) not null,
  descrizione char(50)
);

CREATE TABLE Appartenere(
    id_categoria int references Categoria(id),
    id_prodotto int references Prodotto(id)
);

CREATE TABLE Foto (
    numero int not null,
    id_prodotto int not null references  Prodotto(id),
    estensione  varchar(4) not null,

    primary key(numero, id_prodotto)
);

CREATE TABLE Utente (
    id int auto_increment primary key,
    nome_utente char (30) not null,
    password varchar (20) not null,
    data_nascita date not null,
    nome char (30) not null,
    cognome char (30) not null,
    indirizzo char (50) not null,
    num_acquisti int not null,
    admin boolean not null
);

CREATE TABLE Carrello (
    id_utente int primary key references Utente (id) ,
    totale double not null
);

CREATE TABLE Ordine (
    id int auto_increment primary key,
    prezzo_totale float not null,
    data_o date not null,
    spese_spedizione float not null,
    modalita_pagamento char (30) not null,
    stato char (30) not null,
    id_utente int references Utente (id)
);

CREATE TABLE Contenere (
    id_prodotto int references Prodotto(id),
    id_ordine int references Ordine(ID),
    quantita int not null,
    prezzo_acquisto double not null,
    primary key (id_prodotto, id_ordine)
);

CREATE TABLE Offerta (
    id int auto_increment primary key,
    percentuale double not null,
    data_inizio date not null,
    data_fine date not null
);

CREATE TABLE Applicare(
    id_prodotto int references Prodotto(id),
    id_offerta int references Offerta(id),
    primary key(id_prodotto, id_offerta)
);


CREATE TABLE Comporre(
    id_utente int not null references  Utente(id) ,
    id_prodotto int not null references prodotto(id),
    quantita int not null,

    primary key(id_utente, id_prodotto)
);
