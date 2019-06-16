package com.exec.api.execapi.core;

import java.io.IOException;
import java.util.UUID;

public class GuidGenerator {

	public static String generate() throws IOException {
		String response = UUID.randomUUID().toString().replace("-", "");
		return response;
	}

}
