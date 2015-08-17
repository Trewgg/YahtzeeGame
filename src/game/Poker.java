package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Poker extends JFrame {
    private static final String TITLE = "Yahtzee game";
    private static final int MAX_ROLLS_CNT = 3; //макс кол-во бросков
    private static final int DICES_CNT = 5;     //кол-во кубиков

    private static final int ONE_PAIR = 5;
    private static final int TWO_PAIR = 10;
    private static final int TRIPLE = 15;
    private static final int SHORT_STREET = 20;
    private static final int LONG_STREET = 25;
    private static final int FILL_HOUSE = 30;
    private static final int KARE = 40;
    private static final int POKER = 50;


    private int cntRolls = 0;

    //Массив содержащий заголоки таблицы
    private String[] headers = {"Category", "Score"};

    //Массив содержащий информацию для таблицы
    private String[][] data = {
            {"Aces", null},
            {"Twos", null},
            {"Threes", null},
            {"Fours", null},
            {"Fives", null},
            {"Sixes", null},
            {"SUB-TOTAL", null},
            {null, null},
            {"OnePair (+5)", null},
            {"2 pair (+10)", null},
            {"3 of a kind (+15)", null},
            {"Small Straight (+20)", null},
            {"Large Straight (+25)", null},
            {"Full House (+30)", null},
            {"4 of a kind (+40)", null},
            {"Poker (+50)", null},
            {"TOTAL", null}
    };
    public Poker() {
        initComponents();
    }

    private void initComponents() {

        setTitle(TITLE);
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);    //по центру
        setLayout(new BorderLayout());

        JButton buttonRoll = new JButton("Roll ");
        JButton buttonClean = new JButton("Clean");
        JLabel dice1 = new JLabel(new ImageIcon("resources/0.jpg"));
        JLabel dice2 = new JLabel(new ImageIcon("resources/0.jpg"));
        JLabel dice3 = new JLabel(new ImageIcon("resources/0.jpg"));
        JLabel dice4 = new JLabel(new ImageIcon("resources/0.jpg"));
        JLabel dice5 = new JLabel(new ImageIcon("resources/0.jpg"));

        //JLabel labelTotal = new JLabel(TITLE);

        JTable table = new JTable(data, headers);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panelTable = new JPanel();
        panelTable.add(scrollPane, BorderLayout.NORTH);

        JPanel panelLabels = new JPanel(new GridLayout(8, 1, 5, 5));

        panelLabels.add(dice1);
        panelLabels.add(dice2);
        panelLabels.add(dice3);
        panelLabels.add(dice4);
        panelLabels.add(dice5);
        panelLabels.add(buttonRoll);
        panelLabels.add(buttonClean);

        //добавляем на форму панели
        add(panelLabels, BorderLayout.WEST);
        add(panelTable, BorderLayout.CENTER);

        //кнопка Бросок
        buttonRoll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int randForLabel;
                int sumSub = 0;
                int sumTotal = 0;

                int doubling1 = 0;
                int doubling2 = 0;
                int doubling3 = 0;
                int doubling4 = 0;
                int doubling5 = 0;
                int doubling6 = 0;

                int dice1Value = 0;
                int dice2Value = 0;
                int dice3Value = 0;
                int dice4Value = 0;
                int dice5Value = 0;

                int resultScore = 0;

                boolean onePair;
                boolean twoPair;
                boolean triple;
                boolean shortStreet;
                boolean longStreet;
                boolean fullHouse;
                boolean kare;
                boolean poker;

                cntRolls = cntRolls + 1;

                buttonRoll.setText("Roll " + cntRolls);

                if (cntRolls <= MAX_ROLLS_CNT) {
                    for (int i = 1; i <= DICES_CNT; i++) {
                        randForLabel = getRandom();

                        //изменение иконок в зависимости от выпавших значений
                        switch (i) {
                            case 1: //кубик 1 = Random 1
                                dice1Value = randForLabel;
                                dice1.setIcon(new ImageIcon("resources/" + randForLabel + ".jpg"));
                                break;
                            case 2: //кубик 2 = Random 2
                                dice2Value = randForLabel;
                                dice2.setIcon(new ImageIcon("resources/" + randForLabel + ".jpg"));
                                break;
                            case 3: //кубик 3 = Random 3
                                dice3Value = randForLabel;
                                dice3.setIcon(new ImageIcon("resources/" + randForLabel + ".jpg"));
                                break;
                            case 4: //кубик 4 = Random 4
                                dice4Value = randForLabel;
                                dice4.setIcon(new ImageIcon("resources/" + randForLabel + ".jpg"));
                                break;
                            case 5: //кубик 5 = Random 5
                                dice5Value = randForLabel;
                                dice5.setIcon(new ImageIcon("resources/" + randForLabel + ".jpg"));
                                break;
                        }

                        //очищение таблицы
                        table.setValueAt("", randForLabel - 1, 1);

                        //поиск количества повторений одного номера
                        switch (randForLabel) {
                            case 1:
                                doubling1++;
                                table.setValueAt(String.valueOf(doubling1), randForLabel - 1, 1);
                                break;
                            case 2:
                                doubling2++;
                                table.setValueAt(String.valueOf(doubling2), randForLabel - 1, 1);
                                break;
                            case 3:
                                doubling3++;
                                table.setValueAt(String.valueOf(doubling3), randForLabel - 1, 1);
                                break;
                            case 4:
                                doubling4++;
                                table.setValueAt(String.valueOf(doubling4), randForLabel - 1, 1);
                                break;
                            case 5:
                                doubling5++;
                                table.setValueAt(String.valueOf(doubling5), randForLabel - 1, 1);
                                break;
                            case 6:
                                doubling6++;
                                table.setValueAt(String.valueOf(doubling6), randForLabel - 1, 1);
                                break;
                        }

                        sumSub = sumSub + randForLabel;
                    }
                }

                //строка с суммой
                table.setValueAt(String.valueOf(sumSub), 6, 1);

                //проверка комбинаций
                Combinations combinations = new Combinations();
                onePair = combinations.OnePair(dice1Value, dice2Value, dice3Value, dice4Value, dice5Value);
                twoPair = combinations.TwoPair(dice1Value, dice2Value, dice3Value, dice4Value, dice5Value);
                triple = combinations.Triple(dice1Value, dice2Value, dice3Value, dice4Value, dice5Value);
                shortStreet = combinations.ShortStreet(dice1Value, dice2Value, dice3Value, dice4Value, dice5Value);
                longStreet = combinations.LongStreet(dice1Value, dice2Value, dice3Value, dice4Value, dice5Value);
                fullHouse = combinations.FullHouse(dice1Value, dice2Value, dice3Value, dice4Value, dice5Value);
                kare = combinations.Kare(dice1Value, dice2Value, dice3Value, dice4Value, dice5Value);
                poker = combinations.Poker(dice1Value, dice2Value, dice3Value, dice4Value, dice5Value);

                if (poker) {
                    resultScore = POKER;
                    clearTable(table);
                    table.setValueAt(String.valueOf(resultScore), 15, 1);
                }
                else if (kare){
                    resultScore = KARE;
                    clearTable(table);
                    table.setValueAt(String.valueOf(resultScore), 14, 1);
                }
                else if (fullHouse){
                    resultScore = FILL_HOUSE;
                    clearTable(table);
                    table.setValueAt(String.valueOf(resultScore), 13, 1);
                }
                else if (longStreet){
                    resultScore = LONG_STREET;
                    clearTable(table);
                    table.setValueAt(String.valueOf(resultScore), 12, 1);
                }
                else if (shortStreet){
                    resultScore = SHORT_STREET;
                    clearTable(table);
                    table.setValueAt(String.valueOf(resultScore), 11, 1);
                }
                else if (triple){
                    resultScore = TRIPLE;
                    clearTable(table);
                    table.setValueAt(String.valueOf(resultScore), 10, 1);
                }
                else if (twoPair){
                    resultScore = TWO_PAIR;
                    clearTable(table);
                    table.setValueAt(String.valueOf(resultScore), 9, 1);
                }
                else if (onePair){
                    resultScore = ONE_PAIR;
                    clearTable(table);
                    table.setValueAt(String.valueOf(resultScore), 8, 1);
                } else {
                    resultScore = 0;
                    clearTable(table);
                }

                //строка с общей суммой
                sumTotal = sumSub + resultScore;
                table.setValueAt(String.valueOf(sumTotal), 16, 1);

            }
        });

        //кнопка Очистить
        buttonClean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                buttonRoll.setText("Roll ");
                cntRolls = 0;

                clearFullTable(table);
                clearDice(dice1, dice2, dice3, dice4, dice5);

            }
        });

    }

    //случайное число типа int от 1 до 5
    public int getRandom(){
        return new Random().nextInt(DICES_CNT) + 1;
    }

    //очистить значения на кубиках
    public void clearDice(JLabel dice1, JLabel dice2, JLabel dice3, JLabel dice4, JLabel dice5) {
        for (int i = 1; i <= DICES_CNT; i++) {
            dice1.setIcon(new ImageIcon("resources/0.jpg"));
            dice2.setIcon(new ImageIcon("resources/0.jpg"));
            dice3.setIcon(new ImageIcon("resources/0.jpg"));
            dice4.setIcon(new ImageIcon("resources/0.jpg"));
            dice5.setIcon(new ImageIcon("resources/0.jpg"));
        }
    }

    //очистить в таблице строки 8-15
    public void clearTable(JTable table) {
        for (int i = 8; i <= 15; i++) {
            table.setValueAt("", i, 1);
        }
    }

    //очистить всю таблицу
    public void clearFullTable(JTable table) {
        for (int i = 0; i <= 16; i++) {
            table.setValueAt("", i, 1);
        }
    }
}
