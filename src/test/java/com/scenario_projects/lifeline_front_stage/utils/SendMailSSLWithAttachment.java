package com.scenario_projects.lifeline_front_stage.utils;

import com.scenario_projects.lifeline_front_stage.dataProvider.PathToReportZipFile;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

import static com.scenario_projects.lifeline_front_stage.dataProvider.DateProvider.currentDate;
import static com.scenario_projects.lifeline_front_stage.dataProvider.DateProvider.newCurrentTime;

public class SendMailSSLWithAttachment {

    public static void sendReportFileToMail() {

        // Create object of Property file
        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        //props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.ukr.net");
        props.put("mail.smtp.port", "465");
        //props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.ukr.net");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("testsendmessages@ukr.net", "6gk30G!glv4");
                    }
                });
        try {

            // Create object of MimeMessage class
            Message message = new MimeMessage(session);

            // Set the from
            message.setFrom(new InternetAddress("testsendmessages@ukr.net"));

            // Set the recipient address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("oleg.velmik@ukr.net"));

            // Add the subject link
            message.setSubject("Testing report from " + currentDate() + " " + newCurrentTime());

            // Create object to add multimedia type content
            BodyPart messageBodyPart1 = new MimeBodyPart();

            // Set the body of email
            messageBodyPart1.setText("1. Download report.zip");

            BodyPart messageBodyPart2 = new MimeBodyPart();
            messageBodyPart2.setText("2. Unzip report.zip");

            BodyPart messageBodyPart3 = new MimeBodyPart();
            messageBodyPart3.setText("3. Go to report folder");

            BodyPart messageBodyPart4 = new MimeBodyPart();
            messageBodyPart4.setText("4. Run index.html");

            // Create another object to add another content
            MimeBodyPart messageBodyPart5 = new MimeBodyPart();

            // Mention the file which you want to send
            String filename = PathToReportZipFile.reportZipFile;

            // Create data source and pass the filename
            DataSource source = new FileDataSource(filename);

            // set the handler
            messageBodyPart5.setDataHandler(new DataHandler(source));

            // set the file
            messageBodyPart5.setFileName(DataConverter.parseFileName(filename));

            // Create object of MimeMultipart class
            Multipart multipart = new MimeMultipart();

            // add body part 1
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);
            multipart.addBodyPart(messageBodyPart3);
            multipart.addBodyPart(messageBodyPart4);

            // add body part 2
            multipart.addBodyPart(messageBodyPart5);

            // set the content
            message.setContent(multipart);

            // finally send the email
            Transport.send(message);
            System.out.println("===================Email Sent=====================");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
