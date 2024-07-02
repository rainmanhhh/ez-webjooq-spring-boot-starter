package ez.webjooq

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean

@Suppress("MemberVisibilityCanBePrivate")
@ConfigurationProperties("ez.webjooq")
class WebjooqAutoConfig {
  /**
   * http header name for pagination total count. default value is "x-total-count"
   */
  var paginateHeader = "x-total-count"

  @Bean
  fun webjooq() = Webjooq(paginateHeader)
}