import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Lab5_3 {

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

        // เมื่อกดปุ่ม
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                JOptionPane.showMessageDialog(frame, text,
                        "Your message: ", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // จัด Layout
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);

        // แสดงหน้าจอ
        frame.setVisible(true);
    }
}

