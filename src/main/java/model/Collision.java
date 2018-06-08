package model;

public class Collision {


	public static boolean detecterCollision(Sprite sprt1, Sprite sprt2) {
		if(sprt1 == null || sprt2 == null) {
			return false;
		}
		return MissileEtEnvahisseurOntUnPointEnAbscisseEnCommun(sprt1, sprt2)
				&& MissileEtEnvahisseurOntUnPointEnOrdonneeEnCommun(sprt1, sprt2);
	}

	public static boolean MissileEtEnvahisseurOntUnPointEnOrdonneeEnCommun(Sprite sprt1, Sprite sprt2) {
		return ordonneeLaPlusBasseDesprt1EstDansOrdonneessprt2(sprt2, sprt1)
				|| ordonneeLaPlusHauteDesprt1EstDansOrdonneessprt2(sprt2, sprt1);
	}

	public static boolean MissileEtEnvahisseurOntUnPointEnAbscisseEnCommun(Sprite sprt1, Sprite sprt2) {
		return abscisseAGaucheDesprt1EstDansLesAbscissessprt2(sprt1, sprt2)
				|| abscisseADroiteDesprt1EstDansLesAbscissessprt2(sprt1, sprt2);
	}

	public static boolean ordonneeLaPlusHauteDesprt1EstDansOrdonneessprt2(Sprite sprt1, Sprite sprt2) {
		return sprt1.ordonneeLaPlusHaute() >= sprt2.ordonneeLaPlusBasse()
				&& sprt1.ordonneeLaPlusHaute() <= sprt2.ordonneeLaPlusHaute();
	}

	public static boolean ordonneeLaPlusBasseDesprt1EstDansOrdonneessprt2(Sprite sprt1, Sprite sprt2) {
		return sprt1.ordonneeLaPlusBasse() >= sprt2.ordonneeLaPlusBasse()
				&& sprt1.ordonneeLaPlusBasse() <= sprt2.ordonneeLaPlusHaute();
	}

	public static boolean abscisseADroiteDesprt1EstDansLesAbscissessprt2(Sprite sprt1, Sprite sprt2) {
		return sprt1.abscisseLaPlusADroite() >= sprt2.abscisseLaPlusAGauche() &&
				sprt1.abscisseLaPlusADroite() <= sprt2.abscisseLaPlusADroite();
		}

	public static boolean abscisseAGaucheDesprt1EstDansLesAbscissessprt2(Sprite sprt1, Sprite sprt2) {
		return sprt1.abscisseLaPlusAGauche() >= sprt2.abscisseLaPlusAGauche() &&
			   sprt1.abscisseLaPlusAGauche() <= sprt2.abscisseLaPlusADroite();
	}
}
