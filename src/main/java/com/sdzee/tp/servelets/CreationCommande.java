package com.sdzee.tp.servelets;

import com.sdzee.tp.beans.Client;
import com.sdzee.tp.beans.Commande;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;


@WebServlet(name = "CreationCommande", value ="/creationCommande-servelet" )
public class CreationCommande extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*

         * Récupération des données saisies, envoyées en tant que paramètres de

         * la requête GET générée à la validation du formulaire

         */

        String nom = req.getParameter( "nomClient" );

        String prenom = req.getParameter( "prenomClient" );

        String adresse = req.getParameter( "adresseClient" );

        String telephone = req.getParameter( "telephoneClient" );

        String email = req.getParameter( "emailClient" );

        /* Récupération de la date courante */

        DateTime dt = new DateTime();

        /* Conversion de la date en String selon le format défini */

        DateTimeFormatter formatter = DateTimeFormat.forPattern( "dd/MM/yyyy HH:mm:ss" );

        String date = dt.toString( formatter );

        double montant;

        try {

            /* Récupération du montant */

            montant = Double.parseDouble( req.getParameter( "montantCommande" ) );

        } catch ( NumberFormatException e ) {

            /* Initialisation à -1 si le montant n'est pas un nombre correct */

            montant = -1;

        }

        String modePaiement = req.getParameter( "modePaiementCommande" );

        String statutPaiement = req.getParameter( "statutPaiementCommande" );

        String modeLivraison = req.getParameter( "modeLivraisonCommande" );

        String statutLivraison = req.getParameter( "statutLivraisonCommande" );


        String message;

        /*

         * Initialisation du message à afficher : si un des champs obligatoires

         * du formulaire n'est pas renseigné, alors on affiche un message

         * d'erreur, sinon on affiche un message de succès

         */

        if ( nom.trim().isEmpty() || adresse.trim().isEmpty() || telephone.trim().isEmpty() || montant == -1

                || modePaiement.isEmpty() || modeLivraison.isEmpty() ) {

            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires. <br> <a href=\"creerCommande.jsp\">Cliquez ici</a> pour accéder au formulaire de création d'une commande.";

        } else {

            message = "Commande créée avec succès !";

        }

        /*

         * Création des beans Client et Commande et initialisation avec les

         * données récupérées

         */

        Client client = new Client();

        client.setNom( nom );

        client.setPrenom( prenom );

        client.setAdresse( adresse );

        client.setTelephone( telephone );

        client.setEmail( email );


        Commande commande = new Commande();

        commande.setClient( client );

        commande.setDate( date );

        commande.setMontant( montant );

        commande.setModePaiement( modePaiement );

        commande.setStatutPaiement( statutPaiement );

        commande.setModeLivraison( modeLivraison );

        commande.setStatutLivraison( statutLivraison );


        /* Ajout du bean et du message à l'objet requête */

        req.setAttribute( "commande", commande );

        req.setAttribute( "message", message );


        /* Transmission à la page JSP en charge de l'affichage des données */

        this.getServletContext().getRequestDispatcher( "/afficherCommande.jsp" ).forward( req, resp );
    }
}

