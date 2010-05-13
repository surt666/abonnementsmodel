package dk.yousee.contexts

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import java.util.Date

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 13, 2010
 * Time: 8:04:26 PM
 */

class TestSletAbonnement extends FlatSpec with ShouldMatchers {

  "Delete subscription" should "reprovision 3 deliveries and change period for 1 subscription" in {
    val abon = new SletAbonnement(3,new Date)
    abon.abon.id should equal (3)
  }
}