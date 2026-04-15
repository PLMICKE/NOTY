# Forage — Gestion de Devis et Demandes

Application web Spring Boot (JPA + JSP + PostgreSQL) pour la gestion des demandes de forage, devis et suivi de statuts.

## Prérequis

- Java 17
- Maven
- PostgreSQL

## Configuration

Base de données dans `src/main/resources/application.properties` :

| Paramètre | Valeur |
|-----------|--------|
| URL | `jdbc:postgresql://localhost:5432/forage` |
| Username | `postgres` |
| Password | `123` |
| Port serveur | `8180` |

## Installation

1. Créer la base de données PostgreSQL :
```sql
CREATE DATABASE forage;
```

2. Exécuter le script SQL :
```
psql -U postgres -d forage -f script.sql
```

3. Lancer l'application :
```
mvn spring-boot:run
```

4. Accéder à : `http://localhost:8180/clients`

## Tables

| Table | Description |
|-------|-------------|
| `client` | Clients (nom, contact) |
| `demande` | Demandes de forage (date, client, lieu, districk) |
| `typedevis` | Types de devis (libellé) |
| `devis` | Devis (montant_total, type, date, demande) |
| `details_devis` | Lignes de détail d'un devis (libellé, montant) |
| `status` | Statuts possibles (ex: Créé, Validé, Terminé) |
| `demande_status` | Suivi des statuts d'une demande (demande, status, date) |

## Architecture

```
src/main/java/com/noty/
├── NotyApplication.java
├── model/
│   ├── Client.java
│   ├── Demande.java
│   ├── TypeDevis.java
│   ├── Devis.java
│   ├── DetailsDevis.java
│   ├── Status.java
│   └── DemandeStatus.java
├── repository/
│   ├── ClientRepository.java
│   ├── DemandeRepository.java
│   ├── TypeDevisRepository.java
│   ├── DevisRepository.java
│   ├── DetailsDevisRepository.java
│   ├── StatusRepository.java
│   └── DemandeStatusRepository.java
├── service/
│   ├── ClientService.java
│   ├── DemandeService.java
│   ├── TypeDevisService.java
│   ├── DevisService.java
│   ├── DetailsDevisService.java
│   ├── StatusService.java
│   └── DemandeStatusService.java
└── controller/
    ├── ClientController.java
    ├── DemandeController.java
    ├── TypeDevisController.java
    ├── DevisController.java
    ├── DevisApiController.java    (REST API)
    ├── DetailsDevisController.java
    ├── StatusController.java
    └── DemandeStatusController.java
```

## Pages (URLs)

| URL | Description |
|-----|-------------|
| `/clients` | CRUD Clients |
| `/demandes` | CRUD Demandes |
| `/typedevis` | CRUD Types de Devis |
| `/devis` | Création de devis (formulaire dynamique avec lignes de détail) |
| `/detailsdevis` | CRUD Détails Devis |
| `/status` | CRUD Statuts |
| `/demandestatus` | Suivi des statuts des demandes |

## API REST

| Endpoint | Méthode | Description |
|----------|---------|-------------|
| `/api/devis/demande/{id}` | GET | Retourne les infos d'une demande en JSON (utilisé par AJAX dans le formulaire devis) |

## Fonctionnement du Devis

La création d'un devis se fait en **une seule transaction** :

1. L'utilisateur saisit l'ID de la demande, le type de devis et la date
2. Il ajoute des lignes de détail (libellé + montant) dynamiquement via JavaScript
3. Le montant total est calculé automatiquement (somme des montants des lignes)
4. À la soumission :
   - Insertion dans `devis`
   - Insertion de toutes les lignes dans `details_devis`
   - Insertion automatique dans `demande_status` (idstatus = 1)

## Technologies

- Spring Boot 3.2.3
- Spring Data JPA
- PostgreSQL
- JSP + JSTL
- JavaScript vanilla (lignes dynamiques du formulaire devis)

---

## Guide : Utilisation de `Map` en Java

Un `Map` est une structure clé-valeur. On l'utilise ici dans `DevisApiController` pour construire un objet JSON sans créer de classe DTO.

### Import

```java
import java.util.Map;
import java.util.HashMap;
```

### Créer et remplir un Map

