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
		int WordsNum = 0;
		File file=new File(filelocation);
		if(file.exists()){
			FileInputStream input=new FileInputStream(file);
			@SuppressWarnings("resource")
			BufferedReader reader=new BufferedReader(new InputStreamReader(input));
			while((str=reader.readLine())!=null){
				//判断几种行数
				if(str.trim().length()<1||str.trim().startsWith("{")||str.trim().startsWith("}")){
					//判断空行
					codeFile.setNullLines(codeFile.getNullLines()+1);
				}else if(!(str.trim().startsWith("/*")||str.trim().startsWith("\\*")||str.trim().startsWith("*/")||str.trim().startsWith("//")||str.trim().startsWith("*"))){
					//判断代码行
					codeFile.setCodeLines(codeFile.getCodeLines()+1);
					String str2 = str.trim();
					System.out.println(str2);
					
					//在代码行计算单词数
					int newNum = DetermineWord(str2);
					System.out.println("---------------" + newNum);
					WordsNum +=newNum;
				}
				//余下注释
				else {
					codeFile.setAnnotations(codeFile.getAnnotations()+1);
				}
				
				
			}
			codeFile.setWordNum(WordsNum);
			codeFile.setTotalLines();
		}else {
			return null;
		}
		
		return codeFile;
		
	}
	
	//判断字符串中单词数量，包含了去符号化
	public static int DetermineWord(String str){
		int num = 0;
		
		char[] c = str.toCharArray();
		int flag = 0;
		
		for(int i=0;i<c.length;i++){
//			System.out.print(c[i] +" ");
			if(Character.isLetterOrDigit(c[i])&&flag==0){
				flag = 1;
			}else if(Character.isLetterOrDigit(c[i])&&flag==1){
				continue;
			}else if(!Character.isLetterOrDigit(c[i])&&flag==1){
				num++;
				flag=0;
				continue;
			}
			
			
		}
		System.out.println(num);
		return num;
		
	}
	
}
