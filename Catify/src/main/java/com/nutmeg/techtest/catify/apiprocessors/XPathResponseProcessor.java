package com.nutmeg.techtest.catify.apiprocessors;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public abstract class XPathResponseProcessor implements ResponseProcessor {

	@Override
	public void processResponse(InputStream inputStream) throws Exception {
		Document document = DocumentBuilderFactory
				.newInstance()
				.newDocumentBuilder()
				.parse(inputStream);
		
		XPath xPath =  XPathFactory.newInstance().newXPath();
		NodeList nodeList = (NodeList) xPath.compile(getXPathExpression()).evaluate(document, XPathConstants.NODESET);
		processNodeList(nodeList);
	}
	
	protected abstract String getXPathExpression();
	protected abstract void processNodeList(NodeList nodes);
}
