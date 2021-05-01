package com.sdzee.tp.servelets;

import com.sdzee.tp.beans.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CreationClient", value = "/creationClient-servlet")
public class CreationClient extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String nom = req.getParameter("nomClient");
        String prenom = req.getParameter("prenomClient");
        String adresse = req.getParameter("adresseClient");
        String telephone = req.getParameter("telephoneClient");
        String email = req.getParameter("emailClient");

        String message;

        /*

         * Initialisation du message à afficher : si un des champs obligatoires

         * du formulaire n'est pas renseigné, alors on affiche un message

         * d'erreur, sinon on affiche un message de succès

         */

        if ( nom.trim().isEmpty() || adresse.trim().isEmpty() || telephone.trim().isEmpty() ) {

            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires. <br> <a href=\"creerClient.jsp\">Cliquez ici</a> pour accéder au formulaire de création d'un client.";

        } else {

            message = "Client créé avec succès !";

        }
        /*

         * Création du bean Client et initialisation avec les données récupérées

         */

        Client client = new Client();
        client.setNom( nom );

        client.setPrenom( prenom );

        client.setAdresse( adresse );

        client.setTelephone( telephone );

        client.setEmail( email );
        /* Ajout du bean et du message à l'objet requête */

        req.setAttribute( "client", client );

        req.setAttribute( "message", message );

        /* Transmission à la page JSP en charge de l'affichage des données */

        this.getServletContext().getRequestDispatcher( "/afficherClient.jsp" ).forward( req, resp );
    }


}
