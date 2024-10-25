import java.util.*;

public class DistanceVector{
    static int[][][] tables;
    static int[][] weight;
    static int n;
    public static void train(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(tables[i][j][0]!=0)
                for(int k=0;k<n;k++){
                    if(weight[i][k]!=-1){
                      
                        if(tables[k][j][0]<Integer.MAX_VALUE && tables[i][j][0]>(tables[k][j][0]+weight[i][k])){
                            tables[i][j][0] = tables[k][j][0]+weight[i][k];
                            tables[i][j][1] = k;
                        }
                    }
                }
            }
        }
    }

    public static void showTables(){
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(j+":"+tables[i][j][0]+":"+tables[i][j][1]+" ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }
    public static void main(String args[]){

        Scanner take = new Scanner(System.in);
        System.out.print("Enter number of Nodes: ");
        n=take.nextInt();
        tables = new int[n][n][2];
        weight = new int[n][n];
        
        System.out.println("Enter the weights of nodes:");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(i+"->"+j+" ");
                weight[i][j]=take.nextInt();
            }
        }
       
        //Initializing the tables
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(weight[i][j]!=-1){
                    tables[i][j][0]=weight[i][j];
                    tables[i][j][1]=j;
                }
                else{
                    tables[i][j][0]=Integer.MAX_VALUE;
                    tables[i][j][1]=-1;
                }
            }
        }
        showTables();
        System.out.print("Enter number of iterations: ");
        int t = take.nextInt();
        for(int i=0;i<t;i++){
            System.out.println("After Iteration: "+(i+1));
            train();
            showTables();

        }


    }
}