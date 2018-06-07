package moteurJeu;

import model.Constante;
import model.DessinSpaceInvaders;
import model.SpaceInvaders;
import moteurJeu.MoteurGraphique;

public class Main {

	public static void main(String[] args){
	
		SpaceInvaders jeu = new SpaceInvaders(Constante.ESPACEJEU_LONGUEUR, Constante.ESPACEJEU_HAUTEUR);
	    jeu.initialiserJeu();
	    DessinSpaceInvaders afficheur = new DessinSpaceInvaders(jeu);

	    MoteurGraphique moteur = new MoteurGraphique(jeu, afficheur);
		try {
			moteur.lancerJeu(Constante.ESPACEJEU_LONGUEUR, Constante.ESPACEJEU_HAUTEUR);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    

	}

}
