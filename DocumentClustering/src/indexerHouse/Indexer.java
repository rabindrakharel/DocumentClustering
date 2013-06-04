/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package indexerHouse;

import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import GUI.clusterPanel;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
author : Rabindra Kharel
*/
public class Indexer {
    private String dataDir;
    static int file_counts=0;
    SourceFilesGetter sourcefile = new SourceFilesGetter(); 
private IndexWriter writer;
private FSDirectory fsDir ;
//Directory ramDir;
IndexWriterConfig config; 


public Indexer(String dataDIr) throws IOException {
    File index = new File(dataDIr+"\\LuceneIndex");
     index.mkdirs();
    try{
this.dataDir=dataDIr;
fsDir = FSDirectory.open(index);
StandardAnalyzer analyzer=new StandardAnalyzer(Version.LUCENE_34); 
config = new IndexWriterConfig(Version.LUCENE_34,analyzer);
writer = new IndexWriter(fsDir,config);
writer.setRAMBufferSizeMB(500); 
writer.setMergeFactor(100);
writer.setUseCompoundFile(false);               
    }
    catch(Exception e)
    {
    }
}
public void close() throws IOException {
    writer.optimize();
    writer.close();
}

public int index() throws Exception {
          File[] files = sourcefile.certainFolder(dataDir);
       for (int i = 0; i < files.length; i++) { 
            try{
               File f = files[i];     
            if (!f.isDirectory() &&
            !f.isHidden() &&
            f.exists() && 
            f.canRead() &&
            f.length()>0.0 && 
            f.isFile()&&
            acceptFile(f)) {   
            indexFile(f);
            file_counts++;
            System.out.println(file_counts); 
          clusterPanel.workProgress((int)(file_counts)*100/files.length);
            } 
        }
        
        catch(Exception e)
        {
            continue;
        }
        }
       return 0;
        
}


 

protected boolean acceptFile(File f) throws Exception {
    
   
    if(f.getName().endsWith(".doc"))
    {   //System.out.println("In this block");
        //getDocumentdoc(f);
        return true;
    }
    else if(f.getName().endsWith(".pdf"))
    {
       // System.out.println("getting pdf file");
             //getDocumentPDF(f);
             return true;
    } 
    else if(f.getName().endsWith(".txt"))
        return true;
            
    else        
    return f.getName().endsWith("");
}

protected Document getDocument(File f) throws Exception {
        Document doc = new Document();
        doc.add(new Field("content", new FileReader(f),Field.TermVector.YES));
        doc.add(new Field("fullpath", f.getAbsolutePath(),Field.Store.YES, Field.Index.ANALYZED));   
       return doc;
}

protected Document getDocumentDOC(File f) throws Exception
{  
        DocumentFileParsing docfileparse = new DocumentFileParsing();
        Document doc = new Document();
        doc.add(new Field("content",docfileparse.DocParse(toString(f)),Field.Store.YES,Field.Index.ANALYZED,Field.TermVector.YES));
        doc.add(new Field("fullpath", f.getAbsolutePath(),Field.Store.YES, Field.Index.ANALYZED));
        return doc;
       
}

protected Document getDocumentPDF(File f) throws Exception
{
        newPdfParser pdffileparse = new newPdfParser();
        Document doc = new Document();
        doc.add(new Field("content",pdffileparse.pdfParse(toString(f)),Field.Store.YES,Field.Index.ANALYZED,Field.TermVector.YES));
        doc.add(new Field("fullpath", f.getAbsolutePath(),Field.Store.YES, Field.Index.ANALYZED));
        return doc;
        

}


private void indexFile(File f) throws Exception {
        Document doc = new Document();
        System.out.println("Indexing " + f.getCanonicalPath());
        if(f.getName().endsWith(".doc"))
         doc = getDocumentDOC(f);
        
        else if(f.getName().endsWith(".pdf"))
        doc = getDocumentPDF(f);
        
        else 
             doc=getDocument(f);
        
        if (doc != null) {
        writer.addDocument(doc); 
        }
    }



public String toString(File file)
{
 return "" + file.getAbsolutePath();
}

}


