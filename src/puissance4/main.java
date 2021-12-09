/*
L1 G2 CHIANG Léana, BLANDIN Yann
Projet puissance4 du cours INF-1, 2021
* */

package puissance4;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

import static puissance4.aGagne.aGagne;

public class main {

	//	Exercice 1.1
	public static int[][] GRILLE = new int[6][7];
	public static int JOUEUR = 0;
	public static Scanner SC = new Scanner(System.in);
	public static boolean verbose = true;
	public static int[] dernierPoint = new int[2];

	//	Exercice 1.2
	public static void initialiseGrille() {
		for (int l = 0; l < GRILLE.length; l++) {
			for (int c = 0; c < GRILLE[0].length; c++) {
				GRILLE[l][c] = 0;
			}
		}
	}

	//	Exercice 2.1
	public static void jouer(int numero, int c) {
		jouer(GRILLE, numero, c);
	}

	public static void jouer(int[][] grille, int numero, int c) {
		for (int l = 0; l < grille.length; l++) {
			if (grille[l][c] == 0) {
				grille[l][c] = numero;
				dernierPoint[0] = c;
				dernierPoint[1] = l;
				break;
			}
		}
	}

	//	Exercice 3.1
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

	//	Exercice 4.5
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

	//	Exercice 5
	public static void jeu() {

		initialiseGrille();
		while (!pasDeZero() && matchNul()) {
			clearScreen();
			afficheGrille();
			JOUEUR = JOUEUR == 1 ? 2 : 1;
			if (JOUEUR == 1) {
				jouer(JOUEUR, saisirUnNombre());
//				Laisse l'IA jouer avec l'IA
//				joueCoupRandom3();
			} else {
				joueCoupRandom3();
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

	public static int[][] cloneArray(int[][] arr) {
		int[][] output = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			output[i] = arr[i].clone();
		}
		return output;
	}

	//	Exercice 6
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
			jouer(grille, JOUEUR, i);
			if (aGagne(grille, JOUEUR)) {
				coup = i;
			}
		}
		if (coup != -1) {
			jouer(JOUEUR, coup);
		} else {
			joueCoupRandom();
		}
	}

	public static void joueCoupRandom3() {
		int[][] grille1 = cloneArray(GRILLE);
		int[][] grille2 = cloneArray(grille1);
		int coup = -1;

		int lautreJoueur = JOUEUR == 1 ? 2 : 1;
		ArrayList<Integer> coupPossible = new ArrayList<Integer>();
		for (int i = 0; i < grille1[0].length; i++) coupPossible.add(i);

		for (int i = 0; i < grille1[0].length; i++) {
			grille1 = cloneArray(GRILLE);

			if (aGagne(grille1, JOUEUR)) {
				coup = i;
				break;
			} else {
				for (int j = 0; j < grille2[0].length; j++) {
					grille2 = cloneArray(grille1);
//					Détecter si jouer 1 arrive à gagner ensuite
					jouer(grille2, lautreJoueur, j);
					if (aGagne(grille2, lautreJoueur)) {
						if (coupPossible.contains(i)) {
							coupPossible.remove(coupPossible.indexOf(i));
						}
					}
				}
			}

		}
		if (coup == -1) {
			Random rand = new Random();
			if (coupPossible.size() != 0) {
				coup = coupPossible.get(rand.nextInt(coupPossible.size()));
			} else {
				coup = rand.nextInt(7);
			}
		}
		jouer(JOUEUR, coup);
	}

	public static void main(String[] args) {
		jeu();

		SC.close();
	}
}
