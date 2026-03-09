<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Résultat du Calcul</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
                background: #f5f5f5;
                text-align: center;
            }

            .result-card {
                background: white;
                padding: 40px;
                border-radius: 8px;
                margin: 40px auto;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                max-width: 500px;
            }

            h1 {
                color: #333;
                margin-top: 0;
            }

            h2 {
                color: #E91E63;
                font-size: 3em;
                margin: 20px 0;
            }

            p {
                font-size: 1.2em;
                color: #555;
            }

            .btn {
                display: inline-block;
                padding: 10px 20px;
                background: #2196F3;
                color: white;
                border: none;
                border-radius: 3px;
                cursor: pointer;
                text-decoration: none;
                margin-top: 20px;
                font-weight: bold;
            }

            .btn:hover {
                background: #1976D2;
            }
        </style>
    </head>

    <body>
        <div class="result-card">
            <h1>Résultat Final</h1>
            <p>Candidat: <strong>${candidat != null ? candidat.nom : 'Inconnu'}</strong></p>
            <p>Matière: <strong>${matiere != null ? matiere.nom : 'Inconnue'}</strong></p>

            <h2>${result}</h2>

            <a href="/notes/calculer" class="btn">Faire un autre calcul</a>
            <br>
            <a href="/notes" class="btn" style="background:#4CAF50; margin-top:10px;">Retour aux Notes</a>
        </div>
    </body>

    </html>