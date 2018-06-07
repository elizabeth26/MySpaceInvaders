package model;
import fr.unilim.iut.outils.*;
import moteurJeu.DessinJeu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class DessinSapaceInvaiders implements DessinJeu {
	   private SpaceInvaders jeu;

	   public DessinSapaceInvaiders(SpaceInvaders spaceInvaders) {
		   this.jeu = spaceInvaders;
	   }
	   
	   public void dessiner(BufferedImage im) {
		   if (this.jeu.aUnVaisseau()) {
			   Vaisseau vaisseau = this.jeu.recupererVaisseau();
			   this.dessinerUnVaisseau(vaisseau, im);
		   }
	   }

	   private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage im) {
		   Graphics2D crayon = (Graphics2D) im.getGraphics();

		   crayon.setColor(Color.gray);
		   	crayon.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.longueur(), vaisseau.hauteur());
	   }
}