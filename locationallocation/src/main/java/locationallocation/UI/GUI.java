package locationallocation.UI;

import java.io.File;

import locationallocation.Domain.Pmedian;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A simple GUI for the application.
 */

// A lot is borrowed from here:
// https://github.com/yussiv/Compress/blob/master/squash/src/main/java/fi/yussiv/squash/ui/GUI.java


public class GUI extends JFrame  {

    /**
     * Class containing application logic.
     */
    private Pmedian app;

    /**
     * Holds status of GUI.
     */
    private JLabel statusDemandLocations, statusPossibleLocations, statusP, statusSolutionCost, statusCostMatrix;

    /**
     * Button group specifying algorithm.
     */
    private ButtonGroup algorithmButton;

    /**
     * Graphical interface for application.
     * @param inputApp Application logic.
     */
    public GUI(final Pmedian inputApp) {
        this.app = inputApp;

    }
   
    /**
     * Run application.
     */
    public void start() {
        // Create and set up a frame window
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("P-median solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 
       
        // Panels
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
       
         
        // Set up the title for different panels
        panel1.setBorder(BorderFactory.createTitledBorder("Input:"));
        panel2.setBorder(BorderFactory.createTitledBorder("Parameters:"));
        panel3.setBorder(BorderFactory.createTitledBorder("Choose algorithm:"));
        panel4.setBorder(BorderFactory.createTitledBorder("Actions:"));


         
        // Set up the BoxLayout
        BoxLayout layout1 = new BoxLayout(panel1, BoxLayout.Y_AXIS);
        BoxLayout layout2 = new BoxLayout(panel2, BoxLayout.Y_AXIS);
        BoxLayout layout3 = new BoxLayout(panel3, BoxLayout.Y_AXIS);
        BoxLayout layout4 = new BoxLayout(panel4, BoxLayout.Y_AXIS);
        panel1.setLayout(layout1);
        panel2.setLayout(layout2);
        panel3.setLayout(layout3);
        panel4.setLayout(layout4);

         
        // Input

        JButton inputDemandLocations = new JButton("Load demand locations");        
        JButton inputPossibleFacilities = new JButton("Load possible facility locations");

        inputDemandLocations.addActionListener(new InputFileChooserListener("demand"));
        inputPossibleFacilities.addActionListener(new InputFileChooserListener("possible"));

        // Texts for inputs
        this.statusDemandLocations = new JLabel();
        this.statusPossibleLocations = new JLabel();
        this.statusCostMatrix = new JLabel();

        inputPossibleFacilities.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputDemandLocations.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel1.add(inputDemandLocations);
        panel1.add(statusDemandLocations);
        panel1.add(inputPossibleFacilities);
        panel1.add(statusPossibleLocations);
        panel1.add(statusCostMatrix);

        // Parameters
         
            
        JTextField pChooser = new JTextField(3);
        pChooser.setToolTipText("Choose the number of facilities to allocate.");

        pChooser.addActionListener(new ChoosePListener(pChooser));

        this.statusP = new JLabel();
        
        panel2.add(pChooser);
        panel2.add(statusP);
        
        // Radio buttons for algorithm choice
        //Group for the radio buttons.
        this.algorithmButton = new ButtonGroup();

        String[] algorithms = this.app.getAlgorithms();

        

        //JRadioButton[] algorithmButtons = new JRadioButton[algorithms.length];
        //algorithmButtons[i] = createRadioButton(algorithms[i]);

        for (int i = 0; i < algorithms.length; i++) {
            JRadioButton button = createRadioButton(algorithms[i]);
            
            algorithmButton.add(button);
            panel3.add(button);

            button.addActionListener(new AlgorithmListener(button));

            // Select first button;
            if (i == 0) {
                button.setSelected(true);
                this.app.setSolver(algorithms[i]);
            }
           
        }
       

        // Actions:
        JButton solveButton = new JButton("Solve!");
        panel4.add(solveButton);
        solveButton.addActionListener(new SolveListener());

        JButton loadExampleBUtton = new JButton("Load example");
        panel1.add(loadExampleBUtton);
        loadExampleBUtton.addActionListener(new ExampleListener());

        statusSolutionCost = new JLabel();
        panel4.add(statusSolutionCost);

        // Write to file

        JTextField outputPathInput = new JTextField(3);
        outputPathInput.setToolTipText("Path and filename to write output to.");
        outputPathInput.setText("/home/Documents/user/allocationresults.csv");

        JButton writeResultsButton = new JButton("Write results to file.");
        panel4.add(outputPathInput);
        panel4.add(writeResultsButton);
        
        writeResultsButton.addActionListener(new WriteResultsListener(outputPathInput));

         
        // Add the three panels into the frame
        frame.setLayout(new FlowLayout());
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);

        // set the size of frame 
        frame.setSize(700, 700); 
         
        // Set the window to be visible as the default to be false
        
        frame.setVisible(true);

        GUI.this.updateStatus();
        
    }

