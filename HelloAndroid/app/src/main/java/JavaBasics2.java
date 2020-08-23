public class JavaBasics2 {
    public static void main(String args[]){
        int array[][] = new int[3][3];
        int a = 100;

        for(int i = 0;i < 3;i++){
            for(int j = 0;j < 3;j++) {
                array[i][j] = a;
                System.out.print(array[i][j]+" ");
                a += 10;
            }
            System.out.println();
        }

    }
}
