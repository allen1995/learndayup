package com.allen.dayup.java多线程模式设计.chap3;

/**
 * @Auther: allen
 * @Date: 2021-07-17 08:03
 * @Description:
 */
public final class MMCInfo {

    /**
     * 设备ID
     */
    private final String deviceID;

    /**
     * 彩信中心URL
     */
    private final String url;

    /**
     * 彩信中心允许的最大附件大小
     */
    private final int maxAttachmentSizeInBytes;

    public MMCInfo(String deviceID, String url, int maxAttachmentSizeInBytes) {
        this.deviceID = deviceID;
        this.url = url;
        this.maxAttachmentSizeInBytes = maxAttachmentSizeInBytes;
    }

    public MMCInfo(MMCInfo mmcInfo) {
        this.deviceID = mmcInfo.getDeviceID();
        this.url = mmcInfo.getUrl();
        this.maxAttachmentSizeInBytes = mmcInfo.getMaxAttachmentSizeInBytes();
    }

    public String getDeviceID() {
        return deviceID;
    }

    public String getUrl() {
        return url;
    }

    public int getMaxAttachmentSizeInBytes() {
        return maxAttachmentSizeInBytes;
    }
}
