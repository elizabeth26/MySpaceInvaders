package model;
import outils.DebordementEspaceJeuException;
import outils.HorsEspaceJeuException;
import outils.MissileException;

import java.util.ArrayList;
import java.util.List;
import moteurJeu.Commande;
import moteurJeu.Jeu;


public class SpaceInvaders implements Jeu {
	int longueur;
	int hauteur;
	Vaisseau vaisseau;
	List<Missile> missiles;
	Envahisseur envahisseur;
	boolean deplacementEnvahisseurVersLaDroite; 

	public SpaceInvaders(int longueur, int hauteur) {
		this.missiles = new ArrayList<Missile>();
		this.longueur = longueur;
		this.hauteur = hauteur;
		deplacementEnvahisseurVersLaDroite = true;
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append('\n');
		}
		return espaceDeJeu.toString();
	}

	public void initialiserJeu() {
		Position positionVaisseau = new Position(this.longueur / 2, this.hauteur - 1);
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);

		Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR);
		Position positionEnvahisseur = new Position(this.longueur / 2, dimensionEnvahisseur.hauteur() - 1);
		positionnerUnNouvelEnvahisseur(dimensionEnvahisseur, positionEnvahisseur, Constante.ENVAHISSEUR_VITESSE);
	}
	
	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(y, x))
			marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(y, x))
			marque = Constante.MARQUE_MISSILE;
		else if (this.aUnEnvahisseurQuiOccupeLaPosition(y, x))
			marque = Constante.MARQUE_ENVAHISSEUR;
		else
			marque = Constante.MARQUE_VIDE;
		return marque;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int y, int x) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	public boolean aUnVaisseau() {
		return vaisseau != null;
	}

	private boolean aUnEnvahisseurQuiOccupeLaPosition(int y, int x) {
		return this.aUnEnvahisseur() && envahisseur.occupeLaPosition(x, y);
	}

	public boolean aUnEnvahisseur() {
		return envahisseur != null;
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return (((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur)));
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
		}
	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(dimension, position, vitesse);
	}

	public void evoluer(Commande commandeUser) {

		if (commandeUser.gauche) {
			deplacerVaisseauVersLaGauche();
		}

		if (commandeUser.droite) {
			deplacerVaisseauVersLaDroite();
		}

		if (commandeUser.tir) {
			tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),Constante.MISSILE_VITESSE);
		}

		if (this.aUnMissile()) {
			this.deplacerMissiles();
		}
		
		if(this.aUnEnvahisseur()) {
			this.deplacerEnvahisseur();
			this.eliminerEnvahisseur();
		}

	}
	
	public void eliminerEnvahisseur() {
		for (int i=0; i < missiles.size(); i++) {
			if (Collision.detecterCollision(envahisseur, missiles.get(i))) {
				envahisseur=null;
				missiles.remove(i);
			}
		
		}
	}
	
	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}
	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {

		if ((vaisseau.hauteur() + dimensionMissile.hauteur()) > this.hauteur)
			throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");

		Missile nouveauMissile = this.vaisseau.tirerUnMissile(dimensionMissile,vitesseMissile);
		
		boolean peutTirer = true;
		
		for (int i=0; i < missiles.size(); i++) {
			if (Collision.detecterCollision(missiles.get(i), nouveauMissile)) {
				peutTirer = false;
			}
		}
		
		if (peutTirer) {
			this.missiles.add(nouveauMissile);
		}
		
 	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		if (this.aUnMissile()) {
			for (int i=0; i < missiles.size(); i++) {
				if (missiles.get(i).occupeLaPosition(x, y)) {
					return true;
				}
			}
		}
		
		return false;
	}

	public boolean aUnMissile() {
		return !missiles.isEmpty();
	}

	public List<Missile> recupererMissiles() {
		return this.missiles;
	}
	
	public Missile recupererUnSeulMissile(int index) {
		return this.missiles.get(index);
	}

	public void deplacerMissiles() {
		if (this.aUnMissile()) {
			for (int i=0; i < missiles.size(); i++) { 
				missiles.get(i).deplacerVerticalementVers(Direction.HAUT_ECRAN);
				if (!estDansEspaceJeu(missiles.get(i).abscisseLaPlusADroite(), missiles.get(i).ordonneeLaPlusBasse())) {
					this.missiles.remove(i);
				}
			}
		}
	}


	public void deplacerEnvahisseur() {

		if (this.envahisseurSeDeplaceVersLaDroite()) {
			this.deplacerEnvahisseurVersLaDroite();
		} else {
			this.deplacerEnvahisseurVersLaGauche();
		}

	}

	public boolean envahisseurSeDeplaceVersLaDroite() {
		if (this.envahisseurEstAGauche()) {
			this.envahisseur.origine.y=this.envahisseur.origine.y+20;
			this.deplacementEnvahisseurVersLaDroite = true;
			
		} else if (this.envahisseurEstADroite()) {
			this.envahisseur.origine.y=this.envahisseur.origine.y+20;
			this.deplacementEnvahisseurVersLaDroite = false;
		}

		return this.deplacementEnvahisseurVersLaDroite;
	}

	public boolean envahisseurEstADroite() {
		return this.longueur - 1 == this.envahisseur.abscisseLaPlusADroite();
	}

	private boolean envahisseurEstAGauche() {
		return this.envahisseur.abscisseLaPlusAGauche() == 0;
	}

	public void positionnerUnNouvelEnvahisseur(Dimension dimension, Position position, int vitesse) {

		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position de l'envahisseur est en dehors de l'espace jeu");

		int longueurEnvahisseur = dimension.longueur();
		int hauteurEnvahisseur = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurEnvahisseur - 1, y))
			throw new DebordementEspaceJeuException(
					"L'Envahisseur déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurEnvahisseur + 1))
			throw new DebordementEspaceJeuException(
					"L'Envahisseur déborde de l'espace jeu vers le bas à cause de sa hauteur");

		this.envahisseur = new Envahisseur(dimension, position, vitesse);

	}

	public void deplacerEnvahisseurVersLaDroite() {
		if (envahisseur.abscisseLaPlusADroite() < (longueur - 1)) {
			envahisseur.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(envahisseur.abscisseLaPlusADroite(), envahisseur.ordonneeLaPlusHaute())) {
				envahisseur.positionner(longueur - envahisseur.longueur(), envahisseur.ordonneeLaPlusHaute());
			}
		}

	}

	public void deplacerEnvahisseurVersLaGauche() {
		if (0 < envahisseur.abscisseLaPlusAGauche())
			envahisseur.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusHaute())) {
			envahisseur.positionner(0, envahisseur.ordonneeLaPlusHaute());
		}
		
	}

	public Envahisseur recupererEnvahisseur() {
		return this.envahisseur;
	}

	public boolean etreFini() {
		for (int i=0; i < missiles.size(); i++) {
			if (Collision.detecterCollision(envahisseur, missiles.get(i))) {
				return true;
			}
		
		}
		return false;
	}

	
}
