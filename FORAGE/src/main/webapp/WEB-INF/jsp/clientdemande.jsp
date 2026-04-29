<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Demandes de ${client.nom}</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 20px;
                    background: #f5f5f5;
                }

                h1 {
                    color: #333;
                }

                table {
                    border-collapse: collapse;
                    width: 100%;
                    margin-top: 20px;
                    background: white;
                }

                th,
                td {
                    border: 1px solid #ddd;
                    padding: 10px;
                    text-align: left;
                }

                th {
                    background-color: #2196F3;
                    color: white;
                }

                tr:hover {
                    background-color: #f1f1f1;
                }

                a {
                    color: #e74c3c;
                    text-decoration: none;
                }

                a:hover {
                    text-decoration: underline;
                }

                .nav {
                    margin-bottom: 20px;
                }

                .nav a {
                    color: #4CAF50;
                    margin-right: 15px;
                    font-weight: bold;
                    text-decoration: none;
                }

                .nav a:hover {
                    text-decoration: underline;
                }

                .info {
                    background: #e3f2fd;
                    padding: 10px;
                    border-radius: 3px;
                    margin-bottom: 15px;
                    border-left: 3px solid #1565C0;
                }
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

            <a href="/clients"
                style="background: #607D8B; color: white; padding: 8px 16px; border-radius: 3px; text-decoration: none;">←
                Retour</a>

            <h1>Demandes du client : ${client.nom}</h1>

            <div class="info">
                Client : ${client.nom} | Contact : ${client.contact}
            </div>

            <table>
                <tr>
                    <th>ID</th>
                    <th>Date</th>
                    <th>Lieu</th>
                    <th>District</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="d" items="${demandes}">
                    <tr>
                        <td>${d.id}</td>
                        <td>${d.date}</td>
                        <td>${d.lieu}</td>
                        <td>${d.districk}</td>
                        <td><a href="/demandestatus/demande/${d.id}" style="color: #1565C0;">details demande</a></td>
                    </tr>
                </c:forEach>
            </table>

            <c:if test="${empty demandes}">
                <p>Aucune demande pour ce client.</p>
            </c:if>
        </body>

        </html>