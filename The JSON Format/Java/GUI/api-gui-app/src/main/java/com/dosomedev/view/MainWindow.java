package com.dosomedev.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dosomedev.data.ApiAccess;
import com.dosomedev.model.Animal;

public class MainWindow extends JPanel implements ActionListener {
    private JLabel lblIndex;
    private JTextField txtIndex;
    private JButton btnShow;

    private JLabel lblAnimal;
    private JTextField txtAnimal;

    private JLabel lblName;
    private JTextField txtName;

    private JLabel lblBreed;
    private JTextField txtBreed;

    private JLabel lblAge;
    private JTextField txtAge;

    public MainWindow() {
        this.initFields();
        this.setupLayout();
    }

    private void initFields() {
        // Initialize components.
        this.lblIndex = new JLabel("Index:");
        this.txtIndex = new JTextField(10);
        this.btnShow = new JButton("Show Animal Info");

        this.lblAnimal = new JLabel("Animal:");
        this.txtAnimal = new JTextField(20);
        this.txtAnimal.setEditable(false);

        this.lblName = new JLabel("Name:");
        this.txtName = new JTextField(20);
        this.txtName.setEditable(false);

        this.lblBreed = new JLabel("Breed");
        this.txtBreed = new JTextField(20);
        this.txtBreed.setEditable(false);

        this.lblAge = new JLabel("Age:");
        this.txtAge = new JTextField(20);
        this.txtAge.setEditable(false);
    }

    private void setupLayout() {
        // Define layout type.
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();

        // Define padding.
        constraints.insets = new Insets(5, 5, 5, 5);

        // Add components to panel.
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(this.lblIndex, constraints);
        JPanel indexPanel = new JPanel();
        indexPanel.add(this.txtIndex);
        indexPanel.add(this.btnShow);
        constraints.gridx = 1;
        add(indexPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(this.lblAnimal, constraints);
        constraints.gridx = 1;
        add(this.txtAnimal, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(this.lblName, constraints);
        constraints.gridx = 1;
        add(this.txtName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(this.lblBreed, constraints);
        constraints.gridx = 1;
        add(this.txtBreed, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(this.lblAge, constraints);
        constraints.gridx = 1;
        add(this.txtAge, constraints);

        // Register action listener.
        this.btnShow.addActionListener(this);
    }

    private void setFields(Animal animal) {
        if (animal == null) {
            this.txtAnimal.setText("");
            this.txtName.setText("");
            this.txtBreed.setText("");
            this.txtAge.setText("");
        } else {
            this.txtAnimal.setText(animal.getAnimal());
            this.txtName.setText(animal.getName());
            this.txtBreed.setText(animal.getBreed());
            this.txtAge.setText(animal.getAge().toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Capture button event.
        if (e.getSource() == this.btnShow) {
            // Check if the input is a valid number.
            try {
                // Get index input.
                int indexNumber = Integer.parseInt(this.txtIndex.getText());

                // Get animals from web service.
                ApiAccess apiAccess = ApiAccess.getInstance();
                Animal[] animals = apiAccess.getAnimals();

                // Check if index valid.
                if (animals != null && 0 <= indexNumber && indexNumber < animals.length) {
                    System.out.println("Setting fields.");
                    this.setFields(animals[indexNumber]);
                } else {
                    throw new IndexOutOfBoundsException("Invalid index. Please enter an existing index.");
                }
            } catch (NumberFormatException ex) {
                this.setFields(null);
                System.out.println("Invalid input. Please enter a number.");
            } catch (IndexOutOfBoundsException ex) {
                this.setFields(null);
                System.out.println(ex.getMessage());
            }
        }
    }
}
