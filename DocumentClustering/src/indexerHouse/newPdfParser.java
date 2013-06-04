/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package indexerHouse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 *
 * @author Rabindra Kharel
 */
public class newPdfParser {
    
    public String pdfParse(String filePath) throws FileNotFoundException, IOException{
    FileInputStream fi = new FileInputStream(new File(filePath));       
    PDFParser parser = new PDFParser(fi);  
    parser.parse();  
    COSDocument cd = parser.getDocument();  
    PDFTextStripper stripper = new PDFTextStripper();  
    String text = stripper.getText(new PDDocument(cd)); //error dekhaudai chha yesle 
    cd.close();
    return text; }
}
