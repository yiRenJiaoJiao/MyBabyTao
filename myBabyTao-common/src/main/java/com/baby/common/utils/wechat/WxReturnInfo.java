package com.baby.common.utils.wechat;


/**
 * Created by Administrator on 2017-11-24.
 */
public class WxReturnInfo {

    /**
     * 根据微信返回值得到对应的描述信息
     * @param returnCode 微信返回的code值
     * @return
     */
    public static String GetRetureInfo(String returnCode) {
        if (returnCode.equals("-1")) return "系统繁忙，此时请开发者稍候再试";
        else if (returnCode.equals("0")) return "请求成功";
        else if (returnCode.equals("40001")) return "获取access_token时AppSecret错误，或者access_token无效。请开发者认真比对AppSecret的正确性，或查看是否正在为恰当的公众号调用接口";
        else if (returnCode.equals("40002")) return "不合法的凭证类型";
        else if (returnCode.equals("40003")) return "不合法的OpenID，请开发者确认OpenID（该用户）是否已关注公众号，或是否是其他公众号的OpenID";
        else if (returnCode.equals("40004")) return "不合法的媒体文件类型";
        else if (returnCode.equals("40005")) return "不合法的文件类型";
        else if (returnCode.equals("40006")) return "不合法的文件大小";
        else if (returnCode.equals("40007")) return "不合法的媒体文件id";
        else if (returnCode.equals("40008")) return "不合法的消息类型";
        else if (returnCode.equals("40009")) return "不合法的图片文件大小";
        else if (returnCode.equals("40010")) return "不合法的语音文件大小";
        else if (returnCode.equals("40011")) return "不合法的视频文件大小";
        else if (returnCode.equals("40012")) return "不合法的缩略图文件大小";
        else if (returnCode.equals("40013")) return "不合法的AppID，请开发者检查AppID的正确性，避免异常字符，注意大小写";
        else if (returnCode.equals("40014")) return "不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口";
        else if (returnCode.equals("40015")) return "不合法的菜单类型";
        else if (returnCode.equals("40016")) return "不合法的按钮个数";
        else if (returnCode.equals("40017")) return "不合法的按钮个数";
        else if (returnCode.equals("40018")) return "不合法的按钮名字长度";
        else if (returnCode.equals("40019")) return "不合法的按钮KEY长度";
        else if (returnCode.equals("40020")) return "不合法的按钮URL长度";
        else if (returnCode.equals("40021")) return "不合法的菜单版本号";
        else if (returnCode.equals("40022")) return "不合法的子菜单级数";
        else if (returnCode.equals("40023")) return "不合法的子菜单按钮个数";
        else if (returnCode.equals("40024")) return "不合法的子菜单按钮类型";
        else if (returnCode.equals("40025")) return "不合法的子菜单按钮名字长度";
        else if (returnCode.equals("40026")) return "不合法的子菜单按钮KEY长度";
        else if (returnCode.equals("40027")) return "不合法的子菜单按钮URL长度";
        else if (returnCode.equals("40028")) return "不合法的自定义菜单使用用户";
        else if (returnCode.equals("40029")) return "不合法的oauth_code";
        else if (returnCode.equals("40030")) return "不合法的refresh_token";
        else if (returnCode.equals("40031")) return "不合法的openid列表";
        else if (returnCode.equals("40032")) return "不合法的openid列表长度";
        else if (returnCode.equals("40033")) return "不合法的请求字符，不能包含\\uxxxx格式的字符";
        else if (returnCode.equals("40035")) return "不合法的参数";
        else if (returnCode.equals("40038")) return "不合法的请求格式";
        else if (returnCode.equals("40039")) return "不合法的URL长度";
        else if (returnCode.equals("40050")) return "不合法的分组id";
        else if (returnCode.equals("40051")) return "分组名字不合法";
        else if (returnCode.equals("40060")) return "删除单篇图文时，指定的 article_idx 不合法";
        else if (returnCode.equals("40117")) return "分组名字不合法";
        else if (returnCode.equals("40118")) return "media_id大小不合法";
        else if (returnCode.equals("40119")) return "button类型错误";
        else if (returnCode.equals("40120")) return "button类型错误";
        else if (returnCode.equals("40121")) return "不合法的media_id类型";
        else if (returnCode.equals("40132")) return "微信号不合法";
        else if (returnCode.equals("40137")) return "不支持的图片格式";
        else if (returnCode.equals("40155")) return "请勿添加其他公众号的主页链接";
        else if (returnCode.equals("41001")) return "缺少access_token参数";
        else if (returnCode.equals("41002")) return "缺少appid参数";
        else if (returnCode.equals("41003")) return "缺少refresh_token参数";
        else if (returnCode.equals("41004")) return "缺少secret参数";
        else if (returnCode.equals("41005")) return "缺少多媒体文件数据";
        else if (returnCode.equals("41006")) return "缺少media_id参数";
        else if (returnCode.equals("41007")) return "缺少子菜单数据";
        else if (returnCode.equals("41008")) return "缺少oauth code";
        else if (returnCode.equals("41009")) return "缺少openid";
        else if (returnCode.equals("42001")) return "access_token超时，请检查access_token的有效期，请参考基础支持-获取access_token中，对access_token的详细机制说明";
        else if (returnCode.equals("42002")) return "refresh_token超时";
        else if (returnCode.equals("42003")) return "oauth_code超时";
        else if (returnCode.equals("42007")) return "用户修改微信密码，accesstoken和refreshtoken失效，需要重新授权";
        else if (returnCode.equals("43001")) return "需要GET请求";
        else if (returnCode.equals("43002")) return "需要POST请求";
        else if (returnCode.equals("43003")) return "需要HTTPS请求";
        else if (returnCode.equals("43004")) return "需要接收者关注";
        else if (returnCode.equals("43005")) return "需要好友关系";
        else if (returnCode.equals("43019")) return "需要将接收者从黑名单中移除";
        else if (returnCode.equals("44001")) return "多媒体文件为空";
        else if (returnCode.equals("44002")) return "POST的数据包为空";
        else if (returnCode.equals("44003")) return "图文消息内容为空";
        else if (returnCode.equals("44004")) return "文本消息内容为空";
        else if (returnCode.equals("45001")) return "多媒体文件大小超过限制";
        else if (returnCode.equals("45002")) return "消息内容超过限制";
        else if (returnCode.equals("45003")) return "标题字段超过限制";
        else if (returnCode.equals("45004")) return "描述字段超过限制";
        else if (returnCode.equals("45005")) return "链接字段超过限制";
        else if (returnCode.equals("45006")) return "图片链接字段超过限制";
        else if (returnCode.equals("45007")) return "语音播放时间超过限制";
        else if (returnCode.equals("45008")) return "图文消息超过限制";
        else if (returnCode.equals("45009")) return "接口调用超过限制";
        else if (returnCode.equals("45010")) return "创建菜单个数超过限制";
        else if (returnCode.equals("45011")) return "API调用太频繁，请稍候再试";
        else if (returnCode.equals("45015")) return "回复时间超过限制";
        else if (returnCode.equals("45016")) return "系统分组，不允许修改";
        else if (returnCode.equals("45017")) return "分组名字过长";
        else if (returnCode.equals("45018")) return "分组数量超过上限";
        else if (returnCode.equals("45047")) return "客服接口下行条数超过上限";
        else if (returnCode.equals("46001")) return "不存在媒体数据";
        else if (returnCode.equals("46002")) return "不存在的菜单版本";
        else if (returnCode.equals("46003")) return "不存在的菜单数据";
        else if (returnCode.equals("46004")) return "不存在的用户";
        else if (returnCode.equals("47001")) return "解析JSON/XML内容错误";
        else if (returnCode.equals("48001")) return "api功能未授权，请确认公众号已获得该接口，可以在公众平台官网-开发者中心页中查看接口权限";
        else if (returnCode.equals("48002")) return "粉丝拒收消息（粉丝在公众号选项中，关闭了“接收消息”）";
        else if (returnCode.equals("48004")) return "api接口被封禁，请登录mp.weixin.qq.com查看详情";
        else if (returnCode.equals("48005")) return "api禁止删除被自动回复和自定义菜单引用的素材";
        else if (returnCode.equals("48006")) return "api禁止清零调用次数，因为清零次数达到上限";
        else if (returnCode.equals("50001")) return "用户未授权该api";
        else if (returnCode.equals("50002")) return "用户受限，可能是违规后接口被封禁";
        else if (returnCode.equals("61451")) return "参数错误(invalid parameter)";
        else if (returnCode.equals("61452")) return "无效客服账号(invalid kf_account)";
        else if (returnCode.equals("61453")) return "客服帐号已存在(kf_account exsited)";
        else if (returnCode.equals("61454")) return "客服帐号名长度超过限制(仅允许10个英文字符，不包括@及@后的公众号的微信号)(invalid   kf_acount length)";
        else if (returnCode.equals("61455")) return "客服帐号名包含非法字符(仅允许英文+数字)(illegal character in     kf_account)";
        else if (returnCode.equals("61456")) return "客服帐号个数超过限制(10个客服账号)(kf_account count exceeded)";
        else if (returnCode.equals("61457")) return "无效头像文件类型(invalid   file type)";
        else if (returnCode.equals("61450")) return "系统错误(system error)";
        else if (returnCode.equals("61500")) return "日期格式错误";
        else if (returnCode.equals("65301")) return "不存在此menuid对应的个性化菜单";
        else if (returnCode.equals("65302")) return "没有相应的用户";
        else if (returnCode.equals("65303")) return "没有默认菜单，不能创建个性化菜单";
        else if (returnCode.equals("65304")) return "MatchRule信息为空";
        else if (returnCode.equals("65305")) return "个性化菜单数量受限";
        else if (returnCode.equals("65306")) return "不支持个性化菜单的帐号";
        else if (returnCode.equals("65307")) return "个性化菜单信息为空";
        else if (returnCode.equals("65308")) return "包含没有响应类型的button";
        else if (returnCode.equals("65309")) return "个性化菜单开关处于关闭状态";
        else if (returnCode.equals("65310")) return "填写了省份或城市信息，国家信息不能为空";
        else if (returnCode.equals("65311")) return "填写了城市信息，省份信息不能为空";
        else if (returnCode.equals("65312")) return "不合法的国家信息";
        else if (returnCode.equals("65313")) return "不合法的省份信息";
        else if (returnCode.equals("65314")) return "不合法的城市信息";
        else if (returnCode.equals("65316")) return "该公众号的菜单设置了过多的域名外跳（最多跳转到3个域名的链接）";
        else if (returnCode.equals("65317")) return "不合法的URL";
        else if (returnCode.equals("9001001")) return "POST数据参数不合法";
        else if (returnCode.equals("9001002")) return "远端服务不可用";
        else if (returnCode.equals("9001003")) return "Ticket不合法";
        else if (returnCode.equals("9001004")) return "获取摇周边用户信息失败";
        else if (returnCode.equals("9001005")) return "获取商户信息失败";
        else if (returnCode.equals("9001006")) return "获取OpenID失败";
        else if (returnCode.equals("9001007")) return "上传文件缺失";
        else if (returnCode.equals("9001008")) return "上传素材的文件类型不合法";
        else if (returnCode.equals("9001009")) return "上传素材的文件尺寸不合法";
        else if (returnCode.equals("9001010")) return "上传失败";
        else if (returnCode.equals("9001020")) return "帐号不合法";
        else if (returnCode.equals("9001021")) return "已有设备激活率低于50%，不能新增设备";
        else if (returnCode.equals("9001022")) return "设备申请数不合法，必须为大于0的数字";
        else if (returnCode.equals("9001023")) return "已存在审核中的设备ID申请";
        else if (returnCode.equals("9001024")) return "一次查询设备ID数量不能超过50";
        else if (returnCode.equals("9001025")) return "设备ID不合法";
        else if (returnCode.equals("9001026")) return "页面ID不合法";
        else if (returnCode.equals("9001027")) return "页面参数不合法";
        else if (returnCode.equals("9001028")) return "一次删除页面ID数量不能超过10";
        else if (returnCode.equals("9001029")) return "页面已应用在设备中，请先解除应用关系再删除";
        else if (returnCode.equals("9001030")) return "一次查询页面ID数量不能超过50";
        else if (returnCode.equals("9001031")) return "时间区间不合法";
        else if (returnCode.equals("9001032")) return "保存设备与页面的绑定关系参数错误";
        else if (returnCode.equals("9001033")) return "门店ID不合法";
        else if (returnCode.equals("9001034")) return "设备备注信息过长";
        else if (returnCode.equals("9001035")) return "设备申请参数不合法";
        else if (returnCode.equals("9001036")) return "查询起始值begin不合法";
        else return "其他，未在返回库中找到"+returnCode;
    }

    /**
     * 根据微信返回值得到对应的描述信息
     * @param returnCode 微信返回的code值
     * @return
     */
    public static String GetRetureInfo(Integer returnCode){
        return GetRetureInfo(returnCode.toString());
    }

}
