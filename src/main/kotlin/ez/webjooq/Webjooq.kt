package ez.webjooq

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity

@Suppress("unused")
class Webjooq(
  private val paginateHeader: String
) {
  fun <T> responseEntity(block: (ResponseEntity.BodyBuilder) -> Page<T>): ResponseEntity<MutableList<T>> =
    ez.web.responseEntity {
      val page = block(it)
      it.header(paginateHeader, page.totalElements.toString())
      page.content
    }
}
