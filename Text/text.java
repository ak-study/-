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
			//获取判断正确错误位置的坐标颜色
			Color istrue=null;
			robot.delay(10000);
			int y=364;//a选项的纵坐标
			int count=0;//记录选择失败的次数
			//如果当前界面为黑色，则跳到下一集
			if(getScreenPixel(1434, 554).equals(blackcolor)){
				click(robot, 70, 974);
				robot.delay(5000);
				//点击1.5倍速
				robot.mouseMove(1376, 974);
				robot.delay(500);
				click(robot, 1385, 850);
				
			}
			//判断是否出现选择题
			if(getScreenPixel(1006, 583).equals(buluecolor)) {
				while(true) {
					count++;
					if(count>5) {
						//清0，并且关闭选择框，重新开始选择
						count=0;
						click(robot, 1006, 583);
					}
					//点击选项
					robot.delay(3000);
					click(robot, 830, y);
					istrue=getScreenPixel(719,315);
					if(istrue.equals(green)) {
						//关闭选择框
						click(robot, 1006, 583);
						break;
					}
					y+=42;
				}	
			}
		}
		
	}
	public static Color getScreenPixel(int x, int y) throws AWTException { // 函数返回值为颜色的RGB值。  
        Robot rb = null; // java.awt.image包中的类，可以用来抓取屏幕，即截屏。  
        rb = new Robot();  
        Toolkit tk = Toolkit.getDefaultToolkit(); // 获取缺省工具包  
        Dimension di = tk.getScreenSize(); // 屏幕尺寸规格  
        //System.out.println(di.width);  
        //System.out.println(di.height);  
        Rectangle rec = new Rectangle(0, 0, di.width, di.height);  
        BufferedImage bi = rb.createScreenCapture(rec);  
        int pixelColor = bi.getRGB(x, y);  
        Color color=new Color(16777216 + pixelColor);
        return color; // pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。  
    } 
	public static void click(Robot robot,int x,int y) {
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}


}
