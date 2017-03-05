package com.example.mpape.Ser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import android.text.TextUtils;
public class JsonParser {
	public static String parseIatResult(String json) {
		if(TextUtils.isEmpty(json))
			return "";
		StringBuffer ret = new StringBuffer();
		try {
			JSONTokener tokener = new JSONTokener(json);
			JSONObject joResult = new JSONObject(tokener);

			JSONArray words = joResult.getJSONArray("ws");
			for (int i = 0; i < words.length(); i++) {
				JSONArray items = words.getJSONObject(i).getJSONArray("cw");
				JSONObject obj = items.getJSONObject(0);
				ret.append(obj.getString("w"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret.toString();
	}
	public static String parseGrammarResult(String json) {
		StringBuffer ret = new StringBuffer();
		try {
			JSONTokener tokener = new JSONTokener(json);
			JSONObject joResult = new JSONObject(tokener);

			JSONArray words = joResult.getJSONArray("ws");
			for (int i = 0; i < words.length(); i++) {
				JSONArray items = words.getJSONObject(i).getJSONArray("cw");
				for(int j = 0; j < items.length(); j++)
				{
					JSONObject obj = items.getJSONObject(j);
					if(obj.getString("w").contains("nomatch"))
					{
						ret.append("No matches!");
						return ret.toString();
					}
					ret.append("[Result]" + obj.getString("w"));
					ret.append("[Confidentiality]" + obj.getInt("sc"));
					ret.append("\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret.append("No matches!");
		}
		return ret.toString();
	}
	public static String parseUnderstandResult(String json) {
		StringBuffer ret = new StringBuffer();
		try {
			JSONTokener tokener = new JSONTokener(json);
			JSONObject joResult = new JSONObject(tokener);
			ret.append("[Reply Code]" + joResult.getString("rc") + "\n");
			ret.append("[Translating Result]" + joResult.getString("text") + "\n");
			ret.append("[Service]" + joResult.getString("service") + "\n");
			ret.append("[Operation]" + joResult.getString("operation") + "\n");
			ret.append("[Complete Result]" + json);
		} catch (Exception e) {
			e.printStackTrace();
			ret.append("No matches!");
		}
		return ret.toString();
	}
}