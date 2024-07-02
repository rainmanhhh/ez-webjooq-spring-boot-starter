package ez.webjooq

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity

@Suppress("unused")
class Webjooq(
  /**
   * default value is [WebjooqAutoConfig.paginateHeader]
   */
  private val paginateHeader: String
) {
  /**
   * wrapper for [ez.web.responseEntity].
   * convert function [block] should return a [Page] object.
   * example:
   * ```kotlin
   * @RestController
   * class FooApi(
   *   private val webjooq: Webjooq,
   *   private val service: FooService,
   * ) {
   *   @GetMapping("/")
   *   fun fetchPage(pageNo: Int, pageSize: Int) = webjooq.responseEntity {
   *     service.fetchSql().paginate(pageNo, pageSize) {
   *       fetchInto(Foo::class.java)
   *     }
   *   }
   * }
   * ```
   */
  fun <T> responseEntity(block: (ResponseEntity.BodyBuilder) -> Page<T>): ResponseEntity<MutableList<T>> =
    ez.web.responseEntity {
      val page = block(it)
      it.header(paginateHeader, page.totalElements.toString())
      page.content
    }
}
