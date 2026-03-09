<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Paramètres</title>
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
                    background-color: #00BCD4;
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

                select,
                input[type="number"] {
                    padding: 8px;
                    margin: 5px;
                    border: 1px solid #ddd;
                    border-radius: 3px;
                }

                input[type="submit"] {
                    padding: 8px 20px;
                    background: #00BCD4;
                    color: white;
                    border: none;
                    border-radius: 3px;
                    cursor: pointer;
                }

                input[type="submit"]:hover {
                    background: #0097A7;
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

            <h1>Liste des Paramètres</h1>

            <form action="/paramettres" method="post">
                <label>Matière:</label>
                <select name="matiere.id" required>
                    <option value="">-- Choisir --</option>
                    <c:forEach var="m" items="${matieres}">
                        <option value="${m.id}">${m.nom}</option>
                    </c:forEach>
                </select>

                <label>Nombre Différence:</label>
                <input type="number" step="0.01" name="nombredifference" required />

                <label>Signe:</label>
                <select name="signe.id" required>
                    <option value="">-- Choisir --</option>
                    <c:forEach var="s" items="${signes}">
                        <option value="${s.id}">${s.nom}</option>
                    </c:forEach>
                </select>

                <label>Action:</label>
                <select name="action.id" required>
                    <option value="">-- Choisir --</option>
                    <c:forEach var="a" items="${actions}">
                        <option value="${a.id}">${a.nom}</option>
                    </c:forEach>
                </select>

                <input type="submit" value="Enregistrer" />
            </form>

            <table>
                <tr>
                    <th>ID</th>
                    <th>Matière</th>
                    <th>Nombre Différence</th>
                    <th>Signe</th>
                    <th>Action</th>
                    <th>Supprimer</th>
                </tr>
                <c:forEach var="p" items="${paramettres}">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.matiere.nom}</td>
                        <td>${p.nombredifference}</td>
                        <td>${p.signe.nom}</td>
                        <td>${p.action.nom}</td>
                        <td><a href="/paramettres/delete/${p.id}" onclick="return confirm('Supprimer?')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </body>

        </html>