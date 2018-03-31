import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;




public class TextFileAnalyser {

    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JLabel lblNewLabel_7;
    private JLabel lblNewLabel_8;
    private JTextField textField;

    private JFrame frame;
    private File file1, file2;
    private String filepath;

    private boolean justified = true;
    private boolean fulljustified = false;
    private boolean space = true;
    /* Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TextFileAnalyser window = new TextFileAnalyser();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /* Create the application.
     */
    private TextFileAnalyser() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(176, 224, 230));
        frame.getContentPane().add(desktopPane, BorderLayout.CENTER);


        JLabel lblCharacterMax = new JLabel("Character Max: ");
        lblCharacterMax.setBounds(260, 110, 103, 16);
        desktopPane.add(lblCharacterMax);

//        textField = new JTextField("80");
//        textField.setBounds(360, 105, 39, 26);
//        desktopPane.add(textField);
//        textField.setColumns(3);


        Integer[] listofMaxchar = new Integer[100];
        int inc=20;
        for(int i=0;i<100;i++)
        {
            listofMaxchar[i] = inc;
            inc++;
        }
        JComboBox characterMaxBox = new JComboBox(listofMaxchar);
        characterMaxBox.setBounds(360, 105, 80, 26);
        characterMaxBox.setSelectedIndex(60);
        File_statistics.characterMax = 80;
        desktopPane.add(characterMaxBox);
        characterMaxBox.addActionListener(arg0 -> {
            File_statistics.characterMax = (int) characterMaxBox.getSelectedItem();

        });


        JRadioButton rdbtnLeftJustified = new JRadioButton("Left Justified");
        rdbtnLeftJustified.setBounds(450, 50, 141, 23);
        desktopPane.add(rdbtnLeftJustified);

        JRadioButton rdbtnFullJustified = new JRadioButton("Full Justified");
        rdbtnFullJustified.setBounds(450, 110, 141, 23);
        desktopPane.add(rdbtnFullJustified);

        JRadioButton rdbtnRightJustified = new JRadioButton("Right Justified");
        rdbtnRightJustified.setBounds(450, 80, 141, 23);
        desktopPane.add(rdbtnRightJustified);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnLeftJustified);
        group.add(rdbtnRightJustified);
        group.add(rdbtnFullJustified);
        rdbtnLeftJustified.setSelected(true);
        justified = true;

        rdbtnLeftJustified.addActionListener(arg0 -> {
            justified = true;
            fulljustified = false;
        });

        rdbtnRightJustified.addActionListener(arg0 -> {
            justified = false;
            fulljustified = false;
        });

        rdbtnFullJustified.addActionListener(arg0 -> {
            justified = false;
            fulljustified = true;
        });

        JRadioButton rdbtnSingle = new JRadioButton("Single Spaced");
        rdbtnSingle.setBounds(250, 50, 141, 23);
        desktopPane.add(rdbtnSingle);

        JRadioButton rdbtnDouble = new JRadioButton("Double Spaced");
        rdbtnDouble.setBounds(250, 80, 141, 23);
        desktopPane.add(rdbtnDouble);

        ButtonGroup spacegroup = new ButtonGroup();
        spacegroup.add(rdbtnSingle);
        spacegroup.add(rdbtnDouble);
        rdbtnSingle.setSelected(true);
        space = true;

        rdbtnSingle.addActionListener(arg0 -> {
            space = true;
        });

        rdbtnDouble.addActionListener(arg0 -> {
            space = false;
        });

        JButton btnSelectFile = new JButton("Select File");
        btnSelectFile.setBounds(450, 140, 117, 29);
        desktopPane.add(btnSelectFile);
        btnSelectFile.addActionListener((ActionEvent arg0) -> {
            FileNameExtensionFilter filter1 = new FileNameExtensionFilter("TXT Document", "txt");
            JFileChooser jfc1 = new JFileChooser();
            jfc1.setFileFilter(filter1);
            int rval1 = jfc1.showOpenDialog(null);
            if (rval1 == JFileChooser.APPROVE_OPTION) {
                file1 = jfc1.getSelectedFile();
                filepath = file1.getAbsolutePath();
                //System.out.println(filepath);

            }
        });

        JButton btnRunProgram = new JButton("Run Program");
        btnRunProgram.setBounds(450, 170, 117, 29);
        desktopPane.add(btnRunProgram);
        btnRunProgram.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    readTheFile();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            private void readTheFile() {
                if (file1 != null) {
                    String fp = file1.getPath();
                    File_statistics o1 = new File_statistics(fp);

                    String Numberofspaces = Integer.toString(File_statistics.space_count);
                    lblNewLabel_3.setText(Numberofspaces);

                    String Numberoflinesremoved = Integer.toString(File_statistics.lineremoved);
                    lblNewLabel_2.setText(Numberoflinesremoved);

                    String Wordsprocessed = Integer.toString(File_statistics.wordCount);
                    lblNewLabel_4.setText(Wordsprocessed);

                    String AverageWordsperLine = Integer.toString(File_statistics.avgWordsperLine);
                    lblNewLabel_5.setText(AverageWordsperLine);

                    String AverageLineLength = Integer.toString(File_statistics.avgLineLength);
                    lblNewLabel_6.setText(AverageLineLength);

                    String NumberofLines = Integer.toString(File_statistics.lineCount);
                    lblNewLabel_7.setText(NumberofLines);
                }
            }
        });

        lblNewLabel_2 = new JLabel("0");
        lblNewLabel_2.setBounds(232, 198, 61, 16);
        desktopPane.add(lblNewLabel_2);

        lblNewLabel_3 = new JLabel("0");
        lblNewLabel_3.setBounds(232, 57, 61, 16);
        desktopPane.add(lblNewLabel_3);

        lblNewLabel_4 = new JLabel("0");
        lblNewLabel_4.setBounds(232, 85, 61, 16);
        desktopPane.add(lblNewLabel_4);


        lblNewLabel_5 = new JLabel("0");
        lblNewLabel_5.setBounds(232, 113, 61, 16);
        desktopPane.add(lblNewLabel_5);


        lblNewLabel_6 = new JLabel("0");
        lblNewLabel_6.setBounds(232, 141, 61, 16);
        desktopPane.add(lblNewLabel_6);

        lblNewLabel_7 = new JLabel("0");
        lblNewLabel_7.setBounds(232, 169, 61, 16);
        desktopPane.add(lblNewLabel_7);

