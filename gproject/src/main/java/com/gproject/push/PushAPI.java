package com.gproject.push;


import com.gproject.push.messages.TxtMessage;
import com.gproject.push.models.CodeSuccessReslut;
import com.gproject.push.models.TokenReslut;
import org.springframework.stereotype.Component;

/**
 * Created by LRJ on 19/7/16.
 * api返回格式为{"code":"200","result":{}}
 * 所有api调用返回正常的话 code为 200
 */
@Component
public class PushAPI {
	private static final String appKey = "m7ua80gbmyjem";//融云 appkey（测试环境）
	private static final String appSecret = "k4xHQQBkuEEel";//融云 key的secret
//	200	成功	成功
//	400	错误请求	该请求是无效的，详细的错误信息会说明原因
//	401	验证错误	验证失败，详细的错误信息会说明原因
//	403	被拒绝	被拒绝调用，详细的错误信息会说明原因
//	404	无法找到	资源不存在
//	405	群上限	群容量超出上限
//	429	过多的请求	超出了调用频率限制，详细的错误信息会说明原因
//	500	内部服务错误	服务器内部出错了，请联系我们尽快解决问题
//	504	内部服务响应超时	服务器在运行，本次请求响应超时，请稍后重试
//	1000	服务内部错误	服务器端内部逻辑错误,请稍后重试	500
//	1001	App Secret 错误	App Key 与 App Secret 不匹配	401
//	1002	参数错误	参数错误，详细的描述信息会说明	400
//	1003	无 POST 数据	没有 POST 任何数据	400
//	1004	验证签名错误	验证签名错误	401
//	1005	参数长度超限	参数长度超限，详细的描述信息会说明	400
//	1006	App 被锁定或删除	App 被锁定或删除	401
//	1007	被限制调用	该方法被限制调用，详细的描述信息会说明	401
//	1008	调用频率超限	调用频率超限，详细的描述信息会说明，广播消息未开通时也会返回此状态码。	429
//	1009	服务未开通	未开通该服务，请到开发者管理后台开通。	430
//	1050	内部服务超时	内部服务响应超时	504
//	2007	测试用户数量超限	测试用户数量超限
	
//	30001	进行通信操作过程中，当前 Socket 失效。
//	30002	Socket 连接不可用。应该是您当前网络连接不可用。
//	30003	进行各种信令的通信操作过程中，信令 ACK 返回超时。
//	30007	通过 HTTP 获取连接网络必须的配置数据时，服务器返回的不是 200 OK，而是 HTTP 的其它错误码。
//	30008	通过 HTTP 获取配置数据时，成功获得数据，但得到的内容体部分是空。可能是你所在的网络被劫持，HTTP 被修改。
//	30011	Socket 连接被断开，主要有两种情况，一是用户主动调用 disconnect 之后，Socket 被服务器断开；二是中间路由原因导致 Socket 断开。
//	30014	消息发送失败。
//	31000	做 connect 连接时，收到的 ACK 超时。
//	31001	协议参数错误。
//	31002	参数错误，可能是您使用的 AppKey 错误。
//	31003	服务器不可用。融云的服务器实际上不会发生此错误。
//	31004	您需要更换 Token，Token 即为您连接融云服务器的账号和密码。出于一些客户的安全需求，我们容许您在后台设置 Token 过期时间，当 Token 过期后，您的用户必须通过您的 App 服务器重新获取一次 Token。因此请注意在 connect 操作后的回调内换取一次 Token。iOS 为 tokenIncorrect block，Android 为 onTokenIncorrect()。
//	31007	您提交的包名跟后台注册包名不一致。
//	31008	APP 被屏蔽、删除或不存在。
//	31009	当前用户被屏蔽。
//	31010	用户互踢，用户已在其他设备登录。
//	33001	未首先调用 init 函数。注意您必须先调用 init 进行初始化，才能通过connect 进行连接。
//	33002	数据库错误，可能是数据库不存在或者损坏。
//	33003	传入参数无效。
//	33005	重新连接成功。
//	33006	连接中，再调用 connect 被拒绝。 
//  ERRORCODE_UNKNOWN = -1,/** 未知错误 */
//  ERRORCODE_TIMEOUT = 5004, /** 超时错误 */
//  REJECTED_BY_BLACKLIST = 405,被对方加入黑名单时发送消息的状态
//  NOT_IN_DISCUSSION = 21406,不在讨论组中。
//  NOT_IN_GROUP = 22406,不在群组中。
//  FORBIDDEN_IN_GROUP = 22408,在群组中被禁言。
//  NOT_IN_CHATROOM = 23406,不在聊天室中。
//  MSG_ROAMING_SERVICE_UNAVAILABLE = 33007,从服务器获取历史消息服务未开通
	
	/**
	 * 获取融云token
	 * @param (*必传)userId 用户id
	 * @param (*必传)userName 用户名字(用来在 Push 推送时显示用户的名称。)
	 * @param (*必传)portraitUri 用户头像地址(http 用来在 Push 推送时显示用户的头像)
	 * 返回正常示例
	 * {"code":"200","result":{"code":200,"userId":"402880ef4a","token":"nOqDUxB7g3pzUAfPFhLjCqz6IhHARk64ESDEb5WkdVQERl8bgAqUNiQS+PQsZNKbHpAbr+auhArPzMAy7nGOP0dtGaseEcNj"}}
	 * @return
	 * @throws Exception
	 */

	public String getTokenByUserid(String userId, String userName, String portraitUri) throws Exception{

		RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
		TokenReslut userGetTokenResult = rongCloud.user.getToken(userId, userName, portraitUri);
		System.out.println("getToken:  " + userGetTokenResult.toString());
		return userGetTokenResult.toString();
	}
	

	/**
	 * 更新融云用户
	 * @param (*必传)userId 用户id
	 * @param (*必传)userName 用户名字(用来在 Push 推送时显示用户的名称。)
	 * @param (*必传)portraitUri 用户头像地址(http 用来在 Push 推送时显示用户的头像)
	 * @return
	 * @throws Exception
	 */
	public String refreshUser(String userId, String userName, String portraitUri) throws Exception{

		RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
		CodeSuccessReslut userRefreshResult = rongCloud.user.refresh(userId, userName, portraitUri);
 		System.out.println("refresh:  " + userRefreshResult.toString());
 		return  userRefreshResult.toString();
	}
	
	/**
	 * 发送系统消息
	 * @param (*必传)systemId 系统消息id
	 * @param (*必传)toIds 要发送到的人员id(发送单个就传一个)
	 * @param (*必传)txtMessage 要发送的推送信息(文本。不能大于128kb)
	 * @param parameter 传到前台的参数
	 * @param pushContent 状态栏看到的内容
	 * @param pushData 状态栏看到的
	 * @throws Exception
	 */
	public String publishSystemMessage(String systemId, String []toIds , String txtMessage, String parameter , String pushContent, String pushData) throws Exception{
		// 发送系统消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM。每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。） 
		RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
		TxtMessage messagePublishSystemTxtMessage = new TxtMessage(txtMessage,parameter);
		CodeSuccessReslut messagePublishSystemResult = rongCloud.message.PublishSystem(systemId, toIds, messagePublishSystemTxtMessage, pushContent, "", 0, 0);
		System.out.println("PublishSystem:  " + messagePublishSystemResult.toString());		
		return messagePublishSystemResult.toString();
	}
	

	
	

}
