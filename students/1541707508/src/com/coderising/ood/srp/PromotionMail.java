package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {


	protected String sendMailQuery = null;


//	protected String smtpHost = null;
//	protected String altSmtpHost = null; 
//	protected String fromAddress = null;
//	protected String toAddress = null;
//	protected String subject = null;
//	protected String message = null;

//	protected String productID = null;
//	protected String productDesc = null;
	protected List<Product> productList = null;

	private static Configuration config; 
	
	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";
	

	public static void main(String[] args) throws Exception {

		File f = new File("D:\\office\\zcy\\gitHub\\1541707505\\students\\1541707508\\src\\com\\coderising\\ood\\srp\\product_promotion.txt");
		boolean emailDebug = false;

		PromotionMail pm = new PromotionMail(f, emailDebug);

	}

	
	public PromotionMail(File file, boolean mailDebug) throws Exception {
		
		//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		List<Product> productList = readFileToProducts(file);
		config = new Configuration();
		
		for (Product product : productList) {
			System.out.println(product);
			Mail mail = new Mail();
			mail.setProduct(product);
			setLoadQuery(product.getProductID());
			sendEMails(mail, mailDebug, loadMailingList()); 
		}

		
		
//		setSMTPHost();
//		setAltSMTPHost(); 
	

//		setFromAddress();

		
		
//		sendEMails(mailDebug, loadMailingList()); 

		
	}


//	protected void setProductID(String productID) 
//	{ 
//		this.productID = productID; 
//		
//	} 

//	protected String getproductID() 
//	{
//		return productID; 
//	} 

	protected void setLoadQuery(String productID) throws Exception {
		
		sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + productID +"' "
				+ "and send_mail=1 ";
		
		
		System.out.println("loadQuery set");
	}

	protected List<Product> readFileToProducts(File file) throws IOException // @02C
	{
		List<Product> productList = new ArrayList<Product>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = null;
			while((temp = br.readLine()) != null) {
				String[] data = temp.split(" ");
				Product p = new Product(data[0], data[1]);
				productList.add(p);
			}

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			if(br != null) {
				br.close();
			}
		}
		return productList;
	}

	protected Mail configureEMail(Mail mail, HashMap userInfo) throws IOException 
	{
		String toAddress = (String) userInfo.get(EMAIL_KEY); 
		if (toAddress.length() > 0)  {
			String name = (String) userInfo.get(NAME_KEY);
			String subject = "您关注的产品降价了";
			String message = "尊敬的 "+name+", 您关注的产品 " + mail.getProduct().getProductDesc() + " 降价了，欢迎购买!" ;
			mail.setTo(toAddress);
			mail.setSubject(subject);
			mail.setContent(message);
			mail.setFrom(config.getProperty(ConfigurationKeys.EMAIL_ADMIN));
			mail.setSmtpHost(config.getProperty(ConfigurationKeys.SMTP_SERVER));
		}
		return mail;
	}

	protected List loadMailingList() throws Exception {
		return DBUtil.query(this.sendMailQuery);
	}
	
	
	protected void sendEMails(Mail mail, boolean debug, List mailingList) throws IOException 
	{
		System.out.println("开始发送邮件");

		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				mail = configureEMail(mail, (HashMap) iter.next());  
				try 
				{
					if (mail.getTo().length() > 0)
						MailUtil.sendEmail(mail, debug);
				} 
				catch (Exception e) 
				{
					try {
						mail.setSmtpHost(config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
						MailUtil.sendEmail(mail, debug); 
					} catch (Exception e2) 
					{
						System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage()); 
					}
				}
			}
			

		}

		else {
			System.out.println("没有邮件发送");
			
		}

	}
}
