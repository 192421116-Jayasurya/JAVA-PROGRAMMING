import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConverterNamedInner extends JFrame {

    JTextField amount = new JTextField(8);
    JComboBox<String> currency = new JComboBox<>(new String[]{"USD", "EUR"});
    JLabel result = new JLabel("Result");

    class ConvertHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            convert();
        }
    }

    public ConverterNamedInner() {
        super("Converter - Inner Class");
        setLayout(new FlowLayout());

        JButton b = new JButton("Convert");

        add(new JLabel("INR"));
        add(amount);
        add(currency);
        add(b);
        add(result);

        ConvertHandler h = new ConvertHandler();
        b.addActionListener(h);
        amount.addActionListener(h);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        pack();
        setVisible(true);
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
        SwingUtilities.invokeLater(() -> new ConverterNamedInner());
    }
}