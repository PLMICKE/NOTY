TRUNCATE TABLE 
    demande_status,
    details_devis,
    devis,
    demande,
    typedevis,
    status,
    client
RESTART IDENTITY CASCADE;

TRUNCATE TABLE
    demande_status,
    details_devis,
    devis,
    demande
RESTART IDENTITY CASCADE;

TRUNCATE TABLE 
    typedevis
RESTART IDENTITY CASCADE;