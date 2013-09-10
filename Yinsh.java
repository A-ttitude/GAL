public class Yinsh {

	public enum color {

		BLACK, WHITE
	}

	public Yinsh() {

	}

	public color current_color() {

		return Math.random() > 0.5 ? color.BLACK : color.WHITE;
	}
}
