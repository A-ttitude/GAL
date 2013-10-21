public class Yinsh {

	private final int verifierDeplacement = 1;
	private final int changeMarker = 2;
	private final int removeRow = 3;

	public final static int NORMAL = 1;
	public final static int BLITZ = 2;

	public final static int EN_COURS = 1;
	public final static int FINIE = 2;

	/*
	 * Variables
	 */

	public enum color {

		BLACK, BLACK_MARKER, BLACK_BOTH,
		WHITE, WHITE_MARKER, WHITE_BOTH,
		EMPTY
	}

	public final color[][] plateau = new color[11][11];

	private int nbAnneauBlanc = 0;
	private int nbAnneauNoir = 0;
	private int derniereCouleur;
	private int etatPartie = Yinsh.EN_COURS;

	private final int mode;

	/*
	 * Constructeur
	 */

	public Yinsh(int _mode) {

		mode = _mode;

		for(int colonne = 0; colonne < 11; colonne++)
			for(int ligne = 0; ligne < 11; ligne++)
				plateau[colonne][ligne] = color.EMPTY;
	}

	/*
	 * Accesseurs
	 */

	public boolean isAnneau(char lettreColonne, int ligne) {

		return plateau[Character.getNumericValue(lettreColonne) - 10][ligne - 1] != color.EMPTY;
	}

	public int getNbAnneau() {

		return nbAnneauNoir + nbAnneauBlanc;
	}

	public int getNbAnneau(color c) {

		return (c == color.BLACK) ? nbAnneauNoir : nbAnneauBlanc;
	}

	public color get(char lettreColonne, int ligne) {

		return plateau[Character.getNumericValue(lettreColonne) - 10][ligne - 1];
	}

	public int getScore(color c) {

		return (c == color.BLACK) ? (5 - nbAnneauNoir) : (5 - nbAnneauBlanc);
	}

	public color getGagnant() {

		return (nbAnneauNoir != 5) ? color.BLACK : ((nbAnneauBlanc != 5) ? color.WHITE : color.EMPTY);
	}

	public int getEtat() {

		return etatPartie;
	}

	/*
	 * Méthodes
	 */

	@SuppressWarnings("static-method")
	public color current_color() {

		return Math.random() > 0.5 ? color.BLACK : color.WHITE;
	}

	public void put_ring(char lettreColonne, int ligne, color couleur) throws Exception {

		int colonne = Character.getNumericValue(lettreColonne) - 10;
		int indiceCouleur = (couleur == color.WHITE) ? 1 : 2;

		if(verifierCoordonnees(colonne, ligne, couleur)) {

			if(indiceCouleur != derniereCouleur) {

				plateau[colonne][ligne - 1] = couleur;
				derniereCouleur = indiceCouleur;

				if(indiceCouleur == 1) nbAnneauBlanc++;

				else nbAnneauNoir++;
			} else throw new Exception("/!\\ Deux fois la même couleur.");
		}
	}

	boolean verifierCoordonnees(int colonne, int ligne, color c) throws Exception {

		int l = ligne - 1;

		if(colonne < 0 || colonne > 11 || l < 0 || l > 11) throw new Exception("/!\\ Coordonnees non valide.");

		if((colonne == 0 && (l == 0 || l >= 5)) || // Si A | 1, 6, 7, 8, 9, 10, 11
		   (colonne == 1 && l >= 7) || // Si B | 8, 9, 10, 11
		   (colonne == 2 && l >= 8) || // Si C | 9, 10, 11
		   (colonne == 3 && l >= 9) || // Si D | 10, 11
		   (colonne == 4 && l == 10) || // Si E | 10
		   (colonne == 5 && (l == 0 || l == 10)) || // Si F | 10
		   (colonne == 6 && l == 0) || // Si G | 1
		   (colonne == 7 && l < 2) || // Si H | 1, 2
		   (colonne == 8 && l < 3) ||// Si I | 1, 2, 3
		   (colonne == 9 && l < 4) || // Si J | 1, 2, 3, 4
		   (colonne == 10 && (l < 6 || l == 10))) // Si K | 1, 2, 3, 4, 5, 6, 11
			throw new Exception("/!\\ Coordonnees non valide.");

		if((c == color.BLACK_BOTH && plateau[colonne][l] == color.BLACK_BOTH) || (c == color.WHITE_BOTH && plateau[colonne][l] == color.WHITE_BOTH))
			return true;

		if(c == color.BLACK_MARKER) {

			if(plateau[colonne][l] == color.BLACK) return true;

			throw new Exception("/!\\ Couleur markeur incorrecte.");
		}

		if(c == color.WHITE_MARKER) {

			if(plateau[colonne][l] == color.WHITE) return true;

			throw new Exception("/!\\ Couleur markeur incorrecte.");
		}

		if(plateau[colonne][l] != color.EMPTY) throw new Exception("/!\\ Case déjà occupée.");

		return true;
	}

	boolean verifierDeplacement(int debutColonne, int debutLigne, int finColonne, int finLigne) {

		return !(debutColonne == finColonne && debutLigne == finLigne) &&
				plateau[finColonne][finLigne - 1] == color.EMPTY &&
				function(debutColonne, debutLigne, finColonne, finLigne, verifierDeplacement);
	}

	boolean verifierDeplacement(int i, int j) {

		return plateau[i][j] == color.BLACK || plateau[i][j] == color.WHITE;
	}

	public void put_marker(char lettreColonne, int ligne, color couleur) throws Exception {

		int colonne = Character.getNumericValue(lettreColonne) - 10;

		if(verifierCoordonnees(colonne, ligne, (couleur == color.BLACK) ? color.BLACK_MARKER : color.WHITE_MARKER))
			if(plateau[colonne][ligne - 1] == couleur)
				plateau[colonne][ligne - 1] = (couleur == color.BLACK) ? color.BLACK_BOTH : color.WHITE_BOTH;
	}

	public void move_ring(char lettreDebutColonne, int debutLigne, char lettreFinColonne, int finLigne) throws Exception {

		int debutColonne = Character.getNumericValue(lettreDebutColonne) - 10, finColonne = Character.getNumericValue(lettreFinColonne) - 10;
		color c = plateau[debutColonne][debutLigne - 1];

		if(verifierCoordonnees(debutColonne, debutLigne, c) &&
				verifierCoordonnees(finColonne, finLigne, color.EMPTY) &&
				(plateau[debutColonne][debutLigne - 1] == color.BLACK_BOTH || plateau[debutColonne][debutLigne - 1] == color.WHITE_BOTH) &&
				verifierDeplacement(debutColonne, debutLigne, finColonne, finLigne)) {

			plateau[finColonne][finLigne - 1] = (c == color.BLACK_BOTH) ? color.BLACK : color.WHITE;
			plateau[debutColonne][debutLigne - 1] = (c == color.BLACK_BOTH) ? color.BLACK_MARKER : color.WHITE_MARKER;

			function(debutColonne, debutLigne, finColonne, finLigne, changeMarker);
		} else throw new Exception("/!\\ Déplacement impossible.");
	}

	void changeMarker(int i, int j) {

		plateau[i][j] = (plateau[i][j] == color.BLACK_MARKER) ? color.WHITE_MARKER : color.BLACK_MARKER;
	}

	public void remove_row(char lettreDebutColonne, int debutLigne, char lettreFinColonne, int finLigne) {

		int debutColonne = Character.getNumericValue(lettreDebutColonne) - 10;
		int finColonne = Character.getNumericValue(lettreFinColonne) - 10;

		function(debutColonne - 1, debutLigne - 1, finColonne, finLigne, removeRow);
	}

	void remove(int i, int j) {

		plateau[i][j] = color.EMPTY;
	}

	public void remove_ring(char lettreColonne, int ligne) {

		int colonne = Character.getNumericValue(lettreColonne) - 10;
		color c = plateau[colonne][ligne - 1];

		remove(colonne, ligne - 1);

		if(c == color.BLACK) nbAnneauNoir--;

		else nbAnneauBlanc--;

		verifierGagnant(colonne, ligne);
	}

	void verifierGagnant(int colonne, int ligne) {

		if(mode == Yinsh.BLITZ) {

			System.out.println("Le joueur " + ((plateau[colonne][ligne - 1] == color.BLACK) ? "noir" : "blanc") + " a gagné !");
			etatPartie = Yinsh.FINIE;
		} else if(nbAnneauNoir == 2) {

			System.out.println("Le joueur noir a gagné !");
			etatPartie = Yinsh.FINIE;
		} else if(nbAnneauBlanc == 2) {

			System.out.println("Le joueur blanc a gagné !");
			etatPartie = Yinsh.FINIE;
		}
	}

	boolean function(int debutColonne, int debutLigne, int finColonne, int finLigne, int f) {

		int dC = debutColonne, fC = finColonne, dL = debutLigne, fL = finLigne;

		if(dC > fC) {

			dC = finColonne;
			fC = debutColonne;
		}

		if(dL > fL) {

			dL = finLigne;
			fL = debutLigne;
		}

		int i = dC, j = dL;

		if(dC != fC) i++;

		boolean b = true;

		while(b && (i < fC || j < fL)) {

			switch(f) {

				case verifierDeplacement:
					if(verifierDeplacement(i, j)) b = false;
					break;

				case changeMarker:
					changeMarker(i, j);
					break;
				case removeRow:
					remove(i, j);
					break;
			}

			if(dC != fC) i++;

			j++;
		}

		return b;
	}
}
