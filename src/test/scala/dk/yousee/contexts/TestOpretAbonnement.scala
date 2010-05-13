package dk.yousee.contexts

import org.scalatest.FlatSpec

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 13, 2010
 * Time: 12:51:43 PM
 */

class TestOpretAbonnement extends FlatSpec with org.scalatest.matchers.ShouldMatchers {

  "Opret YouSee Plus abonnement" should "oprette leveringsaftaler og abonnement" in {
    val abon = new OpretAbonnement(1,123,234,456,1203101)
    abon.abonnement.id should equal (1)
    abon.abonnement.leveringer.length should equal (3)
  }

  "Opret YouSee Silver abonnement" should "oprette leveringsaftaler og abonnement" in {
    val abon = new OpretAbonnement(2,123,234,456,1701001)
    abon.abonnement.id should equal (2)
    abon.abonnement.leveringer.length should equal (4)
  }
  
}