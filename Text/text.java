package Text;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

public class text {
	
	
	public static void main(String[] args) throws AWTException {
		Robot robot=new Robot();
		Color blackcolor=new Color(0,0,0);
		Color buluecolor=new Color(61,132,255);
		//Color redcolor=new Color(249,79,23);
		Color green=new Color(0,215,137);
		while(true) {
			//��ȡ�ж���ȷ����λ�õ�������ɫ
			Color istrue=null;
			robot.delay(10000);
			int y=364;//aѡ���������
			int count=0;//��¼ѡ��ʧ�ܵĴ���
			//�����ǰ����Ϊ��ɫ����������һ��
			if(getScreenPixel(1434, 554).equals(blackcolor)){
				click(robot, 70, 974);
				robot.delay(5000);
				//���1.5����
				robot.mouseMove(1376, 974);
				robot.delay(500);
				click(robot, 1385, 850);
				
			}
			//�ж��Ƿ����ѡ����
			if(getScreenPixel(1006, 583).equals(buluecolor)) {
				while(true) {
					count++;
					if(count>5) {
						//��0�����ҹر�ѡ������¿�ʼѡ��
						count=0;
						click(robot, 1006, 583);
					}
					//���ѡ��
					robot.delay(3000);
					click(robot, 830, y);
					istrue=getScreenPixel(719,315);
					if(istrue.equals(green)) {
						//�ر�ѡ���
						click(robot, 1006, 583);
						break;
					}
					y+=42;
				}	
			}
		}
		
	}
	public static Color getScreenPixel(int x, int y) throws AWTException { // ��������ֵΪ��ɫ��RGBֵ��  
        Robot rb = null; // java.awt.image���е��࣬��������ץȡ��Ļ����������  
        rb = new Robot();  
        Toolkit tk = Toolkit.getDefaultToolkit(); // ��ȡȱʡ���߰�  
        Dimension di = tk.getScreenSize(); // ��Ļ�ߴ���  
        //System.out.println(di.width);  
        //System.out.println(di.height);  
        Rectangle rec = new Rectangle(0, 0, di.width, di.height);  
        BufferedImage bi = rb.createScreenCapture(rec);  
        int pixelColor = bi.getRGB(x, y);  
        Color color=new Color(16777216 + pixelColor);
        return color; // pixelColor��ֵΪ��������ʵ���ó���������ɫ���ֵ����ʵ����ɫֵ��  
    } 
	public static void click(Robot robot,int x,int y) {
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}


}
