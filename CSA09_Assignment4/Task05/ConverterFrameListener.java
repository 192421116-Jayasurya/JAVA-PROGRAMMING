import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConverterFrameListener extends JFrame implements ActionListener {

    JTextField amount = new JTextField(8);
    JComboBox<String> currency = new JComboBox<>(new String[]{"USD", "EUR"});
    JLabel result = new JLabel("Result");

    public ConverterFrameListener() {
        super("Converter - Frame Listener");
        setLayout(new FlowLayout());

        JButton b = new JButton("Convert");

        add(new JLabel("INR"));
        add(amount);
        add(currency);
        add(b);
        add(result);

        b.addActionListener(this);
        amount.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        convert();
    }

    private void convert() {
        try {
            double v = Double.parseDouble(amount.getText());
            double r = currency.getSelectedItem().equals("USD") ? 0.012 : 0.011;
            result.setText(String.format("%.2f", v * r));
        } catch (Exception ex) {
            result.setText("Invalid amount");
        }
    }

    public static void main(String[] a) {
        SwingUtilities.invokeLater(() -> new ConverterFrameListener());
    }
}