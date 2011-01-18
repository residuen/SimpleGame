package de.simpleGame.model;

import java.util.ArrayList;

import de.simpleGame.interfaces.XShape;

public class MapData
{
	private ArrayList<XShape> shapeList = new ArrayList<XShape>();
	
	private boolean[][] shapeArray = null;

	public boolean[][] getShapeArray() {
		return shapeArray;
	}

	public void setShapeArray(boolean[][] shapeArray) {
		this.shapeArray = shapeArray;
	}

	public ArrayList<XShape> getShapeList() {
		return shapeList;
	}

	public void setShapeList(ArrayList<XShape> shapeList) {
		this.shapeList = shapeList;
	}

}
