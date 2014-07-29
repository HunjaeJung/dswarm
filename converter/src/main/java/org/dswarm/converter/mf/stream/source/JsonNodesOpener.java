package org.dswarm.converter.mf.stream.source;

import com.fasterxml.jackson.databind.JsonNode;
import org.culturegraph.mf.framework.DefaultObjectPipe;
import org.culturegraph.mf.framework.ObjectReceiver;

/**
 * @author phorn
 */
public class JsonNodesOpener extends DefaultObjectPipe<String, ObjectReceiver<JsonNode>> {

	public JsonNodesOpener() {
	}

	@Override
	public void process(final String obj) {
		super.process(obj); // To change body of overridden methods use File | Settings | File Templates.
	}
}