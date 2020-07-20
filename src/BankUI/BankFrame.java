/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankUI;

import bank.model.BankAccount;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ListIterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ivana Suligoj
 */
public class BankFrame extends JFrame{
    private JLabel fromLabel= new JLabel("Sa računa");
    private JLabel amountLabel=new JLabel("Iznos: ");
    private JLabel toLabel= new JLabel("Na račun: ");
    private JComboBox<BankAccount> fromComboBox= new JComboBox<>();
    private JComboBox<BankAccount> toComboBox= new JComboBox<>();
    private JTextField amountTextField= new JTextField(30);
    private JButton transferButton= new JButton("Transfer");

    public BankFrame(){
        super("Bank Transfer");
        setMinimumSize(new Dimension(450, 250));
        setLayout(new GridLayout(4,1));
        JPanel fromPanel= new JPanel();
        JPanel amountPanel= new JPanel();
        JPanel toPanel= new JPanel();
        JPanel transferPanel= new JPanel();
        add(fromPanel);
        add(amountPanel);
        add(toPanel);
        add(transferPanel);        

        
       fromPanel.add(fromLabel);
        List<BankAccount> bankAccounts=BankAccount.loadAll();
        bankAccounts.forEach(bankAccount->fromComboBox.addItem(bankAccount));
       //fromComboBox.setSelectedIndex(-1);
        fromPanel.add(fromComboBox);
        
        amountPanel.add(amountLabel);
        amountPanel.add(amountTextField);
        
        toPanel.add(toLabel);
        bankAccounts.forEach(bankAccount->toComboBox.addItem(bankAccount));
        //toComboBox.setSelectedIndex(-1);
        toPanel.add(toComboBox);
      
       transferButton.addActionListener((event)-> executeTranfer(event));// ili this::executeTransfer
       transferPanel.add(transferButton);
    }

    public void updateComboBox(){ 
    fromComboBox.removeAllItems();
    toComboBox.removeAllItems();
    List<BankAccount> bankAccounts=BankAccount.loadAll();
    bankAccounts.forEach(bankAccount->fromComboBox.addItem(bankAccount));
    bankAccounts.forEach(bankAccount->toComboBox.addItem(bankAccount));
  }   
   
    private void executeTranfer(ActionEvent event){
    BankAccount fromAccount=(BankAccount)fromComboBox.getSelectedItem();
    BankAccount toAccount=(BankAccount) toComboBox.getSelectedItem();
    Double ammount= Double.parseDouble(amountTextField.getText());
    BankAccount.transferMoney(fromAccount, toAccount, ammount);
    updateComboBox();
    amountTextField.setText(" ");
   
    //fromComboBox.setSelectedIndex(-1);
    //toComboBox.setSelectedIndex(-1);
}
public void showFrame(){
    pack();
    setVisible(true);
    
}
    public static void main(String[] args) {
      BankFrame bankFrame= new BankFrame();
        SwingUtilities.invokeLater(bankFrame::showFrame);
    }
    
  

}
