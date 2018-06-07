package moteurJeu;

import model.DessinSapaceInvaiders;
import model.SpaceInvaders;
import moteurJeu.MoteurGraphique;
import outils.Constante;

public class Main {

	public static void main(String[] args){
	
		SpaceInvaders jeu = new SpaceInvaders(Constante.ESPACEJEU_LONGUEUR, Constante.ESPACEJEU_HAUTEUR);
	    jeu.initialiserJeu();
	    DessinSapaceInvaiders afficheur = new DessinSapaceInvaiders(jeu);

	    MoteurGraphique moteur = new MoteurGraphique(jeu, afficheur);
		try {
			moteur.lancerJeu(Constante.ESPACEJEU_LONGUEUR, Constante.ESPACEJEU_HAUTEUR);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    

	}

}
