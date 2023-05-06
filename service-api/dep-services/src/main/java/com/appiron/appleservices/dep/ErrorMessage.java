package com.appiron.appleservices.dep;

/**
 * @author hpg
 * 
 */
public enum ErrorMessage {

    CONFIG_NAME_INVALID("CONFIG_NAME_INVALID", "The profile_name field in the uploaded profile is either" +
            " empty or has exceeded the maximum allowed length (125 UTF-8 characters)."),

    CONFIG_NAME_REQUIRED("CONFIG_NAME_REQUIRED", "The configuration name is missing in the uploaded profile."),

    CONFIG_URL_INVALID("CONFIG_URL_INVALID", "The URL field in the uploaded profile is either empty or has exceeded the maximum allowed length (2000 URL encoded characters)." +
            " The syntax of the URL is defined by RFC 2396: Uniform Resource Identifiers (URI): " +
            "Generic Syntax, amended by RFC 2732: Format for Literal IPv6 Addresses in URLs."),

    CONFIG_URL_REQUIRED("CONFIG_URL_REQUIRED", "The MDM server URL is missing in the uploaded profile."),

    DEPARTMENT_INVALID("DEPARTMENT_INVALID", "The department field in the uploaded profile is either empty or has exceeded the maximum allowed length (125 UTF-8 characters)."),

    FLAGS_INVALID("FLAGS_INVALID", "The flags have been set incorrectly; is_mdm_removable can be set to false only if flag is_supervised is set to true."),

    LOCALE_INVALID("LOCALE_INVALID", "The locale fields combination is invalid or unsupported."),

    MAGIC_INVALID("MAGIC_INVALID", "The magic field in the uploaded profile is either empty or has exceeded the maximum allowed length (256 UTF-8 characters)."),

    SUPPORT_EMAIL_INVALID("SUPPORT_EMAIL_INVALID", "The support_email_address field in the uploaded profile is either empty or has exceeded the maximum allowed length (250 UTF-8 characters)."),

    SUPPORT_PHONE_INVALID("SUPPORT_PHONE_INVALID", "The support_phone_number field in the uploaded profile is either empty or has exceeded the maximum allowed length (50 UTF-8 characters)."),

    USER_AGENT_INVALID("USER_AGENT_INVALID", "The User-Agent header is invalid."),

    USER_AGENT_MISSING("USER_AGENT_MISSING", "The User-Agent header is missing or has no assigned value."),

    DEVICE_ID_REQUIRED("DEVICE_ID_REQUIRED", "The request did not contain any device IDs."),

    PROFILE_UUID_REQUIRED("PROFILE_UUID_REQUIRED", "The request did not contain a profile ID."),

    NOT_FOUND("PROFILE_UUID_REQUIRED", "The requested profile UUID doesn't match a known profile."),

    PROFILE_NOT_FOUND("PROFILE_NOT_FOUND", "The profile with the specified UUID could not be found."),

    MALFORMED_REQUEST_BODY("MALFORMED_REQUEST_BODY", "The request body is malformed."),

    INVALID_CURSOR("INVALID_CURSOR", "An invalid cursor value was provided."),

    EXHAUSTED_CURSOR("EXHAUSTED_CURSOR", "The cursor had returned all devices in previous calls."),

    CURSOR_REQUIRED("CURSOR_REQUIRED", "The cursor value was not provided in the request body."),

    EXPIRED_CURSOR("EXPIRED_CURSOR", "The provided cursor is older than 7 days."),


    UNAUTHORIZED("Unauthorized", "The token has expired. The client should retry with a new auth token."),

    FORBIDDEN("Forbidden", "The auth token is invalid."),

    ;

    private final String body;

    private final String msg;

    ErrorMessage(String body, String msg) {
        this.body = body;
        this.msg = msg;
    }

    public String getBody() {
        return body;
    }

    public String getMsg() {
        return msg;
    }
}
