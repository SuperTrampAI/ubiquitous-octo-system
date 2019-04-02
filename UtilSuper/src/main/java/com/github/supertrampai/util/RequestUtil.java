package com.github.supertrampai.util;

import javax.servlet.http.HttpServletRequest;

import com.xmlyart.common.constant.NullableEnum;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xmlyart.common.constant.ChannelConsts;

public class RequestUtil {
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.hasText(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.hasText(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	/**
	 * 获取用户id
	 *
	 * @param nullable 结果可否为null
	 * @return
	 */
	public static Integer getUserId(NullableEnum nullable) {
		Integer userId = null;
		String parameterName = "uid";
		String uid = getParameterValue(parameterName);
		if (!uid.isEmpty()) {
			userId = Integer.valueOf(uid);
		}
		if (userId == null && nullable == NullableEnum.FALSE) {
			Et.callEx("用户不存在");
		}
		return userId;
	}

	/**
	 * 获取请求参数值
	 *
	 * @param parameterName 请求参数名
	 * @return
	 */
	private static String getParameterValue(String parameterName) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		return request.getParameter(parameterName);
	}

	/**
	 * 获取渠道
	 * 
	 * @return
	 */
	public static String getChannel() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String channel = request.getHeader("x-channel");
		if (StringUtils.isEmpty(channel)) {
			channel = ChannelConsts.NONE;
		}
		return channel;
	}

	/**
	 * 获取美术馆id
	 * 
	 * @return
	 */
	public static Integer getMuseumId() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String museumId = request.getHeader("x-museumId");
		if (StringUtils.isEmpty(museumId)) {
			Et.callEx("未知的美术馆");
		}
		return Integer.valueOf(museumId);
	}
}
