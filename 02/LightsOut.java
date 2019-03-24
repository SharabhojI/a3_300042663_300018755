import java.util.ArrayList;


/**
 * The class <b>LightsOut</b> launches the game
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
    public static final int DEFAULT_HEIGTH = 3;


    // COMPLETE THE CLASS HERE (AS PER Q1)


    
   /**
     * <b>main</b> of the application. Creates the instance of  GameController 
     * and starts the game. If two parameters width and height
     * are passed, they are used. 
     * Otherwise, a default value is used. Defaults values are also
     * used if the paramters are too small (less than 1).
     * 
     * @param args
     *            command line parameters
     */
     public static void main(String[] args) {
        int width   = DEFAULT_WIDTH;
        int height  = DEFAULT_HEIGTH;
 
        StudentInfo.display();

        if (args.length == 2) {
            try{
                width = Integer.parseInt(args[0]);
                if(width<1){
                    System.out.println("Invalid argument, using default...");
                    width = DEFAULT_WIDTH;
                }
                height = Integer.parseInt(args[1]);
                if(height<1){
                    System.out.println("Invalid argument, using default...");
                    height = DEFAULT_HEIGTH;
                }
            } catch(NumberFormatException e){
                System.out.println("Invalid argument, using default...");
                width   = DEFAULT_WIDTH;
                height  = DEFAULT_HEIGTH;
            }
        }
        GameController game = new GameController(width, height);
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
