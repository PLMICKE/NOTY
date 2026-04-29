<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Status de la demande #${demande.id}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: #f5f5f5; }
        h1 { color: #333; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; background: white; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #9C27B0; color: white; }
        tr:hover { background-color: #f1f1f1; }
        a { color: #e74c3c; text-decoration: none; }
        a:hover { text-decoration: underline; }
        .nav { margin-bottom: 20px; }
        .nav a { color: #4CAF50; margin-right: 15px; font-weight: bold; text-decoration: none; }
        .nav a:hover { text-decoration: underline; }
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

    <a href="/clients/demandes/${demande.client.id}" style="background: #607D8B; color: white; padding: 8px 16px; border-radius: 3px; text-decoration: none;">← Retour</a>

    <h1>Historique des status - Demande #${demande.id}</h1>

    <div class="info">
        Lieu : ${demande.lieu} | District : ${demande.districk} | Date : ${demande.date} | Client : ${demande.client.nom}
    </div>

    <table>
        <tr>
            <th>ID</th>
            <th>Status</th>
            <th>Date</th>
            <th>Observation</th>
            <th>Action</th>
        </tr>
        <c:forEach var="ds" items="${demandeStatusList}">
            <tr>
                <td>${ds.id}</td>
                <td>${ds.status.libelle}</td>
                <td>${ds.date}</td>
                <td>${ds.observation}</td>
                <td>
                    <c:if test="${ds.status.id != 1}">
                        <a href="/devis/demande/${demande.id}" style="background: #1565C0; color: white; padding: 5px 10px; border-radius: 3px; text-decoration: none; font-size: 14px;">détails devis</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>

    <c:if test="${empty demandeStatusList}">
        <p>Aucun status pour cette demande.</p>
    </c:if>
</body>
</html>

<!-- <a href="/demandestatus/demande/${d.id}" style="color: #1565C0; margin-right: 10px;">details demande</a> -->