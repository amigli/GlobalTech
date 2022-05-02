drop database if exists progettoTSW;

CREATE DATABASE progettoTSW;

use progettoTSW;

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

    primary key(numero, id_prodotto)
);

CREATE TABLE Telefonia (
   id_prodotto int not null primary key  references  Prodotto(id) ,
   batteria_tipo varchar(20) not null,
   batteria_capacita float not null,
   ram_tipo varchar(20) not null,
   ram_quantita float(20) not null,
   so varchar(20) not null,
   fotocamera_posteriore float,
   fotocamera_anteriore float,
   cpu_nome varchar(20),
   cpu_hertz float not null
);

CREATE TABLE Informatica(
    id_prodotto int not null references  Prodotto(id),
    tipologia varchar(20) not null,
    so varchar(20),
    ram_tipo varchar(20) not null,
    ram_quantita float(20) not null,
    gpu varchar(20) not null,
    cpu_nome varchar(20),
    cpu_hertz float not null,
    batteria boolean not null,
    schermo varchar(30) not null,

    primary key(id_prodotto)
);

CREATE TABLE Accessorio(
    id_prodotto int not null primary key references Prodotto(id) ,
    tipologia varchar(30) not null,
    autonomia int,
    connessione varchar(20) not null
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
