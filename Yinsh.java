public class Yinsh {

	// Variables //

	public enum color {

		BLACK, WHITE, RIEN
	}

	public color[][]	_plateau;
	public int			_nbAnneau;
	public color		_derniereCouleur;

	// Constructeur

	public Yinsh() {

		_plateau = new color[11][11];
		_nbAnneau = 0;
		
		_derniereCouleur = color.RIEN;
	}

	// Méthodes

	public color current_color() {

		return Math.random() > 0.5 ? color.BLACK : color.WHITE;
	}

	public void put_ring(char lettreColonne, int ligne, Yinsh.color couleur) throws Exception {

		int colonne = Character.getNumericValue(lettreColonne) - 10;

		if(verifierCoordonnees(colonne, ligne - 1)) {

			if(_plateau[ligne - 1][colonne] == null) {
				
				if(couleur != _derniereCouleur) {

					_plateau[ligne - 1][colonne] = couleur;
					_derniereCouleur = couleur;
					_nbAnneau++;
				}
				
				else throw new Exception("/!\\ On ne peut pas placer deux anneaux de même couleur consécutivement.");
			}
		}
	}

	public boolean isAnneau(char lettreColonne, int ligne) {

		return _plateau[ligne - 1][Character.getNumericValue(lettreColonne) - 10] != null;
	}

	public int getNbAnneau() {

		return _nbAnneau;
	}

	private boolean verifierCoordonnees(int colonne, int ligne) throws Exception {

		if(colonne < 0 || colonne > 11 || ligne < 0 || ligne > 11)
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 0 && (ligne == 0 || ligne >= 5)) // Si A | 1, 6, 7, 8, 9, 10, 11
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 1 && ligne >= 7) // Si B | 8, 9, 10, 11
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 2 && ligne >= 8) // Si C | 9, 10, 11
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 3 && ligne >= 9) // Si D | 10, 11
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 4 && ligne == 10) // Si E | 10
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 5 && (ligne == 0 || ligne == 10)) // Si F | 10
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 6 && ligne == 0) // Si G | 1
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 7 && ligne < 2) // Si H | 1, 2
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 8 && ligne < 3) // Si I | 1, 2, 3
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 9 && ligne < 4) // Si J | 1, 2, 3, 4
			throw new Exception("/!\\ Coordonnees non valide.");

		if(colonne == 10 && (ligne < 6 || ligne == 10)) // Si K | 1, 2, 3, 4, 5, 6, 11
			throw new Exception("/!\\ Coordonnees non valide.");

		return true;
	}
}
