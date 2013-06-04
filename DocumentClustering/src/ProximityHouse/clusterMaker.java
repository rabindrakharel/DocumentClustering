/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package ProximityHouse;

import GUI.clusterPanel;
import Jama.Matrix;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
*
* @author paradise lost
*/
public class clusterMaker {
Matrix proximity;
ArrayList<ArrayList<Integer>> resultList= new ArrayList<ArrayList<Integer>>();




clusterMaker(Matrix proximit) {        
    proximity=proximit;       
    for(int col=0;col<proximity.getColumnDimension();col++)
    {   ArrayList<Integer> integer=new ArrayList<Integer>();
        integer.add(col);
        resultList.add(integer);
    }
}



public ArrayList<ArrayList<Integer>> getResult()
{

//     for(int row=0;row<proximity.getRowDimension();row++)
//{
//    for(int col=0;col<proximity.getColumnDimension();col++)
//    {    
//  System.out.print(String.format("%f \t",proximity.get(row, col)));
//    }
//      System.out.print("\n");
//}


ArrayList<ArrayList<Integer>> result=resultList;
float value=clusterPanel.homogenety;
int iter=0;
boolean status=true;
while(status)
{ 
    iter++;
status=false;
int save_row = 0;
int save_col = 0;
float maximum_check=(float) value;// homogenety degreee
for(int row=0;row<proximity.getRowDimension();row++)
for(int col=row+1;col<proximity.getColumnDimension();col++)
    {
    if(proximity.get(row,col)>maximum_check)
    {
     maximum_check=(float) proximity.get(row,col); 
     save_row=row;
     save_col=col;
     status=true;
    }
    }
//System.out.println("here");
if(status)
{
proximity=deleteRow(proximity,save_row);
proximity=deleteCol(proximity,save_row);
ArrayList<Integer> save1=new ArrayList<Integer>();
ArrayList<Integer> save2=new ArrayList<Integer>();
save1=result.get(save_row);
save2=result.get(save_col);
//  System.out.println("merged"+save2.toString()+","+save1.toString());
save2=mergeList(save1,save2);
result.set(save_col, save2);    
result.remove(save_row);
}
}
    return result;
}

public static Matrix deleteRow(Matrix proximity,int ro)
{ 
Matrix resultMatrix =new Matrix(new double[proximity.getRowDimension()-1][proximity.getColumnDimension()]);
int ROW=0;
for(int row=0;row<proximity.getRowDimension();row++)
{  int COL=0;
  if(row==ro)
  continue;
  for(int col=0;col<proximity.getColumnDimension();col++)
    {//System.out.print(ROW+","+COL+"\t");
       resultMatrix.set(ROW,COL,proximity.get(row,col));  
       COL++;
    }
   ROW++;

//  System.out.print(row+"\n");

}
return resultMatrix;
}

public static Matrix deleteCol(Matrix proximity,int co)
{ 
Matrix resultMatrix =new Matrix(new double[proximity.getRowDimension()][proximity.getColumnDimension()-1]);
int COL=0;
for(int col=0;col<proximity.getColumnDimension();col++)
{  int ROW=0;
   if(col==co)   
    continue;


for(int row=0;row<proximity.getRowDimension();row++)
    {    
  resultMatrix.set(ROW,COL,proximity.get(row,col));  
  ROW++;
    }
COL++;
}
return resultMatrix;
}

public ArrayList<Integer> mergeList(ArrayList<Integer> listOne,ArrayList<Integer> listTwo)
{
Set<Integer> set1 = new HashSet<Integer>(listOne);
Set<Integer> set2 = new HashSet<Integer>(listTwo);
set1.addAll(set2);
List<Integer> mergeResult = new ArrayList<Integer>(set1);
return (ArrayList<Integer>) mergeResult;
}  
}


