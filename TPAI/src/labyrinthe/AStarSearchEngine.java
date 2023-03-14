package labyrinthe;

import java.awt.Dimension;
import java.util.PriorityQueue;

public class AStarSearchEngine extends AbstractSearchEngine{
	PriorityQueue<Case> closedList = new PriorityQueue<>();
    PriorityQueue<Case> openList = new PriorityQueue<>();
    Dimension predecessor[][];
	public AStarSearchEngine(int width, int height) {
		super(width, height);
		predecessor = new Dimension[width][height];  
        for (int i=0; i<width; i++) 
            for (int j=0; j<height; j++) 
                predecessor[i][j] = null; 
        double h1=Math.sqrt( Math.pow(startLoc.getWidth()-goalLoc.getWidth(),2) + Math.pow(startLoc.getHeight()-goalLoc.getHeight(), 2));

		java.util.Random rnd = new java.util.Random();
        rnd.setSeed((int)1022);
		Case c= new Case (startLoc, 0, h1);
        openList.add(c);
        doAstar();
        maxDepth = 0;
        if (!isSearching) {
            searchPath[maxDepth++] = goalLoc;
            for (int i=0; i<height*width; i++) {
                searchPath[maxDepth] = predecessor[searchPath[maxDepth - 1].width][searchPath[maxDepth - 1].height];
                maxDepth++;
                if (equals(searchPath[maxDepth - 1], startLoc))  break;  // back to starting node
            }
        }
	}
	
	private void doAstar() {
		Case n;
		double h1;
		while(!openList.isEmpty()){
	         n = openList.poll();
		    closedList.add(n);
		    currentLoc=new Dimension(n.width , n.height);
		    if(goalLoc.equals(currentLoc)){
                System.out.println("But trouvé : (" + currentLoc.width +", " + currentLoc.height+")");
                isSearching = false;
                break;
            }
            else{              
               Dimension [] connected = getPossibleMoves(currentLoc);
               for (int i=0; i<8; i++) {            	   if( connected[i]!=null && !openList.contains(connected[i]) && !closedList.contains(connected[i])) {
            	       h1=Math.sqrt( Math.pow(connected[i].getWidth()-goalLoc.getWidth(),2) + Math.pow(connected[i].getHeight()-goalLoc.getHeight(), 2));
                       predecessor[connected[i].width][connected[i].height] = currentLoc;
                       openList.add( new Case ( connected[i],predecessor.length , predecessor.length + h1));
                   }
               }
            }
		    
		}
	}
}
