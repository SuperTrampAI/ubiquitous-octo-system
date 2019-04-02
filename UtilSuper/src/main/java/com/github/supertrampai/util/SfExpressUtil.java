/**
 * Copyright 2017-2018 m.ximalayaart.com, All rights reserved 喜马拉雅Art.
 */
package com.github.supertrampai.util;

import io.netty.util.internal.StringUtil;

public class SfExpressUtil {
	public static String getOpName(String opCode) {
		String opName = null;
		if (!StringUtil.isNullOrEmpty(opCode)) {
			switch (Integer.parseInt(opCode)) {
			case 50:
				opName = "已揽件";
				break;
			case 30:
				opName = "中转";
				break;
			case 31:
			case 130:
			case 123:
				opName = "运输中";
				break;
			case 607:
				opName = "代理收件";
				break;
			case 44:
				opName = "派送中";
				break;
			case 80:
				opName = "已签收";
				break;
			case 70:
				opName = "派送失败";
				break;
//			case 8000:
//				opName = "签收信息";
//				break;
//			case 33:
//				opName = "派件异常原因";
//				break;
			case 99:
				opName = "转寄中";
				break;
			case 648:
				opName = "已退回/转寄";
				break;
			default:
				opName = null;
			}
		}
		return opName;
	}

}
