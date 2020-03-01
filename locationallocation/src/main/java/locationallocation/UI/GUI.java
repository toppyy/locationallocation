package locationallocation.UI;


import locationallocation.Domain.Pmedian;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import locationallocation.UI.ActionListeners.SolveListener;
import locationallocation.UI.ActionListeners.ChoosePListener;
import locationallocation.UI.ActionListeners.InputFileChooserListener;
import locationallocation.UI.ActionListeners.WriteResultsListener;
import locationallocation.UI.ActionListeners.AlgorithmListener;


/**
 * A simple GUI for the application.
 */

// A lot is borrowed from here:
// https://github.com/yussiv/Compress/blob/master/squash/src/main/java/fi/yussiv/squash/ui/GUI.java


public class GUI extends JFrame  {


    private Pmedian app;
    private JButton solveButton;
    private ButtonGroup algorithmButton;
    
    /**
     * Holds status of GUI.
     */
    private JLabel statusDemandLocations, statusPossibleLocations, statusP, statusSolutionCost, statusCostMatrix;


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

        inputDemandLocations.addActionListener(new InputFileChooserListener(this, this.app, "demand"));
        inputPossibleFacilities.addActionListener(new InputFileChooserListener(this, this.app, "possible"));

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

        
        
        
        pChooser.addActionListener(new ChoosePListener(this, this.app, pChooser));
        this.statusP = new JLabel();
        this.updateStatus("P");
        
        panel2.add(pChooser);
        panel2.add(statusP);
        
        // Radio buttons for algorithm choice
        // Group for the radio buttons.
        this.algorithmButton = new ButtonGroup();

        String[] algorithms = this.app.getAlgorithms();



        for (int i = 0; i < algorithms.length; i++) {
            JRadioButton button = createRadioButton(algorithms[i]);
            
            algorithmButton.add(button);
            panel3.add(button);

            button.addActionListener(new AlgorithmListener(this.app, button));

            // Select first button;
            if (i == 0) {
                button.setSelected(true);
                this.app.setSolver(algorithms[i]);
            }
           
        }
       

        // Actions:
        this.solveButton = new JButton("Solve!");
        solveButton.setEnabled(this.app.solveRequirementsFulfilled());
        panel4.add(solveButton);
        solveButton.addActionListener(new SolveListener(this, this.app));

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
        
        writeResultsButton.addActionListener(new WriteResultsListener(this.app, outputPathInput));

         
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
        
    }


    /**
     * Calculate cost matrix.
     */
    public void calculateCostMatrix() {
        if (this.app.getNumberOfDemandLocations() > 0 & this.app.getNumberOfPossibleLocations() > 0) {
            this.app.calculateCostMatrix();
            
        }
        this.statusCostMatrix.setText("Costmatrix exists: " + this.app.costMatrixExists());
    }

    /**
     * Load example problem and set parameters.
     */
    private void loadExample() {
        // Defaults for dev
        this.app.loadDemandlocations("src/test/resources/testdata_1_demand_locations.csv");
        this.app.loadPossiblelocations("src/test/resources/testdata_1_facility_locations.csv");
        this.app.setP(7);
        this.calculateCostMatrix();
        this.updateStatus("locations");
        this.updateStatus("P");
        this.solveButton.setEnabled(this.app.solveRequirementsFulfilled());

    }

    
    /**
     * Load example data to app.
     */

    class ExampleListener implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            GUI.this.loadExample();
        }

    }

    /**
     * Create a radio button.
     * @param text A text to display and set as action command.
     * @return A radiobutton.
     */
    private JRadioButton createRadioButton(final String text) {
        JRadioButton butt = new JRadioButton(text);
        butt.setActionCommand(text);
        return butt;
    }


    /**
     * Updates status texts based on input.
     * @param which
     */
    public void updateStatus(final String which) {
        if (which == "P") {
            this.statusP.setText("Number of facilities to allocate: " + this.app.getP());
        }
        if (which == "cost") {
            this.statusSolutionCost.setText("Cost: " + this.app.getResultCost());
        }
        if (which == "locations") {
            this.statusDemandLocations.setText("Number of demand locations: " + this.app.getNumberOfDemandLocations());
            this.statusPossibleLocations.setText("Number of possible locations: " + this.app.getNumberOfPossibleLocations());
            this.calculateCostMatrix();
            this.solveButton.setEnabled(this.app.solveRequirementsFulfilled());
        }
    }
  
   
    
}

