package text;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class TextFileAnalyser {
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JLabel lblNewLabel_7;
    private JLabel lblNewLabel_3;

    private JFrame frame;
    private File file1, file2;
    private String filepath;

    public boolean justified = true;

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
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(176, 224, 230));
        frame.getContentPane().add(desktopPane, BorderLayout.CENTER);

        JRadioButton rdbtnLeftJustified = new JRadioButton("Left Justified");
        rdbtnLeftJustified.setBounds(326, 53, 141, 23);
        desktopPane.add(rdbtnLeftJustified);

        JRadioButton rdbtnRightJustified = new JRadioButton("Right Justified");
        rdbtnRightJustified.setBounds(326, 81, 141, 23);
        desktopPane.add(rdbtnRightJustified);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnLeftJustified);
        group.add(rdbtnRightJustified);
        rdbtnLeftJustified.setSelected(true);
        justified = true;
        
        rdbtnLeftJustified.addActionListener(arg0 -> {
            justified = true;
        });
        
        rdbtnRightJustified.addActionListener(arg0 -> {
            justified = false;
        });

        JButton btnSelectFile = new JButton("Select File");
        btnSelectFile.setBounds(326, 108, 117, 29);
        desktopPane.add(btnSelectFile);
        btnSelectFile.addActionListener(arg0 -> {
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
        btnRunProgram.setBounds(326, 136, 117, 29);
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
                    File_staistics o1 = new File_staistics(fp);
                    String Wordsprocessed = Integer.toString(File_staistics.wordCount);
                    lblNewLabel_4.setText(Wordsprocessed);

                    String AverageWordsperLine = Integer.toString(File_staistics.avgWordsperLine);
                    lblNewLabel_5.setText(AverageWordsperLine);

                    String AverageLineLength = Integer.toString(File_staistics.avgLineLength);
                    lblNewLabel_6.setText(AverageLineLength);

                    String NumberofLines = Integer.toString(File_staistics.lineCount);
                    lblNewLabel_7.setText(NumberofLines);
                }
            }
        });


       // lblNewLabel_3 = new JLabel("0");
        //lblNewLabel_3.setBounds(232, 57, 61, 16);
        //desktopPane.add(lblNewLabel_3);

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

        JButton btnSaveFile = new JButton("Save File");
        btnSaveFile.setBounds(326, 164, 117, 29);
        desktopPane.add(btnSaveFile);
        btnSaveFile.addActionListener(arg0 -> {
            FileNameExtensionFilter filter2 = new FileNameExtensionFilter("TXT Document", "txt");
            JFileChooser jfc2 = new JFileChooser();
            jfc2.setFileFilter(filter2);
            int rval2 = jfc2.showSaveDialog(null);
            if (rval2 == JFileChooser.APPROVE_OPTION) {
                try {
                    FileWriter fw = new FileWriter(jfc2.getSelectedFile()+ ".txt");

                    ArrayList <String> write = File_staistics.outputstring;
                    String f = "%0$80s";
                    for (int i = 0; i < write.size(); i++) {	
                    		if (justified)
                    			fw.write(write.get(i) + "\n");
                    		else
                    			fw.write(String.format(f, write.get(i) + "\n"));
                    }
                    


                    fw.close();

                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }

                file2 = jfc2.getSelectedFile();
                filepath = file2.getAbsolutePath();
                System.out.println(filepath);

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

       // JLabel lblNewLabel_2 = new JLabel("Number Of Blank Lines Removed: ");
        //lblNewLabel_2.setBounds(6, 57, 237, 16);
        //desktopPane.add(lblNewLabel_2);

        JLabel lblAverageWordsPer = new JLabel("Average Words Per Line:");
        lblAverageWordsPer.setBounds(6, 113, 194, 16);
        desktopPane.add(lblAverageWordsPer);

        JLabel lblAverageLineLength = new JLabel("Average Line Length: ");
        lblAverageLineLength.setBounds(6, 141, 184, 16);
        desktopPane.add(lblAverageLineLength);

        JLabel lblTeamText = new JLabel("Team 5 Text Analyzer ™");
        lblTeamText.setFont(new Font("Trajan Pro 3", Font.BOLD, 22));
        lblTeamText.setBounds(78, 8, 293, 33);
        desktopPane.add(lblTeamText);

        JLabel lblInstructionsThe = new JLabel("Instructions: Browse for a .txt file by pressing the \"Select File\"  button. The output will have");
        lblInstructionsThe.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        lblInstructionsThe.setBounds(6, 221, 461, 29);
        desktopPane.add(lblInstructionsThe);

        JLabel lblMaximumOf = new JLabel("a maximum of 80 characters per line. Save the file by pressing the “Save File” button. ");
        lblMaximumOf.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        lblMaximumOf.setBounds(16, 245, 420, 16);
        desktopPane.add(lblMaximumOf);
    }

//    public boolean textJustification(){
//        return justified;
//    }

}
