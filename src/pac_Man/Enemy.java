package pac_Man;

import java.awt.Image;
import java.awt.Toolkit;

public class Enemy extends MovingObject {
	public int count=0;
	public int rcd=0;//��֤�ȳ����˼���
	public static boolean isEat=false;//Easter�Ƿ���˴�����
	public static int time=0;//��ʱ
	public Image img_old;//�浵�任֮ǰ��ͼƬ
	public boolean isDie=false;//���Ե����¹�
	public Enemy(Image img, int x, int y, int width, int height, int xStep, int yStep) {
		super(img, x, y, width, height, xStep, yStep);
		arrayX =15;
		arrayY =13;
	}
	
	public  void isEat(){//��Easter���˴�����֮�����Ϊ
		if(time==1){
			img_old=img;//�浵��ͼ
			xStep*=-1;yStep*=-1;
		}
		img=Toolkit.getDefaultToolkit().getImage("image/enemy_0.png");
	}
	
	public void randomChangeDirect(){//��������ı�enemy���˶�����
		arrayY = (this.x+this.width/2-GamePanel.LABEL_WIDTH/2)/GamePanel.LABEL_WIDTH;//x��yΪeater�������ڱ�ǩ �������±ꡣ
		arrayX = (this.y+this.height/2-GamePanel.LABEL_HEIGHT/2)/GamePanel.LABEL_HEIGHT;	
		if(GamePanel.blocks[arrayX][arrayY]==3){//�ж�Ϊ�յ��ǩ	
			if(rcd<2)
				rcd++;//�������Ĺյ㲻�ж�
			else{
				if(xStep!=0){//�����ƶ�ʱ
					if(xStep>0){//��ʼ����
						if(GamePanel.blocks[arrayX][arrayY+1]!=0){//�ұ߿�ͨ��
							xStep=2-(int)(Math.random()*2)*2;//ֻ��ֹͣ���߼�����
						}else xStep=0;//����ͨ������
					}else if(xStep<0){//��ʼ����
						if(GamePanel.blocks[arrayX][arrayY-1]!=0){//��߿�ͨ��
							xStep=-2+(int)(Math.random()*2)*2;//ֻ��ֹͣ���߼�����
						}else xStep=0;
					}
					if(xStep==0){
						if(GamePanel.blocks[arrayX-1][arrayY]!=0 && GamePanel.blocks[arrayX+1][arrayY]!=0)//��ʱ���¾���ͨ��
							yStep=-2+(int)(Math.random()*2)*4;//���Ϊ-2��2
						else if(GamePanel.blocks[arrayX-1][arrayY]==0)//���ϲ�ͨ��������
							yStep=2;
						else yStep=-2;//����ͨ������
					}
				}else if(yStep!=0){//�����ƶ�ʱ
					if(yStep>0){//��ʼ����
						if(GamePanel.blocks[arrayX+1][arrayY]!=0){//�ұ߿�ͨ��
							yStep=2-(int)(Math.random()*2)*2;//ֻ��ֹͣ���߼�����
						}else yStep=0;
					}else if(yStep<0){//��ʼ����
						if(GamePanel.blocks[arrayX-1][arrayY]!=0){//�ұ߿�ͨ��
							yStep=-2+(int)(Math.random()*2)*2;//ֻ��ֹͣ���߼�����
						}else yStep=0;
					}
					if(yStep==0){
						if(GamePanel.blocks[arrayX][arrayY-1]!=0 && GamePanel.blocks[arrayX][arrayY+1]!=0)//��ʱ���Ҿ���ͨ��
							xStep=-2+(int)(Math.random()*2)*4;//���Ϊ-2��2
						else if(GamePanel.blocks[arrayX][arrayY+1]==0)//���Ҳ�ͨ��������
							xStep=-2;
						else xStep=2;//����ͨ������
					}
				}
			}
		}
	}
}
