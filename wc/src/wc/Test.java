package wc;

import wc.model.CodeFile;
import wc.service.FileProcessService;

public class Test {

	public static void main(String[] args) throws Exception {
		String file = "D:\\myeclipse16\\workspace\\test\\src\\Item1\\test3.java";
		
		CodeFile code = FileProcessService.getLines(file);
		String str = FileProcessService.StringFilter(FileProcessService.File2String(file));
		System.out.println("空行："+code.getNullLines() + "代码行："+code.getCodeLines() + "注释："+code.getAnnotations()+"总行数："+code.getTotalLines());
		System.out.println("字符数："+ str.length());
		System.out.println("单词数："+code.getWordNum());
		System.out.println(str);
	}

}
