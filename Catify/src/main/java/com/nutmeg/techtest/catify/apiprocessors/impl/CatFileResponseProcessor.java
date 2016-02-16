package com.nutmeg.techtest.catify.apiprocessors.impl;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nutmeg.techtest.catify.apiprocessors.XPathResponseProcessor;

public class CatFileResponseProcessor extends XPathResponseProcessor {
	
	private String imageUrl;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	protected String getXPathExpression() {
		return "/response/data/images/image[1]/url";
	}

	@Override
	protected void processNodeList(NodeList nodes) {
		if (nodes.getLength() != 0) {
			Node firstNode = nodes.item(0);
			if (firstNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elementNode = (Element)firstNode;
				imageUrl = elementNode.getTextContent();
			}
		}
	}

}
