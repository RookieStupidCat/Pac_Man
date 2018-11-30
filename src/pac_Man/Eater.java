package pac_Man;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;

public class Eater extends MovingObject{
	public static int life=3;//���ڼ�¼����
	public int time=0;//���ƶ�ͼ�ٶ�
	public char udlr='r';//��������-u,d,l,r
	public int restart_delay_count=0;

	public Eater(Image img, int x, int y, int width, int height, int xStep, int yStep) {
		super(img, x, y, width, height, xStep, yStep);
		
	}
	public void draw(Graphics g){ //��ͼ
		switch (time%12) {//��ͼ�ٶ�
		case 0:
			this.img=Toolkit.getDefaultToolkit().getImage("image/eater_0_"+udlr+".png");
			break;
		case 4:
			this.img=Toolkit.getDefaultToolkit().getImage("image/eater_1_"+udlr+".png");
			break;
		case 8:
			this.img=Toolkit.getDefaultToolkit().getImage("image/eater_2_"+udlr+".png");
			break;

		default:
			break;
		}
		time++;
		g.drawImage(this.img,this.x,this.y,this.width,this.height,null);
	}
	
	public boolean hittedByEnemy(Enemy enemy) {
		return (this.x-enemy.x>=-this.width+4 && this.x-enemy.x<=enemy.width-4 &&
				   this.y-enemy.y>=-this.height+4 && this.y-enemy.y<=enemy.height-4 );
	}
	
	public int getScore(BlockLabel[][] blockLabels, int w, int h){ //�õ���ǰ��ǩ�ķ������û�Ϊ��·
		int y = (this.x+this.width/2-w/2)/w;//16Ϊÿ����ǩ�Ŀ�ȣ������x��yΪeater�������ڱ�ǩ �������±ꡣÿ����ǩ���±�Ϊ�ñ�ǩ���ϽǶ�����Գ���
		int x = (this.y+this.height/2-h/2)/h;
		blockLabels[x][y].setIcon(new ImageIcon("image/blank.png"));//����ǰ��ǩ��ͼƬ�滻Ϊ��·
		int score = blockLabels[x][y].label_score;//��õ�ǰ��ǩ�ķ���
		blockLabels[x][y].label_score=0;//��ɵ�·���ֵΪ0
		
		if(GamePanel.blocks[x][y]==5){//����Ե�������
			Enemy.isEat=true;//���Ϊ��
			GamePanel.blocks[x][y]=1;//������ͨ��·
			music();//������Ч
		}
		return score;
	}
	private void music() {
		try {//�Դ��������Ч
			AudioClip audio_daliwan = Applet.newAudioClip(new File("audio/daliwan.wav").toURI().toURL());
			audio_daliwan.play(); //audio.play();ֻ����һ��   audio.loop();ѭ������  audio.stop();ֹͣ����
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		
	}
}



