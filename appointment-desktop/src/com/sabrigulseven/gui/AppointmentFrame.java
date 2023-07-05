package com.sabrigulseven.gui;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.sabrigulseven.database.AppointmentManager;
import com.sabrigulseven.database.PatientManager;
import com.sabrigulseven.entity.Appointment;
import com.sabrigulseven.entity.Patient;

public class AppointmentFrame extends JFrame {

    private JTextField identityNumberTextField;
    private JButton attendButton;

    public AppointmentFrame() {
        setTitle("Randevu Hizmeti");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1350, 900);
        setLocationRelativeTo(null);

        // Create components
        JLabel identityNumberLabel = new JLabel("Kimlik Numaranız:");
        identityNumberTextField = new JTextField();
        attendButton = new JButton("Giriş");
        JButton deleteButton = new JButton("Sil");

        // Set font size for all text
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 50);
        identityNumberLabel.setFont(font);
        identityNumberTextField.setFont(font);
        attendButton.setFont(font);
        deleteButton.setFont(font);

        // Set layout
        setLayout(new GridLayout(5, 3));

        // Add components to the frame
        add(identityNumberLabel);
        add(identityNumberTextField);
        add(new JLabel()); // Empty label for layout alignment
        add(new NumberButton("1"));
        add(new NumberButton("2"));
        add(new NumberButton("3"));
        add(new NumberButton("4"));
        add(new NumberButton("5"));
        add(new NumberButton("6"));
        add(new NumberButton("7"));
        add(new NumberButton("8"));
        add(new NumberButton("9"));
        add(deleteButton);
        add(new NumberButton("0"));
        add(attendButton);

        // Add action listeners
        attendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identityNumberText = identityNumberTextField.getText();
                if (identityNumberText.length()<11) {
                    return;
                }

                long identityNumber;
                try {
                    identityNumber = Long.parseLong(identityNumberText);
                    identityNumberTextField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AppointmentFrame.this, "Kimlik numarası numerik olmalıdır");
                    return;
                }

                attend(identityNumber);
                
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = identityNumberTextField.getText();
                if (!currentText.isEmpty()) {
                    identityNumberTextField.setText(currentText.substring(0, currentText.length() - 1));
                }
            }
        });
    }

    private void attend(long identityNumber) {
        PatientManager patientManager = new PatientManager();
        try {
            Patient patient = patientManager.findByIdentityNumber(identityNumber);
            if (patient == null) {
                JOptionPane.showMessageDialog(AppointmentFrame.this, "Hasta bulunmadı.");
                return;
            }

            AppointmentManager appointmentManager = new AppointmentManager();
            Appointment appointment = appointmentManager.findByPatientToday(patient);
            if (appointment == null) {
                JOptionPane.showMessageDialog(AppointmentFrame.this, "Bugün randevunuz bulunmamaktadır.");
                return;
            }

            appointment.setAttendance(true);
            appointmentManager.update(appointment);

            displayAppointmentInformation(appointment);

        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging the exception instead
        }
    }

	private void displayAppointmentInformation(Appointment appointment) {
		// Create a custom dialog to display appointment information
		JDialog dialog = new JDialog(AppointmentFrame.this, "Randevu Bilgileri");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setSize(1000, 1000);
		dialog.setLocationRelativeTo(AppointmentFrame.this);
		dialog.setLayout(new GridLayout(3, 1));

       // Create and configure appointment information labels
		JLabel patientLabel = new JLabel("Hasta: " + appointment.getPatient().getName());
		JLabel timeLabel = new JLabel("Randevu Saati: " + appointment.getTime());
		JLabel physiotherapistLabel = new JLabel("Fizyoterapist: " + appointment.getPhysiotherapist().getName());

		// Set font size and alignment for appointment information labels
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 50);
		patientLabel.setFont(font);
		patientLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setFont(font);
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		physiotherapistLabel.setFont(font);
		physiotherapistLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// Add appointment information labels to the dialog
		dialog.add(patientLabel);
		dialog.add(timeLabel);
		dialog.add(physiotherapistLabel);

		// Set a timer to close the dialog after 5 seconds
		Timer timer = new Timer(5000, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        dialog.dispose();
		    }
		});
		timer.setRepeats(false); // Only execute once
		timer.start();

		// Make the dialog visible
		dialog.setVisible(true);
	}


    private class NumberButton extends JButton {

        public NumberButton(String number) {
            super(number);
            setFont(getFont().deriveFont(Font.BOLD, 80));
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String currentText = identityNumberTextField.getText();
                    if (currentText.length() < 11) {
                        identityNumberTextField.setText(currentText + number);
                    }              
                }
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AppointmentFrame frame = new AppointmentFrame();
                frame.setVisible(true);
            }
        });
    }
}
