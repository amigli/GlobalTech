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

CREATE TABLE Utente (
    ID int auto_increment primary key,
    nome_utente char (30) not null,
    password varchar (20) not null,
    data_nascita date not null,
    nome char (30) not null,
    cognome char (30) not null,
    indirizzo char (50) not null,
    num_acquisti int not null,
    admin_yn boolean not null
);

CREATE TABLE Carrello (
    IDutente int references Utente (ID) primary key,
    totale double not null
);

CREATE TABLE Ordine (
    ID int auto_increment primary key,
    prezzo_totale float not null,
    data_o date not null,
    spese_spedizione float not null,
    modalita_pagamento char (30) not null,
    stato char (30) not null,
    IDutente int references Utente (ID)
);

CREATE TABLE Contenere (
    IDprodotto int references Prodotto(id),
    IDordine int references Ordine(ID),
    quantita int not null,
    prezzo_acquisto double not null,
    primary key (IDprodotto, IDordine)
);

CREATE TABLE Offerta (
    ID int auto_increment primary key,
    percentuale double not null,
    data_inizio date not null,
    data_fine date not null
);

CREATE TABLE Applicare(
    IDprodotto int references Prodotto(id),
    IDofferta int references Offerta(ID),
    primary key(IDprodotto, IDofferta)
);