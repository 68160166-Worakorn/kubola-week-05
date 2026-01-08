import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class Lab5_5 {


    public static void main(String[] args) {


        JFrame frame = new JFrame("Save File with JFileChooser");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);


        JButton saveButton = new JButton("Save File");


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


        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(saveButton, BorderLayout.SOUTH);


        frame.setVisible(true);
    }
}

