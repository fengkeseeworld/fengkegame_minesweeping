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
 * 扫雷游戏
 * 
 * @author 锋客 功能： 时间限制；难度选择。
 * 
 */
public class MineSweepGame {

	// 声明所需组件
	private JFrame frame;
	private Container contentPane;// 视图
	private JPanel menuPanel, timePanel, gamePanel;// 菜单，时间，游戏三个部分
	private JLabel timeLabel, resutLabel, mineCountLabel;// 时间标签，状态标签，地雷数量
	private JMenuItem menuItem1, menuItem2, menuItem3;// 游戏难度选择

	private JButton[][] buttons;
	private int[][] buttonsValue;
	private boolean[][] buttonsFlag;

	private int timeLength = 0;
	private int row, col;

	private int mineRealCount = 10;
	private boolean winGame = false;
	// 计时器
	private Timer timer;
	private int gameStatus = 0;
	private TimerActionListener temp = new TimerActionListener();
	// 构造方法
	public MineSweepGame() {
		row = 9;
		col = 9;

		frame = new JFrame("锋客扫雷游戏");
		contentPane = frame.getContentPane();

		menuPanel = new JPanel();
		timePanel = new JPanel();
		gamePanel = new JPanel();

		timeLabel = new JLabel("游戏时间：" + new Integer(timeLength).toString()
				+ "秒");
		resutLabel = new JLabel("   状态：准备游戏");
		mineCountLabel = new JLabel("地雷个数：" + mineRealCount);

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

	// 初始化游戏界面
	public void initGame() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("游戏设置");
		menuItem1 = new JMenuItem("初级");
		menuItem2 = new JMenuItem("中级");
		menuItem3 = new JMenuItem("高级");

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
		// 选择难度(匿名内部类)
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
				mineCountLabel.setText("地雷个数：" + mineRealCount);
				resutLabel.setText("   状态：准备游戏");
				// 设置埋雷区域
				gamePanel.removeAll();
				initButtonsAllValues();
				gamePanel.setLayout(new GridLayout(row, col, 0, 0));
				for (int i = 1; i <= row; i++) {
					for (int j = 1; j <= col; j++) {
						gamePanel.add(buttons[i][j]);
					}
				}
				contentPane.add(gamePanel, BorderLayout.CENTER);
				// 设置地雷
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
				mineCountLabel.setText("地雷个数：" + mineRealCount);
				resutLabel.setText("   状态：准备游戏");
				// 设置埋雷区域
				gamePanel.removeAll();
				initButtonsAllValues();
				gamePanel.setLayout(new GridLayout(row, col, 0, 0));
				for (int i = 1; i <= row; i++) {
					for (int j = 1; j <= col; j++) {
						gamePanel.add(buttons[i][j]);
					}
				}
				contentPane.add(gamePanel, BorderLayout.CENTER);
				// 设置地雷
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
				mineCountLabel.setText("地雷个数：" + mineRealCount);
				resutLabel.setText("   状态：准备游戏");
				// 设置埋雷区域
				gamePanel.removeAll();
				initButtonsAllValues();
				gamePanel.setLayout(new GridLayout(row, col, 0, 0));
				for (int i = 1; i <= row; i++) {
					for (int j = 1; j <= col; j++) {
						gamePanel.add(buttons[i][j]);
					}
				}

				contentPane.add(gamePanel, BorderLayout.CENTER);

				// 设置地雷
				timeLength = 0;
				setMines(mineRealCount);
				setButtonValue();
				addListener();

			}
		});

	}

	// 设置地雷
	public void setMines(int mineCount) {
		this.mineRealCount = mineCount;
		int[] randomValue = new int[mineCount];
		// mineCount是地雷的个数，先获得mineCount个不重复的随机数，然后通过随机数计算出地雷的位置
		for (int i = 0; i < mineCount; i++) {

			int temp = (int) (Math.random() * row * col);
			for (int j = 0; j < randomValue.length; j++) {
				if (randomValue[j] == temp) {
					temp = (int) (Math.random() * row * col);
					j = 0;
				}

			}
			randomValue[i] = temp;
			// 把随机数转换成坐标
			int x = randomValue[i] / col + 1;
			int y = randomValue[i] % col + 1;
			// 对应坐标的位置设置为地雷
			buttonsValue[x][y] = 10;
			// 临时显示地雷位置，作为测试使用，随机产生
			// buttons[x][y].setText("Q");

		}
	}

	// 对非地雷的按钮进行计算，周围没有地雷的，默认值为0，有雷的，显示地雷的个数。
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

					// 测试
					//buttons[i][j].setText(new Integer(buttonsValue[i][j])
					//		.toString());

				}

			}

		}
	}

	// 点击事件
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
								System.out.println("停止时间");
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

	// 设置地雷属性
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

	// 计时器
	class TimerActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			timeLength++;
			timeLabel.setText("游戏时间：" + new Integer(timeLength).toString()
					+ "秒");

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
			// resutLabel.setText("恭喜你，你赢了！");
			//
			// }else{
			// resutLabel.setText("很可惜,时间到！");
			//
			// }
			// }
			//
			// }
			// }
		}

	}
    //添加监听器
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
    //标记地雷
	public void markMine(int i, int j) {
		buttons[i][j].setForeground(Color.red);
		buttons[i][j].setText("Q");
		buttons[i][j].setEnabled(false);
		buttonsFlag[i][j] = true;

	}
    //标记空白区域
	public void markZero(int i, int j) {
		// 注意问题：当选择的是地雷时，不进行任何操作
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
						// 注意括号的问题
						for (int t = j - 1; t >= 0 && t <= col && t <= j + 1; t++) {
							markZero(s, t);
						}
				}

			}
		}

	}

	// 发现地雷处理
	public void findMine(int i, int j) {
		if(buttons[i][j].getText()=="Q"){
			buttons[i][j].setForeground(Color.yellow);
			buttons[i][j].setText("？");
		
	}else{
		buttons[i][j].setForeground(Color.red);
		buttons[i][j].setText("Q");
		if (buttonsValue[i][j] == 10) {
			mineRealCount--;
		}
		isWinner();
	}
}
	

	// 判断游戏是否结束
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
			resutLabel.setText("恭喜你，你赢了！");

		} else {
			resutLabel.setText("你踩到地雷了，很可惜！");
		}
	}

	// 判断是否游戏胜利
	public void isWinner() {
		if (mineRealCount == 0) {
			winGame = true;
			stopGame();
		}
	}

}
