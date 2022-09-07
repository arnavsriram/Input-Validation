import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

/**
 * A class that validates user input, used as part of an input form
 */
public class ValidateForm
{
    public ValidateForm() {
        //no variables to initialize
    }

    public static boolean isAllAlpha(String str) {
        boolean alpha = true;
        boolean letter = false;
        for (int i = 0; i < str.length(); i++) {
            char a = str.charAt(i);
            if (!(Character.isLetter(a) || Character.isWhitespace(a))) {
                alpha = false;
                break;
            }
            if (Character.isLetter(a)) {
                letter = true;
            }
        }
        if (!letter) {
            alpha = false;
        }
        return alpha;
    }

    public static boolean isNumeric(String str) {
        boolean number = true;
        for (int i = 0; i < str.length(); i++) {
            char a = str.charAt(i);
            if(!Character.isDigit(a)) {
                number = false;
                break;
            }
        }
        return number;
    }

    /** validate the user's entered name */
    public String checkName(String name) {
        int test = 0;
        for (int i = 0; i < name.length(); i++) {
            char a = name.charAt(i);
            if(Character.isLetter(a)) {
                test++;
            }
        }
        if (test < 2) {
            return "Invalid name!\n";
        }
        if (isAllAlpha(name)) {
            return "";
        } else {
            return "Invalid name!\n";
        }
    }

    /** validate the user's entered email */
    public String checkEmail(String email) {
        //TODO
        int period = 0;
        int at = 0;
        boolean format1 = false;
        boolean format2 = false;
        for (int i = 0; i < email.length(); i++) {
            char a = email.charAt(i);
            if (a == '@') {
                format1 = true;
                at = i;
            }
            if (a == '.') {
                format2 = true;
                period = i;
            }
        }
        if (!(format1 && format2) || (at > period) || (at == 0) || !(period < (email.length() - 2))) {
            return "Invalid email!\n";
        }


        return "";
    }

    /** validate the user's enter password */
    public String checkPW(String pw) {
        int number = 0;
        int lowerCase = 0;
        int upperCase = 0;
        if (pw.length() < 4) {
            return "Invalid password!\n";
        }
        for (int i = 0; i < pw.length(); i++) {
            char a = pw.charAt(i);
            if (Character.isDigit(a)) {
                number++;
            }
            if (Character.isUpperCase(a)) {
                upperCase++;
            }
            if (Character.isLowerCase(a)) {
                lowerCase++;
            }
        }
        if (number >= 1 && lowerCase >= 1 && upperCase >= 1) {
            return "";
        } else {
            return "Invalid password!\n";
        }
    }

    /** validate the user's entered zipcode */
    public String checkZip(String zip) {
       if (zip.length() >= 3 && zip.length() <= 5) {
           return "";
       } else {
           return "Invalid zipcode!\n";
       }
    }

    /** validate the user's entered birth year */
    public String checkBirth(String date) {
        int year = 0;
        try {
            year = Integer.parseInt(date);
        } catch (NumberFormatException ex) {
        }
        int current = Calendar.getInstance().get(Calendar.YEAR);
        if (year > current || current - year > 125 || current - year < 3) {
            return "Invalid birth year!\n";
        } else {
            return "";
        }
    }

    /** validate the user's entered phone number */
    public String checkPhone(String phone) {
        //TODO
        String result = "";
        Scanner scanner = new Scanner(result);
        for (int i = 0; i < phone.length(); i++) {
            String oneLetter = phone.substring(i, i+1);
            if (!oneLetter.equals("(") || !oneLetter.equals(")") || !oneLetter.equals("-")) {
                result += oneLetter;
            }
        }
        while (scanner.hasNext()) {
            if (!scanner.hasNextInt()) {
                return "Invalid phone number!\n";
            }
        }
        return "";
    }

    /** main method for testing / setting up the GUI */
    public static void main(String[] args)
    {
        /*
         * you can add other method calls here for testing
         */

        System.out.println(isAllAlpha(""));
        System.out.println(isAllAlpha(""));
        System.out.println(isNumeric(""));
        System.out.println(isNumeric("));

        //set up the GUI, you don't need to understand this code
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                //the frame is the GUI, it uses a ValidateForm object
                TextComponentFrame frame = new TextComponentFrame(new ValidateForm());
                frame.setVisible(true);
            }
        });
    }
}










/**
 * A frame (GUI) with simple text components to simulate a web form
 */
class TextComponentFrame extends JFrame
{
    static final int DEFAULT_WIDTH  = 300;
    static final int DEFAULT_HEIGHT = 400;

    ValidateForm validater;

    public TextComponentFrame(ValidateForm v)
    {
        validater = v;

        initGUI();

        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null); //center on screen
    }

    /** initialize the GUI components, e.g. buttons and text fields */
    private void initGUI()
    {
        setTitle("Subscription Form");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        final JTextField personName = new JTextField();
        final JTextField emailField = new JTextField();
        final JTextField zipCode    = new JTextField();
        final JTextField birthDate  = new JTextField();

        MaskFormatter mfor = null;
        try {
            mfor = new MaskFormatter("###-###-####"); //for the phone number field
        }
        catch (ParseException e) {}

        final JFormattedTextField phoneNumber   = new JFormattedTextField(mfor);
        final JPasswordField      passwordField = new JPasswordField();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(6, 6)); //dimensions of layout
        northPanel.add(new JLabel("Name :  ", SwingConstants.RIGHT));
        northPanel.add(personName);
        northPanel.add(new JLabel("Password :  ", SwingConstants.RIGHT));
        northPanel.add(passwordField);
        northPanel.add(new JLabel("Email : ", SwingConstants.RIGHT));
        northPanel.add(emailField);
        northPanel.add(new JLabel("Zip Code (US) : ", SwingConstants.RIGHT));
        northPanel.add(zipCode);
        northPanel.add(new JLabel("Year of Birth: ", SwingConstants.RIGHT));
        northPanel.add(birthDate);
        northPanel.add(new JLabel("Phone Number: ", SwingConstants.RIGHT));
        northPanel.add(phoneNumber);

        add(northPanel, BorderLayout.NORTH);

        final JTextArea textArea = new JTextArea(8, 40);
        JScrollPane scrollPane   = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);

        // add button to listen for events, append text into the text area

        JPanel  southPanel   = new JPanel();
        JButton submitButton = new JButton("Submit");
        southPanel.add(submitButton);
        submitButton.addActionListener(new ActionListener() //make anonymous action listener
        {
            /** this method is called in response to an event, in this case the "Submit" button pressed */
            public void actionPerformed(ActionEvent event)
            {
                String name     = personName.getText() ;
                String email    = emailField.getText();
                String zip      = zipCode.getText();
                String birth    = birthDate.getText();
                String phone    = phoneNumber.getText();
                String password = new String(passwordField.getPassword());

                String result = "";

                result += validater.checkName(name);
                result += validater.checkPW(password);
                result += validater.checkEmail(email);
                result += validater.checkZip(zip);
                result += validater.checkBirth(birth);
                result += validater.checkPhone(phone);

                if (result.length() == 0)
                    result = "Input accepted!";

                textArea.setText(result);
            }
        });

        add(southPanel, BorderLayout.SOUTH);
    }
}
