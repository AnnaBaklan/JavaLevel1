package Lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class ParameterWindow extends JFrame {

    private static final int DEFAULT_FIELD_SIZE = 3;
    private List<JRadioButton> dotSymbolChoiceList = new ArrayList <JRadioButton> ();
    private static JTextField fieldSizeFrame = new JTextField(20);
    private static  ButtonGroup dotSymbolChoiceGroup = new ButtonGroup();


    public ParameterWindow() {
        setBounds(300, 300, 500, 500);
        setTitle("Tic Tac Toe parameters");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        fieldSizeFrame.setText(Integer.toString(DEFAULT_FIELD_SIZE));
        JLabel fieldSizeLabel = new JLabel("Введите размерность поля: ");
        JPanel fieldSizePanel = new JPanel();
        fieldSizePanel.setLayout(new FlowLayout());
        fieldSizePanel.add( fieldSizeLabel );
        fieldSizePanel.add( fieldSizeFrame );

        fieldSizePanel.setBounds(20, 20, 300, 60);

        add (fieldSizePanel, BorderLayout.NORTH);

        JPanel dotSymbolChoicePanel = new JPanel();
        dotSymbolChoicePanel.setLayout(new FlowLayout());

        JLabel dotSymbolLabel = new JLabel("Выберите каким символом Вы будете играть: ");
        dotSymbolChoicePanel.add(dotSymbolLabel);

        for (char dotSymbol : TicTacToe.getAvailableDotSymbols()) {
            JRadioButton dotSymbolButton = new JRadioButton(Character.toString(dotSymbol));
            dotSymbolButton.setMnemonic(KeyEvent.VK_B);
            dotSymbolButton.setActionCommand(Character.toString(dotSymbol));
            dotSymbolChoiceGroup.add(dotSymbolButton);
            dotSymbolChoiceList.add(dotSymbolButton);
            dotSymbolChoicePanel.add(dotSymbolButton);
        }

        dotSymbolChoicePanel.setBounds(60, 20, 300, 120);
        add(dotSymbolChoicePanel, BorderLayout.CENTER);

        final JButton playButton = new JButton("Играть!");
        add(playButton, BorderLayout.SOUTH);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               if(checkParameters())  {
                   for (JRadioButton button : dotSymbolChoiceList) {
                       if (button.isSelected()) {
                           char userChatDot = button.getText().charAt(0);
                           setVisible(false);
                           new TicTacToe(Integer.parseInt(fieldSizeFrame.getText()), userChatDot);
                       }
                   }

               }
            }
        });



        setVisible(true);
    }

    private static boolean checkParameters () {
        int fieldSize;
        try {
            fieldSize = Integer.parseInt(fieldSizeFrame.getText());
        } catch (Exception e) {
            new ErrorWindow("Размер поля должен быть задан как число");
            fieldSizeFrame.setText("");
            return false;
        }
        if (!TicTacToe.checkFieldSize(fieldSize)) {
            new ErrorWindow("Размер поля должен быть больше 1");
            fieldSizeFrame.setText("");
            return false;
        }
        try {
            dotSymbolChoiceGroup.getSelection().isSelected();
        } catch (Exception e) {
            new ErrorWindow("Нужно выбрать символ для игры");
            return false;
        }
        return true;
    }






}
