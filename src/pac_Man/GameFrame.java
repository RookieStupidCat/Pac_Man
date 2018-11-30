package pac_Man;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class GameFrame extends JFrame{ //��Ϸ����
	public static final int FRAMEWIDTH=448+6;//�����
	public static final int FRAMEHEIGHT=496+35+50; //�����
	public AudioClip audio;//��������
	
	public GameFrame(){ //���췽����ʼ����
		this.setTitle("�Զ���");
		 //448,496Ϊ��Ϸ����С 448,50Ϊ�÷�����С
		this.setSize(FRAMEWIDTH,FRAMEHEIGHT);  //�����С
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ScorePanel sPanel =new ScorePanel(); //�½��÷����
		this.add(sPanel,BorderLayout.CENTER); //��ӵ÷���嵽������
		GamePanel gPanel=new GamePanel(this,sPanel); //�½���Ϸ��壬ͬʱ����÷�������
		this.add(gPanel,BorderLayout.NORTH); //�����Ϸ��嵽������
		this.setResizable(false); //���ô��岻�ɸı��С
		this.setLocationRelativeTo(null); //�������
		this.setVisible(true);//��ʾ����
		//���� ��BGM�� �ڴ�������У�����Ϸ��ʼʱ��ͷ����
		try {
			audio=Applet.newAudioClip(new File("audio/BGM.wav").toURI().toURL());
			audio.loop(); //audio.play();ֻ����һ��
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		gPanel.action();//������Ϸ������
//		sPanel.action();//���õ÷�����ػ�
		for(;;){//ѭ��������ȴ��û�����ո�
			if (gPanel.direction==-1)//�ո�ֵΪ-1
				break;
			try {
				Thread.sleep(1);//��ֹѭ����ת
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}				
	}	
}
