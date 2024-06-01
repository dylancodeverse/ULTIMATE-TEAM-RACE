drop VIEW COMPLETResultatEtape ; 
CREATE VIEW COMPLETResultatEtape AS
SELECT ResultatEtape.ID , ResultatEtape.Depart,
    ResultatEtape.Arrivee , Etape.Nom AS EtapeNOM ,
    Coureur.NOM AS CoureurNOM, Coureur.Equipe 
    FROM ResultatEtape 
    JOIN Coureur ON Coureur.ID = ResultatEtape.Coureur
    JOIN Etape ON Etape.ID = ResultatEtape.Etape ; 