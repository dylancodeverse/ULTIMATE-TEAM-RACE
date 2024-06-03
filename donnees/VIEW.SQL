drop VIEW COMPLETResultatEtape cascade; 
-- resultat avec libelle complet
CREATE VIEW COMPLETResultatEtape AS
SELECT ResultatEtape.ID ,
    ResultatEtape.Arrivee , Etape.Nom AS EtapeNOM ,
    Coureur.NOM AS CoureurNOM, Equipe.nom as Equipe, etape.id as etapeid , equipe.id as equipeid
    FROM ResultatEtape 
    JOIN Coureur ON Coureur.ID = ResultatEtape.Coureur
    JOIN Etape ON Etape.ID = ResultatEtape.Etape 
    JOIN Equipe on Coureur.Equipe = Equipe.ID
    ; 






DROP view classementCRParEtape0 cascade;
-- classement des coureurs par etape 
CREATE view classementCRParEtape0 AS
SELECT  DENSE_RANK() OVER (partition by COMPLETResultatEtape.etapeid ORDER BY COMPLETResultatEtape.Arrivee ) 
AS rang,
        COMPLETResultatEtape.* 
        
        FROM COMPLETResultatEtape  
                 
        where COMPLETResultatEtape.Arrivee is not null
        ORDER BY COMPLETResultatEtape.Arrivee;

DROP view classementCRParEtape cascade;
CREATE view classementCRParEtape AS
    select classementCRParEtape0.*, coalesce(point.point,0) as point from classementCRParEtape0
        left JOIN POINT ON POINT.RANK =RANG ORDER BY Arrivee;


--  classement general des coureurs etape non prise en compte
drop VIEW classementCR cascade;

CREATE VIEW classementCR AS
SELECT DENSE_RANK() OVER (ORDER BY SUM(POINT) desc) AS RANG ,
        CoureurNOM ,
        EQUIPE ,
        SUM(POINT) AS POINTS 
        FROM classementCRParEtape GROUP BY CoureurNOM, EQUIPE ;


drop view classementEQ1 cascade;
-- classement des equipes par etape
CREATE VIEW classementEQ1 AS
SELECT 
        classementCR.Equipe,
        SUM(classementCR.POINTS) AS POINTS  
        FROM classementCR
        GROUP BY classementCR.Equipe ;

drop view   CLASSEMENTEQ0 cascade;      
    -- initialisation point pour tous les equipes
CREATE VIEW CLASSEMENTEQ0 AS
SELECT Equipe.nom as Equipe ,
        0 AS POINTS
        FROM Equipe;            

drop view CLASSEMENTEQ2 cascade;
--  union initialisation et equipe avec leur points
CREATE VIEW CLASSEMENTEQ2 AS 
    select * FROM classementEQ1 UNION SELECT * FROM CLASSEMENTEQ0 
    ;

drop view CLASSEMENTEQ cascade;
-- classement complet des equipes 
CREATE VIEW CLASSEMENTEQ  AS 
    SELECT  DENSE_RANK() OVER (ORDER BY sum(points) desc) AS rang,
    Equipe,
    sum(points) as points 
    FROM CLASSEMENTEQ2
    GROUP BY Equipe ORDER by rang ;
    
drop view etatcompteparetape cascade;
-- -- verification des etats de coureur participant a une etape par equipe (complet ou pas)
CREATE VIEW etatcompteparetape as
    with resultatetapeavecequipe as
    (select resultatetape.etape , resultatetape.Coureur , Coureur.equipe from
    resultatetape join Coureur on resultatetape.Coureur = Coureur.id )
    ,countetat as
    (select count(etape) as nb, etape , equipe from resultatetapeavecequipe GROUP by etape , equipe) ,
    columnadded as
    (select nbcoureur, countetat.* from countetat join etape on etape.id= countetat.etape)

    select nbCoureur=nb as estcomplet , etape ,equipe from columnadded;

drop VIEW CLASSEMENTPARETAPEAVECCHRONO ;
CREATE VIEW CLASSEMENTPARETAPEAVECCHRONO AS
SELECT classementcrparetape.* , etape.depart ,     arrivee - depart AS chrono from classementcrparetape join etape on classementCRParEtape.etapeid = etape.id ;