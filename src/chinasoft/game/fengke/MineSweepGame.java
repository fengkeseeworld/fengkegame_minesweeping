package chinasoft.game.fengke;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * ɨ����Ϸ
 * 
 * @author ��� ���ܣ� ʱ�����ƣ��Ѷ�ѡ��
 * 
 */
public class MineSweepGame {

	// �����������
	private JFrame frame;
	private Container contentPane;// ��ͼ
	private JPanel menuPanel, timePanel, gamePanel;// �˵���ʱ�䣬��Ϸ��������
	private JLabel timeLabel, resutLabel, mineCountLabel;// ʱ���ǩ��״̬��ǩ����������
	private JMenuItem menuItem1, menuItem2, menuItem3;// ��Ϸ�Ѷ�ѡ��

	private JButton[][] buttons;
	private int[][] buttonsValue;
	private boolean[][] buttonsFlag;

	private int timeLength = 0;
	private int row, col;

	private int mineRealCount = 10;
	private boolean winGame = false;
	// ��ʱ��
	private Timer timer;
	private int gameStatus = 0;
	private TimerActionListener temp = new TimerActionListener();
	// ���췽��
	public MineSweepGame() {
		row = 9;
		col = 9;

		frame = new JFrame("���ɨ����Ϸ");
		contentPane = frame.getContentPane();

		menuPanel = new JPanel();
		timePanel = new JPanel();
		gamePanel = new JPanel();

		timeLabel = new JLabel("��Ϸʱ�䣺" + new Integer(timeLength).toString()
				+ "��");
		resutLabel = new JLabel("   ״̬��׼����Ϸ");
		mineCountLabel = new JLabel("���׸�����" + mineRealCount);

		timer = new Timer(1000,temp);
	}

	public void initButtonsAllValues() {
		buttons = new JButton[row + 2][col + 2];
		buttonsValue = new int[row + 2][col + 2];
		buttonsFlag = new boolean[row + 2][col + 2];
		for (int i = 0; i < row + 2; i++) {
			for (int j = 0; j < col + 2; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setMargin(new Insets(0, 0, 0, 0));
				buttons[i][j].setFont(new Font(null, Font.BOLD, 25));
				buttons[i][j].setText("");
				buttonsValue[i][j] = 0;

			}

		}

	}

	// ��ʼ����Ϸ����
	public void initGame() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("��Ϸ����");
		menuItem1 = new JMenuItem("����");
		menuItem2 = new JMenuItem("�м�");
		menuItem3 = new JMenuItem("�߼�");

		menuBar.add(menu);
		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.add(menuItem3);
		menuPanel.add(menuBar);

