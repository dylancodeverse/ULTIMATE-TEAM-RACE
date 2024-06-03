-- Insertion de données dans la table AppUser
INSERT INTO AppUser (login, pswd) VALUES ('mirary', 'soa');

-- Insertion de données dans la table Equipe
INSERT INTO Equipe (nom, login, pswd) VALUES ('A', 'a', '7');
INSERT INTO Equipe (nom, login, pswd) VALUES ('B', 'b', '7');

-- Insertion de données dans la table Coureur
INSERT INTO Coureur (nom, numerodossard, genre, dtn, Equipe) VALUES ('Dylan', 7, 1, '2024-04-19', 'EQ0004');
INSERT INTO Coureur (nom, numerodossard, genre, dtn, Equipe) VALUES ('SOA', 7, 1, '2024-04-19', 'EQ0005');
INSERT INTO Coureur (nom, numerodossard, genre, dtn, Equipe) VALUES ('MASINA', 7, 1, '2024-04-19', 'EQ0005');

-- Insertion de données dans la table Categorie
INSERT INTO Categorie (nom) VALUES ('junior');

-- Insertion de données dans la table CoureurCategorie
INSERT INTO CoureurCategorie (Categorie, Coureur) VALUES ('CT0002', 'CR0008');

-- Insertion de données dans la table Etape
INSERT INTO Etape (Nom, LongueurKM, nbCoureur, rangEtape) VALUES ('Betsizaraina', 12, 2, 1);

-- Insertion de données dans la table ResultatEtape
INSERT INTO ResultatEtape (Etape, Coureur, Depart, Arrivee) VALUES ('ST0002', 'CR0008', '19:22:50', '26:01:01');

INSERT INTO POINT(ranK,point) values(1,10),(2,6),(3,4),(4,2),(5,1);