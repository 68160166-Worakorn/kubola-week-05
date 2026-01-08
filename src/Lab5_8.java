import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Lab5_8 {
    private static JTextArea text;
    private static JFrame win;
    private static File currentFile = null;

    public static void main(String[] args) {
        win = new JFrame("Worakorn Thaweekoon 68160166 n90");
        win.setSize(600, 400);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setLayout(new BorderLayout());

        text = new JTextArea(10, 30);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(text);

        JMenuBar menubar = new JMenuBar();

        JMenu menu = new JMenu("File");

        JMenuItem itemnew = new JMenuItem("New");
        JMenuItem itemopen = new JMenuItem("Open");
        JMenuItem itemsave = new JMenuItem("Save");
        JMenuItem itemsaveas = new JMenuItem("Save as");
        JMenuItem itemexit = new JMenuItem("Exit");

        menu.add(itemnew);
        menu.add(itemopen);
        menu.add(itemsave);
        menu.add(itemsaveas);
        //Separator = Line
        menu.addSeparator();
        menu.add(itemexit);

        menubar.add(menu);

        win.setJMenuBar(menubar);
        win.add(scrollPane, BorderLayout.CENTER);

        // New File Action
        itemnew.addActionListener(e -> {
            if (text.getText().length() > 0) {
                int confirm = JOptionPane.showConfirmDialog(
                        win,
                        "Do you want to save current file?",
                        "New File",
                        JOptionPane.YES_NO_CANCEL_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    saveFile();
                } else if (confirm == JOptionPane.CANCEL_OPTION) {
                    return;
                }
            }
            text.setText("");
            currentFile = null;
            win.setTitle("Worakorn Thaweekoon 68160166 n90 - Untitled");
        });

        // Open File Action
        itemopen.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            int result = fileChooser.showOpenDialog(win);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    text.setText("");
                    String line;

                    while ((line = reader.readLine()) != null) {
                        text.append(line + "\n");
                    }

                    reader.close();
                    currentFile = file;
                    win.setTitle("Worakorn Thaweekoon 68160166 n90 - " + file.getName());

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            win,
                            "Cannot read file: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // Save File Action
        itemsave.addActionListener(e -> {
            saveFile();
        });

        // Save As File Action
        itemsaveas.addActionListener(e -> {
            saveAsFile();
        });

        // Exit Action
        itemexit.addActionListener(e -> {
            if (text.getText().length() > 0) {
                int confirm = JOptionPane.showConfirmDialog(
                        win,
                        "Do you want to save before exit?",
                        "Exit",
                        JOptionPane.YES_NO_CANCEL_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    saveFile();
                } else if (confirm == JOptionPane.CANCEL_OPTION) {
                    return;
                }
            }
            System.exit(0);
        });

        win.setVisible(true);
    }

    // Save File Method
    private static void saveFile() {
        if (currentFile == null) {
            saveAsFile();
        } else {
            writeToFile(currentFile);
        }
    }

    // Save As File Method
    private static void saveAsFile() {
        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showSaveDialog(win);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Add .txt extension if not present
            // ตรวจว่า file ตอน save มีนามสกิลไหมไม่มีเติม.txt
            if (!file.getName().contains(".")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }

            writeToFile(file);
            currentFile = file;
            win.setTitle("Worakorn Thaweekoon 68160166 n90 - " + file.getName());
        }
    }

    // Write to File Method
    private static void writeToFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(text.getText());
            writer.close();

            JOptionPane.showMessageDialog(
                    win,
                    "File saved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    win,
                    "Cannot save file: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}