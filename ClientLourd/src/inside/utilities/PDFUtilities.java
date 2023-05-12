package inside.utilities;

import java.io.FileOutputStream;
import java.io.IOException;

//import com.lowagie.text.Document;
/*import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;*/

/**
 * Classe implémentant des méthodes utiles au traitement du format pdf
 *
 * @author mourtaza
 *
 */
public class PDFUtilities {
	/**
	 * Exporte le texte dans un document en format pdf
	 *
	 * @param text     Contenu du document
	 * @param filePath Chemin du fichier pdf
	 */
	public static void exportToPDF(String text, String filePath) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();

			String[] paragraphs = text.split("\n");

			for (String paragraph : paragraphs) {
				document.add(new Paragraph(paragraph));
			}

			document.close();
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}
}
