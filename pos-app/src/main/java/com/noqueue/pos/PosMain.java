package com.noqueue.pos;

import com.noqueue.pos.ui.BaseView;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PosMain {

	public static void main(String[] args)
		throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
		System.out.println("Pos Main");
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				BaseView bv = new BaseView();
				bv.setVisible(true);
			}
		});

	}
}
