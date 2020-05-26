# jag-vips-client

VIPS client as part of DPS Interface Modernization project for Ministry of AG/PSSG

## Usage

Add the following dependency to your spring boot application

```xml

    <dependencies>

        <dependency>
            <groupId>ca.bc.gov.open</groupId>
            <artifactId>jag-vips-client</artifactId>
            <version>0.0.2-SNAPSHOT</version>
        </dependency>

    </dependencies>

```

Configure the following properties in your `application.properties`

```
vips.client.basePath=path-to-order
vips.client.username=username
vips.client.password=password
```
