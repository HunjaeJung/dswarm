package de.avgl.dmp.converter.mf.stream.source;

import org.culturegraph.mf.exceptions.MetafactureException;
import org.culturegraph.mf.framework.ObjectReceiver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;


public class CSVJSONWriter implements ObjectReceiver<JsonNode> {

	private JsonNode json;
	private boolean closed;

	public CSVJSONWriter() {
		
	}

	@Override
	public void process(final JsonNode json) {
		
		assert !closed;
		
		// TODO write incoming json
		
		if(this.json != null && this.json.size() > 0) {
			
			// add only incoming data JSON object
			
			final JsonNode dataJSON = this.json.get("data");
			
			if(dataJSON == null) {
				
				throw new MetafactureException("data JSON shouldn't be null");
			}
			
			if(dataJSON.isArray() == false) {
				
				throw new MetafactureException("data JSON should be an array");
			}
			
			final ArrayNode dataJSONArray = (ArrayNode) dataJSON;
			
			dataJSONArray.add(json);
		} else {
			
			// add complete input to init complete JSON object
			
			this.json = json;
		}
	}

	@Override
	public void resetStream() {
		 
		json = null;
	}

	@Override
	public void closeStream() {
		
		closed = true;
	}

	@Override
	public String toString() {
		
		if(json != null) {
			
			return json.toString();
		}
		
		 return null;
	}
}