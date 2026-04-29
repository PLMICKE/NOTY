<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Détails du devis pour la demande #${demande.id}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: #f5f5f5; }
        h1, h2 { color: #333; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; background: white; margin-bottom: 30px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #1565C0; color: white; }
        tr:hover { background-color: #f1f1f1; }
        a { color: #e74c3c; text-decoration: none; }
        a:hover { text-decoration: underline; }
        .nav { margin-bottom: 20px; }
        .nav a { color: #4CAF50; margin-right: 15px; font-weight: bold; text-decoration: none; }
        .nav a:hover { text-decoration: underline; }
        .info { background: #e3f2fd; padding: 15px; border-radius: 3px; margin-bottom: 20px; border-left: 4px solid #1565C0; font-size: 16px; }
        .badge { background-color: #E91E63; color: white; padding: 5px 10px; border-radius: 20px; font-size: 14px; }
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

    <!-- Retourne à l'historique des status -->
    <a href="/demandestatus/demande/${demande.id}" style="background: #607D8B; color: white; padding: 8px 16px; border-radius: 3px; text-decoration: none; display: inline-block; margin-bottom: 20px;">← Retour</a>

    <h1>Détails des Devis - Demande #${demande.id}</h1>

    <div class="info">
        <strong>Client :</strong> ${demande.client.nom} (Contact: ${demande.client.contact}) <br><br>
        <strong>Lieu :</strong> ${demande.lieu} | <strong>District :</strong> ${demande.districk} | <strong>Date Demande:</strong> ${demande.date}
    </div>

    <c:if test="${empty devisList}">
        <p>Aucun devis n'a été créé pour cette demande.</p>
    </c:if>

    <c:forEach var="devis" items="${devisList}">
        <h2>Devis : <span class="badge">${devis.typeDevis.libelle}</span> (Total : ${devis.montantTotal} Ar)</h2>
        <p>Date de création : ${devis.date}</p>

        <table>
            <tr>
                <th>Ligne ID</th>
                <th>Libellé</th>
                <th>Quantité</th>
                <th>Montant</th>
            </tr>
            <c:forEach var="detail" items="${detailsDevisList}">
                <c:if test="${detail.devis.id == devis.id}">
                    <tr>
                        <td>${detail.id}</td>
                        <td>${detail.libelle}</td>
                        <td>${detail.quantite}</td>
                        <td>${detail.montant} Ar</td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </c:forEach>

</body>
</html>
