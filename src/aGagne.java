public class aGagne {
	public static boolean aGagneHor(int numero, int y, int x) {
		return aGagneHor(main.GRILLE, numero, y, x);
	}

	public static boolean aGagneVer(int numero, int y, int x) {
		return aGagneVer(main.GRILLE, numero, y, x);
	}

	public static boolean aGagneDiagMont(int numero, int y, int x) {
		return aGagneDiagMont(main.GRILLE, numero, y, x);
	}

	public static boolean aGagneDiagDesc(int numero, int y, int x) {
		return aGagneDiagDesc(main.GRILLE, numero, y, x);
	}

	public static boolean aGagne(int numero) {
		return aGagne(main.GRILLE, numero);
	}


	//	Exercice 4.1
	public static boolean aGagneHor(int[][] grille, int numero, int y, int x) {
		int count = 0;
		for (int c = x; (c < grille[0].length) && (c < x + 4); c++) {
			if (grille[y][c] == numero) {
				count++;
			}
		}
		return (count == 4);
	}

	//	Exercice 4.2
	public static boolean aGagneVer(int[][] grille, int numero, int y, int x) {
		int count = 0;
		for (int l = y; (l < grille.length) && (l < y + 4); l++) {
			if (grille[l][x] == numero) {
				count++;
			}
		}
		return (count == 4);
	}

	public static boolean aGagneDiagMont(int[][] grille, int numero, int y, int x) {
		int count = 0;
		for (int l = y, c = x; ((l >= 0) && (l > y - 4)) && ((c < grille[0].length) && (c < x + 4)); l--, c++) {
			if (grille[l][c] == numero) {
				count++;
			}
		}
		return (count == 4);
	}

	public static boolean aGagneDiagDesc(int[][] grille, int numero, int y, int x) {
		int count = 0;
		for (int l = y, c = x; ((l < grille.length) && (l < y + 4)) && ((c < grille[0].length) && (c < x + 4)); l++, c++) {
			if (grille[l][c] == numero) {
				count++;
			}
		}
		return (count == 4);
	}

	public static boolean aGagne(int[][] grille, int numero) {
		boolean output = false;
		for (int l = 0; l < grille.length; l++) {
			for (int c = 0; c < grille[0].length; c++) {
				output |= aGagneHor(grille, numero, l, c) || aGagneVer(grille, numero, l, c) || aGagneDiagMont(grille, numero, l, c) || aGagneDiagDesc(grille, numero, l, c);
			}
		}
		return output;
	}
}
