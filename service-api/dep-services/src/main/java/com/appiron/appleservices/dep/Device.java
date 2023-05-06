package com.appiron.appleservices.dep;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author hpg
 */
@Data
@ToString
@Accessors(chain = true)
public class Device implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * The deviceʼs asset tag (string), if provided by Apple.
     */
    @JsonProperty("asset_tag")
    private String assetTag;

    /**
     * The color of the device (string).
     */
    @JsonProperty("color")
    private String color;

    /**
     * A description of the device (string).
     */
    @JsonProperty("description")
    private String description;

    /**
     * The email of the person who assigned the device.
     */
    @JsonProperty("device_assigned_by")
    private String deviceAssignedBy;

    /**
     * A time stamp in ISO 8601 format indicating when the device was enrolled in the
     * Device Enrollment Program.
     */
    @JsonProperty("device_assigned_date")
    private String deviceAssignedDate;

    /**
     * The deviceʼs Apple product family: iPad, iPhone, iPod, Mac, or AppleTV. This
     * key is valid in X-Server-Protocol-Version 2 and later.
     */
    @JsonProperty("device_family")
    private String deviceFamily;

    /**
     * The model name (string).
     */
    @JsonProperty("model")
    private String model;

    /**
     * A time stamp in ISO 8601 format indicating when the device was added, updated,
     * or deleted. If the value of op_type is added, this is the same as
     * device_assigned_date.
     */
    @JsonProperty("op_date")
    private String opDate;

    /**
     * Indicates whether the device was added (assigned to the MDM server), modified,
     * or deleted. Contains one of the following strings: added, modified, or deleted.
     */
    @JsonProperty("op_type")
    private String opType;

    /**
     * The deviceʼs operating system: iOS, OSX, or tvOS. This key is valid in
     * X-Server-Protocol-Version 2 and later.
     */
    @JsonProperty("os")
    private String os;

    /**
     * A time stamp in ISO 8601 format indicating when a profile was assigned to the
     * device. If a profile has not been assigned, this field may be absent.
     */
    @JsonProperty("profile_assign_time")
    private String profileAssignTime;

    /**
     * A time stamp in ISO 8601 format indicating when a profile was pushed to the
     * device. If a profile has not been pushed, this field may be absent.
     */
    @JsonProperty("profile_push_time")
    private String profilePushTime;

    /**
     * The status of profile installation—either ”empty”, ”assigned”, ”pushed”, or ”removed”.
     */
    @JsonProperty("profile_status")
    private String profileStatus;

    /**
     * The unique ID of the assigned profile.
     */
    @JsonProperty("profile_uuid")
    private String profileUUID;

    /**
     * The deviceʼs serial number (string).
     */
    @JsonProperty("serial_number")
    private String serialNumber;

}
