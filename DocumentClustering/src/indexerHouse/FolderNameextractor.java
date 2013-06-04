/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package indexerHouse;
import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
/**
 *
 * Rabindra Kharel
 */
public class FolderNameextractor {
    // IndexSearcher is = new IndexSearcher(new File("indexdirectory"));  
    public void foldernameExtractor(String term) throws CorruptIndexException, IOException, ParseException
    {
        IndexSearcher searcher = new IndexSearcher(FSDirectory.open(new File("indexdirectory")));
        System.out.println("searcher open for " + term);
	Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
	QueryParser queryParser = new QueryParser(Version.LUCENE_34,"content", analyzer);        
	Query query = queryParser.parse(term);        
       TopDocs hits = searcher.search(query, 1); //for displaying total ccontent
       ScoreDoc[] document = hits.scoreDocs;
       System.out.println("Total no of hits for content: " + hits.totalHits);
           for(int i = 0;i <document.length;i++)
           {  
               
               Document doc = searcher.doc(document[i].doc);      
               String filePath = doc.get("fullpath");
                                     
              System.out.println(filePath);
           
           }
}
}