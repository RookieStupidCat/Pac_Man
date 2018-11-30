package pac_Man;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
//�÷����
@SuppressWarnings("serial")
public class ScorePanel extends JPanel{
	public Image img=Toolkit.getDefaultToolkit().getImage("image/eater_0_l.png");
	public int life_count=5; //����
	public int score;//����
	public ScorePanel(){
		Eater.life=5;//��ʼ������
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("",Font.BOLD,20));
		//1.����
		setBackground(Color.BLACK);

		//2.�÷�
		g.drawString("Scroe:", 15,30);
		g.drawString(""+(score-10), 80, 30);//��һ���򲻼Ʒ�

		//3.����
		g.drawString("Lives:",210 , 30);
		for(int i=0;i<Eater.life;i++){
			g.drawImage(img, 270+i*35,8, 30,30,null);
		}

	} 
	public void action(){
		repaint();
	}
}
