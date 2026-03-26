<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Devis</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background: #f5f5f5; }
        h1, h2 { color: #333; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; background: white; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #FF9800; color: white; }
        tr:hover { background-color: #f1f1f1; }
        form { background: white; padding: 20px; border-radius: 5px; margin-bottom: 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        input[type="text"], input[type="number"], input[type="date"], select {
            padding: 8px; margin: 5px; border: 1px solid #ddd; border-radius: 3px;
        }
        input[type="submit"] { padding: 8px 20px; background: #FF9800; color: white; border: none; border-radius: 3px; cursor: pointer; }
        input[type="submit"]:hover { background: #F57C00; }
        a { color: #e74c3c; text-decoration: none; }
        a:hover { text-decoration: underline; }
        .nav { margin-bottom: 20px; }
        .nav a { color: #4CAF50; margin-right: 15px; font-weight: bold; }
        .ligne-detail { display: flex; align-items: center; gap: 10px; margin: 5px 0; }
        .ligne-detail input { flex: 1; }
        .btn-add { background: #4CAF50; color: white; border: none; padding: 6px 14px; border-radius: 3px; cursor: pointer; font-size: 16px; }
        .btn-add:hover { background: #45a049; }
        .btn-remove { background: #e74c3c; color: white; border: none; padding: 6px 10px; border-radius: 3px; cursor: pointer; font-size: 14px; }
        .btn-remove:hover { background: #c0392b; }
        .total-section { margin-top: 10px; font-size: 18px; font-weight: bold; color: #333; }
        .form-group { margin-bottom: 10px; }
        .form-group label { display: inline-block; min-width: 120px; font-weight: bold; }
        .client-info { margin: 5px 0 0 125px; padding: 6px 12px; background: #e3f2fd; color: #1565C0; border-left: 3px solid #1565C0; border-radius: 3px; font-size: 14px; display: none; }
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
    </div>

    <h1>Créer un Devis</h1>

    <form action="/devis" method="post" onsubmit="return validerFormulaire()">

        <div class="form-group">
            <label>Demande:</label>
            <select name="demande.id" id="demandeSelect" required onchange="afficherClient()">
                <option value="">-- Choisir --</option>
                <c:forEach var="d" items="${demandes}">
                    <option value="${d.id}" data-client-nom="${d.client.nom}" data-client-contact="${d.client.contact}">#${d.id} - ${d.lieu}</option>
                </c:forEach>
            </select>
            <div id="clientInfo" class="client-info"></div>
        </div>

        <div class="form-group">
            <label>Type de Devis:</label>
            <select name="typeDevis.id" required>
                <option value="">-- Choisir --</option>
                <c:forEach var="t" items="${typedevisList}">
                    <option value="${t.id}">${t.libelle}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>Date:</label>
            <input type="date" name="date" id="dateField" required />
        </div>

        <h2>Lignes de détail</h2>
        <div id="lignesContainer">
            <div class="ligne-detail">
                <input type="text" name="ligneLibelle" placeholder="Libellé" required />
                <input type="number" step="0.01" name="ligneMontant" placeholder="Montant" required oninput="calculerTotal()" />
                <button type="button" class="btn-remove" onclick="supprimerLigne(this)">−</button>
            </div>
        </div>
        <button type="button" class="btn-add" onclick="ajouterLigne()">+ Ajouter une ligne</button>

        <div class="total-section">
            Montant Total : <span id="totalDisplay">0.00</span>
        </div>
        <input type="hidden" name="montantTotal" id="montantTotalHidden" value="0" />

        <br/><br/>
        <input type="submit" value="Créer le Devis" />
    </form>

    <h1>Liste des Devis</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Montant Total</th>
            <th>Type Devis</th>
            <th>Date</th>
            <th>Demande</th>
            <th>Action</th>
        </tr>
        <c:forEach var="dv" items="${devisList}">
            <tr>
                <td>${dv.id}</td>
                <td>${dv.montantTotal}</td>
                <td>${dv.typeDevis.libelle}</td>
                <td>${dv.date}</td>
                <td>#${dv.demande.id} - ${dv.demande.lieu}</td>
                <td><a href="/devis/delete/${dv.id}" onclick="return confirm('Supprimer?')">Supprimer</a></td>
            </tr>
        </c:forEach>
    </table>

    <script>
        // Date par défaut = aujourd'hui
        document.getElementById('dateField').valueAsDate = new Date();

        function afficherClient() {
            var select = document.getElementById('demandeSelect');
            var info = document.getElementById('clientInfo');
            var opt = select.options[select.selectedIndex];
            if (opt.value) {
                var nom = opt.getAttribute('data-client-nom');
                var contact = opt.getAttribute('data-client-contact');
                info.textContent = 'Client : ' + nom + (contact ? ' — ' + contact : '');
                info.style.display = 'block';
            } else {
                info.style.display = 'none';
            }
        }

        function ajouterLigne() {
            var container = document.getElementById('lignesContainer');
            var div = document.createElement('div');
            div.className = 'ligne-detail';
            div.innerHTML =
                '<input type="text" name="ligneLibelle" placeholder="Libellé" required />' +
                '<input type="number" step="0.01" name="ligneMontant" placeholder="Montant" required oninput="calculerTotal()" />' +
                '<button type="button" class="btn-remove" onclick="supprimerLigne(this)">−</button>';
            container.appendChild(div);
        }

        function supprimerLigne(btn) {
            var container = document.getElementById('lignesContainer');
            if (container.children.length > 1) {
                btn.parentElement.remove();
                calculerTotal();
            } else {
                alert('Minimum 1 ligne obligatoire');
            }
        }

        function calculerTotal() {
            var montants = document.getElementsByName('ligneMontant');
            var total = 0;
            for (var i = 0; i < montants.length; i++) {
                var val = parseFloat(montants[i].value);
                if (!isNaN(val)) {
                    total += val;
                }
            }
            document.getElementById('totalDisplay').textContent = total.toFixed(2);
            document.getElementById('montantTotalHidden').value = total.toFixed(2);
        }

        function validerFormulaire() {
            var container = document.getElementById('lignesContainer');
            if (container.children.length < 1) {
                alert('Ajoutez au moins une ligne de détail');
                return false;
            }
            calculerTotal();
            return true;
        }
    </script>
</body>
</html>