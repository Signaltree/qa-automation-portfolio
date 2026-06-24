package karate

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._

class LoadTest extends Simulation {

  val protocol = karateProtocol()

  val getPosts = scenario("API Load Test")
    .exec(karateFeature("classpath:features/load/api-simulation.feature"))

  setUp(
    getPosts
      .inject(
        rampUsers(10).during(10),
        rampUsers(20).during(20),
        constantUsersPerSec(5).during(30)
      )
      .protocols(protocol)
  ).maxDuration(120)
}
