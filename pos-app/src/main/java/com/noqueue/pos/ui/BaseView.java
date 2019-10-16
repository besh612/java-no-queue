package com.noqueue.pos.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BaseView extends JFrame{

	private JPanel rootPanel;
	private JButton button1;
	private JLabel countLabel;
	private int count;

	public BaseView() {
		initUi();
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				count++;
				countLabel.setText("카운트 : " + count);
			}
		});
	}

	private void initUi() {
		add(rootPanel);
		setTitle("줄 안 설 꼬야 POS");
		setSize(500, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		countLabel.setText("카운트 : " + count);
	}
}
