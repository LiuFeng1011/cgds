package com.liufeng.http.codec;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

import com.liufeng.http.response.HttpResponseMessage;

public class HttpServerProtocolCodecFactory extends DemuxingProtocolCodecFactory {
	public HttpServerProtocolCodecFactory() {
		super.addMessageDecoder(HttpRequestDecoder.class);
		super.addMessageEncoder(HttpResponseMessage.class,HttpResponseEncoder.class);
	}
}  
