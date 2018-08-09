package com.match.springmvc.data;
/**
 * 进度条
 * @author lenovo
 *
 */
public class Progress {
	
	private long bytesRead;
	private long contentLength;  // 内容长度
	private long items;
	
	public long getBytesRead() {
		return bytesRead;
	}
	public void setBytesRead(long bytesRead) {
		this.bytesRead = bytesRead;
	}
	public long getContentLength() {
		return contentLength;
	}
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	public long getItems() {
		return items;
	}
	public void setItems(long items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		return "Progress [bytesRead=" + bytesRead + ", contentLength=" + contentLength + ", items=" + items + "]";
	}
}
