package com.liufeng.http;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liufeng.cgds.GameConfig;
import com.liufeng.http.codec.HttpRequestDecoder;
import com.liufeng.http.codec.HttpResponseEncoder;
import com.liufeng.http.codec.HttpServerProtocolCodecFactory;
import com.liufeng.http.handler.HttpServerHandler;


public class HttpServer {
	private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

	
	private static HttpServer instance = new HttpServer();
	
	public static HttpServer getInstance(){
		return instance;
	}


	private SocketAcceptor acceptor;
	
	private boolean isRunning;

	private String encoding;

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
		HttpRequestDecoder.defaultEncoding = encoding;
		HttpResponseEncoder.defaultEncoding = encoding;
	}

	/**
	 * 启动HTTP服务端箭筒HTTP请求
	 * 
	 * @param port要监听的端口号
	 * @throws IOException
	 */
	public void run() throws IOException {
		synchronized (this) {
			if (isRunning) {
				logger.info("HttpServer is already running.");
				return;
			}
			
			logger.info("bind port:"+GameConfig.http_port);
			setEncoding("UTF-8");
			acceptor = new NioSocketAcceptor();
			acceptor.setReuseAddress(true);
			
			acceptor.getFilterChain().addLast("protocolFilter",new ProtocolCodecFilter(new HttpServerProtocolCodecFactory()));
			acceptor.setHandler(new HttpServerHandler());
			acceptor.bind(new InetSocketAddress(GameConfig.http_port));
			isRunning = true;
			logger.info("HttpServer now listening on port " + GameConfig.http_port);
			
		}
		
	}

	/**
	 * 停止监听HTTP服务
	 */
	public void stop() {
		synchronized (this) {
			if (!isRunning) {
				System.out.print("HttpServer is already stoped.");
				return;
			}
			isRunning = false;
			try {
				acceptor.unbind();
				acceptor.dispose();
				System.out.print("HttpServer is stoped.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws IOException {
		HttpServer.getInstance().run();
	}
}
