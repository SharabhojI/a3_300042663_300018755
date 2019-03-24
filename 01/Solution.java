
/**
 * The class <b>Solution</b> is used
 * to store a (partial) solution to the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class Solution {


    /**
     * our board. board[i][j] is true is in this
     * solution, the cell (j,i) is tapped
     */
    private boolean[][] board;

    /**
     *  width of the game
     */
    private int width;

    /**
     * height of the game
     */
    private int height;
    
    /**
     * how far along have we constructed that solution.
     * values range between 0 and height*width-1
     */
    private int currentIndex;

    private int row; 
    private int col;

    /**
     * Constructor. Creates an instance of Solution 
     * for a board of size <b>heightxwidth</b>. That 
     * solution does not have any board position
     * value explicitly specified yet.
     *
     * @param width
     *  the width of the board
     * @param height
     *  the height of the board
     */
    public Solution(int width, int height) {

        this.width = width;
        this.height = height;
        this.row = 0;
        this.col = 0;

        board = new boolean[height][width];
        currentIndex = 0;

    }

   /**
     * Constructor. Creates an instance of Solution 
     * wich is a deep copy of the instance received
     * as parameter. 
     *
     * @param other
     *  Instance of solution to deep-copy
     */
     public Solution(Solution other) {

        this.width = other.width;
        this.height = other.height;
        this.currentIndex = other.currentIndex;
        this.col = other.col;
        this.row = other.row;

        board = new boolean[height][width];

        for(int i = 0; i < currentIndex; i++){
            board[i/width][i%width] = other.board[i/width][i%width];
        } 

    }


    /**
     * returns <b>true</b> if and only the parameter 
     * <b>other</b> is referencing an instance of a 
     * Solution which is the ``same'' as  this 
     * instance of Solution (its board as the same
     * values and it is completed to the same degree)
     *
     * @param other
     *  referenced object to compare
     */

    public boolean equals(Object other){

        if(other == null) {
            return false;
        }
        if(this.getClass() != other.getClass()) {
            return false;
        }

        Solution otherSolution = (Solution) other;

        if(width != otherSolution.width ||
            height != otherSolution.height ||
            currentIndex != otherSolution.currentIndex) {
            return false;
        }

        for(int i = 0; i < height ; i++){
            for(int j = 0; j < width; j++) {
                if(board[i][j] != otherSolution.board[i][j]){
                    return false;
                }
            }
        }

        return true;

    }


    /** 
    * returns <b>true</b> if the solution 
    * has been entirely specified
    *
    * @return
    * true if the solution is fully specified
    */
    public boolean isReady(){
        return currentIndex == width*height;
    }

    /** 
    * specifies the ``next'' value of the 
    * solution. 
    * The first call to setNext specifies 
    * the value of the board location (1,1), 
    * the second call specifies the value
    *  of the board location (1,2) etc. 
    *
    * If <b>setNext</b> is called more times 
    * than there are positions on the board, 
    * an error message is printed out and the 
    * call is ignored.
    *
    * @param nextValue
    *  the boolean value of the next position
    *  of the solution
    */
    public void setNext(boolean nextValue) {

        if(currentIndex >= width*height) {
            System.out.println("Board already full");
            return;
        }
        board[currentIndex/width][currentIndex%width] = nextValue;
        currentIndex++;
    }
    
    /**
    * returns <b>true</b> if the solution is completely 
    * specified and is indeed working, that is, if it 
    * will bring a board of the specified dimensions 
    * from being  entirely ``off'' to being  entirely 
    * ``on''.
    *
    * @return
    *  true if the solution is completely specified
    * and works
    */
    public boolean isSuccessful(){

        if(currentIndex < width*height) {
            System.out.println("Board incomplete");
            return false;
        }

        for(int i=0; i<height; i++){
            for(int j = 0; j < width; j++) {
                if(!oddNeighborhood(i,j)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
    * this method ensure that add <b>nextValue</b> at the
    * currentIndex does not make the current solution
    * impossible. It assumes that the Solution was
    * built with a series of setNext on which 
    * stillPossible was always true.
    * @param nextValue
    *         The boolean value to add at currentIndex
    * @return true if the board is not known to be
    * impossible (which does not mean that the board
    * is possible!)
    */
    public boolean stillPossible(boolean nextValue) {

        if(currentIndex >= width*height) {
            System.out.println("Board already full");
            return false;
        }

        int i = currentIndex/width;
        int j = currentIndex%width;
        boolean before = board[i][j];
        boolean possible = true;

        board[i][j] = nextValue;
        
        if((i > 0) && (!oddNeighborhood(i-1,j))){
            possible = false;
        }
        if(possible && (i == (height-1))) {
            if((j > 0) && (!oddNeighborhood(i,j-1))){
                possible = false;
            }
            if(possible && (j == (width-1))&& (!oddNeighborhood(i,j))){
                possible = false;            
            }
        }
        board[i][j] = before;
        return possible;
    }


    /**
    * this method attempts to finish the board. 
    * It assumes that the Solution was
    * built with a series of setNext on which 
    * stillPossible was always true. It cannot
    * be called if the board can be extended 
    * with both true and false and still be 
    * possible.
    *
    * @return true if the board can be finished.
    * the board is also completed
    */
    public boolean finish(){


        int i = currentIndex/width;
        int j = currentIndex%width;
        
/*
        if(i == 0 && height > 1) {
            System.out.println("First line incomplete, can't proceed");
            return false;
        }
*/

        while(currentIndex < height*width) {
            if(i < height - 1 ) {
                setNext(!oddNeighborhood(i-1,j));
                i = currentIndex/width;
                j = currentIndex%width;
            } else { //last raw
                if(j == 0){
                    setNext(!oddNeighborhood(i-1,j));
                } else {
                   if((height > 1) && oddNeighborhood(i-1,j) != oddNeighborhood(i,j-1)){
                     return false;
                   }
                   setNext(!oddNeighborhood(i,j-1));
                } 
                i = currentIndex/width;
                j = currentIndex%width;
            }
        }
        if(!oddNeighborhood(height-1,width-1)){
            return false;
        }
        // here we should return true because we could
        // successfully finish the board. However, as a
        // precaution, if someone called the method on
        // a board that was unfinishable before calling
        // the method, we do a complete check
        
        if(!isSuccessful()) {
            System.out.println("Warning, method called incorrectly");
            return false;
        }
       
        return true;

    }


    /**
     * checks if board[i][j] and its neighborhood
     * have an odd number of values ''true''
     */

    private boolean oddNeighborhood(int i, int j) {
        
        int total = 0;
        if(board[i][j]){
            total++;
        }
        if((i > 0) && (board[i-1][j])) {
            total++;
        }
        if((i < height -1 ) && (board[i+1][j])) {
            total++;
        }
        if((j > 0) && (board[i][j-1])) {
            total++;
        }
        if((j < (width - 1)) && (board[i][j+1])) {
            total++;
        }
        return (total%2)== 1 ;                
    }

    /**
     * returns a string representation of the solution
     *
     * @return
     *      the string representation
     */
    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append("[");
        for(int i = 0; i < height; i++){
            out.append("[");
            for(int j = 0; j < width ; j++) {
                if (j>0) {
                    out.append(",");
                }
                out.append(board[i][j]);
            }
            out.append("]"+(i < height -1 ? ",\n" :""));
        }
        out.append("]");
        return out.toString();
    }

    /**
     * This method returns false if the current solution would be impossible 
     * finalize into a working solution for a board in the state specified by 
     * the GameModel instance model, should it be extended from its current 
     * state with the value nextvalue.
     */
    public boolean stillPossible(boolean nextValue, GameModel model){

        row = currentIndex/width;
        col = currentIndex%width;

        boolean[][] newBoard = new boolean[model.getHeight()][model.getWidth()];

        for (int i = 0; i<model.getHeight(); i++){
            for (int j = 0; j<model.getWidth(); j++){

                newBoard[i][j] = model.isON(i,j);
            }
        }
        for (int i = 0; i<this.height; i++){
            for (int j = 0; j<this.width; j++){

                boolean step = this.board[i][j];

                if (step == true){
                    newBoard[i][j] = !newBoard[i][j];

                    if (isValid(i+1,j)){
                        newBoard[i+1][j] = !newBoard[i+1][j];
                    }
                    if (isValid(i-1,j)){
                        newBoard[i-1][j] = !newBoard[i-1][j];
                    }
                    if (isValid(i,j+1)){
                        newBoard[i][j+1] = !newBoard[i][j+1];
                    }
                    if (isValid(i,j-1)){
                        newBoard[i][j-1] = !newBoard[i][j-1];
                    }

                }

            }
        }

        if (row >= board.length || col >= board[0].length) return false;
        if (row == 0 || row-1 == 0) return true;
        
        for(int i = 0; i < board[0].length; i++) {
            if (newBoard[row-2][i] == false) return false;
        }
        return true;
    }

    /**
     * This method assumes that the solution is currently still extendable 
     * for a board in the state specified by the GameModel instance model, 
     * but only one way. It keeps extending that solution with the one 
     * correct way that it finds at each step, until the solution is 
     * complete and correct for a board in the state specified by the 
     * GameModel instance model, or shown to not be work. It returns true 
     * if and only if the solution is extended into a complete, working 
     * solution.
     */
    public boolean finish(GameModel model){
       
       boolean stillPossibleWithTrue = stillPossible(true,model);
       boolean stillPossibleWithFalse = stillPossible(false,model);

       if (stillPossibleWithFalse == true && stillPossibleWithTrue == true){
        return false;
       }

       while (currentIndex<model.getHeight()*model.getWidth()){
            if (stillPossibleWithTrue == true){
                setNext(true);
            }

            else if (stillPossibleWithFalse == true){
                setNext(false);
            }

            else{
                return false;
            }
            stillPossibleWithTrue = stillPossible(true,model);
            stillPossibleWithFalse = stillPossible(false,model);

       }
       if (isSuccessful(model)){
        return true;
       }
       else{
        return false;
       }
    }

    /**
     * this method returns true if the solution is completely specified and 
     * is indeed working, that is, if it will bring a board of the specified 
     * dimensions from the state specified by the GameModel instance model 
     * to being entirely on.
     */
    public boolean isSuccessful(GameModel model){
    	if(currentIndex < model.getWidth()*model.getHeight()){
            System.out.println("Board incomplete");
            return false;
        }

        boolean[][] newBoard = new boolean[model.getHeight()][model.getWidth()];

        for (int i = 0; i<model.getHeight(); i++){
            for (int j = 0; j<model.getWidth(); j++){

                newBoard[i][j] = model.isON(i,j);
            }
        }

        for (int i = 0; i<this.height; i++){
            for (int j = 0; j<this.width; j++){

                boolean step = this.board[i][j];

                if (step == true){
                    newBoard[i][j] = !newBoard[i][j];

                    if (isValid(i+1,j)){
                        newBoard[i+1][j] = !newBoard[i+1][j];
                    }
                    if (isValid(i-1,j)){
                        newBoard[i-1][j] = !newBoard[i-1][j];
                    }
                    if (isValid(i,j+1)){
                        newBoard[i][j+1] = !newBoard[i][j+1];
                    }
                    if (isValid(i,j-1)){
                        newBoard[i][j-1] = !newBoard[i][j-1];
                    }

                }

            }
        }

        for (int i = 0; i<this.height; i++){
            for (int j = 0; j<this.width; j++){
                if(newBoard[i][j] == false){
                    return false;
                }
            }
        }

        return true;

    }

    private boolean isValid(int i,int j){

        if (i<0 || i>=this.height || j<0 || j>=this.width){
            return false;
        }

        return true;
    }

    /**
     * Returns the size of the solution, that is, the number of positions 
     * that must be tapped. 
     */
    public int getSize(){

        int truCtr = 0;

        for (int i = 0; i<height; i++){
            for (int j  = 0; j<width; j++){
                if (board[i][j] == true){
                    truCtr++;
                }
            }
        }
    return truCtr;
    }
}
