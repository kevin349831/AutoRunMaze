// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/5/6 下午 01:28:28
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   JavaApplication11.java

package javaapplication11;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

public class JavaApplication11
    implements ActionListener
{

    public JavaApplication11()
    {
    }

    public static void main(String args[])
    {
        CreateJFrame();
    }

    public static void CreateJFrame()
    {
        frm.setResizable(false);
        frm.setSize(500, 520);
        frm.setBackground(Color.yellow);
        frm.setVisible(true);
        frm.setLayout(null);
        bt.setLabel("Open File");
        bt.setBounds(10, 35, 480, 30);
        frm.add(bt);
        bt.addActionListener(new JavaApplication11());
        frm.setTitle("Maze Game    V1.0");
        frm.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent event)
            {
                System.exit(0);
            }

        }
);
        for(int i = 0; i < 41; i++)
        {
            for(int j = 0; j < 41; j++)
            {
                Lab[i][j] = new Label();
                Lab[i][j].setBounds(x, y, 10, 10);
                x += 10;
                frm.add(Lab[i][j]);
            }

            x = 35;
            y += 10;
        }

        x = 1;
        y = 1;
    }

    public static void FindFile()
    {
        JFileChooser JFC = new JFileChooser();
        JFC.setDialogTitle("\u9078\u64C7\u6A94\u6848");
        int result = JFC.showOpenDialog(frm);
        if(result == 0)
            FileName = JFC.getSelectedFile().getAbsolutePath();
    }

    public static void MazeLoad()
        throws IOException
    {
        FileReader fr = new FileReader(FileName);
        BufferedReader br = new BufferedReader(fr);
        char s[] = new char[82];
        int n = -1;
        while(br.ready()) 
        {
            n++;
            s = br.readLine().toCharArray();
            int i = 0;
            while(i < 41) 
            {
                maze[n][i] = s[i * 2] - 48;
                i++;
            }
        }
        fr.close();
    }

    public static void CreateMazeMap()
        throws InterruptedException
    {
        for(int i = 0; i < 41; i++)
        {
            for(int j = 0; j < 41; j++)
            {
                Thread.currentThread();
                Thread.sleep(5L);
                if(maze[i][j] == 1)
                    Lab[i][j].setBackground(Color.BLACK);
                else
                    Lab[i][j].setBackground(Color.WHITE);
            }

        }

        x = 1;
        y = 1;
        GREEN();
        MazeRun();
    }

    public static void MazeRun()
        throws InterruptedException
    {
        do
        {
            if(x == 39 && y == 39)
                break;
            Thread.currentThread();
            Thread.sleep(50L);
            if(maze[x - 1][y] == 4 && maze[x][y - 1] == 1 && maze[x][y + 1] == 1 && maze[x + 1][y] == 1)
            {
                GRAY();
                x--;
            } else
            if(maze[x - 1][y] == 1 && maze[x][y - 1] == 4 && maze[x][y + 1] == 1 && maze[x + 1][y] == 1)
            {
                GRAY();
                y--;
            } else
            if(maze[x - 1][y] == 1 && maze[x][y - 1] == 1 && maze[x][y + 1] == 4 && maze[x + 1][y] == 1)
            {
                GRAY();
                y++;
            } else
            if(maze[x - 1][y] == 1 && maze[x][y - 1] == 1 && maze[x][y + 1] == 1 && maze[x + 1][y] == 4)
            {
                GRAY();
                x++;
            } else
            if(maze[x + 1][y] == 3)
            {
                x++;
                GREEN();
            } else
            if(maze[x][y + 1] == 3)
            {
                y++;
                GREEN();
            } else
            if(maze[x][y - 1] == 3)
            {
                y--;
                GREEN();
            } else
            if(maze[x - 1][y] == 3)
            {
                x--;
                GREEN();
            }
        } while(true);
    }

    public static void GREEN()
    {
        maze[x][y]++;
        Lab[x][y].setBackground(Color.GREEN);
    }

    public static void GRAY()
    {
        maze[x][y] = 1;
        Lab[x][y].setBackground(Color.GRAY);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == bt)
        {
            FindFile();
            if(FileName != null)
            {
                try
                {
                    MazeLoad();
                }
                catch(IOException ex)
                {
                    Logger.getLogger(javaapplication11/JavaApplication11.getName()).log(Level.SEVERE, null, ex);
                }
                try
                {
                    CreateMazeMap();
                }
                catch(InterruptedException ex)
                {
                    Logger.getLogger(javaapplication11/JavaApplication11.getName()).log(Level.SEVERE, null, ex);
                }
            }
            FileName = null;
        }
    }

    static Frame frm = new Frame();
    static Label Lab[][] = new Label[41][41];
    static Button bt = new Button();
    static String FileName = null;
    static int maze[][] = new int[41][41];
    static int x = 35;
    static int y = 70;

}

