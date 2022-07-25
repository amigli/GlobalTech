drop database if exists progettoTSW;

CREATE DATABASE progettoTSW;

use progettoTSW;

CREATE TABLE Prodotto(
    id integer auto_increment primary key,
    nome varchar(50) not null,
    marca varchar(50) not null,
    colore varchar(50) not null,
    prezzo_listino float not null,
    descrizione text not null,
    batteria boolean not null,
    ram_tipo varchar(20),
    ram_quantita varchar(20),
    sistema_operativo varchar(20),
    cpu_nome varchar(20),
    disponibilita int not null -- disponibilita=-1, il prodotto non è più in vendita
);

CREATE TABLE Foto (
  numero int not null,
  id_prodotto int not null references  Prodotto(id),
  estensione  varchar(4) not null,

  primary key(numero, id_prodotto)
);

CREATE TABLE Categoria(
  id integer not null auto_increment primary key,
  nome varchar(50) not null unique,
  descrizione text not null
);

CREATE TABLE Appartenere(
    id_categoria int not null references Categoria(id) on delete cascade on update cascade ,
    id_prodotto int not null references Prodotto(id) ,

    primary key (id_prodotto, id_categoria)
);

CREATE TABLE Utente (
    id int auto_increment primary key,
    email varchar (30) not null unique,
    passwordhash varchar (100) not null,  -- minimo 8 e massimo 32
    data_nascita date not null,
    nome char (30) not null,
    cognome char (30) not null,
    admin boolean not null,

    via_indirizzo char (30),
    civico int,
    citta char (30),
    cap char(5),
    num_acquisti int, -- Bisogna inserire la query che calcola il numero di ordini effettuati
    numero_telefono char(10),
    numero_cc char(16),
    cvv_cc char (3),
    data_scadenza_cc date
);


CREATE TABLE Ordine (
    id int auto_increment primary key not null,
    prezzo_totale float not null,
    tracking char (20),
    data_ordine date not null,
    spese_spedizione float not null,
    cc_pagamento varchar(30) not null,
    indirizzo_spedizione varchar(100) not null,
    stato int not null,
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
    nome varchar(45),
    percentuale double not null,
    data_inizio date not null,
    data_fine date not null
);

CREATE TABLE Applicare(
    id_prodotto int references Prodotto(id),
    id_offerta int references Offerta(id),
    primary key(id_prodotto, id_offerta)
);


CREATE TABLE Carrello
(
    id_utente   int not null references Utente (id),
    id_prodotto int not null references prodotto (id),
    quantita    int not null,

    primary key (id_utente, id_prodotto)
);








