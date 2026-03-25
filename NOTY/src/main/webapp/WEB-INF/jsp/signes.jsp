<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Signes</title>
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
                    background-color: #FF9800;
                    color: white;
                }

                tr:hover {
                    background-color: #f1f1f1;
                }

                form {
                    background: white;
                    padding: 20px;
                    border-radius: 5px;
                    margin-bottom: 20px;
                    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                }

                input[type="text"] {
                    padding: 8px;
                    margin: 5px;
                    border: 1px solid #ddd;
                    border-radius: 3px;
                }

                input[type="submit"] {
                    padding: 8px 20px;
                    background: #FF9800;
                    color: white;
                    border: none;
                    border-radius: 3px;
                    cursor: pointer;
                }

                input[type="submit"]:hover {
                    background: #F57C00;
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
                }
            </style>
        </head>

        <body>
            <h1>ETU 3638</h1>
            <div class="nav">
                <a href="/candidats">Candidats</a>
                <a href="/matieres">Matières</a>
                <a href="/profs">Profs</a>
                <a href="/signes">Signes</a>
                <a href="/actions">Actions</a>
                <a href="/notes">Notes</a>
                <a href="/paramettres">Paramètres</a>
                <a href="/notes/calculer" style="color: #E91E63;">Calculer une note</a>
            </div>

            <h1>Liste des Signes</h1>

            <form action="/signes" method="post">
                <input type="hidden" name="id" value="${signe.id}" />
                <label>Nom:</label>
                <input type="text" name="nom" value="${signe.nom}" required />
                <input type="submit" value="Enregistrer" />
            </form>

            <table>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="s" items="${signes}">
                    <tr>
                        <td>${s.id}</td>
                        <td>${s.nom}</td>
                        <td><a href="/signes/delete/${s.id}" onclick="return confirm('Supprimer?')">Supprimer</a></td>
                    </tr>
                </c:forEach>
            </table>
        </body>

        </html>