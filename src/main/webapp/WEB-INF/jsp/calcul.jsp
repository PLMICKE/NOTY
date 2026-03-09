<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Calcul Dynamique de Note</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 20px;
                    background: #f5f5f5;
                }

                h1 {
                    color: #333;
                }

                form {
                    background: white;
                    padding: 20px;
                    border-radius: 5px;
                    margin-bottom: 20px;
                    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                    max-width: 500px;
                }

                select {
                    padding: 8px;
                    margin: 10px 0;
                    border: 1px solid #ddd;
                    border-radius: 3px;
                    width: 100%;
                    box-sizing: border-box;
                }

                input[type="submit"] {
                    padding: 10px 20px;
                    background: #E91E63;
                    color: white;
                    border: none;
                    border-radius: 3px;
                    cursor: pointer;
                    width: 100%;
                    margin-top: 10px;
                    font-weight: bold;
                }

                input[type="submit"]:hover {
                    background: #C2185B;
                }

                .error {
                    color: red;
                    background: #ffebee;
                    padding: 10px;
                    border-radius: 3px;
                    margin-bottom: 15px;
                    border: 1px solid #ffcdd2;
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

            <h1>Calculer la Note Finale</h1>

            <form action="/notes/calculer" method="post">
                <c:if test="${not empty error}">
                    <div class="error">Erreur : ${error}</div>
                </c:if>

                <label>Candidat:</label>
                <select name="candidatId" required>
                    <option value="">-- Choisir un candidat --</option>
                    <c:forEach var="c" items="${candidats}">
                        <option value="${c.id}">${c.nom} (${c.numero})</option>
                    </c:forEach>
                </select>

                <label>Matière:</label>
                <select name="matiereId" required>
                    <option value="">-- Choisir une matière --</option>
                    <c:forEach var="m" items="${matieres}">
                        <option value="${m.id}">${m.nom}</option>
                    </c:forEach>
                </select>

                <input type="submit" value="Calculer" />
            </form>
        </body>

        </html>