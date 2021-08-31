package com.allen.dayup.java8.regex;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Template {

	final String template;
	final Pattern pattern = Pattern.compile("\\$\\{(\\w+)\\}");

	public Template(String template) {
		this.template = template;
	}

	public String render(Map<String, Object> data) {
		Matcher m = pattern.matcher(template);
		StringBuffer sb = new StringBuffer();

		while (m.find()) {
			String key = template.substring(m.start()+2, m.end()-1);
			m.appendReplacement(sb, data.getOrDefault(key, "").toString());
		}

		m.appendTail(sb);

		return sb.toString();
	}



	public static void main(String[] args) {
		String str = "Hello, ${name}! You are learning ${lang}!";
		Template template = new Template(str);

		Map<String,Object> data = new HashMap<>();
		data.put("name", "allen");
		data.put("lang", "English");
		String result =  template.render(data);

		System.out.println(result);
	}
}