import java.util.ArrayList;


/**
 * The class <b>LightsOut</b> is the
 * class that implements the method to
 * computs solutions of the Lights Out game.
 * It contains the main of our application.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class LightsOut {

     /**
     * default width of the game.
     */
    public static final int DEFAULT_WIDTH = 3;
     /**
     * default height of the game.
     */
    public static final int DEFAULT_HEIGHT = 3;

    /**
     * The method <b>solve</b> finds all the 
     * solutions to the <b>Lights Out</b> game 
     * for an initially completely ``off'' board 
     * of size <b>widthxheight</b>, using a  
     * Breadth-First Search algorithm. 
     *
     * It returns an <b>ArrayList&lt;Solution&gt;</b> 
     * containing all the valid solutions to the 
     * problem.
     *
     * During the computation of the solution, the 
     * method prints out a message each time a new 
     * solution  is found, along with the total time 
     * it took (in milliseconds) to find that solution.
     *
     * @param width
     *  the width of the board
     * @param height
     *  the height of the board
     * @return
     *  an instance of <b>ArrayList&lt;Solution&gt;</b>
     * containing all the solutions
     */
    public static ArrayList<Solution> solve(int width, int height){

        Queue<Solution> q  = new QueueImplementation<Solution>();
        ArrayList<Solution> solutions  = new ArrayList<Solution>();
        Solution a = new Solution(width,height);
        q.enqueue(a);

        long start = System.currentTimeMillis();
        while(!q.isEmpty()){
            Solution s  = q.dequeue();
            if(s.isReady()) {
                if(s.isSuccessful()) {
                    //System.out.println("Arraylist not full");
                    System.out.println("Solution found in " + (System.currentTimeMillis()-start) + " ms" );
                    solutions.add(s);
                }
            } else {
                Solution s2 = new Solution(s);
                s.setNext(true);
                q.enqueue(s);
                s2.setNext(false);
                q.enqueue(s2);
            }
        }
        return solutions;
    }

    /**
     * <b>main</b> method  calls the method <b>solve</b> 
     * and then prints out the number of solutions found,
     * as well as the details of each solution.
     *
     * The <b>width</b> and <b>height</b> used by the 
     * main are passed as runtime parameters to
     * the program. If no runtime parameters are passed 
     * to the program, or if the parameters are incorrect,
     * then the default values are used.
     *
     * @param args
     *  Strings array of runtime parameters
     */
    public static void main(String[] args) {


        int width   = DEFAULT_WIDTH;
        int height  = DEFAULT_HEIGHT;

        StudentInfo.display();

        if (args.length == 2) {

            try{
                width = Integer.parseInt(args[0]);
                if(width < 1) {
                    System.out.println("Invalid width, using default...");
                    width   = DEFAULT_WIDTH;
                }
                height = Integer.parseInt(args[1]);
                if(height < 1) {
                    System.out.println("Invalid height, using default...");
                    height  = DEFAULT_HEIGHT;
                }
                
            } catch(NumberFormatException e){
                System.out.println("Invalid argument, using default...");
                width   = DEFAULT_WIDTH;
                height  = DEFAULT_HEIGHT;
            }
        }
        ArrayList<Solution> results   = solve(width,height);
        for(int i =0; i < results.size(); i++){

            System.out.println("****");
            System.out.println(results.get(i));

        }
        System.out.println("In a board of "+ width + "x" + height +": " + results.size() + " solution" + (results.size() > 1 ? "s." : "."));
    }

    /**
     * The class method solve finds all the solutions to the Lights Out game for a board in 
     * the state specified by the GameModel instance model, using a Breadth-First Search 
     * algorithm. It returns an ArrayList containing all the valid solutions to the problem.
     */
    public static ArrayList<Solution> solve(GameModel model){

        int height = model.getHeight();
        int width = model.getWidth();

        Queue<Solution> q  = new QueueImplementation<Solution>();
        ArrayList<Solution> solutions  = new ArrayList<Solution>();
        Solution a = new Solution(width,height);
        q.enqueue(a);

        long start = System.currentTimeMillis();
        while(!q.isEmpty()){
            Solution s  = q.dequeue();
            if(s.isReady()) {
                if(s.isSuccessful(model)) {
                    //System.out.println("Arraylist not full");
                    System.out.println("Solution found in " + (System.currentTimeMillis()-start) + " ms" );
                    solutions.add(s);
                }
            } else {
                Solution s2 = new Solution(s);
                if (s.stillPossible(true, model)){
                    s.setNext(true);
                    s.finish(model);
                    q.enqueue(s);
                }
                if (s2.stillPossible(false, model)){
                    s2.setNext(false);
                    s2.finish(model);
                    q.enqueue(s2);
                }
            }
        }
        return solutions;
            
    }

    // private static void FixSolution(Solution solution, GameModel model){

    //     for (int i = 0; i<model.getHeight(); i++){
    //         for (int j = 0; j<model.getWidth(); j++){

    //             if (model.isON(i,j) && solution.get(i,j)==false){
    //                 solution.set(i,j,true);
    //             }
    //             else if (model.isON(i,j) && solution.get(i,j) == true){
    //                 solution.set(i,j,false);
    //             }
    //         }
    //     }
    // }
    
    /**
     * The class method solveShortest returns a reference to a minimum size solution to the Lights Out 
     * game for a board in the state specified by the GameModel instance model. Note that there 
     * could be more than one such minimum-size solution. The method can return a 
     * reference to any one of them.
     */
    static Solution solveShortest(GameModel model){

        ArrayList<Solution> solutions = solve(model);

        if (solutions.size() == 0){
            return null;
        }

        else{

            Solution minimum = solutions.get(0);

            for (int i = 1; i<solutions.size(); i++){

                if (solutions.get(i).getSize()<minimum.getSize()){
                    minimum = solutions.get(i);
                }

            }

            return minimum;

        }
    }
}