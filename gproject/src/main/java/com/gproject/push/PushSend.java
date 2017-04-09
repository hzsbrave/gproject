package com.gproject.push;///**
// * 
// */
//package com.gm.dropship.plugin.push;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.gm.dropship.common.CustomPropertyPlaceholder;
//import com.gm.dropship.common.util.JsonUtils;
//import com.gm.dropship.plugin.MessageResponse;
//
///**
// * Message Send
// * 
// * @author Administrator
// *
// */
//public class PushSend {
//
//	private static Logger log = LoggerFactory.getLogger(PushSend.class);
//	/**
//	 * 发推送消息
//	 * @param response
//	 */
//	public static void getPushMessage(MessageResponse response) {
//		PushAPI push = new PushAPI();
//		if(response != null){
//			try {
//				String key = CustomPropertyPlaceholder.getStrPro("rongyunkey");
//				String secret = CustomPropertyPlaceholder.getStrPro("rongyunsecret");
//				log.info("rongcoud send message text =="+response.getMessage()+",The sender="+JsonUtils.obj2json(response.getList())+",key="+key+",secret="+secret+",SystemId="+response.getSystemId());
//				String message = push.publishSystemMessage(key,
//						secret, response.getSystemId(), response.getList(), response.getMessage(), response.getPushContent(), response.getPushData());
//				log.info("rongcloud send message:"+message);
//			} catch (Exception e) {
//				log.error("rongcloud send message error", e);
//			}
//		}
//	}
//	
//	/**
//	 * 获取token 
//	 * @param id
//	 * @param name
//	 * @param portraitUri
//	 */
//	public static String getToken(String id,String  name,String portraitUri) {
//		PushAPI push = new PushAPI();
//		if(id != null){
//			try {
//				String key = CustomPropertyPlaceholder.getStrPro("rongyunkey");
//				String secret = CustomPropertyPlaceholder.getStrPro("rongyunsecret");
//				String token = push.getTokenByUserid(key, secret, id, name, portraitUri);
//				log.info("rongcloud 获取token:"+token);
//				return token;
//			} catch (Exception e) {
//				log.error("rongcloud 获取token", e);
//			}
//		}
//		return null;
//	}
//}
