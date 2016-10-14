package generator;

/* 程式範例: Ch11_1_4.java */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class d extends JFrame implements ItemListener {
	Font c6 = new Font("新細明體", 0, 20);
	private JLabel label, label2, label_range, label_digits, label_note;
	int i = 0;
	private int choice = 3;
	private JRadioButton blueButton, yellowButton;
	Container c = getContentPane();
	String range;
	String digits_m;
	int k1;
	int statei = 1, statey = 0;
	String n[] = { "a", "i", "u", "e", "o", "ka", "ki", "ku", "ke", "ko", "sa",
			"si", "su", "se", "so", "ta", "chi", "tsu", "te", "to", "na", "ni",
			"nu", "ne", "no", "ha", "hi", "hu", "he", "ho", "ma", "mi", "mu",
			"me", "mo", "ya", "yu", "yo", "ra", "ri", "ru", "re", "ro", "wa",
			"wo", "n" };
	String sd[] = { "\u3042", "\u3044", "\u3046", "\u3048", "\u304a", "\u304b",
			"\u304d", "\u304f", "\u3051", "\u3053", "\u3055", "\u3057",
			"\u3059", "\u305b", "\u305d", "\u305f", "\u3061", "\u3064",
			"\u3066", "\u3068", "\u306a", "\u306b", "\u306c", "\u306d",
			"\u306e", "\u306f", "\u3072", "\u3075", "\u3078", "\u307b",
			"\u307e", "\u307f", "\u3080", "\u3081", "\u3082", "\u3084",
			"\u3086", "\u3088", "\u3089", "\u308a", "\u308b", "\u308c",
			"\u308d", "\u308f", "\u3092", "\u3093" };
	String ld[] = { "\u30a2", "\u30a4", "\u30a6", "\u30a8", "\u30aa", "\u30ab",
			"\u30ad", "\u30af", "\u30b1", "\u30b3", "\u30b5", "\u30b7",
			"\u30b9", "\u30bb", "\u30bd", "\u30bf", "\u30c1", "\u30c3",
			"\u30c6", "\u30c8", "\u30ca", "\u30cb", "\u30cc", "\u30cd",
			"\u30ce", "\u30cf", "\u30d2", "\u30d5", "\u30d8", "\u30db",
			"\u30de", "\u30df", "\u30e0", "\u30e1", "\u30e2", "\u30e4",
			"\u30e6", "\u30e8", "\u30e9", "\u30ea", "\u30eb", "\u30ec",
			"\u30ed", "\u30ef", "\u30f2", "\u30f3"

	};

	// 建構子
	public d() {

		super("The efficient P@ssW0rd Creater,author:Prag222");

		final JTextField text = new JTextField(15);
		final JTextField text2 = new JTextField(3);
		c.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		String[] items = { "defult(faster)", "order(slower)" };
		JComboBox list = new JComboBox(items);
		list.setSelectedIndex(0);
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JComboBox jcb = (JComboBox) evt.getSource();
				String name = (String) jcb.getSelectedItem();
				if (name.equals("defult(faster)")) {
					statei = 1;
				} else {
					if (name.equals("order(slower)")) {
						statei = 2;
					}
				}
			}
		});
		//
		ButtonGroup buttonGroup = new ButtonGroup();
		blueButton = new JRadioButton("平假名");
		blueButton.setMnemonic(KeyEvent.VK_B);
		blueButton.addItemListener(this);
		buttonGroup.add(blueButton);
		yellowButton = new JRadioButton("片假名");
		yellowButton.setMnemonic(KeyEvent.VK_Y);
		yellowButton.addItemListener(this);
		buttonGroup.add(yellowButton);
		//
		label = new JLabel("Make Password By");
		label_note = new JLabel(
				"Hello~,This is my first published program,just share to you guys~!");
		label_range = new JLabel("Range:");
		label_digits = new JLabel("Digits:");
		JPanel jpane = new JPanel();
		JPanel jpane2 = new JPanel();
		JPanel jpane3 = new JPanel();
		// button
		JButton button = new JButton("開始測驗");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				label2.setText("                                            ");
				if (statei == 0 || statey == 0) {
					label.setText("     請選擇單字範圍及片平假名 ");
				} else {
					label.setFont(c6);
					if (statei == 1) {
						Random c = new Random();
						int k = c.nextInt(46);
						k1 = k;
						if (statey == 1)
							label.setText("                " + sd[k]
									+ "               ");
						else
							label.setText("                " + ld[k]
									+ "               ");
					}
					if (statei == 2) {
						Random c = new Random();
						int k = c.nextInt(5);
						k1 = k;
						if (statey == 1)
							label.setText("                " + sd[k]
									+ "               ");
						else
							label.setText("                " + ld[k]
									+ "               ");
					}
					if (statei == 3) {
						Random c = new Random();
						int k = c.nextInt(5);
						k1 = k;
						k += 5;
						k1 = k;
						if (statey == 1)
							label.setText("                " + sd[k]
									+ "               ");
						else
							label.setText("                " + ld[k]
									+ "               ");
					}
					if (statei == 4) {
						Random c = new Random();
						int k = c.nextInt(5);
						k1 = k;
						k += 10;
						k1 = k;
						if (statey == 1)
							label.setText("                " + sd[k]
									+ "               ");
						else
							label.setText("                " + ld[k]
									+ "               ");
					}
					if (statei == 5) {
						Random c = new Random();
						int k = c.nextInt(5);
						k1 = k;
						k += 15;
						k1 = k;
						if (statey == 1)
							label.setText("                " + sd[k]
									+ "               ");
						else
							label.setText("                " + ld[k]
									+ "               ");
					}
					if (statei == 6) {
						Random c = new Random();
						int k = c.nextInt(5);
						k1 = k;
						k += 20;
						k1 = k;
						if (statey == 1)
							label.setText("                " + sd[k]
									+ "               ");
						else
							label.setText("                " + ld[k]
									+ "               ");
					}
					if (statei == 7) {
						Random c = new Random();
						int k = c.nextInt(5);
						k1 = k;
						k += 25;
						k1 = k;
						if (statey == 1)
							label.setText("                " + sd[k]
									+ "               ");
						else
							label.setText("                " + ld[k]
									+ "               ");
					}
					if (statei == 8) {
						Random c = new Random();
						int k = c.nextInt(5);
						k1 = k;
						k += 30;
						k1 = k;
						if (statey == 1)
							label.setText("                " + sd[k]
									+ "               ");
						else
							label.setText("                " + ld[k]
									+ "               ");
					}
					if (statei == 9) {
						Random c = new Random();
						int k = c.nextInt(3);
						k1 = k;
						k += 35;
						k1 = k;
						if (statey == 1)
							label.setText("                " + sd[k]
									+ "               ");
						else
							label.setText("                " + ld[k]
									+ "               ");
					}
					if (statei == 10) {
						Random c = new Random();
						int k = c.nextInt(5);
						k1 = k;
						k += 38;
						k1 = k;
						if (statey == 1)
							label.setText("                " + sd[k]
									+ "               ");
						else
							label.setText("                " + ld[k]
									+ "               ");
					}
					if (statei == 11) {
						Random c = new Random();
						int k = c.nextInt(2);
						k1 = k;
						k += 43;
						k1 = k;
						if (statey == 1)
							label.setText("                " + sd[k]
									+ "               ");
						else
							label.setText("                " + ld[k]
									+ "               ");
					}
					if (statei == 12) {
						Random c = new Random();
						int k = 45;
						k1 = k;
						if (statey == 1)
							label.setText("                " + sd[k]
									+ "               ");
						else
							label.setText("                " + ld[k]
									+ "               ");
					}
				}
			}
		});
		// button
		JButton button3 = new JButton("Run");

		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				range = text.getText();
				digits_m = text2.getText();
				String[] sourceStrArray = range.split(",");

				int digits = Integer.parseInt(digits_m); // 位數
				int char_num = sourceStrArray.length;
				char[] a1 = new char[char_num];
				for (int i = 0; i < sourceStrArray.length; i++) {
					a1[i] = sourceStrArray[i].charAt(0);
					System.out.println(a1[i]);
				}
				// ==========start=========
				if (statei == 1) {
					create_ps(a1, digits, char_num);
				} else {
					create_ps2(a1, digits, char_num);
				}
				// ==========end===========
			}
		});

		//
		c.add(jpane);
		jpane.add(label);
		jpane.add(list);
		// jpane.add(blueButton);
		// jpane.add(yellowButton);

		// c.add(button) ;
		// c.add(label2);
		jpane2.add(label_range);
		jpane2.add(text);
		jpane2.add(label_digits);
		jpane2.add(text2);
		// jpane3.add(label_note);
		c.add(jpane2);
		c.add(button3);
		// c.add(jpane3);
	}

	public void itemStateChanged(ItemEvent evt) {
		if (evt.getSource() == blueButton) {
			statey = 1;
		} else {
			statey = 2;
		}
	}

	public static void create_ps(char a1[], int digits, int char_num) {

		String file = "c:\\dictionary.txt";
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			String write_in = "";

			int total = 1;
			for (int u = 1; u < digits; u++) {
				total = char_num * total;
			}
			System.out.println(total);
			String str1 = "";
			for (int i = 0; i < digits - 1; i++) {
				str1 = str1 + a1[0];
			}
			String temp = str1;
			// System.out.println(str1);
			int static1 = 1;
			String str2 = "";
			String show = "";
			int z = 0;
			int check = 0;
			int c2 = 0;
			String str4 = "";
			String show2 = a1[0] + "";
			for (int j = 1; z < digits - 1; j++) {
				z = 0;
				int i = j;
				int c1 = i;
				// echo $i."<br>";
				if (i % char_num != 0) {
					i = i % char_num;
				} else {
					i = char_num;
				}
				// echo $i."<br>";
				if (j < char_num + 1) {
					show = str1 + a1[i - 1];
					System.out.println(show);
					output.write(show + "\t");
					String neg = "";
					for (int u = 1; u < char_num; u++) {
						show2 = a1[u] + "";
						show2 = show2 + show.substring(1);
						System.out.println(show2);
						output.write(show2 + "\t");
					}

					write_in = "";
				} else {
					show = str2 + a1[i - 1];
					System.out.println(show);
					output.write(show + "\t");
					String neg = "";
					for (int u = 1; u < char_num; u++) {
						show2 = a1[u] + "";
						show2 = show2 + show.substring(1);
						System.out.println(show2);
						output.write(show2 + "\t");
					}
					;
				}
				while ((c1 % char_num == 0) && (c1 > 0)) {
					c1 = c1 / char_num;
					c2 = c1 % char_num;
					z++;
					check = 1;
				}
				if (check == 1) {
					// echo $z.",".$c2."<br>";
					System.out.println("===" + z + "," + c2);
					check = 0;
					int k = digits - 1;
					int location = k - z;
					str2 = str_replace(str1, a1[c2], location);
					temp = str2;
					// echo $str2."===<br>";
					if (z > static1) {
						// echo "!===";
						for (int h = 1; h < z; h++) {
							str2 = str2 + a1[0];
							str1 = str2;
						}

					}
					static1 = z;
				}

			}
			output.close();
		} catch (Exception e) {
		}
	}

	public static void create_ps2(char a1[], int digits, int char_num) {

		String file = "c:\\dictionary.txt";
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			String write_in = "";

			int total = 1;
			for (int u = 1; u < digits; u++) {
				total = char_num * total;
			}
			System.out.println(total);
			String str1 = "";
			for (int i = 0; i < digits - 1; i++) {
				str1 = str1 + a1[0];
			}
			String temp = str1;
			// System.out.println(str1);
			int static1 = 1;
			String str2 = "";
			String show = "";
			int z = 0;
			int check = 0;
			int c2 = 0;
			String str4 = "";
			String show2 = a1[0] + "";
			for (int j = 1; z < digits; j++) {
				z = 0;
				int i = j;
				int c1 = i;
				// echo $i."<br>";
				if (i % char_num != 0) {
					i = i % char_num;
				} else {
					i = char_num;
				}
				// echo $i."<br>";
				if (j < char_num + 1) {
					show = str1 + a1[i - 1];
					System.out.println(show);
					output.write(show + "\t");
					// String neg="";
					// for(int u=1;u<char_num;u++){
					// show2=a1[u]+"";
					// show2=show2+show.substring(1);
					// System.out.println(show2);output.write(show2+"\t");
					// }

					write_in = "";
				} else {
					show = str2 + a1[i - 1];// System.out.println("zzz:"+(i-1));
					System.out.println(show);
					output.write(show + "\t");
					// String neg="";
					// for(int u=1;u<char_num;u++){
					// show2=a1[u]+"";
					// show2=show2+show.substring(1);
					// System.out.println(show2);output.write(show2+"\t");
					// }
					// ;
				}
				while ((c1 % char_num == 0) && (c1 > 0)) {
					c1 = c1 / char_num;
					c2 = c1 % char_num;
					z++;
					check = 1;
				}
				if (check == 1) {
					// echo $z.",".$c2."<br>";
					System.out.println("===" + z + "," + c2);
					check = 0;
					int k = digits - 1;
					int location = k - z;// System.out.println("kkk:"+location);
					if (location >= 0) {
						str2 = str_replace(str1, a1[c2], location);
						temp = str2;
					}
					// echo $str2."===<br>";
					if (z > static1) {
						// echo "!===";
						for (int h = 1; h < z; h++) {
							str2 = str2 + a1[0];
							str1 = str2;
						}

					}
					static1 = z;
				}

			}
			output.close();
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public static String str_replace(String k, char onechar, int location) {
		String all = "";
		int h = k.length();
		char[] a2 = new char[h];
		for (int m = 0; m < h; m++) {
			a2[m] = k.charAt(m);
			// System.out.println(a2[m]+",");
		}
		// System.out.println(k);

		a2[location] = onechar;
		for (int l = 0; l <= location; l++) {
			all = all + a2[l];
		}
		return all;
	}

	// 主程式
	public static void main(String[] args) throws Exception {
		// String file="c:\\diactionay.txt";
		// BufferedWriter output=new BufferedWriter(new FileWriter(file));
		d app = new d(); // 建立Swing應用程式
		// 關閉視窗事件, 結束程式的執行
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
		app.setResizable(false);
		app.setSize(400, 440); // 設定尺寸
		app.setVisible(true); // 顯示視窗

	}
}
