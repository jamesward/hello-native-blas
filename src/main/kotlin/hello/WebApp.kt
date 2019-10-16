package hello

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.runtime.Micronaut

fun main() {
    Micronaut.build().packages("hello").mainClass(WebApp::class.java).start()
}

@Controller
class WebApp {

    @Get("/")
    fun index(): String {
        return "hello, world"
    }

}
