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
    actionTransaction int default 0, --0 debit --4 credit
    PRIMARY KEY(id)
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
INSERT INTO Produit(id, produit, categorie) VALUES (default, 'Chat', 1);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Chien', 1);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Apiculture', 1);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Petits animaux, Rongeurs', 1);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Architecture, Materiaux', 2);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Art du XIXe et avant', 2);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Art du XXe, contemporain', 2);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Reproductions, copies', 2);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Bijoux de corps', 3);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Bijoux fantaisie', 3);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Bijoux pour homme', 3);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Montre', 3);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Location', 4);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Location de vacances', 4);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Locaux commerciaux', 4);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Cuisine', 5);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Bois d oeuvre, bois composites', 5);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Papier peint', 5);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Materiel d atelier et de bricolage', 5);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Ceramiques francaises', 6);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Ceramiques etrangeres', 6);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Verre, Cristal', 6);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Ceramiques vaisselles, deco', 6);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Instruments a cordes', 7);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Piano, clavier', 7);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Batteries, percussions', 7);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Partitions, livres de chansons, accessoires', 7);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Instruments a vents', 7);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Articles de jeux video', 8);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Consoles', 8);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Accessoire', 8);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Jeux de construction', 9);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Jeux de role', 9);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Jeux de societes', 9);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Autres', 9);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Toilettes, bains', 10);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Peluche doudous', 10);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Equipements pour bebe', 10);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Equipements de securite', 10);
INSERT INTO Produit(id, Produit, categorie) VALUES (default, 'Autres', 10);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (default, 2, 3);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (default, 3, 1);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (default, 4, 4);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (default, 5, 5);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (default, 6, 3);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (default, 7, 5);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (default, 8, 2);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (default, 9, 4);
INSERT INTO EnchereAdmin(id, Categorieid, duree) VALUES (default, 10, 2);
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
INSERT INTO Client(id, nom, prenom, contact, identifiant, pwd) VALUES (default, 'Andrianasolonavalona', 'Mbolatiana Liliane', '0382069125', 'Mbola', '8b3cc7deb981e42cbec867c8fd44a5ec');
INSERT INTO Client(id, nom, prenom, contact, identifiant, pwd) VALUES (default, 'Andrianiony', 'Miharizo Kanto', '0345162879', 'Kanto', 'c33a17d4a2729f26fbd86d67dcf3e97b');
INSERT INTO CompteClient(montant, etat, Clientid,actionTransaction) VALUES (250000, 0, 1,0);
INSERT INTO CompteClient(montant, etat, Clientid,actionTransaction) VALUES (100000, 0, 1,4);
INSERT INTO CompteClient(montant, etat, Clientid,actionTransaction) VALUES (20000, 1, 2,0);
INSERT INTO Enchere( produit, libelle, dateHeure, prixMin, duree, etat,idclient) VALUES ( 1, 'Bac a litiere', '2023-01-13 15:23:00', 10000, 1, '0',1);
INSERT INTO Enchere( produit, libelle, dateHeure, prixMin, duree, etat,idclient) VALUES ( 7, 'Violon', '2023-01-13 15:30:00', 5000, 4, '7',2);
INSERT INTO Enchere( produit, libelle, dateHeure, prixMin, duree, etat,idclient) VALUES ( 32, 'Lego', '2023-01-20 17:30:00', 25000, 10, '0',2);
--------------------------------------------------------------------------------------------------------
create table chiffreObtenuSite(
    montant DOUBLE PRECISION,
    dateObtention date
);


insert into chiffreObtenuSite VALUES
(400000,'2022-07-06'),
(600000,'2022-07-12'),
(400000,'2022-09-23'),
(400000,'2022-12-25');

create table mois(
    id int primary key,
    mois varchar(30)
);

insert into mois VALUES
(1,'Janvier'),
(2,'Fevrier'),
(3,'Mars'),
(4,'Avril'),
(5,'Mai'),
(6,'Juin'),
(7,'Juillet'),
(8,'Aout'),
(9,'Septembre'),
(10,'Octobre'),
(11,'Novembre'),
(12,'Decembre');

create or replace view v_produit as 
select p.*, c.categorie as nomCategorie from produit p right join categorie c on p.categorie=c.id;

create or replace view v_enchereCategorie as 
select e.*, vp.categorie, vp.nomCategorie from enchere e right join v_produit vp on e.produit=vp.id;

create or replace view v_statistiqueCategorie as 
select count(ve.id) as nombre,ve.nomCategorie as categorie from v_enchereCategorie ve group by(ve.nomCategorie);

create or replace view v_chiffreAffaireMois as 
select sum(c.montant) as montant, (select extract(month from c.dateobtention)) as mois from chiffreobtenusite c 
group by (select extract(month from c.dateobtention));

create or replace view v_chiffreAffaire as 
select case when v.montant is null then 0 
else v.montant end as montant,
case when m.mois='Janvier' then 1
when m.mois='Fevrier' then 2
when m.mois='Mars' then 3
when m.mois='Avril' then 4
when m.mois='Mai' then 5
when m.mois='Juin' then 6
when m.mois='Juillet' then 7
when m.mois='Aout' then 8
when m.mois='Septembre' then 9
when m.mois='Octobre' then 10
when m.mois='Novembre' then 11
when m.mois='Decembre' then 12
end as mois,
m.mois as nomMois from v_chiffreAffaireMois v right join mois m on m.id=v.mois;

create or replace view v_enchereEnCours as 
select*, dateheure+interval '1 day'*duree as datefin from encheredetail where dateheure<=current_timestamp and current_timestamp<=dateheure+interval '1 day'*duree and etat='0';

create or replace view v_statutEnchere as 
select ed.*, 
dateheure+interval '1 day'*duree as datefin,
case when etat='0' then 'En cours' when etat='7' then 'Termin&eacute;' end as statut 
from encheredetail ed;

CREATE or replace FUNCTION getInfoEnchere() RETURNS 
table(
    idEnchere int,
    produit int,
    libelle text,
    dateHeure TIMESTAMP,
    prixMin DOUBLE PRECISION,
    duree DOUBLE PRECISION,
    etat VARCHAR(10),
    idClient int,
    produitEnchere VARCHAR(50),
    categorie VARCHAR(50),
    dateFin TIMESTAMP,
    statut VARCHAR(20)
) language plpgsql AS $$
DECLARE 
    statutEnchere record;
    tab record;
    id int;
BEGIN 
    for statutEnchere in (select*from v_statutenchere)
    LOOP
        IF statutEnchere.dateHeure<=current_timestamp and current_timestamp<=statutEnchere.dateFin then
            statut:='En cours';
        ELSE 
            EXECUTE format('UPDATE enchere SET etat = 7 WHERE dateheure<=current_timestamp and current_timestamp<=dateheure+interval ''1 day''*duree');
            statut:='Termine';
        END IF;
        idEnchere:=statutEnchere.id;
        produit:=statutEnchere.produit;
        libelle:=statutEnchere.libelle;
        dateHeure:=statutEnchere.dateHeure;
        prixMin:=statutEnchere.prixMin;
        duree:=statutEnchere.duree;
        etat:=statutEnchere.etat;
        idClient:=statutEnchere.idClient;
        produitEnchere:=statutEnchere.produitEnchere;
        categorie:=statutEnchere.categorie;
        dateFin:=statutEnchere.dateFin;
        return next;
    END LOOP;    
END;
$$;
