package dk.yousee.contexts

import java.util.Date
import dk.yousee.abonnement.{Abonnement, Periode}
import dk.yousee.repository.{ProduktRepo}
import dk.yousee.utilities.LeveringsAftaleUtility

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:52:21 PM
 */

class OpretAbonnement(abonId : Int, juridisk : Int, betaler : Int, forbruger : Int, produktId : Int) {
  //find ud af om produkt er bundle og opret tilsvarende Leverings aftaler
  val produkt = ProduktRepo.findProdukt(produktId)
  val leveringsAftaler = LeveringsAftaleUtility.findAlleLeveringsAftaler(produkt,abonId,betaler,forbruger)
  val faktureringsPeriode = new Periode(new Date,new Date) //todo beregn
  val abonnement = new Abonnement(abonId,juridisk,faktureringsPeriode,leveringsAftaler,produkt.pris, 0.0)
  abonnement.persist

  
}