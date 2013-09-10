public class Yinsh {

	public enum color {

		BLACK, WHITE
	}

	public color[][]	_plateau;

	public Yinsh() {

		_plateau = new color[11][11];
	}

	public color current_color() {

		return Math.random() > 0.5 ? color.BLACK : color.WHITE;
	}

	public boolean put_ring(char lettreColonne, int ligne, Yinsh.color couleur) {

		int colonne = -1;
		boolean booleen = false;

		switch(lettreColonne) {

			case 'A': colonne = 0; break;
			case 'B': colonne = 1; break;
			case 'C': colonne = 2; break;
			case 'D': colonne = 3; break;
			case 'E': colonne = 4; break;
			case 'F': colonne = 5; break;
			case 'G': colonne = 6; break;
			case 'H': colonne = 7; break;
			case 'I': colonne = 8; break;
			case 'J': colonne = 9; break;
			case 'K': colonne = 10; break;

			default: colonne = -1;
		}

		if(colonne != -1 && ligne > 0 && ligne < 12) {

			if(_plateau[ligne - 1][colonne] == null) {
				
				_plateau[ligne - 1][colonne] = couleur;

				booleen = true;
			}
		}

		return booleen;
	}
}
