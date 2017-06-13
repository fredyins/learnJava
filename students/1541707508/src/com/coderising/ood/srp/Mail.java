package com.coderising.ood.srp;

public class Mail {
	private Product product = null;
	
	private String from = ConfigurationKeys.NULL_STR;
	private String to = ConfigurationKeys.NULL_STR;
	private String subject = ConfigurationKeys.NULL_STR;
	private String content = ConfigurationKeys.NULL_STR;
	private String smtpHost = ConfigurationKeys.NULL_STR;
	
	public Mail() {
	}

	public Mail(Product product, String from, String to, String subject, String content, String smtpHost) {
		this.product = product;
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.smtpHost = smtpHost;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(from).append("\n");
		buffer.append("To:").append(to).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(content).append("\n");
		return buffer.toString();
	}
	
	
}
