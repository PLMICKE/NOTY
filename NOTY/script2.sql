-- Fichier: script2.sql
-- Description: Données de test pour valider la résolution de conflits par règle de proximité.

-- 1. Nettoyage initial pour avoir une base propre (optionnel mais recommandé)
TRUNCATE TABLE candidat, matiere, prof, signe, action, note, paramettre RESTART IDENTITY CASCADE;

-- 2. Insertion des données de référence
INSERT INTO Signe (nom) VALUES ('>'), ('<'), ('>='), ('<='), ('=');
INSERT INTO Action (nom) VALUES ('petit'), ('grand'), ('moyenne');
INSERT INTO Prof (nom) VALUES ('Professeur Test');

---------------------------------------------------------
-- SCÉNARIO 1 (Tiré du sujet)
-- Paramètres : > 8 (petit) et > 5 (grand)
-- Valeur cible (Somme des différences) = 6
---------------------------------------------------------
INSERT INTO Matiere (nom, coefficient) VALUES ('Matiere S1', 1);
INSERT INTO Candidat (nom, numero) VALUES ('Alice', 'C001');

-- Pour avoir une somme des différences = 6, on insère deux notes : 10 et 16 (|10 - 16| = 6)
INSERT INTO Note (idetudiant, idProf, idmatiere, note) VALUES 
(1, 1, 1, 10.0),
(1, 1, 1, 16.0);

INSERT INTO Paramettre (idmatiere, nombredifference, idsigne, idaction) VALUES 
(1, 8.0, 1, 1), -- > 8 -> Action 1 (petit)
(1, 5.0, 1, 2); -- > 5 -> Action 2 (grand)

---------------------------------------------------------
-- SCÉNARIO 2 (Tiré du sujet)
-- Paramètres : > 8 (petit) et > 6 (grand)
-- Valeur cible = 7
---------------------------------------------------------
INSERT INTO Matiere (nom, coefficient) VALUES ('Matiere S2', 1);
INSERT INTO Candidat (nom, numero) VALUES ('Bob', 'C002');

-- Notes pour avoir une somme de différences = 7 : 10 et 17 (|10 - 17| = 7)
INSERT INTO Note (idetudiant, idProf, idmatiere, note) VALUES 
(2, 1, 2, 10.0),
(2, 1, 2, 17.0);

INSERT INTO Paramettre (idmatiere, nombredifference, idsigne, idaction) VALUES 
(2, 8.0, 1, 1), -- > 8 -> Action 1 (petit)
(2, 6.0, 1, 2); -- > 6 -> Action 2 (grand)


---------------------------------------------------------
-- SCÉNARIO 3 (Version mathématiquement cohérente du S1)
-- Remarque : dans le S1, 6 n'est pas strictement supérieur à 8, 
-- donc la condition "> 8" n'est en fait pas satisfaite.
-- Voici un scénario où les DEUX conditions sont VRAIMENT satisfaites :
-- Paramètres : > 5 (petit) et > 8 (grand). Valeur = 9.
---------------------------------------------------------
INSERT INTO Matiere (nom, coefficient) VALUES ('Matiere S3 (Coherent)', 1);
INSERT INTO Candidat (nom, numero) VALUES ('Charlie', 'C003');

-- Notes pour avoir une différence = 9 : 10 et 19
INSERT INTO Note (idetudiant, idProf, idmatiere, note) VALUES 
(3, 1, 3, 10.0),
(3, 1, 3, 19.0);

INSERT INTO Paramettre (idmatiere, nombredifference, idsigne, idaction) VALUES 
(3, 5.0, 1, 1), -- > 5
(3, 8.0, 1, 2); -- > 8


---------------------------------------------------------
-- SCÉNARIO 4 (Version mathématiquement cohérente du S2 - Égalité)
-- Paramètres : > 4 (petit) et > 6 (grand). Valeur = 5.
-- Distance à 4 = 1. Distance à 6 = 1. (Égalité parfaite)
-- Seuil le plus petit = 4. L'action "petit" doit gagner.
---------------------------------------------------------
INSERT INTO Matiere (nom, coefficient) VALUES ('Matiere S4 (Egalite)', 1);
INSERT INTO Candidat (nom, numero) VALUES ('David', 'C004');

-- Notes pour avoir une différence = 5 : 10 et 15
INSERT INTO Note (idetudiant, idProf, idmatiere, note) VALUES 
(4, 1, 4, 10.0),
(4, 1, 4, 15.0);

INSERT INTO Paramettre (idmatiere, nombredifference, idsigne, idaction) VALUES 
(4, 4.0, 1, 1), -- > 4
(4, 6.0, 1, 2); -- > 6
