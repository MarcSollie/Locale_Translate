package nl.marcsollie.mactranslate;

import java.io.*;
import java.util.*;

import org.apache.commons.lang3.StringUtils;


import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class LocaleTranslate {

	public static void main(String[] args) {
		if(args.length < 4){
			System.out.println("Usage: \"Input File\" \"Output File\" \"Begin tag\" \"End tag\"");
			System.exit(0);
		}
		try{
			/**Your Azure Datamarket ClientID and ClientSecret**/
						//FIXME INPUT CLIENT ID
		String clientId = "CLIENT-KEY";
						//FIXME INPUT CLIENT SECRET
		String clientSecret = "CLIENT-SECRET";
		/**Set's the ID and Secret.**/
		Translate.setClientId(clientId); Translate.setClientSecret(clientSecret);
		
		/**Reads input file.**/
	    BufferedReader br = new BufferedReader(new FileReader(args[0]));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	    	String everything = sb.toString();

	    	/**Grabs the strings between the tags**/
	    	String[] stringsoriginal = StringUtils.substringsBetween(everything, args[2], args[3]);
	    	/**List where the translated string are put back in**/
	    	List<String> translated = new ArrayList<String>();
	    	
	    	//FIXME Change to Language.OUTPUTLANGUAGE
	    	Language outlanguage = null;
	    	int j = 0;
	    	while(j < stringsoriginal.length){
	    		System.out.println("Translating string #"+j);
	    		/**Translate and add to the List**/
	    		translated.add(Translate.execute(stringsoriginal[j], Language.AUTO_DETECT, outlanguage));
	    		j++;
	    	}
	    	int i = 0;
	    	String nieuw = everything;
	    	/**Replace original occurence in file.**/
	    	while(i < translated.size()){
	    		nieuw = StringUtils.replaceOnce(nieuw, stringsoriginal[i], translated.get(i));
	    		i++;
	    	}
	    	PrintWriter out = new PrintWriter(args[1]);
	    	/**Outputs translated file**/
	    	out.println(nieuw);

	    	br.close();
	    	out.close();
	    

		} catch(IOException e){
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
