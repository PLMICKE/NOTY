<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
        <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: #f5f5f5; }
        h1 { color: #333; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; background: white; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #607D8B; color: white; }
        tr:hover { background-color: #f1f1f1; }
        form { background: white; padding: 20px; border-radius: 5px; margin-bottom: 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        input[type="text"] { padding: 8px; margin: 5px; border: 1px solid #ddd; border-radius: 3px; }
        input[type="submit"] { padding: 8px 20px; background: #607D8B; color: white; border: none; border-radius: 3px; cursor: pointer; }
        input[type="submit"]:hover { background: #455A64; }
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
        <a href="/detailsdevis">Details Devis</a>
        <a href="/status">Status</a>
        <a href="/demandestatus">Demande Status</a>
        <a href="/updatestatus">Update Status</a>
        <a href="/detailsdevis/somme">chiffre d affaire</a>
        <a href="/couleur">Couleurs</a>
    </div>
    <h1>Tableau de bord</h1>

    <h2>Chiffre d'affaires</h2>
    <p style="font-size: 24px; font-weight: bold; color: #1565C0;">${somme}</p>

    <h2>Nombre de clients</h2>
    <!-- <p style="font-size: 24px; font-weight: bold; color: #E91E63;">${nbClients}</p> -->
    <a href="/clients">
    <button type="button">${nbClients}</button>
    </a>

    <h2>Statistiques par status</h2>
    <table>
        <tr>
            <th>Status</th>
            <th>Nombre de demandes</th>
            <!-- <th>details</th> -->
        </tr>
        <c:forEach var="entry" items="${statsStatus}">
            <tr>
                <td>${entry.key}</td>
                <td>${entry.value}</td>
                <!-- <td><button type="button">details</button></td> -->
            </tr>
        </c:forEach>
    </table>
</body>
</html>