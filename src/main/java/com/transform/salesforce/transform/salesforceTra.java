package com.transform.salesforce.transform;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.mule.util.CaseInsensitiveHashMap;

import com.sforce.soap.partner.Upsert_element;
import com.transform.salesforce.pojo.salesforceTransform;


public class salesforceTra extends AbstractMessageTransformer{

	

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {

		/*LinkedList<HashMap<String,String>> payloadResponse = (LinkedList<HashMap<String, String>>) message.getPayload();
		LinkedList<salesforceTransform> salesforceRequest = new LinkedList<>();
		
		final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
		
		for(HashMap<String,String> m: payloadResponse){
			final salesforceTransform pojo = mapper.convertValue(m, salesforceTransform.class);
			salesforceRequest.add(pojo);
		}*/
		
		
		
		LinkedList<CaseInsensitiveHashMap> payloadResponse = (LinkedList<CaseInsensitiveHashMap>) message.getPayload();
		ArrayList<Object> salesforceRequest = new ArrayList<>();
		
		for(CaseInsensitiveHashMap m: payloadResponse){
			LinkedHashMap<String, String> object = new LinkedHashMap<>();
			
			object.put("id", (String) m.get("opportunityId"));
			object.put("orderNumber__c", String.valueOf(m.get("orderId")));
			salesforceRequest.add(object);
		}
		
		
		message.setPayload(salesforceRequest);
		
		return message;
	}

	

}
