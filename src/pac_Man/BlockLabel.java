package pac_Man;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class BlockLabel extends JLabel{
	public boolean isGone;//��ǩ�Ƿ���ͨ��
	public int rowNum; //��ǩ���ڷ�����к�
	public int colNum; //��ǩ���ڷ�����к�
	public int label_score;//��ʾ�ñ�ǩ�ķ�ֵ
	public BlockLabel(int rowNum, int colNum) {
		this.rowNum = rowNum;
		this.colNum = colNum;
	}
}
