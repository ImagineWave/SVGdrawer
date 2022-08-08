package utils;

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
                "<svg width=\"300\" height=\"300\" viewBox=\"0 0 300 300\"\n" +
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
        StringBuilder sb = new StringBuilder();
        sb.append("<rect x=\"");
        sb.append(x); // Сюда Х
        sb.append("\" y=\"");
        sb.append(y); // Сюда Y


        fr.write("<rect x=\""+x+"\" y=\""+y+"\" width=\""+width+"\" height=\""+height+"\" style=\"fill:"+color+";stroke:black\"/>");
    }
//    public void writeRoundRect(x,y,w,h,r1,r2,color1,color2){
//
//    }
    public void writeRound(){

    }
    public void writeCube(){

    }
    public void writeFlat(){

    }
}
