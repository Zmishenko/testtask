package testtask.controller;

import org.json.JSONObject;

import testtask.model.JsonLOV;
import testtask.model.JsonValues;
import testtask.reqests.JsonRequest;

public class RequestController {

	public static JsonLOV getLOV() throws Exception {
		JsonRequest req = new JsonRequest("https://70eb249f-17b4-4407-b728-98b2348454fd.mock.pstmn.io/typeLOV");
		JSONObject jsonTypeLOV = req.getJsonResponse();
		JsonLOV lov = new JsonLOV(jsonTypeLOV);

		return lov;
	}

	public static JsonValues getModel() throws Exception {
		JsonRequest req = new JsonRequest("https://70eb249f-17b4-4407-b728-98b2348454fd.mock.pstmn.io/model");
		JSONObject jsonmodel = req.getJsonResponse();
		JsonValues jsonValues = new JsonValues(jsonmodel);

		return jsonValues;
	}

	public static JsonValues getMemoryType() throws Exception {
		JsonRequest req = new JsonRequest("https://70eb249f-17b4-4407-b728-98b2348454fd.mock.pstmn.io/memoryType");
		JSONObject jsonmemoryType = req.getJsonResponse();
		JsonValues jsonValues = new JsonValues(jsonmemoryType);

		return jsonValues;
	}

}
