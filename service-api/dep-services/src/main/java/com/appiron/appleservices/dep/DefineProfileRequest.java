package com.appiron.appleservices.dep;

import com.appiron.appleservices.common.GenerationConvert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author hpg
 * @link {https://developer.apple.com/documentation/devicemanagement/profileRequest}
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DefineProfileRequest extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * Default is true. In iOS 13, this property was deprecated.
     */
    @JsonProperty("allow_pairing")
    private boolean allowPairing = true;

    /**
     * An array of strings, where each string is a DER-encoded (Distinguished Encoding Rules) certificate converted to Base64 encoding. If provided, the device uses these certificates as trusted anchor certificates when evaluating the trust of the connection to the MDM server URL. Otherwise,
     * the device uses the built-in root certificates.
     */
    @JsonProperty("anchor_certs")
    private List<String> anchorCerts;

    /**
     * If set to true, the device will tell Setup Assistant to automatically advance though its screens.
     * Default is false.
     * This key is valid in X-Server-Protocol-Version 2 and later.
     * <p>
     * Available on tvOS and macOS 11 and later.
     */
    @JsonProperty("auto_advance_setup")
    private boolean autoAdvanceSetup = false;

    /**
     * If true, the device will not continue in Setup Assistant until the MDM server sends a command that
     * states the device is configured (see Release Device from Await Configuration). Default is false.
     * Ignored on iOS devices if is_supervised is false. This key is valid in X-Server-Protocol-Version 2 and later.
     */
    @JsonProperty("await_device_configured")
    private boolean awaitDeviceConfigured = false;

    /**
     * The URL that the clients load into a web view during setup.
     * This site provides the appropriate UI to authenticate the user,
     * and when satisfied, initiates the download of the MDM enrollment profileRequest.
     * To provide the MDM enrollment profileRequest,
     * the web view looks for a page with MIME type application/x-apple-aspen-config.
     * <p>
     * While the user is allowed to navigate to any site or host during authentication,
     * the MDM enrollment profileRequest must originate from the same host as specified in this field.
     */
    @JsonProperty("configuration_web_url")
    private String configurationWebUrl;

    /**
     * Default is true. In iOS 13, this property was deprecated.
     */
    @JsonProperty("department")
    private String department;

    /**
     * Array of strings that contains device serial numbers (may be empty).
     */
    @JsonProperty("devices")
    private List<String> devices;

    /**
     * If true, the user may not skip applying the profileRequest returned by the MDM server. Default is false.
     * </p>
     * In iOS 13 and later, all DEP enrollments are mandatory.
     */
    @JsonProperty("is_mandatory")
    private boolean isMandatory = false;

    /**
     * If false, the MDM payload delivered by the configuration URL cannot be removed by the user via the user interface on the device;
     * that is, the MDM payload is locked onto the device.
     * This key can be set to false only if is_supervised is set to true. Defaults to true.
     */
    @JsonProperty("is_mdm_removable")
    private boolean isMdmRemovable = true;

    /**
     * If true, tells the device to configure for Shared iPad.
     * Default is false.
     * This key is valid only for Apple School Manager or Apple Business Manager organizations
     * using X-Server-Protocol-Version 2 and later.
     * </p>
     * Devices that do not meet the Shared iPad minimum requirements do not honor this command. With iOS devices,
     * com.apple.mdm.per-user-connections must be added to the MDM enrollment profileRequest's Server Capabilities.
     */
    @JsonProperty("is_multi_user")
    private boolean isMultiUser = false;

    /**
     * If true, the device must be supervised. Defaults to false.
     * In iOS 11, DEP devices that are not supervised have been deprecated.
     * <p>
     * In iOS 13, all DEP devices will be supervised and the OS will ignore the is_supervised flag completely.
     */
    @JsonProperty("is_supervised")
    private boolean isSupervised = false;

    /**
     * A language designator is a code that represents a language.
     * Use the two-letter ISO 639-1 standard (preferred) or the three-letter ISO 639-2 standard.
     * If an ISO 639-1 code is not available for a particular language, use the ISO 639-2 code instead.
     * <p>
     * See Language and Locale IDs for more information.
     * <p>
     * Example two-letter: en, fr, ja
     * <p>
     * Example three-letter: eng, fre, jpn, haw
     * <p>
     * Available on tvOS and macOS 11 and later.
     */
    @JsonProperty("language")
    private String language;

    /**
     * A string that uniquely identifies various services that are managed by a single organization.
     */
    @JsonProperty("org_magic")
    private String orgMagic;

    /**
     * A human-readable name for the profileRequest.
     */
    @JsonProperty("profile_name")
    private String profileName;

    /**
     * <p> A region designator is a code that represents a country. Available on tvOS and macOS 11 and later.
     * <p> Use the ISO 3166-1 standard, a two-letter, capitalized code.
     *
     * <p> Examples: US, GB, AU
     */
    @JsonProperty("region")
    private String region;

    /**
     * <h3>
     * Optional. Array of strings. A list of setup panes to skip. The array may contain
     * one or more of the following strings:
     * </h3>
     *
     * <p> • AppleID: Skips Apple ID setup.
     * <p> • Biometric: Skips biometric setup.
     * <p> • Diagnostics: Disables automatically sending diagnostic information.
     * <p> • DisplayTone: Skips DisplayTone setup.
     * <p> • Location: Disables Location Services.
     * <p> • Passcode: Hides and disables the passcode pane.
     * <p> • Payment: Skips Apple Pay setup.
     * <p> • Privacy: Skips privacy pane.
     * <p> • Restore: Disables restoring from backup.
     * <p> • SIMSetup: Skips the add cellular plan pane.
     * <p> • Siri: Disables Siri.
     * <p> • TOS: Skips Terms and Conditions.
     * <p> • Zoom: Skips zoom setup.
     * <p> • Android: If the Restore pane is not skipped, removes the ”Move from Android” option from the Restore pane on iOS.
     * <p> • HomeButtonSensitivity: Skips the Home Button screen in iOS.
     * <p> • iMessageAndFaceTime: Skips the iMessage and FaceTime screen in iOS.
     * <p> • OnBoarding: Skips on-boarding informational screens for user education (“Cover Sheet, Multitasking & Control Center”, for example) in iOS.
     * <p> • ScreenTime: Skips the screen for Screen Time in iOS.
     * <p> • SoftwareUpdate: Skips the mandatory software update screen in iOS.
     * <p> • WatchMigration: Skips the screen for watch migration in iOS.
     * <p> • Appearance: Skips the Choose Your Look screen in macOS.
     * <p> • FileVault: Disables FileVault Setup Assistant screen in macOS.
     * <p> • iCloudDiagnostics: Skips iCloud Analytics screen in macOS.
     * <p> • iCloudStorage: Skips iCloud Documents and Desktop screen in macOS.
     * <p> • Registration: Disables registration screen in macOS.
     * <p> • ScreenSaver: Skips the tvOS screen about using aerial screensavers in ATV.
     * <p> • TapToSetup: Skips the Tap To Set Up option in ATV about using an iOS device to set up your ATV (instead of entering all your account information and setting choices separately).
     * <p> • TVHomeScreenSync: Skips TV home screen layout sync screen in tvOS.
     * <p> • TVProviderSignIn: Skips the TV provider sign in screen in tvOS.
     * <p> • TVRoom: Skips the “Where is this Apple TV?” screen in tvOS.
     */
    @JsonProperty("skip_setup_items")
    private List<String> skipSetupItems;

    /**
     * Each string contains a DER-encoded certificate converted to Base64 encoding.
     * If provided, the device will continue to pair with a host that possesses one of these certificates even when allow_pairing is set to false.
     * If is_supervised is false, this list is unused.
     */
    @JsonProperty("supervising_host_certs")
    private List<String> supervisingHostCerts;

    /**
     * A support email address for the organization. This key is valid in X-Server-Protocol-Version 2 and later.
     */
    @JsonProperty("support_email_address")
    private String supportEmailAddress;

    /**
     * A support phone number for the organization.
     */
    @JsonProperty("support_phone_number")
    private String supportPhoneNumber;

    /**
     * String. The URL of the MDM server.
     */
    @JsonProperty("url")
    private String url;
}
