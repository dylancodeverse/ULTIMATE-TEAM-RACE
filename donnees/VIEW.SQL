drop VIEW COMPLETResultatEtape ; 
CREATE VIEW COMPLETResultatEtape AS
SELECT ResultatEtape.ID , ResultatEtape.Depart,
    ResultatEtape.Arrivee , Etape.Nom AS EtapeNOM ,
    Coureur.NOM AS CoureurNOM, Coureur.Equipe 
    FROM ResultatEtape 
    JOIN Coureur ON Coureur.ID = ResultatEtape.Coureur
    JOIN Etape ON Etape.ID = ResultatEtape.Etape ; 

drop view  classementCR ;

SELECT
    CASE
        WHEN condition1 THEN result1
        WHEN condition2 THEN result2
        ...
        ELSE default_result
    END AS alias
FROM table;

DROP view classementCR;
CREATE view classementCR AS
SELECT  RANK() OVER (ORDER BY COMPLETResultatEtape.Arrivee ) AS rang,
        COMPLETResultatEtape.* , 
        CASE 
            WHEN RANK() OVER (ORDER BY COMPLETResultatEtape.Arrivee ) =1 THEN 10
            WHEN RANK() OVER (ORDER BY COMPLETResultatEtape.Arrivee ) =2 THEN 6
            WHEN RANK() OVER (ORDER BY COMPLETResultatEtape.Arrivee ) =3 THEN 4
            WHEN RANK() OVER (ORDER BY COMPLETResultatEtape.Arrivee ) =4 THEN 2
            WHEN RANK() OVER (ORDER BY COMPLETResultatEtape.Arrivee ) =5 THEN 1
            ELSE 0
        END AS POINTS    




        FROM COMPLETResultatEtape
        ORDER BY COMPLETResultatEtape.Arrivee;

