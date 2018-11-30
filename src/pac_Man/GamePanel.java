package pac_Man;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Vector;

import javax.swing.ImageIcon;
//0��ǽ  1��·  2�Ƕ���
import javax.swing.JPanel;

//��Ϸ���
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener{
	public GameFrame gameFrame;//���Ʊ�������
	public static final int LABEL_WIDTH=16;//��ǩ����
	public static final int LABEL_HEIGHT=16;//��ǩ���
	public static final int TOTAL_ROWS=31; //��ǩ����
	public static final int TOTAL_COLS=28; //��ǩ����
	public static final int GAMEPANEL_WIDTH=448;
	public static final int GAMEPANEL_HEIGHT=496;
	public static int ENEMY_COUNT=4;//���˵��������
	public static int size_x=26;//ͼ�γ���
	public static int size_y=26;
	public Vector<Enemy> enemys=new Vector<Enemy>();//���˵�����
	public Image back_img=Toolkit.getDefaultToolkit().getImage("image/background.png");//����ͼ
	public BlockLabel[][] blockLabels=new BlockLabel[TOTAL_ROWS][TOTAL_COLS];//��ǩ����
	public Eater eater;//����eater����
	boolean flag=false; //��ʱ���
	boolean eater_isHitted=false; //���eater�Ƿ���ײ��־
	public ScorePanel sPanel;//����ScorePanel���
	public AudioClip audio_end,audio_success,audio_hit,audio_respawn,audio_chigui,audio_daliwan;//�������֣�ͨ�����֣����Ե�������
	public boolean isGameOver=false; //��Ϸ�Ƿ����
	public boolean isWin=false; //��Ϸ�Ƿ�ɹ�
	public boolean egg,egg_0,egg_1,egg_2=false;
	public int direction = 0;//eater������Ҫ�ߵķ���1��2��3��4���ֱ�Ϊ�ϣ��£�����,0Ϊͣ��
	public static int blocks[][]={//0Ϊǽ��1Ϊ��ͨ��·��2Ϊ�ж��ӵ�·��3Ϊ�жϹյ㣬5Ϊ������
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,3,2,2,2,2,3,2,2,2,2,2,3,0,0,3,2,2,2,2,2,3,2,2,2,2,3,0},
			{0,2,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,2,0},
			{0,5,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,5,0},
			{0,2,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,2,0},
			{0,3,2,2,2,2,3,2,2,3,2,2,3,2,2,3,2,2,3,2,2,3,2,2,2,2,3,0},
			{0,2,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,2,0},
			{0,2,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,2,0},
			{0,3,2,2,2,2,3,0,0,3,2,2,3,0,0,3,2,2,3,0,0,3,2,2,2,2,3,0},
			{0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0},
			{0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0},
			{0,0,0,0,0,0,2,0,0,3,2,2,3,3,3,3,2,2,3,0,0,2,0,0,0,0,0,0},
			{0,0,0,0,0,0,2,0,0,2,0,0,0,1,1,0,0,0,2,0,0,2,0,0,0,0,0,0},
			{0,0,0,0,0,0,2,0,0,2,0,3,3,3,3,3,3,0,2,0,0,2,0,0,0,0,0,0},
			{1,1,1,1,1,1,3,2,2,3,0,3,1,1,1,1,3,0,3,2,2,3,1,1,1,1,1,1},
			{0,0,0,0,0,0,2,0,0,2,0,3,3,3,3,3,3,0,2,0,0,2,0,0,0,0,0,0},
			{0,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0},
			{0,0,0,0,0,0,2,0,0,3,2,2,2,2,2,2,2,2,3,0,0,2,0,0,0,0,0,0},
			{0,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0},
			{0,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0},
			{0,3,2,2,2,2,3,2,2,3,2,2,3,0,0,3,2,2,3,2,2,3,2,2,2,2,3,0},
			{0,2,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,2,0},
			{0,2,0,0,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,2,0,0,0,0,2,0},
			{0,3,5,3,0,0,3,2,2,3,2,2,3,2,2,3,2,2,3,2,2,3,0,0,3,5,3,0},
			{0,0,0,2,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,2,0,0,0},
			{0,0,0,2,0,0,2,0,0,2,0,0,0,0,0,0,0,0,2,0,0,2,0,0,2,0,0,0},
			{0,3,2,3,2,2,3,0,0,3,2,2,3,0,0,3,2,2,3,0,0,3,2,2,3,2,3,0},
			{0,2,0,0,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,0,0,2,0},
			{0,2,0,0,0,0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,0,0,2,0},
			{0,3,2,2,2,2,2,2,2,2,2,2,3,2,2,3,2,2,2,2,2,2,2,2,2,2,3,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	}; //�������

	public GamePanel(GameFrame gameFrame,ScorePanel sPanel){ //��ʼ����Ϸ���
		blocks[3][1]=5;blocks[3][26]=5;blocks[23][2]=5;blocks[23][25]=5;//������������
		this.sPanel=sPanel;
		this.gameFrame=gameFrame;
		this.setLayout(new GridLayout(TOTAL_ROWS,TOTAL_COLS)); //������岼��Ϊ��񲼾�
		assign(blocks);//��ʼ��BolckLabel����
		eater = new Eater(Toolkit.getDefaultToolkit().getImage("image/eater_0_r.png"), LABEL_HEIGHT+LABEL_HEIGHT/2-size_x/2,LABEL_HEIGHT+LABEL_HEIGHT/2-size_y/2, size_x, size_y, 0, 0);//��ʼ��eater
		for (int i = 1; i <= ENEMY_COUNT; i++) {//��ʼ������
			enemys.add(new Enemy(Toolkit.getDefaultToolkit().getImage("image/enemy0"+i+".png"), LABEL_HEIGHT*13+LABEL_HEIGHT/2-size_x/2, LABEL_HEIGHT*15+LABEL_HEIGHT/2-size_x/2, size_x, size_y, 0, -1));
		}
		this.addKeyListener(this);//���Ӽ��̼�����
		this.setFocusable(true);//�õ�����
	}

	@Override
	public void paint(Graphics g) {
		// TODO �Զ����ɵķ������
		super.paint(g);
		//1.����Ϸ����
		g.drawImage(back_img, 0,0,448, 496, this);

		//2.��eater
		eater.draw(g);

		//3.�����е���
		for (int i = 0; i < enemys.size(); i++) {
			enemys.get(i).draw(g);
		}

		//4.��ײ�����û���
		if(flag&&!isGameOver){
			g.setColor(Color.WHITE);
			g.setFont(new Font("",Font.BOLD,30));
			g.drawString("ready!",180 , 240);
			flag=false;
		}

		//5.ʧ�ܻ���
		if(isGameOver){
			g.setColor(Color.WHITE);
			g.setFont(new Font("",Font.BOLD,60));
			g.drawString("GAME OVER", GAMEPANEL_WIDTH/2-180, GAMEPANEL_HEIGHT/2);
		}

		//6.�ɹ�����
		if(isWin){
			g.setColor(Color.WHITE);
			g.setFont(new Font("",Font.BOLD,60));
			g.drawString("SUCCESS", GAMEPANEL_WIDTH/2-145, GAMEPANEL_HEIGHT/2);
		}
		
		//7.�ʵ�����
		if(egg){
			g.setColor(Color.WHITE);
			g.setFont(new Font("",Font.BOLD,30));
			g.drawString("��ķ˹����С������", GAMEPANEL_WIDTH/2-145, GAMEPANEL_HEIGHT/2);
		}

	}
	public void action(){
		while(!isGameOver && !isWin){//����Ϸ��������ʤ��ʱֹͣһ�ж������˳�ѭ��
			if((eater.x+eater.width/2-LABEL_WIDTH/2)%LABEL_WIDTH == 0 && (eater.y+eater.height/2-LABEL_HEIGHT/2)%LABEL_HEIGHT == 0){//��eater���ĵ��뷽���ǩ���ĵ��غ�ʱ
				eater.last_xStep=eater.xStep;
				eater.last_yStep=eater.yStep;
				changeDirect(direction);//�ı䷽��
				eater.preventOutOfWall(blockLabels,LABEL_WIDTH,LABEL_HEIGHT);//����Ƿ�ײǽ,˳��Ե�����
				eater.preventOutOfWall(blockLabels,LABEL_WIDTH,LABEL_HEIGHT);//������һ�μ��ʧ�ܣ�����λ�ú��ٴμ��
				sPanel.score += eater.getScore(blockLabels,LABEL_WIDTH,LABEL_HEIGHT);//�÷����ı����
				sPanel.action();//�÷������ػ�
			}
			eater.move();//�����ż��
			if(Enemy.isEat){//�Ƿ�ִ�гԵ�������Ĳ���
				if(Enemy.time>300){//��ʱ
					Enemy.isEat=false;
					Enemy.time=0;
				}
				else {Enemy.time++;
					for (int i = 0; i < enemys.size(); i++) { 
						enemys.get(i).isEat();					
						if(Enemy.time>=300 || enemys.get(i).isDie){//����ǰ����ȥͼƬ
							enemys.get(i).img=enemys.get(i).img_old;
						}
					}
				}
			}
			
			for (int i = 0; i < enemys.size(); i++) { 
				if((enemys.get(i).x+enemys.get(i).width/2-LABEL_WIDTH/2)%LABEL_WIDTH == 0 && (enemys.get(i).y+enemys.get(i).height/2-LABEL_HEIGHT/2)%LABEL_HEIGHT == 0){//��enemy���ĵ��뷽���ǩ���ĵ��غ�ʱ
					enemys.get(i).randomChangeDirect();//enemy������ı䷽�򷽷�
					enemys.get(i).preventOutOfWall(blockLabels,LABEL_WIDTH,LABEL_HEIGHT);//����Ƿ�ײǽ
					enemys.get(i).preventOutOfWall(blockLabels,LABEL_WIDTH,LABEL_HEIGHT);//����Ƿ�ײǽ
				}
				enemys.get(i).move();//enemy��move����
			}

			//��ײ���
			for(int i=0;i<enemys.size();i++){
				Enemy enemy=enemys.get(i);
				
				if(eater.hittedByEnemy(enemy) && !isGameOver &&!isWin){ //��Ϸû�н�����ײ
				if(Enemy.isEat){//������״̬
						//�Ƴ��Ե��Ĺ�
						enemy.img=enemy.img_old;
						enemy.isDie=true;
						enemy.x=LABEL_HEIGHT*13+LABEL_HEIGHT/2-size_x/2;
						enemy.y=LABEL_HEIGHT*15+LABEL_HEIGHT/2-size_x/2;
						try {//�Ե������Ч
							audio_chigui=Applet.newAudioClip(new File("audio/chigui.wav").toURI().toURL());
							audio_chigui.play(); //audio.play();ֻ����һ��   audio.loop();ѭ������  audio.stop();ֹͣ����
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
					}else
						eater_isHitted=true;
				}
				if(eater_isHitted){
					Eater.life--; //eater������1
					eater.x=LABEL_HEIGHT+LABEL_HEIGHT/2-size_x/2;
					eater.y=LABEL_HEIGHT+LABEL_HEIGHT/2-size_y/2;
					if(Eater.life==0){
						isGameOver=true; //��Ϸ����״̬Ϊtrue
						eater.y=-100; //�Ƴ��߽���
						eater.yStep=0;
						//��Ϸ������Ч
						try {
							gameFrame.audio.stop();//��������ͣ
							audio_end=Applet.newAudioClip(new File("audio/dead.wav").toURI().toURL());
							audio_end.play(); //audio.play();ֻ����һ��   audio.loop();ѭ������  audio.stop();ֹͣ����
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}

					}else{
						try {//������Ե�����Ч
							audio_hit=Applet.newAudioClip(new File("audio/ghost_eaten.wav").toURI().toURL());
							audio_hit.play(); //audio.play();ֻ����һ��   audio.loop();ѭ������  audio.stop();ֹͣ����
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
					}
					eater_isHitted=false;
					flag=true; 
					sPanel.action(); //ScorePanel�ػ�������ֵ��1
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					enemys.clear();
					for (int j = 1; j <= ENEMY_COUNT; j++) {//��ʼ������
						enemys.add(new Enemy(Toolkit.getDefaultToolkit().getImage("image/enemy0"+j+".png"), LABEL_HEIGHT*13+LABEL_HEIGHT/2-size_x/2, LABEL_HEIGHT*15+LABEL_HEIGHT/2-size_x/2, size_x, size_y, 0, -1));
					}
					repaint();

					try {//���³�������Ч
						audio_respawn=Applet.newAudioClip(new File("audio/respawn.wav").toURI().toURL());
						audio_respawn.play(); //audio.play();ֻ����һ��   audio.loop();ѭ������  audio.stop();ֹͣ����
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {						
						e.printStackTrace();
					}
				}
			}

			if(sPanel.score==2880-10){//�ɹ����ж�
				isWin=true;
				try {//ʤ��ͨ�ص���Ч
					audio_success=Applet.newAudioClip(new File("audio/win.wav").toURI().toURL());
					audio_success.play(); //audio.play();ֻ����һ��   audio.loop();ѭ������  audio.stop();ֹͣ����
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
			
			//�ʵ�
			if(eater.x==251 && eater.y==203){
				egg_0=true;
			}
			if(egg_0 && eater.x==251 && eater.y==235 ){
				egg_0=false;
				egg_1=true;
			}
			if(egg_1 && eater.x==171 && eater.y==235){
				egg_2=true;
				egg_1=false;
			}
			if(egg_2 && eater.x==171 && eater.y==203){
				egg=true;
				egg_2=false;
				repaint();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {						
					e.printStackTrace();
				}
			}
			egg=false;
			repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void assign(int[][] blocks){//��block�����blockLabels������и�ֵ
		for(int i=0;i<TOTAL_ROWS;i++){
			for(int j=0;j<TOTAL_COLS;j++){
				blockLabels[i][j]=new BlockLabel(i,j);//�½�BlockLabel����
				if(blocks[i][j]==0){//��Ϊ0,��ֵΪǽ�Ҳ���ͨ��
					blockLabels[i][j].setIcon(new ImageIcon("image/blank.png"));
					blockLabels[i][j].isGone = false;
					blockLabels[i][j].label_score=0;
				}else if(blocks[i][j]==2){//��Ϊ2����ֵΪʳ���ҿ�ͨ��
					blockLabels[i][j].setIcon(new ImageIcon("image/food.png"));
					blockLabels[i][j].isGone = true;
					blockLabels[i][j].label_score=10;
				}
				else if(blocks[i][j]==1){//��Ϊ1�����ͨ��
					blockLabels[i][j].isGone = true;
					blockLabels[i][j].label_score=0;
				}
				else if(blocks[i][j]==3){//��Ϊ3����ֵΪʳ���ҿ�ͨ��,�����յ��ж�
					blockLabels[i][j].setIcon(new ImageIcon("image/food.png"));
					blockLabels[i][j].isGone = true;
					blockLabels[i][j].label_score=10;
				}else if(blocks[i][j]==5){//��Ϊ5����ֵΪ����ʳ���ҿ�ͨ��
					blockLabels[i][j].setIcon(new ImageIcon("image/food_B.png"));
					blockLabels[i][j].isGone = true;
					blockLabels[i][j].label_score=10;
				}
				this.add(blockLabels[i][j]);//����ǩ��ӽ����

			}
		}
	}

	public void changeDirect(int direction){//�÷������ڸı䷽��
		switch(direction){
		case 0://0Ϊ����
			eater.xStep=0;
			eater.yStep=0;
			break;
		case 1://1Ϊ�����ƶ�
			eater.xStep=0;
			eater.yStep=-2;
			eater.udlr='u';
			break;
		case 2://2Ϊ�����ƶ�
			eater.xStep=0;
			eater.yStep=2;
			eater.udlr='d';
			break;
		case 3://3Ϊ�����ƶ�
			eater.xStep=-2;
			eater.yStep=0;
			eater.udlr='l';
			break;
		case 4://4Ϊ�����ƶ�
			eater.xStep=2;
			eater.yStep=0;
			eater.udlr='r';
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!isWin && !isGameOver){
			switch(e.getKeyCode()){//�����̰���ʱ������ݲ�Ϊdirection��ֵ��"��������"�����ֱ��Ӧ"1234"
			case KeyEvent.VK_UP:
				direction = 1;
				break;
			case KeyEvent.VK_DOWN:
				direction = 2;
				break;
			case KeyEvent.VK_LEFT:
				direction = 3;
				break;
			case KeyEvent.VK_RIGHT:
				direction = 4;
				break;
			case KeyEvent.VK_W:
				direction = 1;
				break;
			case KeyEvent.VK_S:
				direction = 2;
				break;
			case KeyEvent.VK_A:
				direction = 3;
				break;
			case KeyEvent.VK_D:
				direction = 4;
				break;

			}
		}else{
			switch(e.getKeyCode()){//�����̻�ȡֵΪ�ո�ʱ����ֵdirectionΪ-1
			case KeyEvent.VK_SPACE:
				direction = -1;
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}


}
