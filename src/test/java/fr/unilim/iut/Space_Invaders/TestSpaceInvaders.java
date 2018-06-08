package fr.unilim.iut.Space_Invaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.outils.DebordementEspaceJeuException;
import model.Collision;
import model.Dimension;
import model.Position;
import model.SpaceInvaders;
import outils.HorsEspaceJeuException;
import outils.MissileException;

public class TestSpaceInvaders {
	
	 private SpaceInvaders spaceinvaders;

	    @Before
	    public void initialisation() {
		    spaceinvaders = new SpaceInvaders(15, 10);
	    }

	 @Test
	   public void test_AuDebut_JeuSpaceInvaderEstVide() {
		 
		    assertEquals("" + 
		    "...............\n" + 
		    "...............\n" +
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	        }

	    @Test
	    public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {

		   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(0,9), 3);
	       spaceinvaders.deplacerVaisseauVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "VVV............\n" + 
	       "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	     }
	    
	    public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite() {

	        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9),3);
	        spaceinvaders.deplacerVaisseauVersLaDroite();
	        assertEquals("" + 
	        "...............\n" + 
	        "...............\n" +
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "..........VVV..\n" + 
	        "..........VVV..\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	    
	    @Test
	    public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {

	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(12,9), 3);
	       spaceinvaders.deplacerVaisseauVersLaDroite();
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "............VVV\n" + 
	       "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	    @Test
	    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(10,9),3);
	       spaceinvaders.deplacerVaisseauVersLaDroite();
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "............VVV\n" + 
	       "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	    
	    @Test
	    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(1,9), 3);
	       spaceinvaders.deplacerVaisseauVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "VVV............\n" + 
	       "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	     }

	    @Test
	     public void test_MissileBienTireDepuisVaisseau_VaisseauLongueurImpaireMissileLongueurImpaire() {

		   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
		   spaceinvaders.tirerUnMissile(new Dimension(3,2),2);

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       ".......MMM.....\n" + 
	       ".......MMM.....\n" + 
	       ".....VVVVVVV...\n" + 
	       ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	    @Test
	    public void test_MissileAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {

		   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
		   spaceinvaders.tirerUnMissile(new Dimension(3,2),2);

		   spaceinvaders.deplacerMissiles();
		   
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       ".......MMM.....\n" + 
	       ".......MMM.....\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       ".....VVVVVVV...\n" + 
	       ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	    
	    @Test
	    public void test_MissileDisparait_QuandIlCommenceASortirDeEspaceJeu() {

	 	   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
	 	   spaceinvaders.tirerUnMissile(new Dimension(3,2),1);
	 	   for (int i = 1; i <=6 ; i++) {
	 		   spaceinvaders.deplacerMissiles();
	 	   }
	 	   
	 	   spaceinvaders.deplacerMissiles();
	 	   
	        assertEquals("" +
	        "...............\n" + 
	        "...............\n" +
	        "...............\n" + 
	        "...............\n" +
	        "...............\n" +
	        "...............\n" + 
	        "...............\n" +
	        "...............\n" + 
	        ".....VVVVVVV...\n" + 
	        ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	    
	    @Test
		  public void test_unNouvelEnvahisseurEstCorrectementPositionneDansEspaceJeu(){
		  spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(7,1), 1);
		  	
		  assertEquals("" 
		  + ".......EE......\n" 
		  + ".......EE......\n"
		  + "...............\n" 
		  + "...............\n"
		  + "...............\n" 
		  + "...............\n"
		  + "...............\n" 
		  + "...............\n"
		  + "...............\n" 
		  + "...............\n",spaceinvaders.recupererEspaceJeuDansChaineASCII());
		  }
		  
		  
		  @Test
		  public void test_EnvahisseurAvance_EnvahisseurSeDeplaceVerLaDroite(){
		  spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(7,1), 1);
		  spaceinvaders.deplacerEnvahisseurVersLaDroite();
		  
		  assertEquals("" 
		  + "........EE.....\n" 
		  + "........EE.....\n"
		  + "...............\n" 
		  + "...............\n"
		  + "...............\n" 
		  + "...............\n"
		  + "...............\n" 
		  + "...............\n"
		  + "...............\n" 
		  + "...............\n",spaceinvaders.recupererEspaceJeuDansChaineASCII());
		  }
		  
		  @Test
		  public void test_EnvahisseurAvance_EnvahisseurSeDeplaceVerLaGauche(){
		  spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(7,1), 1);
		  spaceinvaders.deplacerEnvahisseurVersLaGauche();
		  
		  assertEquals("" 
		  + "......EE.......\n" 
		  + "......EE.......\n"
		  + "...............\n" 
		  + "...............\n"
		  + "...............\n" 
		  + "...............\n"
		  + "...............\n" 
		  + "...............\n"
		  + "...............\n" 
		  + "...............\n",spaceinvaders.recupererEspaceJeuDansChaineASCII());
		  }
		  
		  @Test
		  public void test_EnvahisseurImmobile_EnvahisseurSeDeplaceVerLaDroite(){
		  spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(13,1), 1);
		  spaceinvaders.deplacerEnvahisseurVersLaDroite();
		  
		  assertEquals("" 
		  + ".............EE\n" 
		  + ".............EE\n"
		  + "...............\n" 
		  + "...............\n"
		  + "...............\n" 
		  + "...............\n"
		  + "...............\n" 
		  + "...............\n"
		  + "...............\n" 
		  + "...............\n",spaceinvaders.recupererEspaceJeuDansChaineASCII());
		  }
		  
		  @Test
		  public void test_EnvahisseurImmobile_EnvahisseurSeDeplaceVerLaGauche(){
		  spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(1,1), 1);
		  spaceinvaders.deplacerEnvahisseurVersLaGauche();
		  
		  assertEquals("" 
		  + "EE.............\n" 
		  + "EE.............\n"
		  + "...............\n" 
		  + "...............\n"
		  + "...............\n" 
		  + "...............\n"
		  + "...............\n" 
		  + "...............\n"
		  + "...............\n" 
		  + "...............\n",spaceinvaders.recupererEspaceJeuDansChaineASCII());
		  }
		  
		@Test
		public void test_CollisionEntreDeuxSprite() throws Exception {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(7,1), 1);
		
		spaceinvaders.tirerUnMissile(new Dimension(3,2),2);
		
		spaceinvaders.deplacerMissiles();
		
		assertEquals("" + 
			      ".......EEE.....\n" + 
			      ".......EEE.....\n" +
			      "...............\n" + 
			      "...............\n" + 
			      ".......MMM.....\n" +  
			      ".......MMM.....\n" + 
			      "...............\n" + 
			      "...............\n" + 
			      ".....VVVVVVV...\n" + 
			      ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		 
		
		spaceinvaders.deplacerMissiles();
		
		assertEquals("" + 
			      ".......EEE.....\n" + 
			      ".......EEE.....\n" +
			      ".......MMM.....\n" + 
			      ".......MMM.....\n" + 
			      "...............\n" + 
			      "...............\n" + 
			      "...............\n" + 
			      "...............\n" + 
			      ".....VVVVVVV...\n" + 
			      ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		
		spaceinvaders.deplacerMissiles();
		
	}
		  @Test
		  public void test_siFin(){
		  		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		  		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(8,5), 1);
		  		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		  		spaceinvaders.deplacerMissiles();
		  		
		  		assertEquals(true, spaceinvaders.etreFini());
		  } 
		  
		  @Test
		  public void test_PasEncoreLaFinduJeu(){
		  		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		  		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(8,5), 1);
		  		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		  		
		  		assertEquals(false, spaceinvaders.etreFini());
		  }
		  
		  

}

