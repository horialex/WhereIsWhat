package com.tools.email;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

import org.jsoup.Jsoup;

import com.tools.entities.Mail;

public class GmailService implements EmailService {


	@Override
	public Mail readEmail() {
		Mail mailObject = new Mail();
		String host = "pop.gmail.com";// change accordingly
		String mailStoreType = "pop3";
		String user = "alexandruhoratiu27@gmail.com";// change accordingly
		String password = "alexandruhoratiu123.,";// change accordingly
		try {

			// create properties field
			Properties properties = new Properties();
			properties.put("mail.imap.host", host);
			properties.put("mail.imap.port", "993");
			properties.put("mail.imap.starttls.enable", "true");

			Session emailSession = Session.getDefaultInstance(properties);

			// create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("imaps");
			store.connect(host, user, password);

			// create the folder object and open it
			Folder emailFolder = store.getFolder("Inbox");
			emailFolder.open(Folder.READ_WRITE);

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();
			emailFolder.setFlags(messages, new Flags(Flags.Flag.SEEN), false);
			System.out.println("messages.length --- " + messages.length);
			
			Message message = messages[messages.length - 1];
			mailObject.setSubject(message.getSubject());
			mailObject.setSender(message.getFrom()[0].toString().substring(14, 37));
			mailObject.setMailContent(getTextFromMessage(message));

			// close the store and folder objects
			emailFolder.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mailObject;
	}
	

	private String getTextFromMessage(Message message) throws Exception {
		String result = "";
		if (message.isMimeType("text/plain")) {
			return message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			result = "";
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			int count = mimeMultipart.getCount();
			for (int i = 0; i < count; i++) {
				BodyPart bodyPart = mimeMultipart.getBodyPart(i);
				if (bodyPart.isMimeType("text/plain")) {
					result = result + "\n" + bodyPart.getContent();
					break; // without break same text appears twice in my tests
				} else if (bodyPart.isMimeType("text/html")) {
					String html = (String) bodyPart.getContent();
					result = result + "\n" + Jsoup.parse(html).text();
				}
			}
			return result;
		} else if (message.isMimeType("text/html")) {
			String html = (String) message.getContent();
			result = result + "\n" + Jsoup.parse(html).text();
			return result;
		}
		return "";
	}

	

}
