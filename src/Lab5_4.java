import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Lab5_4 {

    public static void main(String[] args) {

        // สร้าง Frame
        JFrame frame = new JFrame("Progam with JTextArea");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // สร้าง TextArea
        JTextArea textArea = new JTextArea(8, 30);
        textArea.setLineWrap(true);       // ตัดบรรทัดอัตโนมัติ
        textArea.setWrapStyleWord(true);  // ตัดตามคำ

        // ใส่ ScrollBar ให้ TextArea
        JScrollPane scrollPane = new JScrollPane(textArea);

        // สร้างปุ่ม
        JButton button = new JButton("Show message");
        JButton button2 = new JButton("Save");

        // เมื่อกดปุ่ม
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                JOptionPane.showMessageDialog(frame, text,
                        "Your message: ", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        button2.addActionListener(e -> {
            File f = new File("\\D:message.txt");
            PrintWriter p = null;
            try {
                p = new PrintWriter(f);

            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            String text2 = textArea.getText();
            p.print(text2);
            p.close();

        });

        // จัด Layout
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);
        buttonPanel.add(button2);

        // จัด Layoutของ show message กับ save
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        // แสดงหน้าจอ
        frame.setVisible(true);
    }
}