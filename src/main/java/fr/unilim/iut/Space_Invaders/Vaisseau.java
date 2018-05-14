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
		     if ((this.x<=x) && (x<=this.x+this.longueur-1)) 
			      if ( (this.y-this.hauteur+1<=y) && (y<=this.y))
				  return true;
			
		     return false;
	     }
	    
	    public void positionner(int x, int y) {
		    this.x = x;
		    this.y = y;
	    }
}