```java
Map<String, String> info = new HashMap<>();
info.put("cle", "valeur");
info.put("nom", "Rakoto");
info.put("contact", "034 00 000 00");
```

### Lire une valeur

```java
String nom = info.get("nom");         // "Rakoto"
String absent = info.get("adresse");  // null (clé inexistante)
```

### Vérifier si une clé existe

```java
if (info.containsKey("nom")) {
    // la clé "nom" existe
}
```

### Parcourir un Map

```java
// Par clé-valeur
for (Map.Entry<String, String> entry : info.entrySet()) {
    System.out.println(entry.getKey() + " = " + entry.getValue());
}

// Seulement les clés
for (String cle : info.keySet()) { ... }

// Seulement les valeurs
for (String valeur : info.values()) { ... }
```

### Autres méthodes utiles

| Méthode | Description |
|---------|-------------|
| `info.size()` | Nombre d'éléments |
| `info.isEmpty()` | Vrai si le Map est vide |
| `info.remove("cle")` | Supprimer une entrée |
| `info.clear()` | Vider tout le Map |
| `info.getOrDefault("cle", "defaut")` | Valeur par défaut si clé absente |

### Exemple concret (dans le projet)

```java
// DevisApiController.java
Map<String, String> info = new HashMap<>();
info.put("id", String.valueOf(d.getId()));
info.put("lieu", d.getLieu());
info.put("clientNom", d.getClient().getNom());
return ResponseEntity.ok(info);  // → retourne du JSON
```

Résultat JSON :
```json
{
  "id": "1",
  "lieu": "Antsirabe",
  "clientNom": "Rakoto"
}
```

### Types de Map disponibles

| Type | Caractéristique |
|------|----------------|
| `HashMap` | Le plus courant, pas d'ordre garanti |
| `LinkedHashMap` | Conserve l'ordre d'insertion |
| `TreeMap` | Trié par clé (ordre alphabétique) |

---

## Guide : Méthodes par défaut de `JpaRepository`

En étendant `JpaRepository<Entity, Integer>`, on obtient automatiquement toutes les opérations CRUD sans écrire de requête SQL.

### Déclaration d'un Repository

```java
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    // Aucune méthode à écrire — tout est hérité !
}
```

### Méthodes héritées

#### 1. `findAll()` — Lister tout

```java
List<Client> clients = clientRepository.findAll();
```
SQL généré : `SELECT * FROM client`

#### 2. `findById(id)` — Trouver par ID

```java
Optional<Client> opt = clientRepository.findById(1);

// Utilisation sûre avec Optional :
if (opt.isPresent()) {
    Client c = opt.get();
}

// Ou en une ligne :
Client c = clientRepository.findById(1).orElse(null);
Client c = clientRepository.findById(1).orElseThrow();
```
SQL généré : `SELECT * FROM client WHERE id = 1`

#### 3. `save(entity)` — Insérer ou Mettre à jour

```java
// INSERT (id = 0 ou non défini)
Client nouveau = new Client();
nouveau.setNom("Rakoto");
nouveau.setContact("034 00 000 00");
clientRepository.save(nouveau);  // INSERT INTO client ...

// UPDATE (id existant)
Client existant = clientRepository.findById(1).get();
existant.setNom("Rabe");
clientRepository.save(existant);  // UPDATE client SET ... WHERE id = 1
```
> **Règle** : si l'`id` est 0 (ou non défini) → `INSERT`. Si l'`id` existe → `UPDATE`.

#### 4. `deleteById(id)` — Supprimer par ID

```java
clientRepository.deleteById(1);
```
SQL généré : `DELETE FROM client WHERE id = 1`

#### 5. `count()` — Compter

```java
long total = clientRepository.count();
```
SQL généré : `SELECT COUNT(*) FROM client`

#### 6. `existsById(id)` — Vérifier l'existence

```java
boolean existe = clientRepository.existsById(1);  // true ou false
```
SQL généré : `SELECT COUNT(*) > 0 FROM client WHERE id = 1`

#### 7. `findAllById(ids)` — Trouver par liste d'IDs

```java
List<Client> clients = clientRepository.findAllById(List.of(1, 2, 3));
```
SQL généré : `SELECT * FROM client WHERE id IN (1, 2, 3)`

#### 8. `deleteAll()` — Supprimer tout

