-- Creation des sequences
CREATE SEQUENCE seq_appuser_id START 1;
CREATE SEQUENCE seq_equipe_id START 1;
CREATE SEQUENCE seq_coureur_id START 1;
CREATE SEQUENCE seq_categorie_id START 1;
CREATE SEQUENCE seq_coureurcategorie_id START 1;
CREATE SEQUENCE seq_etape_id START 1;
CREATE SEQUENCE seq_resultatetape_id START 1;
CREATE SEQUENCE seq_point_id start 1;

-- Creation de la table AppUser
CREATE TABLE AppUser (
    ID VARCHAR(250) PRIMARY KEY DEFAULT 'APP' || LPAD(nextval('seq_appuser_id')::text, 4, '0'),
    login VARCHAR(250),
    pswd VARCHAR(250)
);

CREATE TABLE POINT(
    ID VARCHAR(250) PRIMARY KEY DEFAULT 'PT'|| LPAD(nextval('seq_point_id')::text,4,'0'),
    ranK int UNIQUE NOT NULL,
    point int NOT NULL
);


-- Creation de la table Equipe
CREATE TABLE Equipe (
    ID VARCHAR(250) PRIMARY KEY DEFAULT 'EQ' || LPAD(nextval('seq_equipe_id')::text, 4, '0'),
    nom VARCHAR(250),
    login VARCHAR(250),
    pswd VARCHAR(250)
);


-- Creation de la table Coureur
CREATE TABLE Coureur (
    ID VARCHAR(250) PRIMARY KEY DEFAULT 'CR' || LPAD(nextval('seq_coureur_id')::text, 4, '0'),
    nom VARCHAR(250) NOT NULL,
    numerodossard INT NOT NULL,
    genre VARCHAR(250) ,
    dtn DATE,
    Equipe VARCHAR(250) REFERENCES Equipe(ID),
    constraint uniquecoureur UNIQUE(Equipe,numerodossard)
);


-- Creation de la table Categorie
CREATE TABLE Categorie (
    ID VARCHAR(250) PRIMARY KEY DEFAULT 'CT' || LPAD(nextval('seq_categorie_id')::text, 8, '0'),
    nom VARCHAR(250) UNIQUE NOT NULL
);


-- Creation de la table CoureurCategorie
CREATE TABLE CoureurCategorie (
    ID VARCHAR(250) PRIMARY KEY DEFAULT 'CRCT' || LPAD(nextval('seq_coureurcategorie_id')::text, 8, '0'),
    Categorie VARCHAR(250) REFERENCES Categorie(ID),
    Coureur VARCHAR(250) REFERENCES Coureur(ID)
);


-- Creation de la table Etape
CREATE TABLE Etape (
    ID VARCHAR(250) PRIMARY KEY DEFAULT 'ST' || LPAD(nextval('seq_etape_id')::text, 4, '0'),
    Nom VARCHAR(250),
    LongueurKM double precision,
    nbCoureur INT,
    rangEtape INT,
    depart timestamp
);



-- Creation de la table ResultatEtape
drop table ResultatEtape;
CREATE TABLE ResultatEtape (
    ID VARCHAR(250) PRIMARY KEY DEFAULT 'RES' || LPAD(nextval('seq_resultatetape_id')::text, 8, '0'),
    Etape VARCHAR(250) REFERENCES Etape(ID),
    Coureur VARCHAR(250) REFERENCES Coureur(ID),
    Arrivee timestamp
);

drop  TABLE RESULTATCSV ;
CREATE TABLE RESULTATCSV (
    etape_rang	INTEGER,
    numero_dossard INTEGER,
    nom VARCHAR(250),
    genre VARCHAR(250),
    date_naissance	DATE,
    equipe	VARCHAR(250),
    arrivee timestamp
);
-- 

CREATE TABLE REGLEcategoriedtn(
    id serial PRIMARY KEY,
    categorie VARCHAR(250),
    agemin int ,
    agemax int ,
    foreign KEY(categorie) REFERENCES categorie(id)
);

create table penalite(
    id serial primary key,
    etape VARCHAR(250),
    equipe VARCHAR(250),
    tempspenalite time,
    foreign key(etape) REFERENCES etape(id),
    foreign key(equipe) REFERENCES equipe(id)
);

