package wc.model;

public class CodeFile {

	private int CodeNum = 0;
	private int CodeLines = 0;
	private int NullLines = 0;
	private int Annotations = 0;
	private int TotalLines = 0;
	private int WordNum = 0;
	
	public CodeFile() {
		super();
	}

	public CodeFile(int codeNum, int codeLines, int nullLines, int annotations) {
		super();
		CodeNum = codeNum;
		CodeLines = codeLines;
		NullLines = nullLines;
		Annotations = annotations;
	}
	
	public CodeFile Add(CodeFile code2){
		this.setAnnotations(this.getAnnotations()+ code2.getAnnotations());
		this.setCodeLines(this.getCodeLines()+code2.getCodeLines());
		this.setNullLines(this.getNullLines()+code2.getNullLines());
		this.setTotalLines2(this.getTotalLines() + code2.getTotalLines());
		
		return this;
	}
	
	public int getCodeNum() {
		return CodeNum;
	}
	public void setCodeNum(int codeNum) {
		CodeNum = codeNum;
	}
	public int getCodeLines() {
		return CodeLines;
	}
	public void setCodeLines(int codeLines) {
		CodeLines = codeLines;
	}
	public int getNullLines() {
		return NullLines;
	}
	public void setNullLines(int nullLines) {
		NullLines = nullLines;
	}
	public int getAnnotations() {
		return Annotations;
	}
	public void setAnnotations(int annotations) {
		Annotations = annotations;
	}
	public int getTotalLines() {
		return TotalLines;
	}
	public void setTotalLines() {
		TotalLines = this.getCodeLines() + this.getNullLines() + this.getAnnotations();
	}
	
	public void setTotalLines2(int totalLines){
		TotalLines = totalLines;
	}
	
	public int getWordNum() {
		return WordNum;
	}

	public void setWordNum(int wordNum) {
		WordNum = wordNum;
	}
	
	
}
