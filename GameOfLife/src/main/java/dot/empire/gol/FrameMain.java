package dot.empire.gol;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Main Frame. Created by Matthew 'siD' Van der Bijl at 09 Apr 2018 5:04:01 PM.
 *
 * @author Matthew 'siD' Van der Bijl
 */
public class FrameMain extends JFrame {

    /**
     * List of events to run at the end of the simulation.
     */
    private final LinkedList<Runnable> events;

    private int size = 46;
    /**
     * Cells.
     */
    private JButton[][] arr;

    /**
     * Creates new form Main Frame.
     *
     * @param parent Parent frame
     */
    public FrameMain(Component parent) {
        super("~ Conway's Game of Life ~");
        this.initComponents();

        this.events = new LinkedList<>();

        super.setLocationRelativeTo(parent); // position main frame

        // --
        this.arr = new JButton[size][size];
        this.pnlMain.setLayout(new GridLayout(size, 0));
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                JButton btn = new JButton();
                this.arr[x][y] = btn;
                this.pnlMain.add(btn);
            }
        }

        this.create();

        // Runs the simulation
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 0, (long) (0.25 * 1000));
    }

    private void create() {
        Random rand = new Random();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                arr[x][y].setEnabled(rand.nextInt(10) == 0);
            }
        }
    }

    /**
     *
     * @param x position of the cell on the X-axis
     * @param y position of the cell on the Y-axis
     *
     * @return true of the block is alone
     */
    private boolean isAlone(int x, int y) {
        return getNumFriends(x, y) < 2;
    }

    private boolean isCrowded(int x, int y) {
        return getNumFriends(x, y) > 3;
    }

    /**
     * Gets the numbers of alive cells surround a given block
     *
     * @param x position of the cell on the X-axis
     * @param y position of the cell on the Y-axis
     *
     * @return the number of neighbours of the block
     */
    private int getNumFriends(int x, int y) {
        int num = 0;

        //<editor-fold defaultstate="collapsed" desc="Cardinal">
        try {
            if (arr[x + 1][y].isEnabled()) {
                num++;
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
        try {
            if (arr[x - 1][y].isEnabled()) {
                num++;
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
        try {
            if (arr[x][y + 1].isEnabled()) {
                num++;
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
        try {
            if (arr[x][y - 1].isEnabled()) {
                num++;
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="comment">
        try {
            if (arr[x + 1][y + 1].isEnabled()) {
                num++;
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
        try {
            if (arr[x - 1][y + 1].isEnabled()) {
                num++;
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
        try {
            if (arr[x + 1][y - 1].isEnabled()) {
                num++;
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
        try {
            if (arr[x - 1][y - 1].isEnabled()) {
                num++;
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
        //</editor-fold>
        return num;
    }

    /**
     * Checks if a given block is alive or not.
     *
     * @param x position of the cell on the X-axis
     * @param y position of the cell on the Y-axis
     *
     * @return true if the block is alive
     */
    private boolean isAlive(int x, int y) {
        return this.arr[x][y].isEnabled();
    }

    /**
     * Kills a cell at a given position.
     *
     * @param x position of the cell on the X-axis
     * @param y position of the cell on the Y-axis
     */
    private void die(int x, int y) {
        this.arr[x][y].setEnabled(false);
    }

    /**
     * Used to update the simulation.
     */
    private void update() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (isAlive(x, y)) {
                    if (isAlone(x, y) || isCrowded(x, y)) {
                        final int xPos = x;
                        final int yPos = y;

                        events.add(() -> {
                            die(xPos, yPos);
                        });
                    }
                } else if (getNumFriends(x, y) == 3) {

                    final int xPos = x;
                    final int yPos = y;

                    events.add(() -> {
                        arr[xPos][yPos].setEnabled(true);
                    });
                }
            }
        }

        for (Runnable evt : events) {
            evt.run();
        }
        events.clear();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * <strong>WARNING: Do NOT modify this code.</strong> The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        pnlMain = new javax.swing.JPanel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu = new javax.swing.JMenu();
        menuItemRecreate = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1598, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 877, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(pnlMain);

        jMenu.setText("File");

        menuItemRecreate.setText("Recreate");
        menuItemRecreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRecreateActionPerformed(evt);
            }
        });
        jMenu.add(menuItemRecreate);

        jMenuBar.add(jMenu);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemRecreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRecreateActionPerformed
        this.create();
    }//GEN-LAST:event_menuItemRecreateActionPerformed

    //<editor-fold defaultstate="collapsed" desc="Generated Variables">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem menuItemRecreate;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
