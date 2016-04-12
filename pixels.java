package stenography;

import java.awt.image.BufferedImage;
import java.io.File;
//import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;

//import javax.imageio.ImageIO;
class pixels{
	



public  pixels() throws Throwable {
	String s;
	Scanner inputs = new Scanner(System.in);
	System.out.print("enter a string : ");
	s=inputs.nextLine();
	TripleDESTest obj1=new TripleDESTest();
    String m=obj1._encrypt(s, "SecretKey");
    //System.out.println(m);
    
    int a[]=new int[m.length()];
    for(int i=0;i<m.length();i++){
 	   a[i]=(int)m.charAt(i);
    }
    /*for(int i=0;i<m.length();i++){
    	System.out.println(a[i]);
    }*/
    
    BufferedImage img;
    File input = new File("abcd.jpg");
    img = ImageIO.read(input);
    int width=img.getWidth();
    int height=img.getHeight();
    
    byte b[] = m.getBytes();
    for(int i=0; i<b.length; i++)
       embedByte(img, b[i], i*8+32, 0);
    
    for(int i=0;i<m.length();i++){
    	System.out.println(b[i]);
    }
    /*for(int x=0;x<width;x++){
    	for(int y=0;y<height;y++){
    		
    	}
    	
    }*/
    File f= new File("hii.jpg");
    ImageIO.write(img, "jpg", f);
    
}		 

public static void main(String arg[]) throws Throwable {
			    new pixels();
			    }

private void embedByte(BufferedImage img, byte b, int start, int storageBit) {
    int maxX = img.getWidth(), maxY = img.getHeight(), 
       startX = start/maxY, startY = start - startX*maxY, count=0;
    for(int i=startX; i<maxX && count<8; i++) {
       for(int j=startY; j<maxY && count<8; j++) {
          int rgb = img.getRGB(i, j), bit = getBitValue(b, count);
          System.out.println("before" + rgb);
          rgb = setBitValue(rgb, storageBit, bit);
          System.out.println("after" + rgb);
          img.setRGB(i, j, rgb);
          count++;
          }
       }
    
}

private int getBitValue(int n, int location) {
    int v = n & (int) Math.round(Math.pow(2, location));
    return v==0?0:1;
    }

private int setBitValue(int n, int location, int bit) {
    int toggle = (int) Math.pow(2, location), bv = getBitValue(n, location);
    if(bv == bit)
       return n;
    if(bv == 0 && bit == 1)
       n |= toggle;
    else if(bv == 1 && bit == 0)
       n ^= toggle;
    return n;
    }
}