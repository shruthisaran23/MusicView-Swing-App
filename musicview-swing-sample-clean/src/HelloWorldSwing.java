import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


public class HelloWorldSwing extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {

    private static int num_staves;
    public static MusicViewer musicViewer;
    private static String currentmode;
    private static JFrame frame;
    private static JMenuItem m22;
    private static JButton deleteStaff;
    private static JButton newStaff;
    private static JLabel statusbar;
    private static JButton select;
    private static JButton play;
    private static JButton stop;
    private static JRadioButton note;
    private static JRadioButton rest;
    private static JRadioButton flat;
    private static JRadioButton sharp;
    private static JButton pen;
    private static JSlider jSliderOne;

    public static void createAndShowGUI(){
        // Initialize number of staves
        num_staves = 4;
        //Initialize starting status
        String status = "Note Selected";

        //Initialize mode
        currentmode = "null";

        //CREATING MAIN FRAME
        frame = new JFrame("My Music Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 800));
        frame.pack();
        frame.setMinimumSize(new Dimension(500, 500));
        Border blackline = BorderFactory.createLineBorder(Color.black);

        //Creating Menu Bar and Menu Items (NORTH PANEL)
        JMenuBar menuBar = new JMenuBar();
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Edit");
        menuBar.add(m1);
        menuBar.add(m2);
        JMenuItem m11 = new JMenuItem("Exit");

        //exit functionality
        m11.addActionListener(new exitApp());

        //increment staff functionality
        JMenuItem m21 = new JMenuItem("New Staff");
        m21.addActionListener(new incrementStaff());

        //decrement staff functionality
        m22 = new JMenuItem("Delete Staff");
        m22.addActionListener(new decrementStaff());


        m1.add(m11);
        m2.add(m21);
        m2.add(m22);




        // Status Bar at the bottom that shows current control (SOUTH PANEL)
        JPanel statusbarPanel = new JPanel();
        statusbar = new JLabel(status, SwingConstants.CENTER);
        statusbarPanel.add(statusbar);
        statusbarPanel.setBorder(blackline);


        // Music Viewer (CENTER PANEL)
        musicViewer = new MusicViewer();
        musicViewer.addMouseListener(new symbolMouse());
        musicViewer.addMouseMotionListener(new movingMouse());


        JScrollPane scrollPane = new JScrollPane(musicViewer, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(1000, 1000));


        // Control Pane on the Left (WEST PANEL)
        JPanel controls = new JPanel();
        controls.setBorder(blackline);
        controls.setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = GridBagConstraints.RELATIVE;
        gbc1.anchor = GridBagConstraints.WEST;
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(3,2));
        controls.add(grid, gbc1);
        JPanel bottomHalf = new JPanel();
        bottomHalf.setLayout(new BoxLayout(bottomHalf, BoxLayout.Y_AXIS));
        JPanel leftBottom = new JPanel();
        leftBottom.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        JPanel rightBottom = new JPanel();
        bottomHalf.add(leftBottom);
        bottomHalf.add(rightBottom);
        controls.add(bottomHalf, gbc1);


        //set up buttons in top half
        select = new JButton("Select");
        select.addActionListener(new updateStatus());
        pen = new JButton("Pen");
        pen.addActionListener(new updateStatus());
        newStaff = new JButton("New Staff");
        newStaff.addActionListener(new incrementStaff());
        deleteStaff = new JButton("Delete Staff");
        deleteStaff.addActionListener(new decrementStaff());
        play = new JButton("Play");
        play.addActionListener(new updateStatus());
        stop = new JButton("Stop");
        stop.addActionListener(new updateStatus());

        //set sizes for buttons
        grid.setMaximumSize(new Dimension(200,100));
        grid.setMinimumSize(new Dimension(200,100));

        //add buttons
        grid.add(select);
        grid.add(pen);
        grid.add(newStaff);
        grid.add(deleteStaff);
        grid.add(play);
        grid.add(stop);
        grid.setBorder(new EmptyBorder(10, 3, 10, 3));

        // set up radio buttons
        note = new JRadioButton("Note");
        note.addActionListener(new updateStatus());
        rest = new JRadioButton("Rest");
        rest.addActionListener(new updateStatus());
        flat = new JRadioButton("Flat");
        flat.addActionListener(new updateStatus());
        sharp = new JRadioButton("Sharp");
        sharp.addActionListener(new updateStatus());
        ButtonGroup bg = new ButtonGroup();
        bg.add(note);
        bg.add(rest);
        bg.add(flat);
        bg.add(sharp);
        note.setSelected(true);
        leftBottom.add(note,gbc);
        leftBottom.add(rest,gbc);
        leftBottom.add(flat,gbc);
        leftBottom.add(sharp,gbc);


        // Set up slider
        JPanel slider = new JPanel();
        slider.setSize(40, 50);

        jSliderOne = new JSlider(JSlider.VERTICAL, 0, 4, 0);
        jSliderOne.setPaintLabels(true);
        jSliderOne.setSnapToTicks(true);

        Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
        table.put (0, new JLabel("Sixteenth"));
        table.put (1, new JLabel("Eight"));
        table.put (2, new JLabel("Quarter"));
        table.put (3, new JLabel("Half"));
        table.put (4, new JLabel("Whole"));
        jSliderOne.setLabelTable (table);
        slider.add(jSliderOne);
        rightBottom.add(slider);

        jSliderOne.addChangeListener(new slider() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (jSliderOne.getValue() == 0){
                    statusbar.setText("Sixteenth Selected");
                }
                else if (jSliderOne.getValue() == 1){
                    statusbar.setText("Eighth Selected");
                }
                if (jSliderOne.getValue() == 2){
                    statusbar.setText("Quarter Selected");
                }
                if (jSliderOne.getValue() == 3){
                    statusbar.setText("Half Selected");
                }
                if (jSliderOne.getValue() == 4){
                    statusbar.setText("Whole Selected");
                }

            }
        });

        //Adding all components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, statusbar);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.WEST, controls);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        scrollPane.addKeyListener(new delete());
        scrollPane.setFocusable(true);
        scrollPane.requestFocus();
        frame.setVisible(true);
    }

    //PAGE LISTENER METHODS//

    public void actionPerformed(ActionEvent e) {
        if("Exit".equals(e.getActionCommand())) {
            System.exit(0);
        }
        if("New Staff".equals(e.getActionCommand()) && num_staves > 1) {
            num_staves++;
            statusbar.setText("Created Staff");
            musicViewer.getSelectedSymbol().setSelected(false);
            musicViewer.NewStaff();
            musicViewer.revalidate();
            musicViewer.repaint();
            currentmode = "newStaff";

        }
        if("New Staff".equals(e.getActionCommand()) && num_staves == 1) {
            num_staves++;
            m22.setEnabled(true);
            statusbar.setText("Created Staff");
            musicViewer.getSelectedSymbol().setSelected(false);
            musicViewer.NewStaff();
            musicViewer.revalidate();
            musicViewer.repaint();
            currentmode = "newStaff";
        }

        if("Delete Staff".equals(e.getActionCommand()) && num_staves > 1) {
            num_staves--;
            statusbar.setText("Deleted Staff");
            musicViewer.getSelectedSymbol().setSelected(false);
            musicViewer.DeleteStaff();
            musicViewer.revalidate();
            musicViewer.repaint();
            currentmode = "delete";
        }
        if("Delete Staff".equals(e.getActionCommand()) && num_staves == 2) {
            num_staves--;
            m22.setEnabled(false);
            statusbar.setText("Deleted Staff");
            musicViewer.getSelectedSymbol().setSelected(false);
            musicViewer.DeleteStaff();
            musicViewer.revalidate();
            musicViewer.repaint();
            currentmode = "delete";
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    static class symbolMouse implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            if (currentmode == "select") {
                for (Staff staff: musicViewer.listOfStaves) {
                    for (Symbol symbol: staff.listofsymbols) {
                        if (symbol.isSelected) {
                            musicViewer.adjustSymbol(symbol);
                            musicViewer.repaint();
                            musicViewer.revalidate();
                            break;
                        }
                    }
                }
            } else {
                Symbol newsym = musicViewer.getNewSymbol();
                musicViewer.adjustSymbol(newsym);
                if (newsym.rest){
                    statusbar.setText("Rest Selected");
                } else if (newsym.note) {
                    statusbar.setText(newsym.getPitch());
                }
                musicViewer.repaint();
                musicViewer.revalidate();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (currentmode == "select") {
                Point p = e.getPoint();
                for (Staff staff: musicViewer.listOfStaves) {
                    for (Symbol symbol: staff.listofsymbols) {
                        int xOfSymbol = symbol.xcoor;
                        int yOfSymbol = symbol.ycoor;
                        if (p.x <= xOfSymbol + 10 && p.x >= xOfSymbol - 10 && p.y <= yOfSymbol + 10 && p.y >= yOfSymbol - 10) {
                            if (musicViewer.getSelectedSymbol() == null) {
                                musicViewer.setSelectedSymbol(symbol);
                                symbol.setSelected(true);
                            } else if (musicViewer.getSelectedSymbol() != symbol) {
                                musicViewer.getSelectedSymbol().setSelected(false);
                                musicViewer.setSelectedSymbol(symbol);
                                symbol.setSelected(true);
                            }
                            break;
                        }
                    }
                }
            } else{
                int duration = jSliderOne.getValue();
                boolean notetype = false;
                boolean resttype = false;
                boolean inDrag = false;
                if(note.isSelected()) {
                    notetype = true;
                }
                else if (rest.isSelected()) {
                    resttype = true;
                }

                Point p = e.getPoint();

                musicViewer.NewSymbol(p.x, p.y, duration, notetype, resttype, false, false);
                if (musicViewer.getNewSymbol().rest){
                    statusbar.setText("Rest Selected");
                } else if (musicViewer.getNewSymbol().note) {
                    statusbar.setText(musicViewer.getNewSymbol().getPitch());
                }
                musicViewer.repaint();
                musicViewer.revalidate();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }
    static class movingMouse implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            musicViewer.revalidate();
            musicViewer.repaint();
            if (currentmode == "select"){
                for (Staff staff: musicViewer.listOfStaves){
                    for (Symbol symbol: staff.listofsymbols) {
                        if (symbol.isSelected) {
                            symbol.setXcoor(e.getX());
                            symbol.setYcoor(e.getY());
                            musicViewer.revalidate();
                            musicViewer.repaint();
                            break;
                        }
                    }
                }
                musicViewer.revalidate();
                musicViewer.repaint();
            }else {
                for (Staff staff: musicViewer.listOfStaves){
                    for (Symbol symbol: staff.listofsymbols) {
                        if (symbol == musicViewer.newSymbol) {
                            symbol.setXcoor(e.getX());
                            symbol.setYcoor(e.getY());
                            musicViewer.revalidate();
                            musicViewer.repaint();
                            break;
                        }
                    }
                }
                musicViewer.revalidate();
                musicViewer.repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    static class delete extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
        }
        @Override
        public void keyPressed(KeyEvent e) {
        }
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            System.out.println("hi");
            System.out.println(key == KeyEvent.VK_BACK_SPACE);
            if ((key == KeyEvent.VK_DELETE || key == KeyEvent.VK_BACK_SPACE) && musicViewer.selectedSymbol != null) {
                musicViewer.deleteSymbol(musicViewer.selectedSymbol);
                musicViewer.repaint();
                musicViewer.revalidate();
                System.out.println("deleted symbol");
            }
        }
    }





    // to update status bar based on slider
    static class slider implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent e) {
            if (jSliderOne.getValue() == 0){
                statusbar.setText("Sixteenth Selected");
            }
            else if (jSliderOne.getValue() == 1){
                statusbar.setText("Eighth Selected");
            }
            if (jSliderOne.getValue() == 2){
                statusbar.setText("Quarter Selected");
            }
            if (jSliderOne.getValue() == 3){
                statusbar.setText("Half Selected");
            }
            if (jSliderOne.getValue() == 4){
                statusbar.setText("Whole Selected");
            }
        }
    }
    //to exit application
    static class exitApp implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
    // to increment staff value
    static class incrementStaff implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            if("New Staff".equals(e.getActionCommand()) && num_staves == 1) {
                num_staves++;
                statusbar.setText("Created Staff");
                if (musicViewer.getSelectedSymbol() != null) {
                    musicViewer.getSelectedSymbol().setSelected(false);
                }
                m22.setEnabled(true);
                deleteStaff.setEnabled(true);
                musicViewer.NewStaff();
                musicViewer.revalidate();
                musicViewer.repaint();
                currentmode = "newStaff";

            }
            else if("New Staff".equals(e.getActionCommand()) && num_staves > 1) {
                num_staves++;
                statusbar.setText("Created Staff");
                if (musicViewer.getSelectedSymbol() != null) {
                    musicViewer.getSelectedSymbol().setSelected(false);
                }
                musicViewer.NewStaff();
                musicViewer.revalidate();
                musicViewer.repaint();
                currentmode = "newStaff";

            }
        }
    }

    // to decrement staff value
    static class decrementStaff implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if("Delete Staff".equals(e.getActionCommand()) && num_staves == 2) {
                num_staves--;
                statusbar.setText("Deleted Staff");
                if (musicViewer.getSelectedSymbol() != null) {
                    musicViewer.getSelectedSymbol().setSelected(false);
                }
                m22.setEnabled(false);
                deleteStaff.setEnabled(false);
                musicViewer.DeleteStaff();
                musicViewer.revalidate();
                musicViewer.repaint();
                currentmode = "delete";
            }
            else if ("Delete Staff".equals(e.getActionCommand()) && num_staves > 2) {
                num_staves--;
                statusbar.setText("Deleted Staff");
                if (musicViewer.getSelectedSymbol() != null) {
                    musicViewer.getSelectedSymbol().setSelected(false);
                }
                musicViewer.DeleteStaff();
                musicViewer.revalidate();
                musicViewer.repaint();
                currentmode = "delete";
            }
        }
    }

    // to update status bar
    static class updateStatus implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if("Select".equals(e.getActionCommand())) {
                statusbar.setText("Select Mode");
                currentmode = "select";

            }
            else if ("Pen".equals(e.getActionCommand())) {
                statusbar.setText("Pen Mode");
                currentmode = "pen";
                if (musicViewer.getSelectedSymbol() != null) {
                    musicViewer.getSelectedSymbol().setSelected(false);
                }
            }
            else if ("Play".equals(e.getActionCommand())) {
                statusbar.setText("Playing Music");
                currentmode = "play";
                if (musicViewer.getSelectedSymbol() != null) {
                    musicViewer.getSelectedSymbol().setSelected(false);
                }
            }
            else if ("Stop".equals(e.getActionCommand())) {
                statusbar.setText("Paused Music");
                currentmode = "stop";
                if (musicViewer.getSelectedSymbol() != null) {
                    musicViewer.getSelectedSymbol().setSelected(false);
                }
            }
            else if ("Note".equals(e.getActionCommand())) {
                statusbar.setText("Note Selected");
                if (musicViewer.getSelectedSymbol() != null) {
                    musicViewer.getSelectedSymbol().setSelected(false);
                }
            }
            else if ("Rest".equals(e.getActionCommand())) {
                statusbar.setText("Rest Selected");
                if (musicViewer.getSelectedSymbol() != null) {
                    musicViewer.getSelectedSymbol().setSelected(false);
                }
            }
            else if ("Flat".equals(e.getActionCommand())) {
                statusbar.setText("Flat Selected");
                if (musicViewer.getSelectedSymbol() != null) {
                    musicViewer.getSelectedSymbol().setSelected(false);
                }
            }
            else if ("Sharp".equals(e.getActionCommand())) {
                statusbar.setText("Sharp Selected");
                if (musicViewer.getSelectedSymbol() != null) {
                    musicViewer.getSelectedSymbol().setSelected(false);
                }
            }
        }
    }


// main method to run application
    public static void main(String args[]) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }

}
