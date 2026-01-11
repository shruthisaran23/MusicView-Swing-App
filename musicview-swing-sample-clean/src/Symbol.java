import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Symbol {
    public boolean isDrag() {
        return isDrag;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected;


    private boolean isDrag;
    private BufferedImage sixteenthNoteImage;
    private BufferedImage eightNoteImage;
    private BufferedImage quarterNoteImage;
    private BufferedImage halfNoteImage;
    private BufferedImage wholeNoteImage;
    private BufferedImage sixteenthRestImage;
    private BufferedImage eightRestImage;
    private BufferedImage quarterRestImage;
    private BufferedImage halfRestImage;
    private BufferedImage wholeRestImage;



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

    int xcoor;
    int ycoor;
    int duration;

    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    String pitch;
    boolean note;
    boolean rest;
    int[] positionpoint;

    public Staff getParentStaff() {
        return parentStaff;
    }

    public void setParentStaff(Staff parentStaff) {
        this.parentStaff = parentStaff;
    }

    public int[] getStaffPosition() {
        return staffPosition;
    }

    public void setStaffPosition(int[] staffPosition) {
        this.staffPosition = staffPosition;
    }

    Staff parentStaff;

    int[] staffPosition;

    public Symbol(int xcoor,int ycoor,int duration, boolean note, boolean rest, boolean isDrag, boolean isSelected){
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.duration = duration;
        this.note = note;
        this.rest = rest;
        this.positionpoint = getPositionPoint(duration, note, rest);
        this.isDrag = isDrag;
        this.isSelected = false;
    }

    public int[] getPositionPoint(int duration, boolean notetype, boolean resttype){
        int[] arr;
        if (notetype) {
            if (duration == 4) {
                arr = new int[]{10, 6};
                return arr;
            }
            else if (duration == 3) {
                arr = new int[]{15, 34};
                return arr;
            }
            else if (duration == 2) {
                arr = new int[]{7, 35};
                return arr;
            }
            else if (duration == 1) {
                arr = new int[]{15, 36};
                return arr;
            }
            else if (duration == 0) {
                arr = new int[]{6, 35};
                return arr;
            }
            else{
                return new int[]{0, 0};
            }

        } else if (resttype) {
            if (duration == 4) {
                arr = new int[]{10, 5};
                return arr;
            }
            else if (duration == 3) {
                arr = new int[]{10, 5};
                return arr;
            }
            else if (duration == 2) {
                arr = new int[]{6, 14};
                return arr;
            }
            else if (duration == 1) {
                arr = new int[]{5, 10};
                return arr;
            }
            else if (duration == 0) {
                arr = new int[]{6, 13};
                return arr;
            }
            else{
                return new int[]{0, 0};
            }
        }
        return new int[]{0,0};

    }

    public void paint(Graphics g) {

        if (this.note) {
            if (this.duration == 4) {
                try {
                    wholeNoteImage = ImageIO.read(getClass().getResource("/images/wholeNote.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(wholeNoteImage, this.xcoor - this.getPositionPoint(this.duration, this.note, this.rest)[0], this.ycoor - this.getPositionPoint(this.duration, this.note, this.rest)[1], null);

            }
            else if (this.duration == 3) {

                try {
                    halfNoteImage = ImageIO.read(getClass().getResource("/images/halfNote.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(halfNoteImage, this.xcoor - this.getPositionPoint(this.duration, this.note, this.rest)[0], this.ycoor - this.getPositionPoint(this.duration, this.note, this.rest)[1], null);

            }
            else if (this.duration == 2) {

                try {
                    quarterNoteImage = ImageIO.read(getClass().getResource("/images/quarterNote.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(quarterNoteImage, this.xcoor - this.getPositionPoint(this.duration, this.note, this.rest)[0], this.ycoor - this.getPositionPoint(this.duration, this.note, this.rest)[1], null);

            }
            else if (this.duration == 1) {
                try {
                    eightNoteImage = ImageIO.read(getClass().getResource("/images/eighthNote.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(eightNoteImage, this.xcoor - this.getPositionPoint(this.duration, this.note, this.rest)[0], this.ycoor - this.getPositionPoint(this.duration, this.note, this.rest)[1], null);

            }
            else if (this.duration == 0) {
                try {
                    sixteenthNoteImage = ImageIO.read(getClass().getResource("/images/sixteenthNote.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);

                }
                g.drawImage(sixteenthNoteImage, this.xcoor - this.getPositionPoint(this.duration, this.note, this.rest)[0], this.ycoor - this.getPositionPoint(this.duration, this.note, this.rest)[1], null);
            }

        }
        else if (this.rest) {
            if (this.duration == 4) {

                try {
                    wholeRestImage = ImageIO.read(getClass().getResource("/images/wholeRest.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(wholeRestImage, this.xcoor - this.getPositionPoint(this.duration, this.note, this.rest)[0], this.ycoor - this.getPositionPoint(this.duration, this.note, this.rest)[1], null);

            }
            else if (this.duration == 3) {

                try {
                    halfRestImage = ImageIO.read(getClass().getResource("/images/halfRest.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(halfRestImage, this.xcoor - this.getPositionPoint(this.duration, this.note, this.rest)[0], this.ycoor - this.getPositionPoint(this.duration, this.note, this.rest)[1], null);

            }
            else if (this.duration == 2) {

                try {
                    quarterRestImage = ImageIO.read(getClass().getResource("/images/quarterRest.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(quarterRestImage,this.xcoor - this.getPositionPoint(this.duration, this.note, this.rest)[0], this.ycoor - this.getPositionPoint(this.duration, this.note, this.rest)[1], null);

            }
            else if (this.duration == 1) {
                try {
                    eightRestImage = ImageIO.read(getClass().getResource("/images/eighthRest.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(eightRestImage, this.xcoor - this.getPositionPoint(this.duration, this.note, this.rest)[0], this.ycoor - this.getPositionPoint(this.duration, this.note, this.rest)[1], null);

            }
            else if (this.duration == 0) {
                try {
                    sixteenthRestImage = ImageIO.read(getClass().getResource("/images/sixteenthRest.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(sixteenthRestImage, this.xcoor - this.getPositionPoint(this.duration, this.note, this.rest)[0], this.ycoor - this.getPositionPoint(this.duration, this.note, this.rest)[1], null);
            }
        }
        int selectedx = this.xcoor - 10;
        int selectedy = this.ycoor - 10;

        if (this.isSelected) {
            g.setColor(Color.red);

            g.drawLine(selectedx, selectedy,selectedx, selectedy + 20);
            g.drawLine(selectedx,selectedy, selectedx + 20, selectedy);
            g.drawLine(selectedx+20,selectedy, selectedx+ 20, selectedy + 20);
            g.drawLine(selectedx,selectedy + 20, selectedx+ 20, selectedy + 20);
            g.setColor(Color.black);

        }


    }
}
