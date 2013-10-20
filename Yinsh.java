public class Yinsh {

	protected final int verifierDeplacement = 1;
	protected final int changeMarker = 2;
	protected final int removeRow = 3;

	/*
	 * Variables
	 */

	public enum color {

		BLACK, BLACK_MARKER, BLACK_BOTH, WHITE, WHITE_MARKER, WHITE_BOTH, EMPTY
	}

	public color[][] _plateau;

	public int _nbAnneau;
	public int _nbAnneauBlanc;
	public int _nbAnneauNoir;
	public int _derniereCouleur;
	public int _scoreNoir;
	public int _scoreBlanc;

	/*
	 * Constructeur
	 */

	public Yinsh() {

		_plateau = new color[11][11];

		for(int colonne = 0; colonne < 11; colonne++)
			for(int ligne = 0; ligne < 11; ligne++)
				_plateau[colonne][ligne] = color.EMPTY;

		_nbAnneau = 0;
		_nbAnneauBlanc = 0;
		_nbAnneauNoir = 0;

		_derniereCouleur = 0;

		_scoreNoir = 0;
		_scoreBlanc = 0;
	}

	/*
	 * Accesseurs
	 */

	public boolean isAnneau(char lettreColonne, int ligne) {

		return _plateau[Character.getNumericValue(lettreColonne) - 10][ligne - 1] != color.EMPTY;
	}

	public int getNbAnneau() {

		return _nbAnneau;
	}

	public int getNbAnneauBlanc() {

		return _nbAnneauBlanc;
	}

	public int getNbAnneauNoir() {

		return _nbAnneauNoir;
	}

	public color get(char lettreColonne, int ligne) {

		return _plateau[Character.getNumericValue(lettreColonne) - 10][ligne - 1];
	}

	public int getScore(color c) {

		return (c == color.BLACK) ? _scoreNoir : _scoreBlanc;
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

			if(indiceCouleur != _derniereCouleur) {

				_plateau[colonne][ligne - 1] = couleur;
				_derniereCouleur = indiceCouleur;
				_nbAnneau++;

				if(indiceCouleur == 1) _nbAnneauBlanc++;

				else _nbAnneauNoir++;
			}

			else throw new Exception("/!\\ Deux fois la même couleur.");
		}
	}

	protected boolean verifierCoordonnees(int colonne, int ligne, color couleur) throws Exception {

		int l = ligne - 1;

		if(colonne < 0 || colonne > 11 || l < 0 || l > 11)
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 0 && (l == 0 || l >= 5)) // Si A | 1, 6, 7, 8, 9, 10, 11
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 1 && l >= 7) // Si B | 8, 9, 10, 11
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 2 && l >= 8) // Si C | 9, 10, 11
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 3 && l >= 9) // Si D | 10, 11
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 4 && l == 10) // Si E | 10
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 5 && (l == 0 || l == 10)) // Si F | 10
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 6 && l == 0) // Si G | 1
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 7 && l < 2) // Si H | 1, 2
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 8 && l < 3) // Si I | 1, 2, 3
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 9 && l < 4) // Si J | 1, 2, 3, 4
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 10 && (l < 6 || l == 10)) // Si K | 1, 2, 3, 4, 5, 6, 11
			throw new Exception("/!\\ Coordonnees non valide.");

		if(couleur == color.BLACK_BOTH)
			if(_plateau[colonne][l] == color.BLACK_BOTH)
				return true;

		if(couleur == color.WHITE_BOTH)
			if(_plateau[colonne][l] == color.WHITE_BOTH)
				return true;

		if(couleur == color.BLACK_MARKER) {

			if(_plateau[colonne][l] == color.BLACK)
				return true;

			throw new Exception("/!\\ Couleur markeur incorrecte.");
		}

		if(couleur == color.WHITE_MARKER) {

			if(_plateau[colonne][l] == color.WHITE)
				return true;

			throw new Exception("/!\\ Couleur markeur incorrecte.");
		}

		if(_plateau[colonne][l] != color.EMPTY)
			throw new Exception("/!\\ Case déjà occupée.");

		return true;
	}

	protected boolean verifierDeplacement(int debutColonne, int debutLigne, int finColonne, int finLigne) {

		if(debutColonne == finColonne && debutLigne == finLigne)
			return false;

		if(_plateau[finColonne][finLigne - 1] != color.EMPTY)
			return false;

		return function(debutColonne, debutLigne, finColonne, finLigne, verifierDeplacement); // verifierDeplacement
	}

	protected boolean verifierDeplacement(int i, int j) {

		if(_plateau[i][j] == color.BLACK || _plateau[i][j] == color.WHITE)
			return false;

		return true;
	}

	public void put_marker(char lettreColonne, int ligne, color couleur) throws Exception {

		int colonne = Character.getNumericValue(lettreColonne) - 10;

		if(verifierCoordonnees(colonne, ligne, (couleur == color.BLACK) ? color.BLACK_MARKER : color.WHITE_MARKER))
			if(_plateau[colonne][ligne - 1] == couleur)
				_plateau[colonne][ligne - 1] = (couleur == color.BLACK) ? color.BLACK_BOTH : color.WHITE_BOTH;
	}

	public void move_ring(char lettreDebutColonne, int debutLigne, char lettreFinColonne, int finLigne) throws Exception {

		int debutColonne = Character.getNumericValue(lettreDebutColonne) - 10;
		int finColonne = Character.getNumericValue(lettreFinColonne) - 10;

		color c = _plateau[debutColonne][debutLigne - 1];

		if(verifierCoordonnees(debutColonne, debutLigne, c)) {

			if(verifierCoordonnees(finColonne, finLigne, color.EMPTY)) {

				if(_plateau[debutColonne][debutLigne - 1] == color.BLACK_BOTH || _plateau[debutColonne][debutLigne - 1] == color.WHITE_BOTH) {

					if(verifierDeplacement(debutColonne, debutLigne, finColonne, finLigne)) {

						_plateau[finColonne][finLigne - 1] = (c == color.BLACK_BOTH) ? color.BLACK : color.WHITE;
						_plateau[debutColonne][debutLigne - 1] = (c == color.BLACK_BOTH) ? color.BLACK_MARKER : color.WHITE_MARKER;

						function(debutColonne, debutLigne, finColonne, finLigne, changeMarker);
					}

					else throw new Exception("/!\\ Déplacement impossible.");
				}
			}
		}
	}

	protected void changeMarker(int i, int j) {

		_plateau[i][j] = (_plateau[i][j] == color.BLACK_MARKER) ? color.WHITE_MARKER : color.BLACK_MARKER;
	}

	public void remove_row(char lettreDebutColonne, int debutLigne, char lettreFinColonne, int finLigne) {

		int debutColonne = Character.getNumericValue(lettreDebutColonne) - 10;
		int finColonne = Character.getNumericValue(lettreFinColonne) - 10;

		function(debutColonne - 1, debutLigne - 1, finColonne, finLigne, removeRow);
	}

	protected void remove(int i, int j) {

		_plateau[i][j] = color.EMPTY;
	}

	public void remove_ring(char lettreColonne, int ligne) {

		int colonne = Character.getNumericValue(lettreColonne) - 10;
		color c = _plateau[colonne][ligne - 1];

		remove(colonne, ligne - 1);

		if(c == color.BLACK)
			_scoreNoir++;

		else _scoreBlanc++;
	}

	protected boolean function(int debutColonne, int debutLigne, int finColonne, int finLigne, int f) {

		int dC = debutColonne, fC = finColonne;
		int dL = debutLigne, fL = finLigne;

		if(dC > fC) {

			dC = finColonne;
			fC = debutColonne;
		}

		if(dL > fL) {

			dL = finLigne;
			fL = debutLigne;
		}

		int i = dC;
		int j = dL;

		if(dC != fC)
			i++;

		while(i < fC || j < fL) {

			switch(f) {

				case verifierDeplacement:
					if(!verifierDeplacement(i, j))
						return false;
					break;

				case changeMarker: changeMarker(i, j); break;
				case removeRow: remove(i, j); break;
			}

			if(dC != fC)
				i++;

			j++;
		}

		return true;
	}
}
