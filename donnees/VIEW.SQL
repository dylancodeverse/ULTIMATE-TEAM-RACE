drop VIEW COMPLETResultatEtape cascade; 
-- resultat avec libelle complet
CREATE VIEW COMPLETResultatEtape AS
SELECT ResultatEtape.ID ,
    ResultatEtape.Arrivee , Etape.Nom AS EtapeNOM ,
    Coureur.NOM AS CoureurNOM, Equipe.nom as Equipe, etape.id as etapeid , equipe.id as equipeid ,
    coureur.id as coureurid 
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

drop VIEW CLASSEMENTPARETAPEAVECCHRONO cascade;
CREATE VIEW CLASSEMENTPARETAPEAVECCHRONO AS
SELECT classementcrparetape.* , etape.depart ,     arrivee - depart AS chrono from classementcrparetape join etape on classementCRParEtape.etapeid = etape.id ;



drop view Categoriecompletselonage cascade;
CREATE VIEW Categoriecompletselonage as
select 
    coureur.id ,  
    case when extract(year from age(coureur.dtn)) <18
    then 'JUNIOR'
    END AS categorie
from coureur ;
drop VIEW categorieselonsexe cascade;
CREATE VIEW categorieselonsexe as
select 
    coureur.id ,  
    coureur.genre as categorie 

from coureur ;

drop view categoriecomplet cascade ;
Create view categoriecomplet as 
    select * from Categoriecompletselonage union 
    select * from categorieselonsexe ;

insert into coureurcategorie(categorie,coureur) select categorie,id   from  
categoriecomplet;

drop VIEW COMPLETResultatEtapecategorie cascade;
CREATE VIEW COMPLETResultatEtapecategorie as
select COMPLETResultatEtape.* , coureurcategorie.categorie from COMPLETResultatEtape join coureurcategorie
on coureurcategorie.coureur =COMPLETResultatEtape.coureurid ;

-- point par categorie
drop view classementparcrparcategorieselonetape cascade;
create view classementparcrparcategorieselonetape as 
select 
DENSE_RANK() OVER (partition by COMPLETResultatEtapecategorie.categorie ,COMPLETResultatEtapecategorie.etapeid  ORDER BY COMPLETResultatEtapecategorie.Arrivee ) as rank
,COMPLETResultatEtapecategorie.* from COMPLETResultatEtapecategorie 
        where COMPLETResultatEtapecategorie.Arrivee is not null
                ORDER BY COMPLETResultatEtapecategorie.Arrivee;


-- 
drop view classementparequipeavecpointparcategorie cascade;
create view classementparequipeavecpointparcategorie as 
with classementAvecPointParCoureurParCategorie as
(select classementparcrparcategorieselonetape.*, point.point  from classementparcrparcategorieselonetape
left JOIN POINT ON POINT.RANK = classementparcrparcategorieselonetape.rank ORDER BY Arrivee ) ,
classementequipeavecpoint as
(select classementAvecPointParCoureurParCategorie.equipe , categorie , sum(point ) as point from classementAvecPointParCoureurParCategorie GROUP by 
classementAvecPointParCoureurParCategorie.equipe , categorie order by categorie)
select DENSE_RANK() over(partition by classementequipeavecpoint.categorie order by point desc ) as rank , 
classementequipeavecpoint.equipe ,
classementequipeavecpoint.categorie,
classementequipeavecpoint.point
from classementequipeavecpoint
;
drop view Classementparequipetous cascade;
create view Classementparequipetous as 
with classementequipeavecpoint as
(select   equipe ,sum( point) as point from 
classementparequipeavecpointparcategorie GROUP by  equipe   )
select DENSE_RANK() over(order by classementequipeavecpoint.point desc) as rank ,classementequipeavecpoint.* ,
'tous' as categorie
from classementequipeavecpoint
;



-- alaina fotsiny ny homme
-- with
-- filtrehomme as
--  (select * from COMPLETResultatEtapecategorie where categorie='M')
--  select DENSE_RANK() OVER(partition by filtrehomme.etapeid order by filtrehomme.arrivee) as rang 
--  , filtrehomme.* from filtrehomme;