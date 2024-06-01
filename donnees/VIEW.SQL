drop VIEW COMPLETResultatEtape cascade; 
CREATE VIEW COMPLETResultatEtape AS
SELECT ResultatEtape.ID , ResultatEtape.Depart,
    ResultatEtape.Arrivee , Etape.Nom AS EtapeNOM ,
    Coureur.NOM AS CoureurNOM, Equipe.nom as Equipe
    FROM ResultatEtape 
    JOIN Coureur ON Coureur.ID = ResultatEtape.Coureur
    JOIN Etape ON Etape.ID = ResultatEtape.Etape 
    JOIN Equipe on Coureur.Equipe = Equipe.ID
    ; 




DROP view classementCR cascade;
CREATE view classementCR AS
SELECT  RANK() OVER (partition by COMPLETResultatEtape.etapenom ORDER BY COMPLETResultatEtape.Arrivee ) AS rang,
        COMPLETResultatEtape.* , 
        CASE 
            WHEN RANK() OVER (ORDER BY COMPLETResultatEtape.Arrivee ) =1 THEN 10
            WHEN RANK() OVER (ORDER BY COMPLETResultatEtape.Arrivee ) =2 THEN 6
            WHEN RANK() OVER (ORDER BY COMPLETResultatEtape.Arrivee ) =3 THEN 4
            WHEN RANK() OVER (ORDER BY COMPLETResultatEtape.Arrivee ) =4 THEN 2
            WHEN RANK() OVER (ORDER BY COMPLETResultatEtape.Arrivee ) =5 THEN 1
            ELSE 0
        END AS POINTS    
        FROM COMPLETResultatEtape where COMPLETResultatEtape.Arrivee is not null and
        COMPLETResultatEtape.Depart is not null
        ORDER BY COMPLETResultatEtape.Arrivee;

drop view classementEQ1 cascade;
CREATE VIEW classementEQ1 AS
SELECT 
        classementCR.Equipe,
        SUM(classementCR.POINTS) AS POINTS  
        FROM classementCR
        GROUP BY classementCR.Equipe ;

drop view   CLASSEMENTEQ0 cascade;      
CREATE VIEW CLASSEMENTEQ0 AS
SELECT Equipe.nom as Equipe ,
        0 AS POINTS
        FROM Equipe;            

drop view CLASSEMENTEQ2 cascade;
CREATE VIEW CLASSEMENTEQ2 AS 
    select * FROM classementEQ1 UNION SELECT * FROM CLASSEMENTEQ0 
    ;

drop view CLASSEMENTEQ cascade;
CREATE VIEW CLASSEMENTEQ  AS 
    SELECT  DENSE_RANK() OVER (ORDER BY sum(points) desc) AS rang,
    Equipe,
    sum(points) as points 
    FROM CLASSEMENTEQ2
    GROUP BY Equipe ORDER by rang ;
    
