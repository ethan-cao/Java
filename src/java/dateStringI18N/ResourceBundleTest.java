package dateStringI18N;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleTest {

    public static void main(String[] args) {
        Locale initial = Locale.getDefault();
        System.out.println("default local : " + initial);


        // when resource is stored in .properties file
        Locale locale = new Locale("en");   // en or fr
        // param baseName = package name + . +  bundle file
        // it looks for file whose name is labels_local.properties (Label_en/Label_fr) in package
        ResourceBundle rb = ResourceBundle.getBundle("dateStringI18N.Labels", locale);
        System.out.println(rb.getString("hello"));

        // when resource is stored in key value pair in .java file
        Locale locale1 = new Locale("en", "CA");

        ResourceBundle rb1 = ResourceBundle.getBundle("dateStringI18N.Labels", locale1);
        System.out.println(rb1.getObject("good"));

        System.out.println();

//        Labels_en_CA newLabel = new Labels_en_CA();
//        System.out.println("contents : " + newLabel.getContents());
//        System.out.println("hello : " + newLabel.getString("hello"));
//        System.out.println("good: " + newLabel.getString("good"));



        System.out.println();

        Locale frLocale = new Locale("fr_FR");
        System.out.println(frLocale.getDisplayCountry() + " # " +frLocale.getDisplayLanguage());
        // fr_fr is only the display language, there is no country code
        // static public final Locale FRANCE = createConstant("fr", "FR");
        // static public final Locale FRENCH = createConstant("fr", "");


        // resource bundle resolution
        rb = ResourceBundle.getBundle("RB", new Locale("fr", "CA"));
       /*
        * Java will look for the following file in the classpath with the order beneath
        *
        * RB_fr_CA.java
		* RB_fr_CA.properties
		* RB_fr.java
		* RB_fr.properties
		* RB_en_US.java      		  // now trying default Locale
		* RB_en_US.properties
		* RB_en.java
		* RB_en.properties		* RB_fr.properties

		* RB.java
		* RB.properties
        */
    }
}