//        lblNewLabel_8 = new JLabel("0");
//        lblNewLabel_8.setBounds(232, 198, 61, 16);
//        desktopPane.add(lblNewLabel_8);

        JButton btnSaveFile = new JButton("Save File");
        btnSaveFile.setBounds(450, 200, 117, 29);
        desktopPane.add(btnSaveFile);
        btnSaveFile.addActionListener(arg0 -> {
            FileNameExtensionFilter filter2 = new FileNameExtensionFilter("TXT Document", "txt");
            JFileChooser jfc2 = new JFileChooser();
            jfc2.setFileFilter(filter2);
            int rval2 = jfc2.showSaveDialog(null);
            if (rval2 == JFileChooser.APPROVE_OPTION) {
                try {
                    FileWriter fw = new FileWriter(jfc2.getSelectedFile()+ ".txt");

                    ArrayList <String> write = File_statistics.outputstring;
                    ArrayList <String> writefull = File_statistics.outputstringfull;
                    String f = "%0$"+Integer.toString(File_statistics.characterMax)+"s";

                    if(fulljustified == true){

                        for (int j =0; j < writefull.size();j++){
                            if(space == true)
                                fw.write(String.format(writefull.get(j) + "\n"));
                            else if(space == false)
                                fw.write(String.format(writefull.get(j) + "\n\n"));
                        }
                    }
                    else {
                        for (int i = 0; i < write.size(); i++) {

                            if (justified == true && space == true)
                                fw.write(write.get(i) + "\n");
                            else if (justified == true && space == false)
                                fw.write(write.get(i) + "\n\n");
                            else if (justified == false && space == true)
                                fw.write(String.format(f, write.get(i) + "\n"));
                            else if (justified == false && space == false)
                                fw.write(String.format(f, write.get(i) + "\n\n"));
                        }
                    }

                    fw.close();

                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }

                file2 = jfc2.getSelectedFile();
                filepath = file2.getAbsolutePath();
                //System.out.println(filepath);

            }
        });


        //String abc = File_staistics.outputstring;

        JLabel lblNewLabel = new JLabel("Number Of Words Procesed:");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        lblNewLabel.setBounds(6, 85, 184, 16);
        desktopPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Number Of Lines:");
        lblNewLabel_1.setBounds(6, 169, 167, 16);
        desktopPane.add(lblNewLabel_1);

        JLabel lblNewLabel_3 = new JLabel("Number of Blank lines removed: ");
        lblNewLabel_3.setBounds(6, 197, 237, 16);
        desktopPane.add(lblNewLabel_3);

        JLabel lblNewLabel_2 = new JLabel("Number Of Spaces Added: ");
        lblNewLabel_2.setBounds(6, 57, 237, 16);
        desktopPane.add(lblNewLabel_2);

        JLabel lblAverageWordsPer = new JLabel("Average Words Per Line:");
        lblAverageWordsPer.setBounds(6, 113, 194, 16);
        desktopPane.add(lblAverageWordsPer);

        JLabel lblAverageLineLength = new JLabel("Average Line Length: ");
        lblAverageLineLength.setBounds(6, 141, 184, 16);
        desktopPane.add(lblAverageLineLength);

        JLabel lblTeamText = new JLabel("Team 5 Text Analyzer ™");
        lblTeamText.setFont(new Font("Trajan Pro 3", Font.BOLD, 22));
        lblTeamText.setBounds(160, 8, 293, 33);
        desktopPane.add(lblTeamText);

        JLabel lblInstructionsThe = new JLabel("Instructions: Browse for a .txt file by pressing the \"Select File\"  button. The output will have");
        lblInstructionsThe.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        lblInstructionsThe.setBounds(56, 221, 461, 29);
        desktopPane.add(lblInstructionsThe);

        JLabel lblMaximumOf = new JLabel("a maximum of 80 characters per line. Save the file by pressing the “Save File” button. ");
        lblMaximumOf.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        lblMaximumOf.setBounds(70, 245, 420, 16);
        desktopPane.add(lblMaximumOf);
    }

}