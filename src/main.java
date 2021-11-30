import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class main {
	public static int[][] GRILLE = new int[6][7];
	public static int JOUEUR = 0;
	public static Scanner SC = new Scanner(System.in);
	public static boolean verbose = true;

	public static void initialiseGrille() {
		for (int l = 0; l < GRILLE.length; l++) {
			for (int c = 0; c < GRILLE[0].length; c++) {
				GRILLE[l][c] = 0;
			}
		}
	}

	public static void jouer(int numero, int c) {
		jouer(GRILLE, numero, c);
	}

	public static void jouer(int[][] grille, int numero, int c) {
		for (int l = 0; l < grille.length; l++) {
			if (grille[l][c] == 0) {
				grille[l][c] = JOUEUR;
				break;
			}
		}
	}

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
		return (aGagne.aGagne(1) == aGagne.aGagne(2));
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
				joueCoupRandom2();
			}
		}

		clearScreen();
		afficheGrille();
		if (aGagne.aGagne(1)) {
			System.out.println("Le joueur " + 1 + " a gagné");
		} else if (aGagne.aGagne(2)) {
			System.out.println("Le joueur " + 2 + " a gagné");
		} else {
			System.out.println("Match nul");
		}
	}

	public static int[][] cloneArray(int[][] arr) {
		int[][] output = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			output[i] = arr[i].clone();
		}
		return output;
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
		int[][] grille = cloneArray(GRILLE);
		int coup = -1;
		for (int i = 0; i < grille[0].length; i++) {
			grille = cloneArray(GRILLE);
			jouer(grille, 2, i);
			if (aGagne.aGagne(grille, 2)) {
				coup = i;
			}
		}
		if (coup != -1) {
			jouer(2, coup);
		} else {
			joueCoupRandom();
		}
	}

	public static void main(String[] args) {
		jeu();

		SC.close();
	}
}
