public class tests {
    public static void print_table_color(int[][] tab, int col, int row){
        int rows = tab.length;                                          // nombre de lignes
        int cols = tab[0].length;                                       // nombre de colonnes
        String[] colors = {"\033[0;37m", "\033[0;31m", "\033[0;34m"};   // codes couleurs
        String[] colors_u = {"\033[4;37m", "\033[4;31m", "\033[4;34m"}; // codes couleurs underline
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if((i == row) && (j == col)) {
                    System.out.print(colors_u[tab[i][j]] + "X");        // affiche le point en argument (underline)
                }
                else System.out.print(colors[tab[i][j]] + "X");
                System.out.print("\033[0;37m" + "  ");                  // remet la couleur par default
            }
            System.out.println();
        }
    }

    public static int check_row(int[][] tab, int col, int row, int joueur, int puissance){ // puissance peut remplece par 4
        int len = tab[row].length;                          // nombre de colonnes
        int same = 0;

        int start = (col - (puissance - 1));                // point de depart
        int end = (col + (puissance - 1));                  // point d'arrivee

        if(start < 0) {
            start = 0;                                      // pour eviter les erreurs
        }
        if(end >= len) {
            end = (len - 1);                                // pour eviter les erreurs
        }
        for(int i = start; i <= end; i++){
            if(tab[row][i] == joueur){
                same++;
            }
            else same = 0;
            if(same >= puissance){
                return joueur;                              // true serait peut etre plus adapte
            }
        }
        System.out.println();
        return 0;
    }

    public static int check_col(int[][] tab, int col, int row, int joueur, int puissance){ // puissance -> 4
        int len = tab.length;
        int same = 0;

        int start = (row - (puissance - 1));
        int end = (row + (puissance - 1));

        if(start < 0) {
            start = 0;
        }
        if(end >= len) {
            end = (len - 1);
        }

        for(int i = start; i < end; i++){
            if(tab[i][col] == joueur){
                same++;
            }
            else same = 0;
            if(same >= puissance){
                return joueur;
            }
        }
        return 0;
    }

    public static int check_diags(int[][] tab, int col, int row, int joueur, int puissance){
        int len_x = tab[0].length;
        int len_y = tab.length;
        int debut_x = col;
        int debut_y = row;
        int j_i = joueur;

        while((debut_x > 0) && (debut_y > 0) && (tab[debut_y][debut_x] == j_i)){
            debut_x--;
            debut_y--;
        }

        int x = debut_x;
        int y = debut_y;
        j_i = tab[y][x];
        x++;
        y++;
        int same = 1;

        while((x < len_x) && (y < len_y)){
            if(j_i == tab[y][x]){
                same++;
            }
            else return 0;
            if(same >= puissance) return j_i;
            j_i = tab[y][x];
            x++;
            y++;
        }

        debut_x = col;
        debut_y = row;
        j_i = tab[row][col];

        while((debut_x > 0) && (debut_y < (len_y - 1)) && (tab[debut_y][debut_x] == j_i)){
            debut_x--;
            debut_y++;
        }

        x = debut_x;
        y = debut_y;
        x++;
        y--;
        same = 1;

        while((x < len_x) && (y > 0)){
            if(j_i == tab[y][x]){
                same++;
            }
            else return 0;
            if(same >= puissance) return j_i;
            j_i = tab[y][x];
            x++;
            y--;
        }

        return 0;
    }
}