		timePanel.add(timeLabel);
		timePanel.add(mineCountLabel);
		timePanel.add(resutLabel);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(menuPanel, BorderLayout.NORTH);
		panel.add(timePanel, BorderLayout.SOUTH);
		contentPane.add(panel, BorderLayout.NORTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(297, 377);
		frame.setBounds(400, 100, 400, 500);
		frame.setVisible(true);
		// ѡ���Ѷ�(�����ڲ���)
		menuItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameStatus == 0) {
					timer.start();
				}
				gameStatus = 1;
				row = 9;
				col = 9;
				mineRealCount = 10;
				mineCountLabel.setText("���׸�����" + mineRealCount);
				resutLabel.setText("   ״̬��׼����Ϸ");
				// ������������
				gamePanel.removeAll();
				initButtonsAllValues();
				gamePanel.setLayout(new GridLayout(row, col, 0, 0));
				for (int i = 1; i <= row; i++) {
					for (int j = 1; j <= col; j++) {
						gamePanel.add(buttons[i][j]);
					}
				}
				contentPane.add(gamePanel, BorderLayout.CENTER);
				// ���õ���
				timeLength = 0;
				setMines(mineRealCount);
				setButtonValue();
				addListener();

			}
		});
		menuItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameStatus == 0) {
					timer.start();
				}
				gameStatus = 1;
				row = 9;
				col = 9;
				mineRealCount = 30;
				mineCountLabel.setText("���׸�����" + mineRealCount);
				resutLabel.setText("   ״̬��׼����Ϸ");
				// ������������
				gamePanel.removeAll();
				initButtonsAllValues();
				gamePanel.setLayout(new GridLayout(row, col, 0, 0));
				for (int i = 1; i <= row; i++) {
					for (int j = 1; j <= col; j++) {
						gamePanel.add(buttons[i][j]);
					}
				}
				contentPane.add(gamePanel, BorderLayout.CENTER);
				// ���õ���
				timeLength = 0;
				setMines(mineRealCount);
				setButtonValue();
				addListener();

			}
		});
		menuItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameStatus == 0) {
					timer.start();
				}
				gameStatus = 1;
				row = 15;
				col = 15;
				mineRealCount = 15;
				mineCountLabel.setText("���׸�����" + mineRealCount);
				resutLabel.setText("   ״̬��׼����Ϸ");
				// ������������
				gamePanel.removeAll();
				initButtonsAllValues();
				gamePanel.setLayout(new GridLayout(row, col, 0, 0));
				for (int i = 1; i <= row; i++) {
					for (int j = 1; j <= col; j++) {
						gamePanel.add(buttons[i][j]);
					}
				}

				contentPane.add(gamePanel, BorderLayout.CENTER);

				// ���õ���
				timeLength = 0;
				setMines(mineRealCount);
				setButtonValue();
				addListener();

			}
		});

	}

	// ���õ���
	public void setMines(int mineCount) {
		this.mineRealCount = mineCount;
		int[] randomValue = new int[mineCount];
		// mineCount�ǵ��׵ĸ������Ȼ��mineCount�����ظ����������Ȼ��ͨ���������������׵�λ��
		for (int i = 0; i < mineCount; i++) {

			int temp = (int) (Math.random() * row * col);
			for (int j = 0; j < randomValue.length; j++) {
				if (randomValue[j] == temp) {
					temp = (int) (Math.random() * row * col);
					j = 0;
				}

			}
			randomValue[i] = temp;
			// �������ת��������
			int x = randomValue[i] / col + 1;
			int y = randomValue[i] % col + 1;
			// ��Ӧ�����λ������Ϊ����
			buttonsValue[x][y] = 10;
			// ��ʱ��ʾ����λ�ã���Ϊ����ʹ�ã��������
			// buttons[x][y].setText("Q");

		}
	}

	// �Էǵ��׵İ�ť���м��㣬��Χû�е��׵ģ�Ĭ��ֵΪ0�����׵ģ���ʾ���׵ĸ�����
	public void setButtonValue() {
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				if (buttonsValue[i][j] != 10) {
					for (int x = j - 1; x <= j + 1; x++) {
						if (buttonsValue[i - 1][x] == 10) {
							buttonsValue[i][j]++;
						}
						if (buttonsValue[i + 1][x] == 10) {
							buttonsValue[i][j]++;
						}

					}
					if (buttonsValue[i][j - 1] == 10) {
						buttonsValue[i][j]++;
					}
					if (buttonsValue[i][j + 1] == 10) {
						buttonsValue[i][j]++;
					}

					// ����
					//buttons[i][j].setText(new Integer(buttonsValue[i][j])
					//		.toString());

				}

			}

		}
	}

	// ����¼�
	class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			for (int i = 1; i <= row; i++) {
				for (int j = 1; j <= col; j++) {
					if (e.getSource() == buttons[i][j]) {
						if (!buttons[i][j].getText().isEmpty()
								&& buttonsFlag[i][j] == false) {
							buttons[i][j].setText("");
						} else {
							if (buttonsValue[i][j] == 0) {
								markZero(i, j);
							} else if (buttonsValue[i][j] == 10) {
								System.out.println("ֹͣʱ��");
								stopGame();
							} else {
								markNumber(i, j);
							}
						}
					}

				}

			}

		}

	}

	// ���õ�������
	class FindMineMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			for (int i = 1; i <= row; i++) {
				for (int j = 1; j <= col; j++) {
					if (e.getSource() == buttons[i][j]
							&& e.getButton() == MouseEvent.BUTTON3) {
						if (buttonsFlag[i][j] == false) {
							findMine(i, j);
						}

					}

				}

			}
		}
	}

	// ��ʱ��
	class TimerActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			timeLength++;
			timeLabel.setText("��Ϸʱ�䣺" + new Integer(timeLength).toString()
					+ "��");

			// if (timeLength>=20) {
			// for (int i = 1; i <= row; i++) {
			// for (int j = 1; j <=col; j++) {
			// if (buttonsFlag[i][j]==false) {
			// if (buttonsValue[i][j]==0) {
			// buttons[i][j].setText("");
			// }else if(buttonsValue[i][j]==10){
			// buttons[i][j].setText("Q");
			// }else{
			// buttons[i][j].setText(new
			// Integer(buttonsValue[i][j]).toString());
			// }
			// }
			// buttons[i][j].setEnabled(false);
			// timer.stop();
			// gameStatus=0;
			// if (winGame) {
			// resutLabel.setText("��ϲ�㣬��Ӯ�ˣ�");
			//
			// }else{
			// resutLabel.setText("�ܿ�ϧ,ʱ�䵽��");
			//
			// }
			// }
			//
			// }
			// }
		}

	}
    //��Ӽ�����
	public void addListener() {
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				buttons[i][j].addActionListener(new ButtonActionListener());
				buttons[i][j].addMouseListener(new FindMineMouseListener());

			}

		}
	}
    
	public void markNumber(int i, int j) {
		buttons[i][j].setText(new Integer(buttonsValue[i][j]).toString());
		buttons[i][j].setEnabled(false);
		buttonsFlag[i][j] = true;
	}
    //��ǵ���
	public void markMine(int i, int j) {
		buttons[i][j].setForeground(Color.red);
		buttons[i][j].setText("Q");
		buttons[i][j].setEnabled(false);
		buttonsFlag[i][j] = true;

	}
    //��ǿհ�����
	public void markZero(int i, int j) {
		// ע�����⣺��ѡ����ǵ���ʱ���������κβ���
		if (buttonsValue[i][j] == 10) {
			return;
		} else {
			buttons[i][j].setEnabled(false);
			if (buttonsFlag[i][j] == true) {
				return;
			} else {
				buttonsFlag[i][j] = true;
				if (buttonsValue[i][j] != 10 && buttonsValue[i][j] != 0) {
					markNumber(i, j);
				}
				if (buttonsValue[i][j] == 0) {
					buttons[i][j].setText("");
					for (int s = i - 1; s >= 0 && s <= row && s <= i + 1; s++)
						// ע�����ŵ�����
						for (int t = j - 1; t >= 0 && t <= col && t <= j + 1; t++) {
							markZero(s, t);
						}
				}

			}
		}

	}

	// ���ֵ��״���
	public void findMine(int i, int j) {
		if(buttons[i][j].getText()=="Q"){
			buttons[i][j].setForeground(Color.yellow);
			buttons[i][j].setText("��");
		
	}else{
		buttons[i][j].setForeground(Color.red);
		buttons[i][j].setText("Q");
		if (buttonsValue[i][j] == 10) {
			mineRealCount--;
		}
		isWinner();
	}
}
	

	// �ж���Ϸ�Ƿ����
	public void stopGame() {
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {

				if (buttonsFlag[i][j] == false) {
					if (buttonsValue[i][j] == 0) {
						buttons[i][j].setText("");
					} else if (buttonsValue[i][j] == 10) {
						buttons[i][j].setText("Q");
					} else {
						buttons[i][j].setText(new Integer(buttonsValue[i][j])
								.toString());
					}
				}

				buttons[i][j].setEnabled(false);

			}

		}
		gameStatus = 0;
		timer.stop();
		if (winGame) {
			resutLabel.setText("��ϲ�㣬��Ӯ�ˣ�");

		} else {
			resutLabel.setText("��ȵ������ˣ��ܿ�ϧ��");
		}
	}

	// �ж��Ƿ���Ϸʤ��
	public void isWinner() {
		if (mineRealCount == 0) {
			winGame = true;
			stopGame();
		}
	}

}
