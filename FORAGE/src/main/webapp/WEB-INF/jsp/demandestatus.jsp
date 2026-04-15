<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Demande Status</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: #f5f5f5; }
        h1 { color: #333; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; background: white; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #E91E63; color: white; }
        tr:hover { background-color: #f1f1f1; }
        form { background: white; padding: 20px; border-radius: 5px; margin-bottom: 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        select, input[type="datetime-local"] { padding: 8px; margin: 5px; border: 1px solid #ddd; border-radius: 3px; }
        input[type="submit"] { padding: 8px 20px; background: #E91E63; color: white; border: none; border-radius: 3px; cursor: pointer; }
        input[type="submit"]:hover { background: #C2185B; }
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

    <h1>Suivi des Demandes (Status)</h1>

    <form action="/demandestatus" method="post">
        <input type="hidden" name="id" value="${demandeStatus.id}" />

        <label>Demande:</label>
        <select name="demande.id" required>
            <option value="">-- Choisir --</option>
            <c:forEach var="d" items="${demandes}">
                <option value="${d.id}">${d.lieu} - ${d.date}</option>
            </c:forEach>
        </select>

        <label>Status:</label>
        <select name="status.id" required>
            <option value="">-- Choisir --</option>
            <c:forEach var="s" items="${statusList}">
                <option value="${s.id}">${s.libelle}</option>
            </c:forEach>
        </select>

        <label>Date:</label>
        <input type="datetime-local" name="date" />

        <label>Observation:</label>
        <input type="text" name="observation" placeholder="Observation (optionnel)" style="width: 300px;" />

        <input type="submit" value="Enregistrer" />
    </form>

    <table>
        <tr>
            <th>ID</th>
            <th>Demande</th>
            <th>Status</th>
            <th>Date</th>
            <th>Observation</th>
            <th>Action</th>
        </tr>
        <c:forEach var="ds" items="${demandeStatusList}">
            <tr>
                <td>${ds.id}</td>
                <td>${ds.demande.lieu} - ${ds.demande.date}</td>
                <td>${ds.status.libelle}</td>
                <td>${ds.date}</td>
                <td>${ds.observation}</td>
                <td><a href="/demandestatus/delete/${ds.id}" onclick="return confirm('Supprimer?')">Supprimer</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
