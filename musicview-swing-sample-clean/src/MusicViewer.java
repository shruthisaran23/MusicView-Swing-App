import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

class MusicViewer extends JComponent {
    ArrayList<Staff> listOfStaves= new ArrayList<>();

    public Symbol getSelectedSymbol() {
        return selectedSymbol;
    }

    public void setSelectedSymbol(Symbol selectedSymbol) {
        this.selectedSymbol = selectedSymbol;
    }

    Symbol selectedSymbol;

    public Symbol getNewSymbol() {
        return newSymbol;
    }

    public void setNewSymbol(Symbol newSymbol) {
        this.newSymbol = newSymbol;
    }

    Symbol newSymbol;

    public MusicViewer() {
        setSize(720,600);
        setPreferredSize(new Dimension(720,600));
        Staff first = new Staff(60, 60, false);
        Staff second = new Staff(60, 180, false);
        Staff third = new Staff(60, 300, false);
        Staff fourth = new Staff(60, 420, true);
        listOfStaves.add(first);
        listOfStaves.add(second);
        listOfStaves.add(third);
        listOfStaves.add(fourth);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawRect(0, 0, 1800, 1800);
        for (Staff staff:listOfStaves){
            staff.paint(g);
        }
    }

    public void NewStaff(){
        listOfStaves.add(new Staff(listOfStaves.get(listOfStaves.size()-1).xcoor,listOfStaves.get(listOfStaves.size()-1).ycoor +120, true));
        listOfStaves.get(listOfStaves.size() - 2).setLast(false);
        Dimension old = getSize();
        setSize((int) old.getWidth(), (int) (old.getHeight()+100));
        setPreferredSize(new Dimension((int) old.getWidth(), (int) (old.getHeight()+100)));
        this.repaint();
        System.out.println(listOfStaves.size());
    }

    public void DeleteStaff(){
        listOfStaves.remove(listOfStaves.size()-1);
        listOfStaves.get(listOfStaves.size() - 1).setLast(true);
        Dimension old = getSize();
        setSize((int) old.getWidth(), (int) (old.getHeight()-100));
        setPreferredSize(new Dimension((int) old.getWidth(), (int) (old.getHeight()-100)));
        this.repaint();
        System.out.println(listOfStaves.size());
    }

    public void NewSymbol(int x, int y, int duration, boolean note, boolean rest, boolean isDrag, boolean isSelected){
        Symbol symbol = new Symbol(x,y,duration,note,rest,isDrag, isSelected);
        int measureduration = 0;

            symbol.setParentStaff(null);

            for(Staff staff: listOfStaves) {
                if (y >= staff.getYcoor() && y <= staff.getYcoor()+60 && x >= staff.getXcoor() && x<= staff.getXcoor()+600){
                    symbol.setParentStaff(staff);
                    break;
                }
                continue;
            }
            if (symbol.getParentStaff() != null) {
                //add to list of symbols belonging to parent staffs
                symbol.getParentStaff().getListofsymbols().add(symbol);
                if (symbol.note) {
                    //input y coordinate of center of note/rest to determine pitch
                    symbol.setPitch(symbol.getParentStaff().pitchCode(y));
                } else {
                    symbol.setPitch("Outside of any staff");
                }
            } else {
                symbol.setPitch("Outside of any staff");
            }
            this.newSymbol = symbol;
            this.repaint();
//        System.out.println(symbol.getParentStaff().getListofsymbols().size());

    }

    public void adjustSymbol(Symbol symbol) {
        if (symbol.getParentStaff() != null) {
            symbol.getParentStaff().listofsymbols.remove(symbol);
        }
        symbol.setParentStaff(null);
        for(Staff staff: listOfStaves) {
            if (symbol.ycoor >= staff.getYcoor() && symbol.ycoor <= staff.getYcoor()+60 && symbol.xcoor >= staff.getXcoor() && symbol.xcoor <= staff.getXcoor()+600){
                symbol.setParentStaff(staff);
                symbol.getParentStaff().listofsymbols.add(symbol);
                if (symbol.note) {
                    //input y coordinate of center of note/rest to determine pitch
                    symbol.setPitch(symbol.getParentStaff().pitchCode(symbol.ycoor));
                } else {
                    symbol.setPitch("Outside of any staff");
                }
                break;
            }
            else {
                symbol.setPitch("Outside of any staff");
            }
        }
        this.repaint();
    }

    public void deleteSymbol(Symbol symbol) {
        symbol.getParentStaff().listofsymbols.remove(symbol);
        this.repaint();
    }



}
