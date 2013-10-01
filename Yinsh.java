public class Yinsh {

	// Variables //

	public enum color {

		BLACK, BLACK_MARKER, BLACK_BOTH, WHITE, WHITE_MARKER, WHITE_BOTH, EMPTY
	}

	public color[][]	_plateau;

	public int			_nbAnneau;
	public int			_nbAnneauBlanc;
	public int			_nbAnneauNoir;
	public int			_derniereCouleur;

	// Constructeur

	public Yinsh() {

		_plateau = new color[11][11];

		for(int i = 0; i < 11; i++)
			for(int j = 0; j < 11; j++)
				_plateau[i][j] = color.EMPTY;

		_nbAnneau = 0;
		_nbAnneauBlanc = 0;
		_nbAnneauNoir = 0;

		_derniereCouleur = 0;
	}

	// Accesseurs

	public boolean isAnneau(char lettreColonne, int ligne) {

		return _plateau[ligne - 1][Character.getNumericValue(lettreColonne) - 10] != null;
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

	// Méthodes

	@SuppressWarnings("static-method")
	public color current_color() {

		return Math.random() > 0.5 ? color.BLACK : color.WHITE;
	}

	public void put_ring(char lettreColonne, int ligne, color couleur) throws Exception {

		int colonne = Character.getNumericValue(lettreColonne) - 10;
		int indiceCouleur = (couleur == color.WHITE) ? 1 : 2;

		if(verifierCoordonnees(colonne, ligne, couleur)) {

			if(indiceCouleur != _derniereCouleur) {

				_plateau[ligne - 1][colonne] = couleur;
				_derniereCouleur = indiceCouleur;
				_nbAnneau++;

				if(indiceCouleur == 1)
					_nbAnneauBlanc++;

				else if(indiceCouleur == 2)
					_nbAnneauNoir++;
			}

			else throw new Exception("/!\\ Deux fois la même couleur.");
		}
	}

	private boolean verifierCoordonnees(int colonne, int ligne, color couleur) throws Exception {
		
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
			if(_plateau[l][colonne] == color.BLACK_BOTH)
				return true;
		
		if(couleur == color.WHITE_BOTH)
			if(_plateau[l][colonne] == color.WHITE_BOTH)
				return true;
		
		if(couleur == color.BLACK_MARKER) {
			
			if(_plateau[l][colonne] == color.BLACK)
				return true;
			
			throw new Exception("/!\\ Couleur markeur incorrecte.");
		}
		
		if(couleur == color.WHITE_MARKER) {
			
			if(_plateau[l][colonne] == color.WHITE)
				return true;
		
			throw new Exception("/!\\ Couleur markeur incorrecte.");
		}
		
		if(_plateau[l][colonne] != color.EMPTY)
			throw new Exception("/!\\ Case déjà occupée.");
		
		return true;
	}

	public void put_marker(char lettreColonne, int ligne, color couleur) throws Exception {

		int colonne = Character.getNumericValue(lettreColonne) - 10;
		
		color c = (couleur == color.BLACK) ? color.BLACK_MARKER : color.WHITE_MARKER;
		
		if(verifierCoordonnees(colonne, ligne, c))
			if(_plateau[ligne - 1][colonne] == couleur)
				_plateau[ligne - 1][colonne] = c;
	}
	
	public void move_ring(char lettreDebutColonne, int debutLigne, char lettreFinColonne, int finLigne) throws Exception {
		
		int debutColonne = Character.getNumericValue(lettreDebutColonne) - 10;
		int finColonne = Character.getNumericValue(lettreFinColonne) - 10;
		
		color c = _plateau[debutLigne - 1][debutColonne];
		
		if(verifierCoordonnees(debutColonne, debutLigne, c)) {
			
			if(verifierCoordonnees(finColonne, finLigne, color.EMPTY)) {
				
				if(_plateau[debutLigne - 1][debutColonne] == color.BLACK_BOTH || _plateau[debutLigne - 1][debutColonne] == color.WHITE_BOTH) {
					
					_plateau[finLigne - 1][finColonne] = (c == color.BLACK_BOTH) ? color.BLACK : color.WHITE;
					_plateau[debutLigne - 1][debutColonne] = (c == color.BLACK_BOTH) ? color.BLACK_MARKER : color.WHITE_MARKER;
				}
			}
		}
	}
}
