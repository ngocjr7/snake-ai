package com.gui;

import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JFrame;

import com.gui.*;
import com.ai.Utils;

public class Window extends JPanel {
	private static final long serialVersionUID = -2542001418764869760L;
	public static ArrayList<ArrayList<DataOfSquare>> Grid;
	public int width;
	public int height;

	public Window(int width,int height,long speed, int alg, GameFrame gameFrame){
		
		this.width = width;
		this.height = height;
		// Creates the arraylist that'll contain the threads
		Grid = new ArrayList<ArrayList<DataOfSquare>>();
		ArrayList<DataOfSquare> data;
		
		// Creates Threads and its data and adds it to the arrayList
		for(int i=0;i<width;i++){
			data= new ArrayList<DataOfSquare>();
			for(int j=0;j<height;j++){
				int color = (j==0||j==height-1||i==0||i==width-1)?3:0;
				DataOfSquare c = new DataOfSquare(color);
				data.add(c);
			}
			Grid.add(data);
		}
		
		// Setting up the layout of the panel
		this.setLayout(new GridLayout(this.width,this.height,0,0));
		setBackground(Color.white);
		// Start & pauses all threads, then adds every square of each thread to the panel
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				this.add(Grid.get(i).get(j));
			}
		}
                
                Tuple position1 = new Tuple(10,10);
		ThreadsController threadControllerPlayer1 = new ThreadsController(true,speed,position1,gameFrame,this);
		threadControllerPlayer1.algorithm = alg;
		threadControllerPlayer1.start();
	}

	public static int[][] convertSimpleGrid(ArrayList<ArrayList<DataOfSquare>> squares) {
		int h = squares.size();
		int w = squares.get(0).size();

		int[][] res = new int[h][w];
		for (int i = 0; i < h; i++) 
			for (int j = 0; j < w; j++)
				res[i][j] = squares.get(i).get(j).obj;
		return res;
	}
}