package com.artificialintelligence.dao.machinelearning.util;

public class BaseConstants {

	public static enum commentType {
		
		Article(1), Material(2), System(3);

		// define private variable
		private int nCode;

		private commentType(int _nCode) {
			this.nCode = _nCode;
		}

		public int getnCode() {
			return nCode;
		}

		public void setnCode(int nCode) {
			this.nCode = nCode;
		}
		
		
	}
	
	public static final String AI_PROP_FILE_NOLANG = "resources.messages.ai";

}
