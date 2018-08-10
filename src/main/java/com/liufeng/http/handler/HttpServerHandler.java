package com.liufeng.http.handler;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liufeng.http.handler.domain.ReturnEntity;
import com.liufeng.http.handler.impl.AppCreate;
import com.liufeng.http.handler.impl.KVDataHttpHandler;
import com.liufeng.http.request.HttpRequestMessage;
import com.liufeng.http.response.HttpResponseMessage;
import com.liufeng.utils.JsonUtil;

public class HttpServerHandler extends IoHandlerAdapter  {

	static final Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);

	@Override  
    public void sessionCreated(IoSession session) {
        // Empty handler
        SocketSessionConfig cfg = (SocketSessionConfig) session.getConfig(); 
        cfg.setIdleTime(IdleStatus.BOTH_IDLE, 10);  
        cfg.setKeepAlive(true);   
        cfg.setSoLinger(0);
    }

    @Override  
    public void sessionOpened(IoSession session) { 

    }  
  
    @SuppressWarnings("unchecked")
	@Override  
    public void messageReceived(IoSession session, Object message) { 
        HttpRequestMessage request = (HttpRequestMessage) message;
//        String a = request.getPostValue();
        String logicName = request.getParameter("logic");
        Class<? extends HttpHandler> clazz = null;
        HttpResponseMessage response = new HttpResponseMessage();
		try {
			
			if("".equals(logicName)){
				ReturnEntity ret = ReturnEntity.createFail("logicName is null!!!!");
				response.appendBody(JsonUtil.ObjectToJsonString(ret));
			}else{
				clazz = (Class<? extends HttpHandler>)Class.forName("com.liufeng.http.handler.impl." + logicName);
				HttpHandler handler = clazz.newInstance();

				if(handler != null )handler.handle(request,response); 
			}
			
		}catch(Exception e){
			System.out.println("HttpServerHandler:http handler error : " + logicName );
			e.printStackTrace();
		}
		response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
//        response.appendBody("uid="+uid+",name="+name);
		session.write(response).addListener(IoFutureListener.CLOSE);
    }  
  
    @Override  
    public void sessionIdle(IoSession session, IdleStatus status) { 
        session.close(true);  
    }  
  
    @Override  
    public void exceptionCaught(IoSession session, Throwable cause) {  
        session.close(true);  
    }  

    @Override
    public void sessionClosed(IoSession session)  {
    }
}
