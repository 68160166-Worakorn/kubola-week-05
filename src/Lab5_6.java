import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class Lab5_6 {


    public static void main(String[] args) {


        JFrame frame = new JFrame("Save File with JFileChooser");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem saveButton = new JMenuItem("Save File");
        JMenuItem exitButton = new JMenuItem("Exit");

        menu.add(saveButton);
        menu.add(exitButton);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);


        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(frame);


                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();


                    try {
                        PrintWriter writer = new PrintWriter(file);
                        writer.write(textArea.getText());
                        writer.close();


                        JOptionPane.showMessageDialog(frame,
                                "Save file successfully.");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame,
                                "Error. Unable to save file.");
                    }
                }
            }
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });


        frame.setLayout(new BorderLayout());
        frame.add(scrollPane);

        frame.setVisible(true);
    }
}

