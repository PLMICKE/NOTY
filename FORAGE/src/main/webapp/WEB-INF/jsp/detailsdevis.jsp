<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Détails Devis</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: #f5f5f5; }
        h1 { color: #333; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; background: white; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #795548; color: white; }
        tr:hover { background-color: #f1f1f1; }
        form { background: white; padding: 20px; border-radius: 5px; margin-bottom: 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        input[type="text"], input[type="number"], select { padding: 8px; margin: 5px; border: 1px solid #ddd; border-radius: 3px; }
        input[type="submit"] { padding: 8px 20px; background: #795548; color: white; border: none; border-radius: 3px; cursor: pointer; }
        input[type="submit"]:hover { background: #5D4037; }
        a { color: #e74c3c; text-decoration: none; }
        a:hover { text-decoration: underline; }
        .nav { margin-bottom: 20px; }
        .nav a { color: #4CAF50; margin-right: 15px; font-weight: bold; }
    </style>
</head>
<body>
    <div class="nav">
        <a href="/clients">Clients</a>
        <a href="/demandes">Demandes</a>
        <a href="/typedevis">Types Devis</a>
        <a href="/devis">Devis</a>
        <a href="/detailsdevis">Détails Devis</a>
        <a href="/status">Status</a>
        <a href="/demandestatus">Demande Status</a>
        <a href="/detailsdevis/somme">chiffre d affaire</a>
    </div>

    <h1>Liste des Détails Devis</h1>

    <form action="/detailsdevis" method="post">
        <input type="hidden" name="id" value="${detailsDevis.id}" />

        <label>Devis:</label>
        <select name="devis.id" required>
            <option value="">-- Choisir --</option>
            <c:forEach var="dv" items="${devisList}">
                <option value="${dv.id}">Devis #${dv.id} - ${dv.montantTotal}</option>
            </c:forEach>
        </select>

        <label>Libellé:</label>
        <input type="text" name="libelle" value="${detailsDevis.libelle}" />

        <label>Montant:</label>
        <input type="number" step="0.01" name="montant" required />

        <input type="submit" value="Enregistrer" />
    </form>

    <table>
        <tr>
            <th>ID</th>
            <th>Devis</th>
            <th>Libellé</th>
            <th>Montant</th>
            <th>Action</th>
        </tr>
        <c:forEach var="dd" items="${detailsDevisList}">
            <tr>
                <td>${dd.id}</td>
                <td>Devis #${dd.devis.id}</td>
                <td>${dd.libelle}</td>
                <td>${dd.montant}</td>
                <td><a href="/detailsdevis/delete/${dd.id}" onclick="return confirm('Supprimer?')">Supprimer</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
