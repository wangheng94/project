# service-api

service-api is a Java library for Deployment Services.

# Getting service-api
use maven.
```xml
<dependency>
    <groupId>com.appiron.apple.services</groupId>
    <artifactId>dep-services</artifactId>
    <version>${current.Latest.version}</version>
</dependency>
```
if not use log on exclusion log.
```xml
<dependency>
    <groupId>com.appiron.apple.services</groupId>
    <artifactId>dep-services</artifactId>
    <version>${current.Latest.version}</version>
    <exclusions>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

# DEP Example

Create DEPManagement by token in text.

```java
String token = 'your token';
DEPManagement depManagement = DEPManagement.create()
        .setTransport(new ApacheHttpTransport())
        .setToken(token).build();
```

Create DEPManagement by cert and key.
```java
DEPManagement depManagement = DEPManagement.create()
                .setCert(ResourceUtil.getResourceInputStream("cert/secb117_Token_2023-01-03T08-43-26Z_smime.p7m"))
                .setKey(ResourceUtil.getResourceInputStream("cert/ca.key"), "123456")
                .setTransport(new ApacheHttpTransport())
                .build();
```

device list
```java
    @Test
    public void listDevices() throws Exception {
        FetchDeviceRequest request = new FetchDeviceRequest();
        request.setLimit(50);
        FetchDeviceResponse fetchDeviceResponse = null;
        // DefineProfileResponse(devices=null, profileUUID=AAD54C578AD0C7E0E213059881A49DB4)
        do {
            fetchDeviceResponse = depManagement.api().listDevices(request);
            request.setCursor(fetchDeviceResponse.getCursor());
        } while (fetchDeviceResponse.getMoreToFollow());
    }
```