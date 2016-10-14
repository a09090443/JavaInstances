

public class CachePersonalVO {

	private String lineNo;
	private String content;

	public CachePersonalVO(String lineNo, String content) {
		this.lineNo = lineNo;
		this.content = content;
	}

	public CachePersonalVO(String str) {
		this.lineNo = str.substring(0, 2);
		this.content = str.substring(2, 100);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	@Override
	public String toString() {
		return content;
	}

	public static String toHeader() {
		return "content";
	}
}
