package dk.yousee.contexts

import java.util.Date
import dk.yousee.repository.Properties
import dk.yousee.utilities.LeveringsAftaleUtility
import dk.yousee.model._

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:53:23 PM
 */

class SletAbonnement(val abonId : Int, val date : Date) {
  //todo find abonnement for abonid via Repository
  val abon = new Abonnement(3,123,List[LeveringsAftale](),List[BetalingsAftale](), AbonnementStatus.Aktiv)
  //val faktururingsPeriode = new Periode(abon.faktureringsPeriode.fra,date)
  val abon2 = new Abonnement(abon.id,abon.juridisk,abon.leveringsAftaler,abon.betalingsAftaler,AbonnementStatus.Lukket)
  abon2.persist
  for (l <- abon2.leveringsAftaler) {
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