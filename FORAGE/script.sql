CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    contact VARCHAR(100)
);

CREATE TABLE demande (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    idclient INT NOT NULL REFERENCES client(id),
    lieu VARCHAR(100),
    districk VARCHAR(100)
);

CREATE TABLE typedevis (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE devis (
    id SERIAL PRIMARY KEY,
    montant_total DECIMAL(15, 2) DEFAULT 0,
    id_typedevis INT NOT NULL REFERENCES typedevis(id),
    date DATE NOT NULL DEFAULT CURRENT_DATE,
    iddemande INT NOT NULL REFERENCES demande(id)
);

CREATE TABLE details_devis (
    id SERIAL PRIMARY KEY,
    iddevis INT NOT NULL REFERENCES devis(id),
    libelle VARCHAR(255),
    montant DECIMAL(15, 2) NOT NULL
);

CREATE TABLE status (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

CREATE TABLE demande_status (
    id SERIAL PRIMARY KEY,
    iddemande INT NOT NULL REFERENCES demande(id),
    idstatus INT NOT NULL REFERENCES status(id),
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
