import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

 class EmployeeManagementSystemGUI {
    public static void main(String[] args) {
        new MainMenu();
    }
}

class MainMenu {
    JFrame frame;

    MainMenu() {
        frame = new JFrame("Employee Management System");

        // Setting layout
        frame.setLayout(new GridLayout(6, 1, 10, 10));

        // Title
        JLabel title = new JLabel("Employee Management System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // Buttons for menu options
        JButton addButton = new JButton("Add Employee");
        JButton viewButton = new JButton("View Employee");
        JButton removeButton = new JButton("Remove Employee");
        JButton updateButton = new JButton("Update Employee");
        JButton exitButton = new JButton("Exit");

        // Adding action listeners
        addButton.addActionListener(e -> new EmployeeAdd());
        viewButton.addActionListener(e -> new EmployeeView());
        removeButton.addActionListener(e -> new EmployeeRemove());
        updateButton.addActionListener(e -> new EmployeeUpdate());
        exitButton.addActionListener(e -> System.exit(0));

        // Adding components to frame
        frame.add(title);
        frame.add(addButton);
        frame.add(viewButton);
        frame.add(removeButton);
        frame.add(updateButton);
        frame.add(exitButton);

        // Setting frame properties
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class EmployeeAdd {
    EmployeeAdd() {
        JFrame frame = new JFrame("Add Employee");
        frame.setLayout(new GridLayout(8, 2, 10, 10));

        // Fields for employee details
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField fatherNameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField positionField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField salaryField = new JTextField();

        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("Back");

        // Adding components
        frame.add(new JLabel("Employee ID:"));
        frame.add(idField);
        frame.add(new JLabel("Name:"));
        frame.add(nameField);
        frame.add(new JLabel("Father's Name:"));
        frame.add(fatherNameField);
        frame.add(new JLabel("Email:"));
        frame.add(emailField);
        frame.add(new JLabel("Position:"));
        frame.add(positionField);
        frame.add(new JLabel("Contact:"));
        frame.add(contactField);
        frame.add(new JLabel("Salary:"));
        frame.add(salaryField);

        frame.add(saveButton);
        frame.add(backButton);

        saveButton.addActionListener(e -> {
            try {
                String id = idField.getText();
                File file = new File("file" + id + ".txt");
                if (file.exists()) {
                    JOptionPane.showMessageDialog(frame, "Employee already exists!");
                } else {
                    FileWriter writer = new FileWriter(file);
                    writer.write("Employee ID:" + id + "\n");
                    writer.write("Name:" + nameField.getText() + "\n");
                    writer.write("Father's Name:" + fatherNameField.getText() + "\n");
                    writer.write("Email:" + emailField.getText() + "\n");
                    writer.write("Position:" + positionField.getText() + "\n");
                    writer.write("Contact:" + contactField.getText() + "\n");
                    writer.write("Salary:" + salaryField.getText());
                    writer.close();
                    JOptionPane.showMessageDialog(frame, "Employee added successfully!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            new MainMenu();
        });

        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}

class EmployeeView {
    EmployeeView() {
        JFrame frame = new JFrame("View Employee");

        JTextField idField = new JTextField();
        JButton viewButton = new JButton("View");
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);

        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Employee ID:"));
        panel.add(idField);
        panel.add(viewButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        viewButton.addActionListener(e -> {
            try {
                String id = idField.getText();
                File file = new File("file" + id + ".txt");
                if (file.exists()) {
                    Scanner sc = new Scanner(file);
                    StringBuilder sb = new StringBuilder();
                    while (sc.hasNextLine()) {
                        sb.append(sc.nextLine()).append("\n");
                    }
                    displayArea.setText(sb.toString());
                } else {
                    JOptionPane.showMessageDialog(frame, "Employee does not exist!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}

class EmployeeRemove {
    EmployeeRemove() {
        JFrame frame = new JFrame("Remove Employee");

        JTextField idField = new JTextField();
        JButton removeButton = new JButton("Remove");

        frame.setLayout(new GridLayout(3, 1));
        frame.add(new JLabel("Employee ID:"));
        frame.add(idField);
        frame.add(removeButton);

        removeButton.addActionListener(e -> {
            try {
                String id = idField.getText();
                File file = new File("file" + id + ".txt");
                if (file.exists()) {
                    file.delete();
                    JOptionPane.showMessageDialog(frame, "Employee removed successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Employee does not exist!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}

class EmployeeUpdate {
    EmployeeUpdate() {
        JFrame frame = new JFrame("Update Employee");

        JTextField idField = new JTextField();
        JTextField oldField = new JTextField();
        JTextField newField = new JTextField();
        JButton updateButton = new JButton("Update");

        frame.setLayout(new GridLayout(5, 2));
        frame.add(new JLabel("Employee ID:"));
        frame.add(idField);
        frame.add(new JLabel("Old Detail:"));
        frame.add(oldField);
        frame.add(new JLabel("New Detail:"));
        frame.add(newField);
        frame.add(updateButton);

        updateButton.addActionListener(e -> {
            try {
                String id = idField.getText();
                File file = new File("file" + id + ".txt");
                if (file.exists()) {
                    Scanner sc = new Scanner(file);
                    StringBuilder sb = new StringBuilder();
                    while (sc.hasNextLine()) {
                        sb.append(sc.nextLine()).append("\n");
                    }
                    String fileContent = sb.toString();
                    fileContent = fileContent.replace(oldField.getText(), newField.getText());
                    FileWriter writer = new FileWriter(file);
                    writer.write(fileContent);
                    writer.close();
                    JOptionPane.showMessageDialog(frame, "Employee updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Employee does not exist!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
