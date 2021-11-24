import java.util.Scanner;
import java.util.Random;

public class main {
	public static int[][] GRILLE = new int[6][7];
	public static int JOUEUR = 0;
	public static Scanner SC = new Scanner(System.in);
	public static boolean verbose = true;

	//	Exercice 1
	public static void initialiseGrille() {
		for (int l = 0; l < GRILLE.length; l++) {
			for (int c = 0; c < GRILLE[0].length; c++) {
				GRILLE[l][c] = 0;
			}
		}
	}

	//  Exercice 2
	public static void jouer(int numero, int c) {
		for (int l = 0; l < GRILLE.length; l++) {
			if (GRILLE[l][c] == 0) {
				GRILLE[l][c] = JOUEUR;
				break;
			}
		}
	}

	//	Exercice 3
	public static void afficheGrille() {
		String output = "";
		for (int l = GRILLE.length - 1; l >= 0; l--) {
//		for (int l = 0; l < GRILLE.length; l++) {
			output += '|';
			for (int c = 0; c < GRILLE[0].length; c++) {
				switch (GRILLE[l][c]) {
					case 0:
						output += " |";
						break;
					case 1:
						output += "X|";
						break;
					case 2:
						output += "O|";
						break;
				}
			}
			output += '\n';
		}
		output += " 0 1 2 3 4 5 6 ";
		System.out.println(output);
	}

	public static boolean aGagneHor(int numero, int y, int x) {
		int count = 0;
		for (int c = x; (c < GRILLE[0].length) && (c <= x + 4); c++) {
			if (GRILLE[y][c] == numero) {
				count++;
			}
		}
		return (count == 4);
	}

	public static boolean aGagneVer(int numero, int y, int x) {
		int count = 0;
		for (int l = y; (l < GRILLE.length) && (l <= y + 4); l++) {
			if (GRILLE[l][x] == numero) {
				count++;
			}
		}
		return (count == 4);
	}


	public static boolean aGagneDiagMont(int numero, int y, int x) {
		int count = 0;
		for (int l = y, c = x; ((l >= 0) && (l >= y - 4)) && ((c < GRILLE[0].length) && (c <= x + 4)); l--, c++) {
			if (GRILLE[l][c] == numero) {
				count++;
			}
		}
		return (count == 4);
	}

	public static boolean aGagneDiagDesc(int numero, int y, int x) {
		int count = 0;
		for (int l = y, c = x; ((l < GRILLE.length) && (l <= y + 4)) && ((c < GRILLE[0].length) && (c <= x + 4)); l++, c++) {
			if (GRILLE[l][c] == numero) {
				count++;
			}
		}
		return (count == 4);
	}

	public static boolean aGagne(int numero) {
		boolean output = false;
		for (int l = 0; l < GRILLE.length; l++) {
			for (int c = 0; c < GRILLE[0].length; c++) {
				output |= aGagneHor(numero, l, c) || aGagneVer(numero, l, c) || aGagneDiagMont(numero, l, c) || aGagneDiagDesc(numero, l, c);
				if (verbose) {
					if (aGagneHor(numero, l, c)){
						System.out.println("aGagneHor" + l + c + numero);
					}if (aGagneVer(numero, l, c)){
						System.out.println("aGagneVer" + l + c + numero);
					}if (aGagneDiagMont(numero, l, c)){
						System.out.println("aGagneDiagMont" + l + c + numero);
					}if (aGagneDiagDesc(numero, l, c)){
						System.out.println("aGagneDiagDesc" + l + c + numero);
					}
				}
			}
		}
		return output;
	}


	public static boolean pasDeZero() {
		int count = 0;
		for (int l = 0; l < GRILLE.length; l++) {
			for (int c = 0; c < GRILLE[0].length; c++) {
				if (GRILLE[l][c] == 0) {
					count++;
				}
			}
		}
		return count == 0;
	}

	public static boolean matchNul() {
		return (aGagne(1) == aGagne(2));
	}

	public static int saisirUnNombre() {
		int output = -1;
		while (!((0 <= output) && (output <= 6))) {
			System.out.println("Quel coup pour le jouer " + JOUEUR + " ?");
			output = SC.nextInt();
		}
		return output;
	}

	public static void clearScreen() {
		System.out.println(System.lineSeparator().repeat(5));
	}

	public static void jeu() {

		initialiseGrille();
		while (!pasDeZero() && matchNul()) {
			clearScreen();
			afficheGrille();
			JOUEUR = JOUEUR == 1 ? 2 : 1;
			if (JOUEUR == 1) {
				jouer(JOUEUR, saisirUnNombre());
			} else {
				joueCoupRandom();
			}
		}

		clearScreen();
		afficheGrille();
		if (aGagne(1)) {
			System.out.println("Le joueur " + 1 + " a gagné");
		} else if (aGagne(2)) {
			System.out.println("Le joueur " + 2 + " a gagné");
		} else {
			System.out.println("Match nul");
		}
	}

	public static void joueCoupRandom() {
		Random rand = new Random();
		int coup = rand.nextInt(7);
		for (int l = 0; l < GRILLE.length; l++) {
			if (GRILLE[l][coup] == 0) {
				GRILLE[l][coup] = JOUEUR;
				break;
			}
		}
	}


	public static void joueCoupRandom2() {
		int[][] backup = GRILLE.clone();
		for (int coup = 0; coup < 7; coup++) {
			for (int l = 0; l < GRILLE.length; l++) {
				if (GRILLE[l][coup] == 0) {
					GRILLE[l][coup] = JOUEUR;
					break;
				}
			}
			aGagne(2);
		}
	}

	public static void main(String[] args) {
		jeu();

		SC.close();
	}
}
