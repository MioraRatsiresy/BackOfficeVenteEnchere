create user venteenchere with password 'venteenchere';

create database venteenchere;

CREATE TABLE AdminLogin (
    id SERIAL NOT NULL,
    adminUser varchar(50) NOT NULL,
    pwd varchar(50) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE Categorie (
    id SERIAL NOT NULL,
    categorie varchar(50) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE Produit (
    id SERIAL NOT NULL,
    produit varchar(50),
    categorie int4 NOT NULL,
    PRIMARY KEY (id)
);
ALTER TABLE Produit ADD FOREIGN KEY(categorie) REFERENCES Categorie(id);


--à voir
CREATE TABLE EnchereAdmin (
    id SERIAL NOT NULL,
    Categorieid int4 NOT NULL,
    duree float8 NOT NULL,
    PRIMARY KEY (id)
);
ALTER TABLE EnchereAdmin ADD FOREIGN KEY (Categorieid) REFERENCES Categorie (id);

--à voir 
CREATE TABLE etat (
    id SERIAL NOT NULL,
    etat varchar(50) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE EtatCategorie (
    Categorieid int4 NOT NULL,
    etatid int4 NOT NULL
);
ALTER TABLE EtatCategorie ADD  FOREIGN KEY (Categorieid) REFERENCES Categorie (id);
ALTER TABLE EtatCategorie ADD FOREIGN KEY (etatid) REFERENCES etat (id);



CREATE TABLE Commission (
    id SERIAL NOT NULL,
    pourcentage float8 NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Client (
    id SERIAL NOT NULL,
    nom varchar(50) NOT NULL,
    prenom varchar(50) NOT NULL,
    contact varchar(50) NOT NULL,
    identifiant varchar(50) NOT NULL,
    pwd varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE CompteClient (
    id SERIAL NOT NULL,
    montant float8 NOT NULL,
    etat int4 default 0 NOT NULL, --0 en cours //3 validé // 8 refusé
    Clientid int4 NOT NULL, 
    actionTransaction int default 0 --0 debit --4 credit
);

ALTER TABLE CompteClient ADD FOREIGN KEY(Clientid) REFERENCES Client(id);

CREATE TABLE Enchere (
    id SERIAL NOT NULL,
    produit int4 NOT NULL,
    libelle varchar(255) NOT NULL,
    dateHeure timestamp NOT NULL,
    prixMin float8 NOT NULL,
    duree float8 NOT NULL, -- en heure ou minute
    etat varchar(20) NOT NULL, --0 en cours // 7 fini
    idclient int not null,
    PRIMARY KEY (id)
);

ALTER TABLE Enchere ADD FOREIGN KEY(produit) REFERENCES Produit(id);
ALTER TABLE Enchere ADD FOREIGN KEY(idclient) REFERENCES Client(id);

----VIEW
CREATE VIEW EnchereDetail as 
SELECT Enchere.*,Produit.produit as produitEnchere,Categorie.categorie
from Enchere join Produit on Produit.id=Enchere.produit join Categorie on Categorie.id=Produit.categorie;

-- ALTER TABLE EnchereAdmin ADD CONSTRAINT FKEnchereAdm945139 FOREIGN KEY (Categorieid) REFERENCES Categorie (id);
-- ALTER TABLE EtatCategorie ADD CONSTRAINT FKEtatCatego274392 FOREIGN KEY (Categorieid) REFERENCES Categorie (id);
-- ALTER TABLE EtatCategorie ADD CONSTRAINT FKEtatCatego52731 FOREIGN KEY (etatid) REFERENCES etat (id);
-- ALTER TABLE CompteClient ADD CONSTRAINT FKCompteClie643909 FOREIGN KEY (Clientid) REFERENCES Client (id);
-- ALTER TABLE Enchere ADD CONSTRAINT FKEnchere812038 FOREIGN KEY (SousCategorieid) REFERENCES SousCategorie (id);
-- ALTER TABLE PhotoEnchere ADD CONSTRAINT FKPhotoEnche511259 FOREIGN KEY (Enchereid) REFERENCES Enchere (id);
INSERT INTO AdminLogin(id, adminUser, pwd) VALUES (1, 'Miora', 'e251952a64f952d8920be4dd36f945b7');
INSERT INTO Categorie(id, categorie) VALUES (default, 'Animalerie');
INSERT INTO Categorie(id, categorie) VALUES (default, 'Art, Antiquite');
INSERT INTO Categorie(id, categorie) VALUES (default, 'Bijoux, Montres');
INSERT INTO Categorie(id, categorie) VALUES (default, 'Immobilier');
INSERT INTO Categorie(id, categorie) VALUES (default, 'Bricolage');
INSERT INTO Categorie(id, categorie) VALUES (default, 'Ceramique, Verre');
INSERT INTO Categorie(id, categorie) VALUES (default, 'Instrument de musique');
INSERT INTO Categorie(id, categorie) VALUES (default, 'Jeux video');
INSERT INTO Categorie(id, categorie) VALUES (default, 'Jouet et jeux');
INSERT INTO Categorie(id, categorie) VALUES (default, 'Bebe');
INSERT INTO Produit(id, produit, categorie) VALUES (1, 'Chat', 1);
INSERT INTO Produit(id, Produit, categorie) VALUES (2, 'Chien', 1);
INSERT INTO Produit(id, Produit, categorie) VALUES (3, 'Apiculture', 1);
INSERT INTO Produit(id, Produit, categorie) VALUES (4, 'Petits animaux, Rongeurs', 1);
INSERT INTO Produit(id, Produit, categorie) VALUES (5, 'Architecture, Materiaux', 2);
INSERT INTO Produit(id, Produit, categorie) VALUES (6, 'Art du XIXe et avant', 2);
INSERT INTO Produit(id, Produit, categorie) VALUES (7, 'Art du XXe, contemporain', 2);
INSERT INTO Produit(id, Produit, categorie) VALUES (8, 'Reproductions, copies', 2);
INSERT INTO Produit(id, Produit, categorie) VALUES (9, 'Bijoux de corps', 3);
INSERT INTO Produit(id, Produit, categorie) VALUES (10, 'Bijoux fantaisie', 3);
INSERT INTO Produit(id, Produit, categorie) VALUES (11, 'Bijoux pour homme', 3);
INSERT INTO Produit(id, Produit, categorie) VALUES (12, 'Montre', 3);
INSERT INTO Produit(id, Produit, categorie) VALUES (13, 'Location', 4);
INSERT INTO Produit(id, Produit, categorie) VALUES (14, 'Location de vacances', 4);
INSERT INTO Produit(id, Produit, categorie) VALUES (15, 'Locaux commerciaux', 4);
INSERT INTO Produit(id, Produit, categorie) VALUES (16, 'Cuisine', 5);
INSERT INTO Produit(id, Produit, categorie) VALUES (17, 'Bois d oeuvre, bois composites', 5);
INSERT INTO Produit(id, Produit, categorie) VALUES (18, 'Papier peint', 5);
INSERT INTO Produit(id, Produit, categorie) VALUES (19, 'Materiel d atelier et de bricolage', 5);
INSERT INTO Produit(id, Produit, categorie) VALUES (20, 'Ceramiques francaises', 6);
INSERT INTO Produit(id, Produit, categorie) VALUES (21, 'Ceramiques etrangeres', 6);
INSERT INTO Produit(id, Produit, categorie) VALUES (22, 'Verre, Cristal', 6);
INSERT INTO Produit(id, Produit, categorie) VALUES (23, 'Ceramiques vaisselles, deco', 6);
INSERT INTO Produit(id, Produit, categorie) VALUES (24, 'Instruments a cordes', 7);
INSERT INTO Produit(id, Produit, categorie) VALUES (25, 'Piano, clavier', 7);
INSERT INTO Produit(id, Produit, categorie) VALUES (26, 'Batteries, percussions', 7);
INSERT INTO Produit(id, Produit, categorie) VALUES (27, 'Partitions, livres de chansons, accessoires', 7);
INSERT INTO Produit(id, Produit, categorie) VALUES (28, 'Instruments a vents', 7);
INSERT INTO Produit(id, Produit, categorie) VALUES (29, 'Articles de jeux video', 8);
INSERT INTO Produit(id, Produit, categorie) VALUES (30, 'Consoles', 8);
INSERT INTO Produit(id, Produit, categorie) VALUES (31, 'Accessoire', 8);
INSERT INTO Produit(id, Produit, categorie) VALUES (32, 'Jeux de construction', 9);
INSERT INTO Produit(id, Produit, categorie) VALUES (33, 'Jeux de role', 9);
INSERT INTO Produit(id, Produit, categorie) VALUES (34, 'Jeux de societes', 9);
INSERT INTO Produit(id, Produit, categorie) VALUES (35, 'Autres', 9);
INSERT INTO Produit(id, Produit, categorie) VALUES (36, 'Toilettes, bains', 10);
INSERT INTO Produit(id, Produit, categorie) VALUES (37, 'Peluche doudous', 10);
INSERT INTO Produit(id, Produit, categorie) VALUES (38, 'Equipements pour bebe', 10);
INSERT INTO Produit(id, Produit, categorie) VALUES (39, 'Equipements de securite', 10);
INSERT INTO Produit(id, Produit, categorie) VALUES (40, 'Autres', 10);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (1, 1, 2);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (2, 2, 3);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (3, 3, 1);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (4, 4, 4);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (5, 5, 5);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (6, 6, 3);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (7, 7, 5);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (8, 8, 2);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (9, 9, 4);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (10, 10, 2);
INSERT INTO etat(id, etat) VALUES (1, 'Occasion');
INSERT INTO etat(id, etat) VALUES (2, 'Neuf avec etiquettes');
INSERT INTO etat(id, etat) VALUES (3, 'Non specifie');
INSERT INTO etat(id, etat) VALUES (4, 'Neuf sans etiquettes');
INSERT INTO etat(id, etat) VALUES (5, 'Neuf avec defauts');
INSERT INTO etat(id, etat) VALUES (6, 'Reconditionne par le vendeur');
INSERT INTO etat(id, etat) VALUES (7, 'Ne fonctionne pas');
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (1, 2);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (1, 3);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (1, 4);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (2, 1);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (2, 6);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (2, 7);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (3, 1);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (3, 3);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (3, 2);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (4, 1);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (4, 7);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (4, 6);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (5, 5);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (5, 6);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (5, 3);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (6, 2);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (6, 4);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (6, 7);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (7, 7);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (7, 4);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (7, 1);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (8, 3);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (8, 2);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (8, 5);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (9, 6);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (9, 1);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (9, 5);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (10, 2);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (10, 3);
INSERT INTO EtatCategorie(Categorieid, etatid) VALUES (10, 1);
INSERT INTO Commission(id, pourcentage) VALUES (1, 5);
INSERT INTO Client(id, nom, prenom, contact, identifiant, pwd) VALUES (1, 'Andrianasolonavalona', 'Mbolatiana Liliane', '0382069125', 'Mbola', '8b3cc7deb981e42cbec867c8fd44a5ec');
INSERT INTO Client(id, nom, prenom, contact, identifiant, pwd) VALUES (2, 'Andrianiony', 'Miharizo Kanto', '0345162879', 'Kanto', 'c33a17d4a2729f26fbd86d67dcf3e97b');
INSERT INTO CompteClient(id, montant, etat, Clientid,actionTransaction) VALUES (1, 250000, 0, 1,0);
INSERT INTO CompteClient(id, montant, etat, Clientid,actionTransaction) VALUES (2, 100000, 0, 1,4);
INSERT INTO CompteClient(id, montant, etat, Clientid,actionTransaction) VALUES (3, 20000, 1, 2,0);
INSERT INTO Enchere(id, produit, libelle, dateHeure, prixMin, duree, etat,idclient) VALUES (1, 1, 'Bac a litiere', '2023-01-13 15:23:00', 10000, 1, '0',1);
INSERT INTO Enchere(id, produit, libelle, dateHeure, prixMin, duree, etat,idclient) VALUES (2, 7, 'Violon', '2023-01-13 15:30:00', 5000, 4, '7',2);

--------------------------------------------------------------------------------------------------------
create table chiffreObtenuSite(
    montant DOUBLE PRECISION,
    dateObtention date
);
