public class Yinsh {

	// Variables //
	
	public enum color {

		BLACK, WHITE
	}

	public color[][]	_plateau;
	public int 			_nbAnneau;

	// Constructeur
	
	public Yinsh() {

		_plateau = new color[11][11];
		_nbAnneau = 0;
	}

	// Méthodes
	
	public color current_color() {

		return Math.random() > 0.5 ? color.BLACK : color.WHITE;
	}

	public void put_ring(char lettreColonne, int ligne, Yinsh.color couleur) {
		
		int colonne = Character.getNumericValue(lettreColonne) - 10;

		if(colonne != -1 && ligne > 0 && ligne < 12) {

			if(_plateau[ligne - 1][colonne] == null) {
				
				_plateau[ligne - 1][colonne] = couleur;

				_nbAnneau++;
			}
		}
	}
	
	public boolean isAnneau(char lettreColonne, int ligne) {
		
		return _plateau[ligne - 1][Character.getNumericValue(lettreColonne) - 10] != null;
	}
	
	public int getNbAnneau() {
		
		return _nbAnneau;
	}
}
