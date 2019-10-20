package IO;

import java.io.*;

public class IOTest {

    public static void main(String[] args) {
//    	FileClassTest();
    	FileWriterAndFileReader();
//    	searchFileTest();
    }
    
    public static void FileClassTest(){
    	try {
    		// this just only create an object, 
    		// it does not mean creating the file
    		File file = new File("fileWrite1.txt");  
    		System.out.println( file.exists() );
    		
    		// this creates the file
    		boolean newFile = file.createNewFile();  
    		System.out.println( newFile );
    		System.out.println( file.exists() );
    	} catch (IOException e){
    	}
    }
    
    public static void FileWriterAndFileReader(){
    	char[] in = new char[50];  // to store input
    	int size = 0;
    	
    	try {
    		File file = new File("fileWrite2.txt");  // this just only creates an object 
    		FileWriter fw = new FileWriter(file);   // this creates the file

    		fw.write("bababa");
    		fw.write("1111bababa");  
    		fw.flush(); // call flush() only when writing data. Readers don't have flush() methods.
    		fw.close();
    		
    		FileReader fr = new FileReader(file);
    		size = fr.read(in);
    		System.out.println( size + " " );
    		
    		for ( char c : in){
    			System.out.print(c);
    		}
    		
    		fr.close();
    	} catch (IOException e){
    	}
    }
    
    public static void readLineTest() throws IOException {
    	File file = new File("fileWrite3.txt");  
    	file.createNewFile();  

    	FileReader fr = new FileReader(file);
    	BufferedReader br = new BufferedReader(fr);
    	
    	// Automatic Resource Management since JDK 7, see JDBC.ReadWriteTest for example
    	// no need to need to close the reader
    	// reader is scoped to just the try block
    	try (Reader reader = new BufferedReader(new FileReader(file)) ){
    	} catch (IOException e){
    	}
    	
    	String s;
    	while(  (s = br.readLine()) != null ){ // readLine returns null when there is no more data to read
    		System.out.println(s);
    	}
    	
    	br.close();
    }

    public static void createDirectory(){
    	// create a file inside a directory
    	File myDir = new File("mydir");   // creates an object
    	myDir.mkdir();                    // creates an actual directory 
    	File myFile = new File(myDir, "myFile.txt");  	// put file object in to the directory 
    	
    	try {
			myFile.createNewFile();
		} catch (IOException e) {
		}

    	// rename the file
    	File newName = new File(myDir, "newName.text");
    	myFile.renameTo(newName);
    	
    	// rename directory
    	File newDir = new File("newDir");
    	myDir.renameTo(newDir);
    	
    	myFile.delete();  // delete the file
    	myDir.delete();   // delete the directory
    	// Directories can only be deleted if they are empty.
    	// Trying to delete a nonempty directory will throw a DirectoryNotEmptyException.
    }

    public static void searchFileTest(){
    	String[] files = new String[100];
    	File search = new File("searchThis");
    	files= search.list();   // create the list
    	
    	for (String fn : files){
    		System.out.println("found " + fn);
    	}
    }
}