package fr.unilim.iut.Space_Invaders;

import fr.unilim.iut.outils.DebordementEspaceJeuException;
import outils.HorsEspaceJeuException;

public class SpaceInvaders {
	private static final char MARQUE_FIN_LIGNE = '\n';
	private static final char MARQUE_VIDE = '.';
	private static final char MARQUE_VAISSEAU = 'V';
	int longueur;
    int hauteur;
   
    Vaisseau vaisseau;
    
    public SpaceInvaders(int longueur, int hauteur) {
	   this.longueur = longueur;
	   this.hauteur = hauteur;
   }
    
    @Override
	public String toString() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				 char marque;
				if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
					marque=MARQUE_VAISSEAU;
				else
					marque=MARQUE_VIDE;
				espaceDeJeu.append(marque);
			}
			espaceDeJeu.append(MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}
    
	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				 char marque;
				if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
					marque=MARQUE_VAISSEAU;
				else
					marque=MARQUE_VIDE;
				espaceDeJeu.append(marque);
			}
			espaceDeJeu.append(MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
		}
	

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	private boolean aUnVaisseau() {
		return vaisseau!=null;
	}



	public void positionnerUnNouveauVaisseau(int longueur, int hauteur, int x, int y) {
		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");
		if ( !estDansEspaceJeu(x+longueur-1,y))
			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x,y-hauteur+1))
			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");
		vaisseau = new Vaisseau(longueur, hauteur);
		vaisseau.positionner(x, y);
		}
	

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}
 
	@SuppressWarnings("unused")
	private char recupererMarqueDeLaPosition(int x, int y) {
        char marque;
        if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
           marque='V';
        else
           marque='.';
        return marque;
	}
    
	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1))
			vaisseau.seDeplacerVersLaADroite();
	}
	 public void deplacerVaisseauVersLaGauche() {
		   vaisseau.seDeplacerVersLaGauche();
	    }

}
