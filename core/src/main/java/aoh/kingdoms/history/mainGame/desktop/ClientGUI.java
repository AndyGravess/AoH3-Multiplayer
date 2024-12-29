// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.desktop;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientGUI
{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private JTextArea textArea;
    private JTextField textField;
    private String uid;
    
    public ClientGUI() {
        final JFrame frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(3);
        frame.setSize(400, 400);
        (this.textArea = new JTextArea()).setEditable(false);
        frame.add(new JScrollPane(this.textArea), "Center");
        frame.add(this.textField = new JTextField(), "South");
        this.textField.addActionListener(e -> this.sendMessage());
        frame.setVisible(true);
        this.connectToServer();
    }
    
    private void connectToServer() {
        try {
            this.socket = new Socket("localhost", 12345);
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
            new Thread(() -> {
                try {
                    while (true) {
                        this.in.readLine();
                        final String s;
                        final String response;
                        if ((response = s) != null) {
                            this.textArea.append(response + "\n");
                            if (response.startsWith("YOU:")) {
                                this.uid = response.split(": ")[1];
                            }
                            else {
                                continue;
                            }
                        }
                        else {
                            break;
                        }
                    }
                }
                catch (final IOException e2) {
                    e2.printStackTrace();
                }
            }).start();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
    }
    
    private void sendMessage() {
        final String message = this.textField.getText();
        if (message != null && !message.trim().isEmpty()) {
            this.out.println(message);
            this.textField.setText("");
        }
    }
}
