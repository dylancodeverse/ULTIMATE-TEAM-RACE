drop view COMPLETResultatEtapeSansPenalite cascade;
-- sans penalite
create view  COMPLETResultatEtapeSansPenalite as
SELECT ResultatEtape.ID ,
    ResultatEtape.Arrivee , Etape.Nom AS EtapeNOM ,
    Coureur.NOM AS CoureurNOM, Equipe.nom as Equipe, etape.id as etapeid , equipe.id as equipeid ,
    coureur.id as coureurid , Etape.depart as depart 
    FROM ResultatEtape 
    JOIN Coureur ON Coureur.ID = ResultatEtape.Coureur
    JOIN Etape ON Etape.ID = ResultatEtape.Etape 
    JOIN Equipe on Coureur.Equipe = Equipe.ID
    ; 

drop view penalitesomme
-- somme des penalites
create view penalitesomme as 
select  etape  , equipe , sum( tempspenalite)   as tempspenalite from penalite GROUP by etape,equipe ;

drop VIEW COMPLETResultatEtape cascade; 
-- resultat avec libelle complet avec penalite
CREATE VIEW COMPLETResultatEtape AS
select
COMPLETResultatEtapeSansPenalite.* , COMPLETResultatEtapeSansPenalite.arrivee - COMPLETResultatEtapeSansPenalite.depart +
coalesce(penalitesomme.tempspenalite,'00:00:00') as chrono from COMPLETResultatEtapeSansPenalite left join penalitesomme on penalitesomme.etape =COMPLETResultatEtapeSansPenalite.etapeid and 
penalitesomme.equipe = COMPLETResultatEtapeSansPenalite.equipeid ;





DROP view classementCRParEtape0 cascade;
-- classement des coureurs par etape 
CREATE view classementCRParEtape0 AS
SELECT  DENSE_RANK() OVER (partition by COMPLETResultatEtape.etapeid ORDER BY COMPLETResultatEtape.chrono ) 
AS rang,
        COMPLETResultatEtape.* 
        
        FROM COMPLETResultatEtape  
                 
        where COMPLETResultatEtape.Arrivee is not null
        ORDER BY COMPLETResultatEtape.chrono;

DROP view classementCRParEtape cascade;
CREATE view classementCRParEtape AS
    select classementCRParEtape0.*, coalesce(point.point,0) as point from classementCRParEtape0
        left JOIN POINT ON POINT.RANK =RANG ;


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
SELECT classementcrparetape.*  from classementcrparetape  ;



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

-- categoriecomplet;

drop VIEW COMPLETResultatEtapecategorie cascade;
CREATE VIEW COMPLETResultatEtapecategorie as
select COMPLETResultatEtape.* , coureurcategorie.categorie from COMPLETResultatEtape join coureurcategorie
on coureurcategorie.coureur =COMPLETResultatEtape.coureurid ;

-- point par categorie
drop view classementparcrparcategorieselonetape cascade;
create view classementparcrparcategorieselonetape as 
select 
DENSE_RANK() OVER (partition by COMPLETResultatEtapecategorie.categorie ,COMPLETResultatEtapecategorie.etapeid  ORDER BY COMPLETResultatEtapecategorie.chrono ) as rank
,COMPLETResultatEtapecategorie.* from COMPLETResultatEtapecategorie 
        where COMPLETResultatEtapecategorie.Arrivee is not null
                ORDER BY COMPLETResultatEtapecategorie.chrono;


-- 
drop view classementparequipeavecpointparcategorie cascade;
create view classementparequipeavecpointparcategorie as 
with classementAvecPointParCoureurParCategorie as
(select classementparcrparcategorieselonetape.*, point.point  from classementparcrparcategorieselonetape
left JOIN POINT ON POINT.RANK = classementparcrparcategorieselonetape.rank ORDER BY chrono ) ,
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



--view manisy nom n etape s n nom equipe 
drop view  getPenaltiesWithName cascade;
create view getPenaltiesWithName as 
select penalite.*, etape.nom as nometape 
from etape 
join 
penalite on penalite.etape=etape.id;

drop view getPenaliteAll cascade;
create view getPenaliteAll as 
select getPenaltiesWithName.Id, getPenaltiesWithName.nometape as etapes, equipe.nom as equipes,getPenaltiesWithName.tempspenalite as temps 
from equipe 
join getPenaltiesWithName 
on getPenaltiesWithName.equipe=equipe.id;


