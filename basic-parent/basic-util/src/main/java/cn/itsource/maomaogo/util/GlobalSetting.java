package cn.itsource.maomaogo.util;

import java.util.Properties;

/**
 * web层全局常量配置帮助类
 * @author nixianhua
 *
 */
public class GlobalSetting {
	private static final String FILE_NAME = "/global.properties";
	private GlobalSetting(){}
	private static Properties p;
	static{
		p = PropertiesHelper.load(FILE_NAME);
	}
	public static String get(String key){
		return null==p?"":p.getProperty(key);
	}
	
}
