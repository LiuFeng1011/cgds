package com.liufeng.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import sun.misc.BASE64Encoder;

public class Tools {

	static String[] chars = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
			"1","2","3","4","5","6","7","8","9","0"};
	/**
	 * 
	 * 产生随机字符串，不长于32位
	 * @param int $length
	 * @return 产生的随机字符串
	 */
	public static String getNonceStr(int length) 
	{  
		String ret ="";
		for ( int i = 0; i < length; i++ )  {  
			ret += chars[(int) (Math.random() * chars.length)];
		} 
		return ret;
	}
	
	public static String getMd5(String str){
		try {
	        // 生成一个MD5加密计算摘要
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        // 计算md5函数
	        md.update(str.getBytes());
	        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
	        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
	        return (new BigInteger(1, md.digest())).toString(16);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return "";
	}
	
	// Gzip压缩
	public static byte[] compressGzip(byte[] data) throws IOException {
		byte[] tArray = null;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(data);
		gzip.close();
		
		tArray = out.toByteArray();
		out.close();
		return tArray;
		//return out.toString("ISO-8859-1");
	}
	
	//字符串用Gzip压缩然后转base64
	public static String compress(String str){
		if (str == null || str.length() == 0) {
			return str;
		}

		byte[] tArray = null;
		
		try{
			tArray = compressGzip(str.getBytes());
		} catch (Exception e) {
			
		}

		BASE64Encoder tBase64Encoder = new BASE64Encoder();
		
		return tBase64Encoder.encode(tArray);
		
	}
	
	// 解压缩   
	public static String uncompress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n;
		while ((n = gunzip.read(buffer))>= 0) {
			out.write(buffer, 0, n);
		}
		
		// toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)   
		return out.toString();
	}
	
}
