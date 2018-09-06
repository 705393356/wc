package wc.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import wc.model.CodeFile;

public class FileProcessService {

	//将文件转化为字符串
	public static String File2String(String filelocation) throws IOException{
		String str="";
		File file=new File(filelocation);
		if(file.exists()){
			FileInputStream in=new FileInputStream(file);
			int size=in.available();
			byte[] buffer=new byte[size];
			in.read(buffer);
			in.close();
			str=new String(buffer,"GB2312");
		}else {
			return null;
		}
		return str;
	}
	
	//过滤掉空格 换行 等
	public static String StringFilter(String str) throws PatternSyntaxException {

		// 使用正则表达式
		String regEx = "[\\n\\s\\r ]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim().replace(" ", "").replace("\\", "");
	}
	
	/*
	 * 
	 */
	//计算代码行数
	public static CodeFile getLines(String filelocation) throws Exception{
		CodeFile codeFile = new CodeFile();
		String str="";
		File file=new File(filelocation);
		if(file.exists()){
			FileInputStream input=new FileInputStream(file);
			BufferedReader reader=new BufferedReader(new InputStreamReader(input));
			while((str=reader.readLine())!=null){
				//判断几种行数
				if(str.trim().length()<1||str.trim().startsWith("{")||str.trim().startsWith("}")){
					//判断空行
					codeFile.setNullLines(codeFile.getNullLines()+1);
				}else if(str.trim().startsWith("/*")||str.trim().startsWith("\\*")||str.trim().startsWith("*/")||str.trim().startsWith("//")){
					//判断代码行
					codeFile.setCodeLines(codeFile.getCodeLines()+1);
				}
				//余下注释
				else {
					codeFile.setAnnotations(codeFile.getAnnotations()+1);
				}
				
				//判断一行单词个数
				if(!(str.trim().length()<1||str.trim().startsWith("{")||str.trim().startsWith("}"))){
					
					String str2 = str.trim();
					codeFile.setWordNum(codeFile.getWordNum() + str2.split(" ").length);
				}
				
				
			}
			codeFile.setTotalLines();
		}else {
			return null;
		}
		
		return codeFile;
		
	}
}
