<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestion des Couleurs</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: #f5f5f5; }
        h1 { color: #333; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; background: white; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #E91E63; color: white; }
        tr:hover { background-color: #f1f1f1; }
        form { background: white; padding: 20px; border-radius: 5px; margin-bottom: 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        input[type="text"] { padding: 8px; margin: 5px; border: 1px solid #ddd; border-radius: 3px; }
        input[type="submit"] { padding: 8px 20px; background: #E91E63; color: white; border: none; border-radius: 3px; cursor: pointer; }
        input[type="submit"]:hover { background: #C2185B; }
        a { color: #e74c3c; text-decoration: none; }
        a:hover { text-decoration: underline; }
        .nav { margin-bottom: 20px; }
        .nav a { color: #4CAF50; margin-right: 15px; font-weight: bold; }
        .color-preview { display: inline-block; width: 20px; height: 20px; border: 1px solid #ccc; vertical-align: middle; margin-right: 5px; border-radius: 3px; }
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
        <a href="/updatestatus">Update Status</a>
        <a href="/couleur">Couleurs</a>
    </div>

    <h1>Gestion des Couleurs</h1>

    <form action="/couleur" method="post">
        <label>Minimum (heures) :</label>
        <input type="text" name="minimum" placeholder="ex: 0" required />

        <label>Maximum (heures) :</label>
        <input type="text" name="maximum" placeholder="ex: 8" required />

        <label>Couleur (CSS) :</label>
        <input type="text" name="loko" placeholder="ex: green, #FF0000" required />

        <input type="submit" value="Ajouter" />
    </form>

    <table>
        <tr>
            <th>ID</th>
            <th>Minimum</th>
            <th>Maximum</th>
            <th>Couleur</th>
            <th>Aperçu</th>
            <th>Action</th>
        </tr>
        <c:forEach var="c" items="${couleurList}">
            <tr>
                <td>${c.id}</td>
                <td>${c.minimum}</td>
                <td>${c.maximum}</td>
                <td>${c.loko}</td>
                <td><span class="color-preview" style="background-color: ${c.loko};"></span>${c.loko}</td>
                <td><a href="/couleur/delete/${c.id}" onclick="return confirm('Supprimer?')">Supprimer</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
