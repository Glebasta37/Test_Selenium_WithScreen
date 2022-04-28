package custom.properties;

import org.aeonbits.owner.Config;

/**
 * Интерфейс обсуживающий проперти файлы
 */

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
              "file:url.properties"
})
public interface PropsUrl extends Config {

    @Key("base.url.yandex")
    String baseURLYandex();

}
