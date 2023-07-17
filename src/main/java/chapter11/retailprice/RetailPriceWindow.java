package chapter11.retailprice;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.InputMismatchException;

public class RetailPriceWindow extends JFrame {
    private JLabel wholesalePriceLabel;
    private JLabel markupPercentLabel;
    private JTextField wholesalePriceTextField;
    private JTextField markupPercentTextField;
    private JButton calculateButton;

    private JPanel panel;

    /**
     * Class Constructor.
     */
    public RetailPriceWindow(){
        super("Retail Price Window");

        final int WINDOW_WIDTH = 320;
        final int WINDOW_HEIGHT = 480;

        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildPanel();
        add(panel);
        setVisible(true);
    }
    private void buildPanel(){

        wholesalePriceLabel = new JLabel("Enter a wholesale price: ");
        wholesalePriceTextField = new JTextField(4);
        markupPercentLabel = new JLabel("Enter markup percent: ");
        markupPercentTextField = new JTextField(3);
        calculateButton = new JButton("Calculate");

        //Add Action Listener to the button.
        calculateButton.addActionListener(new CalcButtonListener());


        panel = new JPanel();

        //add the label, text fields, buttons, to the panel.
        panel.add(wholesalePriceLabel);
        panel.add(wholesalePriceTextField);
        panel.add(markupPercentLabel);
        panel.add(markupPercentTextField);
        panel.add(calculateButton);
    }
    //Private inner class CalcButtonListener implements the ActionListener class.
    private class CalcButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double wholeSalePrice = Double.parseDouble(wholesalePriceTextField.getText());
                double markupPercent = Double.parseDouble(markupPercentTextField.getText());

                double retailPrice = calculateRetailPrice(wholeSalePrice, markupPercent);
                NumberFormat formatter = NumberFormat.getCurrencyInstance();

                JOptionPane.showMessageDialog(null, "Retail price is : " + formatter.format(retailPrice));
            } catch(NumberFormatException er){
                System.out.print("ERROR: Input for wholesale price or markup percent could not be parsed out.\n");
                System.out.print(er.getMessage() + "\n");
            }
        }
        private double calculateRetailPrice(double wholesalePrice, double markupPercent){
            double retailPrice = (wholesalePrice + wholesalePrice * (markupPercent / 100));
            return retailPrice;
        }

    }
}