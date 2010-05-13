package dk.yousee.contexts

import org.scalatest.FlatSpec

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 13, 2010
 * Time: 12:51:43 PM
 */

class TestOpretAbonnement extends FlatSpec with org.scalatest.matchers.ShouldMatchers {

  "Opret abonnement" should "oprette leveringsaftaler og abonnement" in {
    val abon = new OpretAbonnement(1,123,234,456,1203101)
    abon.abonnement.id should equal (1)
  }
  
}