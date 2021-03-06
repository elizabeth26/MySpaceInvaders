package model;
import fr.unilim.iut.outils.*;
import moteurJeu.DessinJeu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class DessinSpaceInvaders implements DessinJeu {

	private SpaceInvaders jeu;

	   public DessinSpaceInvaders(SpaceInvaders spaceInvaders) {
		   this.jeu = spaceInvaders;
	   }

	   public void dessiner(BufferedImage im) {
		   if (this.jeu.aUnVaisseau()) {
			   Vaisseau vaisseau = this.jeu.recupererVaisseau();
			   this.dessinerUnVaisseau(vaisseau, im);  
			   
			   if (this.jeu.aUnMissile()) {
				   Missile missile = this.jeu.recupererMissile();
				   this.dessinerUnMissile(missile, im);  
			   }
			   
			   if (this.jeu.aUnEnvahisseur()){
				   Envahisseur envahisseur = this.jeu.recupererEnvahisseur();
				   this.dessinerUnEnvahisseur(envahisseur, im);
			   }
		   }
		   
	   }

	   private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage im) {
		   Graphics2D crayon = (Graphics2D) im.getGraphics();

		   crayon.setColor(Color.black);
		   	crayon.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.longueur(), vaisseau.hauteur());

	   }
	   
	   
	   public void dessinerMissile (BufferedImage im) {
		   if (this.jeu.aUnVaisseau()) {
			   Missile missile = this.jeu.recupererMissile();
			   this.dessinerUnMissile(missile, im);
		   }
	   }
	   
	   public void dessinerEnvahisseur (BufferedImage im) {
		   if (this.jeu.aUnEnvahisseur()) {
			   Envahisseur envahisseur = this.jeu.recupererEnvahisseur();
			   this.dessinerUnEnvahisseur(envahisseur, im);
		   }
	   }
	   
	   private void dessinerUnEnvahisseur(Envahisseur envahisseur, BufferedImage im) {
		   Graphics2D crayon = (Graphics2D) im.getGraphics();

		   crayon.setColor(Color.ORANGE);
		   crayon.fillRect(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusBasse(),envahisseur.longueur(),envahisseur.hauteur());

	   }
	   
	   private void dessinerUnMissile(Missile missile, BufferedImage im) {
		   Graphics2D crayon = (Graphics2D) im.getGraphics();

		   crayon.setColor(Color.red);
		   crayon.fillRect(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(),missile.longueur(),missile.hauteur());

	   }


}