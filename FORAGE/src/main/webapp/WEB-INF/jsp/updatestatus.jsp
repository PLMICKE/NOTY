<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Status</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: #f5f5f5; }
        h1 { color: #333; }
        form { background: white; padding: 20px; border-radius: 5px; margin-bottom: 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        select, input[type="text"] { padding: 8px; margin: 5px; border: 1px solid #ddd; border-radius: 3px; }
        input[type="submit"] { padding: 8px 20px; background: #E91E63; color: white; border: none; border-radius: 3px; cursor: pointer; }
        input[type="submit"]:hover { background: #C2185B; }
        .nav { margin-bottom: 20px; }
        .nav a { color: #4CAF50; margin-right: 15px; font-weight: bold; text-decoration: none; }
        .nav a:hover { text-decoration: underline; }
        .form-group { margin-bottom: 10px; }
        .form-group label { display: inline-block; min-width: 120px; font-weight: bold; }
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
        <a href="/detailsdevis/somme">chiffre d affaire</a>
        <a href="/couleur">Couleurs</a>
    </div>

    <h1>Update Status</h1>

    <form action="/updatestatus" method="post">

        <div class="form-group">
            <label>Demande:</label>
            <select name="demande.id" required>
                <option value="">-- Choisir --</option>
                <c:forEach var="d" items="${demandes}">
                    <option value="${d.id}">${d.lieu} - ${d.date}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>Status:</label>
            <select name="status.id" required>
                <option value="">-- Choisir --</option>
                <c:forEach var="s" items="${statusList}">
                    <option value="${s.id}">${s.libelle}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>Observation:</label>
            <input type="text" name="observation" placeholder="Observation (optionnel)" style="width: 300px;" />
        </div>
        <div class="form-group">
            <label>Date:</label>
            <input type="datetime-local" name="date" required />
        </div>

        <br/>
        <input type="submit" value="Valider" />
    </form>
</body>
</html>
