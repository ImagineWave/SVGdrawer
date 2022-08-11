package utils;

import exceptions.NegativeCordsException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SVGWriter implements AutoCloseable{

    private File file = new File("C:\\Users\\vkukavsky.BLOGIC\\Desktop\\SVG", "Output.svg");
    private FileWriter fr = new FileWriter(file);


    public SVGWriter() throws IOException {
        whiteHeader();
    }

    private void whiteHeader()throws IOException{
        fr.write("<?xml version=\"1.0\"?>\n" +
                "<svg width=\"5000\" height=\"5000\" viewBox=\"0 0 5000 5000\"\n" +
                "xmlns=\"http://www.w3.org/2000/svg\">\n");
    }
    private void whiteFooter()throws IOException{
        fr.write("</svg>");
    }

    @Override
    public void close() throws IOException {
        whiteFooter();
        fr.close();
        System.out.println("closed file");

    }
    public void writeRect(int x,int y,int width,int height,String color) throws IOException{
        checkCords(x,y);
        StringBuilder sb = new StringBuilder();
        sb.append("<rect x=\"");
        sb.append(x); // Сюда Х
        sb.append("\" y=\"");
        sb.append(y); // Сюда Y
        sb.append("\" width=\"");
        sb.append(width); // Сюда ширину (width)
        sb.append("\" height=\"");
        sb.append(height); // Сюда высоту (height)
        sb.append("\" style=\"fill:");
        sb.append(color); //Сюда ЦВЕТ
        sb.append(";stroke:black\"/>");
        sb.append("\n");
        fr.write(sb.toString());

        //fr.write("<rect x=\""+x+"\" y=\""+y+"\" width=\""+width+"\" height=\""+height+"\" style=\"fill:"+color+";stroke:black\"/>");
    }
    public void writeRoundRect(int x,int y,int width,int height,String color) throws IOException{
        checkCords(x,y);
        StringBuilder sb = new StringBuilder();
        sb.append("<rect x=\"");
        sb.append(x); // Сюда Х
        sb.append("\" y=\"");
        sb.append(y); // Сюда Y
        sb.append("\" width=\"");
        sb.append(width); // Сюда ширину (width)
        sb.append("\" height=\"");
        sb.append(height); // Сюда высоту (height)
        sb.append("\" rx=\"30\"");
        sb.append(" ry=\"30\"");
        sb.append(" style=\"fill:");
        sb.append(color); //Сюда ЦВЕТ
        sb.append(";stroke:black\"/>");
        sb.append("\n");
        fr.write(sb.toString());
        //<rect x="10" y="70" width="100" height="50" rx="30" ry="30" style="fill:green;stroke:red"/>
    }
    public void writeRound(int cx,int cy,int radius,String color) throws IOException{
        checkCords(cx,cy);
        StringBuilder sb = new StringBuilder();
        sb.append("<ellipse cx=\"");
        sb.append(cx); // Сюда Х
        sb.append("\" cy=\"");
        sb.append(cy); // Сюда Y
        sb.append("\" ry=\"");
        sb.append(radius); // Сюда ширину (width)
        sb.append("\" rx=\"");
        sb.append(radius); // Сюда высоту (height)
        sb.append("\" style=\"fill:");
        sb.append(color); //Сюда ЦВЕТ
        sb.append(";stroke:black\"/>");
        sb.append("\n");
        //<ellipse cx="80" cy="200" rx="50" ry="50" style="fill:yellow;stroke:green;stroke-width:5px"/>
        fr.write(sb.toString());
    }
    public void writeCube(){

    }
    public void writeFlat(){

    }
    private void checkCords(int... args){
        for(int i: args){
            if(i<0){
                throw new NegativeCordsException("Coordinates can not be negative");
            }
        }
    }
}
