import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * 简单的深度搜索
 * 找出院子里连通水塘的个数
 * {@link} https://vjudge.net/problem/POJ-2386
 */
public class poj2386 {
    
    private static void dfs(boolean[][] b, int x, int y) {
        b[x][y] = false;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx;
                int ny = y + dy;
                if (0 <= nx && nx < b.length && 0 <= ny && b[0].length > ny && b[nx][ny]) {
                    dfs(b, nx, ny);
                }
            }
        }
        return;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        // Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("poj2386.in"));
        int row = in.nextInt();
        int col = in.nextInt();
        boolean[][] johnField = new boolean[row][col];
        while (in.hasNext()) {
            for (int i = 0; i < row; i++) {
                String inputStr = in.next();
                for (int j = 0; j < col; j++) {
                    johnField[i][j] = inputStr.charAt(j) == 'W';
                }
            }
        }
        int depth = 0;
        for(int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if(johnField[i][j]){
                    dfs(johnField, i, j);
                    depth ++;
                }
            }
        }
        System.out.print(depth);
        in.close();
    }
}