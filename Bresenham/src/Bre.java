import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Bre {
	static JFrame frame;
	static JPanel panel;
	static BufferedImage image;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			image =new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
			panel=new JPanel() {
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(image, 0, 0, null);
				}
			
			};
			frame=new JFrame();
			frame.setSize(500,500);
			frame.add(panel);
			frame.setVisible(true);
			draw_line_Bresenham(image.getRaster(),60,70,80,50,new int[] {255,0,0});
					}
	private static void draw_line_Bresenham(WritableRaster raster, int x1, int y1, int x2, int y2, int[] ms) {
		int dx=Math.abs(x1-x2);
		int dy=Math.abs(y1-y2);
		int ty=2*dy;
		int t1DyDx=2*dy-2*dx;
		int x,y,xEnd;
		int p=t1DyDx-dx;
		if(x1>x2) {
			x=x2;
			y=y2;
			xEnd=x1;
		}else {
			x=x1;
			y=y1;
			xEnd=x2;
			
		}
		raster.setPixel(x, y,  ms);
		while(x<xEnd) {
			x++;
			if(p<0) {
				p+=t1DyDx;
				
			}else {
				y++;
				p+=t1DyDx;
			}
			raster.setPixel(x, y,  ms);
			
		}
		// TODO Auto-generated method stub
		
	}
	
}
