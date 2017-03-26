package testsGraph;

import java.io.*;
import java.lang.invoke.SwitchPoint;
import java.util.*;
import java.lang.*;

/**
 * Created by nikitashanker on 3/9/17.
 */
public class graphQuery {

    //get input string

    // call the relevant class based on the first line of query
    //
    public static void main(String [] argvs) {

        try{
            String query = "";

            while(!query.equals("EXIT"))
            {
                System.out.println("\n Please Enter your Query or type EXIT if done: ");

                Scanner s = new Scanner(System.in);

                query = s.nextLine();

                String[] args = query.split(" ");

                switch(args[0]){
                    case "batchnodeinsert":
                        BatchNodeInsert.main(args);
                        break;
                    case "batchedgeinsert":
                        BatchEdgeInsert.main(args);
                        break;
                    case "batchnodedelete":
                        BatchNodeDelete.main(args);
                        break;
                    case "batchedgedelete":
                        BatchEdgeDelete.main(args);
                        break;
                    case "nodequery":
                        nodequery.main(args);
                        break;
                    case "edgequery":
                        edgequery.main(args);
                        break;
                    case "EXIT":
                        break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println ("Error encountered during Graph Database tests:\n");
            Runtime.getRuntime().exit(1);
        }
    }
}
