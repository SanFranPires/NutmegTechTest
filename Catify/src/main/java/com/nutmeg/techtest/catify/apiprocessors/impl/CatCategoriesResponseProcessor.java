package com.nutmeg.techtest.catify.apiprocessors.impl;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nutmeg.techtest.catify.apiprocessors.XPathResponseProcessor;

public class CatCategoriesResponseProcessor extends XPathResponseProcessor {
	
	private List<String> categories;

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	@Override
	protected String getXPathExpression() {
		return "/response/data/categories/category/name";
	}

	@Override
	protected void processNodeList(NodeList nodes) {
		categories = new ArrayList<>(nodes.getLength());
		for (int i = 0; i != nodes.getLength(); ++i) {
			Node currentNode = nodes.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elementNode = (Element)currentNode;
				categories.add(elementNode.getTextContent());
			}
		}
	}

}
