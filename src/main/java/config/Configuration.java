package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:conf/general.properties"})
public interface Configuration extends Config {

    @Key("url.base")
    String url();

    @Key("timeout")
    String timeout();

    @Key("selenoid.url")
    String selenoidUrl();

    @Key("selenoid.port")
    String selenoidPort();

    @Key("wait.duration")
    String waitDuration();

    @Key("chrome.version")
    String chromeVersion();

    @Key("firefox.version")
    String firefoxVersion();
}