    /**
     * Update input status.
     */
    private void updateStatus() {
        this.statusDemandLocations.setText("Number of demand locations: " + this.app.getNumberOfDemandLocations());
        this.statusPossibleLocations.setText("Number of possible locations: " + this.app.getNumberOfPossibleLocations());
        this.statusP.setText("Number of facilities to allocate: " + this.app.getP());
        this.statusSolutionCost.setText("Cost: " + this.app.getResultCost());
        

        // If both locations are updated, calcucate distance matrix
        if (this.app.getNumberOfDemandLocations() > 0 & this.app.getNumberOfPossibleLocations() > 0) {
            this.app.calculateCostMatrix();
            
        }

        this.statusCostMatrix.setText("Costmatrix exists: " + this.app.costMatrixExists());

        // Chosen algorithm
        
    }
    /**
     * Load example problem and set parameters.
     */
    private void loadExample() {
        // Defaults for dev
        this.app.loadDemandlocations("src/test/resources/testdata_1_demand_locations.csv");
        this.app.loadPossiblelocations("src/test/resources/testdata_1_facility_locations.csv");
        this.app.setP(7);
        this.app.calculateCostMatrix();
        this.updateStatus();

    }

    /**
     * Solve problem.
     */
    class SolveListener implements ActionListener {
        public void actionPerformed(final ActionEvent e) {

            
            GUI.this.app.solve();
            GUI.this.updateStatus();

            
        }
    }
    /**
     * Load example data to app. Dev-purposes in mind.
     */

    class ExampleListener implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            GUI.this.loadExample();
        }

    }

    /**
     * P-chooser listener.
     */
    class ChoosePListener implements ActionListener {

        /**
         * Input field for P.
         */
        private JTextField pInputField;

        ChoosePListener(final JTextField pInput) {
            this.pInputField = pInput;
        }
        public void actionPerformed(final ActionEvent e) {

            Integer chosenP = Integer.parseInt(this.pInputField.getText());
            GUI.this.app.setP(chosenP);

            GUI.this.updateStatus();

        }
    }
    /**
     * Action listener for radio button choosing the algorithm to use.
     */
    class AlgorithmListener implements ActionListener {
        /**
         * Holds button.
         */
        private JRadioButton button;

        AlgorithmListener(final JRadioButton buttonInput) {
            this.button = buttonInput;
        }

        public void actionPerformed(final ActionEvent e) {

            String command = button.getActionCommand();
                  
            GUI.this.app.setSolver(command);
        }

    }
    /**
     * Writes results to a file.
     */
    class WriteResultsListener implements ActionListener {
        /**
         * Is it demand or possible.
         */
        private JTextField pathInputField;

        WriteResultsListener(final JTextField pathInput) {
            this.pathInputField = pathInput;
        }

        public void actionPerformed(final ActionEvent e) {

            
            GUI.this.app.writeAllocationsToFile(this.pathInputField.getText());
        }
    }



    /**
     * File chooser.
     */
    class InputFileChooserListener implements ActionListener {
        /**
         * Is it demand or possible.
         */
        private String which;

        InputFileChooserListener(final String whichInput) {
            this.which = whichInput;
        }

        public void actionPerformed(final ActionEvent e) {
            
            JFileChooser fileChooser = new JFileChooser();

            int result = fileChooser.showOpenDialog(GUI.this);
            if (result == JFileChooser.APPROVE_OPTION) {

                File selectedFile = fileChooser.getSelectedFile();
                if (this.which.equals("demand")) {
                    GUI.this.app.loadDemandlocations(selectedFile.getAbsolutePath());
                }
                if (this.which.equals("possible")) {
                    GUI.this.app.loadPossiblelocations(selectedFile.getAbsolutePath());
                }

                GUI.this.updateStatus();

            }

            
        }
    }
    /**
     * Radio button.
     * @param text A text to display and set as action command.
     * @return A radiobutton.
     */
    private JRadioButton createRadioButton(final String text) {
        JRadioButton butt = new JRadioButton(text);
        butt.setActionCommand(text);
        return butt;
    }

    
  
    
}