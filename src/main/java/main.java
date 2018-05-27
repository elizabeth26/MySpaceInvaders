import fr.unilim.iut.Space_Invaders.DessinSapaceInvaiders;
import fr.unilim.iut.Space_Invaders.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.moteurjeu.MoteurGraphique;
import outils.Constante;

public class main {

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
