package com.tools.email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import com.tools.entities.MailObject;

public class MalinatorService implements EmailService{

	public void sendMessage() {
		System.out.println("Mailinator send message");
		
	}

	public void readMessage() {
		  Properties props = new Properties();
		    Session session = Session.getDefaultInstance(props, null);
		    try {
		        Store store = session.getStore("pop3");
		        store.connect("pop.mailinator.com", 110, "horatiuencian@mailinator.com", "12016768877");
		        Folder inbox = store.getFolder("inbox");
		        if(inbox == null) {
		            System.out.println("no inbox");
		        } else {
		            inbox.open(Folder.READ_ONLY);
		            for(Message message: inbox.getMessages()) {

		                byte[] buffer = new byte[10000];

		                int read = 0;

		                try {

		                    while((read = message.getInputStream().read(buffer, 0, 1024)) > 0) {
		                        for(int i = 0; i < buffer.length; i++) {
		                            System.out.print((char)buffer[i]);
		                        }
		                    }
		                } catch (IOException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }

		                /*try {
		                    System.out.println(message.getContent().toString());
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }*/
		            }
		        }
		        inbox.close(false);
		        store.close();
		    } catch (NoSuchProviderException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    } catch (MessagingException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		
	}

	@Override
	public MailObject getMessage() {
		MailObject mailObject = new MailObject();
		return null;
	}

}
