/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package indexerHouse;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * Rabindra kharel 
 */
public class Checker {
    
    
    
    public boolean CheckerIndexer(String filePath) throws Exception
    {        
        try
        {        
                 IndexSearcher searcher = new IndexSearcher(
                              FSDirectory.open(new File("indexDirectory")));       
                Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);        
                QueryParser queryParserfilepath = new QueryParser(
                        Version.LUCENE_34,"filename", analyzer);
                Query query = queryParserfilepath.parse(filePath);
                TopDocs hits = searcher.search(query, 10);               
                if(hits.totalHits > 0)
                {                             
                    return false;                        
                }
                else     
                {
                    return true;}
            }//if there is no index then first make index 
           catch(Exception e)
        {     
            
              return true;
              
        }
        
    }
}
