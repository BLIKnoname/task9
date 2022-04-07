package com.metanit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

    public class TableForm extends JFrame implements ActionListener {
        private JButton solveButton;
        private JButton chooseFileButton;
        private JTextField columns;
        private JTextField n;
        private JButton changeSize;
        private JButton saveButton;
        private JLabel columnLabel;
        private JLabel nLabel;
        private JTable table;
        private JScrollPane scrollPane;

        public TableForm() {
            this.setTitle("Таблица");
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);

            Container formElementsContainer = getContentPane();
            formElementsContainer.setLayout(null);

            table = new JTable(1, 10);
            scrollPane = new JScrollPane(table);
            scrollPane.setSize(500, 300);
            scrollPane.setLocation(0, 60);
            formElementsContainer.add(scrollPane);

            columns = new JTextField(5);
            columns.setSize(50, 50);
            columns.setLocation(225, 0);
            formElementsContainer.add(columns);

            columnLabel = new JLabel("enter columns: ");
            columnLabel.setLabelFor(columns);
            columnLabel.setSize(90, 10);
            columnLabel.setLocation(135, 25);
            add(columnLabel);

            n = new JTextField(1);
            n.setSize(50, 50);
            n.setLocation(350, 0);
            formElementsContainer.add(n);

            nLabel = new JLabel("enter n: ");
            nLabel.setLabelFor(n);
            nLabel.setSize(90, 10);
            nLabel.setLocation(290, 25);
            add(nLabel);

            solveButton = new JButton("Посчитать");
            solveButton.setSize(100, 50);
            solveButton.setLocation(0, 350);
            solveButton.addActionListener(this);
            formElementsContainer.add(solveButton);

            chooseFileButton = new JButton("Загрузить из файла");
            chooseFileButton.setSize(100, 50);
            chooseFileButton.setLocation(120, 350);
            chooseFileButton.addActionListener(this);
            formElementsContainer.add(chooseFileButton);

            changeSize = new JButton("Изменить размер");
            changeSize.setSize(100, 50);
            changeSize.setLocation(240, 350);
            changeSize.addActionListener(this);
            formElementsContainer.add(changeSize);

            saveButton = new JButton("Сохранить");
            saveButton.setSize(100, 50);
            saveButton.setLocation(340, 350);
            saveButton.addActionListener(this);
            formElementsContainer.add(saveButton);

            this.setSize(500, 500);
            this.setVisible(true);
        }

        // обработка событий
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == solveButton) { // при нажатии на кнопку посчитать запускается функция handleSolveButtonClick()
                handleSolveButtonClick();
            } else if (e.getSource() == chooseFileButton) { //при нажатии на кнопку выбрать файл запускается функция loadDataFromFile()
                loadDataFromFile();
            } else if (e.getSource() == changeSize) { // и тд..
                setTableSize();
            } else if (e.getSource() == saveButton) {
                saveTable();
            }
        }

        private void saveTable() {
            JFileChooser fileChooser = new JFileChooser(); // создаем окошко выбора файла
            int openStatus = fileChooser.showOpenDialog(null);

            if (openStatus == JFileChooser.APPROVE_OPTION) {
                File inputFile = fileChooser.getSelectedFile(); // получаем выбранный файл
                String path = inputFile.getAbsolutePath(); // получаем путь файла
                List<Integer> ints = convertTableToList(table); // превращаем таблицу в массив
                TaskManager.writeToOutputFile(path, ints); // пишем массив в файл
                displayMessage("Сохранено успешно");
            }
        }

        private void setTableSize() {
            try {
                int m = Integer.parseInt(columns.getText());
                if (m < 1) throw new NumberFormatException(); // если данные некорректные кидаем исключение
                remove(scrollPane); // удаляем старую таблицу
                table = new JTable(1, m); // создаем новую
                scrollPane = new JScrollPane(table);
                scrollPane.setSize(500, 300);
                scrollPane.setLocation(0, 60);
                add(scrollPane);// добавляем новую таблицу в окошко нашего приложения
            } catch (NumberFormatException nfe) {
                displayError("enter data correctly");
            }
        }

        private void loadDataFromFile() {
            JFileChooser fileChooser = new JFileChooser();

            int openStatus = fileChooser.showOpenDialog(null);

            if (openStatus == JFileChooser.APPROVE_OPTION) {
                File inputFile = fileChooser.getSelectedFile();
                String path = inputFile.getAbsolutePath();
                ParsedValuePair result = TaskManager.parseInputFile(path);
                convertListToTable(result.getList());
            }
        }

        private void convertListToTable(List<Integer> list) {
            remove(scrollPane);
            table = new JTable(1, list.size());
            scrollPane = new JScrollPane(table);
            scrollPane.setSize(500, 300);
            scrollPane.setLocation(0, 60);
            add(scrollPane);
            for (int i = 0; i < list.size(); i++) {
                table.getModel().setValueAt(list.get(i), 0, i);
            }
        }

        private void handleSolveButtonClick() {
            try {
                List<Integer> list = convertTableToList(table);
                List<Integer> taskAnswer = TaskSolver.createNewList(list,Integer.parseInt(n.getText())); // выполняем решение задачи
                convertListToTable(taskAnswer); // полученный результат превращаем в таблицу
                displayMessage("Выполнено успешно");
            } catch (NumberFormatException exception) {
                displayError("Таблица должна быть заполнена числами");
            }
        }

        private List<Integer> convertTableToList(JTable table) {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel(); // берем модель таблицы

            List<Integer> list = new ArrayList<>(tableModel.getColumnCount());

            for (int c = 0; c < tableModel.getColumnCount(); c++) {
                list.add(Integer.parseInt(String.valueOf(tableModel.getValueAt(0,c)))); // заполняем массив данными из модели таблицы
            }
            return list;
        }

        private void displayMessage(String messageText) {
            JOptionPane.showMessageDialog(this, messageText,
                    "Сообщение", JOptionPane.INFORMATION_MESSAGE);
        }

        private void displayError(String errorText) {
            JOptionPane.showMessageDialog(this, errorText,
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
}
