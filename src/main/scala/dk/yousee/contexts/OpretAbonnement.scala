package dk.yousee.contexts

import java.util.Date
import dk.yousee.repository.{ProduktRepo}
import dk.yousee.utilities.LeveringsAftaleUtility
import dk.yousee.model.{BetalingsAftale, Abonnement, Periode, AbonnementStatus}

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:52:21 PM
 */

class OpretAbonnement(abonId : Int, juridisk : Int, betaler : Int, forbruger : Int, produktId : Int, installationsId : Int) {
  //find ud af om produkt er bundle og opret tilsvarende Leverings aftaler
  val produkt = ProduktRepo.findProdukt(produktId)
  val leveringsAftaler = LeveringsAftaleUtility.findAlleLeveringsAftaler(produkt,abonId,forbruger,installationsId)
  val faktureringsPeriode = new Periode(Some(new Date), Some(new Date)) //todo beregn
  val abonnement = new Abonnement(abonId,juridisk,leveringsAftaler,List[BetalingsAftale](), AbonnementStatus.Aktiv)
  leveringsAftaler.foreach(l => l.persist)
  abonnement.persist
}