package labyrinthe;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class MazeAStarSearch extends javax.swing.JFrame {
    JPanel jPanel1 = new JPanel();
    AStarSearchEngine currentSearchEngine = null;

    public MazeAStarSearch(int height, int width) {
        try {
          jbInit();
        } catch (Exception e) {
          System.out.println("GUI initilization error: " + e);
        }
        currentSearchEngine = new AStarSearchEngine(height,width);
        repaint();
    }

    public void paint(Graphics g_unused) {
        if (currentSearchEngine == null) return;
        Maze maze = currentSearchEngine.getMaze();
        int width = maze.getWidth();
        int height = maze.getHeight();
        System.out.println("Size of current maze: " + width + " by " + height);
        Graphics g = jPanel1.getGraphics();
        BufferedImage image = new BufferedImage(720, 720, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = image.getGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0, 0, 720, 720);
        g2.setColor(Color.black);
        maze.setValue(0, 0, Maze.START_LOC_VALUE);
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                short val = maze.getValue(x,y);
                
                if ( val == Maze.OBSTICLE) {
                    g2.setColor(Color.lightGray);
                    g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
                    g2.setColor(Color.black);
                	g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else if (val == Maze.START_LOC_VALUE) {
                    g2.setColor(Color.blue);
                    g2.drawString("S", 16 + x * 29, 19 + y * 29);
                    g2.setColor(Color.black);
                	g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else if (val == Maze.GOAL_LOC_VALUE) {
                    g2.setColor(Color.red);
                    g2.drawString("G", 16 + x * 29, 19 + y * 29);
                    g2.setColor(Color.black);
                	g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else {
                	g2.setColor(Color.black);
                	g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                }
            }
        }
        // redraw the path in black:
        g2.setColor(Color.red);
        Dimension [] path = currentSearchEngine.getPath();
        for (int i=1; i< (path.length-1); i++) {
          int x = path[i].width;
          int y = path[i].height;
          short val = maze.getValue(x,y);
          /*g2.drawString("" + (path.length - i), 16 + x * 29, 19 + y * 29);
          g2.setColor(Color.red);
      	  g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);*/
          g2.setColor(Color.green);
          g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
          g2.setColor(Color.green);
      	g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
      	
        }
        g.drawImage(image, 60, 80, 720, 720, null);

    }

    public static void main(String[] args) {
    	MazeAStarSearch mazeSearch1 = new MazeAStarSearch(20,20);
    	

    }

    private void jbInit() throws Exception {

        this.setContentPane(jPanel1);
        this.setCursor(null);
        this.setDefaultCloseOperation(3);
        this.setTitle("MazeAStarSearch");
        this.getContentPane().setLayout(null);
        jPanel1.setBackground(Color.white);
        jPanel1.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
        jPanel1.setDoubleBuffered(false);
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(null);
        this.setSize(720, 720);
        this.setVisible(true);
    }
}