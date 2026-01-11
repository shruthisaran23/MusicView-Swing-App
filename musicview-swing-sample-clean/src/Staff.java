import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Staff {
    public int getXcoor() {
        return xcoor;
    }

    public void setXcoor(int xcoor) {
        this.xcoor = xcoor;
    }

    public int getYcoor() {
        return ycoor;
    }

    public void setYcoor(int ycoor) {
        this.ycoor = ycoor;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    int xcoor;
    int ycoor;
    boolean last;
    private BufferedImage trebleClefImage;
    private BufferedImage commonTimeImage;

    public ArrayList<Symbol> getListofsymbols() {
        return listofsymbols;
    }

    public void setListofsymbols(ArrayList<Symbol> listofsymbols) {
        this.listofsymbols = listofsymbols;
    }

    ArrayList<Symbol> listofsymbols = new ArrayList<>();

    public Staff(int xcoor,int ycoor,boolean last){
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.last = last;
    }


    public void paint(Graphics g) {
        g.drawLine(this.xcoor,this.ycoor,this.xcoor+600,this.ycoor);
        g.drawLine(this.xcoor,this.ycoor+15,this.xcoor+600,this.ycoor+15);
        g.drawLine(this.xcoor,this.ycoor + 30,this.xcoor+600,this.ycoor+30);
        g.drawLine(this.xcoor,this.ycoor +  45,this.xcoor+600,this.ycoor+45);
        g.drawLine(this.xcoor,this.ycoor +60,this.xcoor+600,this.ycoor+60);
        g.drawLine(this.xcoor, this.ycoor, this.xcoor, this.ycoor + 60);
        if(this.last){
            g.drawLine(this.xcoor+590, this.ycoor, this.xcoor+590, this.ycoor + 60);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(10));
            g2.drawLine(this.xcoor+600, this.ycoor+4, this.xcoor+600, this.ycoor + 56);
            g2.setStroke(new BasicStroke(1));
        } else {
            g.drawLine(this.xcoor+600, this.ycoor, this.xcoor+600, this.ycoor + 60);
        }


        try {
            trebleClefImage = ImageIO.read(getClass().getResource("/images/trebleClef.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            commonTimeImage = ImageIO.read(getClass().getResource("/images/commonTime.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //images
        g.drawImage(trebleClefImage, this.xcoor, this.ycoor-10, null);
        g.drawImage(commonTimeImage, this.xcoor +50, this.ycoor+3, null);

        for (Symbol symbol:listofsymbols){
            symbol.paint(g);
        }
    }
    public String pitchCode(int ycoor) {
        if (ycoor == this.ycoor + 60 || ycoor >= this.ycoor + 55 && ycoor <= this.ycoor + 63 ) {
            return "E4";
        }
        if (ycoor < (this.ycoor + 60) && ycoor > (this.ycoor + 45)){
            return "F4";
        }
        if (ycoor == this.ycoor + 45 || (ycoor <= this.ycoor+50 && ycoor >= this.ycoor+40) ){
            return "G4";
        }
        if (ycoor < (this.ycoor + 45) && ycoor > (this.ycoor + 30)){
            return "A5";
        }
        if (ycoor == this.ycoor + 30|| (ycoor <= this.ycoor+35 && ycoor >= this.ycoor+25)) {
            return "B5";
        }
        if (ycoor < (this.ycoor + 30) && ycoor > (this.ycoor + 15)){
            return "C5";
        }
        if (ycoor == this.ycoor + 15 || (ycoor <= this.ycoor+20 && ycoor >= this.ycoor+10)) {
            return "D5";
        }
        if (ycoor < (this.ycoor + 15) && ycoor > (this.ycoor)){
            return "E5";
        }
        if (ycoor == this.ycoor || ycoor <= this.ycoor+5 && ycoor >= this.ycoor+7 ) {
            return "F5";
        }

        return "Outside of any staff";
    }

}


