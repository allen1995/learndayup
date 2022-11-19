package com.allen.dayup.arithmetic.二分查找;

import java.util.ArrayList;
import java.util.List;

public class AppLockEventPushSetup {
    /**
     * APP推送设置
     */
	private boolean appPush = true;
	/**
	 * app非法开门告警
	 */
	private boolean appIllegalOpenDoorAlarm = true;
	/**
	 * app门未锁好告警
	 */
	private boolean appDoorUnlockedAlarm = true;
	/**
	 * app门外逗留检测告警
	 */
	private boolean appActiveDefenseAlarm = true;
	/**
	 * app禁试告警
	 */
	private boolean appForbiddenAlarm = true;
	/**
	 * app门铃提醒
	 */
	private boolean appDoorbellNotification = true;
	
    /**
     * 短信推送设置
     */
	private boolean msgPush = true;
	/**
	 * 短信非法开门告警
	 */
	private boolean msgIllegalOpenDoorAlarm = true;
	/**
	 * 短信门未锁好告警
	 */
	private boolean msgDoorUnlockedAlarm = false;
	/**
	 * 短信门外逗留检测告警
	 */
	private boolean msgActiveDefenseAlarm = false;
	/**
	 * 短信禁试告警
	 */
	private boolean msgForbiddenAlarm = false;
	/**
	 * 短信门铃提醒
	 */
	private boolean msgDoorbellNotification = false;	
	private List<MobileMsgAccepter> list = new ArrayList<MobileMsgAccepter>();
	
	public AppLockEventPushSetup() {
		
	}

	public boolean isAppPush() {
		return appPush;
	}

	public void setAppPush(boolean appPush) {
		this.appPush = appPush;
	}

	public boolean isAppIllegalOpenDoorAlarm() {
		return appIllegalOpenDoorAlarm;
	}

	public void setAppIllegalOpenDoorAlarm(boolean appIllegalOpenDoorAlarm) {
		this.appIllegalOpenDoorAlarm = appIllegalOpenDoorAlarm;
	}

	public boolean isAppDoorUnlockedAlarm() {
		return appDoorUnlockedAlarm;
	}

	public void setAppDoorUnlockedAlarm(boolean appDoorUnlockedAlarm) {
		this.appDoorUnlockedAlarm = appDoorUnlockedAlarm;
	}

	public boolean isAppActiveDefenseAlarm() {
		return appActiveDefenseAlarm;
	}

	public void setAppActiveDefenseAlarm(boolean appActiveDefenseAlarm) {
		this.appActiveDefenseAlarm = appActiveDefenseAlarm;
	}

	public boolean isAppForbiddenAlarm() {
		return appForbiddenAlarm;
	}

	public void setAppForbiddenAlarm(boolean appForbiddenAlarm) {
		this.appForbiddenAlarm = appForbiddenAlarm;
	}

	public boolean isAppDoorbellNotification() {
		return appDoorbellNotification;
	}

	public void setAppDoorbellNotification(boolean appDoorbellNotification) {
		this.appDoorbellNotification = appDoorbellNotification;
	}

	public boolean isMsgPush() {
		return msgPush;
	}

	public void setMsgPush(boolean msgPush) {
		this.msgPush = msgPush;
	}

	public boolean isMsgIllegalOpenDoorAlarm() {
		return msgIllegalOpenDoorAlarm;
	}

	public void setMsgIllegalOpenDoorAlarm(boolean msgIllegalOpenDoorAlarm) {
		this.msgIllegalOpenDoorAlarm = msgIllegalOpenDoorAlarm;
	}

	public boolean isMsgDoorUnlockedAlarm() {
		return msgDoorUnlockedAlarm;
	}

	public void setMsgDoorUnlockedAlarm(boolean msgDoorUnlockedAlarm) {
		this.msgDoorUnlockedAlarm = msgDoorUnlockedAlarm;
	}

	public boolean isMsgActiveDefenseAlarm() {
		return this.msgActiveDefenseAlarm;
	}

	public void setMsgActiveDefenseAlarm(boolean msgActiveDefenseAlarm) {
		this.msgActiveDefenseAlarm = msgActiveDefenseAlarm;;
	}

	public boolean isMsgForbiddenAlarm() {
		return msgForbiddenAlarm;
	}

	public void setMsgForbiddenAlarm(boolean msgForbiddenAlarm) {
		this.msgForbiddenAlarm = msgForbiddenAlarm;
	}

	public boolean isMsgDoorbellNotification() {
		return msgDoorbellNotification;
	}

	public void setMsgDoorbellNotification(boolean msgDoorbellNotification) {
		this.msgDoorbellNotification = msgDoorbellNotification;
	}

	public List<MobileMsgAccepter> getList() {
		return list;
	}

	public void setList(List<MobileMsgAccepter> list) {
		this.list = list;
	}
}
