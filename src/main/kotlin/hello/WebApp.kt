package hello

import com.github.fommil.netlib.BLAS
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.runtime.Micronaut
import smile.interpolation.KrigingInterpolation1D

fun main() {
    Micronaut.build().packages("hello").mainClass(WebApp::class.java).start()
}

@Controller
class WebApp {

    @Get("/")
    fun index(): String {
        val x = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0)
        val y = doubleArrayOf(0.0, 0.8415, 0.9093, 0.1411, -0.7568, -0.9589, -0.2794)

        val linear = KrigingInterpolation1D(x, y)
        linear.interpolate(1.0)

        return "hello, world"
    }

}