```java
clientRepository.deleteAll();
```
SQL généré : `DELETE FROM client`

### Tableau récapitulatif

| Méthode | Action | SQL équivalent |
|---------|--------|---------------|
| `findAll()` | Lister tout | `SELECT *` |
| `findById(id)` | Trouver par ID | `SELECT * WHERE id = ?` |
| `save(entity)` | Insérer ou MAJ | `INSERT` ou `UPDATE` |
| `deleteById(id)` | Supprimer | `DELETE WHERE id = ?` |
| `count()` | Compter | `SELECT COUNT(*)` |
| `existsById(id)` | Vérifier existence | `SELECT COUNT(*) > 0 WHERE id = ?` |
| `findAllById(ids)` | Par liste d'IDs | `SELECT * WHERE id IN (...)` |
| `deleteAll()` | Tout supprimer | `DELETE` |

### Méthodes personnalisées (query methods)

On peut aussi ajouter des méthodes personnalisées par convention de nommage :

```java
@Repository
public interface DemandeRepository extends JpaRepository<Demande, Integer> {

    // Trouver par lieu
    List<Demande> findByLieu(String lieu);
    // → SELECT * FROM demande WHERE lieu = ?

    // Trouver par client ID
    List<Demande> findByClientId(int clientId);
    // → SELECT * FROM demande WHERE idclient = ?

    // Trouver par lieu contenant un texte
    List<Demande> findByLieuContaining(String texte);
    // → SELECT * FROM demande WHERE lieu LIKE '%texte%'

    // Trouver par date après
    List<Demande> findByDateAfter(LocalDate date);
    // → SELECT * FROM demande WHERE date > ?

    // Compter par client
    long countByClientId(int clientId);
    // → SELECT COUNT(*) FROM demande WHERE idclient = ?
}
```

> **Règle** : Spring génère automatiquement le SQL à partir du nom de la méthode. Pas besoin d'écrire la requête !

---

## Guide : Tri croissant et décroissant (List & Map)

### Import nécessaire

```java
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
```

---

### 1. Trier une List de types simples

```java
List<String> noms = new ArrayList<>(List.of("Charlie", "Alice", "Bob"));

// Croissant (A → Z)
Collections.sort(noms);
// → [Alice, Bob, Charlie]

// Décroissant (Z → A)
Collections.sort(noms, Collections.reverseOrder());
// → [Charlie, Bob, Alice]
```

```java
List<Integer> nombres = new ArrayList<>(List.of(30, 10, 20));

// Croissant
Collections.sort(nombres);
// → [10, 20, 30]

// Décroissant
Collections.sort(nombres, Collections.reverseOrder());
// → [30, 20, 10]
```

---

### 2. Trier une List d'objets (ex: Client, Demande)

#### Par un champ String (nom)

```java
List<Client> clients = clientRepository.findAll();

// Croissant par nom (A → Z)
clients.sort(Comparator.comparing(Client::getNom));

// Décroissant par nom (Z → A)
clients.sort(Comparator.comparing(Client::getNom).reversed());
```

#### Par un champ numérique (id)

```java
// Croissant par id
clients.sort(Comparator.comparingInt(Client::getId));

// Décroissant par id
clients.sort(Comparator.comparingInt(Client::getId).reversed());
```

#### Par un champ date

```java
List<Demande> demandes = demandeRepository.findAll();

// Croissant par date (plus ancienne d'abord)
demandes.sort(Comparator.comparing(Demande::getDate));

// Décroissant par date (plus récente d'abord)
demandes.sort(Comparator.comparing(Demande::getDate).reversed());
```

#### Par BigDecimal (montant)

```java
List<Devis> devisList = devisRepository.findAll();

// Croissant par montant
devisList.sort(Comparator.comparing(Devis::getMontantTotal));

// Décroissant par montant
devisList.sort(Comparator.comparing(Devis::getMontantTotal).reversed());
```

#### Tri multiple (par nom puis par id)

```java
clients.sort(Comparator.comparing(Client::getNom)
                        .thenComparingInt(Client::getId));
```

---

### 3. Trier avec Stream (sans modifier la liste originale)

```java
// Retourne une NOUVELLE liste triée (l'originale ne change pas)
List<Client> triCroissant = clients.stream()
    .sorted(Comparator.comparing(Client::getNom))
    .collect(Collectors.toList());

List<Client> triDecroissant = clients.stream()
    .sorted(Comparator.comparing(Client::getNom).reversed())
    .collect(Collectors.toList());
```

