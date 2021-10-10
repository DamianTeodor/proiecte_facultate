package main;

import graphic.Graphics;

import java.io.FileWriter;
import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            FileWriter fileWriter = new FileWriter("outer.txt");
            Graphics gp = new Graphics(fileWriter);
            gp.setVisible(true);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}