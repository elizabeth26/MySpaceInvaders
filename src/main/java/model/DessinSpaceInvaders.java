package model;
import moteurJeu.DessinJeu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;


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
				List<Missile> missiles = this.jeu.recupererMissiles();
				for (int i=0; i < missiles.size(); i++) {
					if (missiles.get(i) != null) {
						this.dessinerUnMissile(missiles.get(i), im);
					}
				}
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
		   List<Missile> missiles = this.jeu.recupererMissiles();
		   for (int i=0; i < missiles.size(); i++) {
					this.dessinerUnMissile(missiles.get(i), im);
			}
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