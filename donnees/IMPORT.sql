
INSERT INTO EQUIPE(NOM ,LOGIN, PSWD ) SELECT RESULTATCSV.equipe, RESULTATCSV.equipe,RESULTATCSV.equipe 
FROM RESULTATCSV ON CONFLICT DO NOTHING;


INSERT INTO Coureur (nom,numerodossard,genre,dtn,Equipe)
select RESULTATCSV.nom , RESULTATCSV.numero_dossard , 
RESULTATCSV.genre
, RESULTATCSV.date_naissance , equipe.id
from RESULTATCSV join equipe on equipe.nom= RESULTATCSV.nom on CONFLICT do NOTHING;


INSERT INTO ResultatEtape (Etape, Coureur, Arrivee) 
select etape.ID, coureur.id , arrivee from RESULTATCSV
join etape on RESULTATCSV.etape_rang = etape.rangEtape 
join coureur on RESULTATCSV.nom = coureur.nom  ON CONFLICT DO NOTHING;



