package wc;

import java.io.IOException;

import wc.model.CodeFile;
import wc.service.FileProcessService;

public class Test {

	@org.junit.Test
	public void testFile2String(){
		String filelocation = "./testFile/Interface1.java";
		String str = "";
		try {
			str = FileProcessService.File2String(filelocation);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(str);
	}
	
	@org.junit.Test
	public void testStringFilter(){
		String filelocation = "15893 95956626 	d";
		String str = "";
		str = FileProcessService.StringFilter(filelocation);
		
		System.out.println(str);
		
	}
	
	@org.junit.Test
	public void testgetlines() throws Exception{
		String filelocation = "./testFile/Interface1.java";
		CodeFile file = new CodeFile();
		file = FileProcessService.getLines(filelocation);
		
		System.out.println(file);
		
	}
	
	@org.junit.Test
	public void testDetermineWord() throws Exception{
		String filelocation = "./testFile/Interface1.java";
		String str = "";
		str = FileProcessService.File2String(filelocation);			
		
		int num = FileProcessService.DetermineWord(str);
		
		System.out.println(num+"个单词");
		
	}
	
}
