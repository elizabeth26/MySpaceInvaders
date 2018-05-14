package fr.unilim.iut.Space_Invaders;

public class Vaisseau {
	int x;
	int y;
    int longueur;
    int hauteur;

    public Vaisseau(int longueur, int hauteur) {
		this(longueur, hauteur, 0, 0);
	}
    
	 public Vaisseau( int longueur, int hauteur , int x, int y) {
		this.x = x;
		this.y = y;
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	    public boolean occupeLaPosition(int x, int y) {
	    	return (estAbscisseCouverte(x) && estOrdonneeCouverte(y));
	     }

		public boolean estOrdonneeCouverte(int y) {
			return (ordonneeLaPlusBasse()<=y) && (y<=ordonneeLaPlusHaute());
		}

		public int ordonneeLaPlusHaute() {
			return this.y;
		}

		public int ordonneeLaPlusBasse() {
			return ordonneeLaPlusHaute()-this.hauteur+1;
		}

		public boolean estAbscisseCouverte(int x) {
			return (abscisseLaPlusAGauche()<=x) && x<=abscisseLaPlusADroite();
		}

		public int abscisseLaPlusAGauche() {
			return this.x;
		}
	    public void seDeplacerVersLaADroite() {
		      this.x = this.x + 1 ;
	   }
	    public void seDeplacerVersLaGauche() {
		      this.x = this.x - 1 ;
	   }
		public int abscisseLaPlusADroite() {
			return abscisseLaPlusAGauche()+this.longueur-1;
		}
	    
	    public void positionner(int x, int y) {
		    this.x = x;
		    this.y = y;
	    }
}
