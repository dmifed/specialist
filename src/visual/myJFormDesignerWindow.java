/*
 * Created by JFormDesigner on Tue Aug 18 15:45:06 GMT+03:00 2020
 */

package visual;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Дмитрий Федоров
 */
public class myJFormDesignerWindow extends JFrame {
    public myJFormDesignerWindow() {

        initComponents();
        setContentPane(contentPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        menuItem2.addActionListener(e -> {
            System.out.println("Click exit");
            if(JOptionPane.showConfirmDialog(menuItem2, "Realy quit?", "Quiting", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION){
                System.exit(0);
            }
        });
    }

    private void menuItem2MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Дмитрий Федоров
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();
        helpButton = new JButton();
        textField1 = new JTextField();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
            . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder
            . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .
            awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,dialogPane. getBorder () ) )
            ; dialogPane. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
            ;
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

                //======== menuBar1 ========
                {

                    //======== menu1 ========
                    {
                        menu1.setText("main menu");

                        //---- menuItem1 ----
                        menuItem1.setText("start");
                        menu1.add(menuItem1);

                        //---- menuItem2 ----
                        menuItem2.setText("exit");
                        menuItem2.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                menuItem2MouseClicked(e);
                                menuItem2MouseClicked(e);
                            }
                        });
                        menu1.add(menuItem2);
                    }
                    menuBar1.add(menu1);
                }
                contentPanel.add(menuBar1);
            }
            dialogPane.add(contentPanel, BorderLayout.NORTH);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- helpButton ----
                helpButton.setText("Help");
                buttonBar.add(helpButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);

            //---- textField1 ----
            textField1.setText("insert a text");
            dialogPane.add(textField1, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public static void main(String[] args) {
        new myJFormDesignerWindow();
    }



    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Дмитрий Федоров
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private JButton helpButton;
    private JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
