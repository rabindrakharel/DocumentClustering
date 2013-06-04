/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package indexerHouse;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author rabindra
 */
public class SourceFilesGetter {
    Checker checker = new Checker();
    int totalnofiles ;
    
    
    public File[] wholeDrive()
  {
     
  File[] allfiles = File.listRoots(); 			
      for(File f : allfiles){ 
          System.out.println("from " + f + "drive");
		try
		{			
		
			File dir = new File(f.getPath());
			String[] extensions = new String[] { "txt", "pdf" ,"doc",".docx"};	
			List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
                        int i =0;
                        File[] filefromwholedrive = new File[files.size()];
		        for(File file : files){ 
                        filefromwholedrive[i] = file;
                        i++;
			}
                    return filefromwholedrive;
		}
			catch(Exception e){			
			
				continue;			
			}   
               
      }
                 return null;
}
    public File[] whichDrive() throws Exception
{

    
	System.out.print("From which drive u want the file: ");
	Scanner drive = new Scanner(System.in);
	String newDrive = drive.nextLine();		
        File dir = new File(newDrive);			
        String[] extensions = new String[] { "txt", "pdf" ,"doc"};
        List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
        File[] filetoindex = new File[files.size()];
        for(File f:filetoindex)
        {
            f=null;
        }
        int i = 0;
       
	for(File file : files) 
	{     
          boolean b = checker.CheckerIndexer(file.getName());                      
          if(b)
          {              
             filetoindex[i]=file;
             i= i +1;
             continue;               
          }
                 
         }
        return filetoindex;
        
      
                
	
}

    public File[] certainFolder(String newFolder) throws Exception
    {    
        File folderfile = new File(newFolder);
        String[] extensions = new String[]{"txt","pdf","doc"};
        List<File> files = (List<File>) FileUtils.listFiles(folderfile, extensions, true);
        File[] filetoindex = new File[files.size()];
        int i = 0;
       for(File f:filetoindex)
        {
            f=null;
        }
        
	for(File file : files) 
	{     
          boolean b = checker.CheckerIndexer(file.getName());                      
          if(b)
          { 
             
             filetoindex[i]=file;
             i= i +1;
             continue;
               
          }
                 
         }
        return filetoindex;
        
      
      
    }
    
    
    
}
