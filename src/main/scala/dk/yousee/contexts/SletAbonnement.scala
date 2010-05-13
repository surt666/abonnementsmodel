package dk.yousee.contexts

import java.util.Date
import dk.yousee.model.{LeveringsAftale, Periode, Abonnement}
import dk.yousee.repository.Properties
import dk.yousee.utilities.LeveringsAftaleUtility

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:53:23 PM
 */

class SletAbonnement(val abonId : Int, val date : Date) {
  //todo find abonnement for abonid via Repository
  val abon = new Abonnement(3,123,new Periode(new Date,new Date),List[LeveringsAftale](),100,0)
  val faktururingsPeriode = new Periode(abon.faktureringsPeriode.fra,date)
  val abon2 = new Abonnement(abon.id,abon.juridisk,faktururingsPeriode,abon.leveringer,abon.pris,abon.rabat)
  abon2.persist
  for (l <- abon2.leveringer) {
    if (l.properties.keySet.contains(Properties.ProvNum)) {
      val la = LeveringsAftaleUtility.cloneWithProvisonering(l)
      l.properties(Properties.ProvSystem) match {
        case "Stalone" => la.provisionerStalone(l.properties(Properties.ProvNum),date)
        case "Sigma" => la.provisionerSigma(l.properties(Properties.ProvNum),date)
        case _ =>
      }
    }
  }
}