package com.dosomedev.view;

import javax.swing.JFrame;

public class Viewer {
    private JFrame frame;

    public Viewer() {
        // Create a new frame.
        this.frame = new JFrame("Animal Info");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add main window to the frame.
        MainWindow mainWindow = new MainWindow();
        frame.getContentPane().add(mainWindow);
        frame.pack();
    }

    public void setVisible() {
        this.frame.setVisible(true);
    }
}
