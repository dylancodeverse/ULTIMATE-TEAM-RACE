CREATE OR REPLACE FUNCTION reset_database() RETURNS VOID AS $$
DECLARE
    table_record RECORD;
    delete_query TEXT;
BEGIN
    -- Désactiver les contraintes de clés étrangères pour toutes les tables
    EXECUTE 'SET session_replication_role = ''replica''';
    
    -- Parcourir toutes les tables dans le schéma public
    FOR table_record IN
        SELECT table_name
        FROM information_schema.tables
        WHERE table_schema = 'public' AND table_type = 'BASE TABLE' and table_name != 'appuser' 
    LOOP
        -- Construire la requête DELETE pour chaque table
        delete_query := 'DELETE FROM ' || table_record.table_name || ';';
        
        -- Exécuter la requête DELETE
        EXECUTE delete_query;
    END LOOP;
    
    -- Réactiver les contraintes de clés étrangères
    EXECUTE 'SET session_replication_role = ''origin''';
    
    -- Afficher un message indiquant que la réinitialisation est terminée
    RAISE NOTICE 'Base de données réinitialisée avec succès.';
END;
$$ LANGUAGE plpgsql;
