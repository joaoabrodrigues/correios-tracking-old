package com.joaoabrodrigues.integration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class CorreiosIntegration {

    private static final String XML_ENVELOPE = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:res=\"http://resource.webservice.correios.com.br/\">"
            + "   <soapenv:Header/>" + "   <soapenv:Body>" + "      <res:buscaEventos>" + "         <usuario>ECT</usuario>" + "         <senha>SRO</senha>"
            + "         <tipo>L</tipo>" + "         <resultado>T</resultado>" + "         <lingua>101</lingua>" + "         <objetos> %s </objetos>"
            + "      </res:buscaEventos>" + "   </soapenv:Body>" + "</soapenv:Envelope>";

	public static String trackObject(String codigo) throws IOException, SOAPException {
		String envelope = String.format(XML_ENVELOPE, codigo);
        
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
        
        String url = "http://webservice.correios.com.br:80/service/rastro";
        MimeHeaders headers = new MimeHeaders();
        headers.addHeader("Content-Type", "text/xml");

        MessageFactory messageFactory = MessageFactory.newInstance();

        SOAPMessage msg = messageFactory.createMessage(headers, (new ByteArrayInputStream(envelope.getBytes())));

        SOAPMessage soapResponse = soapConnection.call(msg, url);
        Document responseXML = soapResponse.getSOAPBody().getOwnerDocument();
        return parseXmlToString(responseXML, 4);
    }

    public static String parseXmlToString(Document xml, int espacosIdentacao) {
        try {
            // set up a transformer
            TransformerFactory transfac = TransformerFactory.newInstance();
            transfac.setAttribute("indent-number", new Integer(espacosIdentacao));
            Transformer trans = transfac.newTransformer();
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            // create string from xml tree
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(xml);
            trans.transform(source, result);
            String xmlString = sw.toString();
            return xmlString.substring(xmlString.indexOf("<objeto>"), xmlString.indexOf("</objeto>") + 10);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

}