---

### 4. Trier via JpaRepository (directement en SQL)

```java
import org.springframework.data.domain.Sort;

// Croissant par nom
List<Client> clients = clientRepository.findAll(Sort.by("nom").ascending());

// Décroissant par nom
List<Client> clients = clientRepository.findAll(Sort.by("nom").descending());

// Tri multiple
List<Client> clients = clientRepository.findAll(
    Sort.by("nom").ascending().and(Sort.by("id").descending())
);
```

Ou via les query methods :

```java
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    // Croissant
    List<Client> findAllByOrderByNomAsc();

    // Décroissant
    List<Client> findAllByOrderByNomDesc();

    // Par date décroissante
    List<Demande> findAllByOrderByDateDesc();
}
```

---

### 5. Trier un Map par clé

```java
Map<String, String> map = new HashMap<>();
map.put("C", "Charlie");
map.put("A", "Alice");
map.put("B", "Bob");

// Croissant par clé (A → Z)
Map<String, String> triParCle = map.entrySet().stream()
    .sorted(Map.Entry.comparingByKey())
    .collect(Collectors.toMap(
        Map.Entry::getKey, Map.Entry::getValue,
        (e1, e2) -> e1, LinkedHashMap::new
    ));
// → {A=Alice, B=Bob, C=Charlie}

// Décroissant par clé (Z → A)
Map<String, String> triParCleDesc = map.entrySet().stream()
    .sorted(Map.Entry.<String, String>comparingByKey().reversed())
    .collect(Collectors.toMap(
        Map.Entry::getKey, Map.Entry::getValue,
        (e1, e2) -> e1, LinkedHashMap::new
    ));
// → {C=Charlie, B=Bob, A=Alice}
```

> **Important** : on utilise `LinkedHashMap` car `HashMap` ne conserve pas l'ordre.

---

### 6. Trier un Map par valeur

```java
Map<String, Integer> scores = new HashMap<>();
scores.put("Rakoto", 85);
scores.put("Rabe", 92);
scores.put("Ravao", 78);

// Croissant par valeur
Map<String, Integer> triParValeur = scores.entrySet().stream()
    .sorted(Map.Entry.comparingByValue())
    .collect(Collectors.toMap(
        Map.Entry::getKey, Map.Entry::getValue,
        (e1, e2) -> e1, LinkedHashMap::new
    ));
// → {Ravao=78, Rakoto=85, Rabe=92}

// Décroissant par valeur
Map<String, Integer> triParValeurDesc = scores.entrySet().stream()
    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
    .collect(Collectors.toMap(
        Map.Entry::getKey, Map.Entry::getValue,
        (e1, e2) -> e1, LinkedHashMap::new
    ));
// → {Rabe=92, Rakoto=85, Ravao=78}
```

---

### 7. TreeMap (Map trié automatiquement par clé)

```java
import java.util.TreeMap;

// Croissant automatique
Map<String, String> treeMap = new TreeMap<>(map);
// → {A=Alice, B=Bob, C=Charlie}

// Décroissant automatique
Map<String, String> treeMapDesc = new TreeMap<>(Collections.reverseOrder());
treeMapDesc.putAll(map);
// → {C=Charlie, B=Bob, A=Alice}
```

---

### Tableau récapitulatif

| Quoi | Croissant | Décroissant |
|------|-----------|-------------|
| `List<String>` | `Collections.sort(list)` | `Collections.sort(list, Collections.reverseOrder())` |
| `List<Objet>` par champ | `.sort(Comparator.comparing(Obj::getChamp))` | `.sort(Comparator.comparing(Obj::getChamp).reversed())` |
| `JpaRepository` | `findAll(Sort.by("champ").ascending())` | `findAll(Sort.by("champ").descending())` |
| `Map` par clé | `stream().sorted(comparingByKey())` | `stream().sorted(comparingByKey().reversed())` |
| `Map` par valeur | `stream().sorted(comparingByValue())` | `stream().sorted(comparingByValue().reversed())` |
| `TreeMap` | `new TreeMap<>(map)` | `new TreeMap<>(Collections.reverseOrder())` |
