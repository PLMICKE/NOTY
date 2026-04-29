<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifier Demande Status</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: #f5f5f5; }
        h1 { color: #333; }
        form { background: white; padding: 20px; border-radius: 5px; margin-bottom: 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        input[type="text"], input[type="datetime-local"] { padding: 8px; margin: 5px; border: 1px solid #ddd; border-radius: 3px; }
        input[type="submit"] { padding: 8px 20px; background: #1565C0; color: white; border: none; border-radius: 3px; cursor: pointer; }
        input[type="submit"]:hover { background: #0D47A1; }
        .nav { margin-bottom: 20px; }
        .nav a { color: #4CAF50; margin-right: 15px; font-weight: bold; text-decoration: none; }
        .nav a:hover { text-decoration: underline; }
        .form-group { margin-bottom: 10px; }
        .form-group label { display: inline-block; min-width: 120px; font-weight: bold; }
        .info { background: #e3f2fd; padding: 10px; border-radius: 3px; margin-bottom: 15px; border-left: 3px solid #1565C0; }
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

    <h1>Modifier Demande Status #${editDs.id}</h1>

    <div class="info">
        Demande : ${editDs.demande.lieu} - ${editDs.demande.date} | Status : ${editDs.status.libelle}
    </div>

    <form action="/demandestatus/edit/${editDs.id}" method="post">

        <div class="form-group">
            <label>Date:</label>
            <input type="datetime-local" name="date" value="${editDs.date}" required />
        </div>

        <div class="form-group">
            <label>Observation:</label>
            <input type="text" name="observation" value="${editDs.observation}" style="width: 300px;" />
        </div>

        <br/>
        <input type="submit" value="Modifier" />
    </form>
</body>
</html>
