package in.nic.igrs.data.util;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParamBuilderWithNameFun {

	LinkedHashMap<String, Object> maplist = new LinkedHashMap<String, Object>();

	String functionNameB = null;

	public ParamBuilderWithNameFun(String fun) {
		this.functionNameB = fun;

	}

	public ParamBuilderWithNameFun addfun(String OnlyfunctionName) {
		this.functionNameB = OnlyfunctionName;
		return this;
	}

	public ParamBuilderWithNameFun addParam(String prm, Object obj) {

		try {
			if (this.maplist.isEmpty()) {

				this.maplist.put(prm, obj);
				return this;
			} else {

				this.maplist.put(prm, obj);
				return this;
			}

		} catch (Exception e) {
			System.out.println(" Excetion in ParamBuilder.class in wrapping param " + e);
		}

		return this;

	}

	public Map<String, Object> getParamList() {

		log.info(" ====== mapListNew ======" + this.maplist);
		return this.maplist;
	}

	public String getFun() {
		StringBuilder str = new StringBuilder(this.functionNameB);
		str.append("(");
		int i = 0;

		for (Map.Entry<String, Object> entry : this.maplist.entrySet()) {
			i++;
			log.info(" ====== mapListNew item ====== " + i + "==" + entry.getKey());

			if (i != this.maplist.size()) {
				str.append(":" + entry.getKey() + ",");
			} else {
				str.append(":" + entry.getKey());
			}

		}
		str.append(")");
		return str.toString();

	}

	public String getPrintFun() {
		StringBuilder str = new StringBuilder(this.functionNameB);
		str.append("(");
		int i = 0;

		for (Map.Entry<String, Object> entry : this.maplist.entrySet()) {
			i++;

			if (i != this.maplist.size()) {
				str.append(entry.getValue() + ",");
			} else {
				str.append(entry.getValue());
			}

		}
		str.append(")");
		log.info(" ====== called Fun ======" + str.toString());
		return str.toString();

	}

}
