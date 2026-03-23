<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Demandes</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: #f5f5f5; }
        h1 { color: #333; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; background: white; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #2196F3; color: white; }
        tr:hover { background-color: #f1f1f1; }
        form { background: white; padding: 20px; border-radius: 5px; margin-bottom: 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        input[type="text"], input[type="date"], select { padding: 8px; margin: 5px; border: 1px solid #ddd; border-radius: 3px; }
        input[type="submit"] { padding: 8px 20px; background: #2196F3; color: white; border: none; border-radius: 3px; cursor: pointer; }
        input[type="submit"]:hover { background: #1976D2; }
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
    </div>

    <h1>Liste des Demandes</h1>

    <form action="/demandes" method="post">
        <input type="hidden" name="id" value="${demande.id}" />

        <label>Date:</label>
        <input type="date" name="date" value="${demande.date}" required />

        <label>Client:</label>
        <select name="client.id" required>
            <option value="">-- Choisir --</option>
            <c:forEach var="c" items="${clients}">
                <option value="${c.id}">${c.nom}</option>
            </c:forEach>
        </select>

        <label>Lieu:</label>
        <input type="text" name="lieu" value="${demande.lieu}" />

        <label>Districk:</label>
        <input type="text" name="districk" value="${demande.districk}" />

        <input type="submit" value="Enregistrer" />
    </form>

    <table>
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>Client</th>
            <th>Lieu</th>
            <th>Districk</th>
            <th>Action</th>
        </tr>
        <c:forEach var="d" items="${demandes}">
            <tr>
                <td>${d.id}</td>
                <td>${d.date}</td>
                <td>${d.client.nom}</td>
                <td>${d.lieu}</td>
                <td>${d.districk}</td>
                <td><a href="/demandes/delete/${d.id}" onclick="return confirm('Supprimer?')">Supprimer</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
