package Text;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

public class erya {
	
	
	public static void main(String[] args) throws AWTException {
		Robot robot=new Robot();
		Color select=new Color(123,158,49);
		Color green=new Color(24,179,21);
		Color red=new Color(255,122,56);
		int select_y=345;
		int next_y=370;
		while(true) {
			robot.delay(3000);
			//�ƶ���ѡ����,����ѡ�����ұ���ʾ�Ѿ��ۿ���
			if(getScreenPixel(1617, select_y).equals(select) && getScreenPixel(1863, select_y).equals(green)) {
				System.out.println(getScreenPixel(1617, select_y));
				System.out.println(getScreenPixel(1863, select_y));
				click(robot, 1724, next_y);//��һ��
				click(robot, 868, 568);//��ʼ�ۿ�
				select_y+=25;
				next_y+=25;
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
